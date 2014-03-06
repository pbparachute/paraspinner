package paraSpinner;

import org.parabot.api.methods.Bank;
import org.parabot.api.methods.Game;
import org.parabot.api.methods.Inventory;
import org.parabot.api.methods.Menu;
import org.parabot.api.methods.Players;
import org.parabot.api.util.Time;
import org.parabot.api.walking.web.Web;
import org.parabot.api.walking.web.WebPath;
import org.parabot.api.wrappers.Tile;

import paraSpinner.Condition;

public class data {
	public static boolean playerIsInBounds(Tile one, Tile two) {
		int startX = one.getX();
		int endX = two.getX();

		int startY = one.getY();
		int endY = two.getY();

		int playerX = Players.getMyPlayer().getLocation().getX();
		int playerY = Players.getMyPlayer().getLocation().getY();

		if (playerX >= startX && playerX <= endX && playerY <= startY
				&& playerY >= endY) {
			return true;
		}
		return false;
	}
	
	public static float calculateXPH(int xpg) {
		float xpsec = 0;
		if (((minutes > 0) || (hours > 0) || (seconds > 0)) && (xpg > 0)) {
			xpsec = xpg / (float) (seconds + minutes * 60 + hours * 60 * 60);
		}

		float xpmin = xpsec * 60;
		float xphour = xpmin * 60;

		return Math.round(xphour);
	}

	public static void webWalkTo(Tile dest) {
		Tile t = Players.getMyPlayer().getLocation();
		WebPath web = Web.getPath(t, dest);

		while (dest.getDistance() > 6) {
			web.traverse();
		}

		Time.sleep(950);
	}
	
	public static String format(long f) {
		return (String.valueOf(f).length() == 1) ? 0 + "" + f : "" + f;
	}

	// Item and Object ID's
	public static final int ID_BANKBOOTH = 2213;
	public static final int ID_FLAX = 1780;
	public static final int ID_BOWSTRINGS = 1778;
	public static final int ID_WHEEL = 2644;
	public static final int ID_STAIRS = 1747;
	public static final int ID_STAIRS_DOWN = 1746;
	public static final int ANIM_SPINNING = 896;
	public static int bowstringsmade;
	
	public static long hours;
	public static long minutes;
	public static long seconds;
	public static long millis;
	public static long startTime;
	
	public static int startxp;
	public static int currentxp;
	public static int xpgained;
	
	
	// Locaties
	public static Tile TILE_BANK = new Tile(2725, 3492);
	public static Tile TILE_STAIRS = new Tile(2714, 3471);
	public static Tile TILE_STAIRS2 = new Tile(2715, 3470);

	// Paths
	public static Tile[] PATH_STAIRS_TO_BANK = { new Tile(2715, 3471),
			new Tile(2717, 3472), new Tile(2719, 3472), new Tile(2721, 3472),
			new Tile(2723, 3474), new Tile(2724, 3476), new Tile(2725, 3478),
			new Tile(2725, 3480), new Tile(2724, 3482), new Tile(2724, 3484),
			new Tile(2725, 3486), new Tile(2725, 3488), new Tile(2725, 3490),
			new Tile(2726, 3492) };
	
	public static Tile[] PATH_BANK_TO_STAIRS = {
			new Tile(2725, 3491),	new Tile(2725, 3489),	new Tile(2725, 3487),	new Tile(2726, 3485),
			new Tile(2727, 3483),	new Tile(2727, 3481),	new Tile(2726, 3479),	new Tile(2726, 3477),
			new Tile(2724, 3475),	new Tile(2722, 3474),	new Tile(2720, 3474),	new Tile(2717, 3472), new Tile(2715, 3472)
		};


	// Areas
	public static boolean inBankArea() {
		return playerIsInBounds(new Tile(2718, 3498), new Tile(2732, 3490));
	}

	public static boolean inHuisArea() {
		return playerIsInBounds(new Tile(2709, 3473), new Tile(2715, 3470));
	}
	public static boolean inFailArea() {
		return playerIsInBounds(new Tile(2709, 3469), new Tile(2718, 3467));
	}
	public static boolean InArea() {
		return playerIsInBounds(new Tile(2706, 3499), new Tile(2734, 3469));
	}
	
	public static final Condition isOpen = new Condition(2500) {
		public boolean isValid() {
			return Bank.isOpen();
		}
	};
	public static final Condition isEmpty = new Condition(1500) {
		public boolean isValid() {
			return !Inventory.isFull();
		}
	};
	public static final Condition upstairs = new Condition(5000) {
		public boolean isValid() {
			return (Game.getPlane() == 1);
		}
	};
	public static final Condition downstairs = new Condition(5000) {
		public boolean isValid() {
			return (Game.getPlane() == 0);
		}
	};
	public static final Condition NotMoving = new Condition(1500) {
		public boolean isValid() {
			return !Players.getMyPlayer().isMoving()
					&& Players.getMyPlayer().getAnimation() == -1;
		}
	};
	public static final Condition genoeg = new Condition(1500) {
		public boolean isValid() {
			return (Menu.getMenuItems().length > 3);
		}
	};
	public static final Condition DoneWithSpinning2 = new Condition(5000) {
		public boolean isValid() {
			return Inventory.getCount(ID_BANKBOOTH) > 10;
		}
	};
	public static final Condition DoneWithSpinning1 = new Condition(5000) {
		public boolean isValid() {
			return Inventory.getCount(ID_BOWSTRINGS) > 5;
		}
	};
	public static final Condition DoneWithSpinning3 = new Condition(80000) {
		public boolean isValid() {
			return !Inventory.contains(ID_FLAX);
		}
	};

	public static void sleepUntill(Condition condition) {
		long timeOut = System.currentTimeMillis() + condition.getTimeout();
		while (!condition.isValid() && System.currentTimeMillis() < timeOut) {
			Time.sleep(50);
		}
	}

	public static String status = "null";
}
