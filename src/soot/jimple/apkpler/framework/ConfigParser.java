package soot.jimple.apkpler.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ConfigParser 
{
	private static final String ELE_PLUGINS = "plugins";
	private static final String ATT_CLS = "cls";
	private static final String ATT_PATH = "path";
	
	private String configPath = "res/apkpler.xml";

	private List<Plugin> plugins = new ArrayList<Plugin>();
	
	public void parse()
	{
		SAXBuilder builder = new SAXBuilder();
		
		try
		{
			Document doc = builder.build(configPath);
			Element ele = doc.getRootElement().getChild(ELE_PLUGINS);
			List<Element> plugins = ele.getChildren();
			
			for (Iterator<Element> iter = plugins.iterator(); iter.hasNext(); )
			{
				Element plugin = iter.next();
				String cls = plugin.getAttributeValue(ATT_CLS);
				String path = plugin.getAttributeValue(ATT_PATH);
				String input = plugin.getText().trim();
				
				Plugin p = new Plugin(cls, path, input);
				addPlugin(p);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void setConfigPath(String configPath) 
	{
		this.configPath = configPath;
	}
	
	public void addPlugin(Plugin plugin)
	{
		plugins.add(plugin);
	}
	
	public List<Plugin> getPlugins() {
		return plugins;
	}
}
