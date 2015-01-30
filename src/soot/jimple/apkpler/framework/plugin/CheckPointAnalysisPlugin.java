package soot.jimple.apkpler.framework.plugin;

import soot.jimple.apkpler.interf.DefaultPlugin;
import soot.jimple.apkpler.model.CheckPoint;
import soot.jimple.apkpler.util.TimeUtil;

/**
 * Extracting app data first and then outputting them as a structured data
 * 
 *
 */
public class CheckPointAnalysisPlugin extends DefaultPlugin 
{
	private String[] checkPointPaths;
	
	public CheckPointAnalysisPlugin(String appPath, String name) 
	{
		super(appPath, name);
	}
	
	public CheckPointAnalysisPlugin(String appPath, String name, String input) 
	{
		super(appPath, name, input);
		if (input.contains(":"))
		{
			checkPointPaths = input.split(":");
		}
		else
		{
			checkPointPaths = new String[1];
			checkPointPaths[0] = input;
		}
	}

	@Override
	public void sceneTransform() 
	{
		CheckPoint[] checkPoints = new CheckPoint[checkPointPaths.length];
		for (int i = 0; i < checkPoints.length; i++)
		{
			checkPoints[i] = new CheckPoint().read(checkPointPaths[i]);
			System.out.println("====> CheckPoint" + i);
			System.out.println(checkPoints[i]);
		}
		
		
		for (int i = 1; i < checkPoints.length; i++)
		{
			System.out.println("====> Plugin" + i + " seconds");
			System.out.println("Time Cost:" + TimeUtil.computeTimeDifferenceInSeconds(
					checkPoints[i-1].getCurrentTime(), 
					checkPoints[i].getCurrentTime()));
			System.out.println("#. of different Classes:" + (checkPoints[i].getApk().getNumOfClasses() - checkPoints[i-1].getApk().getNumOfClasses()));
			System.out.println("#. of different Methods:" + (checkPoints[i].getApk().getNumOfMethods() - checkPoints[i-1].getApk().getNumOfMethods()));
			System.out.println("#. of different Fields:" + (checkPoints[i].getApk().getNumOfFields() - checkPoints[i-1].getApk().getNumOfFields()));
			System.out.println("#. of different Components:" + (checkPoints[i].getApk().getNumOfComponent() - checkPoints[i-1].getApk().getNumOfComponent()));
			System.out.println("#. of different Activity:" + (checkPoints[i].getApk().getNumOfActivity() - checkPoints[i-1].getApk().getNumOfActivity()));
			System.out.println("#. of different Service:" + (checkPoints[i].getApk().getNumOfService() - checkPoints[i-1].getApk().getNumOfService()));
			System.out.println("#. of different Receiver:" + (checkPoints[i].getApk().getNumOfReceiver() - checkPoints[i-1].getApk().getNumOfReceiver()));
			System.out.println("#. of different Provider:" + (checkPoints[i].getApk().getNumOfProvider() - checkPoints[i-1].getApk().getNumOfProvider()));
		}
		
	}
	
}
