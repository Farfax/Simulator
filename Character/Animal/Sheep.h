#pragma once
#include "..\Character.h"
class Sheep:
	public Character
{
public:
	Sheep(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Sheep();

	Character* getInstanceOf(Vector2i& position) const override;
};

