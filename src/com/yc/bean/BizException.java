package com.yc.bean;

/**
 * 自定义业务也常
 * @author DELL
 *
 */
public class BizException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//重写构造方法

	public BizException() {
		super();
	
	}

	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BizException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
