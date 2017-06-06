#include "Grass.h"



Grass::Grass(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Plant(0, 'Z', position, map, statusBar) 
{
}


Grass::~Grass()
{
}

Character* Grass::getInstanceOf(Vector2i & position) const
{
	return new Grass(position,map, statusBar );
}
