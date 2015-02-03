package soot.jimple.apkpler.plugin.cpa4rdc;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import soot.jimple.apkpler.interf.DefaultPlugin;
import soot.jimple.apkpler.model.CLS;
import soot.jimple.apkpler.model.CheckPoint;

/**
 * Extracting app data first and then outputting them as a structured data
 * 
 *
 */
public class CheckPointAnalysis4RDCPlugin extends DefaultPlugin 
{
	private String metaDataPath;
	private String checkPointPath1;
	private String checkPointPath2;
	
	private Set<String> removedClasses = null;
	private Set<String> addedClasses = null;
	
	public CheckPointAnalysis4RDCPlugin(String appPath, String name) 
	{
		super(appPath, name);
	}
	
	public CheckPointAnalysis4RDCPlugin(String appPath, String name, String input) 
	{
		super(appPath, name, input);
		if (input.contains(":"))
		{
			String[] paths = input.split(":");
			if (3 != paths.length)
			{
				throw new RuntimeException("wrong input args.");
			}
			
			metaDataPath = paths[0];
			checkPointPath1 = paths[1];
			checkPointPath2 = paths[2];
		}
		else
		{
			throw new RuntimeException("wrong input args.");
		}
		
		removedClasses = new HashSet<String>();
		addedClasses = new HashSet<String>();
	}

	private void loadMetaData()
	{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(metaDataPath);
		
		try
		{
			Document doc = (Document) builder.build(xmlFile);
			Element rootEle = doc.getRootElement();
			Element removeEle = rootEle.getChild("class-remove");
			String[] strs = removeEle.getContent().toString().split(":");
			
			for (String str : strs)
			{
				removedClasses.add(str);
			}
			
			Element addEle = rootEle.getChild("class-add");
			strs = addEle.getContent().toString().split(":");
			
			for (String str : strs)
			{
				addedClasses.add(str);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void sceneTransform() 
	{
		loadMetaData();
		
		//Before and after RDC plugin
		CheckPoint[] checkPoints = new CheckPoint[2];
		checkPoints[0] = new CheckPoint().read(checkPointPath1);
		checkPoints[1] = new CheckPoint().read(checkPointPath2);
		
		Map<String, CLS> clsesOfCP1 = checkPoints[0].getApk().getClses();
		Map<String, CLS> clsesOfCP2 = checkPoints[1].getApk().getClses();
		
		boolean failed = false;
		
		//for all added classes, which do not exist in checkpoint 1
		for (String cls : addedClasses)
		{
			if (clsesOfCP1.containsKey(cls))
			{
				failed = true;
				break;
			}
		}
		
		//for all removed classes, which do not exist in checkpoint 2 any more
		for (String cls : removedClasses)
		{
			if (clsesOfCP2.containsKey(cls))
			{
				failed = true;
				break;
			}
		}
		
		
		if (failed)
		{
			System.out.println("[CheckPointAnalysis4RDCPlugin]" + "FAILED");
		}
		else
		{
			System.out.println("[CheckPointAnalysis4RDCPlugin]" + "SUCCESSED");
		}
	}
	
}
