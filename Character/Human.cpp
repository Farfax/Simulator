#include "Human.h"
#include <conio.h>

#define UP 72
#define DOWN 80
#define RIGHT 77
#define LEFT 75
#define ARROW_USED 224
#define USE 107
#define LOAD 108
#define SAVE 115
#define END 27

int Human::userAction = 0;

Human::Human(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar):
	Character(4,5, 'H', position, map, statusBar),
	_ability(new MagicPotion(statusBar))
{}

Human::~Human()
{
	delete _ability;

	if (userAction == LOAD)
		return;

	if(dead)
		std::cout<<std::endl<<std::endl<<"You died it's over, click L to load game. Any other key= quit";
	else
		std::cout << std::endl << std::endl << "Click L to load game. Any other key= quit";

	Human::userAction = _getch();
	if (userAction != LOAD)
		userAction = END;


}

Vector2i Human::onAction(std::vector<Character*>& heros, const int& moveRange)
{
	//TODO: move to function in RandomGenerator getNextPosition

		Vector2i newPos=getInput();

	if (newPos == Vector2i(-1, -1))
		return newPos;

	if (!(newPos.x() >= 0 && newPos.x() < map[0].size() && newPos.y() >=0 && newPos.y() < map.size()))	// can't go outside map
		return Vector2i(-1, -1);

	if (map[newPos.y()][newPos.x()] != ' ' && newPos != position)
		return newPos;
	else
	{
		setPosition(newPos);
		return Vector2i(-1, -1);
	}
}

Character* Human::getInstanceOf(Vector2i& position) const 
{
	return new Human(position, map, statusBar);
}

Vector2i Human::getInput()
{
	_ability->status(this);

	userAction = _getch();

	if (userAction == ARROW_USED)
		userAction = _getch();

	if (userAction == RIGHT)
		return getPosition() - Vector2i(-1, 0);
	else if (userAction == LEFT)
		return getPosition() - Vector2i(1, 0);
	else if (userAction == DOWN)
		return getPosition() - Vector2i(0, -1);
	else if (userAction == UP)
		return getPosition() - Vector2i(0, 1);
	else if (userAction == USE)
		_ability->use(this);

	return Vector2i(-1,-1);
}