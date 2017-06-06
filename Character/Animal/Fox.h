#pragma once
#include "..\Character.h"
class Fox :
	public Character
{
public:
	Fox(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Fox();

	Vector2i onAction(std::vector<Character*>& heros, const int& moveRange=1) override;

	Character* getInstanceOf(Vector2i& position) const override;

	bool isSafe(const Vector2i& position, std::vector<Character*>& heros) const;
};

