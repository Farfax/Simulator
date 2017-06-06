#pragma once
#include "Character.h"
#include "Abilites\Ability.h"
#include "..\GameMode.h"

class Human :
	public Character
{
	Ability* _ability;
	Vector2i getInput();

public:
	Human(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Human();
	static int userAction;

	Vector2i onAction(std::vector<Character*>& heros, const int& moveRange = 1);
	Character* getInstanceOf(Vector2i& position) const override;
};

