package abilities;

import characters.Hero;
import core.simulator.StatusBar;

public final class MagicPotion extends Ability
{
	final int _baseStrengh;
	final int _baseTime;
	int _strengh;
	int _time;
	
	public MagicPotion(StatusBar statusBar)
	{
		super(new String("MagicPotion"), statusBar);
		_baseStrengh=10;
		_baseTime=10;
		_strengh=10;
		_time=10;
		// TODO Auto-generated constructor stub
	}

	@Override
	public final void deactive()
	{
		if (_isActive)
		{
			_statusBar.addInfo(new String("Magical Potion is deactived"));
			_isActive = false;
			_isLocked = true;
		}

	}

	@Override
	public final void regenerate()
	{
		_time--;

		if (_time <= 0)
		{
			_statusBar.addInfo(new String("Magical Potion is Up. Type USE to active."));
			_isLocked = false;
			_strengh = 10;
			_time = 10;
		}

	}

	@Override
	public final void use(Hero hero)
	{
		if (_isLocked || _isActive)
		{
			_statusBar.addInfo(new String("Can't use Magical Potion, now. Lose Action for cheating."));
			return;
		}

		_statusBar.addInfo(new String("Human used Magical Potion!He has 10 bonus Strengh point (-1/per Turn)"));
		
		_isActive = true;
		hero.addStrengh(_strengh);
		--_strengh;

	}

	@Override
	public final void status(Hero hero)
	{
		if (_isActive)
		{
			hero.addStrengh(-1);
			_strengh--;

			if (_strengh <= 0)
				deactive();
		}
		else if (_isLocked)
			regenerate();

	}
}
