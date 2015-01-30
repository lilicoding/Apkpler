package soot.jimple.apkpler.framework.test;

import java.util.List;

import soot.jimple.apkpler.framework.ApkplerTransformer;
import soot.jimple.apkpler.framework.ConfigParser;
import soot.jimple.apkpler.framework.Plugin;
import soot.jimple.apkpler.framework.SootLauncher;
import soot.jimple.apkpler.util.OutputUtil;
import soot.jimple.apkpler.util.TimeUtil;


public class Main 
{
	/**
	 * args[0] = apkPath
	 * args[1] = androidJars, e.g., https://github.com/Sable/android-platforms.git
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		OutputUtil.println("Start Time:" + startTime);
		
		String apkPath = args[0];
		String androidJars = args[1];
		
		ConfigParser parser = new ConfigParser();
		parser.parse();
		List<Plugin> plugins = parser.getPlugins();
		
		ApkplerTransformer apkplerTransformer = new ApkplerTransformer(plugins, apkPath);
		
		SootLauncher launcher = new SootLauncher(apkplerTransformer);
		launcher.launch(apkPath, androidJars);
		
		long endTime = System.currentTimeMillis();
		OutputUtil.println("End Time:" + endTime);
		OutputUtil.print("====>" + TimeUtil.computeTimeDifferenceInSeconds(startTime, endTime));
	}
}
