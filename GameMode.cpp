#pragma once

#include "GameMode.h"
#include <string>
#include <iostream>
#include "Character\Animal\Wolfs.h"
#include "Character\Animal\Sheep.h"
#include "Character\Animal\Antilope.h"
#include "Character\Animal\Turtle.h"
#include "Character\Animal\Fox.h"
#include "Character\Plants\Grass.h"
#include "Character\Plants\Belladonna.h"
#include "Character\Plants\Guarana.h"
#include "Character\Plants\SowThistle.h"
#include "Character\Plants\Sosnowsky.h"
#include "Character\Human.h"
#include <algorithm>
#include <fstream>

#define END 27
#define LOAD 108
#define SAVE 115

// Delete dead from Hero vector
void GameMode::garbageCollector()
{
	auto toErase = world.findByIter(Vector2i(-1, -1));
	while (toErase != world.heros().end())
	{
		delete *toErase;
		world.heros().erase(toErase);
		toErase = world.findByIter(Vector2i(-1, -1));
	}
}

GameMode::GameMode()
{
	std::cout << "LEGEND:" << std::endl << "W= Wolf, S=Sheep, A=Antilope, F=Fox, T=Turtle, B=Belladonna, G=Guarana, Z=Grass, Y=Sosnowsky, M= Sowthistle, H=You(Human)" << std::endl << "L=Load, S=Save, Arrow=move, ESC= End Game" << std::endl << std::endl << "Podaj rozmiar mapy x y"; //( Please use >7x7  ";
	std::cin >> size; 

	world.setmap(size);
	for (int i = 0; i < (size.x()*size.y()) / 50; i++)
	{
		world.heros().emplace_back(new Wolfs(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Sheep(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Antilope(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Turtle(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Fox(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Sosnowsky(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Grass(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Guarana(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new Belladonna(world.randomFromWorld(), world.map(), statusBar));
		world.heros().emplace_back(new SowThistle(world.randomFromWorld(), world.map(), statusBar));
	}
	world.heros().emplace_back(new Human(world.randomFromWorld(), world.map(), statusBar));
	std::sort(world.heros().begin(), world.heros().end(), [](const Character * lhs, const Character * rhs) { return lhs > rhs; });
	//save("Example");
	//load("Example");
}

GameMode::~GameMode()
{
	for (auto& hero : world.heros())
		delete hero;
}

void GameMode::draw()
{
	//draw upper border
	std::cout << std::endl;
	for (int i = 0; i <= size.x() + 1; i++)
		std::cout << '*';

	for (int i = 0; i != size.y(); i++)
	{
		std::cout << std::endl << '*';	//left border

		for (int j = 0; j < size.x(); j++)		// Drawing Char Map
			std::cout << world.map()[i][j];

		std::cout << '*';			//right border
	}

	std::cout << std::endl;

	for (int i = 0; i <= size.x() + 1; i++)		// Down Border
		std::cout << '*';

	std::cout << std::endl << std::endl << "Maciej Kuligowski" << std::endl << "165291" << std::endl;

	statusBar.printInfo();
	std::cout << std::endl << "Your Action: " << std::endl;
}

void GameMode::move()
{
	for (auto& hero : world.heros())
	{
		if (hero->isDead())		// We don't want Walking Deads
			continue;

		Vector2i newPosition = hero->onAction(world.heros());

		if (newPosition != Vector2i(-1, -1))	//collision is happen
		{
			
			Character* defender = findByPosition(world.heros(), newPosition);

			try		///catch exception if defender is null
			{
				if (defender == nullptr)
					throw std::invalid_argument("DEFENDER IS NULL");

				if (hero->symbol == defender->symbol)
					calculateCopulate(hero, defender, newPosition);
				else
					defender->onColission(hero);
			}
			catch (std::invalid_argument excp)
			{
				continue;
			}
		}
	}

	// add spawned world.heros() to Hero container
	while (!world.toBorn().empty())
	{
		world.heros().push_back(world.toBorn().top());
		world.toBorn().pop();
	}

	auto kek = RandomGenerator::getInt(1, 1);
	std::sort(world.heros().begin(), world.heros().end(), [](const Character * lhs, const Character * rhs) { return lhs > rhs; });
	garbageCollector();

}

void GameMode::load(std::string && fileName)
{
	world.clear();

	std::ifstream file;
	file.open(fileName);
	size_t heroSize;
	char symbol;
	int X, Y;

	if (!file.is_open())
	{
		statusBar.addInfo("Cannot open file " + fileName);
		return;
	}

	file >> X >> Y;
	file >> heroSize;

	Vector2i MapSize(X,Y);
	size = MapSize;
	world.setmap(MapSize);

	for (int i = 0; i < heroSize; i++)
	{
		file >> symbol >> X>> Y;

		world.heros().push_back(decode(symbol, Vector2i(X,Y)));
	}

	file.close();
}

Character* GameMode::decode(char symbol, Vector2i & pos)
{
	switch (symbol)
	{
	case 'W':
		return new Wolfs(pos, world.map(), statusBar);
	case 'S':
		return new Sheep(pos, world.map(), statusBar);
	case 'A':
		return new Antilope(pos, world.map(), statusBar);
	case 'F':
		return new Fox(pos, world.map(), statusBar);
	case 'T':
		return new Turtle(pos, world.map(), statusBar);
	case 'B':
		return new Belladonna(pos, world.map(), statusBar);
	case 'G':
		return new Guarana(pos, world.map(), statusBar);
	case 'Z':
		return new Grass(pos, world.map(), statusBar);
	case 'Y':
		return new Sosnowsky(pos, world.map(), statusBar);
	case 'M':
		return new SowThistle(pos, world.map(), statusBar);
	case 'H':
		return new Human(pos, world.map(),statusBar);

	default:
		return nullptr;
	}
}

void GameMode::save(std::string && fileName)
{
	std::ofstream file;
	file.open(fileName);

	if (!file.is_open())
		file.open(fileName, std::ios_base::in | std::ios_base::out | std::ios_base::trunc);


	file << size.x()<<std::endl << size.y() << std::endl;

	size_t siz= world.heros().size();
	file << siz << std::endl;

	for(auto hero : world.heros())
		file << hero;

	file.close();
}

void GameMode::calculateCopulate(Character* hero, Character* defender, const Vector2i& newPosition)
{
	Character* kid = hero->copulate();

	if (kid != nullptr)	// if kid exist( copulate was good), generete Info and push kid toBorn container
	{
		kid->bornInfo(kid);
		world.toBorn().push(kid);
	}
}

Character * GameMode::findByPosition(std::vector<Character*>& heros, const Vector2i & toFind)
{
	for (auto hero : heros)
		if (toFind == hero->getPosition())
			return hero;

	return nullptr;
}

void GameMode::mainLoop()
{
	do
	{
		draw();
		move();

		if (Human::userAction == LOAD)
		{
			//std::string temp;
			//std::cout << std::endl << "Podaj nazwe pliku" << std::endl;
			//std::cin >> temp;
			//load(std::move(temp));
			load("Example");
		}
		if (Human::userAction == SAVE)
		{
			//std::string temp;
			//std::cout << std::endl << "Podaj nazwe pliku" << std::endl;
			//std::cin >> temp;
			//save(std::move(temp));
			save("Example");
		}
	} while (Human::userAction != END);
}
