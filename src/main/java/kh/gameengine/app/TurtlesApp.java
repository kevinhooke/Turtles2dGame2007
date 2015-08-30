package kh.gameengine.app;

import kh.gameengine.GameFrame;
import kh.gameengine.ui.TurtlesGameCanvas;

/**
 * Main app entry point for 2d game, Turtles.
 * 
 * @author kevinhooke
 *
 */
public class TurtlesApp extends GameFrame {

	public static void main(String[] args)
	{
		new TurtlesApp().startUp( new TurtlesGameCanvas() );
	}
}
