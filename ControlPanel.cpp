#include "ControlPanel.h"
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
#include <fstream>

//Character * ControlPanel::decode(char symbol, Vector2i & pos)
//{
//	switch (symbol)
//	{
//	case 'W':
//		return new Wolfs(pos, world.map(), statusBar);
//	case 'S':
//		return new Sheep(pos, world.map(), statusBar);
//	case 'A':
//		return new Antilope(pos, world.map(), statusBar);
//	case 'F':
//		return new Fox(pos, world.map(), statusBar);
//	case 'T':
//		return new Turtle(pos, world.map(), statusBar);
//	case 'B':
//		return new Belladonna(pos, world.map(), statusBar);
//	case 'G':
//		return new Guarana(pos, world.map(), statusBar);
//	case 'Z':
//		return new Grass(pos, world.map(), statusBar);
//	case 'Y':
//		return new Sosnowsky(pos, world.map(), statusBar);
//	case 'M':
//		return new SowThistle(pos, world.map(), statusBar);
//	case 'H':
//		return new Human(pos, world.map(), statusBar);
//
//	default:
//		return nullptr;
//	}
//}
//
//ControlPanel::ControlPanel(WorldMap & world, StatusBar& statusBar):
//	_keyCode(0),
//	world(world),
//	statusBar(statusBar)
//{
//}
//
//int ControlPanel::keyCode() const
//{
//	return _keyCode;
//}
//
//void ControlPanel::keyCode(int keyCode)
//{
//	_keyCode = keyCode;
//}
//
//void ControlPanel::getIntput()
//{
//}
//
//void ControlPanel::save(std::string && fileName)
//{
//	std::ofstream file;
//	file.open(fileName);
//
//	if (!file.is_open())
//		file.open(fileName, std::ios_base::in | std::ios_base::out | std::ios_base::trunc);
//
//
//	file << size.x() << std::endl << size.y() << std::endl;
//
//	size_t siz = world.heros().size();
//	file << siz << std::endl;
//
//	for (auto hero : world.heros())
//		file << hero;
//
//	file.close();
//}
//
//void ControlPanel::load(std::string && fileName)
//{
//	world.clear();
//
//	std::ifstream file;
//	file.open(fileName);
//	size_t heroSize;
//	char symbol;
//	int X, Y;
//
//	if (!file.is_open())
//	{
//		statusBar.addInfo("Cannot open file " + fileName);
//		return;
//	}
//
//	file >> X >> Y;
//	file >> heroSize;
//
//	Vector2i MapSize(X, Y);
//	size = MapSize;
//	world.setmap(MapSize);
//
//	for (int i = 0; i < heroSize; i++)
//	{
//		file >> symbol >> X >> Y;
//
//		world.heros().push_back(decode(symbol, Vector2i(X, Y)));
//	}
//
//	file.close();
//}
//
//ControlPanel::~ControlPanel()
//{
//}
