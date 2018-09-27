package htmlBuilder.builder;

import java.io.StringReader;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import htmlBuilder.module.Module;

public class moduleBuilder extends Builder{


	private final static String htmlXmlNode="hcode";
	private final static String cssXmlNode="ccode";
	private final static String jsXmlNode="jcode";
	private static String htmlCode="";
	private static String cssCode="";
	private static String jsCode="";
	public final static Integer HTML=0,CSS=1,JS=2;
	private static int status=-1;
	
	/**
	 * 构造时在link.xml链接文件里的代码取出
	 * @param moduleName 模块的名称
	 */

	public moduleBuilder(String moduleName){
		try {
			htmlCode=Tools.parse(linkfile).getRootElement()
					.selectSingleNode("/xml/module[@name='"+moduleName+"']")
					.selectSingleNode("hcode").asXML()
					.replace("<"+htmlXmlNode+">","")
					.replace("</"+htmlXmlNode+">", "")
					.replace("	","").trim();
			cssCode=Tools.parse(linkfile).getRootElement()
					.selectSingleNode("/xml/module[@name='"+moduleName+"']")
					.selectSingleNode("ccode").asXML()
					.replace("<"+cssXmlNode+">","")
					.replace("</"+cssXmlNode+">", "")
					.replace("	","").trim();
			jsCode=Tools.parse(linkfile).getRootElement()
					.selectSingleNode("/xml/module[@name='"+moduleName+"']")
					.selectSingleNode("jcode").asXML()
					.replace("<"+jsXmlNode+">","")
					.replace("</"+jsXmlNode+">", "")
					.replace("	","").trim();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param moduleName
	 * @param status 0为html 1为css 2为js
	 */
	
	public moduleBuilder(String moduleName,int status){    
		this(moduleName);
		moduleBuilder.status=status;
	}
	
	/**
	 * 
	 * @param code 加入的string代码
	 * @param status 0为html 1为css 2为js
	 * @param bool 无关紧要
	 */
	
	public moduleBuilder(String code,int status,boolean bool){
		switch(status){
			case 0:
				moduleBuilder.htmlCode=code;
				 break;
			case 1:
				moduleBuilder.cssCode=code;
				break;
			case 2:
				moduleBuilder.jsCode=code;
				break;
		default: break;
		}
		moduleBuilder.status=status;
	}
	
	/**
	 * 获取html代码
	 * @return
	 */
	
	public String getHtmlCode(){
		return htmlCode;
	}
	
	/**
	 * 获取css代码
	 * @return
	 */
	
	public String getCssCode(){
		return cssCode;
	}
	
	/**
	 * 获取js代码
	 * @return
	 */
	
	public String getJsCode(){
		return jsCode;
	}
	
	/**
	 * 获取对应status的代码  1是html 2是css 3是js
	 * @return
	 */
	
	public String getCode(){
		switch(status){
		case 0:
			return htmlCode;
		case 1:
			return cssCode;
		case 2:
			return jsCode;
		default:
			return null;
		}
			
	}
	
	/**
	 * moduleBuilder转module
	 * @return
	 */
	
	public Module toModule() {
		try {
			Element ele=null;
			SAXReader reader=new SAXReader();
			String str=this.getCode();
			Document doc=reader.read(new StringReader(str));
			ele=doc.getRootElement();
			return getModule(ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 想了我几个小时 心累
	 * @param ele
	 * @return
	 * @throws Exception
	 */
	
	private Module getModule(Element ele)throws Exception{
		Iterator<Element> it=ele.elementIterator();
		Module m=new Module(ele.getName());
		while(it.hasNext()){
			Element e=it.next();
			m.add(getModule(e));
		}
		return m;
	}
	

	public static void main(String[] args) throws DocumentException {
		// TODO Auto-generated method stub
		moduleBuilder builder=new moduleBuilder("nav",moduleBuilder.HTML);
		System.out.println(builder.toModule().Code());
		
	}
	
}
