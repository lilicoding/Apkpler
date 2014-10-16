package soot.jimple.apkpler.util;

public class StringUtil 
{
	public static boolean isEmpty(String str)
	{
		if (null == str || str.isEmpty())
		{
			return true;
		}
		
		return false;
	}
}
