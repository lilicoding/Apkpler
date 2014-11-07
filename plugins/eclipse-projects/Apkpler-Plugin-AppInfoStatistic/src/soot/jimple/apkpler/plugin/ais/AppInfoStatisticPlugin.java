package soot.jimple.apkpler.plugin.ais;

import soot.jimple.apkpler.interf.DefaultPlugin;
import soot.jimple.apkpler.interf.SharedReferences;
import soot.jimple.infoflow.android.manifest.ProcessManifest;

public class AppInfoStatisticPlugin extends DefaultPlugin 
{
	public static final String PACKAGE_NAME = "PACKAGE-NAME";
	
	public AppInfoStatisticPlugin(String appPath, String name) 
	{
		super(appPath, name);
	}

	@Override
	public void sceneTransform() 
	{
		try 
		{
			ProcessManifest manifest = new ProcessManifest(appPath);
			String pkgName = manifest.getPackageName();
			SharedReferences.refs.put(PACKAGE_NAME, pkgName);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
