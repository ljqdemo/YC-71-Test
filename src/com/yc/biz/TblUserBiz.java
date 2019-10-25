package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.BizException;
import com.yc.dao.TblUserDao;

public class TblUserBiz {
	public Map<String ,Object> login (String name,String pwd) throws BizException{
		if(name==null||name.trim().isEmpty()) {
			throw new BizException("����д�û�����");
		}
		if(pwd==null||pwd.trim().isEmpty()) {
			throw new BizException("�������룡");
		}
		TblUserDao dao=new TblUserDao();
		try {
		List<Map<String,Object>> list=dao.selectForLogin(name, pwd);
		if(list.size()==0) {
			throw new BizException("�û������������");
		}
		return list.get(0);
	}catch (RuntimeException e) {
		//�쳣ת��(תΪҵ���쳣)
		throw new BizException("ҵ��æ���Ժ����ԣ�",e);
	} 
	
	}
}
