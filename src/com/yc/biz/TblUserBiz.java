package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.BizException;
import com.yc.dao.TblUserDao;

public class TblUserBiz {
	public Map<String ,Object> login (String name,String pwd) throws BizException{
		if(name==null||name.trim().isEmpty()) {
			throw new BizException("请填写用户名！");
		}
		if(pwd==null||pwd.trim().isEmpty()) {
			throw new BizException("请填密码！");
		}
		TblUserDao dao=new TblUserDao();
		try {
		List<Map<String,Object>> list=dao.selectForLogin(name, pwd);
		if(list.size()==0) {
			throw new BizException("用户名或密码错误！");
		}
		return list.get(0);
	}catch (RuntimeException e) {
		//异常转型(转为业务异常)
		throw new BizException("业务繁忙，稍后再试！",e);
	} 
	
	}
}
