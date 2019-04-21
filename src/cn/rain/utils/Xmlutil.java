package cn.rain.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 对数据库文件进行读写操作的工具类
 * @author rain
 * @version 2014-09-10
 * @since JDK1.6
 */
public class Xmlutil {
	
	//获取当前jar包所在路径
	static URL url = new Xmlutil().getClass().getProtectionDomain().getCodeSource().getLocation();
	static String realPath = url.getPath();
	static {
		int i = realPath.lastIndexOf("/");
		realPath = realPath.substring(0,i+1);
	}
	static File file = new File(realPath+"/DB.xml");
	
	/**
	 * 获取数据库文件的Document对象
	 * @author rain
	 * @return 数据库文件的Document对象(Document)
	 */
	public static Document getDocument() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		return doc;
	}

	/**
	 * 将传入的Document对象写入数据库文件
	 * @author rain
	 * @param 数据库文件的Document对象(Document)
	 */
	public static void write2Xml(Document doc) throws IOException {

		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
		writer.write(doc);
	}
}
