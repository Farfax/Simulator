#pragma once
#include "Character.h"
class Plant :
	public Character
{
public:
	Plant(unsigned int&& strengh, unsigned char&& symbol, Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	virtual ~Plant();

	virtual Vector2i onAction(std::vector<Character*>& heros, const int& moveRange = 1) override;
};

