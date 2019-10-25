package com.yc.dao;

import java.util.*;
import java.util.List;
import java.util.Map;

public class TblBoardDao {

	public List<Map<String, Object>> selectAll() {
		String sql="select * from tbl_board ";
		return DBHelper.executeQuery(sql);
	}

	public List<Map<String, Object>> select(String boardName, String parentBoard) {
		String sql="select * from tbl_board where 1=1 ";
		List<Object> list=new ArrayList<>();
		if(boardName!=null && boardName.trim().isEmpty()==false) {
			sql+=" and boardname like?";
			list.add("%"+boardName+"%");
		}
		if(parentBoard!=null && parentBoard.trim().isEmpty()==false) {
			sql+=" and parentid =?";
			list.add(parentBoard);
		}
		
		return DBHelper.executeQuery(sql, list.toArray());
	}


	public int insert(String boardname, String parentid) {
		String sql="insert into tbl_board values(null,?,?)";
		return DBHelper.executeUpdate(sql, boardname,parentid);
	}

	public int update(String boardname, String parentid, String boardid) {
		String sql="update tbl_board set boardname=? ,parentid=? where boardid=?";
		return DBHelper.executeUpdate(sql, boardname,parentid,boardid);
	}

	public List<Map<String, Object>> pageQuery(int page, int rows) {
		String sql="select * from tbl_board limit "+(page-1)*rows+","+rows;
		return DBHelper.executeQuery(sql);
	}

}
