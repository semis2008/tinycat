package com.tinycat.util;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * String对象操作的辅助类
 * 
 * @author kalor
 * @time 2012-12-17
 */
public class StringUtil {
	/**
	 * 对用户密码进行加密
	 * 
	 * @param password
	 * @return
	 */
	public static String passEncrypt(String password) {
		String temp = EncryptUtil.Encrypt(password, null);
		password = temp + "a86aff6cf0a1";
		return EncryptUtil.Encrypt(password, null);
	}

	public static void main(String args[]) {
		String p = passEncrypt("wangning");
		System.out.print(p);
		
	}
	
/**
	 * 
	  * 过滤Str中全部的html标签及内容,
	  *
	  * @autor: wn  2014-4-14 上午11:50:05
	  * @param srcStr
	  * @return    
	  * @return String 会对Str中转码的html信息反转：&lt;--> '<'
	 */
	public static String escapeHtmlTags(String srcStr) {
		String tarStr = "";
		Pattern p = Pattern.compile("<[^>]+>");
		Matcher m = p.matcher(srcStr);
		tarStr = StringEscapeUtils.unescapeHtml(m.replaceAll(""));
		return tarStr;
	}

	public static boolean isNull(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	/**
	 * 判断邮箱是否合法
	 * 
	 * @date 2012-5-15 pjian 与前端判断条件保持一�?
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (StringUtil.isNull(email)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^([\\w]+)(.[\\w]+)*@([\\w-]+\\.){1,5}([A-Za-z]){2,4}$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断QQ是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isQQ(String QQ) {
		if (StringUtil.isNull(QQ)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[1-9][0-9]{4,19}");
		Matcher matcher = pattern.matcher(QQ);
		if (matcher.matches()) {
			return true;
		} else
			return false;
	}

	/**
	 * 判断身份证是否合�?
	 * 
	 * @param IDNo
	 * @return
	 */
	public static boolean isIDNo(String IDNo) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]{1,30}");
		Matcher matcher = pattern.matcher(IDNo);
		if (matcher.matches()) {
			return true;
		} else
			return false;
	}

	/**
	 * 判断昵称是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isRealName(String realName) {
		if (realName.length() > 20)
			return false;
		Pattern pattern = Pattern.compile("([[a-zA-Z0-9\u4E00-\u9FA5]{1}][a-zA-Z0-9_\\-\u4E00-\u9FA5]{0,19})|(\\-{1}[a-zA-Z0-9_\\-\u4E00-\u9FA5]*[a-zA-Z0-9\u4E00-\u9FA5]+[a-zA-Z0-9_\\-\u4E00-\u9FA5]*)");
		Matcher matcher = pattern.matcher(realName);
		if (matcher.matches()) {
			return true;
		} else
			return false;
	}

	/**
	 * 判断地址是否合法
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isAddress(String address) {
		Pattern pattern = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#�?…�?&*（）—�?+|{}【�?‘；：�?“�?。，、？]");
		Matcher matcher = pattern.matcher(address);
		return !matcher.find();
	}

	/**
	 * 判断邮编是否合法
	 * 
	 * @param postcode
	 * @return
	 */
	public static boolean isPostcode(String postcode) {
		Pattern pattern = Pattern.compile("[0-9]{6}");
		Matcher matcher = pattern.matcher(postcode);
		if (matcher.matches()) {
			return true;
		} else
			return false;
	}

	public static boolean isNumeric(String str) {
		if (isNull(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断电话号码是否合法
	 * 
	 * @param postcode
	 * @return
	 */
	public static boolean isPhoneNumber(String phonenumber) {
		if (StringUtil.isNull(phonenumber)) {
			return false;
		}
		// TODO下面的那个pattern有问题，无法识别不带区号的8位电话号码
		if (phonenumber.length() == 8 && isNumeric(phonenumber)) {
			return true;
		}
		Pattern pattern = Pattern.compile("((^(13[0-9]|15[0-3]|15[5-9]|18[0-9]|147)[0-9]{8}$)|(^0[1,2]{1}\\d{1}(-|_)?\\d{8}$)|(^0[3-9]{1}\\d{2}(-|_)?\\d{7,8}$)|(^0[1,2]{1}\\d{1}(-|_)?\\d{8}(-|_)(\\d{1,4})$)|(^0[3-9]{1}\\d{2}(-|_)?\\d{7,8}(-|_)(\\d{1,4})$))");
		Matcher matcher = pattern.matcher(phonenumber);
		if (matcher.matches()) {
			return true;
		} else
			return false;
	}

	/**
	 * 去掉list中重复元素
	 * 
	 * @autor: wn 2014-5-29 下午3:46:13
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}
}
