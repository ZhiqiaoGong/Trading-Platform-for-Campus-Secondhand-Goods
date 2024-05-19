package server;

import common.Message;
import common.MsgType;
import common.ChatUser;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private ServerSocket server;
    private Socket client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean isRunning;

    public Server(){
        System.out.println("---Server Start---");
        isRunning = true;
        new Thread(this).start();
    }

    /**
     * �����߳�����
     */
    public void myStop() {
        isRunning = false;
        close(server);
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            while(isRunning) {
                client = server.accept();
                System.out.println("һ���ͻ���������");
                input = new ObjectInputStream(client.getInputStream());
                output = new ObjectOutputStream(client.getOutputStream());
                ChatUser u = (ChatUser)input.readObject();
                System.out.println(u.toString());
                doUserLogin(u);
            }
        } catch (IOException e) {
            close(output,input,client,server);//�ͷ���Դ
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * �����û���¼
     * @param u
     */
    private void doUserLogin(ChatUser u){
        Message msg = new Message();
        UsingDB jc = new UsingDB();
        //���û�δ��¼
        if(null == MultiToClientThread.getClientThread(u.getUid())){
            try{
                String qname = jc.checkUserInfo(u);
                if(null != qname){//��¼�ɹ�
                    msg.setType(MsgType.LOGIN_SUCCEED);
                    msg.setContent(qname + "-" + jc.getFriendsList(u.getUid()));
                    output.writeObject(msg);
                    //�ͻ������ӳɹ���Ϊ�䴴���̱߳������������ͨѶ
                    ServerMesgIdentifier th = new ServerMesgIdentifier(client);
                    th.start();
                    //������ӵ��̼߳���
                    MultiToClientThread.addClientThread(u.getUid(),th);
                    //֪ͨ�����û�
                    th.notifyOthers(u.getUid());
                    ServerPage.showMsg("�û�"+u.getUid()+"�ɹ���¼��");
                }else{
                    msg.setType(MsgType.LOGIN_FAILED);
                    output.writeObject(msg);
                    close(output,input,client);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{//���û��ѵ�¼
            try {
                msg.setType(MsgType.ALREADY_LOGIN);
                output.writeObject(msg);
                close(output,input,client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ���ڹرն��io��
     * @param ios
     */
    private void close(Closeable... ios) {//�ɱ䳤����
        for(Closeable io: ios) {
            try {
                if(null != io)
                    io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
