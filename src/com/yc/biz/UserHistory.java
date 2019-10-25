package com.yc.biz;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**用户浏览历史的业务类
 * 
 * @author DELL
 *
 */

public class UserHistory {
	
	/**
	 * 记录用户的访问帖子历史
	 */
	public void history	(int topicid ,int userid ) {
		//获取Redis连接	
		Jedis jedis=new Jedis();
		try {
			String key ="userid"+userid;
			long result=jedis.sadd(key, ""+topicid);
			//设置有效期为3天
			jedis.expire(key,3*24*60*60);
			//设置记录帖子浏览数的集合
			if(result==1) {
				jedis.zincrby("TopicNumber", 1, "userid:"+userid);
			}
		}finally {
			jedis.close();
		}
	}
	/***
	 * 将浏览量前10的用户排序
	 */
	public void sortuser() {
				//获取Redis连接	
				Jedis jedis=new Jedis();
				try {
					//获取用户集合并按照降序排列
					Set<String> users=jedis.zrevrange("TopicNumber", -10, -1);
					System.out.println("userssize:"+users.size());
					Iterator<String> it=users.iterator();
					while(it.hasNext()) {
						String userkey=it.next().replace(":", "");
						//获取浏览量
						long lookNumber=jedis.scard(userkey);
						System.out.println(userkey+"   lookNmber:"+lookNumber);
					}
				}finally {
					jedis.close();
				}
		
	}
	public static void main(String[] args) {
		UserHistory user=new UserHistory();
		//随机生成记录
		rand(user);
		user.sortuser();
	}
	//随机生成
	public static void rand(UserHistory user) {
		for(int i=0;i<110;i++) {
			for(int j=0;j<120;j++) {
				//模拟随机浏览
				if(Math.random()<0.5) {
					user.history(i, j);
				}
			}
		}
	}
}
