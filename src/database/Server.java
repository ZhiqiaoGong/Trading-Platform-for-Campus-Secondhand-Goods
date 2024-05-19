//本来想搞个服务端的，没写，不会
package database;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;



public class Server implements Runnable {



	public Server(Socket socket) {
		// TODO 自动生成的构造函数存根
		//super(socket);
	}

	public void run() {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost(); 
			ServerSocket serverSocket=new ServerSocket(8888);
			int count = 0;
			System.out.println("服务器启动，等待客户端的连接。。。");
			Socket socket = null;
			while(true) {
			socket=serverSocket.accept( );
			//用来计入登陆的个数
			++count ;
			Thread serverThread=new Thread(new Server(socket));
			serverThread. start() ;
			System.out.println(".上线的客户端有"+ count + "个! ");

			System.out.println( "当前客户端的IP地址是: "+inetAddress.getHostAddress());
			} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e. printStackTrace();}
		}
	}
		


