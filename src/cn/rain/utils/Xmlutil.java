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
 * �����ݿ��ļ����ж�д�����Ĺ�����
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */
public class Xmlutil {

	// ��ȡ��ǰjar������·��
	static URL url = new Xmlutil().getClass().getProtectionDomain().getCodeSource().getLocation();
	static String realPath = url.getPath();
	static {
		int i = realPath.lastIndexOf("/");
		realPath = realPath.substring(0, i + 1);
	}
	static File file = new File(realPath + "/DB.xml");
	static File file2 = new File(realPath + "/DB2.xml");

	/**
	 * ��ȡ���ݿ��ļ���Document����
	 * 
	 * @author SwYoung
	 * @return ���ݿ��ļ���Document����(Document)
	 */
	public static Document getDocument() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		return doc;
	}

	/**
	 * ��ȡ���ݿ��ļ���Document����
	 * 
	 * @author SwYoung
	 * @return ���ݿ��ļ���Document����(Document)
	 */
	public static Document getDocument_2() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file2);
		return doc;
	}

	/**
	 * �������Document����д�����ݿ��ļ�
	 * 
	 * @author SwYoung
	 * @param ���ݿ��ļ���Document����(Document)
	 */
	public static void write2Xml(Document doc) throws IOException {

		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
		writer.write(doc);
	}

	/**
	 * �������Document����д�����ݿ��ļ�
	 * 
	 * @author SwYoung
	 * @param ���ݿ��ļ���Document����(Document)
	 */
	public static void write2Xml_2(Document doc) throws IOException {

		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(file2), format);
		writer.write(doc);
	}
}
