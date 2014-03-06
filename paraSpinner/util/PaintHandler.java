package paraSpinner.util;

import java.awt.*;
import javax.imageio.ImageIO;

import org.parabot.api.methods.Skill;

import paraSpinner.data;

import java.io.IOException;
import java.net.URL;

public class PaintHandler {

	private static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	private final static Color color1 = new Color(0, 0, 0);

	private final static Font font1 = new Font("Verdana", 1, 11);

	private final static Image img1 = getImage("http://i50.tinypic.com/2v3sbrr.png");
	private final static Image img2 = getImage("http://www.global-rs.com/img/bow_string.gif");

	public static void draw(Graphics g1) {

		data.millis = System.currentTimeMillis() - data.startTime;
		data.hours = data.millis / (1000 * 60 * 60);
		data.millis -= data.hours * (1000 * 60 * 60);
		data.minutes = data.millis / (1000 * 60);
		data.millis -= data.minutes * (1000 * 60);
		data.seconds = data.millis / 1000;
		
		data.xpgained = Skill.CRAFTING.getCurrentExp() - data.startxp;

		Graphics2D g = (Graphics2D) g1;
		g.drawImage(img1, 4, 296, null);
		g.setFont(font1);
		g.setColor(color1);
		g.drawString(
				"Runtime: " + data.format(data.hours) + ":"
						+ data.format(data.minutes) + ":"
						+ data.format(data.seconds), 10, 322);
		 g.drawString("Xp Gained: " + data.xpgained + "(" + data.calculateXPH(data.xpgained) + ")", 170, 333);
	     g.drawString("Bowstrings made: " + data.bowstringsmade + "(" + data.calculateXPH(data.bowstringsmade) + ")", 170, 312);
	     g.drawImage(img2, 343, 302, null);
	}

}
