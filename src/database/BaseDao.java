//这个类专门用于连接数据库和关闭数据库

package database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author llq
 *创建对数据库连接对象，整个项目与数据库打交道都用这一个对象.
 */
public class BaseDao {
	public Connection con = new DbUtil().getCon();
	public void closeDao(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
