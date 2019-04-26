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
 * ���ݽ����ӿ�ʵ����
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */
public class UsersDaoXmlIpl extends UnicastRemoteObject implements UserDao {

	private static final long serialVersionUID = -5454546011882426268L;

	public Map<String, String> getAdmin() {

		String username = PropUtil.getUsername();// ��ȡ����Ա�û���
		String password = PropUtil.getPassword();// ��ȡ����Ա����

		Map<String, String> admin = new LinkedHashMap<String, String>();// MAP��ʼ��
		admin.put("username", username);// ����Ԫ��
		admin.put("password", password);// ����Ԫ��
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
		// �����û��������롢�û�Ȩ��
		try {
			Document doc = Xmlutil.getDocument();
			// ��ȡDocument����
			if (permission.equals("ѧ��")) {
				// �û���ɫΪѧ��
				Element StudentNode = (Element) doc
						.selectSingleNode("//student[@username='" + username + "' and @password = '" + password + "']");
				// �õ���Ӧѧ���ڵ�
				if (StudentNode == null) {
					return null;
					// ��������ڶ�Ӧѧ���򷵻ؿ�
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
				// ����ѧ���ڵ��ѧ����Ϣ����ѧ������
				return student;
			} else {
				// �û���ɫΪ��ʦ
				Element teacherNode = (Element) doc
						.selectSingleNode("//teacher[@username='" + username + "' and @password = '" + password + "']");
				// �õ���Ӧѧ����ʦ
				if (teacherNode == null) {
					return null;
					// ��������ڶ�Ӧ��ʦ�򷵻ؿ�
				}
				Teacher teacher = new Teacher();
				teacher.setName(teacherNode.attributeValue("name"));
				teacher.setUsername(teacherNode.attributeValue("username"));
				teacher.setPassword(teacherNode.attributeValue("password"));
				// ���ý�ʦ�ڵ����Ϣ�����ʦ����
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
		// ����ѧ��
		try {
			Document doc = Xmlutil.getDocument();
			// ��ȡDocument����
			Element StudentNode = (Element) doc.selectSingleNode("//student[@sno='" + sno + "']");
			// ��ȡ��Ӧѧ���ڵ�
			if (StudentNode == null) {
				return null;
				// ��������ڶ�Ӧѧ���ڵ��򷵻�null
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
			// ����ѧ���ڵ����Ϣ����ѧ������
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
		// ��������
		try {
			Document doc = Xmlutil.getDocument();
			// ��ȡDocument����
			Element StudentNode = (Element) doc.selectSingleNode("//student[@name='" + name + "']");
			// ��ȡ��Ӧѧ���ڵ�
			if (StudentNode == null) {
				return null;
				// ��������ڶ�Ӧѧ���ڵ��򷵻�null
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
			// ����ѧ���ڵ����Ϣ����ѧ������
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
		// �����û���
		try {
			Document doc = Xmlutil.getDocument();
			// ��ȡDocument����
			Element teacherNode = (Element) doc.selectSingleNode("//teacher[@username='" + username + "']");
			// ��ȡ��Ӧ��ʦ�ڵ�
			if (teacherNode == null) {
				return null;
				// ��������ڶ�Ӧ��ʦ�ڵ��򷵻�null
			}
			Teacher teacher = new Teacher();
			teacher.setName(teacherNode.attributeValue("name"));
			teacher.setUsername(teacherNode.attributeValue("username"));
			teacher.setPassword(teacherNode.attributeValue("password"));
			// ����Ӧ��ʦ��Ϣ�����ʦ����
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
		// ���뱣���û���Ϣ��nap
		User user = null;
		try {
			if (((String) map.get("permission")).equals("ѧ��")) {
				// ѧ���û�
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
					// ��ѧ����Ϣ�浽ѧ������
				}

				if (findStudentBySno(user.getSno()) != null) {
					// ����Ѵ��ڶ�Ӧѧ���ڵ㣬���׳��쳣UserExistedException
					throw new UserExistedException();
				} else {

					try {
						Document doc = Xmlutil.getDocument();
						Element rootNode = doc.getRootElement();
						Student student = (Student) user;
						Element StudentsNode = rootNode.addElement("student");
						// ���ѧ���ڵ�
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
						// ��ѧ����Ϣд��ѧ���ڵ�
						Xmlutil.write2Xml(doc);
						// д��Document����xml�����ļ�
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}

			} else {
				// ��ʦ�û�
				if (findTeacher((String) map.get("username")) != null) {
					throw new UserExistedException();
					// ����Ѵ��ڶ�Ӧ��ʦ�ڵ㣬���׳��쳣UserExistedException
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
						// ����ʦ��Ϣ�����ʦ����
						Element teachersNode = rootNode.addElement("teacher");
						teachersNode.addAttribute("username", user.getUsername());
						teachersNode.addAttribute("password", user.getPassword());
						teachersNode.addAttribute("name", user.getName());
						// ����ʦ��Ϣд���ʦ�ڵ�
						Xmlutil.write2Xml(doc);
						// д��Document����xml�����ļ�
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
		// ����ѧ�š�������ѧ��
		if (findStudentBySno(sno) == null) {
			throw new UserNotFoundException();
			// ���û�ж�Ӧѧ�ŵ�ѧ�����׳��쳣UserNotFoundException
		} else {
			Document doc;
			try {
				doc = Xmlutil.getDocument();
				// ��ȡDocument����
				@SuppressWarnings("unchecked")
				List<Element> students = doc.selectNodes("//student");
				// ��ȡ����ѧ���ڵ�
				for (int i = 0; i < students.size(); i++) {
					Element student = students.get(i);
					String sno1 = student.attribute("sno").getValue();
					if (sno1 != null && sno1.equals(sno)) {
						Element scoresNode = student.element("scores");
						// ����ѧ���ڵ㣬�ҵ���Ӧѧ�ŵ�ѧ���ڵ�
						Element yearNode = scoresNode.element(("year" + (term + 1) / 2));
						Element termNode = yearNode.element("term" + term);
						// ��ȡ��Ӧѧ�ڽڵ�
						Iterator<Entry<String, Integer>> iterator = scores.entrySet().iterator();
						while (iterator.hasNext()) {
							Entry<String, Integer> entry = iterator.next();
							termNode.addAttribute(entry.getKey(), entry.getValue() + "");
							// �ڶ�Ӧѧ�ڽڵ���ӳɼ�
						}

						Xmlutil.write2Xml(doc);
						// д��Document���������ļ�
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
		// ����ѧ�š���ѯ������ѧ�ڻ�ѧ�꣩��ѧ�ڻ�ѧ����
		if (findStudentBySno(sno) == null) {
			throw new UserNotFoundException();
			// ����û��Ѵ������׳��Զ����쳣 UserNotFoundException
		} else {

			Map<String, Integer> scores = new LinkedHashMap<String, Integer>();
			// ����װ�û�ѧ�źͷ�����map
			if ("term".equals(method)) {
				// ��ѧ�ڲ�ѯ
				try {
					Document doc = Xmlutil.getDocument();// ��ȡDocument����
					List<Element> students = doc.selectNodes("//student");// ��ȡ����student�ڵ�
					for (int i = 0; i < students.size(); i++) {
						Element student = students.get(i);
						String sno1 = student.attribute("sno").getValue();
						if (sno1 != null && sno1.equals(sno)) {
							// �õ���Ӧѧ�ŵ�ѧ���ڵ�
							Element scoresNode = student.element("scores");
							Element yearNode = scoresNode.element(("year" + (num + 1) / 2));
							Element termNode = yearNode.element("term" + num);
							Iterator<Attribute> iterator = termNode.attributeIterator();
							while (iterator.hasNext()) {
								Attribute attr = (Attribute) iterator.next();
								scores.put(attr.getName(), Integer.parseInt(attr.getValue()));
							} // ��������ѧ�ڽڵ�
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return scores;// ���ؽ��

			} else if ("year".equals(method)) {
				// ��ѧ���ѯ
				try {
					Document doc = Xmlutil.getDocument();
					List<Element> students = doc.selectNodes("//student");
					// ��ȡ����ѧ���ڵ�
					for (int i = 0; i < students.size(); i++) {
						Element student = students.get(i);
						String sno1 = student.attribute("sno").getValue();
						if (sno1 != null && sno1.equals(sno)) {
							// ��ȡ��Ӧѧ�ŵ�ѧ���ڵ�
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
							// ������ȡ��ѡѧ��ĳɼ�
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
		// ����ѧ�š��γ�����
		Map<String, Integer> scores = new LinkedHashMap<String, Integer>();
		Attribute attribute = getSubAttribute(sno, subject);
		// ��ȡ��Ӧѧ�ŵ�ѧ����Ӧ�γ̵Ľڵ�
		if (attribute != null) {
			scores.put(attribute.getName(), Integer.parseInt(attribute.getValue()));
			// ѧ����ѧ�źͽ���Ӧ�γ̷�������map
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
		// ������Ҫ��������ݡ����򷽷�
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		if (sortMethod) {
			Collections.sort(list, new SortUtil.SortByUp());
			// �ӵ͵�������
		} else {
			Collections.sort(list, new SortUtil.SortByDown());
			// �Ӹߵ�������
		}
		map.clear();
		for (int i = 0; i < list.size(); i++) {
			Entry<String, Integer> entry = list.get(i);
			map.put(entry.getKey(), entry.getValue());
			// ������������װ��map
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
		// ����ѧ��
		List<String> subjects = new ArrayList<String>();

		try {
			Document doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// ��ȡ����ѧ���ڵ�
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				String sno1 = student.attribute("sno").getValue();
				if (sno1 != null && sno1.equals(sno)) {
					Element scoresNode = student.element("scores");
					// ��ȡ��Ӧѧ���ķ����ڵ�
					for (int yearNum = 1; yearNum < 5; yearNum++) {
						Element yearNode = scoresNode.element("year" + yearNum);
						for (int termNum = yearNum * 2 - 1; termNum < yearNum * 2 + 1; termNum++) {
							Element termNode = yearNode.element("term" + termNum);
							// ����ѧ�ڽڵ�
							@SuppressWarnings("unchecked")
							List<Attribute> attributes = termNode.attributes();
							for (int j = 0; j < attributes.size(); j++) {
								Attribute attribute = attributes.get(j);
								subjects.add(attribute.getName());
							}
							// ���������ڵ㣬��ȡ���пγ����ƴ���map
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
			// ��ȡ����ѧ���ڵ�
			String schoolClass;
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				schoolClass = student.attributeValue("schoolClass");
				if (!schoolClasses.contains(schoolClass)) {
					schoolClasses.add(schoolClass);
				}
			}
			// ������ȡ���а༶���ƴ���List
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
		// ����༶��Ϣ
		List<String> subjects = new ArrayList<String>();
		try {
			Document doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// ��ȡ����ѧ���ڵ�
			for (int j = 0; j < students.size(); j++) {
				Element student = students.get(j);
				String schoolClass1 = student.attribute("schoolClass").getValue();
				if (schoolClass.equals(schoolClass1)) {
					String sno = student.attributeValue("sno");
					subjects = getSubjects(sno);
					break;
				}
			}
			// ������ȡ���пγ����ƴ���List
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
		// ����ѧ�ţ��γ����ƣ�����
		Attribute attribute = getSubAttribute(sno, subject);
		// �õ���Ӧѧ���ڵ�
		if (attribute != null) {
			attribute.setValue(score + "");
			// �޸ĸ�ѧ���ڵ��Ӧ�γ̷���
			try {
				Xmlutil.write2Xml(attribute.getDocument());
				// д��Document����xml�����ļ�
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
		// ����ѧ�š��γ�����
		Document doc;
		Attribute attribute = null;
		try {
			doc = Xmlutil.getDocument();
			// ��ȡDocument����
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// ��ȡ����ѧ���ڵ�
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				String sno1 = student.attribute("sno").getValue();
				// ����ѧ���ڵ㣬��ȡ��Ӧѧ�ŵ�ѧ���Ľڵ�
				if (sno1 != null && sno1.equals(sno)) {
					Element scoresNode = student.element("scores");
					for (int yearNum = 1; yearNum < 5; yearNum++) {
						Element yearNode = scoresNode.element("year" + yearNum);
						for (int termNum = yearNum * 2 - 1; termNum < yearNum * 2 + 1; termNum++) {
							Element termNode = yearNode.element("term" + termNum);
							attribute = termNode.attribute(subject);
							// ������ȡ��Ӧ�γ̵Ľڵ�
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
		// ����γ����ƣ�ָ����������ѯ��ʽ�����ڻ����ָ��������

		Document doc;
		HashMap<String, Integer> snos = new HashMap<String, Integer>();
		try {
			doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// ��ȡ����ѧ���ڵ�
			for (int i = 0; i < students.size(); i++) {
				Element student = students.get(i);
				String sno = student.attributeValue("sno");
				Map<String, Integer> map = findScore(sno, subject);
				if (map != null) {
					int score = map.entrySet().iterator().next().getValue();
					if (isHigh) {
						// ���ڻ����ָ������
						if (score >= num) {
							snos.put(sno, score);
						}
					} else {
						// ����ָ������
						if (score < num) {
							snos.put(sno, score);
						}
					}

				}
			}
			// ������ȡ���з��������ķ���������map
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		return snos;
	}

	public List<Integer> getClassScoresBysub(String schoolClass, String subject)
			throws UserNotFoundException, RemoteException {
		// ����༶���ƣ��γ�����
		Document doc;
		ArrayList<Integer> scores = new ArrayList<Integer>();
		int score;
		String sno;
		try {
			doc = Xmlutil.getDocument();
			@SuppressWarnings("unchecked")
			List<Element> students = doc.selectNodes("//student");
			// ��ȡ����ѧ���ڵ�
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
			// ������ȡ���ж�Ӧ�༶���γ̵ķ�������List
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
		// ����ͳ�ƺ�����
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
		// ����ͳ�����з������жϷ���ֵ�����Ӧ������λ��

		BigDecimal b;
		float per;
		int[] levels = { level_1, level_2, level_3, level_4, level_5 };
		for (int i = 0; i < levels.length; i++) {
			b = new BigDecimal(levels[i] * 100 / count);
			per = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			// ͳ�ưٷֱ���������
			result[i][0] = levels[i] + "��";
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
			// ��ȡ�����û��ڵ�
			for (int i = 0; i < usersList.size(); i++) {
				Element user = usersList.get(i);
				String username = user.attributeValue("name");
				usernames.add(username);
			}
			// ������ȡ�����û��ڵ㣬��ȡ�����û�������List
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
			// ��ȡ�����û��ڵ�
			for (int i = 0; i < usersList.size(); i++) {
				Element user = usersList.get(i);
				String username1 = user.attributeValue("name");
				if (username.equals(username1)) {
					rootNode.remove(user);
				}
			}
			// ������ȡ�����û��ڵ㣬ɾ����Ӧ�û����Ľڵ�
			Xmlutil.write2Xml(doc);
			// ��Document����д����xml�����ļ�
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
