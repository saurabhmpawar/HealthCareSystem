package com.healtcare;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddLoginUser extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 8755118638310025697L;
	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel firstpanel;
	private JPanel panel2;
	private JButton AddBtn;
	private JButton ResetBtn;
	private JButton ExitBtn;
	private JLabel LblEmp_Name1, LblEmp_Name2, Lblretry;
	private JTextField TxtEmp_Name1;

	private static JPasswordField passwordTxt, TxtPass2;
	String dialogmessage;
	String dialogs;

	int dialogtype = JOptionPane.PLAIN_MESSAGE;
	public static int record;

	String Emp_Name1 = "";
	String PASS = "";
	String PASS2 = "";

	// Class Variables

	clsSettings settings = new clsSettings();
	clsConnection connect = new clsConnection();

	Connection conn;

	public AddLoginUser(JFrame getParentFrame) {

		super("Add -LOGIN ", true, true, true, true);
		setSize(400, 800);
		JFParentFrame = getParentFrame;

		firstpanel = new JPanel();
		firstpanel.setLayout(new GridLayout(3, 3));

		LblEmp_Name1 = new JLabel(" Login  Name     :");
		LblEmp_Name2 = new JLabel(" Password        :");
		Lblretry = new JLabel(" Retype Password :");

		// =================================================================
		TxtEmp_Name1 = new JTextField(20);
		passwordTxt = new JPasswordField(20);
		TxtPass2 = new JPasswordField(20);
		// /=================================================

		firstpanel.add(LblEmp_Name1);
		firstpanel.add(TxtEmp_Name1);

		firstpanel.add(LblEmp_Name2);
		firstpanel.add(passwordTxt);

		firstpanel.add(Lblretry);
		firstpanel.add(TxtPass2);
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

			Emp_Name1 = TxtEmp_Name1.getText().trim();
			PASS = passwordTxt.getText().trim();
			PASS2 = TxtPass2.getText().trim();
			try {
				conn = connect.setConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (PASS.equals(PASS2)) {
				if ((PASS.length()) > 5) {
					System.out.println("CORRECT PASSSSSSSSSSSSSSSS");

					try {

						Statement stmt = conn.createStatement();
						if (!Emp_Name1.equals("") && !passwordTxt.equals("")) {
							System.out.println("Login name:" + Emp_Name1
									+ " pass:" + PASS);

							String a = "INSERT INTO login (USERNAME ,PASSWORD)VALUES ('"
									+ Emp_Name1 + "','" + PASS + "')";
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
		passwordTxt.setText("");
		TxtPass2.setText("");
	}

}
