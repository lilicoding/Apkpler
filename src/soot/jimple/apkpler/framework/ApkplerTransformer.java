package soot.jimple.apkpler.framework;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import soot.jimple.apkpler.interf.IPlugin;
import soot.jimple.apkpler.util.StringUtil;

public class ApkplerTransformer
{
	List<IPlugin> transformers = new ArrayList<IPlugin>();
	String appPath = "";
	
	public ApkplerTransformer()
	{
		
	}
	
	public ApkplerTransformer(List<Plugin> plugins, String appPath)
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
				
				IPlugin ip = null;
				if (StringUtil.isEmpty(plugin.getInput()))
				{
					Class<?>[] pType = new Class[] {
						Class.forName("java.lang.String"),
						Class.forName("java.lang.String")
					};
					Object[] pObj = new Object[] {
						appPath,
						plugin.getCls()
					};
					
					Constructor<?> constructor = clazz.getConstructor(pType);
					ip = (IPlugin) constructor.newInstance(pObj);
				}
				else
				{
					Class<?>[] pType = new Class[] {
						Class.forName("java.lang.String"),
						Class.forName("java.lang.String"),
						Class.forName("java.lang.String")
					};
					Object[] pObj = new Object[] {
						appPath,
						plugin.getCls(),
						plugin.getInput()
					};
					
					Constructor<?> constructor = clazz.getConstructor(pType);
					ip = (IPlugin) constructor.newInstance(pObj);
				}
				addPlugin(ip);
				
				jarLoader.close();
			}
			catch (Exception ex)
			{
				System.out.println("EXCEPTION:" + plugin);
				ex.printStackTrace();
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
