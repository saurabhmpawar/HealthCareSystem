package com.healtcare;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AmbulanceReport extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = -1481003103097094974L;

	private JLabel lblAmbulanceLat;

	private JLabel lblAmbulanceLong;

	private JTextField txtAmbulanceLat;
	private JTextField txtAmbulanceLong;

	JFrame JFParentFrame;

	public AmbulanceReport(JFrame getParentFrame) {
		super("Delete - Employee ", true, true, true, true);
		setSize(400, 800);
		JFParentFrame = getParentFrame;
		
		

	}

	@Override
	public void actionPerformed(ActionEvent element) {

	}

}
