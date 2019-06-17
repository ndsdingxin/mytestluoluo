package com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.exception;

import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.bean.ResultMode;
 
/**
 * 异常返回结果
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public  class OpensnsException extends Exception {
	
	// 序列化UID
	private static final long serialVersionUID = 8243127099991355146L;
	
	// 错误码
	private String code;
	
	// 错误描述
	private String describe;

	private ResultMode resultMode;

	public OpensnsException(String code, String describe) {
		super(describe);
		this.code = code;
	}
	
	public OpensnsException(ResultMode resultMode) {
		super(resultMode.getDescribe());
		this.resultMode = resultMode;
	}
	
	/**
	 * 构造异常
	 * @param code 异常状态码
	 * @param ex 异常来源
	 */
	public  OpensnsException(String code, Exception ex) {
		super(ex);
		this.code = code;
	}
	
	/**
	 * 返回异常状态码
	 * @return
	 */
	public  String getErrorCode() {
		return code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public ResultMode getResultMode() {
		return resultMode;
	}

	public void setResultMode(ResultMode resultMode) {
		this.resultMode = resultMode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}


