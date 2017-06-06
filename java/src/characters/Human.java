package characters;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import abilities.Ability;
import abilities.MagicPotion;
import core.simulator.Command;
import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.Vector2i;

public final class Human extends Hero
{
	protected Ability _ability;
	public static Command userAction;
	private boolean _canMove;
	
	@Override
	public Color color()
	{
		return Color.BLACK;
	}
	
	public boolean canMove()
	{
		return _canMove;
	}
	private Vector2i getInput()
	{
		_ability.status(this);

		_canMove=true;
		
		if (userAction == Command.RIGHT)
			return position().sub(new Vector2i(-1,0));
		else if (userAction == Command.LEFT)
			return position().sub(new Vector2i(1,0));
		else if (userAction == Command.DOWN)
			return position().sub(new Vector2i(0,-1));
		else if (userAction == Command.UP)
			return position().sub(new Vector2i(0,1));
		else if (userAction == Command.USE)
			_ability.use(this);
		
		
		return new Vector2i(-1,-1);
		
	}
	
	public Human(Vector2i position, char[][] map, StatusBar statusBar, GameMode game)
	{		
		super(4, 5, 'H', position, map, statusBar, game);
		_ability= new MagicPotion(statusBar);
		userAction= Command.NOTHING;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexBlack.png");
	}

	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Human(position, super._map, super._statusBar, super.game);
	}
	
	@Override
	public Vector2i onAction(ArrayList<Hero> heros, final int moveRange)
	{
		Vector2i newPos=getInput();
//				
		userAction= Command.NOTHING;

		if (!(newPos.x() >= 0 && newPos.x() < _map[0].length && newPos.y() >=0 && newPos.y() < _map.length))	// can't go outside map				return new Vector2i(-1, -1);
			return new Vector2i(-1,-1);
		
		if (_map[newPos.y()][newPos.x()] != ' ' && !newPos.equals(position()))
			return newPos;
		else
		{
			position(newPos);
			return new Vector2i(-1, -1);			
		}
		
	}

}

