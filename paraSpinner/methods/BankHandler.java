package paraSpinner.methods;

import org.parabot.api.methods.Bank;
import org.parabot.api.methods.Camera;
import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Inventory;
import org.parabot.api.script.Strategy;
import org.parabot.api.wrappers.GameObject;

import paraSpinner.data;

public class BankHandler extends Strategy {

	@Override
	public boolean isValid() {
		if (data.inBankArea()) {
			GameObject bankbooth = GameObjects.getNearest(data.ID_BANKBOOTH);
			if (bankbooth.isOnScreen()) {
				if (Inventory.getCount(data.ID_FLAX) < 20) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void run() {
		GameObject bankbooth = GameObjects.getNearest(data.ID_BANKBOOTH);
		data.status = "Banking";
		if (bankbooth != null) {
				if (Bank.isOpen()) {
					Bank.depositAllExcept(data.ID_FLAX);
					data.sleepUntill(data.isEmpty);

					if (!Inventory.isFull()) {
						if (Inventory.getCount(data.ID_FLAX) < 20) {
							Bank.getItem(data.ID_FLAX).interact("Withdraw All");
						}
					}
				}else{
					bankbooth.interact("Use-quickly");
					data.sleepUntill(data.isOpen);
				}

			}
		}

	}


