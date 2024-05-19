
//用于修改用户信息

package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



import database.User;
import database.UserDao;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMessage extends JFrame {
	private JTextField zhuanyeText;
	private JTextField currentTime;
	private JComboBox xiaoquComboBox;
	private  JComboBox comboBox;
	public static User user;
	private int aa;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EditMessage(User use) {
		this.user=(User) use;
		setResizable(false);
		setTitle("\u4FEE\u6539\u4FE1\u606F");
		setBounds(100, 100, 424, 408);
		User user = MainPage.user;

		JLabel label = new JLabel("\u5F53\u524D\u7528\u6237");
		label.setBounds(74, 44, 87, 28);
		label.setFont(new Font("宋体", Font.PLAIN, 18));

		JLabel label_1 = new JLabel("\u6240\u5C5E\u6821\u533A");
		label_1.setBounds(74, 90, 87, 37);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));

		JLabel label_2 = new JLabel("\u6240\u5C5E\u5E74\u7EA7");
		label_2.setBounds(74, 190, 87, 28);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));

		JLabel label_3 = new JLabel("\u6240\u5C5E\u4E13\u4E1A");
		label_3.setBounds(74, 144, 87, 28);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));

		JLabel label_4 = new JLabel("\u6CE8\u518C\u65F6\u95F4");
		label_4.setBounds(74, 236, 87, 32);
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));

		JLabel cuerrentName = new JLabel();
		cuerrentName.setText("【用户】" + user.getName());
		cuerrentName.setBounds(167, 47, 174, 22);
		cuerrentName.setFont(new Font("宋体", Font.PLAIN, 18));
		
         aa=user.getId();
// currentTime.setText("aaaaa");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = user.getZhuceriqi();
//		currentTime.setText(sdf.format(date));
		/*
		 * String dateStr = new
		 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(user.getZhuceriqi().toString() ); 
		 * currentTime.setText(dateStr);
		 */
		
		
		//String str=String.valueOf(user.getId());

		zhuanyeText = new JTextField();
		String str1=user.getZhuanye();
		zhuanyeText.setText(str1);
		zhuanyeText.setBounds(167, 144, 154, 28);
		zhuanyeText.setFont(new Font("宋体", Font.PLAIN, 18));
		zhuanyeText.setColumns(20);

		xiaoquComboBox = new JComboBox();
		String className=user.getXiaoqu();
		xiaoquComboBox.setMaximumRowCount(4);
		xiaoquComboBox.setBounds(167, 94, 154, 28);
		xiaoquComboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		xiaoquComboBox.setModel(new DefaultComboBoxModel(new String[] {"兴隆山校区", "软件园校区", "中心校区", "千佛山校区", "洪楼校区", "趵突泉校区", "青岛校区", "威海校区"}));
		for(int i=0;i<xiaoquComboBox.getItemCount();i++){
			String sc = (String)xiaoquComboBox.getItemAt(i);
			if(className.equals(sc)){
				xiaoquComboBox.setSelectedIndex(i);
				break;
			}
		}
		
		comboBox = new JComboBox();
	
		comboBox.setMaximumRowCount(4);
		comboBox.setBounds(167, 190, 154, 28);
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "2019级", "2018级", "2017级", "2016级" }));
		
		int a=user.getNianji();
		String str3=a+"级";
		comboBox.setSelectedItem(str3);
		comboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		getContentPane().setLayout(null);
		getContentPane().add(label);
		getContentPane().add(label_1);
		getContentPane().add(label_2);
		getContentPane().add(label_3);
		getContentPane().add(label_4);
		getContentPane().add(cuerrentName);
		getContentPane().add(zhuanyeText);
		getContentPane().add(xiaoquComboBox);
		getContentPane().add(comboBox);

		JButton xiugaiButton = new JButton("\u4FEE\u6539");
		xiugaiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                     xiugaiAct();
			}
		});
		xiugaiButton.setFont(new Font("Dialog", Font.PLAIN, 18));
		xiugaiButton.setBounds(74, 306, 97, 23);
		getContentPane().add(xiugaiButton);

		JButton quxiaoButton = new JButton("\u53D6\u6D88");
		quxiaoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		quxiaoButton.setFont(new Font("Dialog", Font.PLAIN, 18));
		quxiaoButton.setBounds(224, 306, 97, 23);
		getContentPane().add(quxiaoButton);
		
		currentTime = new JTextField();
		currentTime.setFont(new Font("宋体", Font.PLAIN, 18));
		currentTime.setEditable(false);
//		String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(user.getZhuceriqi() ); 
		Date dat=user.getZhuceriqi();
		currentTime.setText(user.getDateString(dat));
		currentTime.setBounds(171, 236, 150, 28);
		getContentPane().add(currentTime);
		currentTime.setColumns(20);


	}

	protected void xiugaiAct() {
		// TODO 自动生成的方法存根
		String str = zhuanyeText.getText().toString();
		User usera = new User();
		usera.setId(aa);
		usera.setZhuanye(str);
		String xiaoqu = (String)xiaoquComboBox.getSelectedItem();
		usera.setXiaoqu(xiaoqu);
		String nianji = (String)comboBox.getSelectedItem();
		String strtemp= nianji.substring(0, 4) ;
		int result =Integer.parseInt(strtemp);
		usera.setNianji(result);
		UserDao userDao=new UserDao();
		if(userDao.update(usera)){
			JOptionPane.showMessageDialog(this,"修改成功！");
		}else{
			JOptionPane.showMessageDialog(this, "修改失败！");
		}
		userDao.closeDao();
		dispose();

	}

}
