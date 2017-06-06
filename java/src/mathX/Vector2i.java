package mathX;

public final class Vector2i implements java.io.Serializable
{
	private int _x;
	private int _y;
	
	public Vector2i()
	{
		_x=0;
		_y=0;
	}
	
	public Vector2i(Vector2i vec)
	{
		_x=vec.x();
		_y=vec.y();
	}
	public Vector2i(String toVec)
	{
		Vector2i vec= toVector2i(toVec);
		_x=vec.x();
		_y=vec.y();
	}
	
	public Vector2i(int x, int y)
	{
		_x=x;
		_y=y;
		// TODO Auto-generated constructor stub
	}
		
	public Vector2i(float x, float y)
	{
		_x=(int) x;
		_y=(int) y;
		// TODO Auto-generated constructor stub
	}

	public final int x() { return _x; }
	public final int y() { return _y; }
	
	public void x(int x) { _x=x; }
	public void y(int y) { _y=y; }
	
	public boolean equals(final Vector2i rhs)
	{
		return rhs.x()== _x && rhs.y() == _y;
	}
	
	public String toString()
	{
		String stringed=x() + " " + y();
		
		return stringed;
	}
	
	public static Vector2i toVector2i(String toVec)
	{
		String[] part=toVec.split("\\s");
		return new Vector2i(Integer.parseInt(part[0]),Integer.parseInt(part[1]));
	}
	
	public Vector2i add(Vector2i number)//Vector2i... numbers)
	{
		return new Vector2i(x()+number.x(), y()+number.y());
	}
	
	public final Vector2i sub(final Vector2i number)//Vector2i... numbers)
	{
		return new Vector2i(x()-number.x(), y()-number.y());
	}
	
	public final Vector2i multiply(final Vector2i number)//Vector2i... numbers)
	{

		return new Vector2i(x()*number.x(), y()*number.y());
	}
	
	public final Vector2i div(final Vector2i number)//Vector2i... numbers)
	{
		return new Vector2i(x()/number.x(), y()/number.y());
	}
	
	public final Vector2i get()
	{
		return new Vector2i(x(), y());
	}
	
	public final void set(final Vector2i rhs)
	{
		_x=rhs.x();
		_y=rhs.y();
	}
}