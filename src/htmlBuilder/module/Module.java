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
	 * ����ģ��ʱ����ģ����߽ڵ����ƺ�������ֵ�Ĺ�ϣͼ
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
	 * @param nodeName ����ģ��ʱ��ģ��Ľڵ�����
	 */
	
	public Module(String nodeName){
		this.nodeName=nodeName;
		modules=new LinkedList<>();
	}
	
	/**
	 * 
	 * @param nodeName ����ģ��ʱ��ģ��Ľڵ����� Ϊnull��û��
	 * @param module ���ģ������Ӹ���module
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
	 * ���ṩ�κ�������ڵ㲻����
	 */
	
	public Module(){
		this.nodeName=null;
		modules=new LinkedList<>();
	}
	
	/**
	 * ��ģ�������ģ��
	 * @param module
	 * @return
	 */

	public Module add(Module module){
		modules.add(module);
		return this;
	}
	
	/**
	 * ��ģ��������ⲿmodulebuilder���͵�ģ��
	 * @param modulebuilder
	 * @return
	 */
	
	public Module add(moduleBuilder modulebuilder){
		modules.add(modulebuilder.toModule());
		return this;
	}
	
	/**
	 * ��ģ������Ӷ��ģ��
	 * @param modules
	 * @return
	 */
	
	public Module add(LinkedList<Module> modules){
		this.modules.addAll(modules);
		return this;
	}
	
	/**
	 * �������������ı�ǩ
	 * @return
	 */
	
	public Module addAttr(String name,String value){
		this.attributes.put("name", "value");
		return this;
	}
	
	/**
	 * ������������ı�ǩ
	 * @param attributes
	 * @return
	 */
	
	public Module addAttr(Map<String,String> attributes){
		this.attributes.putAll(attributes);
		return this;
	}
	
	/**
	 * ���ģ������Ӵ���
	 * @param innerCode Ϊ���򲻼Ӵ���
	 * @return
	 */
	
	public Module innerCode(String innerCode){
		if(this.innerCode==null)
			this.innerCode=innerCode==null?"":innerCode;
		else this.innerCode+=innerCode;
		return this;
	}
	
	/**
	 * ���ģ�����ģ������Ӵ���
	 * @param innerCode
	 * @param nodeName
	 * @return
	 */
	
	
	public Module innerCode(String innerCode,String nodeName){
		return null;
	}
	
	/**
	 * ��ȡģ��ĳ���Ե�ֵ
	 * @param attrName
	 * @return
	 */
	
	public String getValue(String attrName){
		return attributes.get(attrName)==null?"none":attributes.get(attrName);
	}
	
	/**
	 * ��ȡ��ģ��Ľڵ�����
	 * @return
	 */
	
	public String getNodeName(){
		return this.nodeName;
	}
	
	/**
	 * ��ȡģ��html����
	 * @return
	 */
	
	public String Code(){
		getCode();
		return code;
	}
	
	/**
	 * 
	 * @return ��module�����б�ǩ
	 */
	
	public LinkedHashMap<String,String> attributes(){
		return attributes;
	}
	
	/**
	 * 
	 * @param nodeName ���õ�node����
	 * @return
	 */
	
	public Module setNodeName(String nodeName){
		this.nodeName=nodeName;
		return this;
	}
	
	/**
	 * ����ģ��������Ϊname����ģ�鵯��
	 * @param name ����Ҫ�����Ķ���
	 * @return ���ص�������ģ��
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
	 * ���ģ���в����޸Ĺ���ģ��
	 * @param list �����޸��˵�ģ�������
	 */
	
	public void pushInModule(LinkedList<Module> list){
		this.modules.addAll(list);
	}
	
	/**
	 * ���ش�ģ�����ģ�����
	 * @return ��ģ�����
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
