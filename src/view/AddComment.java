//用来添加评论的界面

package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Comment;
import database.CommentDao;
import database.Products;
import database.StringUtil;
import database.User;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddComment extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	public static User user;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public AddComment(Products products,User user) {
		setResizable(false);
		this.user=(User) user;
		setTitle("\u8BC4\u8BBA");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 23, 332, 182);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCommentAct(products);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBounds(68, 230, 97, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		button_1.setBounds(292, 230, 97, 23);
		contentPane.add(button_1);
	}

	protected void addCommentAct(Products products) {
		// TODO 自动生成的方法存根
		String pinjia =textArea.getText().toString();
		if(StringUtil.isEmpty(pinjia)){
			JOptionPane.showMessageDialog(this, "请填写商品名!");
			return;
		}
		Comment aaa=new Comment();
		aaa.setPid(products.getId());
		aaa.setUid(user.getId());
		aaa.setWord(pinjia);
		CommentDao commentDao=new CommentDao();
		if(commentDao.addComment(aaa)){
			JOptionPane.showMessageDialog(this, "评价成功!");	
		}else{
			JOptionPane.showMessageDialog(this, "评价失败!");
		}
		commentDao.closeDao();
		dispose();
		ProductsComment aaaa= new ProductsComment(products,user);
		aaaa.setLocationRelativeTo(null);
		aaaa.setVisible(true);
	}
}
