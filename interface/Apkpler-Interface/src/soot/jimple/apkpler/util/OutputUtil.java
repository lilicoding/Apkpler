package soot.jimple.apkpler.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputUtil 
{
	private static String PREFIX = "[Apkpler]";
	public static void setPrefix(String name)
	{
		PREFIX = name;
	}
	
	public static void print(String str)
	{
		System.out.print(PREFIX + str);
	}
	
	public static void println(String str)
	{
		System.out.println(PREFIX + str);
	}
	
	public static void output(String filename, String str)
	{
		output(filename, str, false);
	}
	
	public static void outputln(String filename, String str)
	{
		output(filename, str, true);
	}
	
	private static void output(String filename, String str, boolean wrapper)
	{
		PrintWriter out = null;
		try 
		{
		    out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
		    if (wrapper)
		    {
		    	out.println(str);
		    }
		    else
		    {
		    	out.print(str);
		    }
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
