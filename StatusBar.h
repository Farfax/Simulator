#pragma once
#include <iostream>
#include <stack>
#include <string>

class StatusBar final
{
	std::stack<std::string> nfo;

public:
	void printInfo();
	void addInfo(std::string&& info);

};