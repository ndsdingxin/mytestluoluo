package com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.bean;

import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.bean.PrivateData;
import com.iflytek.luoluo.apiluoluo.com.axino.accessPlatform.bean.PublicData;

/**
 * 请求model
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class RequestMode {
	
	private PublicData Public;

	private PrivateData Private;

	public PublicData getPublic() {
		return Public;
	}

	public void setPublic(PublicData public1) {
		Public = public1;
	}

	public PrivateData getPrivate() {
		return Private;
	}

	public void setPrivate(PrivateData private1) {
		Private = private1;
	}
	
}
