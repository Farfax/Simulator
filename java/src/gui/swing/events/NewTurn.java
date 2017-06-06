package gui.swing.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import core.simulator.GameMode;

public final class NewTurn implements ActionListener
{

	GameMode game;
	public NewTurn(GameMode game)
	{
		this.game=game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		game.doTurn();
		game.draw();
		

	//	GameMode.newTurn=true;
		// TODO Auto-generated method stub

	}

}
