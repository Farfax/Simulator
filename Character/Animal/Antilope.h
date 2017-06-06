#pragma once
#include "..\Character.h"
class Antilope :
	public Character
{
public:
	Antilope(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Antilope();

	// inform about fight result, true if defender lose
	Action onColission(Character* attacker) override;

	// inform about hero moves, return position if collision happen, otherwise Vector2i(-1,-1)
	Vector2i onAction(std::vector<Character*>& heros, const int& moveRange=1) override;

	bool reflect(Character* attacker) override;

	//create new instace of actual class
	virtual Character* getInstanceOf(Vector2i& position) const override;
	
};

