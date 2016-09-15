package com.healtcare;

public class SmsTestClient {

	public static void main(String[] args) {

		System.out.println("------------Shree----------------");

		try {
			SmsUtilImpl sms = new SmsUtilImpl("COM4");

			sms.sendSMS("+919028437425", "hi");

			// System.out.println("message index"+sms.getMsgIndex());

			// ArrayList alSMSStore= null;
			// sms.startReceive(alSMSStore, 1200);
			//System.out					.println("getCurrentMsgIndex:" + sms.getCurrentMsgIndex());

			//System.out.println("getReceivedMessages"					+ sms.getReceivedMessages());

			// System.err.println("data--"+alSMSStore.get(0));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
