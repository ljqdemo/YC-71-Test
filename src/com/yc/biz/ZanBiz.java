package com.yc.biz;

import redis.clients.jedis.Jedis;

/***
 * 点赞的业务类
 * 
 */
public class ZanBiz {
	
	/***
	 * 点赞 ： 
	 * topicid 帖子id
	 * userid 点赞人id
	 */
public void addZan(int topicid,int userid) {
		//获取redis连接
		Jedis jedis=new Jedis("127.0.0.1",6379);
		try {
			//将当前的帖子点赞的用户 ，保存在一个set中
			//设计 key 格式
			String key ="zan:"+topicid;
			long result=jedis.sadd(key, ""+userid);//当添加有效时候返回 0 无效添（已经点赞的用户）加返回1
			if(result==1) {
			//使用zset 保存帖子 : key: topicZset
			//redis中有一系列的包含incr关键字的方法
			//自动给指定元素序号加一 zincr如果监测到没有该元素，则自动添加该元素
			//zincrby 可以当zadd用
			jedis.zincrby("topicZset", 1, "topicid:"+topicid);
			}
		}finally {
			jedis.close();
		}
	}
/**
 * 取消点赞
 * 
 */
 public void delZan(int topicid,int userid) {
	//获取redis连接
			Jedis jedis=new Jedis("127.0.0.1",6379);
			try {
				//将当前的帖子点赞的用户 ，保存在一个set中
				//设计 key 格式
				String key ="zan:"+topicid;
				long result=jedis.srem(key, ""+userid);
				if(result==1) {
					//使用zset 保存帖子 : key: topicZset
					//redis中有一系列的包含incr关键字的方法
					//自动给指定元素序号加一 zincr如果监测到没有该元素，则自动添加该元素
					//zincrby 可以当zadd用
					//取消赞序号减一
					jedis.zincrby("topicZset", -1, ""+topicid);
					}
			}finally {
				jedis.close();
			}
 }
 public static void main(String [] args) {
	 ZanBiz zb=new ZanBiz();
	 for(int i=0;i<10;i++) {
		 for(int j=0;j<10;j++) {
			 //随机点赞
			 if(Math.random()>0.5) {
				 zb.addZan(i, j);
			 }
		 }
	 }
//	 zb.delZan(2, 2);
//	 zb.delZan(2, 3);
 }
}
