
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
 * 聊天界面
 */
public class ChatPage extends JFrame implements ActionListener,MouseListener,WindowListener {
	public static void main(String[] args) {
		new ChatPage(null,null,null,null);
	}//测试用

    private JPanel panel_north;//北部区域面板
    private JLabel jbl_touxiang;//头像
    private JLabel jbl_friendname;//好友名称

    //聊天内容显示面板
    private static JTextPane panel_Msg;

    private JPanel panel_south;//南部区域面板
    public static JTextPane jtp_input;//消息输入区
    private JButton recorde_search,lxmesg_search;//查看消息记录按钮
    private JButton btn_send, btn_close;//消息输入区下方关闭和发送按钮

    private JPanel panel_east;//东部面板
    private CardLayout cardLayout;//卡片布局
    //默认东部面板显示一张图,点击查询聊天记录按钮切换到聊天记录面板
    private final JLabel label1 = new JLabel(new ImageIcon());
    private static JTextPane panel_Record;//聊天记录显示面板
    private static JTextPane panel_lxmesg;

    public static boolean islx=false;//是否为离线消息
    public static int offcount=0;
    public static int oncount=0;
    public static boolean changestate=false;
    private boolean isDragged = false;//鼠标拖拽窗口标志
    private Point frameLocation;//记录鼠标点击位置
    public static String myId;//本人账号
    public static String myName;
    public static String friendId;//好友账号
    private static String friendName;
    private DateFormat df;//日期解析

    public ChatPage(String myId, String myName, String friendId, String friendName) {

        ChatPage.myId = myId;
        ChatPage.friendId = friendId;
        ChatPage.myName = myName;
        ChatPage.friendName=friendName;
        df = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
        //获取窗口容器
        Container c = this.getContentPane();
        //设置布局
        c.setLayout(null);

        //北部面板
        panel_north = new JPanel();
        panel_north.setBounds(0, 0, 729, 92);
        panel_north.setLayout(null);
        //添加北部面板
        c.add(panel_north);
        //左上角头像
        jbl_touxiang = new JLabel(new ImageIcon("src/chatimage/peronl.png"));
        jbl_touxiang.setBounds(10, 10, 42, 42);
        panel_north.add(jbl_touxiang);
        //头像右方正在聊天的对方姓名
        jbl_friendname = new JLabel(friendName+"("+friendId+")");
        jbl_friendname.setBounds(62, 30, 105, 20);
        panel_north.add(jbl_friendname);
//        //右上角最小化按钮
//        btn_min = new JButton(new ImageIcon ("image/small.png"));
//        btn_min.addActionListener(e -> setExtendedState(JFrame.ICONIFIED));
//        btn_min.setBounds(668, 0, 30, 30);
//        panel_north.add(btn_min);
//        //右上角关闭按钮
//        btn_exit = new JButton(new ImageIcon ("image/clo.png"));
//        btn_exit.addActionListener(this);
//        btn_exit.setBounds(698, 0, 30, 30);
//        panel_north.add(btn_exit);

        //设置北部面板背景色
        panel_north.setBackground(new Color(22, 154, 228));

        //中部聊天内容显示部分
        panel_Msg = new JTextPane();
        JScrollPane scrollPane_Msg = new JScrollPane(panel_Msg);
        scrollPane_Msg.setBounds(0, 92, 446, 270);
        c.add(scrollPane_Msg);

        //南部面板
        panel_south = new JPanel();
        panel_south.setBounds(0, 370, 446, 170);
        panel_south.setLayout(null);
        //添加南部面板
        c.add(panel_south);
        //内容输入区
        jtp_input = new JTextPane();
        jtp_input.setBounds(0, 34, 446, 105);
        //添加到南部面板
        panel_south.add(jtp_input);

        //查询聊天记录
        recorde_search = new JButton("查找聊天记录");    
        recorde_search.addActionListener(e-> {
            System.out.println("点击查找聊天记录");
            cardLayout.show(panel_east, "jl");        
        });
        recorde_search.setBounds(180, 0, 120, 28);
        panel_south.add(recorde_search);
        //查询离线消息
        lxmesg_search = new JButton("查找离线消息");
        lxmesg_search.addActionListener(this);
        lxmesg_search.setBounds(320, 0, 120, 28);
        panel_south.add(lxmesg_search);
        
        
        
        
        
        //消息关闭按钮
        btn_close = new JButton("关闭(C)");//new ImageIcon("image/xxclo(1).png")
        btn_close.setBounds(277, 145, 77, 24);
        btn_close.addActionListener(this);
        panel_south.add(btn_close);
        //消息发送按钮
        btn_send = new JButton("发送(S)");//new ImageIcon("image/xxsend(1).png")
        btn_send.addActionListener(this);
        btn_send.setBounds(370, 145, 77, 24);
        panel_south.add(btn_send);

        //东部面板(聊天记录)
        panel_east = new JPanel();
        //卡片布局
        cardLayout = new CardLayout(2,2);
        panel_east.setLayout(cardLayout);
        panel_east.setBounds(444, 91, 285, 418);
        //添加东部面板
        c.add(panel_east);
        //显示聊天记录面板
        panel_Record = new JTextPane();
        panel_Record.setText("---聊天记录---\n\n");
        JScrollPane scrollPane_Record = new JScrollPane(panel_Record);
        scrollPane_Record.setBounds(2, 2, 411, 410);
        //添加到东部面板
        panel_east.add(label1,"0");
        panel_east.add(scrollPane_Record,"jl");
        //显示离线消息面板
        setPanel_lxmesg(new JTextPane());
        getPanel_lxmesg().setText("---TA发给我的离线消息---\n\n");
        JScrollPane scrollPane_lxmesg = new JScrollPane(getPanel_lxmesg());
        scrollPane_lxmesg.setBounds(2, 2, 411, 410);
        //添加到东部面板
        panel_east.add(scrollPane_lxmesg,"lx");


        //注册鼠标事件监听器
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //鼠标释放
                isDragged = false;
                //光标恢复
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                //鼠标按下
                //获取鼠标相对窗体位置
                frameLocation = new Point(e.getX(),e.getY());
                isDragged = true;
                //光标改为移动形式
                if(e.getY() < 92)
                    setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        //注册鼠标事件监听器
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //指定范围内点击鼠标可拖拽
                if(e.getY() < 92){
                    //如果是鼠标拖拽移动
                    if(isDragged) {
                        Point loc = new Point(getLocation().x+e.getX()-frameLocation.x,
                                getLocation().y+e.getY()-frameLocation.y);
                        //保证鼠标相对窗体位置不变,实现拖动
                        setLocation(loc);
                    }
                }
            }
        });

        //this.setIconImage(new ImageIcon("image/login/Q.png").getImage());//修改窗体默认图标
        this.setSize(728, 580);//设置窗体大小
        //this.setUndecorated(true);//去掉自带装饰框
        this.setVisible(true);//设置窗体可见
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
	        }//判断好友在线状态 再选择发送在线或离线消息
	        
	        	if(islx==false) {
	        		System.out.println("发送");
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
	                    System.out.println("发送离线消息成功");
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
            System.out.println("成功显示离线消息页面");
            Message msg1 = new Message();
            msg1.setType(MsgType.GET_OFFLINE_MEAG);
            msg1.setSenderId(ChatPage.myId);
            msg1.setGetterId(friendId);
            msg1.setSenderName(friendName);
            System.out.println("成功发送获取离线消息请求");
            try {
            	ObjectOutput out = new ObjectOutputStream(MultiToServerThread.getThread(ChatPage.myId).getClient().getOutputStream());
                out.writeObject(msg1);
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 实现消息发送
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
                System.out.println("发送成功");
                showMessage(msg,true);
                jtp_input.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(f,"不能发送空内容！");
        }
    }

    /**
     * 将接收到的消息显示出来
     * @param msg
     */
    public static void showMessage(Message msg, boolean fromSelf) {
        showMessage(panel_Msg, msg, fromSelf);//先显示到聊天内容面板
        showMessage(panel_Record, msg, fromSelf);//再显示到聊天记录面板
    }

    /**
     * 将消息内容显示到指定面板
     * @param jtp
     * @param msg
     * @param fromSelf
     */
    public static void showMessage(JTextPane jtp, Message msg, boolean fromSelf) {
        //设置显示格式
        SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attrset, "仿宋");
        StyleConstants.setFontSize(attrset,14);
        Document docs = jtp.getDocument();
        String info = null;
        try {
            if(fromSelf){//发出去的消息内容
                info = "我  ";
                StyleConstants.setForeground(attrset, Color.red);
                docs.insertString(docs.getLength(), info, attrset); StyleConstants.setForeground(attrset, Color.red);
                info = msg.getSendTime()+":\n";
                StyleConstants.setForeground(attrset, Color.black);
                docs.insertString(docs.getLength(), info, attrset);
                info = " "+msg.getContent()+"\n";
                StyleConstants.setFontSize(attrset,16);
                StyleConstants.setForeground(attrset, Color.red);
                docs.insertString(docs.getLength(), info, attrset);
            }else{//接收到的消息内容
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

