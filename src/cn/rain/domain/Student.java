package cn.rain.domain;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Map;

/** 
* 学生用户实体 
* @author  rain 
* @version 2014-09-10
* @since   JDK1.6
*/ 
public class Student extends UnicastRemoteObject implements User {
	
	private static final long serialVersionUID = -4774660907147088266L;

	private String username;//用户名
	private String password;//密码
	private String permission = "学生";//用户权限为“学生”

	private String sno;//学号
	private String name;//姓名
	private String gender;//性别
	private Date birthday;//出生日期
	private Map<String, Integer> scores;//分数集合
	private String schoolClass;//班级

	public Student() throws RemoteException {
	}
	
	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getUsername()
	 */
	@Override
	public String getUsername() throws RemoteException{
		return username;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) throws RemoteException{
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getPassword()
	 */
	@Override
	public String getPassword() throws RemoteException{
		return password;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) throws RemoteException{
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#isPermission()
	 */
	@Override
	public String isPermission() throws RemoteException{
		return permission;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setPermission(boolean)
	 */
	@Override
	public void setPermission(String permission) throws RemoteException{
		this.permission = permission;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getSno()
	 */
	@Override
	public String getSno() throws RemoteException{
		return sno;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setSno(java.lang.String)
	 */
	@Override
	public void setSno(String sno) throws RemoteException{
		this.sno = sno;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getName()
	 */
	@Override
	public String getName() throws RemoteException{
		return name;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) throws RemoteException{
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getGender()
	 */
	@Override
	public String getGender() throws RemoteException{
		return gender;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setGender(java.lang.String)
	 */
	@Override
	public void setGender(String gender) throws RemoteException{
		this.gender = gender;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getBirthday()
	 */
	@Override
	public Date getBirthday() throws RemoteException{
		return birthday;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setBirthday(java.util.Date)
	 */
	@Override
	public void setBirthday(Date birthday) throws RemoteException{
		this.birthday = birthday;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getScores()
	 */
	@Override
	public Map<String, Integer> getScores() throws RemoteException{
		return scores;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setScores(java.util.Map)
	 */
	@Override
	public void setScores(Map<String, Integer> scores) throws RemoteException{
		this.scores = scores;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#getSchoolClass()
	 */
	@Override
	public String getSchoolClass() throws RemoteException{
		return schoolClass;
	}

	/* (non-Javadoc)
	 * @see cn.rain.domain.User#setSchoolClass(java.lang.String)
	 */
	@Override
	public void setSchoolClass(String schoolClass) throws RemoteException{
		this.schoolClass = schoolClass;
	}
}
