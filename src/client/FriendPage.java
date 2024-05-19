package client;

import common.Message;
import common.MsgType;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * 登录成功后的主页面，显示好友列表，未在线好友头像灰色，
 * 双击某好友即可打开与其聊天界面
 * 点击退出按钮即可退出登录
 */
public class FriendPage extends JFrame implements ActionListener,MouseListener,TreeModelListener,WindowListener{

	private static final long serialVersionUID = 1L;
    private Container c;//本窗口面板
    private Point tmp,loc;//记录位置
	private boolean isDragged = false;//是否拖拽
    private static String ownerId;//本人qq
    private String myName;//本人昵称
    private JTree jtree;//树组件显示好友列表
    public static String[] fri;
    public static Hashtable<String,Object> ht;
    public static int i=0;

	public FriendPage(String name, String ownerId, Message msg) {
	    this.ownerId = ownerId;
	    this.myName = name;
		//获取本窗体容器
		c = this.getContentPane();
		//设置窗体大小
		//this.setSize(250,600);
		this.addWindowListener(this);
		//设置布局
		c.setLayout(null);
		//qq头像
		JLabel jbl_photo = new JLabel(new ImageIcon("src/chatimage/peronl.png"));
		jbl_photo.setBounds(20, 8, 78, 78);
		c.add(jbl_photo);
		//qq昵称
		JLabel jbl_qqName = new JLabel(name+"("+ownerId+")");
		jbl_qqName.setBounds(100, 20, 68, 21);
		c.add(jbl_qqName);

//		//上半部分背景图
//		JLabel jbl_background = new JLabel(new ImageIcon("image/friendlist/beijing.png"));
//		jbl_background.setBounds(0, 0, 277, 156);
//		c.add(jbl_background);
		

		//显示好友列表
        initList(this, msg);
        
		this.setSize(250,600);


		//去除其定义装饰框
//		this.setUndecorated(true);
		//设置窗体可见
		this.setVisible(true); 
        //添加鼠标监听事件
        this.addMouseListener(new java.awt.event.MouseAdapter() { 
        	@Override
            public void mouseReleased(MouseEvent e) {  
                isDragged = false;  
                //拖拽结束图标恢复
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
            }  
        	@Override
            public void mousePressed(MouseEvent e) {  
        		//限定范围内可拖拽
            	if(e.getY()<30)
            	{
            		//获取鼠标按下位置
            		tmp = new Point(e.getX(), e.getY());  
            		isDragged = true;
            		//拖拽时更改鼠标图标
            		setCursor(new Cursor(Cursor.MOVE_CURSOR));  
            	}  
            }
        });  
   
        this.addMouseMotionListener(new MouseMotionAdapter() {  
        	@Override
            public void mouseDragged(MouseEvent e) {
                if (isDragged) {  
                	//设置鼠标与窗体相对位置不变
                	loc = new Point(getLocation().x + e.getX() - tmp.x,
                    getLocation().y + e.getY() - tmp.y);  
                    setLocation(loc);  
                }  
            }  

        });
	}

    /**
     * 将下线消息发送到服务器
     */
    public void sendUnloadMsgToServer() {
	    Message msg = new Message();
	    msg.setSenderId(ownerId);
	    msg.setType(MsgType.UNLOAD_LOGIN);
        try {
            ClientMesgIdentifier th = MultiToServerThread.getThread(ownerId);
            ObjectOutputStream out = new ObjectOutputStream(th.getClient().getOutputStream());
            out.writeObject(msg);
            //结束线程
            th.myStop();
            MultiToServerThread.removeThread(ownerId);
            this.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void fakeUnload() {
    	
    }
    /**
     * 以树形结构显示全部好友列表
     * @param msg
     */
	public void initList(JFrame f, Message msg){
        //用Hashtable创建jtree显示好友列表
        ht = new Hashtable<>();
        fri= msg.getContent().split(" ");
        //ht.put("我的好友",fri);
        int l =fri.length;
        for(i=0;i<l;i++) {
        	ht.put("f"+i,fri[i]);
        }
        jtree=new JTree(fri);
        jtree.setCellRenderer(new ManageFriendList(msg));
        jtree.putClientProperty("JTree.lineStyle","None");

        jtree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    JTree tree = (JTree) e.getSource();
                    TreePath path = tree.getSelectionPath();
                    if(null != path){
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if(node.isLeaf()){
                            String[] info = node.toString().split("\\(");
                            String friendId = info[1].substring(0,info[1].length()-1);//取出id号
                            String frameName = ownerId+friendId;
                            if(MultiChatFrame.getChatFrame(frameName) == null){
                                System.out.println("添加frameName="+frameName);
                                MultiChatFrame.addChatFrame(frameName, new ChatPage(ownerId, myName, friendId, info[0]));
                            }else{
                                JOptionPane.showMessageDialog(f,"该窗口已存在!");
                            }
                        }
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(jtree);
        scrollPane.setBounds(20, 100, 274, 422);
        c.add(scrollPane);
    }
	
    /**
     * 刷新在线好友列表
     * @param msg
     */
    public void updateOnlineFriends(Message msg) {
        this.jtree.setCellRenderer(new ManageFriendList(msg));
    }

	@Override
	public void treeNodesChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
//		ChatPage.islx=true;
//        sendUnloadMsgToServer();
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

}

