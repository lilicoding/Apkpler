package soot.jimple.apkpler.framework;

import java.net.URL;
import java.net.URLClassLoader;

public class JarClassLoader extends URLClassLoader 
{

	public JarClassLoader(URL[] urls) 
	{
		super(urls);
	}

	public JarClassLoader(URL[] urls, ClassLoader parent) 
	{
		super(urls, parent);
	}
	
	public void addJar(URL url)
	{
		addURL(url);
	}
}
