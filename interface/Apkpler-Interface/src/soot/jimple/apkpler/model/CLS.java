package soot.jimple.apkpler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CLS implements Serializable
{
	private static final long serialVersionUID = -1822070257760226160L;
	private String name;
	private Set<String> fields;
	private Set<String> methods;
	
	public CLS(String name)
	{
		this.name = name;
		fields = new HashSet<String>();
		methods = new HashSet<String>();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name: " + name + "\n");
		sb.append("Field: " + "\n");
		for (String field : fields)
		{
			sb.append(field + "\n");
		}
		sb.append("Method: " + "\n");
		for (String method : methods)
		{
			sb.append(method + "\n");
		}
		
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getFields() {
		return fields;
	}

	public void setFields(Set<String> fields) {
		this.fields = fields;
	}

	public Set<String> getMethods() {
		return methods;
	}

	public void setMethods(Set<String> methods) {
		this.methods = methods;
	}
}
