
//�����Ʒ�Ľ���
package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.PhotoCode;
import database.Products;
import database.ProductsDao;
import database.StringUtil;
import database.User;
import database.UserDao;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ProductsAdd extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JComboBox comboBox;
	private ButtonGroup group;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;	
	public static User user;
	private JTextField textField_2;
	private File resfile;
	private String aa;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ProductsAdd(User user) {
		this.user=(User) user;
		setResizable(false);
		setTitle("\u6DFB\u52A0\u5546\u54C1");
		getContentPane().setFont(new Font("����", Font.PLAIN, 18));

		setBounds(100, 100, 567, 562);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D");
		label.setFont(new Font("����", Font.PLAIN, 18));
		label.setBounds(85, 62, 81, 22);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u4ECB\u7ECD");
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		label_1.setBounds(85, 104, 96, 22);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u662F\u5426\u62CD\u5356");
		label_2.setFont(new Font("����", Font.PLAIN, 18));
		label_2.setBounds(85, 360, 124, 35);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u4EF7\u683C");
		label_3.setFont(new Font("����", Font.PLAIN, 18));
		label_3.setBounds(85, 203, 108, 35);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u5546\u54C1\u7C7B\u522B");
		label_4.setFont(new Font("����", Font.PLAIN, 18));
		label_4.setBounds(85, 261, 124, 30);
		getContentPane().add(label_4);
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 18));
		textField.setBounds(181, 58, 244, 30);
		getContentPane().add(textField);
		textField.setColumns(20);
		
		
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("����", Font.PLAIN, 18));
		textField_1.setBounds(181, 203, 66, 29);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5143");
		label_5.setFont(new Font("����", Font.PLAIN, 18));
		label_5.setBounds(259, 207, 58, 26);
		getContentPane().add(label_5);
		
		comboBox= new JComboBox();
		comboBox.setFont(new Font("����", Font.PLAIN, 18));
		comboBox.setMaximumRowCount(4);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u670D\u88C5\u7C7B", "\u4E66\u7C4D\u7C7B", "\u6570\u7801\u7528\u54C1\u7C7B", "\u5316\u5986\u54C1\u7C7B", "\u4F53\u80B2\u7C7B", "\u98DF\u54C1\u7C7B", "\u5176\u4ED6\u7C7B"}));
		comboBox.setBounds(181, 261, 244, 28);
		getContentPane().add(comboBox);
		
		radioButton = new JRadioButton("\u662F");
		radioButton.setFont(new Font("����", Font.PLAIN, 18));
		radioButton.setBounds(181, 366, 66, 23);
		getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5426");
		radioButton_1.setFont(new Font("����", Font.PLAIN, 18));
		radioButton_1.setBounds(259, 366, 127, 23);
		getContentPane().add(radioButton_1);
		
		group=new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		
		JButton addproducts = new JButton("\u6DFB\u52A0");
		addproducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent be) {
				addAct();
			}
		});
		addproducts.setFont(new Font("����", Font.PLAIN, 18));
		addproducts.setBounds(85, 412, 97, 35);
		getContentPane().add(addproducts);
		
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAct();
			}
		});
		reset.setFont(new Font("����", Font.PLAIN, 18));
		reset.setBounds(220, 412, 97, 35);
		getContentPane().add(reset);
		
		JButton btnNewButton = new JButton("\u53D6\u6D88");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton.setBounds(353, 412, 97, 35);
		getContentPane().add(btnNewButton);
		
		JLabel label_6 = new JLabel("\u5546\u54C1\u56FE\u7247");
		label_6.setFont(new Font("����", Font.PLAIN, 18));
		label_6.setBounds(85, 321, 81, 29);
		getContentPane().add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("����", Font.PLAIN, 18));
		textField_2.setBounds(183, 320, 244, 30);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u4E0A\u4F20\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mmmAct();
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton_1.setBounds(452, 319, 87, 33);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(179, 104, 246, 82);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		

}


	protected void mmmAct() {
		// TODO �Զ����ɵķ������
		PhotoCode fileFilter=new PhotoCode();
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setFileFilter(fileFilter);
		 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 int returnVal = fileChooser.showOpenDialog(fileChooser);
		 if(returnVal == JFileChooser.APPROVE_OPTION){ 
			 String filePath= fileChooser.getSelectedFile().getAbsolutePath();//���������ѡ����ļ��е�·��
			 textField_2.setText(filePath);
			 System.out.println(filePath);//������ѡ�ļ���·��
		 }
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
		 System.out.println(df.format(new Date()));
	     resfile = new File(System.getProperty("user.dir")+"/src/photo/"+df.format(new Date())+".jpg");
	    aa="/src/photo/"+df.format(new Date())+".jpg";
	     try {
		   copyFileUsingFileStreams(fileChooser.getSelectedFile(),resfile);
		   System.out.println("�ɹ�"+resfile.getAbsolutePath());//������ѡ�ļ���·��
	   } catch (IOException e) {
		// TODO �Զ����ɵ� catch ��
		   e.printStackTrace();
	   }
}


	protected void addAct() {
		String shangpinName = textField.getText().toString();
		String jiageName =textField_1.getText().toString();
		String jieshanName =textArea.getText().toString();
		String photoName =textField_2.getText().toString();
		
		double a=0;
		if(StringUtil.isEmpty(shangpinName)){
			JOptionPane.showMessageDialog(this, "����д��Ʒ��!");
			return;
		}
		if(StringUtil.isEmpty(jiageName)){
			JOptionPane.showMessageDialog(this, "����д�۸�!");
			return;
		}
		try {
			a=Double.parseDouble(jiageName);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "�۸�ֻ�����������֣�");
			return;
		}
		if( a == 0 || a < 0){
			JOptionPane.showMessageDialog(this, "�۸�������0��");
			return;
		}
		
		if(!(radioButton.isSelected())&&!(radioButton_1.isSelected())){
			JOptionPane.showMessageDialog(this, "����д������ʽ!");
			return;
		}
		String type = (String)comboBox.getSelectedItem();
		int fangshi = 0;
		if(radioButton.isSelected())
		{
			fangshi=1;
		}
		if(radioButton_1.isSelected())
		{
			fangshi=0;
		}
		if(StringUtil.isEmpty(photoName)){
			JOptionPane.showMessageDialog(this, "��ѡ����ƷͼƬ!");
			return;
		}
		
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		
		Products fff=new Products();
		fff.setName(shangpinName);
		fff.setPrice(a);
		fff.setPhoto(aa);
		fff.setAuction(fangshi);
		fff.setIntroduction(jieshanName);
		fff.setTime(date);
		fff.setType(type);
		User user = (User)MainPage.user;
		int i=user.getId();
	    fff.setUserid(i);
		fff.setIsbuy(1);
		ProductsDao dddd = new ProductsDao();
		if(dddd.addProducts(fff)){
			JOptionPane.showMessageDialog(this, "��ӳɹ�!");	
		}else{
			JOptionPane.showMessageDialog(this, "���ʧ��!");
		}
		dddd.closeDao();
		resetAct();
	}

	protected void resetAct() {
		// TODO �Զ����ɵķ������
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textArea.setText("");
		group.clearSelection();
		comboBox.setSelectedIndex(0);
	}
	
	
	private static void copyFileUsingFileStreams(File source, File dest)throws IOException {
	    InputStream input = null;
		OutputStream output = null;
		try {
			     input = new FileInputStream(source);
			     output = new FileOutputStream(dest);
			      byte[] buf = new byte[1024];
			      int bytesRead;
			      while ((bytesRead = input.read(buf)) > 0) {
			           output.write(buf, 0, bytesRead);
			      }
			} finally {
			input.close();
			output.close();
		}
	}
}


