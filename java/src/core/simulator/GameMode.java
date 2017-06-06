package core.simulator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import characters.*;
import gui.swing.*;
import map.type.CrateMap;
import map.type.HexMap;
import map.type.WorldType;
import mathX.Vector2i;

public final class GameMode implements java.io.Serializable
{
	private StatusBar statusBar;
	private WorldMap world;
	private Vector2i size;
	private GameInterface display;
	private WorldType worldType;
	
	public final WorldType getWorldType()
	{
		return worldType;
	}
	public final WorldMap getWorld()
	{
		return world;
	}
		
	public final void draw()
	{
			display.clearMap();
			
		for(Hero hero : world.heros())
			display.spawnHero(hero);
		
		display.printText();
		
	}
	
	public void doTurn()
	{
		for (Hero hero : world.heros())
		{
			if (hero.isDead())		// We don't want Walking Deads
				continue;

			Vector2i newPosition = hero.onAction(world.heros(), 1);

			if (!newPosition.equals(new Vector2i(-1, -1)))	//collision is happen
			{
				
				try		///catch exception if defender is null
				{
					Hero defender = findByPosition(world.heros(), newPosition);

					if (hero.symbol == defender.symbol)
						calculateCopulate(hero, defender, newPosition);
					else
						defender.onColission(hero);
				}
				catch (NotExistException excp)
				{
					continue;
				}
			}
		}

		// add spawned world.heros() to Hero container
		while (!world.toBorn().empty())
			world.heros().add(world.toBorn().pop());
		

		Collections.sort(world.heros(), new HeroComparator());
		garbageCollector();
	}
	
	public void calculateCopulate(Hero hero, Hero defender, final Vector2i newPosition)
	{
		try
		{
			Hero kid = hero.copulate();
			kid.bornInfo(kid); // if kid exist( copulate was good), generete Info and push kid toBorn container
			world.toBorn().push(kid);
		
		}
		catch(CopulateFailException excp)
		{
			return;
		}
	}
	
	public void garbageCollector()
	{
		do
		{
			try
			{
				Hero toErase= world.findByIter(new Vector2i(-1,-1));
				world.heros().remove(toErase);
			}
			catch(NotExistException excp)
			{
				break;
			}
		}
		while(true);
	}
		
	
	public void load(String fileName)
	{
	      try(FileInputStream stream = new FileInputStream(fileName);
	    	      ObjectInputStream objectInput = new ObjectInputStream(stream))
	      {
	    	  
	  		world.clear();
			world.setMap(size);
			
			int herosize= objectInput.readInt();
			
	    	  for(int i=0; i<herosize; i++)
	    	  {
	    		  String position= (String) objectInput.readObject();
	    		  char color=objectInput.readChar();
	    		 world.spawnHero(color,position);
	    	  }
	    	  draw();
	      }
	      catch(Exception e)
	      {
	    	  statusBar.addInfo("Cannot open file "+fileName);
	      }
	}

	public void save(String fileName)
	{
	      
	      try(FileOutputStream stream = new FileOutputStream(fileName);
	    	      ObjectOutputStream objectOutput = new ObjectOutputStream(stream))
	      {
	    	  objectOutput.writeInt(world.heros().size());
	    	  
	    	  
	    	  for(Hero hero : world.heros())
	    	  {
	    		  objectOutput.writeObject(hero.write());
	    		  objectOutput.writeChar(hero.symbol);
	    	  }
	    	  
	      }
	      catch(Exception e)
	      {
	    	  statusBar.addInfo("Cannot open file "+fileName);
	      }
	}

	static public Hero findByPosition(ArrayList<Hero> heros, final Vector2i toFind)
			throws NotExistException
	{
		for (final Hero hero : heros)
			if (toFind.equals(hero.position()))
				return hero;

		throw new NotExistException("Cannot find hero on position"+ toFind.toString());
	}
	
	public GameMode(WorldType worldType)
	{
		this.worldType= worldType;
		
		statusBar= new StatusBar();
		world= new WorldMap(statusBar, this);
		display= new GameInterface(statusBar, this);
		size = display.size();
		world.setMap(size);

			world.heros().add(new Wolfs(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Sheep(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Wolfs(world.randomFromWorld(), world.map(), statusBar, this));

			
			world.heros().add(new Antilope(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Turtle(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Fox(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Sosnowsky(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Grass(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Guarana(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Belladonna(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new SowThistle(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Antilope(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Turtle(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Fox(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Sosnowsky(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Grass(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Guarana(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new Belladonna(world.randomFromWorld(), world.map(), statusBar, this));
			world.heros().add(new SowThistle(world.randomFromWorld(), world.map(), statusBar, this));
			Human human = new Human(world.randomFromWorld(), world.map(), statusBar, this);
			world.heros().add(human);
		// TODO Auto-generated constructor stub
			Collections.sort(world.heros(), new HeroComparator());

			
	}

	public static void main(String[] args)
	{
//		Scanner input=new Scanner(System.in);
		GameMode game;
//		System.out.println("[0] Crate, [1] Hex, default Crate");
//		switch(input.nextInt())
//		{
//			case 1:
				game= new GameMode(new HexMap());			
//			default:
			//	game= new GameMode(new CrateMap());
//		}
		
			
		game.draw();
			
	}
}
