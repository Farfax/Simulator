#include "Character.h"
#include<iostream>

unsigned int Character::heroNumber = 0;

Character::Character(unsigned int&& activity, unsigned int&& strengh, unsigned char&& symbol, Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar) :
	activity(activity),
	strengh(strengh),
	symbol(symbol),
	position(position),
	map(map),
	ID(heroNumber+1),
	statusBar(statusBar)
{
	heroNumber++;
	map[position.y()][position.x()] = symbol;
}

Character::Character(const Character& rhs) :
	activity(rhs.activity),
	strengh(rhs.strengh),
	symbol(rhs.symbol),
	position(rhs.position),
	map(rhs.map),
	ID(rhs.ID),
	statusBar(rhs.statusBar)
{}

Character::~Character()
{
	if(!dead)
		map[position.y()][position.x()] = ' ';
}

void Character::addStrengh(int && toAdd)
{
	strengh += toAdd;
}

void Character::addStrengh(const int & toAdd)
{
	strengh += toAdd;
}

int Character::getStrengh() const
{
	return strengh;
}

Vector2i Character::getPosition() const
{
	return position;
}

void Character::setPosition(Vector2i position)
{
	map[this->position.y()][this->position.x()] = ' ';

	if (position == Vector2i(-1, -1))
	{
		this->position = position;
		dead = true;
		return;
	}

	map[position.y()][position.x()] = symbol;
	this->position = position;
}

bool Character::isDead() const
{
	return dead;
}

void Character::die()
{
	dieInfo(this);
	setPosition(Vector2i(-1, -1));
}

Action Character::onColission(Character* attacker)
{
	Vector2i temp = position;
	if (attacker->strengh > strengh)
	{
		dead = true;
		die();
		attacker->setPosition(temp);
		return 	DEFENDER_DIED;
	}
	else if (attacker->strengh == strengh)
	{
		if (attacker->ID > ID)
		{
			dead = true;
			die();
			attacker->setPosition(temp);
			return 	DEFENDER_DIED;
		}
	}
	else
	{
		attacker->dead = true;
		attacker->die();
		return 	ATTACKER_DIED;
	}
}

Vector2i Character::onAction(std::vector<Character*>& heros,const int& moveRange)
{
	Vector2i newPos;

	//TODO: move to function in RandomGenerator getNextPosition
	do
	{
		newPos = position + RandomGenerator::getVector2i(Vector2i(1, 1+moveRange), Vector2i(1, 1+moveRange));
	} while (newPos.x() >= map.begin()->size() || newPos.y() >= map.size());

	if (map[newPos.y()][newPos.x()] != ' ' && newPos != position)
	{
		return newPos;
	}
	else
	{
		setPosition(newPos);
		return Vector2i(-1, -1);
	}
}

bool Character::reflect(Character* attacker)
{
	reflectInfo(this);

	return true;	//Only Special Animal can reflect or escape
}

Character* Character::copulate()
{
	Vector2i newPos;

		do
		{
			newPos = position + RandomGenerator::getVector2i(Vector2i(1, 2), Vector2i(1, 2));
		} while (newPos.x() >= map.begin()->size() || newPos.y() >= map.size());

		if (map[newPos.y()][newPos.x()] == ' ')
		{
			map[newPos.y()][newPos.x()] = symbol;
			return this->getInstanceOf(newPos);
		}

		return nullptr;
}

void Character::bornInfo(Character* kid)
{
	std::string build = "";
	build += "new ";
	build += kid->symbol;
	build += " " + kid->getPosition().toString() + "is borned.";
	statusBar.addInfo(std::move(build));
}

void Character::dieInfo(Character* dead)
{
	std::string build = "";
	build += dead->symbol;
	build += dead->getPosition().toString() + " die.";
	statusBar.addInfo(std::move(build));
}

void Character::reflectInfo(Character * reflected)
{
	std::string build = "";
	build += reflected->symbol;
	build += reflected->getPosition().toString() + " reflect attack.";
	statusBar.addInfo(std::move(build));
}

bool Character::operator<(const Character* rhs) const
{
	if(this->activity != rhs->activity)
		return this->activity < rhs->activity;
	else
		return this->ID > rhs->ID; 
}

bool Character::operator>(const Character * rhs) const
{
	if (this->activity != rhs->activity)
		return this->activity > rhs->activity;
	else
		return this->ID < rhs->ID;
}

std::ostream& operator<<(std::ostream& os, Character* hero)
{
	os << hero->symbol << std::endl << hero->position.x() << std::endl << hero->position.y();
	// writhe obj to stream
	return os;
}
