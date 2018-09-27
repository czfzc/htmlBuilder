package htmlBuilder.builder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Tools {

	protected String replaceSelect(int num,String str,String regix,String replaceValue){
		String[] arr=str.split(regix);
		String a="";
		for(int i=0;i<=num;i++){
			a+=arr[i];
			if(i!=num)
				a+=regix;
		}
		a+=replaceValue;
		for(int i=num+1;i<arr.length;i++){
			a+=arr[i];
			if(i!=arr.length-1){
				a+=regix;
			}
		}
		return a;
	}
	
	protected static Document parse(String url) throws DocumentException{
		SAXReader reader=new SAXReader();
		return reader.read(url);
	}
	
}
