package com.yc.biz;

import redis.clients.jedis.Jedis;

/***
 * ���޵�ҵ����
 * 
 */
public class ZanBiz {
	
	/***
	 * ���� �� 
	 * topicid ����id
	 * userid ������id
	 */
public void addZan(int topicid,int userid) {
		//��ȡredis����
		Jedis jedis=new Jedis("127.0.0.1",6379);
		try {
			//����ǰ�����ӵ��޵��û� ��������һ��set��
			//��� key ��ʽ
			String key ="zan:"+topicid;
			long result=jedis.sadd(key, ""+userid);//�������Чʱ�򷵻� 0 ��Ч���Ѿ����޵��û����ӷ���1
			if(result==1) {
			//ʹ��zset �������� : key: topicZset
			//redis����һϵ�еİ���incr�ؼ��ֵķ���
			//�Զ���ָ��Ԫ����ż�һ zincr�����⵽û�и�Ԫ�أ����Զ���Ӹ�Ԫ��
			//zincrby ���Ե�zadd��
			jedis.zincrby("topicZset", 1, "topicid:"+topicid);
			}
		}finally {
			jedis.close();
		}
	}
/**
 * ȡ������
 * 
 */
 public void delZan(int topicid,int userid) {
	//��ȡredis����
			Jedis jedis=new Jedis("127.0.0.1",6379);
			try {
				//����ǰ�����ӵ��޵��û� ��������һ��set��
				//��� key ��ʽ
				String key ="zan:"+topicid;
				long result=jedis.srem(key, ""+userid);
				if(result==1) {
					//ʹ��zset �������� : key: topicZset
					//redis����һϵ�еİ���incr�ؼ��ֵķ���
					//�Զ���ָ��Ԫ����ż�һ zincr�����⵽û�и�Ԫ�أ����Զ���Ӹ�Ԫ��
					//zincrby ���Ե�zadd��
					//ȡ������ż�һ
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
			 //�������
			 if(Math.random()>0.5) {
				 zb.addZan(i, j);
			 }
		 }
	 }
//	 zb.delZan(2, 2);
//	 zb.delZan(2, 3);
 }
}
