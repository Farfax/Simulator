package core.simulator;

import java.util.Stack;

public final  class StatusBar implements java.io.Serializable
{
	Stack<String> nfo;
	
	public StatusBar()
	{
		nfo=new Stack<>();
		// TODO Auto-generated constructor stub
	}
	
	public void printInfo()
	{
		while(!nfo.isEmpty())
			System.out.println(nfo.pop());
	}
	
	public void addInfo(String info)
	{
		nfo.push(info);
	}
	
	public Stack<String> get()
	{
		return nfo;
	}
	
	public void clear()
	{
		nfo.clear();
	}
	

}