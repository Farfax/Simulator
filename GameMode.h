#pragma once
#include "MathX.h"
#include "Character\Character.h"
#include <stack>
#include <vector>
#include "WorldMap.h"
#include "ControlPanel.h"


class GameMode final
{
	StatusBar statusBar;
	WorldMap world;
	Vector2i size;

	void draw();
	void move();
	void calculateCopulate(Character* hero, Character* defender, const Vector2i& newPosition);
	void garbageCollector();

public:
	GameMode();
	~GameMode();

	void mainLoop();
	void load(std::string&& fileName);
	Character* decode(char symbol, Vector2i& pos);
	void save(std::string&& fileName);



	static Character* findByPosition(std::vector<Character*>& heros, const Vector2i& toFind);

};
