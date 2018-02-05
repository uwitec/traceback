package com.tmsps.traceback.util.mail;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tmsps.traceback.model.t_fk_email;
import com.tmsps.traceback.model.t_fk_email_template;

public class MailAliyunTools {

	public static void main(String[] args) {

	}
	
	public static boolean sendMail(String toAddress,t_fk_email email, t_fk_email_template template) {
		IClientProfile profile = DefaultProfile.getProfile(email.getDomainName(), email.getAccesskey(),
				email.getAccessSecret());
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendMailRequest request = new SingleSendMailRequest();
		request.setAccountName(email.getAccountName());
		request.setToAddress(toAddress);
		request.setReplyToAddress(true);
		request.setAddressType(1);
		request.setSubject(template.getSubject());
		request.setHtmlBody(template.getHtmlBody());
		try {
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
