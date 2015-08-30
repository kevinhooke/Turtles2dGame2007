package kh.gameengine.app;

import kh.gameengine.GameApplet;
import kh.gameengine.ui.TurtlesGameCanvas;

/**
 * Applet wraper for Turtles game.
 * 
 * @author kevinhooke
 *
 */
public class TurtlesApplet extends GameApplet {

	@Override
	public void initGameCanvas()
	{
		this.gameCanvas = new TurtlesGameCanvas();
	}
}
