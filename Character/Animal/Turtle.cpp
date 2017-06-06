#include "Turtle.h"



Turtle::Turtle(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Character( 1, 2, 'T', position, map, statusBar) 
{
}


Turtle::~Turtle()
{
}

Action Turtle::onColission(Character* attacker)
{
	if (attacker->getStrengh() <= 5)
	{
		Character::reflect(attacker); //generate comunicta
		return REFLECTED;
	}
	else
		return Character::onColission(attacker);
}

Vector2i Turtle::onAction(std::vector<Character*>& heros, const int& moveRange)
{
	if (RandomGenerator::getInt(0, 4) == 0) // 25% chance for move
		return Character::onAction(heros);
	else
		return Vector2i(-1,-1); //else just return no collision 
}

Character* Turtle::getInstanceOf(Vector2i & position) const
{
	return new Turtle(position, map, statusBar);
}
