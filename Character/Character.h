#pragma once
#include "..\MathX.h"
#include <vector>
#include "..\StatusBar.h"

enum Action
{
	REFLECTED,
	ATTACKER_DIED,
	DEFENDER_DIED,
	ALL_DIED
};

class Character
{
protected:
	std::vector<std::vector<char>>& map;
	int strengh;
	bool dead = false;
	Vector2i position;
	StatusBar& statusBar;
public:
	Character(unsigned int&& activity, unsigned int&& strengh, unsigned char&& symbol, Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& status); //std::vector<std::vector<char>>& map);
	Character(const Character& rhs);
	virtual ~Character();

	unsigned static int heroNumber;
	const unsigned int activity;
	const unsigned int ID;
	const unsigned char symbol;

	void addStrengh(int&& toAdd);
	void addStrengh(const int& toAdd);
	
	int getStrengh() const;
	Vector2i getPosition() const;
	void setPosition(Vector2i position);
	bool isDead() const;

	void die();

	// inform about fight result, true if defender lose
	virtual Action onColission(Character* attacker);

	// inform about hero moves, return position if collision happen, otherwise Vector2i(-1,-1)
	virtual Vector2i onAction(std::vector<Character*>& heros, const int& moveRange=1);
	virtual bool reflect(Character* attacker);
	//create new instace of actual class
	virtual Character* getInstanceOf(Vector2i& position) const  = 0;

	//return pointer to new Kid, otherwise nullptr
	virtual Character* copulate();

	void bornInfo(Character * kid);

	void dieInfo(Character * dead);

	void reflectInfo(Character * reflected);

	bool operator<(const Character*rhs) const;
	bool operator>(const Character*rhs) const;

	friend std::ostream& operator<<(std::ostream& os, Character* hero);
};

//if you want to create your own character, feel free to use this abstract class
class CharInterface
{
public:
	CharInterface();
	virtual ~CharInterface();

	virtual void addStrengh(int&& toAdd) = 0;
	virtual void addStrengh(const int& toAdd) = 0;
	virtual int getStrengh() const = 0;
	virtual Vector2i getPosition() const=0;
	virtual void setPosition(Vector2i position)=0;
	virtual bool isDead() const=0;
	virtual void die()=0;
	virtual Action onColission(Character* attacker)=0;
	virtual Vector2i onAction(std::vector<Character*>& heros, const int& moveRange = 1)=0;
	virtual Character* getInstanceOf(Vector2i& position) const = 0;
	virtual Character* copulate()=0;
	virtual void bornInfo(Character * kid)=0;
	virtual void dieInfo(Character * dead)=0;
	virtual void reflectInfo(Character * reflected)=0;
};