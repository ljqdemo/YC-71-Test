package com.yc.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �û��� ��Ӧ tbl-user��
 * @author DELL
 *
 */
public class TblUser implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int  id;
	private String uname;
	private String upass;
	private String head;
	private Timestamp regtime; //java.jdbc.Dateֻ��ʾ������   �� Timetamp��ʾ������ʱ����
	private int gender;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
