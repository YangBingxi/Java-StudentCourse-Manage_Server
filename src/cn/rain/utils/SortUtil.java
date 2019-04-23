package cn.rain.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * �Ա����ݽ�������Ĺ�����
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */
public class SortUtil {

	/**
	 * �Ա����ݽ��дӵ͵����������
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
	 * �Ա����ݽ��дӸߵ����������
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