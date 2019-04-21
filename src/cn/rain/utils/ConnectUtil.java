package cn.rain.utils;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import cn.rain.dao.UserDao;
import cn.rain.dao.impl.UsersDaoXmlIpl;

/** 
* ���ӿͻ��˵Ĺ�����
* @author  rain 
* @version 2014-09-10
* @since   JDK1.6
*/

public class ConnectUtil extends Thread {

	@Override
	public synchronized void start() {
		try {
			// ����һ��Զ�̶���
			UserDao dao = new UsersDaoXmlIpl();

			String ip = AddressUtil.getIP();
			int port = Integer.parseInt(AddressUtil.getPort());
			// ���������ϵ�Զ�̶���ע���Registry��ʵ������ָ���˿�Ϊ8888
			LocateRegistry.createRegistry(port);
			Naming.bind("rmi://"+ip+":"+port+"/Dao", dao);
			/*Naming.bind("rmi://localhost:8888/Dao",dao);*/ 

			System.out.println("Զ�̶���󶨳ɹ���");
		} catch (RemoteException e) {
			System.out.println("����Զ�̶������쳣��");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.out.println("�����ظ��󶨶����쳣��");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("����URL�����쳣��");
			e.printStackTrace();
		}
	}

}
