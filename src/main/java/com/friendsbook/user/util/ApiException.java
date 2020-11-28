package com.friendsbook.user.util;

public class ApiException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6146934263453675682L;
	
	private String msg;
	
	public ApiException(String message){
		this.setMsg(message);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
