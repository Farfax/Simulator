#include "WorldMap.h"

WorldMap::WorldMap()
{
}

WorldMap::~WorldMap()
{
}

WorldMap::WorldMap(const WorldMap& rhs):
	herosList(rhs.herosList),
	charMap(rhs.charMap),
	deadPool(rhs.deadPool),
	kidList(rhs.kidList)
{
}

std::vector<Character*>& WorldMap::heros()
{
	return herosList;
	// TODO: insert return statement here
}

std::vector<std::vector<char>>& WorldMap::map()
{
	return charMap;
	// TODO: insert return statement here
}

std::stack<Character*>& WorldMap::toDie()
{
	return deadPool;
	// TODO: insert return statement here
}

std::stack<Character*>& WorldMap::toBorn()
{
	return kidList;
	// TODO: insert return statement here
}

void WorldMap::setmap(const Vector2i& size)
{
	std::vector<char> a;
	for (int i = 0; i < size.y(); ++i)
	{
		map().push_back(a);
		for (int j = 0; j < size.x(); ++j)
			map()[i].emplace_back(' ');
	}
}

void WorldMap::clear()
{
	while (!deadPool.empty())
		deadPool.pop();

	while (!toBorn().empty())
		toBorn().pop();

	for (auto& hero : heros())
		delete hero;

	heros().clear();

	map().clear();
	Character::heroNumber = 0;
}

void WorldMap::calcaluteDeath()
{
	while (!deadPool.empty())
	{
		auto kek = findByIter(deadPool.top()->getPosition());

		if (kek == herosList.end())
			break;

		delete *kek;
		herosList.erase(kek);
		deadPool.pop();
	}
}

bool WorldMap::isInWorld(const Vector2i & position) const
{
	if (position.x() > 0 && position.x() < charMap[0].size() && position.y() >0 && position.y() < charMap.size())
		return true;
	else
		return false;
}

Vector2i WorldMap::randomFromWorld() const
{
	Vector2i randomed=RandomGenerator::randomVector2i(Vector2i(0, charMap[0].size()), Vector2i(0, charMap.size()));

	while(charMap[randomed.x()][randomed.y()]!=' ')
		randomed = RandomGenerator::randomVector2i(Vector2i(0, charMap[0].size()), Vector2i(0, charMap.size()));

	return randomed;
}

std::vector<Character*>::const_iterator WorldMap::findByIter(const Vector2i & toFind)
{
		for (auto it = heros().begin(); it != heros().end(); it++)
			if ((*it)->getPosition() == toFind)
				return it;

		return heros().end();
}

