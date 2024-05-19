
//商品展示的界面
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import client.ChatPage;
import client.FriendPage;
import client.LoginUser;
import client.MultiChatFrame;
import client.MultiToServerThread;
import common.Message;
import common.MsgType;
import database.Products;
import database.ProductsDao;
import database.User;
import database.UserDao;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SearchProducts extends JFrame {

	private JTable table;
	private JTextField namekuang;
	private JTextField jiagekuang;
	private JTextField fabuzhekuang;
	private JTextField leixingkuang;
	private JTextField soumaikuang;
	private JTextField sousuokuang;
	private JPanel panel_1;
	private JLabel lblNewLabel_2;
	private List<User> getUserList;
	private JTextArea jieshaokuang;
	public static User user;
	private int idd;
	private JScrollPane scrollPane_1 ;

	
	/**
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SearchProducts(User user) {
		this.user=(User) user;
		setResizable(false);

		setTitle("\u5546\u54C1\u641C\u7D22");
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 22));
		setBounds(100, 100, 811, 671);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 84, 740, 278);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedTableRow(arg0);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5E8F\u53F7", "\u5546\u54C1\u540D", "\u53D1\u5E03\u8005", "\u4EF7\u683C", "\u53D1\u5E03\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane.setViewportView(table);

		setTable(new Products());

		JPanel panel = new JPanel();
		panel.setBounds(28, 372, 740, 234);
		panel.setBorder(new TitledBorder(null, "\u5546\u54C1\u8BE6\u60C5", TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 18), null));
		
		JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D");
		lblNewLabel.setBounds(34, 24, 74, 33);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		namekuang = new JTextField();
		namekuang.setBounds(118, 29, 136, 21);
		namekuang.setEditable(false);
		namekuang.setFont(new Font("宋体", Font.PLAIN, 18));
		namekuang.setColumns(20);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u4ECB\u7ECD");
		label_1.setBounds(34, 60, 94, 24);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("\u4EF7\u683C");
		label_2.setBounds(301, 30, 94, 21);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("\u53D1\u5E03\u8005");
		label_3.setBounds(301, 60, 94, 25);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_4 = new JLabel("\u7C7B\u578B");
		label_4.setBounds(301, 94, 94, 24);
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_5 = new JLabel("\u552E\u5356\u65B9\u5F0F");
		label_5.setBounds(301, 128, 94, 21);
		label_5.setFont(new Font("宋体", Font.PLAIN, 18));
		
		jieshaokuang = new JTextArea();
		jieshaokuang.setLineWrap(true);
		jieshaokuang.setBounds(118, 60, 136, 104);
		jieshaokuang.setEditable(false);
		
		jiagekuang = new JTextField();
		jiagekuang.setBounds(380, 29, 136, 21);
		jiagekuang.setFont(new Font("宋体", Font.PLAIN, 18));
		jiagekuang.setEditable(false);
		jiagekuang.setColumns(20);
		
		fabuzhekuang = new JTextField();
		fabuzhekuang.setBounds(380, 61, 136, 21);
		fabuzhekuang.setFont(new Font("宋体", Font.PLAIN, 18));
		fabuzhekuang.setEditable(false);
		fabuzhekuang.setColumns(20);
		
		leixingkuang = new JTextField();
		leixingkuang.setBounds(380, 95, 136, 21);
		leixingkuang.setFont(new Font("宋体", Font.PLAIN, 18));
		leixingkuang.setEditable(false);
		leixingkuang.setColumns(20);
		
		soumaikuang = new JTextField();
		soumaikuang.setBounds(380, 128, 136, 21);
		soumaikuang.setFont(new Font("宋体", Font.PLAIN, 18));
		soumaikuang.setEditable(false);
		soumaikuang.setColumns(20);
		
		JButton button = new JButton("\u8D2D\u4E70\u6B64\u5546\u54C1");
		button.setBounds(34, 182, 174, 31);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyit();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		
		//与发布者交流
		JButton button_1 = new JButton("\u4E0E\u53D1\u5E03\u8005\u4EA4\u6D41");
		button_1.setBounds(301, 179, 171, 31);
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fid=String.valueOf(connectAct());
				if(!judgefriend(getOnlyUserNameById(connectAct()))) {
					Message msg=new Message();
					msg.setSenderId(user.getIdString(user.getId()));
					msg.setSenderName(user.getName());
					msg.setGetterId(fid);
					msg.setContent(getOnlyUserNameById(connectAct()));
					msg.setType(MsgType.ADD_FRIEND);
		            ObjectOutputStream out;
					try {
						out = new ObjectOutputStream(MultiToServerThread.getThread(user.getIdString(user.getId())).getClient().getOutputStream());
						out.writeObject(msg);
					} catch (IOException e1) {e1.printStackTrace();}
					FriendPage.i++;
					FriendPage.ht.put("f"+FriendPage.i,getOnlyUserNameById(connectAct()));
//					UserDao aaa=new UserDao();
//					User use = null;
//					User bbb=new User();
//					String userName =user.getName();
//					String password =user.getPassword();
//					bbb.setName(userName);
//					bbb.setPassword(password);
//					use = aaa.login(bbb);
//		            Message msg1 = new LoginUser().sendLoginInfoToServer(Login.frame, user.getIdString(user.getId()) , user.getPassword());

				}
                String frameName = user.getIdString(user.getId())+fid;
                System.out.println(frameName);
                MultiChatFrame.addChatFrame(frameName, new ChatPage(user.getIdString(user.getId()), user.getName(), fid, getOnlyUserNameById(connectAct())));
			}
			
		});
		
		JButton button_3 = new JButton("\u67E5\u770B\u8BC4\u8BBA");
		button_3.setBounds(552, 179, 158, 31);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent me) {
				pinglunAct();
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		sousuokuang = new JTextField();
		sousuokuang.setBounds(161, 34, 412, 26);
		sousuokuang.setFont(new Font("宋体", Font.PLAIN, 18));
		sousuokuang.setColumns(10);
		
		JButton button_2 = new JButton("\u5546\u54C1\u641C\u7D22");
		button_2.setBounds(600, 35, 118, 25);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ge) {
				searchUser(ge);
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 18));
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0");
		label.setBounds(69, 36, 133, 23);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		getContentPane().add(label);
		getContentPane().add(sousuokuang);
		getContentPane().add(button_2);
		getContentPane().add(scrollPane);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(lblNewLabel);
		panel.add(namekuang);
		panel.add(label_2);
		panel.add(jiagekuang);
		panel.add(label_1);
		panel.add(jieshaokuang);
		panel.add(fabuzhekuang);
		panel.add(label_3);
		panel.add(label_4);
		panel.add(leixingkuang);
		panel.add(label_5);
		panel.add(soumaikuang);
		panel.add(button);
		panel.add(button_1);
		panel.add(button_3);
		
		panel_1 = new JPanel();
		panel_1.setBounds(556, 24, 154, 140);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(0, 0, 154, 140);
		panel_1.add(lblNewLabel_2);
		
//		scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(118, 60, 136, 101);
//		panel.add(scrollPane_1);
//		
//		jieshaokuang = new JTextArea();
//		jieshaokuang.setEditable(false);
//		scrollPane_1.setViewportView(jieshaokuang);
		

	}


	
	protected void pinglunAct() {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		int pid=(int) dft.getValueAt(table.getSelectedRow(), 0);
		ProductsDao ccc = new ProductsDao();
		Products ddd = new Products();
		Products eee = null;
		ddd.setId(pid);
		eee = ccc.search(ddd);
		ProductsComment aaaa= new ProductsComment(eee,user);
		ccc.closeDao();
		aaaa.setLocationRelativeTo(null);
		aaaa.setVisible(true);
		
	}



	protected void searchUser(ActionEvent ge) {
		// TODO 自动生成的方法存根
		Products aaa = new Products();
		aaa.setName(sousuokuang.getText().toString());
		setTable(aaa);

	}

	private void setTable(Products products) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		dft.setRowCount(0);
		ProductsDao productsDao = new ProductsDao();
		List<Products> productsList = productsDao.getProductsList(products);
		for (Products t : productsList) {
			Vector v = new Vector();
			v.add(t.getId());
			v.add(t.getName());
			v.add(getUserNameById(t.getUserid()));
			v.add(t.getPrice());
			v.add(t.getTime());
			dft.addRow(v);
		}
		productsDao.closeDao();
	}
	
	protected int connectAct() {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		int pid=(int) dft.getValueAt(table.getSelectedRow(), 0);
		ProductsDao ccc = new ProductsDao();
		Products ddd = new Products();
		Products eee = null;
		ddd.setId(pid);
		eee = ccc.search(ddd);
		int temp=eee.getUserid();//得到商品出售者的id
		ccc.closeDao();
		return temp;
		
	}
	private String getUserNameById(int id){
		UserDao classDao = new UserDao();
		String a = null;
		getUserList = classDao.getUserList(new User());
		for (User user : getUserList) {		
			if(user.getOnline()==0)  a="（离线）";
			if(user.getOnline()==1)  a="（在线）";
			if(user.getId() == id)return user.getName()+a;
		}
		classDao.closeDao();
		return "";
	}
	private String getOnlyUserNameById(int id){
		UserDao classDao = new UserDao();
		String a = null;
		getUserList = classDao.getUserList(new User());
		for (User user : getUserList) {		
			if(user.getOnline()==0)  a="（离线）";
			if(user.getOnline()==1)  a="（在线）";
			if(user.getId() == id)return user.getName();
		}
		classDao.closeDao();
		return "";
	}


	protected void selectedTableRow(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		namekuang.setText(dft.getValueAt(table.getSelectedRow(), 1).toString());
		fabuzhekuang.setText(dft.getValueAt(table.getSelectedRow(), 2).toString());
		jiagekuang.setText(dft.getValueAt(table.getSelectedRow(), 3).toString());
		int pid=(int) dft.getValueAt(table.getSelectedRow(), 0);
		ProductsDao ccc = new ProductsDao();
		Products ddd = new Products();
		Products eee = null;
		ddd.setId(pid);
		eee = ccc.search(ddd);
		leixingkuang.setText(eee.getType());
		if(eee.getAuction()==0)
		{
			soumaikuang.setText("非拍卖品");
		}else
		{
			soumaikuang.setText("拍卖品");
		}
		jieshaokuang.setText(eee.getIntroduction());


		ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+eee.getPhoto());//调取数据库中的图片地址
		  Image img = imageIcon.getImage();
	        img = img.getScaledInstance(154,140, Image.SCALE_DEFAULT);//固定大小
	        imageIcon.setImage(img);
		lblNewLabel_2.setIcon(new ImageIcon(img));
		ccc.closeDao();
	}
	
		
		
	protected void buyit() {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		idd=(int) dft.getValueAt(table.getSelectedRow(), 0);
		ProductsDao productsDao = new ProductsDao();
		Products products = new Products();
		User user = (User)MainPage.user;
		int i=user.getId();
		products.setIsbuy(0);
		products.setBuyerid(i);
		products.setId(idd);
		productsDao.update(products);
		JOptionPane.showMessageDialog(this, "恭喜您，成功购买此商品");
		productsDao.closeDao();
		setTable(new Products());
	}
	
	
	protected boolean judgefriend(String f) {
		int i=0;
		int l=Login.fstr.length;
		while(i<l) {
			if(f==Login.fstr[i]) {
				return true;
			}
			i++;
		}
		return false;
	}
}


