package soot.jimple.apkpler.framework;

public class Plugin
{
	public static final String PATH_IN_FRAMEWORK = "IN-FRAMEWORK";
	
	private String cls;
	private String path;
	private String input;
	
	public Plugin(String cls, String path, String input)
	{
		this.cls = cls;
		this.path = path;
		this.input = input;
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

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@Override
	public String toString() 
	{
		return cls + "@" + path;
	}
}
