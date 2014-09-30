package soot.jimple.apkpler.framework;

import java.util.List;

import org.junit.Test;

public class ConfigParserTest 
{
	@Test
	public void testParse()
	{
		ConfigParser cp = new ConfigParser();
		cp.parse();
		
		List<Plugin> plugins = cp.getPlugins();
		
		for (Plugin p : plugins)
		{
			System.out.println(p);
		}
	}
}
