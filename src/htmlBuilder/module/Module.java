package htmlBuilder.module;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import htmlBuilder.builder.moduleBuilder;

public class Module {
	
	private static String code="";
	private static int codeStyle=0;
	private LinkedList<Module> modules=null;
	private LinkedHashMap<String,String> attributes=null;
	private String nodeName=null;
	private String innerCode=null;
	
	/**
	 * 构造模块时传入模块最高节点名称和属性与值的哈希图
	 * @param nodeName
	 * @param attributes
	 */
	
	public Module(String nodeName,LinkedHashMap<String,String> attributes){
		this.attributes=attributes;
		this.nodeName=nodeName;
		modules=new LinkedList<>();
	}
	
	public Module(String nodeName,LinkedHashMap<String,String> attributes,LinkedList<Module> modules){
		this.modules=modules;
		this.attributes=attributes;
		this.nodeName=nodeName;
		
	}
	
	/**
	 * 
	 * @param nodeName 构造模块时此模块的节点名称
	 */
	
	public Module(String nodeName){
		this.nodeName=nodeName;
		modules=new LinkedList<>();
	}
	
	/**
	 * 
	 * @param nodeName 构造模块时此模块的节点名称 为null则没有
	 * @param module 向此模块中添加更多module
	 */
	
	@SuppressWarnings("serial")
	public Module(String nodeName,LinkedList<Module> modules){
		this.modules=new LinkedList<Module>(){
			{
				this.addAll(modules);
			}
		};
		this.nodeName=nodeName;
	}
	
	/**
	 * 不提供任何数据则节点不存在
	 */
	
	public Module(){
		this.nodeName=null;
		modules=new LinkedList<>();
	}
	
	/**
	 * 向模块中添加模块
	 * @param module
	 * @return
	 */

	public Module add(Module module){
		modules.add(module);
		return this;
	}
	
	/**
	 * 向模块中添加外部modulebuilder解释的模块
	 * @param modulebuilder
	 * @return
	 */
	
	public Module add(moduleBuilder modulebuilder){
		modules.add(modulebuilder.toModule());
		return this;
	}
	
	/**
	 * 向模块中添加多个模块
	 * @param modules
	 * @return
	 */
	
	public Module add(LinkedList<Module> modules){
		this.modules.addAll(modules);
		return this;
	}
	
	/**
	 * 加入其他单个的标签
	 * @return
	 */
	
	public Module addAttr(String name,String value){
		this.attributes.put("name", "value");
		return this;
	}
	
	/**
	 * 加入其他更多的标签
	 * @param attributes
	 * @return
	 */
	
	public Module addAttr(Map<String,String> attributes){
		this.attributes.putAll(attributes);
		return this;
	}
	
	/**
	 * 向此模块中添加代码
	 * @param innerCode 为空则不加代码
	 * @return
	 */
	
	public Module innerCode(String innerCode){
		if(this.innerCode==null)
			this.innerCode=innerCode==null?"":innerCode;
		else this.innerCode+=innerCode;
		return this;
	}
	
	/**
	 * 向此模块的子模块中添加代码
	 * @param innerCode
	 * @param nodeName
	 * @return
	 */
	
	
	public Module innerCode(String innerCode,String nodeName){
		return null;
	}
	
	/**
	 * 获取模块某属性的值
	 * @param attrName
	 * @return
	 */
	
	public String getValue(String attrName){
		return attributes.get(attrName)==null?"none":attributes.get(attrName);
	}
	
	/**
	 * 获取此模块的节点名称
	 * @return
	 */
	
	public String getNodeName(){
		return this.nodeName;
	}
	
	/**
	 * 获取模块html代码
	 * @return
	 */
	
	public String Code(){
		getCode();
		return code;
	}
	
	/**
	 * 
	 * @return 此module的所有标签
	 */
	
	public LinkedHashMap<String,String> attributes(){
		return attributes;
	}
	
	/**
	 * 
	 * @param nodeName 设置的node名称
	 * @return
	 */
	
	public Module setNodeName(String nodeName){
		this.nodeName=nodeName;
		return this;
	}
	
	/**
	 * 将此模块中名称为name的子模块弹出
	 * @param name 传入要弹出的东西
	 * @return 返回弹出的子模块
	 */
	
	public LinkedList<Module> pushOutModule(String name){
		LinkedList<Module> list=new LinkedList<>();
		Iterator<Module> it=this.modules.iterator();
		while(it.hasNext()){
			Module mo=it.next();
			if(name.equals(mo.getNodeName())){
				list.add(mo);
				it.remove();
			}
		}
		return list;
	}
	
	/**
	 * 向此模块中插入修改过的模块
	 * @param list 传入修改了的模块的链表
	 */
	
	public void pushInModule(LinkedList<Module> list){
		this.modules.addAll(list);
	}
	
	/**
	 * 返回此模块的子模块个数
	 * @return 子模块个数
	 */
	
	@SuppressWarnings("unused")
	private int getSize(){
		return modules.size();
	}
	
	private void getCode(){
		if(nodeName!=null){
			code+=this.codeSytle(codeStyle++)+"<"+this.nodeName;
			if(attributes!=null){
				for(String key:attributes.keySet()){
					code+=" "+key+"=\""+attributes.get(key)+"\"";
				}
			}
			code+=">\n";
		}
		for(Module module:modules){
			module.getCode();
		}
		if(nodeName!=null){
			code+=this.innerCode==null?"":this.codeSytle(codeStyle)
					+this.innerCode.replace("\n", "\n"+this.codeSytle(codeStyle))+"\n";
			code+=this.codeSytle(--codeStyle)+"</"+this.nodeName+">\n";
		}
	}
	
	private String codeSytle(int num){
		String topri="";
		for(int i=0;i<num;i++)
			topri+="	";
		return topri;
	}

	public static void main(String[] args){
		Module mo=new moduleBuilder("nav",moduleBuilder.HTML).toModule();
	//	LinkedList<Module> list=mo.pushOutModule("a");
	//	System.out.println(new Module(null,list).Code()+"\n\n\n");
		System.out.println(mo.Code());
	}

}
