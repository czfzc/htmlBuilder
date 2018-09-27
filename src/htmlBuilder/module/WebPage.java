package htmlBuilder.module;

public class WebPage extends Module{
	
	private static String title=null;

	public WebPage(String title) {
		super(null);
		WebPage.title=title==null?"":title;
		this.add(new Html().add(new Head().add(new Title(WebPage.title))).add(new Body()));
	}

	public static void main(String[] args){
		System.out.println(new WebPage("hello").Code());
	}
}
