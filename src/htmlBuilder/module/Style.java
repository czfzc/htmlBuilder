package htmlBuilder.module;

import java.util.LinkedHashMap;

import htmlBuilder.builder.moduleBuilder;

public class Style extends Module{
	/**
	 * 
	 * @param jsCodeName Ϊnull������
	 */

	@SuppressWarnings("serial")
	public Style(String cssCodeName) {
		super("script", new LinkedHashMap<String,String>(){
			{
				this.put("type", "text/javascript");
			}
		});
		if(cssCodeName!=null)
			this.innerCode(new moduleBuilder(cssCodeName).getCssCode());
	}

}
