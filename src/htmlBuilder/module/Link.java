package htmlBuilder.module;

import java.util.LinkedHashMap;

public class Link extends Module{
	
	@SuppressWarnings("serial")
	public Link(String href) {
		super("link", new LinkedHashMap<String,String>(){
			{
				this.put("rel","stylesheet");
				this.put("type", "text/css");
				if(href!=null)
					this.put("href", href);
			}
		});
	}
	
}
