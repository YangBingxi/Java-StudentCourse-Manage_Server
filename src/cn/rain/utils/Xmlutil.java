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
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */
public class Xmlutil {

	// 获取当前jar包所在路径
	static URL url = new Xmlutil().getClass().getProtectionDomain().getCodeSource().getLocation();
	static String realPath = url.getPath();
	static {
		int i = realPath.lastIndexOf("/");
		realPath = realPath.substring(0, i + 1);
	}
	static File file = new File(realPath + "/DB.xml");
	static File file2 = new File(realPath + "/DB2.xml");

	/**
	 * 获取数据库文件的Document对象
	 * 
	 * @author SwYoung
	 * @return 数据库文件的Document对象(Document)
	 */
	public static Document getDocument() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		return doc;
	}

	/**
	 * 获取数据库文件的Document对象
	 * 
	 * @author SwYoung
	 * @return 数据库文件的Document对象(Document)
	 */
	public static Document getDocument_2() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file2);
		return doc;
	}

	/**
	 * 将传入的Document对象写入数据库文件
	 * 
	 * @author SwYoung
	 * @param 数据库文件的Document对象(Document)
	 */
	public static void write2Xml(Document doc) throws IOException {

		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
		writer.write(doc);
	}

	/**
	 * 将传入的Document对象写入数据库文件
	 * 
	 * @author SwYoung
	 * @param 数据库文件的Document对象(Document)
	 */
	public static void write2Xml_2(Document doc) throws IOException {

		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(file2), format);
		writer.write(doc);
	}
}
