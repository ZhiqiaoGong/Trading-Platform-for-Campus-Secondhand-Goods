//用于提取Products表的数据
package database;

import java.io.InputStream;
import java.sql.Date;

public class Products {


	private int id;
	private int isbuy;
    private String photo;
	private int userid;
	private String introduction;
	private double price;
	private int auction;
	private String type;
	private Date time;
	public int getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	private int buyerid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAuction() {
		return auction;
	}
	public void setAuction(int auction) {
		this.auction = auction;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getIsbuy() {
		return isbuy;
	}
	public void setIsbuy(int isbuy) {
		this.isbuy = isbuy;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
