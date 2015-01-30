package soot.jimple.apkpler.util;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class Sha256Util 
{
	public static void main(String[] args)
	{
		String filePath = "libraryStudy/guava/guava-18.0.jar";
		System.out.println(getSha256Sum(filePath));
	}
	
	public static String getSha256Sum(String filePath) 
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
	        FileInputStream fis = new FileInputStream(filePath);
	 
	        byte[] dataBytes = new byte[1024];
	 
	        int nread = 0; 
	        while ((nread = fis.read(dataBytes)) != -1) {
	        	md.update(dataBytes, 0, nread);
	        };
	        fis.close();
	        byte[] mdbytes = md.digest();
	 
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<mdbytes.length;i++) 
	    	{
	    		hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
	    	}
	 
	    	
	    	return hexString.toString();
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex.getMessage());
		}
	}

}
