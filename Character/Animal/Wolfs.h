#pragma once
#include "..\Character.h"
class Wolfs final :
	public Character
{
public:
	Wolfs(Vector2i& position, std::vector<std::vector<char>>& map, StatusBar& statusBar);
	
	Character* getInstanceOf(Vector2i& position) const override;
	~Wolfs();
};

