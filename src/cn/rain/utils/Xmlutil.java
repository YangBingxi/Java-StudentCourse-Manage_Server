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
 * @author rain
 * @version 2014-09-10
 * @since JDK1.6
 */
public class Xmlutil {
	
	//��ȡ��ǰjar������·��
	static URL url = new Xmlutil().getClass().getProtectionDomain().getCodeSource().getLocation();
	static String realPath = url.getPath();
	static {
		int i = realPath.lastIndexOf("/");
		realPath = realPath.substring(0,i+1);
	}
	static File file = new File(realPath+"/DB.xml");
	
	/**
	 * ��ȡ���ݿ��ļ���Document����
	 * @author rain
	 * @return ���ݿ��ļ���Document����(Document)
	 */
	public static Document getDocument() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		return doc;
	}

	/**
	 * �������Document����д�����ݿ��ļ�
	 * @author rain
	 * @param ���ݿ��ļ���Document����(Document)
	 */
	public static void write2Xml(Document doc) throws IOException {

		OutputFormat format = new OutputFormat();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
		writer.write(doc);
	}
}
