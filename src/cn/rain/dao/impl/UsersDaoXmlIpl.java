package cn.rain.dao.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import cn.rain.Exceptions.UserExistedException;
import cn.rain.Exceptions.UserNotFoundException;
import cn.rain.dao.UserDao;
import cn.rain.domain.Student;
import cn.rain.domain.Teacher;
import cn.rain.domain.User;
import cn.rain.utils.PropUtil;
import cn.rain.utils.SortUtil;
import cn.rain.utils.Xmlutil;

/**
 * 数据交互接口实现类
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */
public class UsersDaoXmlIpl extends UnicastRemoteObject implements UserDao {

	private static final long serialVersionUID = -5454546011882426268L;

	public Map<String, String> getAdmin() {

		String username = PropUtil.getUsername();// 获取管理员用户名
		String password = PropUtil.getPassword();// 获取管理员密码

		Map<String, String> admin = new LinkedHashMap<String, String>();// MAP初始化
		admin.put("username", username);// 插入元素
		admin.put("password", password);// 插入元素
		return admin;

	}

	public UsersDaoXmlIpl() throws RemoteException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#find(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User find(String username, String password, String permission) throws RemoteException {
		// 传入用户名、密码、用户权限
		try {
			Document doc = Xmlutil.getDocument();
			// 获取Document对象
			if (permission.equals("学生")) {
				// 用户角色为学生
				Element StudentNode = (Element) doc
						.selectSingleNode("//student[@username='" + username + "' and @password = '" + password + "']");
				// 得到对应学生节点
				if (StudentNode == null) {
					return null;
					// 如果不存在对应学生则返回空
				}
				Student student = new Student();
				student.setUsername(StudentNode.attributeValue("username"));
				student.setPassword(StudentNode.attributeValue("password"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				Date birthday = dateFormat.parse(StudentNode.attributeValue("birthday"));
				student.setBirthday(birthday);
				student.setGender(StudentNode.attributeValue("gender"));
				student.setName(StudentNode.attributeValue("name"));
				student.setSno(StudentNode.attributeValue("sno"));
				// 将该学生节点的学生信息存入学生对象
				return student;
			} else {
				// 用户角色为教师
				Element teacherNode = (Element) doc
						.selectSingleNode("//teacher[@username='" + username + "' and @password = '" + password + "']");
				// 得到对应学生教师
				if (teacherNode == null) {
					return null;
					// 如果不存在对应教师则返回空
				}
				Teacher teacher = new Teacher();
				teacher.setName(teacherNode.attributeValue("name"));
				teacher.setUsername(teacherNode.attributeValue("username"));
				teacher.setPassword(teacherNode.attributeValue("password"));
				// 将该教师节点的信息存入教师对象
				return teacher;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#findStudentBySno(java.lang.String)
	 */
	@Override
	public User findStudentBySno(String sno) throws RemoteException {
		// 传入学号
		try {
			Document doc = Xmlutil.getDocument();
			// 获取Document对象
			Element StudentNode = (Element) doc.selectSingleNode("//student[@sno='" + sno + "']");
			// 获取对应学生节点
			if (StudentNode == null) {
				return null;
				// 如果不存在对应学生节点则返回null
			}
			User student = new Student();
			student.setUsername(StudentNode.attributeValue("username"));
			student.setPassword(StudentNode.attributeValue("password"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date birthday = dateFormat.parse(StudentNode.attributeValue("birthday"));
			student.setBirthday(birthday);
			student.setGender(StudentNode.attributeValue("gender"));
			student.setName(StudentNode.attributeValue("name"));
			student.setSno(StudentNode.attributeValue("sno"));
			Element scoresNode = StudentNode.element("scores");
			Map<String, Integer> scores = new LinkedHashMap<String, Integer>();
			@SuppressWarnings("unchecked")
			Iterator<Attribute> iterator = scoresNode.attributeIterator();
			while (iterator.hasNext()) {
				Attribute attr = (Attribute) iterator.next();
				scores.put(attr.getName(), Integer.parseInt(attr.getValue()));
			}
			// 将该学生节点的信息存入学生对象
			return student;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#findStudentByName(java.lang.String)
	 */
	@Override
	public User findStudentByName(String name) throws RemoteException {
		// 传入姓名
		try {
			Document doc = Xmlutil.getDocument();
			// 获取Document对象
			Element StudentNode = (Element) doc.selectSingleNode("//student[@name='" + name + "']");
			// 获取对应学生节点
			if (StudentNode == null) {
				return null;
				// 如果不存在对应学生节点则返回null
			}
			User student = new Student();
			student.setUsername(StudentNode.attributeValue("username"));
			student.setPassword(StudentNode.attributeValue("password"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date birthday = dateFormat.parse(StudentNode.attributeValue("birthday"));
			student.setBirthday(birthday);
			student.setGender(StudentNode.attributeValue("gender"));
			student.setName(StudentNode.attributeValue("name"));
			student.setSno(StudentNode.attributeValue("sno"));
			Element scoresNode = StudentNode.element("scores");
			Map<String, Integer> scores = new LinkedHashMap<String, Integer>();
			@SuppressWarnings("unchecked")
			Iterator<Attribute> iterator = scoresNode.attributeIterator();
			while (iterator.hasNext()) {
				Attribute attr = (Attribute) iterator.next();
				scores.put(attr.getName(), Integer.parseInt(attr.getValue()));
			}
			// 将该学生节点的信息存入学生对象
			return student;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#findTeacher(java.lang.String)
	 */
	@Override
	public Teacher findTeacher(String username) throws RemoteException {
		// 传入用户名
		try {
			Document doc = Xmlutil.getDocument();
			// 夺取Document对象
			Element teacherNode = (Element) doc.selectSingleNode("//teacher[@username='" + username + "']");
			// 获取对应教师节点
			if (teacherNode == null) {
				return null;
				// 如果不存在对应教师节点则返回null
			}
			Teacher teacher = new Teacher();
			teacher.setName(teacherNode.attributeValue("name"));
			teacher.setUsername(teacherNode.attributeValue("username"));
			teacher.setPassword(teacherNode.attributeValue("password"));
			// 将对应教师信息存入教师对象
			return teacher;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#addUser(cn.rain.domain.User)
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void addUser(Map<String, Object> map) throws UserExistedException, RemoteException {
		// 传入保存用户信息的nap
		User user = null;
		try {
			if (((String) map.get("permission")).equals("学生")) {
				// 学生用户
				user = new Student();
				for (int i = 0; i < map.size(); i++) {
					user.setUsername((String) map.get("username"));
					user.setPassword((String) map.get("password"));
					user.setName((String) map.get("name"));
					user.setGender((String) map.get("gender"));
					user.setSchoolClass((String) map.get("schoolClass"));
					user.setBirthday((Date) map.get("birthday"));
					user.setSno((String) map.get("sno"));
					user.setPermission((String) map.get("permission"));
					// 将学生信息存到学生对象
				}

				if (findStudentBySno(user.getSno()) != null) {
					// 如果已存在对应学生节点，则抛出异常UserExistedException
					throw new UserExistedException();
				} else {

					try {
						Document doc = Xmlutil.getDocument();
						Element rootNode = doc.getRootElement();
						Student student = (Student) user;
						Element StudentsNode = rootNode.addElement("student");
						// 添加学生节点
						StudentsNode.addAttribute("username", student.getUsername());
						StudentsNode.addAttribute("password", student.getPassword());
						StudentsNode.addAttribute("birthday", student.getBirthday().toLocaleString());
						StudentsNode.addAttribute("sno", student.getSno());
						StudentsNode.addAttribute("name", student.getName());
						StudentsNode.addAttribute("gender", student.getGender());
						StudentsNode.addAttribute("schoolClass", student.getSchoolClass());
						Element scoresNode = StudentsNode.addElement("scores");
						Element yearNode;
						for (int i = 1; i < 5; i++) {
							yearNode = scoresNode.addElement("year" + i);
							yearNode.addElement("term" + (2 * i - 1));
							yearNode.addElement("term" + (2 * i));
						}
						// 将学生信息写入学生节点
						Xmlutil.write2Xml(doc);
						// 写入Document对象到xml数据文件
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}

			} else {
				// 教师用户
				if (findTeacher((String) map.get("username")) != null) {
					throw new UserExistedException();
					// 如果已存在对应教师节点，则抛出异常UserExistedException
				} else {

					try {
						Document doc = Xmlutil.getDocument();
						Element rootNode = doc.getRootElement();
						user = new Teacher();
						for (int i = 0; i < map.size(); i++) {
							user.setUsername((String) map.get("username"));
							user.setPassword((String) map.get("password"));
							user.setName((String) map.get("name"));
							user.setGender((String) map.get("gender"));
							user.setPermission((String) map.get("permission"));
						}
						// 将教师信息存入教师对象
						Element teachersNode = rootNode.addElement("teacher");
						teachersNode.addAttribute("username", user.getUsername());
						teachersNode.addAttribute("password", user.getPassword());
						teachersNode.addAttribute("name", user.getName());
						// 将教师信息写入教师节点
						Xmlutil.write2Xml(doc);
						// 写出Document对象到xml数据文件
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#addScores(java.lang.String, java.util.Map, int)
	 */
	@Override
	public boolean addScores(String sno, Map<String, Integer> scores, int term)
			throws UserNotFoundException, RemoteException {
		// 传入学号、分数，学期
		if (findStudentBySno(sno) == null) {
			throw new UserNotFoundException();
			// 如果没有对应学号的学生，抛出异常UserNotFoundException
		} else {
			Document doc;
			try {
				doc = Xmlutil.getDocument();
				// 获取Document对象
				@SuppressWarnings("unchecked")
				List<Element> students = doc.selectNodes("//student");
				// 获取所有学生节点
				for (int i = 0; i < students.size(); i++) {
					Element student = students.get(i);
					String sno1 = student.attribute("sno").getValue();
					if (sno1 != null && sno1.equals(sno)) {
						Element scoresNode = student.element("scores");
						// 迭代学生节点，找到对应学号的学生节点
						Element yearNode = scoresNode.element(("year" + (term + 1) / 2));
						Element termNode = yearNode.element("term" + term);
						// 获取对应学期节点
						Iterator<Entry<String, Integer>> iterator = scores.entrySet().iterator();
						while (iterator.hasNext()) {
							Entry<String, Integer> entry = iterator.next();
							termNode.addAttribute(entry.getKey(), entry.getValue() + "");
							// 在对应学期节点添加成绩
						}

						Xmlutil.write2Xml(doc);
						// 写入Document对象到数据文件
						return true;
					}

				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#findScores(java.lang.String, java.lang.String, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Integer> findScores(String sno, String method, int num)
			throws UserNotFoundException, RemoteException {
		// 传入学号、查询方法（学期或学年）、学期或学年数
		if (findStudentBySno(sno) == null) {
			throw new UserNotFoundException();
			// 如果用户已存在则抛出自定义异常 UserNotFoundException
		} else {

			Map<String, Integer> scores = new LinkedHashMap<String, Integer>();
			// 用来装用户学号和分数的map
			if ("term".equals(method)) {
				// 按学期查询
				try {
					Document doc = Xmlutil.getDocument();// 获取Document对象
					List<Element> students = doc.selectNodes("//student");// 获取所有student节点
					for (int i = 0; i < students.size(); i++) {
						Element student = students.get(i);
						String sno1 = student.attribute("sno").getValue();
						if (sno1 != null && sno1.equals(sno)) {
							// 得到对应学号的学生节点
							Element scoresNode = student.element("scores");
							Element yearNode = scoresNode.element(("year" + (num + 1) / 2));
							Element termNode = yearNode.element("term" + num);
							Iterator<Attribute> iterator = termNode.attributeIterator();
							while (iterator.hasNext()) {
								Attribute attr = (Attribute) iterator.next();
								scores.put(attr.getName(), Integer.parseInt(attr.getValue()));
							} // 迭代所有学期节点
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return scores;// 返回结果

			} else if ("year".equals(method)) {
				// 按学年查询
				try {
					Document doc = Xmlutil.getDocument();
					List<Element> students = doc.selectNodes("//student");
					// 获取所有学生节点
					for (int i = 0; i < students.size(); i++) {
						Element student = students.get(i);
						String sno1 = student.attribute("sno").getValue();
						if (sno1 != null && sno1.equals(sno)) {
							// 获取对应学号的学生节点
							Element scoresNode = student.element("scores");
							Element yearNode = scoresNode.element("year" + num);
							Element termNode = yearNode.element("term" + (2 * num - 1));
							Iterator<Attribute> iterator = termNode.attributeIterator();
							while (iterator.hasNext()) {
								Attribute attr = (Attribute) iterator.next();
								scores.put(attr.getName(), Integer.parseInt(attr.getValue()));
							}
							termNode = yearNode.element("term" + 2 * num);
							iterator = termNode.attributeIterator();
							while (iterator.hasNext()) {
								Attribute attr = (Attribute) iterator.next();
								scores.put(attr.getName(), Integer.parseInt(attr.getValue()));
							}
							// 迭代获取所选学年的成绩
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return scores;
			}
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#findScore(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Integer> findScore(String sno, String subject) throws UserNotFoundException, RemoteException {
		// 传入学号、课程名称
		Map<String, Integer> scores = new LinkedHashMap<String, Integer>();
		Attribute attribute = getSubAttribute(sno, subject);
		// 获取对应学号的学生对应课程的节点
		if (attribute != null) {
			scores.put(attribute.getName(), Integer.parseInt(attribute.getValue()));
			// 学生的学号和将对应课程分数存入map
			return scores;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#sortTable(java.util.Map, boolean)
	 */
	@Override
	public Map<String, Integer> sortTable(Map<String, Integer> map, boolean sortMethod) throws RemoteException {
		// 传入需要排序的内容、排序方法
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		if (sortMethod) {
			Collections.sort(list, new SortUtil.SortByUp());
			// 从低到高排序
		} else {
			Collections.sort(list, new SortUtil.SortByDown());
			// 从高到低排序
		}
		map.clear();
		for (int i = 0; i < list.size(); i++) {
			Entry<String, Integer> entry = list.get(i);
			map.put(entry.getKey(), entry.getValue());
			// 将排序后的内容装入map
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#getSubjects(java.lang.String)
	 */
	@Override
	public List<String> getSubjects(String sno) throws RemoteException {
		// 传入学号
		List<String> subjects = new ArrayList<String>();

		try {
			Document doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// 获取所有学生节点
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				String sno1 = student.attribute("sno").getValue();
				if (sno1 != null && sno1.equals(sno)) {
					Element scoresNode = student.element("scores");
					// 获取对应学生的分数节点
					for (int yearNum = 1; yearNum < 5; yearNum++) {
						Element yearNode = scoresNode.element("year" + yearNum);
						for (int termNum = yearNum * 2 - 1; termNum < yearNum * 2 + 1; termNum++) {
							Element termNode = yearNode.element("term" + termNum);
							// 迭代学期节点
							@SuppressWarnings("unchecked")
							List<Attribute> attributes = termNode.attributes();
							for (int j = 0; j < attributes.size(); j++) {
								Attribute attribute = attributes.get(j);
								subjects.add(attribute.getName());
							}
							// 迭代分数节点，获取所有课程名称存入map
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return subjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#getAllClass()
	 */
	@Override
	public List<String> getAllClass() throws RemoteException {
		List<String> schoolClasses = new ArrayList<String>();

		try {
			Document doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// 获取所有学生节点
			String schoolClass;
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				schoolClass = student.attributeValue("schoolClass");
				if (!schoolClasses.contains(schoolClass)) {
					schoolClasses.add(schoolClass);
				}
			}
			// 迭代获取所有班级名称存入List
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return schoolClasses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#getAllsubjectsByClass(java.lang.String)
	 */
	@Override
	public List<String> getAllsubjectsByClass(String schoolClass) throws RemoteException {
		// 传入班级信息
		List<String> subjects = new ArrayList<String>();
		try {
			Document doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// 获取所有学生节点
			for (int j = 0; j < students.size(); j++) {
				Element student = students.get(j);
				String schoolClass1 = student.attribute("schoolClass").getValue();
				if (schoolClass.equals(schoolClass1)) {
					String sno = student.attributeValue("sno");
					subjects = getSubjects(sno);
					break;
				}
			}
			// 迭代获取所有课程名称存入List
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return subjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#updateScore(java.lang.String, java.lang.String, int)
	 */
	@Override
	public boolean updateScore(String sno, String subject, int score) throws RemoteException {
		// 传入学号，课程名称，分数
		Attribute attribute = getSubAttribute(sno, subject);
		// 得到对应学生节点
		if (attribute != null) {
			attribute.setValue(score + "");
			// 修改该学生节点对应课程分数
			try {
				Xmlutil.write2Xml(attribute.getDocument());
				// 写出Document对象到xml数据文件
				return true;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#getSubAttribute(java.lang.String, java.lang.String)
	 */
	@Override
	public Attribute getSubAttribute(String sno, String subject) throws RemoteException {
		// 传入学号、课程名称
		Document doc;
		Attribute attribute = null;
		try {
			doc = Xmlutil.getDocument();
			// 获取Document对象
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// 获取所有学生节点
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				String sno1 = student.attribute("sno").getValue();
				// 迭代学生节点，获取对应学号的学生的节点
				if (sno1 != null && sno1.equals(sno)) {
					Element scoresNode = student.element("scores");
					for (int yearNum = 1; yearNum < 5; yearNum++) {
						Element yearNode = scoresNode.element("year" + yearNum);
						for (int termNum = yearNum * 2 - 1; termNum < yearNum * 2 + 1; termNum++) {
							Element termNode = yearNode.element("term" + termNum);
							attribute = termNode.attribute(subject);
							// 迭代获取对应课程的节点
							if (attribute != null) {
								return attribute;
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#findSubscoresBynum(java.lang.String, int, boolean)
	 */
	@Override
	public Map<String, Integer> findSubscoresBynum(String subject, int num, boolean isHigh)
			throws UserNotFoundException, RemoteException {
		// 传入课程名称，指定分数，查询方式（高于或低于指定分数）

		Document doc;
		HashMap<String, Integer> snos = new HashMap<String, Integer>();
		try {
			doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// 获取所有学生节点
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				String sno = student.attributeValue("sno");
				Map<String, Integer> map = findScore(sno, subject);
				if (map != null) {
					int score = map.entrySet().iterator().next().getValue();
					if (isHigh) {
						// 高于或等于指定分数
						if (score >= num) {
							snos.put(sno, score);
						}
					} else {
						// 低于指定分数
						if (score < num) {
							snos.put(sno, score);
						}
					}

				}
			}
			// 迭代获取所有符合条件的分数，存入map
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		return snos;
	}

	public List<Integer> getClassScoresBysub(String schoolClass, String subject)
			throws UserNotFoundException, RemoteException {
		// 传入班级名称，课程名称
		Document doc;
		ArrayList<Integer> scores = new ArrayList<Integer>();
		int score;
		String sno;
		try {
			doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// 获取所有学生节点
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				if (student.attributeValue("schoolClass").equals(schoolClass)) {
					sno = student.attributeValue("sno");
					Map<String, Integer> map = findScore(sno, subject);
					if (map != null) {
						score = map.values().iterator().next();
						scores.add(score);
					}
				}
			}
			// 迭代获取所有对应班级、课程的分数存入List
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		return scores;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#statistics(java.lang.String, java.lang.String)
	 */
	@Override
	public String[][] statistics(String schoolClass, String subject) throws UserNotFoundException, RemoteException {

		List<Integer> scores = getClassScoresBysub(schoolClass, subject);
		int score, level_1 = 0, level_2 = 0, level_3 = 0, level_4 = 0, level_5 = 0;
		String[][] result = new String[5][2];
		// 保存统计后数据
		Float count = (float) scores.size();

		for (int i = 0; i < count; i++) {
			score = scores.get(i);
			if (score < 60) {
				level_1++;
			} else if (score < 70) {
				level_2++;
			} else if (score < 80) {
				level_3++;
			} else if (score < 90) {
				level_4++;
			} else if (score <= 100) {
				level_5++;
			}
		}
		// 迭代统计所有分数，判断分数值存入对应的数组位置

		BigDecimal b;
		float per;
		int[] levels = { level_1, level_2, level_3, level_4, level_5 };
		for (int i = 0; i < levels.length; i++) {
			b = new BigDecimal(levels[i] * 100 / count);
			per = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			// 统计百分比四舍五入
			result[i][0] = levels[i] + "人";
			result[i][1] = per + "%";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#getUsernames()
	 */
	@Override
	public List<String> getUsernames() throws RemoteException {

		List<String> usernames = new ArrayList<String>();

		try {
			Document doc = Xmlutil.getDocument();
			Element rootNode = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> usersList = rootNode.elements();
			// 获取所有用户节点
			for (int i = 0; i < usersList.size(); i++) {
				Element user = usersList.get(i);
				String username = user.attributeValue("name");
				usernames.add(username);
			}
			// 迭代获取所有用户节点，获取所有用户名存入List
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return usernames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.rain.dao.UserDao#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String username) throws RemoteException {

		try {
			Document doc = Xmlutil.getDocument();
			Element rootNode = doc.getRootElement();

			@SuppressWarnings("unchecked")
			List<Element> usersList = rootNode.elements();
			// 获取所有用户节点
			for (int i = 0; i < usersList.size(); i++) {
				Element user = usersList.get(i);
				String username1 = user.attributeValue("name");
				if (username.equals(username1)) {
					rootNode.remove(user);
				}
			}
			// 迭代获取所有用户节点，删除对应用户名的节点
			Xmlutil.write2Xml(doc);
			// 将Document对象写出到xml数据文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
