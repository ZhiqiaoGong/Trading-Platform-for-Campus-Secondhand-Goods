//登录界面


package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.FriendPage;
import client.LoginUser;
import client.MultFriendList;
import client.MultiToServerThread;
import common.Message;
import common.MsgType;
import database.Client;
import database.StringUtil;
import database.User;
import database.UserDao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import common.*;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;
	public static Message fmsg;
	public static String [] fstr;
	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		setResizable(false);
		setTitle("\u5C0F\u5C0F\u9E1F\u4EA4\u6613\u4E2D\u5FC3");

		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/lb.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(513, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setIcon(new ImageIcon(Login.class.getResource("/images/userType.png")));
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		userNameTextField.setColumns(20);
		
		JLabel label_1 = new JLabel("\u5BC6 \u7801");
		label_1.setIcon(new ImageIcon(Login.class.getResource("/images/password.png")));
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		//登录
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAct();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton zhuce = new JButton("\u6CE8\u518C");
		zhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                    dispose();
                    Register frame = new Register();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
			}
		});
		zhuce.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("\u5C0F\u5C0F\u9E1F\u4EA4\u6613\u4E2D\u5FC3");
		label_2.setIcon(new ImageIcon(Login.class.getResource("/images/\u767B\u9646\u56FE\u6807.png")));
		label_2.setFont(new Font("楷体", Font.PLAIN, 22));
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(20);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(89)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(zhuce, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(passwordTextField, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(99)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(115))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(zhuce))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	
	protected void loginAct() {
		try{// TODO 自动生成的方法存根
		    String userName = userNameTextField.getText().toString();
		    String password = passwordTextField.getText().toString();
		    if(StringUtil.isEmpty(userName)){
		    	JOptionPane.showMessageDialog(this, "用户名不能为空！");
		    	return;
		    }
		    if(StringUtil.isEmpty(password)){
		    	JOptionPane.showMessageDialog(this, "密码不能为空！");
		    	return;
		    }
		    User user = null;
		    UserDao userDao = new UserDao();

			    
		    User userTmp = new User();
		    userTmp.setName(userName);
		    userTmp.setPassword(password);
		    user = userDao.login(userTmp);
		    
		    //+++
            Message msg = new LoginUser().sendLoginInfoToServer(this, user.getIdString(user.getId()) , user.getPassword());
            if(null != msg){
                String[] info = msg.getContent().split("-");
                msg.setContent(info[1]);//后面内容为全部好友
                fstr=info;
                fmsg=msg;
               
            }
            
            
		
//		    if(user == null){
//		    	JOptionPane.showMessageDialog(this, "用户名或密码错误！");
//		    	return;
//		    }
		    JOptionPane.showMessageDialog(this, "欢迎【用户】："+user.getName()+"登录本系统！");
		    userDao.closeDao();
		    
		    
		    UserDao userDao1 = new UserDao();

		    int aa=user.getId();
			 User aaa= new User();
			 aaa.setId(aa);
			  aaa.setOnline(1);
			  userDao1.online(aaa);
			    userDao1.closeDao();
	    
		    MainPage frame = new MainPage(user);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);	
			//Client client=new Client();
		    this.dispose();
		}
	  catch(Exception e1) {}
	      
	}
		
}


