package map.type;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import characters.Hero;
import mathX.Vector2i;

public interface WorldType
{
	public Vector2i getVector2iforMove(int range);
	
	public void drawMap(JPanel fields, Vector2i size, ActionListener mapEvent);

	public void spawnHero(JPanel fields, Hero hero, Vector2i size);

	public void clearMap(JPanel fields);
}
