//设计用于判断商品是否为拍卖品，但是没用上


package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class AutionPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutionPage frame = new AutionPage();
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
	public AutionPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u662F\u5426\u4E3A\u62CD\u5356\u5546\u54C1\uFF1F");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(104, 53, 156, 30);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("\u662F");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(43, 124, 97, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5426");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(217, 124, 97, 30);
		contentPane.add(btnNewButton_1);
	}
}
