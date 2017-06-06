package characters;

import java.awt.Color;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class Wolfs extends Hero
{

	@Override
	public Color color()
	{
		return Color.RED;
	}

	public Wolfs(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(5,9, 'W', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}
	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexRed.png");
	}
	
	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Wolfs(position, super._map, super._statusBar, super.game);
	}

}
