#pragma once
#include <string>
#include <iostream>
#include "WorldMap.h"
#include "StatusBar.h"

enum HeroType
{
	W,
	S,
	A,
	F,
	T,
	B,
	G,
	Z,
	Y,
	M,
	H
};

//class ControlPanel
//{
//	int _keyCode;
//	WorldMap& world;
//	StatusBar& statusBar;
//
//	Character* decode(char symbol, Vector2i& pos);
//public:
//	ControlPanel(WorldMap& world, StatusBar& statusBar);
//
//	int keyCode() const;
//	void keyCode(int keyCode);
//	void getIntput();
//	void save(std::string&& fileName);
//	void load(std::string&& fileName);
//
//	ControlPanel();
//	~ControlPanel();
//};
//
