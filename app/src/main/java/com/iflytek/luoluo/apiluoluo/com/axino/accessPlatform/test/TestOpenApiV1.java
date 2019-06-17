/*
package com.axino.accessPlatform.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.events.StartDocument;

import com.axino.accessPlatform.bean.PrivateData;
import com.axino.accessPlatform.bean.PublicData;
import com.axino.accessPlatform.bean.RequestMode;
import com.axino.accessPlatform.exception.OpensnsException;
import com.axino.accessPlatform.service.OpenApiV1;
import com.axino.accessPlatform.util.SecurityUtil;
import com.axino.accessPlatform.util.StateData;
import com.axino.accessPlatform.util.ValidataUtil;

*/
/**
 * 测试示例
 * 
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 *//*

public class TestOpenApiV1 {
	private static String shuihao = StateData.user_Tax;
	private static final String[] TAXNUM = { "taxnum","taxNum"};
	private static String INVOICETIMESTART ="invoiceTimeStart";
	private static String INVOICETIMEEND = "invoiceTimeEnd";
	private static String start = "2018-01-01 00:00:00";
	private static String end = "2019-05-01 23:59:59";
	public static void main(String[] args) {

		// 定义请求头数据
		// 定义请求体中的公共数据
		// 定义请求体中的业务数据 PrivateData<Object>
		// 生成6位不重复的随机数字
		// api版本
		Map<String, String> headers = getHeaders(); 
		//公共请求参数消息体
		PublicData pdData = getPublicData(); 
		
		PrivateData<Object> pvData = getPvData();
				
		RequestMode requestMode = getRequestMode(pdData, pvData);
		OpenApiV1 sdk = new OpenApiV1();
		String result = "";
		try { 
			result = sdk.handle(StateData.url, headers, requestMode);
			if (!ValidataUtil.isEmpty(result)) {
				System.out.println("服务端的响应：" + result);
			}
		} catch (OpensnsException e) {
			System.out.printf("Request failed 【" + e.getErrorCode() + ":" + e.getMessage() + "】");
			e.printStackTrace();
		}

		//mytest();
	}

	private static PrivateData<Object> getPvData() {
		
		new PrivateData<Object>();
		PrivateData<Object> pvData = new PrivateData<Object>();
		
		//私有请求参数消息体
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(TAXNUM[0], StateData.taxNum);
		map.put(INVOICETIMESTART,start);
		map.put(INVOICETIMEEND, end);
		list.add(map);
		pvData.setServicedata(list);
		return pvData;
	}

	// 定义请求头数据
	private static Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("userTax", StateData.user_Tax); // ISV下商家需要填写当前商户税号，普通商家模式用户可以不填写此值
		headers.put("compress", StateData.app_compressType);// 压缩方式：提供GZIP 置空“”不压缩
		headers.put("appKey", StateData.app_key); // 用户申请的appkey
		headers.put("appRate", StateData.app_rate); // app并发请求数 ，平台默认10如需升级请联系开放平台
		headers.put("dataType", StateData.app_dataType); // 数据请求格式： JSON/XML
		headers.put("signMethod", StateData.app_signType); // 加密方式：提供AES/AES，不可为空
		headers.put("accessToken", StateData.app_accessToken); // 用户Oauth登录后得到的令牌accessToken
		headers.put("Content-Type", StateData.contentType); // http发送模式
		
		return headers;
	}

	// 定义请求体中的公共数据
	private static PublicData getPublicData() {
		PublicData pdData = new PublicData();
		pdData.setVersion(StateData.app_apiVersion); // API版本
		pdData.setTimestamp(String.valueOf(System.currentTimeMillis()));
		pdData.setMethod(StateData.API_METHOD[8]);// API名称
		return pdData;
	}

	private static RequestMode getRequestMode(PublicData pdata, PrivateData<Object> pvData) {
		RequestMode requestMode = new RequestMode();
		requestMode.setPrivate(pvData);
		requestMode.setPublic(pdata);
		return requestMode;
	}

	public static void mytest() {
		// 定义请求头数据
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("appKey", "i3DD5GRf");
		headers.put("accessToken", "529397f243ddbe6f7ac53c01c1191d94");// 通过Oauth获取的令牌
		headers.put("compress", "GZIP");// 压缩方式,例：GZIP;当请求数据需要压缩时,compress值设置GZIP,同时数据在URLEncoder编码后调用压缩方法
		headers.put("signMethod", "AES");// 加密方式
		headers.put("dataType", "JSON");// 数据类型
		headers.put("appRate", StateData.app_rate);// 平台默认10次/秒
		
		
		headers.put("userTax", StateData.user_Tax); // ISV下商家需要填写当前商户税号，普通商家模式用户可以不填写此值
		headers.put("Content-Type", StateData.contentType); // http发送模式
		// 定义请求体中的公共数据
		
		
		PublicData pdData = new PublicData();
		pdData.setVersion("1.0"); // api版本
		pdData.setTimestamp(String.valueOf(System.currentTimeMillis()));
		pdData.setMethod("nuonuo.einvoice.createreq1"); // API名称
		// 定义请求体中的业务数据
		PrivateData<Object> pvData = new PrivateData<Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("signid", "6772af8821cb259bd9f30e175e903321");
		map.put("shuihao", "330681687889191");
		map.put("validator", "123456");
		list.add(map);
		pvData.setServicedata(list);
		RequestMode requestMode = new RequestMode();
		requestMode.setPrivate(pvData);
		requestMode.setPublic(pdData);
		OpenApiV1 sdk = new OpenApiV1();
		// 设置请求接口的url
		// sdk.setServerName(StateData.url);
		String result = "";
		try {
			// 调用服务
			result = sdk.handle(StateData.url, headers, requestMode);
			if (!ValidataUtil.isEmpty(result)) {
				System.out.println("服务端的响应：" + URLDecoder.decode(result, "UTF-8"));
			}
		} catch (OpensnsException e) {
			System.out.printf("Request failed [" + e.getErrorCode() + ":" + e.getMessage() + "]");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
*/
