package characters;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class SowThistle extends Plant
{
	@Override
	public Color color()
	{
		return Color.ORANGE;
	}

	public SowThistle(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(0, 'M', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new SowThistle(position, super._map, super._statusBar, super.game);
	}

	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexOrange.png");
	}
	@Override
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{
		int copulateAttempt = 0;
		Vector2i kid;
		
			do
			{
				copulateAttempt++;
				kid = super.onAction(heros, 0);
				
			} while (kid.equals(new Vector2i(-1, -1)) && copulateAttempt < 3);

			return kid;
	}
}
