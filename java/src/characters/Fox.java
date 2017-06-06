package characters;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.NotExistException;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class Fox extends Hero
{

	@Override
	public Color color()
	{
		return Color.MAGENTA;
	}
	
	public Fox(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(7, 3, 'F', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Fox(position, super._map, super._statusBar, super.game);
	}

	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexMagenta.png");
	}
	@Override
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{

		Vector2i newPos;
		int safeAttempt=0;
		do
		{
			safeAttempt++;
			
			newPos=super.onAction(heros, 1);
			
			if(safeAttempt>100)
				return newPos;
		}
		while(!isSafe(newPos, heros));

		return newPos;
	}

	
	private boolean isSafe(Vector2i position, ArrayList<Hero> heros)
	{
		// sometime find() cannot find hero and return null, it's bug need to fix it TODO:FIX FIND BUG
		if (position.equals(new Vector2i(-1,-1)) || _map[position.y()][position.x()]== ' ')
			return true;
		else
			try
			{
				Hero temp=GameMode.findByPosition(heros, position);

				if (temp.strengh() < strengh())
					return true;
				else
					return false;
			} catch (NotExistException e)
			{
				// TODO Auto-generated catch block
				return true;
			}
	}

}
