package htmlBuilder.module;

import java.util.LinkedHashMap;

import htmlBuilder.builder.moduleBuilder;

public class Div extends Module{
	
	/**
	 * 
	 * @param className Ϊnull������
	 * @param innerCode Ϊnull��û��
	 */
	
	@SuppressWarnings("serial")
	public Div(String className,String innerCode) {     
		super("div",className==null?null:new LinkedHashMap<String,String>(){
			{	
				this.put("class", className);
			}
		});
		if(innerCode!=null){
			this.innerCode(innerCode);
		}
	}
	
	/**
	 * 
	 * @param className Ϊnull������
	 * @param modulebuilder Ϊnull��û��
	 */
	
	@SuppressWarnings("serial")
	public Div(String className,moduleBuilder modulebuilder) {     
		super("div",className==null?null:new LinkedHashMap<String,String>(){
			{	
				this.put("class", className);
			}
		});
		if(modulebuilder!=null){
			this.innerCode(modulebuilder.getCode());
		}
	}
	
	public Div(){
		super("div");
	}
	
	public String Class(){
		return this.getValue("class");
	}
	
}
