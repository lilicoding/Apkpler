package soot.jimple.apkpler.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.util.Chain;

public class APK implements Serializable
{
	private static final long serialVersionUID = -4012799265096463639L;
	private Map<String, CLS> clses;
	private int numOfComponent = 0;
	private int numOfActivity = 0;
	private int numOfService = 0;
	private int numOfReceiver = 0;
	private int numOfProvider = 0;
	
	void init()
	{
		clses = new HashMap<String, CLS>();
		numOfComponent = 0;
		numOfActivity = 0;
		numOfService = 0;
		numOfReceiver = 0;
		numOfProvider = 0;
	}
	
	public APK check()
	{
		init();
		
		Chain<SootClass> sootClasses = Scene.v().getApplicationClasses();
		for (Iterator<SootClass> iter = sootClasses.snapshotIterator(); iter.hasNext(); )
		{
			SootClass sc = iter.next();
			
			int compType = getComponentType(sc);
			switch (compType)
			{
			case 1:
				numOfComponent++;
				numOfActivity++;
				break;
			case 2:
				numOfComponent++;
				numOfService++;
				break;
			case 3:
				numOfComponent++;
				numOfReceiver++;
				break;
			case 4:
				numOfComponent++;
				numOfProvider++;
				break;
			}
			
			CLS cls = new CLS(sc.getName());
			
			Chain<SootField> sootFields = sc.getFields();
			for (Iterator<SootField> fieldIter = sootFields.snapshotIterator(); fieldIter.hasNext(); )
			{
				SootField sf = fieldIter.next();
				cls.getFields().add(sf.getSignature());
			}
			
			List<SootMethod> sootMethods = sc.getMethods();
			for (SootMethod sm : sootMethods)
			{
				cls.getMethods().add(sm.getModifiers() + sm.getSignature());
			}
			
			clses.put(sc.getName(), cls);
		}
		
		return this;
	}

	/**
	 * 1:Activity
	 * 2:Service
	 * 3:Receiver
	 * 4:Provider
	 * 
	 * @param sc
	 * @return
	 */
	public int getComponentType(SootClass sc)
	{
		while (sc.hasSuperclass())
		{
			String className = sc.getName();
			
			if (className.equals("android.app.Activity"))
			{
				return 1;
			}
			else if (className.equals("android.app.Service"))
			{
				return 2;
			}
			else if (className.equals("android.content.BroadcastReceiver"))
			{
				return 3;
			}
			else if (className.equals("android.content.ContentProvider"))
			{
				return 4;
			}
			
			sc = sc.getSuperclass();
		}
		
		return -1;
	}
	
	public int getNumOfClasses()
	{
		return clses.size();
	}
	
	public int getNumOfMethods()
	{
		int count = 0;
		
		for (Entry<String, CLS> entry : clses.entrySet())
		{
			CLS cls = entry.getValue();
			count += cls.getMethods().size();
		}
		
		return count;
	}
	
	public int getNumOfFields()
	{
		int count = 0;
		
		for (Entry<String, CLS> entry : clses.entrySet())
		{
			CLS cls = entry.getValue();
			count += cls.getFields().size();
		}
		
		return count;
	}
	
	public Map<String, CLS> getClses() {
		return clses;
	}

	public void setClses(Map<String, CLS> clses) {
		this.clses = clses;
	}

	public int getNumOfComponent() {
		return numOfComponent;
	}

	public void setNumOfComponent(int numOfComponent) {
		this.numOfComponent = numOfComponent;
	}

	public int getNumOfActivity() {
		return numOfActivity;
	}

	public void setNumOfActivity(int numOfActivitie) {
		this.numOfActivity = numOfActivitie;
	}

	public int getNumOfService() {
		return numOfService;
	}

	public void setNumOfService(int numOfService) {
		this.numOfService = numOfService;
	}

	public int getNumOfProvider() {
		return numOfProvider;
	}

	public void setNumOfProvider(int numOfProvider) {
		this.numOfProvider = numOfProvider;
	}

	public int getNumOfReceiver() {
		return numOfReceiver;
	}

	public void setNumOfReceiver(int numOfReceiver) {
		this.numOfReceiver = numOfReceiver;
	}
}
