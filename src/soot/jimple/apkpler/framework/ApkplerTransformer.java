package soot.jimple.apkpler.framework;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import soot.jimple.apkpler.interf.IPlugin;

public class ApkplerTransformer
{
	List<IPlugin> transformers = new ArrayList<IPlugin>();
	
	public ApkplerTransformer()
	{
		
	}
	
	public ApkplerTransformer(List<Plugin> plugins)
	{
		for (Plugin plugin : plugins)
		{
			try
			{
				ClassLoader loader = ApkplerTransformer.class.getClassLoader();
				JarClassLoader jarLoader = new JarClassLoader(new URL[]{}, loader);
				
				Class<?> clazz = null;
				
				if (plugin.getPath().equals(Plugin.PATH_IN_FRAMEWORK))
				{
					clazz = loader.loadClass(plugin.getCls());
				}
				else
				{
					jarLoader.addJar(new File(plugin.getPath()).toURI().toURL());
					clazz = jarLoader.loadClass(plugin.getCls());
				}
				
				IPlugin ip = (IPlugin) clazz.newInstance();
				addPlugin(ip);
				
				jarLoader.close();
			}
			catch (Exception ex)
			{
				System.out.println("EXCEPTION:" + plugin);
			}
			
		}
	}
	
	public void addPlugin(IPlugin plugin)
	{
		transformers.add(plugin);
	}
	
	public void removePlugin(IPlugin plugin)
	{
		transformers.remove(plugin);
	}

	public List<IPlugin> getTransformers() {
		return transformers;
	}
}
