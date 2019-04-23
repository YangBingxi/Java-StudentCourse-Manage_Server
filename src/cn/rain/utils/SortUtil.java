package cn.rain.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 对表单内容进行排序的工具类
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */
public class SortUtil {

	/**
	 * 对表单内容进行从低到高排序的类
	 * 
	 * @author SwYoung
	 * @version 2014-09-10
	 * @since JDK1.6
	 */
	public static class SortByUp implements Comparator<Map.Entry<String, Integer>> {
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			return o1.getValue().compareTo(o2.getValue());
		}
	}

	/**
	 * 对表单内容进行从高到低排序的类
	 * 
	 * @author SwYoung
	 * @version 2014-09-10
	 * @since JDK1.6
	 */
	public static class SortByDown implements Comparator<Map.Entry<String, Integer>> {
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			return -o1.getValue().compareTo(o2.getValue());
		}
	}
}