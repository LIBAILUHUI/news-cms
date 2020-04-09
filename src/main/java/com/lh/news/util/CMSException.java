package com.lh.news.util;

/**
 * 
 * @ClassName: CMSException 
 * @Description: CMS的自定义异常
 * @author: Administrator
 * @date: 2020年4月9日 上午10:59:13
 */
public class CMSException extends RuntimeException{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public String message;//消息

	public CMSException() {
		
	}
	public CMSException(String message) {
		//交给父类处理
		super(message);//super必须放在构造第一句
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
