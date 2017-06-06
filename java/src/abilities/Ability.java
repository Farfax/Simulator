package abilities;

import characters.Hero;
import core.simulator.StatusBar;

public abstract class Ability
{
		protected boolean _isActive;
		protected boolean _isLocked;
		public final String _name;
		protected StatusBar _statusBar;
		
		public abstract void deactive();
		public abstract void regenerate();

		public final boolean isActive()
		{
			return _isActive;
		}
		
		public abstract void use(Hero hero);
		public abstract void status(Hero hero);
	
	public Ability(String name, StatusBar statusBar)
	{
		_isActive=false;
		_isLocked=false;
		_name=name;
		_statusBar=statusBar;
		// TODO Auto-generated constructor stub
	}

}

