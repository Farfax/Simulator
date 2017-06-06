#include "Fox.h"
#include "../../GameMode.h"

Fox::Fox(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Character(7, 3, 'F', position, map, statusBar) 
{
}

Fox::~Fox()
{
}

Vector2i Fox::onAction(std::vector<Character*>& heros, const int& moveRange)
{
	Vector2i newPos;

	//TODO: move to function in RandomGenerator getNextPosition
	int SafeAttempt = 0;

	try
	{
		do
		{
			SafeAttempt++;
			newPos = position + RandomGenerator::getVector2i(Vector2i(1, 2), Vector2i(1, 2));

			if (SafeAttempt > 100)
				throw;

		} while ((newPos.x() >= map.begin()->size() || newPos.y() >= map.size()) || !isSafe(newPos, heros));

	}
	catch (...)
	{
		return Vector2i(-1, -1);
	}

	if (map[newPos.y()][newPos.x()] != ' ' && newPos != position )
	{
		return newPos;
	}
	else
	{
		setPosition(newPos);
		return Vector2i(-1, -1);
	}
}

Character * Fox::getInstanceOf(Vector2i & position) const
{
	return new Fox(position, map, statusBar);
}

bool Fox::isSafe(const Vector2i & position, std::vector<Character*>& heros) const
{
	// sometime find() cannot find hero and return null, it's bug need to fix it TODO:FIX FIND BUG
	if (map[position.y()][position.x()] == ' ')
		return true;
	else if (GameMode::findByPosition(heros, position) == nullptr)
		return false;
	else if (GameMode::findByPosition(heros, position)->getStrengh() < strengh)
		return true;
	else
		return false;
}

