package com.healtcare;

/**
 *This is thrown if the the name of the COM Port is invalid
 *@author:Soham Sengupta
 *@version:1.0
 *
 */

public class PortNotReadyException extends Exception
{
	public PortNotReadyException(String strPortName){
		super("Port "+strPortName+" does not exist!");
	}

}