package com.yc.biz;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**�û������ʷ��ҵ����
 * 
 * @author DELL
 *
 */

public class UserHistory {
	
	/**
	 * ��¼�û��ķ���������ʷ
	 */
	public void history	(int topicid ,int userid ) {
		//��ȡRedis����	
		Jedis jedis=new Jedis();
		try {
			String key ="userid"+userid;
			long result=jedis.sadd(key, ""+topicid);
			//������Ч��Ϊ3��
			jedis.expire(key,3*24*60*60);
			//���ü�¼����������ļ���
			if(result==1) {
				jedis.zincrby("TopicNumber", 1, "userid:"+userid);
			}
		}finally {
			jedis.close();
		}
	}
	/***
	 * �������ǰ10���û�����
	 */
	public void sortuser() {
				//��ȡRedis����	
				Jedis jedis=new Jedis();
				try {
					//��ȡ�û����ϲ����ս�������
					Set<String> users=jedis.zrevrange("TopicNumber", -10, -1);
					System.out.println("userssize:"+users.size());
					Iterator<String> it=users.iterator();
					while(it.hasNext()) {
						String userkey=it.next().replace(":", "");
						//��ȡ�����
						long lookNumber=jedis.scard(userkey);
						System.out.println(userkey+"   lookNmber:"+lookNumber);
					}
				}finally {
					jedis.close();
				}
		
	}
	public static void main(String[] args) {
		UserHistory user=new UserHistory();
		//������ɼ�¼
		rand(user);
		user.sortuser();
	}
	//�������
	public static void rand(UserHistory user) {
		for(int i=0;i<110;i++) {
			for(int j=0;j<120;j++) {
				//ģ��������
				if(Math.random()<0.5) {
					user.history(i, j);
				}
			}
		}
	}
}
