package htmlBuilder.module;

import java.util.LinkedHashMap;

import htmlBuilder.builder.moduleBuilder;

public class Script extends Module{
	
	/**
	 * 
	 * @param jsCodeName Ϊnull������
	 * @param url Ϊnull��û��
	 */

	@SuppressWarnings("serial")
	public Script(String jsCodeName,String src) {
		super("script", new LinkedHashMap<String,String>(){
			{
				this.put("type", "text/javascript");
				if(src!=null)
					this.put("src", src);
			}
		});
		if(jsCodeName!=null)
			this.innerCode(new moduleBuilder(jsCodeName).getJsCode());
	}
	
	
	public String Url(String url){
		return this.getValue("url");
	}

}
