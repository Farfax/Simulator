package gui.swing.events;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ColorHandler implements ActionListener
{

	private Color UserColor;
	public static Color Colour;
	public Color UserColor() { return UserColor; }
	
	public ColorHandler()
	{
		UserColor= Color.LIGHT_GRAY;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		
		JButton clicked= (JButton) event.getSource();
		UserColor= clicked.getBackground();
		Colour=UserColor;
		// TODO Auto-generated method stub
		

	}
	
	

}
