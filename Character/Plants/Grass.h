#pragma once
#include "..\Plant.h"
class Grass :
	public Plant
{
public:
	Grass(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Grass();

	Character* getInstanceOf(Vector2i& position) const override;

};

