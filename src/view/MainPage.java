
//主界面
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import database.User;
import database.UserDao;

import java.awt.Window.Type;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import client.ChatPage;
import client.ClientMesgIdentifier;
import client.FriendPage;
import client.MultFriendList;
import client.MultiToServerThread;
import common.Message;
import common.MsgType;

public class MainPage extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	//public static User user;
	public static User user;




	public MainPage(User user) {
		this.user=(User) user;
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/images/lb.png")));
		setTitle("\u5C0F\u5C0F\u9E1F\u4EA4\u6613\u7CFB\u7EDF");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1092, 838);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u5546\u54C1\u7BA1\u7406");
		menu.setFont(new Font("宋体", Font.BOLD, 14));
		menuBar.add(menu);
		
		JMenuItem productsadd = new JMenuItem("\u5546\u54C1\u6DFB\u52A0");
		productsadd.setIcon(new ImageIcon(MainPage.class.getResource("/images/userType.png")));
		productsadd.setSelected(true);
		productsadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductsAdd studentManageFrm = new ProductsAdd(user);
				studentManageFrm.setVisible(true);
				studentManageFrm.setLocationRelativeTo(null);
			}
		});
		productsadd.setFont(new Font("宋体", Font.BOLD, 14));
		menu.add(productsadd);
		
		JMenuItem menuItem_1 = new JMenuItem("\u5546\u54C1\u5217\u8868");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent he) {
				SearchProducts student= new SearchProducts(user);
				student.setVisible(true);
				student.setLocationRelativeTo(null);

			}
		});
		menuItem_1.setFont(new Font("宋体", Font.BOLD, 14));
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u5546\u54C1\u7C7B\u522B");
		menu_1.setFont(new Font("宋体", Font.BOLD, 14));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u670D\u88C5\u7C7B");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				FuzhuangPage frame1 = new FuzhuangPage(user);
				frame1.setLocationRelativeTo(null);
				frame1.setVisible(true);
			}
		});
		menuItem_2.setFont(new Font("宋体", Font.BOLD, 14));
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u4E66\u7C4D\u7C7B");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent be) {
				ShujiPage frame2 = new ShujiPage(user);
				frame2.setVisible(true);
				frame2.setLocationRelativeTo(null);

			}
		});
		menuItem_3.setFont(new Font("宋体", Font.BOLD, 14));
		menu_1.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("\u4F53\u80B2\u7C7B");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ce) {
				TiyuPage frame3 = new TiyuPage(user);
				frame3.setVisible(true);
				frame3.setLocationRelativeTo(null);
		
			}
		});
		menuItem_4.setFont(new Font("宋体", Font.BOLD, 14));
		menu_1.add(menuItem_4);
		
		JMenuItem menuItem_6 = new JMenuItem("\u98DF\u54C1\u7C7B");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent de) {
				ShipinPage frame4 = new ShipinPage(user);
				frame4.setVisible(true);
				frame4.setLocationRelativeTo(null);
		
			}
		});
		menuItem_6.setFont(new Font("宋体", Font.BOLD, 14));
		menu_1.add(menuItem_6);
		
		JMenuItem menuItem_5 = new JMenuItem("\u5316\u5986\u54C1\u7C7B");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent fe) {
				HuazhuangpinPage frame5 = new HuazhuangpinPage(user);
				frame5.setVisible(true);
				frame5.setLocationRelativeTo(null);
			}
		});
		menuItem_5.setFont(new Font("宋体", Font.BOLD, 14));
		menu_1.add(menuItem_5);
		
		JMenuItem menuItem_7 = new JMenuItem("\u6570\u7801\u4EA7\u54C1\u7C7B");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ge) {
				ShumachanpinPage frame6 = new ShumachanpinPage(user);
				frame6.setVisible(true);
				frame6.setLocationRelativeTo(null);

			}
		});
		menuItem_7.setFont(new Font("宋体", Font.BOLD, 14));
		menu_1.add(menuItem_7);
		
		JMenuItem menuItem_8 = new JMenuItem("\u5176\u4ED6\u7C7B");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent he) {
				QitaPage frame7 = new QitaPage(user);
				frame7.setVisible(true);
				frame7.setLocationRelativeTo(null);
		
			}
		});
		menuItem_8.setFont(new Font("宋体", Font.BOLD, 14));
		menu_1.add(menuItem_8);
		
		JMenu mnNewMenu = new JMenu("\u7CFB\u7EDF\u8BBE\u7F6E");
		mnNewMenu.setFont(new Font("宋体", Font.BOLD, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem_9 = new JMenuItem("\u4FEE\u6539\u4FE1\u606F");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditMessage editmessage = new EditMessage(user);
				editmessage.setLocationRelativeTo(null);
				editmessage.setVisible(true);

			}
		});
		menuItem_9.setEnabled(true);
		menuItem_9.setFont(new Font("宋体", Font.BOLD, 14));
		mnNewMenu.add(menuItem_9);
		
		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditPassword editpassword = new EditPassword();
				editpassword.setVisible(true);
				editpassword.setLocationRelativeTo(null);
				
			}
		});
		menuItem.setFont(new Font("宋体", Font.BOLD, 14));
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_10 = new JMenuItem("\u672A\u88AB\u8D2D\u4E70\u8BB0\u5F55");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent je) {
				NotBuyRecord buy = new NotBuyRecord(user);
				buy.setLocationRelativeTo(null);
				buy.setVisible(true);

			}
		});
		menuItem_10.setFont(new Font("宋体", Font.BOLD, 14));
		mnNewMenu.add(menuItem_10);
		
		JMenuItem menuItem_12 = new JMenuItem("\u8D2D\u4E70\u8BB0\u5F55");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyRecord add1 = new BuyRecord(user);
				add1.setLocationRelativeTo(null);
				add1.setVisible(true);
			}
		});
		
		JMenuItem menuItem_14 = new JMenuItem("\u5DF2\u88AB\u8D2D\u4E70\u8BB0\u5F55");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HaveBuyRecord add2 = new HaveBuyRecord(user);
				add2.setLocationRelativeTo(null);
				add2.setVisible(true);
			}
		});
		menuItem_14.setFont(new Font("宋体", Font.BOLD, 14));
		mnNewMenu.add(menuItem_14);
		menuItem_12.setFont(new Font("宋体", Font.BOLD, 14));
		mnNewMenu.add(menuItem_12);
		
		//交流
		JMenu menu_2 = new JMenu("\u4EA4\u6D41\u8BAF\u606F");
		menu_2.setFont(new Font("宋体", Font.BOLD, 14));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_11 = new JMenuItem("\u6D88\u606F");
		menuItem_11.setFont(new Font("宋体", Font.BOLD, 14));
		menu_2.add(menuItem_11);
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    FriendPage fl = new FriendPage(Login.fstr[0], user.getIdString(user.getId()), Login.fmsg);//进入列表界面
	                MultFriendList.addFriendListFrame(user.getIdString(user.getId()),fl);
	                //发送获取在线好友信息包
	                getOnlineFriends(user.getIdString(user.getId()));
			}
			public void getOnlineFriends(String fromId){
		        Message msg = new Message();
		        msg.setType(MsgType.GET_ONLINE_FRIENDS);
		        msg.setSenderId(fromId);
		        try {
		            ObjectOutputStream out = new ObjectOutputStream(MultiToServerThread.getThread(fromId).getClient().getOutputStream());
		            out.writeObject(msg);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		});
		
		
		JMenu menu_3 = new JMenu("\u5237\u65B0");
		menu_3.setFont(new Font("宋体", Font.BOLD, 14));
		menuBar.add(menu_3);
		
		JMenuItem mntmJiemianshuaxin = new JMenuItem("\u754C\u9762\u5237\u65B0");
		mntmJiemianshuaxin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDao aaa=new UserDao();
				User use = null;
				User bbb=new User();
				String userName =user.getName();
				String password =user.getPassword();
				bbb.setName(userName);
				bbb.setPassword(password);
				use = aaa.login(bbb);
				MainPage frame = new MainPage(use);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);	
				aaa.closeDao();
				//Client client=new Client();
			    dispose();
			}
		});
		mntmJiemianshuaxin.setFont(new Font("宋体", Font.BOLD, 14));
		menu_3.add(mntmJiemianshuaxin);
		
		JMenu menu_4 = new JMenu("\u9000\u51FA");
		menu_4.setFont(new Font("宋体", Font.BOLD, 14));
		menuBar.add(menu_4);
		
		JMenuItem menuItem_13 = new JMenuItem("\u5B89\u5168\u9000\u51FA");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
		              tuichuAct();
			}
		});
		menuItem_13.setFont(new Font("宋体", Font.BOLD, 14));
		menu_4.add(menuItem_13);

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		



	}




	protected void tuichuAct() {
		// TODO 自动生成的方法存根
		String info="您真的要残忍离开小小鸟交易中心吗？";
		String[] buttons = {"迫不及待回去看看!","心情不好残忍离开!"};
		int ret = JOptionPane.showOptionDialog(this, info, "安全退出", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, buttons, null);
		if(ret == 0){
			//采用Java 调用系统浏览器打开制定
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(this, "你真是狠心，坏淫！");
			UserDao userDao = new UserDao();
			int aa=user.getId();
		    User aaa= new User();
		    aaa.setId(aa);
		    aaa.setOnline(0);
			dispose();
		    userDao.online(aaa);
		    userDao.closeDao();
		    ChatPage.islx=true;
	        sendUnloadMsgToServer();
		}
	}
	
	 public void sendUnloadMsgToServer() {
		    Message msg = new Message();
		    msg.setSenderId(user.getIdString(user.getId()));
		    msg.setType(MsgType.UNLOAD_LOGIN);
	        try {
	            ClientMesgIdentifier th = MultiToServerThread.getThread(user.getIdString(user.getId()));
	            ObjectOutputStream out = new ObjectOutputStream(th.getClient().getOutputStream());
	            out.writeObject(msg);
	            //结束线程
	            th.myStop();
	            MultiToServerThread.removeThread(user.getIdString(user.getId()));
	            this.dispose();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.exit(0);
	    }
	
}

