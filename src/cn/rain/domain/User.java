package cn.rain.domain;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

/** 
* 用户类接口
* @author  rain 
* @version 2014-09-10
* @since   JDK1.6
*/ 

public interface User extends Remote{

	String getUsername() throws RemoteException;

	void setUsername(String username) throws RemoteException;

	String getPassword() throws RemoteException;

	void setPassword(String password) throws RemoteException;

	String isPermission() throws RemoteException;

	void setPermission(String permission) throws RemoteException;

	String getSno() throws RemoteException;

	void setSno(String sno) throws RemoteException;

	String getName() throws RemoteException;

	void setName(String name) throws RemoteException;

	String getGender() throws RemoteException;

	void setGender(String gender) throws RemoteException;

	Date getBirthday() throws RemoteException;

	void setBirthday(Date birthday) throws RemoteException;

	Map<String, Integer> getScores() throws RemoteException;

	void setScores(Map<String, Integer> scores) throws RemoteException;

	String getSchoolClass() throws RemoteException;

	void setSchoolClass(String schoolClass) throws RemoteException;

}