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
 * 服务器与客户端通信线程
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
     * 将自己上线或下线的消息通知好友
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
                if(!id.equals(uid)){//不用通知自己
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
                //判断消息类型
                if(msg.getType() == MsgType.GET_ONLINE_FRIENDS) {
                    msg.setType(MsgType.RET_ONLINE_FRIENDS);
                    msg.setGetterId(msg.getSenderId());
                    msg.setContent(MultiToClientThread.getOnLineList());
                    ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getGetterId()).client.getOutputStream());
                    output.writeObject(msg);
                    System.out.println("返回列表成功");
                }else if(msg.getType() == MsgType.COMMON_MESSAGE) {
                    ServerMesgIdentifier thread = MultiToClientThread.getClientThread(msg.getGetterId());//找到接收者的线程
                    if(thread!=null) {
                    	ObjectOutputStream output = new ObjectOutputStream(thread.client.getOutputStream());
                        output.writeObject(msg);
                        System.out.println("转发成功");
                    }
                }else if(msg.getType() == MsgType.UNLOAD_LOGIN) {
                    String fromId = msg.getSenderId();
                    //结束此线程
                    myStop();
                    MultiToClientThread.removeClientThread(fromId);
                    notifyOthers(fromId);
                    System.out.println(fromId+" 退出登录");
                    ServerPage.showMsg("用户"+fromId+"退出登录！");
                }
                else if(msg.getType()==MsgType.GET_OFFLINE_MEAG) {
                  ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                  msg.setType(MsgType.OFFLINE_MESG);
                  msg.setContent(UsingDB.searchLxmessage(msg.getSenderId(),msg.getGetterId()));
                  output.writeObject(msg);
                }else if(msg.getType()==MsgType.SEND_OFFLINE_MESG) {
                	UsingDB.addOneMesg(msg.getContent(), msg.getSenderId(), msg.getGetterId(),msg.getSendTime());
                    System.out.println(msg.getSenderId()+" 添加了一条离线消息");
                }
                else if(msg.getType()==MsgType.JUDGE_STATE) {
                    ServerMesgIdentifier thread = MultiToClientThread.getClientThread(msg.getGetterId());//找到接收者的线程
                    if(null == thread) {
                    	//通知发送者好友不在线
                        ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                        msg.setType(MsgType.NOT_ONLINE);
                        output.writeObject(msg);
                        System.out.println("通知对方不在线成功");
                    }
                    else {
                    	ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                        msg.setType(MsgType.IS_ONLINE);
                        output.writeObject(msg);
                        System.out.println("通知对方在线成功");
                    }
                }
                else if(msg.getType()==MsgType.ADD_FRIEND) {
                	UsingDB.addFriend(msg.getSenderId(), msg.getSenderName(), msg.getGetterId(), msg.getContent());
                	Message msg1=new Message();
                	msg1.setType(MsgType.REPRESH_FRIENDS_LIST);
                	msg1.setContent(UsingDB.ref(msg.getSenderId()));
                	ObjectOutputStream output = new ObjectOutputStream(MultiToClientThread.getClientThread(msg.getSenderId()).client.getOutputStream());
                    output.writeObject(msg);
                    System.out.println("刷新好友列表成功");
                	
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}