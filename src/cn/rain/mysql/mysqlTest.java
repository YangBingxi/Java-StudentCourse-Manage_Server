package cn.rain.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.rain.domain.Student;
import cn.rain.domain.User;
import cn.rain.utils.Xmlutil;

/* 
 * ����ǰ��Ҫ����JDBC����
 */
public class mysqlTest {
	public static void mysqlWriteXml() {
		User user = null;
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "2020");
			// ����URLΪ jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û���������

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");

			// user Ϊ��������
			while (rs.next()) { // ��ӡ���ж�Ӧ������
				System.out.println(rs.getString("username") + "\t" + rs.getString("password") + "\t"
						+ rs.getString("birthday") + "\t" + rs.getString("sno") + "\t" + rs.getString("name") + "\t"
						+ rs.getString("gender") + "\t" + rs.getString("schoolClass"));

				try {
					Document doc = Xmlutil.getDocument_2();
					Element rootNode = doc.getRootElement();
					@SuppressWarnings("unused")
					Student student = (Student) user;
					Element StudentsNode = rootNode.addElement("student");
					// ���ѧ���ڵ�
					StudentsNode.addAttribute("username", rs.getString("username"));
					StudentsNode.addAttribute("password", rs.getString("password"));
					StudentsNode.addAttribute("birthday", rs.getString("birthday"));
					StudentsNode.addAttribute("sno", rs.getString("sno"));
					StudentsNode.addAttribute("name", rs.getString("name"));
					StudentsNode.addAttribute("gender", rs.getString("gender"));
					StudentsNode.addAttribute("schoolClass", rs.getString("schoolClass"));
					// Element scoresNode = StudentsNode.addElement("scores");

					Xmlutil.write2Xml_2(doc);// д���ļ�
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("�ļ�д�����!");
				}

			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	public static void readFromMysql() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // ����MYSQL JDBC��������
			// Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "2020");
			// ����URLΪ jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û���������

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
//			ResultSet rsCourse1 = stmt.executeQuery("select * from course_term1,course_term2,course_term3,course_term4,course_term5 where"
//					+ "course_term1.sno=\"B16012321\" and course_term2.sno=\"B16012321\" and course_term3.sno=\"B16012321\" and course_term4.sno=\"B16012321\" and course_term5.sno=\"B16012321\"");
			// user Ϊ��������
			while (rs.next()) { // ��ӡ���ж�Ӧ������
				System.out.println(rs.getString("username") + "\t" + rs.getString("password") + "\t"
						+ rs.getString("birthday") + "\t" + rs.getString("sno") + "\t" + rs.getString("name") + "\t"
						+ rs.getString("gender") + "\t" + rs.getString("schoolClass"));
				// System.out.println(rsCourse1.getString("������"));
			}
			ResultSet rsCourse1 = stmt.executeQuery("select * from course_term1,course_term2 where"
					+ " course_term1.sno=\"B16012321\" and course_term2.sno=\"B16012321\"");
			while (rsCourse1.next()) {
				System.out.println(rsCourse1.getString("�ߵ���ѧ��") + "\t" + rsCourse1.getString("��·����"));
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	public static void writeToMysql() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // ����MYSQL JDBC��������
			// Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "202052");

			int num = 100;
			PreparedStatement Statement = connect.prepareStatement("INSERT INTO user VALUES(?,?)");
			for (int i = 0; i < num; i++) // �����100�ε�ѭ�������������һ������Ϣ��
			{
				Statement.setString(1, "chongshi" + i);
				Statement.setString(2, "bo" + i);
				Statement.executeUpdate();
			}

			// } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// System.out.println("An error has occurred:"+e.toString());
			// e.printStackTrace();
		} catch (SQLException e) {
		}
	}

	public static void main(String args[]) {
		readFromMysql();
		// writeToMysql();
	}

}