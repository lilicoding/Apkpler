package soot.jimple.apkpler.framework;

public class Plugin
{
	public static final String PATH_IN_FRAMEWORK = "IN-FRAMEWORK";
	
	private String cls;
	private String path;
	
	public Plugin(String cls, String path)
	{
		this.cls = cls;
		this.path = path;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() 
	{
		return cls + "@" + path;
	}
}
