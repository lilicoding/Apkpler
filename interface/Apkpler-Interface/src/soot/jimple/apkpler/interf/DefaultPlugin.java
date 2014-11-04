package soot.jimple.apkpler.interf;

import soot.Body;

public class DefaultPlugin implements IPlugin
{
	protected String appPath;
	protected String name;
	protected String input;
	
	public DefaultPlugin(String appPath, String name)
	{
		this(appPath, name, "");
	}
	
	public DefaultPlugin(String appPath, String name, String input)
	{
		this.appPath = appPath;
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
