package server;

import common.Message;
import common.MsgType;
import server.UsingDB;
import view.Login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.FriendPage;
import client.MultFriendList;

/**
 * ��������ͻ���ͨ���߳�
 */
public class ServerMesgIdentifier extends Thread {

    private Socket client;
    private volatile boolean isRunning;

    public ServerMesgIdentifier(Socket client) {
        this.client = client;
        this.isRunning = true;
    }

    public Socket getClient() {
        return client;
    }

    public void myStop(){
        isRunning = false;
    }

    /**
     * ���Լ����߻����ߵ���Ϣ֪ͨ����
     * @param uid
     */
    public void notifyOthers(String uid){
        ObjectOutputStream out = null;
        Message msg = new Message();
        msg.setType(MsgType.RET_ONLINE_FRIENDS);
        msg.setContent(MultiToClientThread.getOnLineList());
        for (Object o : MultiToClientThread.getClientThreads().keySet()) {
            try {
                String id = o.toString();
                if(!id.equals(uid)){//����֪ͨ�Լ�
                    msg.setGetterId(id);
                    out = new ObjectOutputStream(MultiToClientThread.getClientThread(id).client.getOutputStream());
                    out.writeObject(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        try {
            while(isRunning){
                ObjectInputStream input = new ObjectInputStream(this.client.getInputStream());
                Message msg = (Message) input.readObject();
                //�ж���Ϣ����
                if(msg.getType() == MsgType.GET_ONLINE_FRIENDS) {
                    msg.setType(MsgType.RET_ONLINE_FRIENDS);
                    msg.setGetterId(msg.getSenderId());
                    msg.setContent(MultiToClientThread.getOnLineList());
                    ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getGetterId()).client.getOutputStream());
                    output.writeObject(msg);
                    System.out.println("�����б�ɹ�");
                }else if(msg.getType() == MsgType.COMMON_MESSAGE) {
                    ServerMesgIdentifier thread = MultiToClientThread.getClientThread(msg.getGetterId());//�ҵ������ߵ��߳�
                    if(thread!=null) {
                    	ObjectOutputStream output = new ObjectOutputStream(thread.client.getOutputStream());
                        output.writeObject(msg);
                        System.out.println("ת���ɹ�");
                    }
                }else if(msg.getType() == MsgType.UNLOAD_LOGIN) {
                    String fromId = msg.getSenderId();
                    //�������߳�
                    myStop();
                    MultiToClientThread.removeClientThread(fromId);
                    notifyOthers(fromId);
                    System.out.println(fromId+" �˳���¼");
                    ServerPage.showMsg("�û�"+fromId+"�˳���¼��");
                }
                else if(msg.getType()==MsgType.GET_OFFLINE_MEAG) {
                  ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                  msg.setType(MsgType.OFFLINE_MESG);
                  msg.setContent(UsingDB.searchLxmessage(msg.getSenderId(),msg.getGetterId()));
                  output.writeObject(msg);
                }else if(msg.getType()==MsgType.SEND_OFFLINE_MESG) {
                	UsingDB.addOneMesg(msg.getContent(), msg.getSenderId(), msg.getGetterId(),msg.getSendTime());
                    System.out.println(msg.getSenderId()+" �����һ��������Ϣ");
                }
                else if(msg.getType()==MsgType.JUDGE_STATE) {
                    ServerMesgIdentifier thread = MultiToClientThread.getClientThread(msg.getGetterId());//�ҵ������ߵ��߳�
                    if(null == thread) {
                    	//֪ͨ�����ߺ��Ѳ�����
                        ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                        msg.setType(MsgType.NOT_ONLINE);
                        output.writeObject(msg);
                        System.out.println("֪ͨ�Է������߳ɹ�");
                    }
                    else {
                    	ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                        msg.setType(MsgType.IS_ONLINE);
                        output.writeObject(msg);
                        System.out.println("֪ͨ�Է����߳ɹ�");
                    }
                }
                else if(msg.getType()==MsgType.ADD_FRIEND) {
                	UsingDB.addFriend(msg.getSenderId(), msg.getSenderName(), msg.getGetterId(), msg.getContent());
                	Message msg1=new Message();
                	msg1.setType(MsgType.REPRESH_FRIENDS_LIST);
                	msg1.setContent(UsingDB.ref(msg.getSenderId()));
                	ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                    output.writeObject(msg);
                    System.out.println("ˢ�º����б�ɹ�");
                	
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}