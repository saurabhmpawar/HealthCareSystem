package com.healtcare;


public class SmsUtilImpl extends SMSUtil {

	public SmsUtilImpl(String strPortName) throws Exception {
		super(strPortName);
		}

	@Override
	public void processSMS(String strPhNo, String strMsg) throws Exception {
		
		System.out.println("hi sms is recived"+ strMsg  +"from no"+ strMsg);
		
		
		
		
	}

	@Override
	public int getCurrentMsgIndex() throws Exception {
		
		
		
		
		return 1;
	}

}
