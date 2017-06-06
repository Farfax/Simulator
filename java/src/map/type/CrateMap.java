package map.type;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import characters.Hero;
import mathX.RandomGenerator;
import mathX.Vector2i;

public class CrateMap implements WorldType
{

	public CrateMap()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public final Vector2i getVector2iforMove(final int range)
	{
		return new Vector2i(RandomGenerator.getInt(-1, range), RandomGenerator.getInt(-1, range)); //For Crate
	}
	
	@Override
	public void drawMap(JPanel fields, Vector2i size, ActionListener mapEvent)
	{
		fields.setLayout(new GridLayout(size.y(), size.x()));
		
		for(int j=0;j<size.y();j++)	
			for(int i=0;i<size.x();i++)
			{
				JButton temp= new JButton();
				temp.setFocusable(false);
				temp.setName(Integer.toString(i)+" "+Integer.toString(j));
				fields.add(temp);
				temp.addActionListener(mapEvent);
			
			}
	}
	
	@Override
	public void spawnHero(JPanel fields, Hero hero, Vector2i size)
	{
		if(hero.position().x()<size.x() && hero.position().y()<size.y() && hero.position().x()>=0 &&  hero.position().y()>=0)
			fields.getComponent(hero.position().x() + hero.position().y()*size.x()).setBackground(hero.color());
	}
	
	@Override
	public void clearMap(JPanel fields)
	{
		for(Component field : fields.getComponents())
			field.setBackground(null);
	}
}
