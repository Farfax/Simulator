package characters;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.RandomGenerator;
import mathX.Vector2i;

public class Turtle extends Hero
{
	@Override
	public Color color()
	{
		return Color.LIGHT_GRAY;
	}

	public Turtle(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(1, 2, 'T', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public characters.Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Turtle(position, super._map, super._statusBar, super.game);
	}
	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexLightGray.png");
	}
	
	@Override
	public Action onColission(Hero attacker)
	{
		if (attacker.strengh() <= 5)
		{
			super.reflect(attacker); //generate comunicta
			return Action.REFLECTED;
		}
		else
			return super.onColission(attacker);
	}

	@Override
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{
		if (RandomGenerator.getInt(0, 3) == 0) // 25% chance for move
			return super.onAction(heros, moveRange);
		else
			return new Vector2i(-1,-1); //else just return no collision 
	}

}


