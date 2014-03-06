package paraSpinner.methods;

import org.parabot.api.methods.Camera;
import org.parabot.api.methods.Game;
import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Keyboard;
import org.parabot.api.methods.Menu;
import org.parabot.api.methods.Mouse;
import org.parabot.api.methods.Players;
import org.parabot.api.script.Strategy;
import org.parabot.api.util.Time;
import org.parabot.api.wrappers.GameObject;

import paraSpinner.data;

public class SpinHandler extends Strategy {

	@Override
	public boolean isValid() {
		if (Players.getMyPlayer().getAnimation() == -1) {
			if (Game.getPlane() == 1) {
				if (data.inHuisArea()) {
					if (Inventory.contains(data.ID_FLAX)) {
						return true;
					}
				}
			}
		}
		return false;

	}

	@Override
	public void run() {
		data.status = "Spinning";
		GameObject wheel = GameObjects.getNearest(data.ID_WHEEL);
		if (Players.getMyPlayer().getAnimation() == -1) {
			if (Inventory.contains(data.ID_FLAX)) {
				if (wheel != null) {
					Camera.turnTo(wheel);
					Camera.setPitch(true);	
						wheel.interact("Spin");
						Time.sleep(1000);
						while (Players.getMyPlayer().isMoving()) {
							Time.sleep(40);
						}
						Mouse.hop(254, 419);
						Time.sleep(2000);
						Mouse.click(false);
						if (Menu.getMenuItems().length > 3) {
							Time.sleep(2000);
							Menu.interact("Make X");
							Time.sleep(2500);
							Keyboard.sendKeys("28");
							Time.sleep(2500);
							if (Players.getMyPlayer().getAnimation() == 896) {
								data.sleepUntill(data.DoneWithSpinning3);
							}

						}

				}

			}
		}
	}
}
