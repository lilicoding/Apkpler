package soot.jimple.apkpler.interf;

import soot.Body;

public class DefaultPlugin implements IPlugin
{
	protected String name;
	protected String input;
	
	public DefaultPlugin(String name)
	{
		this.name = name;
	}
	
	public DefaultPlugin(String name, String input)
	{
		this.name = name;
		this.input = input;
	}
	
	@Override
	public void sceneTransform() 
	{ 
		
	}

	@Override
	public void bodyTransform(Body body) 
	{ 
		
	}

	public String getInput() {
		return input;
	}

	@Override
	public String getName() 
	{
		return name;
	}
}
