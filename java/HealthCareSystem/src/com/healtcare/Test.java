package com.healtcare;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Test {

private	JFrame frame;

private JButton ok;
	Test() {
		
		
		frame= new JFrame("asasdsa");
		ok =new JButton();
		
		ok.setText("OK...");
		
		frame.add(ok);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {

		Test r =new Test();
		
		
	}

}
