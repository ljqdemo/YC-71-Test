package com.yc.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class TblReply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pid;
	private  String title;
	private String content;
	private Timestamp publishTiem;
	private Timestamp modifytime;
	private int uid;
	private int tid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPublishTiem() {
		return publishTiem;
	}
	public void setPublishTiem(Timestamp publishTiem) {
		this.publishTiem = publishTiem;
	}
	public Timestamp getModifytime() {
		return modifytime;
	}
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
