
//用于修改用户的密码

package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;


import database.StringUtil;
import database.User;
import database.UserDao;

public class EditPassword extends JFrame {
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditPassword frame = new EditPassword();
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
	public EditPassword() {
		setResizable(false);
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 427, 370);
		
		JLabel label = new JLabel("\u5F53\u524D\u7528\u6237\uFF1A");
		label.setBounds(65, 56, 101, 33);
		label.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel label_1 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		label_1.setBounds(65, 99, 84, 39);
		label_1.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_2.setBounds(65, 150, 84, 33);
		label_2.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel label_3 = new JLabel("\u518D\u6B21\u8F93\u5165\uFF1A");
		label_3.setBounds(65, 203, 101, 33);
		label_3.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel currentUser = new JLabel();
		User user = MainPage.user;
		currentUser.setText("【用户】" + user.getName());
		currentUser.setBounds(195, 56, 160, 25);
		currentUser.setFont(new Font("宋体", Font.BOLD, 18));
		
		JButton queren = new JButton("\u786E\u8BA4");
		queren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent et) {
				submitEdit();
			}
		});
		queren.setBounds(43, 270, 97, 31);
		queren.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField_2.setText("");
				passwordField.setText("");
				passwordField_1.setText("");
			}
		});
		button_1.setBounds(158, 270, 97, 31);
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton quxiao = new JButton("\u53D6\u6D88");
		quxiao.setBounds(273, 270, 97, 31);
		quxiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ea) {
				dispose();
			}
		});
		quxiao.setFont(new Font("宋体", Font.PLAIN, 18));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 151, 206, 30);
		passwordField.setFont(new Font("宋体", Font.PLAIN, 18));
		passwordField.setColumns(20);
		getContentPane().setLayout(null);
		getContentPane().add(queren);
		getContentPane().add(button_1);
		getContentPane().add(quxiao);
		getContentPane().add(passwordField);
		getContentPane().add(label);
		getContentPane().add(currentUser);
		getContentPane().add(label_1);
		getContentPane().add(label_2);
		getContentPane().add(label_3);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		passwordField_1.setFont(new Font("宋体", Font.PLAIN, 18));
		passwordField_1.setBounds(158, 204, 207, 31);
		getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("宋体", Font.PLAIN, 18));
		passwordField_2.setColumns(20);
		passwordField_2.setBounds(158, 99, 206, 31);
		getContentPane().add(passwordField_2);

	}


	protected void submitEdit() {
		// TODO 自动生成的方法存根
		String oldPassword = passwordField_2.getText().toString();
		String newPassword = passwordField.getText().toString();
		String conformPassword = passwordField_1.getText().toString();
		if(StringUtil.isEmpty(oldPassword)){
			JOptionPane.showMessageDialog(this, "请填写旧密码！");
			return;
		}
		if(StringUtil.isEmpty(newPassword)){
			JOptionPane.showMessageDialog(this, "请填写新密码！");
			return;
		}
		if(StringUtil.isEmpty(conformPassword)){
			JOptionPane.showMessageDialog(this, "请确认新密码！");
			return;
		}
		if(!newPassword.equals(conformPassword)){
			JOptionPane.showMessageDialog(this, "两次密码输入不一致！");
			return;
		}
		UserDao userDao = new UserDao();
		User userTmp = new User();
		User user = (User)MainPage.user;
		userTmp.setName(user.getName());
		userTmp.setId(user.getId());
		userTmp.setPassword(oldPassword);
		JOptionPane.showMessageDialog(this, userDao.editPassword(userTmp, newPassword));
		userDao.closeDao();	
	}
}
