package com.healtcare;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7493642523427932220L;

	static JFrame frame;

	private static JPanel panel1;
	private static JPanel panel2;
	private static JPanel panel3;
	private JButton loginBtn;
	private JButton exitBtn;
	int dialogtype = JOptionPane.PLAIN_MESSAGE;
	String dialogmessage;
	String dialogs;
	private JLabel nameLbl;
	private JLabel userLbl;
	private JLabel passwordLbl;
	private static JTextField userTxt;
	private static JPasswordField passwordTxt;

	public String loginname;
	public String loginpass;

	// class Veriables
	clsConnection connect = new clsConnection();
	// Connection variable

	Connection conn;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	static Date td = new Date();

	public LoginWindow() {
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		nameLbl = new JLabel("WELCOME TO HealthCare  SYSTEM");

		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 2));
		userLbl = new JLabel("Username :");
		userTxt = new JTextField(20);

		passwordLbl = new JLabel("Password :");

		passwordTxt = new JPasswordField(20);

		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());

		loginBtn = new JButton("Login", new ImageIcon("src/images/key.gif"));

		loginBtn.addActionListener(this);
		exitBtn = new JButton("Exit", new ImageIcon("src/images/Keys.gif"));

		exitBtn.addActionListener(this);
		panel1.add(nameLbl);
		panel1.setOpaque(true);
		panel2.add(userLbl);
		panel2.add(userTxt);
		panel2.add(passwordLbl);
		panel2.add(passwordTxt);
		panel2.setOpaque(true);
		panel3.add(loginBtn);
		panel3.add(exitBtn);
		panel3.setOpaque(true);
		frame = new JFrame("Healthcare  User Login...");
		frame.setSize(300, 200);

		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		// pane.setLayout(new GridLayout(3,1));
		pane.add(panel1);
		pane.add(panel2);
		pane.add(panel3);
		frame.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		System.out.println("hi");
		LoginWindow frame1 = new LoginWindow();
		frame1.addNotify();
		frame1.pack();

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();
		if (source.equals(loginBtn)) {
			login();

		} else if (source.equals(exitBtn)) {
			System.exit(0);

		}

	}

	public void login() {
		loginname = userTxt.getText().trim();
		loginpass = passwordTxt.getText().trim();

		try {
			conn = connect.setConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();

		}
		try {

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM login WHERE USERNAME='" + loginname
					+ "'AND PASSWORD='" + loginpass + "'";
			ResultSet rs = stmt.executeQuery(query);
			boolean recordfound = rs.next();
			if (recordfound) {

				dialogmessage = "Welcome - " + loginname;
				dialogtype = JOptionPane.INFORMATION_MESSAGE;
				JOptionPane.showMessageDialog((Component) null, dialogmessage,
						dialogs, dialogtype);
				userTxt.setText("");
				passwordTxt.setText("");
				frame.setVisible(false);
				frame.dispose();

				Dashboard myDashboard = new Dashboard(loginname, td);
			} else {
				dialogmessage = "Login Failed!";
				JOptionPane.showMessageDialog(null, "INVALID ID OR PASSWORD!",
						"WARNING!!", JOptionPane.WARNING_MESSAGE);

				userTxt.setText("");
				passwordTxt.setText("");
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION",
					"WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
