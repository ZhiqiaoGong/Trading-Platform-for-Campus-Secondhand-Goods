

//用于对从comment的表提取出来的数据进行增删改查
package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends BaseDao {
	public List<Comment> show(Comment comment){
		//用于找到对应商品的所有评论
		String sql = "select * from comment where pid=?";
		List<Comment> retList = new ArrayList<Comment>();
		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setInt(1, comment.getPid());
			ResultSet executeQuery = prst.executeQuery();
			while(executeQuery.next()){
				Comment comment1 = new Comment();
				comment1 = new Comment();
				comment1.setId(executeQuery.getInt("id"));
				comment1.setUid(executeQuery.getInt("uid"));
				comment1.setPid(executeQuery.getInt("pid"));
				comment1.setWord(executeQuery.getString("word"));
				retList.add(comment1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
//	public String show(Comment comment){
//		String sql = "select * from comment where pid=?";
//		String resultStr = "";
//		try {
//			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
//			prst.setInt(1, comment.getPid());
//			ResultSet executeQuery = prst.executeQuery();
//			while(executeQuery.next()){
//				resultStr += executeQuery.getInt("uid")+":"+executeQuery.getString("word") + "\n";         		
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return resultStr;
//	}
	public boolean addComment(Comment comment){
		//用于添加评论
		String sql = "insert into comment values(null,?,?,?)";
		try {
			java.sql.PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, comment.getPid());
			preparedStatement.setInt(2, comment.getUid());
			preparedStatement.setString(3, comment.getWord());
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
