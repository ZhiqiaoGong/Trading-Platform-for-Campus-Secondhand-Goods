
//其他类的界面
package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.Products;
import database.ProductsDao;
import database.User;
import database.UserDao;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class QitaPage extends JFrame {
	private JTable table;
	private JTextField shangpinmingkuang;
	private JTextField jiagekuang;
	private JTextField fabukuang;
	private JTextField typekuang;
	private JTextField soumaikuang;
	private JTextArea jieshaokuang;
	private List<User> getUserList;
	private String Type="其他类";
	private int idd;
	public static User user;
	
	public JLabel phptokuang ;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public QitaPage(User user) {
		this.user=(User) user;
		setTitle("其他类");
//		setClosable(true);
//		setIconifiable(true);
		setResizable(false);
		setBounds(100, 100, 812, 645);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("其他类商品");
		label.setFont(new Font("宋体", Font.PLAIN, 22));
		label.setBounds(327, 10, 202, 34);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 54, 752, 278);
		getContentPane().add(scrollPane);
		
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
				"\u5E8F\u53F7", "\u5546\u54C1\u540D", "\u53D1\u5E03\u8005", "\u4EF7\u683C"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	
		scrollPane.setViewportView(table);
		setTable(new Products());
		
		JPanel namekuang = new JPanel();
		namekuang.setBorder(new TitledBorder(null, "\u5546\u54C1\u8BE6\u60C5", TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 18), null));
		namekuang.setBounds(28, 357, 752, 234);
		getContentPane().add(namekuang);
		
		JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D");
		lblNewLabel.setBounds(34, 34, 74, 33);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		shangpinmingkuang = new JTextField();
		shangpinmingkuang.setBounds(118, 39, 136, 21);
		shangpinmingkuang.setEditable(false);
		shangpinmingkuang.setFont(new Font("宋体", Font.PLAIN, 18));
		shangpinmingkuang.setColumns(20);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u4ECB\u7ECD");
		label_1.setBounds(34, 70, 94, 24);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("\u4EF7\u683C");
		label_2.setBounds(301, 40, 94, 21);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("\u53D1\u5E03\u8005");
		label_3.setBounds(301, 70, 94, 25);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_4 = new JLabel("\u7C7B\u578B");
		label_4.setBounds(301, 104, 94, 24);
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_5 = new JLabel("\u552E\u5356\u65B9\u5F0F");
		label_5.setBounds(301, 138, 94, 21);
		label_5.setFont(new Font("宋体", Font.PLAIN, 18));
		
		jieshaokuang = new JTextArea();
		jieshaokuang.setBounds(118, 70, 136, 104);
		jieshaokuang.setEditable(false);
		jieshaokuang.setLineWrap(true);
		
		jiagekuang = new JTextField();
		jiagekuang.setBounds(380, 39, 136, 21);
		jiagekuang.setFont(new Font("宋体", Font.PLAIN, 18));
		jiagekuang.setEditable(false);
		jiagekuang.setColumns(20);
		
		fabukuang = new JTextField();
		fabukuang.setBounds(380, 71, 136, 21);
		fabukuang.setFont(new Font("宋体", Font.PLAIN, 18));
		fabukuang.setEditable(false);
		fabukuang.setColumns(20);
		
		typekuang = new JTextField();
		typekuang.setBounds(380, 105, 136, 21);
		typekuang.setFont(new Font("宋体", Font.PLAIN, 18));
		typekuang.setEditable(false);
		typekuang.setColumns(20);
		
		soumaikuang = new JTextField();
		soumaikuang.setBounds(380, 138, 136, 21);
		soumaikuang.setFont(new Font("宋体", Font.PLAIN, 18));
		soumaikuang.setEditable(false);
		soumaikuang.setColumns(20);
		
		JButton button = new JButton("\u8D2D\u4E70\u6B64\u5546\u54C1");
		button.setBounds(34, 189, 163, 31);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ea) {
				buyitAct();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton button_1 = new JButton("\u4E0E\u53D1\u5E03\u8005\u4EA4\u6D41");
		button_1.setBounds(301, 189, 172, 31);
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton button_2 = new JButton("\u67E5\u770B\u8BC4\u8BBA");
		button_2.setBounds(549, 189, 160, 31);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent be) {
				commentAct();
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JPanel panel = new JPanel();
		panel.setBounds(564, 24, 163, 150);
		panel.setLayout(null);
		
		phptokuang = new JLabel();
		phptokuang.setBounds(0, 0, 154, 140);
		panel.add(phptokuang);
		namekuang.setLayout(null);
		namekuang.add(jieshaokuang);
		namekuang.add(label_1);
		namekuang.add(button);
		namekuang.add(button_1);
		namekuang.add(button_2);
		namekuang.add(fabukuang);
		namekuang.add(label_3);
		namekuang.add(label_4);
		namekuang.add(typekuang);
		namekuang.add(soumaikuang);
		namekuang.add(label_5);
		namekuang.add(panel);
		namekuang.add(lblNewLabel);
		namekuang.add(shangpinmingkuang);
		namekuang.add(jiagekuang);
		namekuang.add(label_2);

	}

	protected void commentAct() {
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
		ccc.closeDao();
		
	}

	protected void buyitAct() {
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
	}

	private void setTable(Products products) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		dft.setRowCount(0);
		ProductsDao productsDao = new ProductsDao();
		List<Products> productsList = productsDao.getProductsListA(Type);
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
	private String getUserNameById(int id){
		UserDao classDao = new UserDao();
		getUserList = classDao.getUserList(new User());
		for (User user : getUserList) {
			if(user.getId() == id)return user.getName();
		}
		classDao.closeDao();
		return "";
	}

	protected void selectedTableRow(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		shangpinmingkuang.setText(dft.getValueAt(table.getSelectedRow(), 1).toString());
		fabukuang.setText(dft.getValueAt(table.getSelectedRow(), 2).toString());
		jiagekuang.setText(dft.getValueAt(table.getSelectedRow(), 3).toString());
		int pid=(int) dft.getValueAt(table.getSelectedRow(), 0);
		ProductsDao ccc = new ProductsDao();
		Products ddd = new Products();
		Products eee = null;
		ddd.setId(pid);
		eee = ccc.search(ddd);
     	
		if(eee.getAuction()==0)
		{
			soumaikuang.setText("非拍卖品");
		}else
		{
			soumaikuang.setText("拍卖品");
		}
		jieshaokuang.setText(eee.getIntroduction());
		typekuang.setText(eee.getType());
		
		
		ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+eee.getPhoto());//调取数据库中的图片地址
		  Image img = imageIcon.getImage();
	        img = img.getScaledInstance(154,140, Image.SCALE_DEFAULT);//固定大小
	        imageIcon.setImage(img);
	        phptokuang.setIcon(new ImageIcon(img));
		ccc.closeDao();
	}
}
