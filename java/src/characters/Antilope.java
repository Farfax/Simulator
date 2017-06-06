package characters;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.RandomGenerator;
import mathX.Vector2i;

public class Antilope extends Hero
{

	@Override
	public Color color()
	{
		return Color.PINK;
	}
	
	public Antilope(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(4, 4, 'A', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Antilope(position, super._map, super._statusBar, super.game);
	}

	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexPink.png");
	}
	@Override
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{
		return super.onAction(heros,2);
	}

	@Override
	public boolean reflect(Hero attacker)
	{
		Vector2i newpos;
		Vector2i oldPos=position().get();
		ArrayList<Hero> temp= new ArrayList<>();
		
		int escapeAttempt = 0; 
		
			do
			{
				escapeAttempt++;
				newpos = super.onAction(temp, 1);
				
				if (escapeAttempt > 100)		///don't want infinite escape
					return false;

			} while (!(newpos.equals(new Vector2i(-1, -1)) || oldPos.equals(newpos)));

		attacker.position(oldPos);
		return true;
	}
	
	@Override
	public Action onColission(Hero attacker)
	{

		if (RandomGenerator.getInt(0, 2)==1)	//50% chance for escape 1 if fail
		{
			if (reflect(attacker))
			{
				super.reflect(attacker); // generate comunicate
				return Action.REFLECTED;
			}
		}

			return super.onColission(attacker);
		
	}
}
