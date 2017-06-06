#pragma once
#include <iostream>
#include <string>
#include "..\Character.h"
#include "..\..\GameMode.h"

class Ability
{
protected:
	bool _isActive;
	bool _isLocked;
	const std::string _name;
	StatusBar& _statusBar;
	virtual void deactive() = 0;
	virtual void regenerate() = 0;

public:
	Ability(std::string name, StatusBar& statusBar);

	bool isActive() const;
	virtual void use(Character* hero) = 0;
	virtual void status(Character* hero) = 0;
};


class MagicPotion final :
	public Ability
{
	const int _baseStrengh;
	const int _baseTime;
	int _strengh;
	int _time;

	void regenerate() override;
	void deactive() override;

public:
	MagicPotion(StatusBar& statusBar);

	void use(Character* hero) override;
	void status(Character* hero) override;
};