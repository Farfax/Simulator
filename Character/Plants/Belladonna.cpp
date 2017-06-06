#include "Belladonna.h"



Belladonna::~Belladonna()
{
}

Character* Belladonna::getInstanceOf(Vector2i & position) const
{
	return new Belladonna(position, map,   statusBar);
}

Action Belladonna::onColission(Character* attacker)
{
	attacker->addStrengh(-attacker->getStrengh());
	Character::onColission(attacker);
	dead = true;
	die();
	return ALL_DIED;
}


Belladonna::Belladonna(Vector2i & position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Plant(99,'B', position, map, statusBar) 
{
}