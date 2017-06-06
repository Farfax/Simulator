#include<iostream>
#include "GameMode.h"
#include <ctime>
#include <cstdlib>	

int main()
{
	std::srand(time(NULL));
	GameMode game;
	game.mainLoop();

	return 0;
}