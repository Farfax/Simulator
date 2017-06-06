package gui.swing.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.simulator.GameMode;

public class SaveEvent implements ActionListener
{

	GameMode game;
	public SaveEvent(GameMode game)
	{
		this.game=game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent aS)
	{
		game.save("example");

	}

}
