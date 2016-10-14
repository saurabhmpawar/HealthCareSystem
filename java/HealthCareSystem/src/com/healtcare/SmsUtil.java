package com.healtcare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Saurabh Pawar
 * 
 *         This class is used for sending sms via sms gateway.
 *
 */
public class SmsUtil {

	public static boolean sendSms(String phoneNumber, String message)
			throws Exception {

		boolean result = false;
		System.out.println("Sender :" + phoneNumber);
		System.out.println("message :" + message);

		String uname="YOUR_username";
		String password="********";
		
		
		String USER_AGENT = "Mozilla/5.0";

		String url = "http://businesslead.bulksmss.com/api/sendmsg.php?user="+uname+"&pass="+password+"&sender=RPSUMB&phone="+phoneNumber+"&text="+message+"&priority=ndnd&stype=normal";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		if (responseCode == 200) {
			result = true;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

		return result;
	}

	public static boolean sendIVRCall(String phoneNumber, String message) {

		// call twlio api
		return false;
	}
}
