package soot.jimple.apkpler.interf;

import java.util.HashMap;

public class SharedReferences 
{
	public static HashMap<String, String> refs = new HashMap<String, String>();
	
	public boolean exist(String key)
	{
		return refs.entrySet().contains(key);
	}
}
