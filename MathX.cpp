#include "MathX.h"
#include <utility>
#include <cstdlib>
#include <ctime>

Vector2i::Vector2i() :
	_x(0),
	_y(0)
{}

Vector2i::Vector2i(int x = 0, int y = 0) :
	_x(x),
	_y(y)
{}
Vector2i::Vector2i(const Vector2i& right) :
	_x(right.x()),
	_y(right.y())
{}

int Vector2i::x() const
{
	return _x;
}

int Vector2i::y() const
{
	return _y;
}

void Vector2i::x(int x)
{
	_x = x;
}

void Vector2i::y(int y)
{
	_y = y;
}

Vector2i::Vector2i(Vector2i&& right) :
	_x(right.x()),
	_y(right.y())
{}

Vector2i& Vector2i::reverse() const
{
	return Vector2i(_y, _x);
}

Vector2i & Vector2i::opposed() const
{
	return Vector2i(-_x, -_y);
}

bool operator==(const Vector2i & left, const Vector2i & right)
{
	return ((left.x() == right.x()) && (left.y() == right.y()));
}

bool operator!=(const Vector2i & left, const Vector2i & right)
{
	return !(left==right);
}

Vector2i& Vector2i::operator=(Vector2i rhs)
{
	_x = rhs.x();
	_y = rhs.y();
	return *this;
}

Vector2i& Vector2i::operator+=(Vector2i rhs)
{
	_x=this->x() + rhs.x();
	_y = this->y() + rhs.y();
	return *this;
	// TODO: insert return statement here
}

Vector2i& Vector2i::operator-=(Vector2i rhs)
{
	return *this - rhs;
}

std::string Vector2i::toString() const
{
	std::string nfo = "(" + std::to_string(_x) + " ; " + std::to_string(_y) + ")";
	return nfo;
}


//Vector2i& Vector2i::operator=(const Vector2i& rhs)
//{
//	x = rhs.x;
//	y = rhs.y;
//	return *this;
//}

Vector2i operator+(Vector2i& right, const Vector2i& left)
{
	Vector2i temp = right;
	temp += left;
	return temp;
}

Vector2i& operator*(const Vector2i& right, const Vector2i& left)
{
	return Vector2i(right.x() * left.x(), right.y() * left.y());
}

Vector2i& operator*(const Vector2i& right, const int& left)
{
	return Vector2i(right.x() *left, right.y() + right.y()* left);
}

Vector2i operator-(const Vector2i& right, const Vector2i& left)
{
	return Vector2i(right.x() - left.x(), right.y() - left.y());
}

inline bool operator>(const Vector2i& left, const Vector2i& right) 
{
	return (left.x()> right.x()) || (left.y() > right.y());
}

inline bool Vector2i::operator<(const Vector2i& left) const
{
	return true;
	//return !(*this > left);
}

std::istream& operator >> (std::istream &stream, Vector2i& right)
{
	std::cin >> right._x >> right._y;
	stream.setstate(std::ios::failbit);
	return stream;
}

int RandomGenerator::getInt(const int& min, const int& max)
{
	return (std::rand() % max) + min;
}

int RandomGenerator::getIntforMove()
{
	switch (getInt(0, 3))
	{
	case 0:
		return 0;

	case 1:
		return 1;

	case 2:
		return -1;
	}
}

Vector2i RandomGenerator::getVector2i(const Vector2i& X, const Vector2i& Y)// (First, NumberCount)
{
	return Vector2i(RandomGenerator::getIntforMove(), RandomGenerator::getIntforMove());
}

// a.x =min a.y = max for Xpos, b.x=min b.y max Ypos
Vector2i RandomGenerator::randomVector2i(const Vector2i & a, const Vector2i & b)
{
	return Vector2i(getInt(a.x(),a.y()), getInt(b.x(),b.y()));
}

MathX::MathX()
{
}


MathX::~MathX()
{
}
