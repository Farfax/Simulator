#include "Wolfs.h"


Wolfs::Wolfs(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Character(5, 9, 'W', position, map, statusBar)
{
}


Wolfs::~Wolfs()
{
}

Character* Wolfs::getInstanceOf(Vector2i& position) const
{
	return new Wolfs(position,map,   statusBar);
}

