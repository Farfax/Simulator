package characters;

import java.util.ArrayList;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.RandomGenerator;
import mathX.Vector2i;

abstract class Plant extends Hero
{

	public Plant(int strengh, char symbol, Vector2i position, char[][] map, StatusBar statusBar, GameMode game)
	{
		super(0, strengh, symbol, position,map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{
		if (RandomGenerator.getInt(0, 8) == 1) //25% chance for kid
			return _position;
		else
			return new Vector2i(-1, -1);
	}

}