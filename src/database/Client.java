//本来想搞个客户端，但是不会，不知道这个类有没有用
package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class Client {
	//需要连接的端口
	static final int port=8888; 
	//连接的服务器的地址
	static final String host="127.0.0.1";
	private Socket socket; 
	BufferedWriter writer;
	BufferedReader reader;
	PrintWriter pw;
	//需要连接的端口

	public Client() {
	try {
	//创建socket连接
	socket=new Socket (host ,port);
	//输出流，向服务端输出消息
	writer=new BufferedWriter(new OutputStreamWriter(socket. getOutputStream()));
	//输入流，接收服务器消息
	reader=new BufferedReader (new java.io.InputStreamReader(socket. getInputStream()));
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
