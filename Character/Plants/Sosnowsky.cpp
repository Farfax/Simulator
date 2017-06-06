#include "Sosnowsky.h"
#include "..\..\GameMode.h"


Sosnowsky::Sosnowsky(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Plant( 10, 'Y', position, map, statusBar) 
{
}


Sosnowsky::~Sosnowsky()
{
}

Action Sosnowsky::onColission(Character* attacker)
{
	attacker->addStrengh(-attacker->getStrengh());
	Character::onColission(attacker);
	die();
	dead = true;
	return ALL_DIED;
}

Vector2i Sosnowsky::onAction(std::vector<Character*>& heros, const int & moveRange)
{
	for(int i=-1;i<2;i++)
		for (int j = -1; j < 2; j++)
		{
			Vector2i toKill = getPosition()+ Vector2i(i,j);
			auto foundHero = GameMode::findByPosition(heros, toKill);
			if (foundHero != nullptr && foundHero!=this)// && dynamic_cast<CyberSheep>(attacker) != nullptr)
			{
				if (dynamic_cast<Plant*>(foundHero) == nullptr)
					foundHero->die();
			}
		}

	return Vector2i(-1,-1);
}

Character * Sosnowsky::getInstanceOf(Vector2i & position) const
{
	return new Sosnowsky(position, map, statusBar);
}
