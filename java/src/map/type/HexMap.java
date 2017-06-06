package map.type;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import characters.Hero;
import mathX.RandomGenerator;
import mathX.Vector2i;

public class HexMap implements WorldType
{

	public HexMap()
	{
		// TODO Auto-generated constructor stub
	}

	private final Vector2i hexMove()
	{
		switch(RandomGenerator.getInt(0, 6))
		{
			case 0:	// N
				return new Vector2i(0,1);

			case 1:	// NW
				return new Vector2i(-1,0);

			case 2:// NE
				return new Vector2i(1,1);
		
			case 3:	// SW	
				return new Vector2i(-1,-1);

			case 4:	// S
				return new Vector2i(0,-1);

			case 5:// SE
				return new Vector2i(1,0);
		
			default:
				return new Vector2i(0,0);
		}
	}
	
	@Override
	public Vector2i getVector2iforMove(int range)
	{
		// TODO Auto-generated method stub
		return hexMove();
	}
		
	@Override
	public void spawnHero(JPanel fields, Hero hero, Vector2i size)
	{
		if(hero.position().x()<size.x() && hero.position().y()<size.y() && hero.position().x()>=0 &&  hero.position().y()>=0)
		{
				JButton n=(JButton) fields.getComponent(hero.position().x() + hero.position().y()*size.x());
				n.setIcon(hero.getIcon());
				
				fields.repaint();
				fields.validate();
			}
	}
	
	@Override
	public void clearMap(JPanel fields)
	{
		for(Component field : fields.getComponents())
		{
			JButton n=(JButton) field;
			n.setIcon(new ImageIcon("hexWhite.png"));
			
		}
		fields.repaint();
		fields.validate();
	}
	

	@Override
	public void drawMap(JPanel fields, Vector2i size, ActionListener mapEvent)
	{
		fields.setLayout(null);
		ImageIcon imag= new ImageIcon("hexWhite.png");

		
		int x=0,y=0;
		
		for(int j=0;j<size.x();j++)	//-1
		{
			x+=35;
			
			if(j%2==0)
				y=0;
			else
				y=20;
			
			for(int i=0;i<size.y();i++)//-13
			{
				//if(i==0 && j%2==0)
			//		i=1;
				
				JButton temp= new JButton(imag);
				temp.setBorder(BorderFactory.createEmptyBorder());
				temp.setContentAreaFilled(false);
				temp.setName(Integer.toString(j)+" "+Integer.toString(i));
				temp.setBounds(x, y, 50, 50);
				fields.add(temp);
				temp.addActionListener(mapEvent);
				y+=40;
			}
		}
	}

}
