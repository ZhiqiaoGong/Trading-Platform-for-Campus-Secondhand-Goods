//用户发布的商品已售出的统计界面

package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.Products;
import database.ProductsDao;
import database.User;
import database.UserDao;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class HaveBuyRecord extends JFrame {

	private JTable table;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_3;
	private List<User> getUserList;
	private JTextArea  textArea;
	private JTextField txtFuzhuanglei;
	public static User user;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public HaveBuyRecord(User user) {
		this.user=(User) user;
		setResizable(false);
		setTitle("\u8D2D\u4E70\u8BB0\u5F55");
		setBounds(100, 100, 640, 734);

		getContentPane().setLayout(null);
		
		JLabel label = new JLabel();
		User use = MainPage.user;
		label.setText("【用户】" + user.getName()+"已被购买的商品");
		label.setFont(new Font("宋体", Font.PLAIN, 22));
		label.setBounds(140, 10, 360, 34);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 54, 576, 278);
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

		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5546\u54C1\u8BE6\u60C5", TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 18), null));
		panel.setBounds(28, 357, 576, 187);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("宋体", Font.PLAIN, 18));
		textField.setColumns(20);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u4ECB\u7ECD");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("\u4EF7\u683C");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("\u53D1\u5E03\u8005");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_4 = new JLabel("\u7C7B\u578B");
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_5 = new JLabel("\u552E\u5356\u65B9\u5F0F");
		label_5.setFont(new Font("宋体", Font.PLAIN, 18));
		
	    textArea = new JTextArea();
		textArea.setEditable(false);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 18));
		textField_2.setEditable(false);
		textField_2.setColumns(20);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 18));
		textField_1.setEditable(false);
		textField_1.setColumns(20);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 18));
		textField_3.setEditable(false);
		textField_3.setColumns(20);
		
		txtFuzhuanglei = new JTextField();
		txtFuzhuanglei.setFont(new Font("宋体", Font.PLAIN, 18));
		txtFuzhuanglei.setEditable(false);
		txtFuzhuanglei.setColumns(20);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(79)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(84)
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
							.addGap(47)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(79)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(79)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(79)
									.addComponent(txtFuzhuanglei, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))))
					.addGap(3)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(1)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFuzhuanglei, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addGap(64))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u5408\u8BA1", TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.PLAIN, 18), null));
		panel_1.setBounds(28, 567, 576, 113);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_6 = new JLabel("一共卖出");
		label_6.setFont(new Font("宋体", Font.PLAIN, 18));
		label_6.setBounds(22, 30, 94, 30);
		panel_1.add(label_6);
		
		ProductsDao productsDao1 = new ProductsDao();
		int m=user.getId();
		double[] aaa=productsDao1.getProductsA(m);
		productsDao1.closeDao();
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setEditable(false);
		textField_4.setText(""+(int)aaa[0]);
		textField_4.setFont(new Font("宋体", Font.PLAIN, 18));
		textField_4.setBounds(117, 30, 139, 25);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_7 = new JLabel("\u4EF6\u5546\u54C1\uFF0C");
		label_7.setFont(new Font("宋体", Font.PLAIN, 18));
		label_7.setBounds(266, 34, 139, 22);
		panel_1.add(label_7);
		
		JLabel lblNewLabel_1 = new JLabel("共    赚");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(22, 70, 94, 34);
		panel_1.add(lblNewLabel_1);
		
		JLabel label_8 = new JLabel("\u5143\u3002");
		label_8.setFont(new Font("宋体", Font.PLAIN, 18));
		label_8.setBounds(266, 72, 139, 30);
		panel_1.add(label_8);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_5.setEditable(false);
		textField_5.setText(""+aaa[1]);
		textField_5.setFont(new Font("宋体", Font.PLAIN, 18));
		textField_5.setBounds(117, 70, 139, 25);
		panel_1.add(textField_5);
		textField_5.setColumns(10);



	}

	private void setTable(Products products) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) table.getModel();
		dft.setRowCount(0);
		ProductsDao productsDao = new ProductsDao();
		User user = (User)MainPage.user;
		int a=user.getId();
		List<Products> productsList = productsDao.getProductsListB(a,0);
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
		textField.setText(dft.getValueAt(table.getSelectedRow(), 1).toString());
		textField_1.setText(dft.getValueAt(table.getSelectedRow(), 2).toString());
		textField_2.setText(dft.getValueAt(table.getSelectedRow(), 3).toString());
		int pid=(int) dft.getValueAt(table.getSelectedRow(), 0);
		ProductsDao ccc = new ProductsDao();
		Products ddd = new Products();
		Products eee = null;
		ddd.setId(pid);
		eee = ccc.search(ddd);
		textField_3.setText(eee.getType());
		if(eee.getAuction()==0)
		{
			txtFuzhuanglei.setText("非拍卖品");
		}else
		{
			txtFuzhuanglei.setText("拍卖品");
		}
		textArea.setText(eee.getIntroduction());	
		ccc.closeDao();
	}
}

