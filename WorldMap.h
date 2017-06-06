#pragma once
#include <vector>
#include <iostream>
#include "Character\Character.h"
#include "MathX.h"
#include <stack>

class WorldMap
{
	std::vector<Character*> herosList;
	std::vector<std::vector<char>> charMap;
	std::stack<Character*> deadPool;
	std::stack<Character*> kidList;
public:
	WorldMap();
	~WorldMap();
	WorldMap(const WorldMap& rhs);

	///return World Size 
	Vector2i getSize() const;

	std::vector<Character*>& heros();
	std::vector<std::vector<char>>& map();
	std::stack<Character*>& toDie();
	std::stack<Character*>& toBorn();

	void setmap(const Vector2i& size);
	void clear();
	void calcaluteDeath();
	bool isInWorld(const Vector2i& position) const;
	Vector2i randomFromWorld() const;
	std::vector<Character*>::const_iterator findByIter(const Vector2i& toFind);
};
