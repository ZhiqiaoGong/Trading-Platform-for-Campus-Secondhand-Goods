//注册界面
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.StringUtil;
import database.User;
import database.UserDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTextField;
	private JPasswordField PasswordTextField1;
	private JPasswordField PasswordTextField2;
	private JTextField ZhuanyeTextField;
	private JComboBox XiaoquComboBox;
	private ButtonGroup group;
	private JRadioButton ninteen;
	private JRadioButton eighteen;
	private JRadioButton seventeen;
	private JRadioButton sixteen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setTitle("\u5C0F\u5C0F\u9E1F\u4EA4\u6613\u7CFB\u7EDF");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/images/lb.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(475, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u5C0F\u5C0F\u9E1F\u4EA4\u6613\u4E2D\u5FC3");
		label.setBounds(121, 25, 287, 60);
		label.setFont(new Font("楷体", Font.PLAIN, 22));
		label.setIcon(new ImageIcon(Register.class.getResource("/images/\u767B\u9646\u56FE\u6807.png")));
		
		JLabel label_1 = new JLabel("\u7528 \u6237 \u540D");
		label_1.setIcon(new ImageIcon(Register.class.getResource("/images/\u4EBA\u6570\u7EDF\u8BA1.png")));
		label_1.setBounds(77, 106, 97, 22);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("\u5BC6  \u7801");
		label_2.setIcon(new ImageIcon(Register.class.getResource("/images/\u5BC6\u7801.png")));
		label_2.setBounds(79, 152, 95, 22);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("\u518D\u6B21\u8F93\u5165");
		label_3.setIcon(new ImageIcon(Register.class.getResource("/images/\u5BC6\u7801.png")));
		label_3.setBounds(79, 198, 95, 22);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_4 = new JLabel("\u6240\u5C5E\u6821\u533A");
		label_4.setBounds(80, 244, 94, 22);
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_5 = new JLabel("\u6240\u5C5E\u4E13\u4E1A");
		label_5.setBounds(76, 290, 94, 22);
		label_5.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_6 = new JLabel("\u6240\u5C5E\u5E74\u7EA7");
		label_6.setBounds(76, 337, 86, 22);
		label_6.setFont(new Font("宋体", Font.PLAIN, 18));
		
		UsernameTextField = new JTextField();
		UsernameTextField.setBounds(184, 103, 206, 28);
		UsernameTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		UsernameTextField.setColumns(20);
		
		PasswordTextField1 = new JPasswordField();
		PasswordTextField1.setBounds(184, 149, 206, 28);
		PasswordTextField1.setFont(new Font("宋体", Font.PLAIN, 18));
		PasswordTextField1.setColumns(20);
		
		PasswordTextField2 = new JPasswordField();
		PasswordTextField2.setBounds(184, 195, 206, 28);
		PasswordTextField2.setFont(new Font("宋体", Font.PLAIN, 18));
		PasswordTextField2.setColumns(20);
		
		XiaoquComboBox = new JComboBox();
		XiaoquComboBox.setBounds(185, 241, 205, 28);
		XiaoquComboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5174\u9686\u5C71\u6821\u533A", "\u5343\u4F5B\u5C71\u6821\u533A", "\u6D2A\u697C\u6821\u533A", "\u4E2D\u5FC3\u6821\u533A", "\u8F6F\u4EF6\u56ED\u6821\u533A", "\u5A01\u6D77\u6821\u533A", "\u9752\u5C9B\u6821\u533A", "\u8DB5\u7A81\u6CC9\u6821\u533A"}));
		XiaoquComboBox.setMaximumRowCount(4);
		XiaoquComboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		
		ZhuanyeTextField = new JTextField();
		ZhuanyeTextField.setBounds(184, 287, 206, 28);
		ZhuanyeTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		ZhuanyeTextField.setColumns(20);
		
		ninteen = new JRadioButton("2019\u7EA7");
		ninteen.setBounds(175, 333, 86, 31);
		ninteen.setFont(new Font("宋体", Font.PLAIN, 18));
		
		eighteen = new JRadioButton("2018\u7EA7");
		eighteen.setBounds(263, 333, 127, 31);
		eighteen.setFont(new Font("宋体", Font.PLAIN, 18));
		
		seventeen = new JRadioButton("2017\u7EA7");
		seventeen.setBounds(175, 382, 83, 31);
		seventeen.setFont(new Font("宋体", Font.PLAIN, 18));
		
		sixteen = new JRadioButton("2016\u7EA7");
		sixteen.setBounds(263, 382, 127, 31);
		sixteen.setFont(new Font("宋体", Font.PLAIN, 18));
		
		group = new ButtonGroup();
		group.add(ninteen);
		group.add(eighteen);
		group.add(seventeen);
		group.add(sixteen);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerAct();
			}
		});
		button.setBounds(77, 471, 97, 31);
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    dispose();
	                try {
						Login frame = new Login();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
			}
		});
		button_1.setBounds(311, 471, 97, 31);
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton button_2 = new JButton("\u91CD\u7F6E");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordTextField1.setText("");
				PasswordTextField2.setText("");
				UsernameTextField.setText("");
				ZhuanyeTextField.setText("");
                //单选框如何清空
				XiaoquComboBox.setSelectedIndex(0);
				group.clearSelection();
			}
		});
		button_2.setBounds(195, 471, 97, 31);
		button_2.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.setLayout(null);
		contentPane.add(button);
		contentPane.add(button_1);
		contentPane.add(button_2);
		contentPane.add(label);
		contentPane.add(label_1);
		contentPane.add(label_2);
		contentPane.add(label_3);
		contentPane.add(PasswordTextField2);
		contentPane.add(PasswordTextField1);
		contentPane.add(UsernameTextField);
		contentPane.add(label_5);
		contentPane.add(label_4);
		contentPane.add(ZhuanyeTextField);
		contentPane.add(XiaoquComboBox);
		contentPane.add(label_6);
		contentPane.add(ninteen);
		contentPane.add(eighteen);
		contentPane.add(seventeen);
		contentPane.add(sixteen);
		
		
	}

	protected void registerAct() {
		// TODO 自动生成的方法存根
		String studentName = UsernameTextField.getText().toString();
		String studentPassword1 =PasswordTextField1.getText().toString();
		String studentPassword2 =PasswordTextField2.getText().toString();
		String zhuanyeTextField = ZhuanyeTextField.getText().toString();
		if(StringUtil.isEmpty(studentName)){
			JOptionPane.showMessageDialog(this, "请填写用户名!");
			return;
		}
		if(StringUtil.isEmpty(studentPassword1)){
			JOptionPane.showMessageDialog(this, "请填写密码!");
			return;
		}
		if(StringUtil.isEmpty(studentPassword2)){
			JOptionPane.showMessageDialog(this, "请再次填写密码!");
			return;
		}
		if(!(studentPassword1.equals(studentPassword2))) {
			JOptionPane.showConfirmDialog(null, "两次输入的密码不同！", null, JOptionPane.OK_OPTION);
		}	
		if(StringUtil.isEmpty(zhuanyeTextField)){
			JOptionPane.showMessageDialog(this, "请填写专业!");
			return;
		}
		if(!(ninteen.isSelected())&&!(eighteen.isSelected())&&!(seventeen.isSelected())&&!(sixteen.isSelected())){
			JOptionPane.showMessageDialog(this, "请填写年级!");
			return;
		}
		String xiaoqu = (String)XiaoquComboBox.getSelectedItem();
		int nianji = 0;
		if(ninteen.isSelected())
		{
			nianji=2019;
		}
		if(eighteen.isSelected())
		{
			nianji=2018;
		}
		if(seventeen.isSelected())
		{
			nianji=2017;
		}
		if(sixteen.isSelected())
		{
			nianji=2016;
		}
		
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		
		User user = new User();
		user.setName(studentName);
		user.setPassword(studentPassword1);
		user.setNianji(nianji);
		user.setXiaoqu(xiaoqu);
		user.setZhuanye(zhuanyeTextField);
		user.setZhuceriqi(date);
		
		
		UserDao studentDao = new UserDao();
		dispose();
		if(studentDao.addUser(user,studentName)){
			JOptionPane.showMessageDialog(this, "注册成功!");
			Login frame = new Login();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);			
		}else{
			JOptionPane.showMessageDialog(this, "注册失败!");
		}
		studentDao.closeDao();
		
	}	
}
