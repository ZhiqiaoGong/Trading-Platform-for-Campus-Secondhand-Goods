//用于提取User表的数据


package database;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class User {
	private int id;
	private int nianji;
	private String xiaoqu;
	private String name;
	private String password;
	private String zhuanye;
	private Date zhuceriqi;
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	private int online;
	public  Date getZhuceriqi() {
		return zhuceriqi;
	}
	public void setZhuceriqi(Date date) {
		this.zhuceriqi = date;
	}
	public String getDateString(Date date) {
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		String sdf1 = f.format(date);
		return sdf1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdString(int id) {
		String str=String.valueOf(id);
		return str;
	}
	public int getNianji() {
		return nianji;
	}
	public void setNianji(int nianji) {
		this.nianji = nianji;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
}
