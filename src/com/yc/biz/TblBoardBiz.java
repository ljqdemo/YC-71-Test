package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.BizException;
import com.yc.dao.TblBoardDao;

public class TblBoardBiz {
	public List<Map<String,Object>> query(String boardName, String parentBoard){
	TblBoardDao dao=new TblBoardDao();
	return dao.select(boardName,parentBoard);
	}

	public int creat(String boardname, String parentid) throws BizException {
		if(boardname==null||boardname.trim().length()==0) {
		throw new  BizException("ÇëÌîÐ´Ä£¿éÃû£¡");
		}
		TblBoardDao dao=new TblBoardDao();
		return dao.insert(boardname, parentid);
	}

	public int modify(String boardname, String parentid, String boardid) throws BizException {
		if(boardname==null||boardname.trim().length()==0) {
			throw new  BizException("ÇëÌîÐ´Ä£¿éÃû£¡");
			}
			TblBoardDao dao=new TblBoardDao();
			return dao.update(boardname, parentid,boardid);
		
	}

	public List<Map<String, Object>> pageQuery(int page, int rows) {
		TblBoardDao dao=new TblBoardDao();
		return dao.pageQuery(page,rows);
		
	}
	
	
}
