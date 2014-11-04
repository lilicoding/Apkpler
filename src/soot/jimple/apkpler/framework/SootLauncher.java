package soot.jimple.apkpler.framework;

import java.util.List;
import java.util.Map;

import soot.Body;
import soot.G;
import soot.PackManager;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.jimple.apkpler.interf.IPlugin;
import soot.options.Options;
import soot.util.Chain;

public class SootLauncher extends SceneTransformer
{	
	private ApkplerTransformer transformer = new ApkplerTransformer();
	
	
	
	public SootLauncher(ApkplerTransformer transformer)
	{
		this.transformer = transformer;
	}
	
	public void launch(String apkPath, String androidJars)
	{
		String[] args2 =
        {
            "-android-jars", androidJars,
            "-process-dir", apkPath,
            "-ire",
            "-pp",
            "-allow-phantom-refs",
            "-w",
			"-p", "cg", "enabled:true"
        };
		
		G.reset();
		
		Options.v().set_src_prec(Options.src_prec_apk);
		Options.v().set_output_format(Options.output_format_dex);
		
        PackManager.v().getPack("wjtp").add(new Transform("wjtp.apkpler", new SootLauncher(transformer)));
		
        soot.Main.main(args2);
	}
	
	public void runPlugin(IPlugin plugin)
	{
		plugin.sceneTransform();
		
		Chain<SootClass> classes = Scene.v().getClasses();
		for (SootClass sc : classes)
		{
			List<SootMethod> methods = sc.getMethods();
			for (SootMethod sm : methods)
			{
				try
				{
					Body b = sm.retrieveActiveBody();
					
					plugin.bodyTransform(b);
				}
				catch (Exception ex)
				{
					//System.out.println("EXCEPTION: cannot retrieve active body for method " + sm);
				}
			}
		}
		
	}
	
	@Override
	protected void internalTransform(String phaseName, Map<String, String> options) 
	{
		List<IPlugin> plugins = transformer.getTransformers();
		
		for (IPlugin plugin : plugins)
		{
			System.out.println("[Apkpler] start to launch " + plugin.getName());
			runPlugin(plugin);
		}
	}
}
