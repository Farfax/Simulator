package characters;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.NotExistException;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class Sosnowsky extends Plant
{
	@Override
	public Color color()
	{
		return Color.CYAN;
	}

	public Sosnowsky(Vector2i position, char[][] map,
			StatusBar statusBar , GameMode game)
	{
		super(10, 'Y', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Sosnowsky(position, super._map, super._statusBar, super.game);
	}

	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexCyan.png");
	}
	
	@Override
	public Action onColission(Hero attacker)
	{
		attacker.addStrengh(-attacker.strengh());
		super.onColission(attacker);
		die();
		return Action.ALL_DIED;
	}

	@Override
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{
		for(int i=-1;i<2;i++)
			for (int j = -1; j < 2; j++)
			{
				Vector2i toKill = position().get().add(new Vector2i(i,j));
				
				Hero foundHero;
				try
				{
					foundHero = GameMode.findByPosition(heros, toKill);
				} catch (NotExistException e)
				{
					continue;
				}

					if ( !(foundHero instanceof Plant) && !foundHero.equals(this))
						foundHero.die();
			}

			return super.onAction(heros, moveRange);
	}
}

