
//���ڶԴ�Products�ı���ȡ���������ݽ�����ɾ�Ĳ�

package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductsDao extends BaseDao{
	public Products search(Products products){
		//ͨ��id�ҵ���Ʒ
		String sql = "select * from products where id=? ";
		Products bbb = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
			prst.setInt(1, products.getId());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				bbb = new Products();
				bbb.setId(executeQuery.getInt("id"));
				bbb.setName(executeQuery.getString("name"));
				bbb.setUserid(executeQuery.getInt("userid"));
				bbb.setIntroduction(executeQuery.getString("introduction"));
				bbb.setPrice(executeQuery.getDouble("price"));
				bbb.setAuction(executeQuery.getInt("auction"));
				bbb.setTime(executeQuery.getDate("time"));
				bbb.setType(executeQuery.getString("type"));
				bbb.setBuyerid(executeQuery.getInt("buyerid"));
				bbb.setIsbuy(executeQuery.getInt("isbuy"));
				bbb.setPhoto(executeQuery.getString("photo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bbb;
	}
	public List<Products> getProductsListA(String type ){
		//ͨ����Ʒ�����Լ��������������Ʒ
		List<Products> retList = new ArrayList<Products>();
		String sql = new String("select * from products where type=? and isbuy=1");
		try {
			PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
			prst.setString(1, type);
			ResultSet executeQuery = prst.executeQuery();
			while(executeQuery.next()){
				Products s = new Products();
				s.setId(executeQuery.getInt("id"));
				s.setName(executeQuery.getString("name"));
				s.setUserid(executeQuery.getInt("userid"));
				s.setBuyerid(executeQuery.getInt("buyerid"));
				s.setIntroduction(executeQuery.getString("introduction"));
				s.setType(executeQuery.getString("type"));
				s.setPrice(executeQuery.getDouble("price"));
				s.setTime(executeQuery.getDate("time"));
				s.setIsbuy(executeQuery.getInt("isbuy"));
				s.setPhoto(executeQuery.getString("photo"));
				retList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean addProducts(Products prosucts){
		//�����Ʒ
		String sql = "insert into products values(null,?,?,null,?,?,?,?,?,?,1)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, prosucts.getName());
			preparedStatement.setInt(2, prosucts.getUserid());
			//preparedStatement.setInt(3, prosucts.getBuyerid());
			preparedStatement.setString(3, prosucts.getIntroduction());
			preparedStatement.setString(4, prosucts.getPhoto());
			preparedStatement.setDate(5, prosucts.getTime());
			preparedStatement.setDouble(6, prosucts.getPrice());
			preparedStatement.setInt(7, prosucts.getAuction());
			preparedStatement.setString(8, prosucts.getType());
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//�˴��е�С���⣬��ȷ��isbuyʱ�޷�ʵ�֣�����ʵ����������ʱ����ʵ��isbuyer
	public List<Products> getProductsList(Products products){
		//չʾ����δ������Ʒ���Լ�ģ������
		List<Products> retList = new ArrayList<Products>();
		StringBuffer sqlString = new StringBuffer("select * from products where isbuy=1");
		if(!StringUtil.isEmpty(products.getName())){
			sqlString.append(" and name like '%"+products.getName()+"%'");//and��ǰ��Ҫ��һ���ո񣬲��ӵĻ����ͳ���where isbuy=1and name like������䡣
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString());
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Products s = new Products();
				s.setId(executeQuery.getInt("id"));
				s.setName(executeQuery.getString("name"));
				s.setUserid(executeQuery.getInt("userid"));
				s.setBuyerid(executeQuery.getInt("buyerid"));
				s.setIntroduction(executeQuery.getString("introduction"));
				s.setType(executeQuery.getString("type"));
				s.setPrice(executeQuery.getDouble("price"));
				s.setTime(executeQuery.getDate("time"));
				s.setIsbuy(executeQuery.getInt("isbuy"));
				s.setPhoto(executeQuery.getString("photo"));
				retList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}


public List<Products> getProductsListB(int a,int b){
	//չʾ�û���������Ʒ�����Է�Ϊ�ѱ������δ�������
	List<Products> retList = new ArrayList<Products>();
	String sql = new String("select * from products where userid=? and isbuy=?");
	try {
		PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
		prst.setInt(1, a);
		prst.setInt(2, b);
		ResultSet executeQuery = prst.executeQuery();
		while(executeQuery.next()){
			Products s = new Products();
			s.setId(executeQuery.getInt("id"));
			s.setName(executeQuery.getString("name"));
			s.setUserid(executeQuery.getInt("userid"));
			s.setBuyerid(executeQuery.getInt("buyerid"));
			s.setIntroduction(executeQuery.getString("introduction"));
			s.setType(executeQuery.getString("type"));
			s.setPrice(executeQuery.getDouble("price"));
			s.setTime(executeQuery.getDate("time"));
			s.setIsbuy(executeQuery.getInt("isbuy"));
			s.setPhoto(executeQuery.getString("photo"));
			retList.add(s);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return retList;
}
public List<Products> getProductsListC(int a,int b){
	//�����û��������Ʒ
	List<Products> retList = new ArrayList<Products>();
	String sql = new String("select * from products where buyerid=? and isbuy=?");
	try {
		PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
		prst.setInt(1, a);
		prst.setInt(2, b);
		ResultSet executeQuery = prst.executeQuery();
		while(executeQuery.next()){
			Products s = new Products();
			s.setId(executeQuery.getInt("id"));
			s.setName(executeQuery.getString("name"));
			s.setUserid(executeQuery.getInt("userid"));
			s.setBuyerid(executeQuery.getInt("buyerid"));
			s.setIntroduction(executeQuery.getString("introduction"));
			s.setType(executeQuery.getString("type"));
			s.setPrice(executeQuery.getDouble("price"));
			s.setTime(executeQuery.getDate("time"));
			s.setIsbuy(executeQuery.getInt("isbuy"));
			s.setPhoto(executeQuery.getString("photo"));
			retList.add(s);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return retList;
}

public double[] getProductsNumber(int a){
	//�����û�������Ʒ�������Լ������ѽ��
	double[] d= {0,0};
	String sql = new String("SELECT SUM(price),COUNT(id) FROM products WHERE buyerid=? AND isbuy=0");
	try {
		PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
		prst.setInt(1, a);
		ResultSet executeQuery = prst.executeQuery();
		if(executeQuery.next()){
			d[0]=executeQuery.getInt("COUNT(id)");
			d[1]=executeQuery.getDouble("SUM(price)");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return d;
}

public double[] getProductsA(int a){
	//�����û�������Ʒ���������
	double[] d= {0,0};
	String sql = new String("SELECT SUM(price),COUNT(id) FROM products WHERE userid=? AND isbuy=0");
	try {
		PreparedStatement prst = con.prepareStatement(sql);//��sql��䴫�����ݿ��������
		prst.setInt(1, a);
		ResultSet executeQuery = prst.executeQuery();
		if(executeQuery.next()){
			d[0]=executeQuery.getInt("COUNT(id)");
			d[1]=executeQuery.getDouble("SUM(price)");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return d;
}

public boolean update(Products products){
	//����Ʒ������ʱ���ı�����
	String sql = "update products set buyerid=?,isbuy=? where id=?";
	try {
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, products.getBuyerid());
		preparedStatement.setInt(2, products.getIsbuy());
		preparedStatement.setInt(3, products.getId());
		if(preparedStatement.executeUpdate() > 0){
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
}
