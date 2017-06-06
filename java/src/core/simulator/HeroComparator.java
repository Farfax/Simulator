package core.simulator;

import java.util.Comparator;

import characters.Hero;

final class HeroComparator implements Comparator<Hero>
{
	@Override
	public int compare(Hero lhs, Hero rhs)
	{
		if (lhs.activity == rhs.activity)
		{
			if(lhs.ID< rhs.ID)
			{
				return 1;
			}
			else if(lhs.ID< rhs.ID)
			{
				return -1;
			}
			else return 0;
		}
		else if(lhs.activity < rhs.activity)
			return 1;

		return 0;
	}
}
