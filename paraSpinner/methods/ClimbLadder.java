package paraSpinner.methods;

import org.parabot.api.methods.Camera;
import org.parabot.api.methods.Game;
import org.parabot.api.methods.GameObjects;
import org.parabot.api.methods.Inventory;
import org.parabot.api.script.Strategy;
import org.parabot.api.wrappers.GameObject;

import paraSpinner.data;

public class ClimbLadder extends Strategy {

	@Override
	public boolean isValid() {
			//if (data.inHuisArea()) {
				if (Game.getPlane() == 0) {
					if (Inventory.getCount(data.ID_FLAX) > 20) {
						return true;
					}
				//}
			}
		

		if (Game.getPlane() == 1) {
			if (Inventory.getCount(data.ID_BOWSTRINGS) == 28) {
				if (data.inHuisArea()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void run() {
		data.status = "Climbing up";
		GameObject ladder = GameObjects.getNearest(data.ID_STAIRS);
		if (!data.inFailArea()) {
			if (Game.getPlane() == 0) {
				if (ladder != null) {
					Camera.setPitch(true);
						ladder.interact("Climb-up");
						data.sleepUntill(data.upstairs);
					}
				}
			}
		

		data.status = "Climbing down";
		GameObject ladder2 = GameObjects.getNearest(data.ID_STAIRS_DOWN);
		if (ladder2 != null) {
			if (Inventory.getCount(data.ID_BOWSTRINGS) == 28) {
				Camera.setCameraRotation(189);
				ladder2.interact("Climb-down");
				data.sleepUntill(data.downstairs);

				}
			}
		}
	

}