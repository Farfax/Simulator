#include "Antilope.h"

Antilope::Antilope(Vector2i & position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Character(4,4, 'A', position, map, statusBar) 
{
}

Antilope::~Antilope()
{
}


Action Antilope::onColission(Character* attacker)
{

	if (RandomGenerator::getInt(0, 2))	//50% chance for escape 1 if fail
	{
		if (!reflect(attacker))
			return Character::onColission(attacker);
		else
		{
			Character::reflect(attacker); // generate comunicate
			return REFLECTED;
		}
	}
	else
		return Character::onColission(attacker);
	
}

Vector2i Antilope::onAction(std::vector<Character*>& heros, const int& moveRange)
{
	return Character::onAction(heros,2);
}

bool Antilope::reflect(Character* Attacker)
{
	Vector2i newpos;
	std::vector<Character*> temp;

	int escapeAttempt = 0; 
	
		do
		{
			escapeAttempt++;
			Vector2i newpos = Character::onAction(temp, 1);

			if (escapeAttempt > 100)		///don't want infinite escape
				return false;

		} while (newpos != Vector2i(-1, -1));

		setPosition(newpos);
		Attacker->setPosition(position);
	return true;
}

Character* Antilope::getInstanceOf(Vector2i & position) const
{
	return new Antilope(position, map, statusBar);
}
