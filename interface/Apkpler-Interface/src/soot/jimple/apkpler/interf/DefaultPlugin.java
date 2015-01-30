package soot.jimple.apkpler.interf;

import java.util.Map;

import soot.Body;
import soot.G;
import soot.PackManager;
import soot.SceneTransformer;
import soot.Transform;
import soot.Transformer;
import soot.options.Options;

public class DefaultPlugin implements IPlugin
{
	protected String appPath;
	protected String name;
	protected String input;
	
	public DefaultPlugin(String appPath, String name)
	{
		this(appPath, name, "");
	}
	
	public DefaultPlugin(String appPath, String name, String input)
	{
		this.appPath = appPath;
		this.name = name;
		this.input = input.trim();
	}
	
	protected void debugSceneTransformer(String androidJars)
	{
		String[] args =
        {
            "-android-jars", androidJars,
            "-process-dir", appPath,
            "-cp", ".:" + androidJars + "/android-18/android.jar",
            "-ire",
            //"-pp",
            "-allow-phantom-refs",
            "-w",
			//"-p", "cg", "enabled:false"
        };
		
		G.reset();
		
		Options.v().set_src_prec(Options.src_prec_apk);
		Options.v().set_output_format(Options.output_format_dex);
		
        PackManager.v().getPack("wjtp").add(new Transform("wjtp.defaultPlugin", new SceneTransformer()
        {
        	DefaultPlugin defaultPlugin = null;
        	public Transformer setDefaultPlugin(DefaultPlugin defaultPlugin)
        	{
        		this.defaultPlugin = defaultPlugin;
        		return this;
        	}
        	
			@Override
			protected void internalTransform(String phaseName, Map<String, String> options) 
			{
				defaultPlugin.sceneTransform();
				
			}}.setDefaultPlugin(this))
        );
		
        soot.Main.main(args);
	}
	
	@Override
	public void sceneTransform() 
	{ 
		
	}

	@Override
	public void bodyTransform(Body body) 
	{ 
		
	}

	public String getInput() {
		return input;
	}

	@Override
	public String getName() 
	{
		return name;
	}
}
