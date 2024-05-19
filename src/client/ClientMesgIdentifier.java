package client;

import common.Message;
import common.MsgType;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import client.ChatPage;
import client.FriendPage;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端与服务器通信线程，识别从服务器发送过来的消息
 */
public class ClientMesgIdentifier extends Thread{

    private Socket client;
    private volatile boolean isRunning;
    private DateFormat df=new SimpleDateFormat();

    public ClientMesgIdentifier(Socket client){
        this.client = client;
        this.isRunning = true;
    }

    public Socket getClient() {
        return client;
    }

    public void myStop(){
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while(isRunning){
                ObjectInputStream input = new ObjectInputStream(this.client.getInputStream());
                Message msg = (Message) input.readObject();
                //判断消息类型
                if(msg.getType() == MsgType.RET_ONLINE_FRIENDS) {
                    String uid = msg.getGetterId();
                    System.out.println("find FriendList uid="+uid);
                    FriendPage fl = MultFriendList.getFriendListFrame(uid);
                    //第一个用户上线通知其他用户时，其他用户不在线，这里为空
                    if(null != fl){
                        fl.updateOnlineFriends(msg);
                    }
                }else if(msg.getType() == MsgType.COMMON_MESSAGE) {
                    String frameName = msg.getGetterId()+msg.getSenderId();
                    System.out.println("find Chat framename="+frameName);
                    MultiChatFrame.getChatFrame(frameName).showMessage(msg,false);
                }else if(msg.getType() == MsgType.NOT_ONLINE) {
                	if(ChatPage.offcount<1) {
                		ChatPage chat = MultiChatFrame.getChatFrame(msg.getSenderId() + msg.getGetterId());
                        JOptionPane.showMessageDialog(chat, "该好友未上线，您现在可以发送离线信息哦！");
                        ChatPage.offcount++;
                        ChatPage.islx=true;//修改对方在线状态为离线
                        ChatPage.changestate=true;
                        System.out.println(msg.getGetterId()+"状态修改为离线");
                	}
                	else {
                        ChatPage.changestate=false;
                	}
                }else if(msg.getType() == MsgType.IS_ONLINE) {
                	if(ChatPage.offcount>0&&ChatPage.oncount<1) {
                		ChatPage chat = MultiChatFrame.getChatFrame(msg.getSenderId() + msg.getGetterId());
                        JOptionPane.showMessageDialog(chat, "该好友上线啦，您现在可以开始在线聊天哦！");
                        ChatPage.oncount++;
                    	ChatPage.islx=false;//修改对方在线状态为在线
                        ChatPage.changestate=true;
                	}
                	else {
                        ChatPage.changestate=false;
                	}
                }else if(msg.getType() == MsgType.SERVER_CLOSE){
                    String toId = msg.getGetterId();
                    //自动下线
                    MultFriendList.getFriendListFrame(toId).sendUnloadMsgToServer();
                    MultiToServerThread.removeThread(toId);
                    MultFriendList.removeFriendListFrame(toId);
                }else if(msg.getType() ==MsgType.OFFLINE_MESG) {
                	if(msg.getContent()==null) {
                        ChatPage chat1 = MultiChatFrame.getChatFrame(msg.getSenderId() + msg.getGetterId());
                        JOptionPane.showMessageDialog(chat1, "TA还没有给您发送过离线消息哦");
                	}else {
//                		String[] lx=msg.getContent().split(" ");
//                		for(int i=0;lx[i]!=null;i++){
//                			String[] lxx=lx[i].split("/");
//                			Message m=new Message();
//                			m.setContent(lxx[0]);
//                			m.setSenderId(msg.getSenderId());
//                			m.setSenderName(msg.getSenderName());
//                			m.setSendTime(lxx[1]);
//                			ChatPage.showMessage(ChatPage.getPanel_lxmesg(),m, false);
//                		}
                		SimpleAttributeSet attrset = new SimpleAttributeSet();
                        StyleConstants.setFontFamily(attrset, "仿宋");
                        StyleConstants.setFontSize(attrset,14);
                        Document docs = ChatPage.getPanel_lxmesg().getDocument();
                        String info = msg.getContent();
                        StyleConstants.setForeground(attrset, Color.blue);
                        try {
							docs.insertString(docs.getLength(), info, attrset);
						} catch (BadLocationException e) {e.printStackTrace();} 
                        //ChatPage.getPanel_lxmesg().setText("---TA发给我的离线消息---\n\n"+msg.getContent());
                	}
                	System.out.println("离线消息显示成功");
                }
                else if(msg.getType()==MsgType.REPRESH_FRIENDS_LIST) {
                	FriendPage.fri=msg.getContent().split(" ");
                	
                    System.out.println("刷新好友列表成功");

                }
                
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
