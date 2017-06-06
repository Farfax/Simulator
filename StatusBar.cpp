#include "StatusBar.h"

void StatusBar::printInfo()
{
	while (!nfo.empty())
	{
		std::cout << nfo.top() << std::endl;
		nfo.pop();
	}
}

void StatusBar::addInfo(std::string&& info)
{
	nfo.push(info);
}
