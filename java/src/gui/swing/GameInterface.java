package gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import characters.Hero;
import characters.Human;
import core.simulator.Command;
import core.simulator.GameMode;
import core.simulator.StatusBar;
import gui.swing.events.ColorHandler;
import gui.swing.events.LoadEvent;
import gui.swing.events.MapEvent;
import gui.swing.events.NewTurn;
import gui.swing.events.SaveEvent;
import map.type.WorldType;
import mathX.Vector2i;

public class GameInterface
{
	private JFrame mainFrame;
	private JPanel fields;
	private JPanel rightMenu;
	protected StatusBar statusBar;
	protected ColorHandler colorHandler;
	protected MapEvent mapEvent;
	protected Color chooseColor;
	protected JPanel info;
	private Vector2i size;
	private WorldType world;
	private static Vector2i defaultSize=new Vector2i(30,30);
	
	private String[] commands= { "UP", "DOWN", "LEFT", "RIGHT", "K" };
	
	private void setHeroChooser()
	{
		JButton[] buttons= new JButton[10];
		//Human is Black
		for(int i=0; i<buttons.length; i++)
			buttons[i]= new JButton();
		
		buttons[0].setBackground(Color.LIGHT_GRAY);
		buttons[0].setText("Turtle");
		
		buttons[1].setBackground(Color.GREEN);
		buttons[1].setText("Grass");
		
		buttons[2].setBackground(Color.RED);
		buttons[2].setText("Wolf");
		
		buttons[3].setBackground(Color.YELLOW);
		buttons[3].setText("Guarana");
		
		buttons[4].setBackground(Color.ORANGE);
		buttons[4].setText("Sowthistle");
		
		buttons[5].setBackground(Color.BLUE);
		buttons[5].setText("Belladona");
		
		buttons[6].setBackground(Color.CYAN);
		buttons[6].setText("Sosnowsky");
		
		buttons[7].setBackground(Color.GRAY);
		buttons[7].setText("Sheep");
		
		buttons[8].setBackground(Color.PINK);
		buttons[8].setText("Antilope");
		
		buttons[9].setBackground(Color.MAGENTA);
		buttons[9].setText("Fox");
		
		for( JButton button : buttons)
		{
			rightMenu.add(button);
			button.setFocusable(false);
			button.addActionListener(colorHandler);
		}
		
	}
	public final void printText()
	{
	//TODO get info from statusbar add toinfo
		
		info.removeAll();
		for(String toPrint: statusBar.get())
		{
			info.add(new JLabel(toPrint));
			
			info.setFocusable(false);
		}
		info.repaint();
		info.validate();
		mainFrame.validate();
		mainFrame.repaint();
		statusBar.clear();
		
	}
	public final void setMap(Vector2i size)
	{					
		this.size=size.get();
		
		world.drawMap(fields, size, mapEvent);

	}
		
	public final void spawnHero(Hero hero)
	{
		world.spawnHero(fields, hero, size);
	}
	
	public final void clearMap()
	{
		world.clearMap(fields);
	}

	private	final ActionListener panelAction= new ActionListener()
		{   
			 @Override
			 public void actionPerformed(ActionEvent ae)
			 {
					
				 String command = (String) ae.getActionCommand();
			 	 if (command.equals("UP"))
					 Human.userAction=Command.UP;          
				 else if (command.equals("DOWN"))
					 Human.userAction=Command.DOWN;
				 else if (command.equals("RIGHT"))
					 Human.userAction=Command.RIGHT;
				 else if (command.equals("LEFT"))
					 Human.userAction=Command.LEFT;
				 else if (command.equals("K"))
					 Human.userAction=Command.USE;
            
		///			game.move();
		//			game.draw();
			//		printText();
        	}
		};
    
	private final void setSize()
	{
		int x,y;
		
		try
		{
			x = Integer.parseInt(JOptionPane.showInputDialog("Enter new Width X"));
	    
			y = Integer.parseInt(JOptionPane.showInputDialog("Enter new Height Y"));
		}
		catch(Exception exp)
		{
			size=defaultSize;
			return;
		}
	    	    
		size = new Vector2i(x,y);
	}
	
	public final Vector2i size()
	{
		return size.get();
	}
	
	public GameInterface(StatusBar statusBar, GameMode game)
	{
		this.world=game.getWorldType();
		this.statusBar=statusBar;
		setSize();
		mainFrame = new JFrame("Simulator Java PO Maciej Kuligowski 165291");
		
		mainFrame.setSize(1200,750);
		mainFrame.setLayout(new BorderLayout());
		
		colorHandler= new ColorHandler();
		
		mapEvent= new MapEvent(colorHandler.UserColor(),game.getWorld(), statusBar);
		rightMenu= new JPanel();
		info = new JPanel();
		fields=new JPanel();
		JButton save= new JButton("Save");
		JButton load= new JButton("Load");
		info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
		rightMenu.setLayout(new BoxLayout(rightMenu , BoxLayout.PAGE_AXIS));
		JButton newTurn=new JButton("Next Turn");
		rightMenu.add(newTurn);
		newTurn.addActionListener(new NewTurn(game));
		rightMenu.add(info);

		setMap(size);

		setHeroChooser();
		
		rightMenu.add(save);
		rightMenu.add(load);
		
		save.addActionListener(new SaveEvent(game));
		load.addActionListener(new LoadEvent(game));
		mainFrame.add(rightMenu, BorderLayout.LINE_END);
		mainFrame.add(fields, BorderLayout.CENTER);
		
		mainFrame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent windowEvent)
				{ System.exit(0); }
		});
		
		mainFrame.setFocusable(true);
		mainFrame.setVisible(true);

		for(int i=0;i<5;i++)
			fields.registerKeyboardAction(panelAction, commands[i], KeyStroke.getKeyStroke(commands[i]), JComponent.WHEN_IN_FOCUSED_WINDOW);
		// TODO Auto-generated constructor stub
	}

}
