#pragma once
#include "..\Plant.h"
class Sosnowsky :
	public Plant
{
public:
	Sosnowsky(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Sosnowsky();

	// inform about fight result, true if defender lose
	virtual Action onColission(Character* attacker) override;

	// inform about hero moves, return position if collision happen, otherwise Vector2i(-1,-1)
	virtual Vector2i onAction(std::vector<Character*>& heros, const int& moveRange = 1) override;

	Character* getInstanceOf(Vector2i& position) const override;
};

