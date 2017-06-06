package mathX;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomGenerator
{

	private RandomGenerator()
	{		
		// TODO Auto-generated constructor stub
	}
	
	public static final int getInt(final int min, final int max )
	{		
		return ThreadLocalRandom.current().nextInt(min, max + 1);
		
	}
		
	public static final Vector2i randomVector2i(final Vector2i a, final Vector2i b)
	{
		return new Vector2i(getInt(a.x(), a.y()), getInt(b.x(),b.y()));
	}

}