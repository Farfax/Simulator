package core.simulator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import characters.Antilope;
import characters.Belladonna;
import characters.Fox;
import characters.Grass;
import characters.Guarana;
import characters.Hero;
import characters.Human;
import characters.Sheep;
import characters.Sosnowsky;
import characters.SowThistle;
import characters.Turtle;
import characters.Wolfs;
import mathX.RandomGenerator;
import mathX.Vector2i;

public final class WorldMap implements java.io.Serializable
{
	private ArrayList<Hero> _heros;
	private char[][] _map;
	private Stack<Hero> _toDie;
	private Stack<Hero> _toBorn;
	private Vector2i _size;
	private StatusBar statusBar;
	private final GameMode game;
	public final Vector2i size()
	{
		return _size;
	}

	public WorldMap(StatusBar statusBar, GameMode game)
	{
		this.statusBar = statusBar;
		this.game=game;
		_heros = new ArrayList<Hero>();
		_toDie = new Stack<Hero>();
		_toBorn = new Stack<Hero>();

		// TODO Auto-generated constructor stub
	}

	public final Hero decode(Color color, Vector2i pos) throws NotExistException
	{
		if (color == Color.RED)
			return new Wolfs(pos, _map, statusBar, game);
		if (color == Color.GRAY)
			return new Sheep(pos, _map, statusBar, game);
		if (color == Color.PINK)
			return new Antilope(pos, _map, statusBar, game);
		if (color == Color.MAGENTA)
			return new Fox(pos, _map, statusBar, game);
		if (color == Color.LIGHT_GRAY)
			return new Turtle(pos, _map, statusBar, game);
		if (color == Color.BLUE)
			return new Belladonna(pos, _map, statusBar, game);
		if (color == Color.YELLOW)
			return new Guarana(pos, _map, statusBar, game);
		if (color == Color.GREEN)
			return new Grass(pos, _map, statusBar, game);
		if (color == Color.CYAN)
			return new Sosnowsky(pos, _map, statusBar, game);
		if (color == Color.ORANGE)
			return new SowThistle(pos, _map, statusBar, game);
		if (color == Color.BLACK)
			return new Human(pos, _map, statusBar, game);
		else
			throw new NotExistException("Can't find excepted animal type");
	}

	private final Hero decode(char symbol, Vector2i pos) throws NotExistException
	{
			switch(symbol)
			{
			case 'W':
				return new Wolfs(pos, _map, statusBar, game);
			case 'S':
				return new Sheep(pos, _map, statusBar, game);
			case 'A':
				return new Antilope(pos, _map, statusBar, game);
			case 'F':
				return new Fox(pos, _map, statusBar, game);
			case 'T':
				return new Turtle(pos, _map, statusBar, game);
			case 'B':
				return new Belladonna(pos, _map, statusBar, game);
			case 'G':
				return new Guarana(pos, _map, statusBar, game);
			case 'Z':
				return new Grass(pos, _map, statusBar, game);
			case 'Y':
				return new Sosnowsky(pos, _map, statusBar, game);
			case 'M':
				return new SowThistle(pos, _map, statusBar, game);
			case 'H':
				return new Human(pos, _map, statusBar, game);
		
		default:
			throw new NotExistException("Can't find excepted animal type");
			}
	}
	public final ArrayList<Hero> heros()
	{
		return _heros;
	}

	public char[][] map()
	{
		return _map;
	}

	public Stack<Hero> toDie()
	{
		return _toDie;
	}

	public Stack<Hero> toBorn()
	{
		return _toBorn;
	}

	public void setMap(final Vector2i size)
	{
		_size = size.get();

		clear();

		_map = new char[size.y()][size.x()];

		for (int i = 0; i < size.y(); ++i)
			for (int j = 0; j < size.x(); j++)
				_map[i][j] = ' ';

	}

	public void clear()
	{
		toDie().clear();
		toBorn().clear();
		heros().clear();

		Hero.heroNumber = 0;
	}
	
	public void spawnHero(char color, String position)
	{
			try
			{
				_heros.add(decode(color,new Vector2i(position)));
			} catch (NotExistException e)
			{
				// TODO Auto-generated catch block
				return;
			}
			
	}

	public void calculateDeath()
	{
		while (!toDie().empty())
		{
			try
			{
				heros().remove(toDie().pop());
			} catch (EmptyStackException excpetion)
			{
				break;
			}
		}
	}

	public boolean isInWorld(final Vector2i position)
	{
		if (position.x() > 0 && position.x() < map()[0].length && position.y() > 0 && position.y() < map().length)
			return true;
		else
			return false;
	}

	public final Vector2i randomFromWorld()
	{
		Vector2i randomed;

		do
		{
			randomed = RandomGenerator.randomVector2i(new Vector2i(0, map()[0].length - 1),
					new Vector2i(0, map().length - 1));
		} while (map()[randomed.y()][randomed.x()] != ' ');

		return randomed;
	}

	public Hero findByIter(final Vector2i toFind) throws NotExistException
	{
		for (Hero hero : heros())
			if (hero.position().equals(toFind))
				return hero;

		throw new NotExistException("Hero Not Exist findByIter()");
	}

}
