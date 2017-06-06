package characters;

import java.awt.Color;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class Guarana extends Plant
{

	@Override
	public Color color()
	{
		return Color.YELLOW;
	}
	
	public Guarana(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(0, 'G', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexYellow.png");
	}
	
	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Guarana(position, super._map, super._statusBar, super.game);
	}

	public Action onColission(Hero attacker)
	{
		attacker.addStrengh(3);
		return super.onColission(attacker);
	}

}
