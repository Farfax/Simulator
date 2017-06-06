#include "SowThistle.h"

SowThistle::SowThistle(Vector2i & position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Plant( 0, 'M', position, map, statusBar) 
{
}

SowThistle::~SowThistle()
{
}

Character * SowThistle::getInstanceOf(Vector2i & position) const
{
	return new SowThistle(position, map, statusBar);
}

Vector2i SowThistle::onAction(std::vector<Character*>& heros, const int & moveRange)
{
	int copulateAttempt = 0;
	Vector2i kid;
	
		do
		{
			copulateAttempt++;
			kid = Plant::onAction(heros);
			
		} while (kid != Vector2i(-1, -1) && copulateAttempt < 3);

		return kid;
}
