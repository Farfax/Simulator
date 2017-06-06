#pragma once
#include "..\Plant.h"

class SowThistle :
	public Plant
{
public:
	SowThistle(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~SowThistle();

	Character* getInstanceOf(Vector2i& position) const override;

	Vector2i onAction(std::vector<Character*>& heros, const int& moveRange = 1) override;
};

