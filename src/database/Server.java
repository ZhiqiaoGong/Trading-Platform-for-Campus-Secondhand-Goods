//������������˵ģ�ûд������
package database;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;



public class Server implements Runnable {



	public Server(Socket socket) {
		// TODO �Զ����ɵĹ��캯�����
		//super(socket);
	}

	public void run() {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost(); 
			ServerSocket serverSocket=new ServerSocket(8888);
			int count = 0;
			System.out.println("�������������ȴ��ͻ��˵����ӡ�����");
			Socket socket = null;
			while(true) {
			socket=serverSocket.accept( );
			//���������½�ĸ���
			++count ;
			Thread serverThread=new Thread(new Server(socket));
			serverThread. start() ;
			System.out.println(".���ߵĿͻ�����"+ count + "��! ");

			System.out.println( "��ǰ�ͻ��˵�IP��ַ��: "+inetAddress.getHostAddress());
			} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e. printStackTrace();}
		}
	}
		


