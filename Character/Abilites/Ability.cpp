#include "Ability.h"
#include <string>

Ability::Ability(std::string name,StatusBar& statusBar):
	_name(name),
	_isActive(false),
	_isLocked(false),
	_statusBar(statusBar)
{
}

MagicPotion::MagicPotion(StatusBar& statusBar):
	Ability("MagicPotion", statusBar),
	_strengh(10),
	_baseStrengh(10),
	_baseTime(10)
{
}

bool Ability::isActive() const
{
	return _isActive;
}

void MagicPotion::use(Character* hero)
{
	if (_isLocked || _isActive)
	{
		_statusBar.addInfo(std::string("Can't use Magical Potion, now. Lose Action for cheating."));
		return;
	}

	_statusBar.addInfo(std::string("Human used Magical Potion!He has 10 bonus Strengh point (-1/per Turn)"));
	
	_isActive = true;
	hero->addStrengh(_strengh);
	--_strengh;
}

void MagicPotion::regenerate()
{
	_time--;

		if (_time <= 0)
		{
			_statusBar.addInfo(std::string("Magical Potion is Up. Type USE to active."));
			_isLocked = false;
			_strengh = 10;
			_time = 10;
		}
}

void MagicPotion::status(Character* hero) 
{
	if (_isActive)
	{
		hero->addStrengh(-1);
		_strengh--;

		if (_strengh <= 0)
			deactive();
	}
	else if (_isLocked)
		regenerate();
}

void MagicPotion::deactive()
{
	if (_isActive)
	{
		_statusBar.addInfo(std::string("Magical Potion is deactived"));
		_isActive = false;
		_isLocked = true;
	}
}
