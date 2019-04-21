package cn.rain.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/** 
* ��ȡ����Ա�û���������Ĺ�����
* @author  rain 
* @version 2014-09-10
* @since   JDK1.6
*/
public class PropUtil {
	private static String username;//�û���
	private static String password;//����

	static {
		//��ȡ��ǰjar������·��
		URL url = new PropUtil().getClass().getProtectionDomain().getCodeSource().getLocation();
		String realPath = url.getPath();
		int i = realPath.lastIndexOf("/");
		realPath = realPath.substring(0,i+1);
		
		//����������
		InputStream in = null;
		try {
			in = new FileInputStream(realPath+"admin.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//�������ļ��ж�ȡ����Ա�û���������
		Properties properties = new Properties();
		try {
			properties.load(in);
			username = properties.getProperty("username");
			password = properties.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author rain
	 * @return �û���(String)
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @author rain
	 * @return ����(String)
	 */
	public static String getPassword() {
		return password;
	}

}
