package gui.swing.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import core.simulator.GameMode;

public class LoadEvent implements ActionListener
{

	GameMode game;
	public LoadEvent(GameMode game)
	{
		this.game=game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent a)
	{
		game.load("example");
		// TODO Auto-generated method stub

	}

}
