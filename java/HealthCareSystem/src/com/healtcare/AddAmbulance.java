package com.healtcare;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddAmbulance extends JInternalFrame implements ActionListener {


	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel firstpanel;
	private JPanel panel2;
	private JButton AddBtn;
	private JButton ResetBtn;
	private JButton ExitBtn;
	private JLabel LblEmp_Name1, LblEmp_Name2, LblEmp_Name3, LblEmp_Name4, LblEmp_Name5, LblEmp_Name6, LblEmp_Name7, LblEmp_Name8, LblEmp_Name9, LblEmp_Name10;
	private JTextField TxtEmp_Name1, TxtEmp_Name2, TxtEmp_Name3, TxtEmp_Name4, TxtEmp_Name5, TxtEmp_Name6, TxtEmp_Name7, TxtEmp_Name8, TxtEmp_Name9, TxtEmp_Name10;
	private JComboBox Emp_Type;

//	private static JPasswordField passwordTxt, TxtPass2;
	String dialogmessage;
	String dialogs;

	int dialogtype = JOptionPane.PLAIN_MESSAGE;
	public static int record;

	String amb1 = "";
	String amb2 = "";
	String amb3 = "";
	String amb4 = "";
	String amb5 = "";
	String amb6 = "";
	String amb7 = "";
	String amb8 = "";
	String amb9 = "";
	String amb10 = "";
	// Class Variables

	clsSettings settings = new clsSettings();
	clsConnection connect = new clsConnection();

	Connection conn;

	public AddAmbulance(JFrame getParentFrame) {

		super("Add -ambulance details ", true, true, true, true);
		setSize(400, 800);
		JFParentFrame = getParentFrame;

		firstpanel = new JPanel();
		firstpanel.setLayout(new GridLayout(3, 3));
		/*
		`ambulance_id` int(10) NOT NULL,
		  `driver_id` int(10) NOT NULL,
		  `driver_ph` varchar(15) NOT NULL,
		  `hospital_ph` varchar(15) NOT NULL,
		  `hospital_nm` varchar(255) NOT NULL,
		  `ambulance_adr` varchar(255) NOT NULL,
		  `ambulance_lat` decimal(20,16) NOT NULL,
		  `ambulance_long` decimal(20,16) NOT NULL,
		  `ambulane_status` varchar(20) NOT NULL,
		  `city` varchar(50) NOT NULL,
		  `description` varchar(255) NOT NULL,
		  PRIMARY KEY (`ambulance_id`)*/

		LblEmp_Name1 = new JLabel("ambulance no      :");
		LblEmp_Name2 = new JLabel(" driver id       :");
		LblEmp_Name4 = new JLabel(" driver phone       :");
		LblEmp_Name5 = new JLabel(" hospital name       :");
		LblEmp_Name6 = new JLabel(" hospital phone       :");
		LblEmp_Name7 = new JLabel("latitude       :");
		LblEmp_Name8 = new JLabel(" longitude       :");
		LblEmp_Name9 = new JLabel(" city       :");
		LblEmp_Name10 = new JLabel(" discription       :");
		LblEmp_Name3= new JLabel("sss");
		
		// =================================================================
		TxtEmp_Name1 = new JTextField(20);
		TxtEmp_Name2 = new JTextField(20);
		TxtEmp_Name3 = new JTextField(20);
		TxtEmp_Name4 = new JTextField(20);
		TxtEmp_Name5 = new JTextField(20);
		TxtEmp_Name6 = new JTextField(20);
		TxtEmp_Name7 = new JTextField(20);
		TxtEmp_Name8 = new JTextField(20);
		TxtEmp_Name9 = new JTextField(20);
		TxtEmp_Name10 = new JTextField(20);
		
		// /=================================================

		firstpanel.add(LblEmp_Name1);
		firstpanel.add(TxtEmp_Name1);

		firstpanel.add(LblEmp_Name2);
		firstpanel.add(TxtEmp_Name2);
		
		firstpanel.add(LblEmp_Name3);
		firstpanel.add(TxtEmp_Name3);
		
		firstpanel.add(LblEmp_Name4);
		firstpanel.add(TxtEmp_Name4);
		
		firstpanel.add(LblEmp_Name5);
		firstpanel.add(TxtEmp_Name5);
		
		firstpanel.add(LblEmp_Name6);
		firstpanel.add(TxtEmp_Name6);
		
		firstpanel.add(LblEmp_Name7);
		firstpanel.add(TxtEmp_Name7);
		
		firstpanel.add(LblEmp_Name8);
		firstpanel.add(TxtEmp_Name8);
		
		firstpanel.add(LblEmp_Name9);
		firstpanel.add(TxtEmp_Name9);
		
		firstpanel.add(LblEmp_Name10);
		firstpanel.add(TxtEmp_Name10);

		//	firstpanel.add(LblEmp_Name3);
		//firstpanel.add(TxtPass2);
		// =====================================================

		firstpanel.setOpaque(true);
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		AddBtn = new JButton("Add", new ImageIcon("src/images/add.gif"));
		ResetBtn = new JButton("Reset", new ImageIcon("src/images/reset.png"));// reset
		ExitBtn = new JButton("Exit", new ImageIcon("src/images/exit.png"));

		panel2.add(AddBtn);
		AddBtn.addActionListener(this);
		panel2.add(ResetBtn);
		ResetBtn.addActionListener(this);
		panel2.add(ExitBtn);
		ExitBtn.addActionListener(this);
		panel2.setOpaque(true);

		getContentPane().setLayout(new GridLayout(2, 1));
		getContentPane().add(firstpanel, "CENTER");
		getContentPane().add(panel2, "CENTER");
		setFrameIcon(new ImageIcon("src/images/backup.gif"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source.equals(AddBtn)) {

			amb1 = TxtEmp_Name1.getText().trim();
			amb2 = TxtEmp_Name2.getText().trim();
			amb3 = TxtEmp_Name3.getText().trim();
			amb4 = TxtEmp_Name4.getText().trim();
			amb5 = TxtEmp_Name5.getText().trim();
			amb6 = TxtEmp_Name6.getText().trim();
			amb7 = TxtEmp_Name7.getText().trim();
			amb8 = TxtEmp_Name8.getText().trim();
			amb9 = TxtEmp_Name9.getText().trim();
			amb10 = TxtEmp_Name10.getText().trim();
					try {
				conn = connect.setConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}

					String PASS="";
					String PASS2="";
			if (PASS.equals(PASS2)) {
				if ((PASS.length()) > 5) {
					System.out.println("CORRECT PASSSSSSSSSSSSSSSS");

					try {

						Statement stmt = conn.createStatement();
						if (!amb1.equals("") && !amb2.equals("")) {
							// ==========================================insert
							// into table FG ===================================

							System.out.println("Login name:" + amb1
									+ " pass:" + PASS);
							// INSERT INTO Login VALUES ('pavan','aaaa');

							String a = "INSERT INTO login (USERNAME ,PASSWORD)VALUES ('"
									+ amb1 + "','" + PASS + "')";
							int result = stmt.executeUpdate(a);
							if (result == 1) {
								System.out.println("Recorded Added");
								ResetRecord();
								dialogmessage = "Recoard is added!!!";
								dialogtype = JOptionPane.WARNING_MESSAGE;
								JOptionPane.showMessageDialog((Component) null,
										dialogmessage, dialogs, dialogtype);
							}
							// ===============================================================================================

						}

						else {
							dialogmessage = "Empty Record !!!";
							dialogtype = JOptionPane.WARNING_MESSAGE;
							JOptionPane.showMessageDialog((Component) null,
									dialogmessage, dialogs, dialogtype);
						}
						conn.close();
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"GENERAL EXCEPTION", "WARNING!!!",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} else {
					ResetRecord();
					JOptionPane.showMessageDialog(null,
							"Plze inter password at 6-12 charactor",
							"WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
				}

			} else
				ResetRecord();

		}

		else if (source == ResetBtn) {
			ResetRecord();
		} else if (source == ExitBtn) {
			setVisible(false);
			dispose();
		}

	}

	private void ResetRecord() {

		TxtEmp_Name1.setText("");
		TxtEmp_Name2.setText("");
		TxtEmp_Name3.setText("");
		TxtEmp_Name4.setText("");
		TxtEmp_Name5.setText("");
		TxtEmp_Name6.setText("");
		TxtEmp_Name7.setText("");
		TxtEmp_Name8.setText("");
		TxtEmp_Name9.setText("");
		TxtEmp_Name10.setText("");
	}

	public void add_Cat_combo(JComboBox cmb) {

		try {
			conn = connect.setConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM Settings";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String Txtcmb = rs.getString(2).trim();
				record = rs.getInt("Category_Type");

				cmb.addItem(Txtcmb);

			}
			conn.close();
		}

		catch (Exception ex) {

			ex.printStackTrace();

		}

	}

}