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
 * 调用前需要加载JDBC驱动
 */
public class mysqlTest {
	public static void mysqlWriteXml() {
		User user = null;
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "2020");
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");

			// user 为你表的名称
			while (rs.next()) { // 打印表中对应的内容
				System.out.println(rs.getString("username") + "\t" + rs.getString("password") + "\t"
						+ rs.getString("birthday") + "\t" + rs.getString("sno") + "\t" + rs.getString("name") + "\t"
						+ rs.getString("gender") + "\t" + rs.getString("schoolClass"));

				try {
					Document doc = Xmlutil.getDocument_2();
					Element rootNode = doc.getRootElement();
					@SuppressWarnings("unused")
					Student student = (Student) user;
					Element StudentsNode = rootNode.addElement("student");
					// 添加学生节点
					StudentsNode.addAttribute("username", rs.getString("username"));
					StudentsNode.addAttribute("password", rs.getString("password"));
					StudentsNode.addAttribute("birthday", rs.getString("birthday"));
					StudentsNode.addAttribute("sno", rs.getString("sno"));
					StudentsNode.addAttribute("name", rs.getString("name"));
					StudentsNode.addAttribute("gender", rs.getString("gender"));
					StudentsNode.addAttribute("schoolClass", rs.getString("schoolClass"));
					// Element scoresNode = StudentsNode.addElement("scores");

					Xmlutil.write2Xml_2(doc);// 写入文件
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("文件写入错误!");
				}

			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	public static void readFromMysql() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "2020");
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码

			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
//			ResultSet rsCourse1 = stmt.executeQuery("select * from course_term1,course_term2,course_term3,course_term4,course_term5 where"
//					+ "course_term1.sno=\"B16012321\" and course_term2.sno=\"B16012321\" and course_term3.sno=\"B16012321\" and course_term4.sno=\"B16012321\" and course_term5.sno=\"B16012321\"");
			// user 为你表的名称
			while (rs.next()) { // 打印表中对应的内容
				System.out.println(rs.getString("username") + "\t" + rs.getString("password") + "\t"
						+ rs.getString("birthday") + "\t" + rs.getString("sno") + "\t" + rs.getString("name") + "\t"
						+ rs.getString("gender") + "\t" + rs.getString("schoolClass"));
				// System.out.println(rsCourse1.getString("高数上"));
			}
			ResultSet rsCourse1 = stmt.executeQuery("select * from course_term1,course_term2 where"
					+ " course_term1.sno=\"B16012321\" and course_term2.sno=\"B16012321\"");
			while (rsCourse1.next()) {
				System.out.println(rsCourse1.getString("高等数学上") + "\t" + rsCourse1.getString("电路分析"));
			}
		} catch (Exception e) {
			System.out.print("get data error!");
			e.printStackTrace();
		}
	}

	public static void writeToMysql() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
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
			for (int i = 0; i < num; i++) // 定义个100次的循环，往表里插入一百条信息。
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