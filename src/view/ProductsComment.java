//评论商品的界面，展示评论

package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import database.Comment;
import database.CommentDao;
import database.Products;
import database.ProductsDao;
import database.User;
import database.UserDao;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductsComment extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea ;
	private List<User> getUserList;
	public static User user;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ProductsComment(Products products,User user) {
		this.user=(User) user;
		setResizable(false);
		setTitle("\u5546\u54C1\u8BC4\u8BBA");
		setBounds(100, 100, 589, 477);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(43, 18, 58, 33);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u53D1\u5E03\u8005");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(227, 18, 58, 33);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setText(products.getName());
		textField.setEditable(false);
		textField.setFont(new Font("宋体", Font.PLAIN, 18));
		textField.setBounds(108, 18, 95, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		int a=products.getUserid();
		UserDao userDao=new UserDao();
		User user6=userDao.get(a);
		textField_1.setText(user6.getName());
		
		userDao.closeDao();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 18));
		textField_1.setBounds(295, 18, 95, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton button = new JButton("\u8BC4\u8BBA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				AddComment addcomment=new AddComment(products,user);
				addcomment.setLocationRelativeTo(null);
				addcomment.setVisible(true);
				dispose();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBounds(429, 24, 97, 23);
		getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 81, 479, 318);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		CommentDao commentDao=new CommentDao();
		Comment comment=new Comment();
		int b=products.getId();
		comment.setPid(b);
		List<Comment> commentList = null;
		commentList=commentDao.show(comment);

		String aa="";
		for (Comment t : commentList) {
			aa+=getUserNameById(t.getUid())+":"+t.getWord()+"\n";
			
		}
		textArea.setText(aa);
		
		
		commentDao.closeDao();
	}

	private String getUserNameById(int uid) {
		// TODO 自动生成的方法存根
		UserDao classDao = new UserDao();
		getUserList = classDao.getUserList(new User());
		for (User user : getUserList) {		
			if(user.getId() == uid) return user.getName();
		}
		classDao.closeDao();
		return "";
	}
}
