package htmlBuilder.module;

import java.util.LinkedHashMap;

public class A extends Module{

	@SuppressWarnings("serial")
	public A(String href,String value) {
		super("script", new LinkedHashMap<String,String>(){
			{
				if(href!=null)
					this.put("href", href);
			}
		});
		if(value!=null){
			this.innerCode(value);
		}
	}

}
