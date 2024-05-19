package server;

import com.mysql.jdbc.Connection;

import common.ChatUser;

import java.sql.*;
import java.text.DateFormat;

import javax.swing.JOptionPane;

/**
 * ����һ������JDBC����MySql�Ĺ�����
 * ʵ�����ж����ݿ�Ĳ����������ṩ����
 */
public class UsingDB {
	private static Connection con;
	public PreparedStatement ps;
	private ResultSet rs;
	private static String content = "";
	private static int num=4;


	public UsingDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //��������
            con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/chat?useUnicode=true&characterEncoding=UTF-8","root","123");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * ����uid��pwd�����ݿ��к�ʵ�û���Ϣ
     * ����qq�ǳƷ���
     * @param u
     * @return
     */
    public String checkUserInfo(ChatUser u){
        String qname = null;
        int uid = Integer.parseInt(u.getUid());
        String pwd = u.getPwd();
        String sql = "select uname from  t_user where uid=? and pwd=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setString(2,pwd);
            rs = ps.executeQuery();
            while (rs.next()){
                qname = rs.getString("uname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            release(con,ps,rs);
        }
        return qname;
    }
    /**
     * ����ȫ�������б�
     * @return
     */
    public String getFriendsList(String uid){
        try {
        	StringBuilder contents = new StringBuilder();
            int mqq = Integer.parseInt(uid);
            String sql = "select fname,fqq from  t_friends where mqq=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,mqq);
            rs = ps.executeQuery();
            while(rs.next()){
                contents.append(rs.getString("fname")+"("+rs.getString("fqq")+") ");
            }
            return contents.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            release(con,ps,rs);
        }
        return null;
    }
    public static String ref(String id) {
        try {
        	StringBuilder contents = new StringBuilder();
            int mqq = Integer.parseInt(id);
            String sql = "select fname,fqq from  t_friends where mqq=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,mqq);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                contents.append(rs.getString("fname")+"("+rs.getString("fqq")+") ");
            }
            con.close();
            ps.close();
            rs.close();
            System.out.println(contents.toString());
            return contents.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
     * ����ָ���û����͸��û�����������Ϣ
     */
	public static String searchLxmessage(String uid,String fid) {
	    try {	
	        String sql9="select * from chatmessage where tonam = '"+uid+"' and fromnam = '"+fid+"'";
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql9);
        	
            while(rs.next()) {
        	    if(rs.getString(1)!=null&&rs.getString(4)!=null) {
                   content+=rs.getString(1)+"  ("+rs.getString(4)+")\n";   
        	    }
            }  
            System.out.println("��ѯ������Ϣ�ɹ�");
	}catch(Exception e) {e.printStackTrace();}
	return content;
}
	/*
	 * ����������Ϣ���������ݿ⣩
	 */
  	public static void addOneMesg(String mesg,String fid,String tid,String date) {
  		try {
  			String sql9 ="insert into chatmessage values (?,?,?,?)";
  	        PreparedStatement pp3=con.prepareStatement(sql9);
 		    pp3.setString(1, mesg);
 		    pp3.setString(2, fid);
 		    pp3.setString(3, tid);
 		    pp3.setString(4, date);
 		    pp3.executeUpdate();
            System.out.println(fid+"�����һ�����͸�"+tid+"��������Ϣ");

  		}catch(Exception e) {e.printStackTrace();}
  	}
  	
  	/*
  	 * ��Ӻ��ѵ������б���
  	 */
  	public static void addFriend(String mid,String mnam,String fid,String fnam) {
  		try {
  			num++;
  			String sql10="insert into t_friends values(?,?,?,?)";
  	        PreparedStatement pp4=con.prepareStatement(sql10);
  	        pp4.setInt(1,num);
  	        pp4.setString(2, mid);
  	        pp4.setString(3, fid);
  	        pp4.setString(4, fnam);
 		    pp4.executeUpdate();
 		    num++;
  	        pp4.setInt(1,num);
 		    pp4.setString(2, fid);
 	        pp4.setString(3, mid);
 	        pp4.setString(4, mnam);
		    pp4.executeUpdate();
            System.out.println(mid+"��"+fid+"�����Ϊ����");
  		}catch(Exception e) {e.printStackTrace();}
  	}
    /**
     * �ͷ���Դ
     * @param conn
     * @param st
     * @param rs
     */
	public void release(Connection conn,Statement st,ResultSet rs) {
		//�ر���Դ
		try {
			if(null != rs)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(null != st)
				st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * �ͷ���Դ����
     * @param conn
     * @param st
     */
	public void release(Connection conn,Statement st) {
		//�ر���Դ
		try {
			if(null != st)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
