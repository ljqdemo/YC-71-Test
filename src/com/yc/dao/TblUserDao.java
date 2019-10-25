package com.yc.dao;

import java.util.*;

public class TblUserDao {
	
	public List<Map<String,Object>> selectForLogin(String name,String pwd){
		String sql="select * from tbl_user where uname=? and upass=?";
		List<Map<String,Object>> list=DBHelper.executeQuery(sql, name,pwd);	
		return list;
	}

}
