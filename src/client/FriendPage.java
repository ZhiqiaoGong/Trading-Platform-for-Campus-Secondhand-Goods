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
 * ��¼�ɹ������ҳ�棬��ʾ�����б�δ���ߺ���ͷ���ɫ��
 * ˫��ĳ���Ѽ��ɴ������������
 * ����˳���ť�����˳���¼
 */
public class FriendPage extends JFrame implements ActionListener,MouseListener,TreeModelListener,WindowListener{

	private static final long serialVersionUID = 1L;
    private Container c;//���������
    private Point tmp,loc;//��¼λ��
	private boolean isDragged = false;//�Ƿ���ק
    private static String ownerId;//����qq
    private String myName;//�����ǳ�
    private JTree jtree;//�������ʾ�����б�
    public static String[] fri;
    public static Hashtable<String,Object> ht;
    public static int i=0;

	public FriendPage(String name, String ownerId, Message msg) {
	    this.ownerId = ownerId;
	    this.myName = name;
		//��ȡ����������
		c = this.getContentPane();
		//���ô����С
		//this.setSize(250,600);
		this.addWindowListener(this);
		//���ò���
		c.setLayout(null);
		//qqͷ��
		JLabel jbl_photo = new JLabel(new ImageIcon("src/chatimage/peronl.png"));
		jbl_photo.setBounds(20, 8, 78, 78);
		c.add(jbl_photo);
		//qq�ǳ�
		JLabel jbl_qqName = new JLabel(name+"("+ownerId+")");
		jbl_qqName.setBounds(100, 20, 68, 21);
		c.add(jbl_qqName);

//		//�ϰ벿�ֱ���ͼ
//		JLabel jbl_background = new JLabel(new ImageIcon("image/friendlist/beijing.png"));
//		jbl_background.setBounds(0, 0, 277, 156);
//		c.add(jbl_background);
		

		//��ʾ�����б�
        initList(this, msg);
        
		this.setSize(250,600);


		//ȥ���䶨��װ�ο�
//		this.setUndecorated(true);
		//���ô���ɼ�
		this.setVisible(true); 
        //����������¼�
        this.addMouseListener(new java.awt.event.MouseAdapter() { 
        	@Override
            public void mouseReleased(MouseEvent e) {  
                isDragged = false;  
                //��ק����ͼ��ָ�
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
            }  
        	@Override
            public void mousePressed(MouseEvent e) {  
        		//�޶���Χ�ڿ���ק
            	if(e.getY()<30)
            	{
            		//��ȡ��갴��λ��
            		tmp = new Point(e.getX(), e.getY());  
            		isDragged = true;
            		//��קʱ�������ͼ��
            		setCursor(new Cursor(Cursor.MOVE_CURSOR));  
            	}  
            }
        });  
   
        this.addMouseMotionListener(new MouseMotionAdapter() {  
        	@Override
            public void mouseDragged(MouseEvent e) {
                if (isDragged) {  
                	//��������봰�����λ�ò���
                	loc = new Point(getLocation().x + e.getX() - tmp.x,
                    getLocation().y + e.getY() - tmp.y);  
                    setLocation(loc);  
                }  
            }  

        });
	}

    /**
     * ��������Ϣ���͵�������
     */
    public void sendUnloadMsgToServer() {
	    Message msg = new Message();
	    msg.setSenderId(ownerId);
	    msg.setType(MsgType.UNLOAD_LOGIN);
        try {
            ClientMesgIdentifier th = MultiToServerThread.getThread(ownerId);
            ObjectOutputStream out = new ObjectOutputStream(th.getClient().getOutputStream());
            out.writeObject(msg);
            //�����߳�
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
     * �����νṹ��ʾȫ�������б�
     * @param msg
     */
	public void initList(JFrame f, Message msg){
        //��Hashtable����jtree��ʾ�����б�
        ht = new Hashtable<>();
        fri= msg.getContent().split(" ");
        //ht.put("�ҵĺ���",fri);
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
                            String friendId = info[1].substring(0,info[1].length()-1);//ȡ��id��
                            String frameName = ownerId+friendId;
                            if(MultiChatFrame.getChatFrame(frameName) == null){
                                System.out.println("���frameName="+frameName);
                                MultiChatFrame.addChatFrame(frameName, new ChatPage(ownerId, myName, friendId, info[0]));
                            }else{
                                JOptionPane.showMessageDialog(f,"�ô����Ѵ���!");
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
     * ˢ�����ߺ����б�
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
		// TODO �Զ����ɵķ������
		
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

