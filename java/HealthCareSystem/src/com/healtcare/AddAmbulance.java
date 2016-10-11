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
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class AddAmbulance extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 925464882234346671L;

	JFrame JFParentFrame;
	JDesktopPane desktop;
	JTable table;
	private JPanel firstpanel;

	private JPanel panel2;

	private JPanel panel3;

	private JButton AddBtn;
	private JButton ResetBtn;
	private JButton ExitBtn;

	JLabel lblAmbulanceNumber;
	JLabel lblDriverName;
	JLabel lblDriverPhone;
	JLabel lblHospitalphone;
	JLabel lblHospitalName;
	JLabel lblAmbulanceAddress;
	JLabel lblAmbulanceLat;
	JLabel lblAmbulanceLong;
	JLabel lblCity;
	JLabel lblDescription;

	private JTextField txtAmbulanceNumber;
	private JTextField txtDriverName;
	private JTextField txtDriverPhone;
	private JTextField txtHospitalphone;
	private JTextField txtHospitalName;
	private JTextField txtAmbulanceAddress;
	private JTextField txtAmbulanceLat;
	private JTextField txtAmbulanceLong;
	private JTextField txtCity;
	private JTextField txtDescription;

	int dialogtype = JOptionPane.PLAIN_MESSAGE;
	public static int record;
	String strAmbulanceNumber;
	String strAmbulanceId;
	String strDriverName;
	String strDriverPhone;
	String strHospitalPhone;
	String strHospitalName;
	String strAmbulanceAdderss;
	String strAmbulanceLat;
	String strAmbulanceLong;
	String strAmbulaneStatus;
	String strCity;
	String strAmbulanceAdderssdescription; // Class Variables

	String dialogmessage;
	String dialogs;

	clsSettings settings = new clsSettings();
	clsConnection connect = new clsConnection();

	Connection conn;

	public AddAmbulance(JFrame getParentFrame) {

		super("Add - Ambulance details ", true, true, true, true);

		table = new JTable();

		setSize(400, 800);
		JFParentFrame = getParentFrame;

		firstpanel = new JPanel();
		lblAmbulanceNumber = new JLabel("Ambulance Number   ");
		lblDriverName = new JLabel("Driver Name        ");
		lblDriverPhone = new JLabel("Driver Phone	    ");
		lblHospitalphone = new JLabel("Hospital Phone    ");
		lblHospitalName = new JLabel("Hospital Name      ");
		lblAmbulanceAddress = new JLabel("Ambulance Address  ");
		lblAmbulanceLat = new JLabel("Ambulance latitude ");
		lblAmbulanceLong = new JLabel("Ambulance longitude");
		lblCity = new JLabel("City               ");
		lblDescription = new JLabel("Discription        ");

		// =================================================================

		txtAmbulanceNumber = new JTextField(20);
		txtDriverName = new JTextField(20);
		txtDriverPhone = new JTextField(20);
		txtHospitalphone = new JTextField(20);
		txtHospitalName = new JTextField(20);
		txtAmbulanceAddress = new JTextField(20);
		txtAmbulanceLat = new JTextField(20);
		txtAmbulanceLong = new JTextField(20);
		txtCity = new JTextField(20);
		txtDescription = new JTextField(20);

		// /=================================================

		firstpanel.add(lblAmbulanceNumber);
		firstpanel.add(txtAmbulanceNumber);
		firstpanel.add(lblDriverName);
		firstpanel.add(txtDriverName);
		firstpanel.add(lblDriverPhone);
		firstpanel.add(txtDriverPhone);
		firstpanel.add(lblHospitalphone);
		firstpanel.add(txtHospitalphone);
		firstpanel.add(lblHospitalName);
		firstpanel.add(txtHospitalName);
		firstpanel.add(lblAmbulanceAddress);
		firstpanel.add(txtAmbulanceAddress);
		firstpanel.add(lblAmbulanceLat);
		firstpanel.add(txtAmbulanceLat);
		firstpanel.add(lblAmbulanceLong);
		firstpanel.add(txtAmbulanceLong);
		firstpanel.add(lblCity);
		firstpanel.add(txtCity);
		firstpanel.add(lblDescription);
		firstpanel.add(txtDescription);

		// =====================================================

		firstpanel.setOpaque(true);
		firstpanel.setLayout(new GridLayout(10, 2));
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		AddBtn = new JButton("Add");// , new ImageIcon("src/images/add.gif")
		ResetBtn = new JButton("Reset");// reset, new
										// ImageIcon("src/images/reset.png")
		ExitBtn = new JButton("Exit");// , new ImageIcon("src/images/exit.png")

		panel2.add(AddBtn);
		AddBtn.addActionListener(this);
		panel2.add(ResetBtn);
		ResetBtn.addActionListener(this);
		panel2.add(ExitBtn);
		ExitBtn.addActionListener(this);
		panel2.setOpaque(true);
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		loadTableData();

		JScrollPane scrollPane = new JScrollPane(table);
		panel3.add(scrollPane);
		getContentPane().setLayout(new GridLayout(2, 1));
		getContentPane().add(firstpanel, "CENTER");
		getContentPane().add(panel3, "CENTER");
		getContentPane().add(panel2, "CENTER");

		setFrameIcon(new ImageIcon("src/images/backup.gif"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

	private void loadTableData() {
		try {
			conn = connect.setConnection(conn);
			String query = "select * from ambulance";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source.equals(AddBtn)) {

			strAmbulanceNumber = txtAmbulanceNumber.getText().trim();
			strDriverName = txtDriverName.getText().trim();
			strDriverPhone = txtDriverPhone.getText().trim();
			strHospitalPhone = txtHospitalphone.getText().trim();
			strHospitalName = txtHospitalName.getText().trim();
			strAmbulanceAdderss = txtAmbulanceAddress.getText().trim();
			strAmbulanceLat = txtAmbulanceLat.getText().trim();
			strAmbulanceLong = txtAmbulanceLong.getText().trim();
			strCity = txtCity.getText().trim();
			strAmbulanceAdderssdescription = txtDescription.getText().trim();
			try {
				conn = connect.setConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("CORRECT PASSSSSSSSSSSSSSSS");

			try {

				Statement stmt = conn.createStatement();
				if (!strDriverPhone.equals("") && !strDriverPhone.equals("")
						&& !strHospitalPhone.equalsIgnoreCase("")) {
					// ==========================================insert
					// into table FG ===================================

					String query = "INSERT INTO `healthcare`.`ambulance` ( `ambulance_number`, `driver_name`, `driver_ph`, `hospital_ph`, `hospital_nm`, `ambulance_adr`, `ambulance_lat`, `ambulance_long`, `ambulane_status`, `city`, `description`) VALUES ('"
							+ strAmbulanceNumber
							+ "', '"
							+ strDriverName
							+ "', '"
							+ strDriverPhone
							+ "', '"
							+ strHospitalPhone
							+ "', '"
							+ strHospitalName
							+ "', '"
							+ strAmbulanceAdderss
							+ "', '"
							+ strAmbulanceLat
							+ "', '"
							+ strAmbulanceLong
							+ "', 'ACTIVE', '"
							+ strCity
							+ "', '"
							+ strAmbulanceAdderssdescription + "')";
					int result = stmt.executeUpdate(query);
					if (result == 1) {
						loadTableData();
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
				JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION",
						"WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
			}

		}

		else if (source == ResetBtn) {
			ResetRecord();
		} else if (source == ExitBtn) {
			setVisible(false);
			dispose();
		}

	}

	private void ResetRecord() {
		txtAmbulanceNumber.setText("");
		txtDriverName.setText("");
		txtDriverPhone.setText("");
		txtHospitalphone.setText("");
		txtHospitalName.setText("");
		txtAmbulanceAddress.setText("");
		txtAmbulanceLat.setText("");
		txtAmbulanceLong.setText("");
		txtCity.setText("");
		txtDescription.setText("");
	}

}