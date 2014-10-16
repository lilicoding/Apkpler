package soot.jimple.apkpler.interf;

import soot.Body;

public interface IPlugin 
{
	public void sceneTransform();
	public void bodyTransform(Body body);
	public String getName();
}
