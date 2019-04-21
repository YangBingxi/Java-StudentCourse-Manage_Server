package cn.rain.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/** 
* 读取管理员用户名和密码的工具类
* @author  rain 
* @version 2014-09-10
* @since   JDK1.6
*/
public class PropUtil {
	private static String username;//用户名
	private static String password;//密码

	static {
		//获取当前jar包所在路径
		URL url = new PropUtil().getClass().getProtectionDomain().getCodeSource().getLocation();
		String realPath = url.getPath();
		int i = realPath.lastIndexOf("/");
		realPath = realPath.substring(0,i+1);
		
		//建立输入流
		InputStream in = null;
		try {
			in = new FileInputStream(realPath+"admin.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//从配置文件中读取管理员用户名和密码
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
	 * @return 用户名(String)
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @author rain
	 * @return 密码(String)
	 */
	public static String getPassword() {
		return password;
	}

}
