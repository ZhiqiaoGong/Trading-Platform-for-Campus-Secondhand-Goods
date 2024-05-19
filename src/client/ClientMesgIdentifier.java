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
 * �ͻ����������ͨ���̣߳�ʶ��ӷ��������͹�������Ϣ
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
                //�ж���Ϣ����
                if(msg.getType() == MsgType.RET_ONLINE_FRIENDS) {
                    String uid = msg.getGetterId();
                    System.out.println("find FriendList uid="+uid);
                    FriendPage fl = MultFriendList.getFriendListFrame(uid);
                    //��һ���û�����֪ͨ�����û�ʱ�������û������ߣ�����Ϊ��
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
                        JOptionPane.showMessageDialog(chat, "�ú���δ���ߣ������ڿ��Է���������ϢŶ��");
                        ChatPage.offcount++;
                        ChatPage.islx=true;//�޸ĶԷ�����״̬Ϊ����
                        ChatPage.changestate=true;
                        System.out.println(msg.getGetterId()+"״̬�޸�Ϊ����");
                	}
                	else {
                        ChatPage.changestate=false;
                	}
                }else if(msg.getType() == MsgType.IS_ONLINE) {
                	if(ChatPage.offcount>0&&ChatPage.oncount<1) {
                		ChatPage chat = MultiChatFrame.getChatFrame(msg.getSenderId() + msg.getGetterId());
                        JOptionPane.showMessageDialog(chat, "�ú����������������ڿ��Կ�ʼ��������Ŷ��");
                        ChatPage.oncount++;
                    	ChatPage.islx=false;//�޸ĶԷ�����״̬Ϊ����
                        ChatPage.changestate=true;
                	}
                	else {
                        ChatPage.changestate=false;
                	}
                }else if(msg.getType() == MsgType.SERVER_CLOSE){
                    String toId = msg.getGetterId();
                    //�Զ�����
                    MultFriendList.getFriendListFrame(toId).sendUnloadMsgToServer();
                    MultiToServerThread.removeThread(toId);
                    MultFriendList.removeFriendListFrame(toId);
                }else if(msg.getType() ==MsgType.OFFLINE_MESG) {
                	if(msg.getContent()==null) {
                        ChatPage chat1 = MultiChatFrame.getChatFrame(msg.getSenderId() + msg.getGetterId());
                        JOptionPane.showMessageDialog(chat1, "TA��û�и������͹�������ϢŶ");
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
                        StyleConstants.setFontFamily(attrset, "����");
                        StyleConstants.setFontSize(attrset,14);
                        Document docs = ChatPage.getPanel_lxmesg().getDocument();
                        String info = msg.getContent();
                        StyleConstants.setForeground(attrset, Color.blue);
                        try {
							docs.insertString(docs.getLength(), info, attrset);
						} catch (BadLocationException e) {e.printStackTrace();} 
                        //ChatPage.getPanel_lxmesg().setText("---TA�����ҵ�������Ϣ---\n\n"+msg.getContent());
                	}
                	System.out.println("������Ϣ��ʾ�ɹ�");
                }
                else if(msg.getType()==MsgType.REPRESH_FRIENDS_LIST) {
                	FriendPage.fri=msg.getContent().split(" ");
                	
                    System.out.println("ˢ�º����б�ɹ�");

                }
                
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
