#pragma once
#include <iostream>
#include <string>

class MathX final
{
	MathX();
public:
	~MathX();
};

class Vector2i
{
	int _x, _y;
public:
	Vector2i();
	Vector2i(int __x, int y);
	Vector2i(const Vector2i& right);

	int x() const;
	int y() const;

	void x(int x);
	void y(int y);
	Vector2i(Vector2i&& right);

	Vector2i& reverse() const;
	Vector2i& opposed() const;
	Vector2i& operator=(Vector2i rhs);
	//Vector2i& operator=(const Vector2i& rhs);
	Vector2i& operator+=(Vector2i rhs);
	Vector2i& operator-=(Vector2i rhs);
	std::string toString() const;

	inline bool operator>(const Vector2i& left) const;
	inline bool operator<(const Vector2i& left) const;

	friend std::istream& operator >> (std::istream &stream, Vector2i& right);

};


Vector2i operator+(Vector2i& right, const Vector2i& left);
Vector2i& operator*(const Vector2i& right, const Vector2i& left);
Vector2i& operator*(const Vector2i& right, const int& left);
Vector2i operator-(const Vector2i& right, const Vector2i& left);
inline bool operator>(const Vector2i& left, const Vector2i& right);
bool operator==(const Vector2i& left, const Vector2i& right);
bool operator!=(const Vector2i& left, const Vector2i& right);

class RandomGenerator final	// TODO: use <random> NOT rand()
{
	RandomGenerator();
public: 
	static int getIntforMove();
	static int getInt(const int& min = 1, const int& max = 2);
	static Vector2i getVector2i(const Vector2i& X, const Vector2i& Y);
	static Vector2i randomVector2i(const Vector2i& a, const  Vector2i& b);
};