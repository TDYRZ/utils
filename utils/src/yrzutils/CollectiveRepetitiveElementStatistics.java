package yrzutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 集合重复元素统计工具类
 * @version:
 * @Description:
 * @author:yrz
 * @date: date{time}
 */
public class CollectiveRepetitiveElementStatistics {
	/**
	 * 公共调用方法，传入要统计集合
	 * @Description:
	 * @author:yrz
	 * @data :2019年4月28日上午10:07:22
	 * @version: 
	 * @param strs
	 */
	public static void getValueNumlist(List<String> strs) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Set<String> set = new HashSet<String>(strs);
		for (String str : set) {
			for (String lstr : strs) {
				if (str.equals(lstr)) {
					if (map.containsKey(str)) {
						Integer count = map.get(str);
						count++;
						map.put(str, count);
					} else {
						map.put(str, 1);
					}

				}
			}
		}
		// printMap(map);
		Map<String, Integer> sortMap = sortMapByValue(map);
		printMap(sortMap);

	}

	/**
	 * 按值排序集合
	 * @Description:
	 * @author:yrz
	 * @data :2019年4月28日上午10:08:02
	 * @version: 
	 * @param map
	 * @return
	 */
	private static Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
		List<Map.Entry<String, Integer>> mapList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue() - o2.getValue();
			}
		});
		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : mapList) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * 输出排序集合
	 * @Description:
	 * @author:yrz
	 * @data :2019年4月28日上午10:08:13
	 * @version: 
	 * @param map
	 */
	public static void printMap(Map<String, Integer> map) {

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "          " + entry.getValue());
		}

	}

	/**
	 * 按key排序集合
	 * @Description:
	 * @author:yrz
	 * @data :2019年4月28日上午10:08:34
	 * @version: 
	 * @param oriMap
	 * @return
	 */
	public Map<String, String> sortMapByKey(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				int intKey1 = 0, intKey2 = 0;
				try {
					intKey1 = getInt(key1);
					intKey2 = getInt(key2);
				} catch (Exception e) {
					intKey1 = 0;
					intKey2 = 0;
				}
				return intKey1 - intKey2;
			}
		});
		sortedMap.putAll(oriMap);
		return sortedMap;
	}

	/**
	 * 获取int类型key
	 * @Description:
	 * @author:yrz
	 * @data :2019年4月28日上午10:09:16
	 * @version: 
	 * @param str
	 * @return
	 */
	private int getInt(String str) {
		int i = 0;
		try {
			Pattern p = Pattern.compile("^\\d+");
			Matcher m = p.matcher(str);
			if (m.find()) {
				i = Integer.valueOf(m.group());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return i;
	}
}
