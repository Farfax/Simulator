#include "Plant.h"



Plant::Plant(unsigned int&& strengh, unsigned char&& symbol, Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Character(std::move(0), std::move(strengh), std::move(symbol), position, map, statusBar)
{
}


Plant::~Plant()
{
}

Vector2i Plant::onAction(std::vector<Character*>& heros, const int & moveRange)
{
	if (RandomGenerator::getInt(0, 4) == 1) //25% chance for kid
		return position;
	else
		return Vector2i(-1, -1);
}