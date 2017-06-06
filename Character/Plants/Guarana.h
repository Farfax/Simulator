#pragma once
#include "..\Plant.h"
class Guarana :
	public Plant
{
public:
	Guarana(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	~Guarana();

	Character* getInstanceOf(Vector2i& position) const override;
	Action onColission(Character* attacker) override;
};

