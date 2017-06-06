#include "Guarana.h"



Guarana::Guarana(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Plant(0, 'G', position, map, statusBar) 

{
}


Guarana::~Guarana()
{
}

Character * Guarana::getInstanceOf(Vector2i & position) const
{
	return new Guarana(position, map, statusBar);
}

Action Guarana::onColission(Character * attacker)
{
	attacker->addStrengh(3);
	return Character::onColission(attacker);
}
