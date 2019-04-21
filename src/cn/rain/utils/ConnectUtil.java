package cn.rain.utils;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import cn.rain.dao.UserDao;
import cn.rain.dao.impl.UsersDaoXmlIpl;

/** 
* 连接客户端的工具类
* @author  rain 
* @version 2014-09-10
* @since   JDK1.6
*/

public class ConnectUtil extends Thread {

	@Override
	public synchronized void start() {
		try {
			// 创建一个远程对象
			UserDao dao = new UsersDaoXmlIpl();

			String ip = AddressUtil.getIP();
			int port = Integer.parseInt(AddressUtil.getPort());
			// 本地主机上的远程对象注册表Registry的实例，并指定端口为8888
			LocateRegistry.createRegistry(port);
			Naming.bind("rmi://"+ip+":"+port+"/Dao", dao);
			/*Naming.bind("rmi://localhost:8888/Dao",dao);*/ 

			System.out.println("远程对象绑定成功！");
		} catch (RemoteException e) {
			System.out.println("创建远程对象发生异常！");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.out.println("发生重复绑定对象异常！");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("发生URL畸形异常！");
			e.printStackTrace();
		}
	}

}
