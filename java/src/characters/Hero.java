package characters;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.RandomGenerator;
import mathX.Vector2i;

public abstract class Hero
{
	protected final char[][] _map;
	protected int _strengh;
	protected boolean _dead;
	protected final Vector2i _position;
	protected final StatusBar _statusBar;
	public static int heroNumber=0;
	public final int activity;
	public final int ID;
	public final char symbol;
	protected final GameMode game;
	
	
	public Hero(int activity, int strengh, char symbol, Vector2i position, char[][] map, StatusBar statusBar, GameMode game )
	{
		this.game=game;
		_map= map;
		_position= position.get();
		_statusBar= statusBar;
		_dead=false;
		_strengh= strengh;
		this.activity= activity;
		ID=++heroNumber;
		this.symbol=symbol;
		
		_map[position.y()][position.x()]=symbol;
		
		// TODO Auto-generated constructor stub
	}
		
	public abstract Color color();
	
	public final void addStrengh(int toAdd) { _strengh+= toAdd; }
	
	public final int strengh() { return _strengh; }
	
	public final void position(Vector2i newPos) 
	{
		if(newPos.x()>=_map[0].length || newPos.y()>=_map.length || newPos.x()<0|| newPos.y()<0)
			return;
		
		_map[_position.y()][_position.x()]=' ';

		_position.set(newPos.get());
		
			_map[_position.y()][_position.x()]=symbol;
	}
	
	public final Vector2i position() { return _position; }
	public final boolean isDead() { return _dead; }
	
	public void die() 
	{
		dieInfo(this);
		_map[_position.y()][_position.x()]=' ';
		_position.set(new Vector2i(-1,-1));
		_dead=true;
	}
	
	public final String write()
	{
		return position().toString();
	}
	
	public final Hero read(String position)
	{
		Vector2i pos= new Vector2i(position);
		
		return this.getInstanceOf(pos);
	}
	
	public abstract ImageIcon getIcon();
	public Action onColission(Hero attacker)
	{
		Vector2i temp = position().get();
		
		if (attacker.strengh() > _strengh)
		{
			die();
			attacker.position(temp);

			return 	Action.DEFENDER_DIED;
		}
		else if (attacker.strengh() == _strengh)
		{
			if (attacker.ID > ID)
			{
				die();
				attacker.position(temp);
				return 	Action.DEFENDER_DIED;
			}
			else
			{
				attacker.die();
				return 	Action.ATTACKER_DIED;
			}
		}
		else if (attacker.strengh() < _strengh)
		{
			attacker.die();
			return 	Action.ATTACKER_DIED;
		}
		else 
		{
			die();
			attacker.die();
			return Action.ALL_DIED;
		}
	}
	
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{
		Vector2i newPos;
		//TODO: move to function in RandomGenerator getNextPosition
		do
		{
			newPos=_position.get();
			newPos=newPos.add(game.getWorldType().getVector2iforMove(moveRange));
			
		} while (!(newPos.x() >= 0 && newPos.x() < _map[0].length && newPos.y() >=0 && newPos.y() < _map.length));

		if (_map[newPos.y()][newPos.x()]!= ' ' && !newPos.equals(_position))
		{ 
			return newPos;
		}
		else
		{
			position(newPos.get());
			return new Vector2i(-1, -1);
		}
	}
	
	public boolean reflect(Hero attacker)
	{
		reflectInfo(this);

		return true;	//Only Special Animal can reflect or escape
	}
	
	public abstract Hero getInstanceOf(Vector2i position);
	
	public Hero copulate()
		throws CopulateFailException
	{
			Vector2i newPos;

				do
				{
					newPos=_position.get();
					newPos=newPos.add(game.getWorldType().getVector2iforMove(1));
				} while (!(newPos.x()>= 0 && newPos.x() < _map[0].length && newPos.y() >=0 && newPos.y() < _map.length));

				if (_map[newPos.y()][newPos.x()]== ' ')
				{
					return this.getInstanceOf(newPos);
				}

				throw new CopulateFailException();
	}
	
	public final void bornInfo(Hero kid)
	{
		String build = new String();
		build += "new ";
		build += kid.symbol;
		build += kid.position().toString() + " borned.";
		_statusBar.addInfo(build);
	}
	public final void dieInfo(Hero dead)
	{
		String build = new String();
		build += dead.symbol + " " +dead.position().toString() + " die.";
		_statusBar.addInfo(build);
	}
	public final void reflectInfo(Hero reflected)
	{
		String build = new String();
		build += reflected.symbol;
		build += reflected.position().toString() + " reflect attack.";
		_statusBar.addInfo(build);
	}
	
	public final boolean compare(final Hero rhs)
	{
		if (activity != rhs.activity)
			return activity > rhs.activity;
		else
			return ID < rhs.ID;
	}
}
