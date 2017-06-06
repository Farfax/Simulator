package gui.swing.events;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import characters.Hero;
import core.simulator.NotExistException;
import core.simulator.StatusBar;
import core.simulator.WorldMap;
import mathX.Vector2i;

public class MapEvent implements ActionListener
{
	WorldMap world;
	StatusBar statusBar;
	
	public MapEvent(Color UserColor, WorldMap world, StatusBar statusBar)
	{
		this.statusBar=statusBar;
		this.world=world;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton clicked= (JButton) e.getSource();
		
		Hero hero;
		Vector2i position= Vector2i.toVector2i(clicked.getName());
			
		try
		{
			hero= world.decode(ColorHandler.Colour, position);
		} 
		catch (NotExistException e1)
		{
			// TODO Auto-generated catch block
			return;
		}
		world.heros().add(hero);
		clicked.setBackground(ColorHandler.Colour);
		//position.x() + position.y()*size.x()=button.pos
		

	}

}
