package characters;

import java.awt.Color;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class Belladonna extends Plant
{

	@Override
	public Color color()
	{
		return Color.BLUE;
	}
	
	public Belladonna(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(99, 'B', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Belladonna(position, super._map, super._statusBar, super.game);
	}
	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexBlue.png");
	}

	@Override
	public Action onColission(Hero attacker)
	{
		attacker.die();
		die();
		return Action.ALL_DIED;
	}
}
