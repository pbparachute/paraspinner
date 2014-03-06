package paraSpinner.methods;

import org.parabot.api.methods.Camera;
import org.parabot.api.methods.Game;
import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Players;
import org.parabot.api.script.Strategy;
import org.parabot.api.walking.Path;
import org.parabot.api.wrappers.GameObject;
import org.parabot.api.wrappers.Tile;

import paraSpinner.data;

public class WalkHandler extends Strategy {

	@Override
	public boolean isValid() {
		if (Game.getPlane() == 0) {
			if (Inventory.getCount(data.ID_FLAX) > 20) {
				if (!data.inHuisArea()) {
					return true;
				}
			}

			if (Inventory.getCount(data.ID_FLAX) < 20) {
				if (!data.inBankArea()) {
					return true;
				}
			}

			if (!data.InArea()) {
				return true;
			}
		}

		return false;

	}

	@Override
	public void run() {
		GameObject ladder = GameObjects.getNearest((data.TILE_STAIRS2),
				data.ID_STAIRS);
		if (data.InArea()) {
			if (Inventory.getCount(data.ID_FLAX) > 20) {
						Camera.turnTo(ladder);
						data.status = "Going to Wheel";
						Path route = new Path(data.PATH_BANK_TO_STAIRS);
						if (!Players.getMyPlayer().isMoving()) {
							if (data.TILE_STAIRS.getDistance() > 1) {
								route.traverse();

							}
						}
					}
				}
			
			if (data.InArea()) {
				GameObject bankbooth = GameObjects
						.getNearest(data.ID_BANKBOOTH);
				if (Inventory.getCount(data.ID_FLAX) < 20) {
					if (!Players.getMyPlayer().isMoving()) {
						if (Players.getMyPlayer().getAnimation() == -1) {
								Camera.turnTo(bankbooth);
								data.status = "Going to Bank";
								Path route = new Path(data.PATH_STAIRS_TO_BANK);
								if (!Players.getMyPlayer().isMoving()) {
									if (data.TILE_BANK.getDistance() > 3) {
										route.traverse();
									}

								}
							}

						

					}
				}

			}
			if (!data.InArea()) {
				if (!Players.getMyPlayer().isMoving()) {
					if (Players.getMyPlayer().getAnimation() == -1) {
						data.status = "Walking to bank";
						data.webWalkTo(data.TILE_BANK);
					}
				}
			}
		}
	}
