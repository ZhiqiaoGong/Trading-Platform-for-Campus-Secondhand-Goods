//���������ͻ��ˣ����ǲ��ᣬ��֪���������û����
package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class Client {
	//��Ҫ���ӵĶ˿�
	static final int port=8888; 
	//���ӵķ������ĵ�ַ
	static final String host="127.0.0.1";
	private Socket socket; 
	BufferedWriter writer;
	BufferedReader reader;
	PrintWriter pw;
	//��Ҫ���ӵĶ˿�

	public Client() {
	try {
	//����socket����
	socket=new Socket (host ,port);
	//������������������Ϣ
	writer=new BufferedWriter(new OutputStreamWriter(socket. getOutputStream()));
	//�����������շ�������Ϣ
	reader=new BufferedReader (new java.io.InputStreamReader(socket. getInputStream()));
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
