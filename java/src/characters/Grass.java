package characters;

import java.awt.Color;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class Grass extends Plant
{

	@Override
	public Color color()
	{
		return Color.GREEN;
	}
	
	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexGreen.png");
	}
	
	public Grass(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(1, 'Z', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Grass(position, super._map, super._statusBar, super.game);
	}

}
