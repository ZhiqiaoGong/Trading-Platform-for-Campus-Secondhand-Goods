
package client;

import common.Message;
import common.MsgType;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �������
 */
public class ChatPage extends JFrame implements ActionListener,MouseListener,WindowListener {
	public static void main(String[] args) {
		new ChatPage(null,null,null,null);
	}//������

    private JPanel panel_north;//�����������
    private JLabel jbl_touxiang;//ͷ��
    private JLabel jbl_friendname;//��������

    //����������ʾ���
    private static JTextPane panel_Msg;

    private JPanel panel_south;//�ϲ��������
    public static JTextPane jtp_input;//��Ϣ������
    private JButton recorde_search,lxmesg_search;//�鿴��Ϣ��¼��ť
    private JButton btn_send, btn_close;//��Ϣ�������·��رպͷ��Ͱ�ť

    private JPanel panel_east;//�������
    private CardLayout cardLayout;//��Ƭ����
    //Ĭ�϶��������ʾһ��ͼ,�����ѯ�����¼��ť�л��������¼���
    private final JLabel label1 = new JLabel(new ImageIcon());
    private static JTextPane panel_Record;//�����¼��ʾ���
    private static JTextPane panel_lxmesg;

    public static boolean islx=false;//�Ƿ�Ϊ������Ϣ
    public static int offcount=0;
    public static int oncount=0;
    public static boolean changestate=false;
    private boolean isDragged = false;//�����ק���ڱ�־
    private Point frameLocation;//��¼�����λ��
    public static String myId;//�����˺�
    public static String myName;
    public static String friendId;//�����˺�
    private static String friendName;
    private DateFormat df;//���ڽ���

    public ChatPage(String myId, String myName, String friendId, String friendName) {

        ChatPage.myId = myId;
        ChatPage.friendId = friendId;
        ChatPage.myName = myName;
        ChatPage.friendName=friendName;
        df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
        //��ȡ��������
        Container c = this.getContentPane();
        //���ò���
        c.setLayout(null);

        //�������
        panel_north = new JPanel();
        panel_north.setBounds(0, 0, 729, 92);
        panel_north.setLayout(null);
        //��ӱ������
        c.add(panel_north);
        //���Ͻ�ͷ��
        jbl_touxiang = new JLabel(new ImageIcon("src/chatimage/peronl.png"));
        jbl_touxiang.setBounds(10, 10, 42, 42);
        panel_north.add(jbl_touxiang);
        //ͷ���ҷ���������ĶԷ�����
        jbl_friendname = new JLabel(friendName+"("+friendId+")");
        jbl_friendname.setBounds(62, 30, 105, 20);
        panel_north.add(jbl_friendname);
//        //���Ͻ���С����ť
//        btn_min = new JButton(new ImageIcon ("image/small.png"));
//        btn_min.addActionListener(e -> setExtendedState(JFrame.ICONIFIED));
//        btn_min.setBounds(668, 0, 30, 30);
//        panel_north.add(btn_min);
//        //���Ͻǹرհ�ť
//        btn_exit = new JButton(new ImageIcon ("image/clo.png"));
//        btn_exit.addActionListener(this);
//        btn_exit.setBounds(698, 0, 30, 30);
//        panel_north.add(btn_exit);

        //���ñ�����屳��ɫ
        panel_north.setBackground(new Color(22, 154, 228));

        //�в�����������ʾ����
        panel_Msg = new JTextPane();
        JScrollPane scrollPane_Msg = new JScrollPane(panel_Msg);
        scrollPane_Msg.setBounds(0, 92, 446, 270);
        c.add(scrollPane_Msg);

        //�ϲ����
        panel_south = new JPanel();
        panel_south.setBounds(0, 370, 446, 170);
        panel_south.setLayout(null);
        //����ϲ����
        c.add(panel_south);
        //����������
        jtp_input = new JTextPane();
        jtp_input.setBounds(0, 34, 446, 105);
        //��ӵ��ϲ����
        panel_south.add(jtp_input);

        //��ѯ�����¼
        recorde_search = new JButton("���������¼");    
        recorde_search.addActionListener(e-> {
            System.out.println("������������¼");
            cardLayout.show(panel_east, "jl");        
        });
        recorde_search.setBounds(180, 0, 120, 28);
        panel_south.add(recorde_search);
        //��ѯ������Ϣ
        lxmesg_search = new JButton("����������Ϣ");
        lxmesg_search.addActionListener(this);
        lxmesg_search.setBounds(320, 0, 120, 28);
        panel_south.add(lxmesg_search);
        
        
        
        
        
        //��Ϣ�رհ�ť
        btn_close = new JButton("�ر�(C)");//new ImageIcon("image/xxclo(1).png")
        btn_close.setBounds(277, 145, 77, 24);
        btn_close.addActionListener(this);
        panel_south.add(btn_close);
        //��Ϣ���Ͱ�ť
        btn_send = new JButton("����(S)");//new ImageIcon("image/xxsend(1).png")
        btn_send.addActionListener(this);
        btn_send.setBounds(370, 145, 77, 24);
        panel_south.add(btn_send);

        //�������(�����¼)
        panel_east = new JPanel();
        //��Ƭ����
        cardLayout = new CardLayout(2,2);
        panel_east.setLayout(cardLayout);
        panel_east.setBounds(444, 91, 285, 418);
        //��Ӷ������
        c.add(panel_east);
        //��ʾ�����¼���
        panel_Record = new JTextPane();
        panel_Record.setText("---�����¼---\n\n");
        JScrollPane scrollPane_Record = new JScrollPane(panel_Record);
        scrollPane_Record.setBounds(2, 2, 411, 410);
        //��ӵ��������
        panel_east.add(label1,"0");
        panel_east.add(scrollPane_Record,"jl");
        //��ʾ������Ϣ���
        setPanel_lxmesg(new JTextPane());
        getPanel_lxmesg().setText("---TA�����ҵ�������Ϣ---\n\n");
        JScrollPane scrollPane_lxmesg = new JScrollPane(getPanel_lxmesg());
        scrollPane_lxmesg.setBounds(2, 2, 411, 410);
        //��ӵ��������
        panel_east.add(scrollPane_lxmesg,"lx");


        //ע������¼�������
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //����ͷ�
                isDragged = false;
                //���ָ�
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                //��갴��
                //��ȡ�����Դ���λ��
                frameLocation = new Point(e.getX(),e.getY());
                isDragged = true;
                //����Ϊ�ƶ���ʽ
                if(e.getY() < 92)
                    setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        //ע������¼�������
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //ָ����Χ�ڵ��������ק
                if(e.getY() < 92){
                    //����������ק�ƶ�
                    if(isDragged) {
                        Point loc = new Point(getLocation().x+e.getX()-frameLocation.x,
                                getLocation().y+e.getY()-frameLocation.y);
                        //��֤�����Դ���λ�ò���,ʵ���϶�
                        setLocation(loc);
                    }
                }
            }
        });

        //this.setIconImage(new ImageIcon("image/login/Q.png").getImage());//�޸Ĵ���Ĭ��ͼ��
        this.setSize(728, 580);//���ô����С
        //this.setUndecorated(true);//ȥ���Դ�װ�ο�
        this.setVisible(true);//���ô���ɼ�
    	this.addWindowListener(this);


    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_send){
        	Message msg = new Message();
	        msg.setType(MsgType.JUDGE_STATE);
	        msg.setSenderId(ChatPage.myId);
	        msg.setGetterId(friendId);
	        try {
	        	ObjectOutput out = new ObjectOutputStream(MultiToServerThread.getThread(ChatPage.myId).getClient().getOutputStream());
	            out.writeObject(msg);
	        }catch (Exception e1) {
	            e1.printStackTrace();
	        }//�жϺ�������״̬ ��ѡ�������߻�������Ϣ
	        
	        	if(islx==false) {
	        		System.out.println("����");
	                sendMsg(this, ChatPage.myName);
	            }
	        	else if(islx==true){
	                Message msg1 = new Message();
	                msg1.setType(MsgType.SEND_OFFLINE_MESG);
	                msg1.setSenderId(ChatPage.myId);
	                msg1.setGetterId(friendId);
	                msg1.setContent(jtp_input.getText());
	                msg1.setSendTime(df.format(new Date()));
	                try {
	                	ObjectOutput out = new ObjectOutputStream(MultiToServerThread.getThread(ChatPage.myId).getClient().getOutputStream());
	                    out.writeObject(msg1);
	                    System.out.println("����������Ϣ�ɹ�");
	                    msg1.setSenderName(myName);
	                    msg1.setSendTime(df.format(new Date()));
	                    showMessage(msg1,true);
	                    jtp_input.setText("");
	                }catch (Exception e1) {
	                    e1.printStackTrace();
	                }

        	    }
	        }
        else if(e.getSource() == btn_close ) {
            MultiChatFrame.removeChatFrame(myId + friendId);
            System.out.println("remove chatFrame="+myId + friendId);
            this.dispose();
        }
        else if(e.getSource()==lxmesg_search) {
            cardLayout.show(panel_east, "lx");        
            System.out.println("�ɹ���ʾ������Ϣҳ��");
            Message msg1 = new Message();
            msg1.setType(MsgType.GET_OFFLINE_MEAG);
            msg1.setSenderId(ChatPage.myId);
            msg1.setGetterId(friendId);
            msg1.setSenderName(friendName);
            System.out.println("�ɹ����ͻ�ȡ������Ϣ����");
            try {
            	ObjectOutput out = new ObjectOutputStream(MultiToServerThread.getThread(ChatPage.myId).getClient().getOutputStream());
                out.writeObject(msg1);
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * ʵ����Ϣ����
     * @param f
     */
    public void sendMsg(JFrame f, String senderName){
        String str = jtp_input.getText();
        if(!str.equals("")){
            Message msg = new Message();
            msg.setType(MsgType.COMMON_MESSAGE);
            msg.setSenderId(ChatPage.myId);
            msg.setSenderName(senderName);
            msg.setGetterId(ChatPage.friendId);
            msg.setContent(str);
            msg.setSendTime(df.format(new Date()));
            try {
                ObjectOutput out = new ObjectOutputStream(MultiToServerThread.getThread(ChatPage.myId).getClient().getOutputStream());
                out.writeObject(msg);
                System.out.println("���ͳɹ�");
                showMessage(msg,true);
                jtp_input.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(f,"���ܷ��Ϳ����ݣ�");
        }
    }

    /**
     * �����յ�����Ϣ��ʾ����
     * @param msg
     */
    public static void showMessage(Message msg, boolean fromSelf) {
        showMessage(panel_Msg, msg, fromSelf);//����ʾ�������������
        showMessage(panel_Record, msg, fromSelf);//����ʾ�������¼���
    }

    /**
     * ����Ϣ������ʾ��ָ�����
     * @param jtp
     * @param msg
     * @param fromSelf
     */
    public static void showMessage(JTextPane jtp, Message msg, boolean fromSelf) {
        //������ʾ��ʽ
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attrset, "����");
        StyleConstants.setFontSize(attrset,14);
        Document docs = jtp.getDocument();
        String info = null;
        try {
            if(fromSelf){//����ȥ����Ϣ����
                info = "��  ";
                StyleConstants.setForeground(attrset, Color.red);
                docs.insertString(docs.getLength(), info, attrset); StyleConstants.setForeground(attrset, Color.red);
                info = msg.getSendTime()+":\n";
                StyleConstants.setForeground(attrset, Color.black);
                docs.insertString(docs.getLength(), info, attrset);
                info = " "+msg.getContent()+"\n";
                StyleConstants.setFontSize(attrset,16);
                StyleConstants.setForeground(attrset, Color.red);
                docs.insertString(docs.getLength(), info, attrset);
            }else{//���յ�����Ϣ����
                info = msg.getSenderName()+"("+msg.getSenderId()+")  ";
                StyleConstants.setForeground(attrset, Color.blue);
                docs.insertString(docs.getLength(), info, attrset); StyleConstants.setForeground(attrset, Color.red);
                info = msg.getSendTime()+":\n";
                StyleConstants.setForeground(attrset, Color.black);
                docs.insertString(docs.getLength(), info, attrset);
                info = " "+msg.getContent()+"\n";
                StyleConstants.setFontSize(attrset,16);
                StyleConstants.setForeground(attrset, Color.blue);
                docs.insertString(docs.getLength(), info, attrset);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

	public static JTextPane getPanel_lxmesg() {
		return panel_lxmesg;
	}

	public static void setPanel_lxmesg(JTextPane panel_lxmesg) {
		ChatPage.panel_lxmesg = panel_lxmesg;
	}
	
	
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		MultiChatFrame.removeChatFrame(myId + friendId);
        System.out.println("remove chatFrame="+myId + friendId);
        this.dispose();
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}


}

