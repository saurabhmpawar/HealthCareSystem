package com.healtcare;



import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

/**
  * This class is an abstract class to send and receive text SMS with a GSM Modem. It uses Sun's Java Communication API for windows. It can be,
  * however, migrated to Rx-Tx, too. This is a simple API, which works with many tested devices like Smasung SGH-B520 etc., which cost very little
  * as compared to other trendy handsets.
  * You can use this API with J2SE 1.4 and above.
  *@auhor:Soham Sengupta
  *@version:1.0
  */


public abstract class SMSUtil implements Runnable
{
		 private Enumeration portList;
    	 private CommPortIdentifier portId;
		 private SerialPort serialPort;
		 private OutputStream outputStream;
		 private String strPortName;
		 private InputStream inputStream;
		 private boolean boolKeepReceiving=true;
		 private Thread threadRX;
		 private ArrayList alSMSStore;
		 private int intDelay;

		/**
		  * This is the constructor that takes as argument the name of the COM Port where the GSM device is connected to. If the port is owned by another application or does not exist,
		  * Exception is thrown.

		  */

		 public SMSUtil(String strPortName) throws Exception{
		 		this.strPortName=strPortName;
	 			initCommPort();

		 }

		 private void initCommPort() throws Exception{
		 	 	boolean boolPortOK=false;
		 	 	portList = CommPortIdentifier.getPortIdentifiers();
		 	 	
		 	 	while (portList.hasMoreElements()) {
		 	           portId = (CommPortIdentifier) portList.nextElement();
		 	            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
		 		                if (portId.getName().equalsIgnoreCase(strPortName)) {
		 		                    this.serialPort = (SerialPort)portId.open("SimpleWriteApp", 2000);
		 		                    outputStream = serialPort.getOutputStream();
		 							inputStream=serialPort.getInputStream();

		 		  					serialPort.notifyOnDataAvailable(true);
		 		                    serialPort.setSerialPortParams(9600,
		 		                            SerialPort.DATABITS_8,
		 		                            SerialPort.STOPBITS_1,
		 		                            SerialPort.PARITY_NONE);
		 		                            System.out.println("Here I come!");
		 		                    boolPortOK=true;
		 							break;
		 						}


		 	 			}
		 		}
		 		if(!boolPortOK) throw new PortNotReadyException(strPortName);

		 	}


	private String readSMSByIndex(int index) throws Exception{

		final String INIT_ME="AT+CPMS=\"ME\"\r";
		final String TXT_MODE="AT+CMGF=1\r";
		final String AT_LIST="AT+CMGR="+index+"\r";
		StringBuffer sb=new StringBuffer();

		writeATCmd(INIT_ME);
		writeATCmd(TXT_MODE);
		sb.append(writeATCmd(AT_LIST));
		return sb.toString();
	}


	private String writeATCmd(String strATCmd) throws Exception{
			outputStream.write(strATCmd.getBytes());
			outputStream.flush();

			byte[] data=new byte[1024];
			int ch=inputStream.read(data);
			String str=new String(data,0,ch);
			System.out.println(str);
			return str;

	}

	private void writeATCmdSMS(String strATCmd) throws Exception{
				System.out.println("strATCmd:"+strATCmd);
				outputStream.write(strATCmd.getBytes());
				outputStream.flush();
				System.out.println("Done");
				byte[] data=new byte[20];
				int ch=inputStream.read(data);
				System.out.println("output:"+new String(data,0,ch));



			}


/**
  * This method is used to send an SMS to a phone no with a text message.
  *  The first argument is the phone number and the second one is the message  text
  *
  */

 final public void sendSMS(String strPhNo,String strMsg) throws Exception{

			final String STR_AT_CMGF="AT+CMGF=1\r";
					final String STR_AT_CMGS="AT+CMGS=\""+strPhNo+"\"\r";
					final String STR_CTRL_Z=strMsg+(char)26+"\r";

					writeATCmdSMS("ATD 9028437425;\r");

					//writeATCmdSMS("AT");

					writeATCmdSMS(STR_AT_CMGF);

					writeATCmdSMS(STR_AT_CMGS);

					writeATCmdSMS(STR_CTRL_Z);


	}

	private String getPhoneNoFromRsp(String rsp){
			final String OFFSET_PH_NO="READ\",\"";
			final int PH_NO_LNGTH=13;
			int offset=rsp.indexOf(OFFSET_PH_NO)+OFFSET_PH_NO.length();
			int end=rsp.indexOf("\"",offset);
			return rsp.substring(offset,end);



		}

	private	String getMsgFromRsp(String str){

			int offset1=str.indexOf("/");
			int off1=str.indexOf("\"",offset1)+1;
			int endOffset=str.lastIndexOf(",0,1");
			String msg=str.substring(off1,endOffset).trim();
			return msg;

	}

	public int getMsgIndex() throws Exception{
		int intCount=1;
		final String ERROR="ERROR:321";
		while(true){
			try{
				String str=this.readSMSByIndex(intCount);
				if(str.toUpperCase().indexOf(ERROR)!=-1){
					break;
				}else{
					intCount++;
				}
			}catch(Throwable t){
			t.printStackTrace();
			}

		}

		return intCount;

	}




	private void startReceivingSMS() throws Exception{
			final String ERROR="ERROR";

			while(boolKeepReceiving){
				int cmgr=getCurrentMsgIndex();
				Thread.sleep(intDelay);

						try{

						String str=this.readSMSByIndex(cmgr);
						if(str.toUpperCase().indexOf(ERROR)!=-1){
							System.out.print(".");
						}else{
								String phone=this.getPhoneNoFromRsp(str);
								String msg=this.getMsgFromRsp(str);
								alSMSStore.add(new String[]{phone,msg});
								System.out.println("message"+msg);
								processSMS(phone,msg);

						}
					}catch(Throwable t){

						//t.printStackTrace();
						System.out.println("ERROR RECEIVING MSG");
						t.printStackTrace();

					}



			}


	}

	/**
	  * This method starts receiving a message in a separate thread.
	  * The arguments are an ArrayList where the Sender's number and message text are Stored in a String[] as element, and an int delay that the program
	  * waits for before checking an SMS
	  */

	final public void startReceive(ArrayList alSMSStore,int intDelay) throws Exception{
		this.alSMSStore=alSMSStore;
		this.intDelay=intDelay;
		threadRX=new Thread(this);
		threadRX.start();
	}

	final public void run(){
		try{
			startReceivingSMS();
		}catch(Throwable t){
			t.printStackTrace();
		}
	}

	/**
	  * This method is used to stop sending SMS.
	  *
	  *
	  */

	final public void stopReceivingSMS(){
		this.boolKeepReceiving=false;
	}


	/**
	 * This method returns an ArrayList containing String[]'s where [0] is the phone number and [1] is the msg text
	 *
	 *
	 */



	public ArrayList getReceivedMessages(){
			return this.alSMSStore;
	}


	/**
	 * This method is abstract and must be defined in the subclass so as to customize the action to be taken on receiving an SMS
	 * It takes Phone No. and Message Text Received as argument in form of String both, for further customization
	 *
	 */


	public abstract void processSMS(String strPhNo,String strMsg) throws Exception;



	/**
	  * This method is abstract and must be defined in the sub class so as to tell the program where to start from while reading the SMS.
	  * If you want to continuously receive SMS, then you should ideally provide the code in this method definition to store the index of last saved
	  * index of the SMS in a database table for future. Even if the machine is turned off, the GSM device will receive message(s) and when the PC is
	  * turned on again, it'd just process all the new SMS. You should also update the msg index in the database table accordingly after having processed
	  * an SMS
	  */

	public abstract int getCurrentMsgIndex() throws Exception;







}







