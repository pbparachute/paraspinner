package paraSpinner;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.parabot.api.methods.Mouse;
import org.parabot.api.methods.Skill;
import org.parabot.api.script.Script;
import org.parabot.api.script.ScriptDetails;
import org.parabot.api.script.Strategy;
import org.parabot.api.script.events.MessageEvent;
import org.parabot.core.bot.gui.BotGUI;
import org.parabot.core.bot.impl.listeners.MessageListener;
import org.parabot.core.bot.impl.listeners.Painter;


import paraSpinner.methods.BankHandler;
import paraSpinner.methods.ClimbLadder;
import paraSpinner.methods.SpinHandler;
import paraSpinner.methods.WalkHandler;
import paraSpinner.util.PaintHandler;

@ScriptDetails(author = "Parachute", category = "Crafting", description = "Spins your flax", name = "paraSpinner", version = 2.0)
public class paraSpinner extends Script implements Painter, MessageListener {
	ArrayList<Strategy> jobs = new ArrayList<Strategy>();

	@Override
	protected boolean onExecute() {
		BotGUI.error("paraSpinner started up succesfully.");
		BotGUI.error("Please do not use more than 2 bots with this script. And don't run it too long.");
		BotGUI.error("If I see the bowstrings crashing I'll remove the script. -Parachute.");
		data.startTime = System.currentTimeMillis();
		data.startxp =  Skill.CRAFTING.getCurrentExp();
		jobs.add(new WalkHandler());
		jobs.add(new BankHandler());
		jobs.add(new ClimbLadder());
		jobs.add(new SpinHandler());

		provide(jobs);

		return true;

	}

	@Override
	protected void onFinish() {
	}

	@Override
	public void paint(Graphics g) {
		PaintHandler.draw(g);
		g.drawOval(Mouse.getX() - 7, Mouse.getY() - 7, 14, 14);
		  g.setColor(Color.BLUE);
		  g.drawOval(Mouse.getX() - 5, Mouse.getY() - 5, 10, 10);
		  g.setColor(Color.CYAN);
		  g.drawOval(Mouse.getX() - 3, Mouse.getY() - 3, 6, 6);
		  g.setColor(Color.WHITE);
		  g.drawOval(Mouse.getX(), Mouse.getY(), 1, 1);

		  g.drawLine(Mouse.getX()-9,  Mouse.getY(),  Mouse.getX()+9,  Mouse.getY());
		  
		  g.drawLine(Mouse.getX(),  Mouse.getY()-9,  Mouse.getX(),  Mouse.getY()+9);
	}

	@Override
	public void onMessageReceived(MessageEvent msg) {
		final String txt = msg.getMessage();
		
		if(txt.contains("You make the Flax into a")) {
			data.bowstringsmade++;
		}
		
	}

}
