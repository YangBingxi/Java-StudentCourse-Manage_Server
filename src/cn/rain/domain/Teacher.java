package cn.rain.domain;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Map;

/**
 * 教师用户实体
 * 
 * @author SwYoung
 * @version V1.0
 * @since 2019-4-29
 */

public class Teacher extends UnicastRemoteObject implements User {

	private static final long serialVersionUID = -1273816804939108384L;

	public Teacher() throws RemoteException {
	}

	private String name;// 姓名
	private String username;// 用户名
	private String password;// 密码
	private String permission = "教师";// 用户权限为“教师”

	public String getUsername() throws RemoteException {
		return username;
	}

	public void setUsername(String username) throws RemoteException {
		this.username = username;
	}

	public String getPassword() throws RemoteException {
		return password;
	}

	public void setPassword(String password) throws RemoteException {
		this.password = password;
	}

	public String isPermission() throws RemoteException {
		return permission;
	}

	public void setPermission(String permission) throws RemoteException {
		this.permission = permission;
	}

	@Override
	public String getSno() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return name;
	}

	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public void setSno(String sno) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getGender() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGender(String gender) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getBirthday() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBirthday(Date birthday) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Integer> getScores() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScores(Map<String, Integer> scores) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSchoolClass() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSchoolClass(String schoolClass) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
