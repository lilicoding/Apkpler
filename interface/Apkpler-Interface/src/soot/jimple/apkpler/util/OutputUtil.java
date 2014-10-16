package soot.jimple.apkpler.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputUtil 
{
	private static String PREFIX;
	public static void setPrefix(String name)
	{
		PREFIX = name;
	}
	
	public static void output(String str)
	{
		System.out.println(PREFIX + str);
	}
	
	public static void output(String filename, String str)
	{
		PrintWriter out = null;
		try 
		{
		    out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
		    out.println(str);
		    out.close();
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		finally
		{
			if (null != out)
			{
				out.close();
			}
		}
	}
}
