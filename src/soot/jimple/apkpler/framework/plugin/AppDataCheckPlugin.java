package soot.jimple.apkpler.framework.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import soot.jimple.apkpler.interf.DefaultPlugin;
import soot.jimple.apkpler.model.CheckPoint;

/**
 * Extracting app data first and then outputting them as a structured data
 * 
 *
 */
public class AppDataCheckPlugin extends DefaultPlugin 
{
	public AppDataCheckPlugin(String appPath, String name) 
	{
		super(appPath, name);
	}
	
	public AppDataCheckPlugin(String appPath, String name, String input) 
	{
		super(appPath, name, input);
	}

	@Override
	public void sceneTransform() 
	{
		CheckPoint checkPoint = new CheckPoint().check();
		
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(input)));
			oos.writeObject(checkPoint);
			oos.flush();
			oos.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
