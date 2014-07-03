package com.tinycat.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

public class EscapedHtmlUtil {
	
	private static final String [] empty = new String [] {};

	/**
	 * 
	  * 转译目标类中所有String属性的html标签
	  *
	  * @autor: gwang  Mar 6, 2014 3:33:55 PM
	  * @param object 需要转译html标签的目标类
	  * @throws Exception    
	  * @return void
	 */
	public static void escape(Object object) throws Exception{
		escape(object, empty);
	}
	
	/**
	 * 
	  * 转义String
	  * @see StringEscapeUtils.escapeHtml()
	  * @autor: wn  2014-3-6 下午6:36:37
	  * @param object String对象
	  * @return String
	  * 
	 */
	public static String escapeStr(String object) {
		return StringEscapeUtils.escapeHtml(object);
	}
	
	/**
	 * 
	  * 转译目标类中指定(attributeNames)属性的html标签
	  *
	  * @autor: gwang  Mar 6, 2014 3:34:34 PM
	  * @param object 需要转译html标签的目标类
	  * @param attributeNames 目标类中指定属性
	  * @throws Exception    
	  * @return void
	 */
	public static void escape(Object object, String... attributeNames) throws Exception{
		Class<?> originClass = object.getClass();
		Field[] fields = originClass.getDeclaredFields();
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (Field field : fields){
			field.setAccessible(true);
			fieldMap.put(field.getName(), field);
		}
		if (attributeNames != null && attributeNames.length> 0){
			for (String attribute : attributeNames){
				resetAttribute(attribute, object, fieldMap);
			}
		}else {
			for (int i = 0; i < fields.length; i++) {
				resetAttribute(fields[i].getName(), object, fieldMap);
			}
		}
	}
	
	/**
	 * 
	  * 重新设置属性
	  *
	  * @autor: gwang  Mar 6, 2014 3:32:06 PM
	  * @param attribute 需要重设的属性名
	  * @param object 需要重设属性的目标类
	  * @param fieldMap 目标类的所有属Map性集合
	  * @throws Exception    
	  * @return void
	 */
	@SuppressWarnings("rawtypes")
	private static void resetAttribute(String attribute, Object object, Map<String, Field> fieldMap) throws Exception {
		Field field = fieldMap.get(attribute);
		if (String.class.equals(field.getType())){
			Object fStr = field.get(object);
			if(fStr!=null) {
				String value = StringEscapeUtils.escapeHtml(fStr.toString());
				field.set(object, value);
			}
		}
	}
    
    public static void main(String[] args) throws Exception {
    	System.out.println(StringEscapeUtils.unescapeHtml("<p>&lt;html&gt;<br />&lt;head&gt;<br /> &lt;meta charset=\"utf-8\" /&gt;<br /> &lt;title&gt;test html &lt;/title&gt;<br />&lt;/head&gt;<br />&lt;body&gt;<br /> <br />&lt;/body&gt;<br />&lt;/html&gt;</p>"));

	}
}
