#pragma once
#include "..\Plant.h"
class Belladonna :
	public Plant
{
public:
	Belladonna(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Belladonna();

	Character* getInstanceOf(Vector2i& position) const override;

	virtual Action onColission(Character* attacker) override;
};

