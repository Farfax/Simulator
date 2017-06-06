#include "Sheep.h"



Sheep::Sheep(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Character(4,4, 'S',position,map, statusBar) 
{
}


Sheep::~Sheep()
{
}

Character * Sheep::getInstanceOf(Vector2i & position) const
{
	return new Sheep(position, map, statusBar);
}
