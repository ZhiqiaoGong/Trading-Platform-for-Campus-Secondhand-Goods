
//���ڶԴ�User�ı���ȡ���������ݽ�����ɾ�Ĳ�

package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



public class UserDao extends BaseDao{
	public User login(User user){
		//ʵ�ֵ�¼���ܣ����û�����������в���
		String sql = "select * from login where name=? and password=?";
		User user1 = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
			prst.setString(1, user.getName());
			prst.setString(2, user.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				user1 = new User();
				user1.setId(executeQuery.getInt("id"));
				user1.setName(executeQuery.getString("name"));
				user1.setPassword(executeQuery.getString("password"));
				user1.setXiaoqu(executeQuery.getString("xiaoqu"));
				user1.setNianji(executeQuery.getInt("nianji"));
				user1.setZhuanye(executeQuery.getString("zhuanye"));
				user1.setZhuceriqi(executeQuery.getDate("zhuceriqi"));
				user1.setOnline(executeQuery.getInt("online"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user1;
	}
	public List<User> getUserList(User user){
		//�ȵ����Ƿ����ߵ���Ϣ
		List<User> retList = new ArrayList<User>();
		String sqlString = "select * from login";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				User sc = new User();
				sc.setId(executeQuery.getInt("id"));
				sc.setName(executeQuery.getString("name"));
				sc.setOnline(executeQuery.getInt("online"));
				retList.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	
	public boolean addUser(User user,String nname){
//		String sql1 = "select * from login ";
//
//		try {
//			 Statement st =con.createStatement();
//      	      ResultSet rs = st.executeQuery(sql1);
//      	      
//      	      Boolean flag=true;
//         	   while(rs.next()){
//         		   String dnam=rs.getString(1);
//				if(dnam.equals(nname)) {
//  	    			JOptionPane.showConfirmDialog(null, "�û����Ѵ��ڣ�", null, JOptionPane.OK_OPTION);
//  	    		    flag=false;
//  	    			break;
//         		   }
//         	   }			
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		//ע�Ṧ��
		String sql = "insert into login values(null,?,?,?,?,?,?,1)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getZhuanye());
			preparedStatement.setInt(4, user.getNianji());
			preparedStatement.setString(5, user.getXiaoqu());
			preparedStatement.setDate(6, (Date) user.getZhuceriqi());
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean update(User user){
		//�����û���Ϣ
		String sql = "update login set zhuanye=?,nianji=?,xiaoqu=? where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, user.getZhuanye());
			preparedStatement.setInt(2, user.getNianji());
			preparedStatement.setString(3, user.getXiaoqu());
			preparedStatement.setInt(4, user.getId());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String editPassword(User user,String newPassword){
		//�����û�����
		String sql = "select * from login where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, user.getId());
			prst.setString(2, user.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(!executeQuery.next()){
				String retString = "���������";
				return retString;
			}
			id = executeQuery.getInt("id");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//��sql��䴫�����ݿ��������
		String retString = "�޸�ʧ��";
		String sqlString = "update login set password = ? where id = ?";
		try {
			prst = con.prepareStatement(sqlString);
			prst.setString(1, newPassword);
			prst.setInt(2, id);
			int rst = prst.executeUpdate();
			if(rst > 0){
				retString = "�����޸ĳɹ���";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��sql��䴫�����ݿ��������
		return retString;
	}
	public boolean online(User user){
		//��¼ʱ����������״��
		String sql = "update login set online=?  where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, user.getOnline());
			preparedStatement.setInt(2, user.getId());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public User get(int a){
		//�õ��û����������
		String sqlString = "select * from login where id=?";
		User sc = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setInt(1, a);
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				sc = new User();
				sc.setName(executeQuery.getString("name"));
				sc.setOnline(executeQuery.getInt("online"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sc;
	}
	
 }
