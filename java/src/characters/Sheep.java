package characters;

import java.awt.Color;

import javax.swing.ImageIcon;

import core.simulator.GameMode;
import core.simulator.StatusBar;
import mathX.Vector2i;

public class Sheep extends Hero
{
	@Override
	public Color color()
	{
		return Color.GRAY;
	}
	
	public Sheep(Vector2i position, char[][] map,
			StatusBar statusBar, GameMode game)
	{
		super(4, 4, 'S', position, map, statusBar, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImageIcon getIcon()
	{
		return new ImageIcon("hexGray.png");
	}
	
	@Override
	public Hero getInstanceOf(Vector2i position)
	{
		// TODO Auto-generated method stub
		return new Sheep(position, super._map, super._statusBar, super.game);
	}

}
