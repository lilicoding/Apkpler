package soot.jimple.apkpler.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import soot.jimple.apkpler.util.TimeUtil;

public class CheckPoint implements Serializable
{
	private static final long serialVersionUID = -782450604046645529L;
	private APK apk;
	private long currentTime;
	
	public CheckPoint check()
	{
		currentTime = System.currentTimeMillis();
		apk = new APK().check();
		
		return this;
	}

	public CheckPoint read(String filePath)
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)));
			CheckPoint checkPoint = (CheckPoint) ois.readObject();
			
			this.apk = checkPoint.getApk();
			this.currentTime = checkPoint.getCurrentTime();
			
			ois.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return this;
	}
	
	
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Current Time:" + TimeUtil.toReadableTime(currentTime) + "\n");
		sb.append("#. of Classes:" + apk.getNumOfClasses() + "\n");
		sb.append("#. of Methods:" + apk.getNumOfMethods() + "\n");
		sb.append("#. of Fields:" + apk.getNumOfFields() + "\n");
		sb.append("#. of Components:" + apk.getNumOfComponent() + "\n");
		sb.append("#. of Activity:" + apk.getNumOfActivity() + "\n");
		sb.append("#. of Service:" + apk.getNumOfService() + "\n");
		sb.append("#. of Receiver:" + apk.getNumOfReceiver() + "\n");
		sb.append("#. of Provider:" + apk.getNumOfProvider() + "\n");
		
		return sb.toString();
	}

	public APK getApk() {
		return apk;
	}

	public void setApk(APK apk) {
		this.apk = apk;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
}
