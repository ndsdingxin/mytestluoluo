package com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.util;

/**
 * 静态用户数据
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class StateData {

	/**
	 * APP 调用接口需填写以下系统参数
	 */
	/*1  诺诺发票
	 *2 财税助手
	 *3 
	 *4  
	 * 
	 */
	public static String[][] appkey_secret1=
		{{"EXD3oA7M","6D6B523CB1D64675","3919e4a3b8cb7b5122aeaa0sypimxczs"},
			{"V9W1d5eQ","B17FECC5BA04497A","3919e4a3b8cb7b5122aeaa02m3vcfans"},
			{"","",""}};
	
	
	public static String[][] appkey_secret2=
		{{"aUTJHxKQ","BE39AD47477B463E","a69273a1397e0ca73db89f029asnp1ps",""}
		,{"","",""},
		{"","",""}};
	public static String tax[][]
			= {{"913401006679310226","6mvi7z"
			},{"91370211MA3NEU722T","11ixk7"
			},{"913401006679310227","u2uxmy"}
					};
	public static String app_secret = appkey_secret1[0][1]; // 填写本APP申请的 appSecret,注意区分正式环境与沙箱环境参数，如： 6D6B523CB1D64675
	public static String app_key = appkey_secret1[0][0]; // 填写本APP申请的 appKey,注意区分正式环境与沙箱环境参数，如：EXD3oA7M
	public static String app_apiVersion = "V1.0.0"; // 填写本APP调用接口版本，如：1.0.0
	public static String app_accessToken = appkey_secret1[0][2];
	// 填写本APP申请的 令牌 ,注意区分正式环境与沙箱环境参数，：
	//"access_token":"3919e4a3b8cb7b5122aeaa0um27k48hs","expires_in":86400
	public static String app_dataType = "JSON"; // 填写本APP传输数据格式，如：JSON,XML
	public static String app_compressType = "GZIP"; // 填写本APP传输数据压缩格式，如：GZIP
	
	//*********************************************************
	//public static String app_signType = "AES/AES"; // 填写本APP传输数据加密格式，如：
	//  填AES/AES 报错  javax.crypto.IllegalBlockSizeException: Input length must be multiple of 16 when decrypting with padded cipher
	//*****************************************************************
	
	public static String app_signType = "AES"; // 填写本APP传输数据加密格式，如：AES/AES
	public static String user_Tax = tax[2][1]; // 业务发生方税号（ISV类型用户必填）；如：339901999999142
	
	/**
	 * APP 请求并发数（平台默认）
	 */
	public static String app_rate = "10"; // 填写APP并发请求数
	
	/**
	 * 发送方式
	 */
	//public static String contentType = "application/x-www-form-urlencoded";
	public static String contentType = "application/x-www-form-urlencoded;charset=UTF-8";
	
	/**
	 * 开放平台API访问地址
	 */
	public static String url = "https://sdk.jss.com.cn/openPlatform/services"; // 正式环境地址
	public static String url_sbox = "https://sandbox.jss.com.cn/openPlatform/services"; // 沙箱环境地址
	
	/**
	 * 开放平台Oauth授权访问地址
	 */
	public static String url_token = "https://open.jss.com.cn/accessToken"; // 正式环境地址
	
	/**
	 * 正式环境/沙箱环境OAUTH参数
	 *  请求access_token环节
	 */
	public static String auth_code = tax[2][1]; // ISV商户获取的auth_code
	public static String refresh_token = ""; // ISV商户刷新令牌，普通商户无此值，可程序赋值
	public static String taxNum = tax[2][1]; // 获取auth_code时开放平台返回当前商户税号，ISV模式下商户请求API时需传此值，可程序赋值
	public static String userId = ""; // 获取access_token时开放平台返回，ISV模式下商户刷新令牌时需传此值，可程序赋值
	public static String expires_in = ""; // access_token失效时间，可程序赋值
	public static String client_id = app_key; // 用户ID，取$appKey值，当调用刷新令牌时取$userId值
	public static String client_secret = app_secret; // 用户秘钥，取$app_secret值
	public static String redirect_uri = "http://127.0.0.1/index.html"; // 对调地址由商户自定义
	//public static String grant_type_token = "authorization_code"; // 获取access_token时，OAUTH定义编码类型
	public static String grant_type_token = "client_credentials"; // 获取access_token时授权类型，此值固定为“client_credentials”
	public static String grant_type_refreshToken = "refresh_token"; // 获取access_token时，OAUTH定义编码类型
	public static String accessToken_url = url_token; // 获取access_token和refresh_token请求地址
	
	
    /**
     * 定时任务 刷新access_token
     */
	public static long delay = 60; // 步长
	public static long preiod = 29 * 24 * 60 * 60; // 偏移量accesstoken尝试时间
	
	/*
	 * api 方法的调用
	 * */
	
	
	
	public static String[] API_METHOD = {"nuonuo.einvoice.createreq1",
			"nuonuo.speedBilling.prefixQuery",
			"nuonuo.speedBilling.querySpeedBilling ",
			"nuonuo.speedBilling.queryGoldenPlate",
			"nuonuo.speedBilling.queryNameAndTaxByCode",
			"nuonuo.electronInvoice.requestBilling",
			"nuonuo.electronInvoice.CheckEInvoice",
			"nuonuo.electronInvoice.querySerialNum",
			"nuonuo.electronInvoice.queryInvoiceQuantity ",
			"nuonuo.smartCoding.batchTaxCode",
			"nuonuo.synergy.approvalForm",
			"nuonuo.taxAction.getAuthCode",
			"nuonuo.taxAction.updateAuthCode",
			"nuonuo.order.queryList",
			"nuonuo.order.detailsQuery",
			"nuonuo.order.usersAndIntegrals",
			"nuonuo.order.queryAvailableIntegral",
			"nuonuo.basis.BusCallbackCommon",
			"nuonuo.operation.buyMemberPackage ",
			"nuonuo.operation.queryMemberLevel",
			"nuonuo.operation.queryMemberOrder",
			"nuonuo.polymerization.BScanCPayment",
			"nuonuo.polymerization.queryPaymentOrder",
			"nuonuo.polymerization.dynamicOrderScanPayment",
			"nuonuo.polymerization.getInvoiceLinks",
			"nuonuo.cloudTally.queryAccountHolderParam",
			"nuonuo.cloudTally.queryBusinessTemplate",
			"nuonuo.cloudTally.autoMakeAccountingVouchers",
			"nuonuo.cloudTally.queryVoucherOrigin",
			"nuonuo.cloudTally.deleteVoucherOrigin",
			"nuonuo.cloudTally.AddAuxiliaryAccountingItems",
			"nuonuo.cloudTally.queryIntelligentCredentials",
			"nuonuo.cloudTally.newAccountSet",
			"nuonuo.cloudTally.queryAccountingSubjects ",
			"nuonuo.cloudTally.addAccountingSubjects",
			"nuonuo.cloudTally.carryOverGainsLosses",
			"nuonuo.cloudTally.settleAccounts",
			"nuonuo.cloudAgentAccounts.addCustomers",
			"nuonuo.cloudAgentAccounts.modifyCustomers",
			"nuonuo.cloudAgentAccounts.addContract",
			"nuonuo.cloudAgentAccounts.deleteContract ",
			"nuonuo.cloudTally.addPay",
			"nuonuo.cloudTally.deletePay",
			}; 
}
