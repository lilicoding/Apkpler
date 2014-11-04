package soot.jimple.apkpler.interf;

import java.util.HashMap;

/**
 * This class is designed to share variables between different plugins.
 * For example, one plugin can put some infos (key-value) to #refs# and 
 * later other plugin can read that infos.
 */
public class SharedReferences 
{
	public static HashMap<String, String> refs = new HashMap<String, String>();
	
	public boolean exist(String key)
	{
		return refs.entrySet().contains(key);
	}
}
