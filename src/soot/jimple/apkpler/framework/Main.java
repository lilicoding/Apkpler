package soot.jimple.apkpler.framework;

import java.util.List;


public class Main 
{
	/**
	 * args[0] = apkPath
	 * args[1] = androidJars
	 */
	public static void main(String[] args)
	{
		String apkPath = args[0];
		String androidJars = args[1];
		
		ConfigParser parser = new ConfigParser();
		parser.parse();
		List<Plugin> plugins = parser.getPlugins();
		
		ApkplerTransformer apkplerTransformer = new ApkplerTransformer(plugins);
		
		SootLauncher launcher = new SootLauncher(apkplerTransformer);
		launcher.launch(apkPath, androidJars);
	}
}
