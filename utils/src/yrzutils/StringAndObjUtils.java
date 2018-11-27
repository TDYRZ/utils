package yrzutils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 对象字符串判断工具类
 * 
 * @author yrz
 *
 */
public class StringAndObjUtils {

	/**
	 * 判断当前对象是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	/**
	 * 判断字符串是否有效
	 * 
	 * @param Str
	 * @return
	 * @author yrz
	 */
	public static boolean isNullOrEmpty(String Str) {
		if (Str != null && Str.trim().length() > 0)
			return false;
		else
			return true;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param arr
	 * @return
	 * @author yrz
	 * 
	 */
	public static boolean isNullOrEmpty(Object[] arr) {
		if (arr != null && arr.length > 0)
			return false;
		else
			return true;
	}
	
	/**
	 * 判断集合是否为空
	 * @param list
	 * @return
	 * @author yrz
	 */
	public static boolean isNullOrEmpty(List list) {
		if (list != null && !list.isEmpty())
			return false;
		else
			return true;
	}
	
	/**
	 * 判断数组中是否包含某个字符串
	 * 
	 * @param array
	 * @param Str
	 * @param CaseSensitive
	 * @return
	 * @author yrz
	 */
	public static boolean isArrayContainString(String[] array, String Str, Boolean CaseSensitive) {
		if (array == null || array.length == 0 || isNullOrEmpty(Str))
			return false;
		boolean flag = false;
		if (CaseSensitive) {
			// 区分大小写
			for (String tmp : array) {
				if (tmp.equals(Str)) {
					flag = true;
					break;
				}
			}
		} else {
			// 不区分大小写
			for (String tmp : array) {
				if (tmp.trim().equalsIgnoreCase(Str.trim())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 把字符串转换成数组
	 * 
	 * @param Str
	 * @param splitStr
	 * @return
	 * @author yrz
	 */
	public static String[] StringToArray(String Str, String splitStr) {
		if (isNullOrEmpty(Str))
			return null;
		else
			return Str.trim().split(splitStr);
	}
	
	/**
	 * 将tomcat默认的ISO88591编码转为utf8(get)传参时用,不用再使用url编码
	 * 
	 * @param str
	 * @return
	 * @author yrz
	 */
	public static String changeCode(String str) {
		try {
			return new String(str.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	
	/**
	 * 把传入的StringBuffer长度减n
	 * 
	 * @param n
	 * @param sbs
	 * @author yrz
	 */
	public static void StringBuffersLengthReduce(int n, StringBuffer... sbs) {
		if (sbs != null && sbs.length >= n) {
			for (StringBuffer sb : sbs) {
				if (sb.length() > 0)
					sb.setLength(sb.length() - n);
			}
		}
	}
	
	/**
	 * 获取数组长度
	 * 
	 * @param arr
	 * @return
	 * @author yrz
	 */
	public static int getArrayLength(Object[] arr) {
		if (isNullOrEmpty(arr))
			return 0;
		else
			return arr.length;
	}
	
	/**
	 * 获取非空
	 * 
	 * @param arr
	 * @return
	 * @author yrz
	 */
	public static int getStringArrayAvailableLength(String[] arr) {
		if (isNullOrEmpty(arr))
			return 0;
		else {
			int n = 0;
			for (int i = 0; i < arr.length; i++) {
				if (!isNullOrEmpty(arr[i]))
					n++;
			}
			return n;
		}
	}
	
	/**
	 * 检查所有数组的某一个元素是否都为空
	 * 
	 * @param index
	 * @param arrs
	 * @return
	 * @author yrz
	 */
	public static boolean isAllNullOrEmpty(int index, String[]... arrs) {
		int maxlength = 0;
		boolean allArraysIsNull = true;
		for (String[] arr : arrs) {
			if (!isNullOrEmpty(arr)) {
				allArraysIsNull = false;
				maxlength = arr.length > maxlength ? arr.length : maxlength;
			}
		}
		if (allArraysIsNull)
			return true;

		for (String[] arr1 : arrs) {
			if (arr1 != null && arr1.length > index && !isNullOrEmpty(arr1[index]))
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串是否能转换成整型
	 * @param str
	 * @return
	 * @author yrz
	 */
	public static boolean canConvertToInt(String str){
		if(str==null || str.trim().length()==0)
			return false;
		try {
			new Integer(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
