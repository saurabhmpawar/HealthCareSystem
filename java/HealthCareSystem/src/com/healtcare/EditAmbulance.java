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

public class EditAmbulance extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 7989244126649664217L;
	JFrame JFParentFrame;
	JDesktopPane desktop;
	private JPanel firstpanel;
	private JPanel panel2, panel3;
	private JButton FindBtn;
	private JButton EditBtn;
	private JButton ExitBtn;

	private JLabel lblAmulanceId;
	private JLabel lblAmbulanceNumber;
	private JLabel lblDriverName;
	private JLabel lblDriverPhone;
	private JLabel lblHospitalphone;
	private JLabel lblHospitalName;
	private JLabel lblAmbulanceAddress;
	private JLabel lblAmbulanceLat;
	private JLabel lblAmbulanceLong;
	private JLabel lblCity;
	private JLabel lblDescription;

	private JTextField txtAmbulanceId;
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

	JTable table;

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

	int dialogtype = JOptionPane.PLAIN_MESSAGE;
	String dialogmessage;
	String dialogs;

	// Class Variables
	clsSettings settings = new clsSettings();
	clsConnection connect = new clsConnection();
	Connection conn;

	public EditAmbulance(JFrame getParentFrame) {
		super("Edit - Ambuance ", true, true, true, true);

		table = new JTable();
		setSize(400, 800);
		JFParentFrame = getParentFrame;
		firstpanel = new JPanel();
		lblAmulanceId = new JLabel("Ambulance Id");
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

		txtAmbulanceId = new JTextField(20);
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

		firstpanel.add(lblAmulanceId);
		firstpanel.add(txtAmbulanceId);
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
		firstpanel.setLayout(new GridLayout(11, 2));
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		loadTableData();

		JScrollPane scrollPane = new JScrollPane(table);
		panel3.add(scrollPane);

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		FindBtn = new JButton("Find");
		EditBtn = new JButton("Edit");
		ExitBtn = new JButton("Exit");

		panel2.add(FindBtn);
		FindBtn.addActionListener(this);
		panel2.add(EditBtn);
		EditBtn.addActionListener(this);
		panel2.add(ExitBtn);
		ExitBtn.addActionListener(this);
		panel2.setOpaque(true);

		getContentPane().setLayout(new GridLayout(2, 1));
		getContentPane().add(firstpanel, "CENTER");
		getContentPane().add(panel3, "CENTER");
		getContentPane().add(panel2, "CENTER");

		setFrameIcon(new ImageIcon("src/images/backup.gif"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		enableMyTextBoxes(false);
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

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source.equals(FindBtn)) {

			strAmbulanceId = txtAmbulanceId.getText().trim();
			// sEmp_Code = TxtEmp_Code.getText().trim();

			try {
				conn = connect.setConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {

				Statement stmt = conn.createStatement();

				if (!strAmbulanceId.equals("")) {
					String query = "select * from ambulance WHERE ambulance_id="
							+ strAmbulanceId;
					ResultSet rs = stmt.executeQuery(query);
					int foundrec = 0;
					while (rs.next()) {
						strAmbulanceNumber = rs.getString(2).trim();// ambulance_number
						strDriverName = rs.getString(3).trim();// driver_name
						strDriverPhone = rs.getString(4).trim();// driver_ph
						strHospitalPhone = rs.getString(5).trim();// hospital_ph
						strHospitalName = rs.getString(6).trim();// hospital_nm

						strAmbulanceAdderss = rs.getString(7).trim();// ambulance_adr

						strAmbulanceLat = rs.getString(8).trim();// ambulance_lat

						strAmbulanceLong = rs.getString(9).trim();// ambulance_long

						strAmbulaneStatus = rs.getString(10).trim();// ambulane_status

						strCity = rs.getString(11).trim();// city

						strAmbulanceAdderssdescription = rs.getString(12)
								.trim();// description

						txtAmbulanceNumber.setText(strAmbulanceNumber);
						txtDriverName.setText(strDriverName);
						txtDriverPhone.setText(strDriverPhone);
						txtHospitalphone.setText(strHospitalPhone);
						txtHospitalName.setText(strHospitalName);
						txtAmbulanceAddress.setText(strAmbulanceAdderss);
						txtAmbulanceLat.setText(strAmbulanceLat);
						txtAmbulanceLong.setText(strAmbulanceLong);
						txtCity.setText(strCity);
						txtDescription.setText(strAmbulanceAdderssdescription);

						foundrec = 1;

					}

					if (foundrec == 0) {
						dialogmessage = "No Such Ambulance";

						dialogtype = JOptionPane.WARNING_MESSAGE;
						JOptionPane.showMessageDialog((Component) null,
								dialogmessage, dialogs, dialogtype);
						ResetRecord();

					} else {
						enableMyTextBoxes(true);
						txtAmbulanceId.setEditable(false);
					}

				} else {
					dialogmessage = "No Blank Field Allowed";

					dialogtype = JOptionPane.WARNING_MESSAGE;
					JOptionPane.showMessageDialog((Component) null,
							dialogmessage, dialogs, dialogtype);
					ResetRecord();

				}
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nUnknown Error");
			}

		}

		else if (source == EditBtn) {

			// sEmp_Code = TxtEmp_Code.getText().trim();
			// sEmp_Name1 = TxtEmp_Name1.getText().trim();
			// sEmp_Name2 = TxtEmp_Name2.getText().trim();
			// sEmp_Desi = (String) Emp_Type.getSelectedItem();
			// sEmp_Add = TxtEmp_Add.getText().trim();
			// sEmp_No = TxtEmp_No.getText().trim();

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
			try {

				Statement stmt = conn.createStatement();

				if (!strAmbulanceNumber.equals("") && !strDriverName.equals("")
						&& !strDriverPhone.equals("")
						&& !strHospitalPhone.equals("")
						&& !strHospitalName.equals("")
						&& !strAmbulanceAdderss.equals("")
						&& !strAmbulanceLat.equals("")
						&& !strAmbulanceLong.equals("")
						&& !strAmbulaneStatus.equals("") && !strCity.equals("")
						&& !strAmbulanceAdderssdescription.equals(""))

				{
					String temp = "UPDATE ambulance SET "
							+ "ambulance_number = '" + strAmbulanceNumber
							+ "',driver_name = '" + strDriverName
							+ "',driver_ph = '" + strDriverPhone
							+ "',hospital_ph = '" + strHospitalPhone
							+ "',hospital_nm = '" + strHospitalName
							+ "',ambulance_adr = '" + strAmbulanceAdderss
							+ "',city = '" + strCity + "',description = '"
							+ strAmbulanceAdderssdescription
							+ "',ambulance_lat = " + strAmbulanceLat
							+ ",ambulance_long = " + strAmbulanceLong
							+ "WHERE ambulance_id = " + strAmbulanceId;
					System.out.println("Update query= " + temp);

					int result = stmt.executeUpdate(temp);
					if (result == 1) {
						loadTableData();
						dialogmessage = "Record Updated!!!";
						dialogtype = JOptionPane.INFORMATION_MESSAGE;
						JOptionPane.showMessageDialog((Component) null,
								dialogmessage, dialogs, dialogtype);
						ResetRecord();

					} else {
						dialogmessage = "NO SUCH EMPLOYEE!!!";
						dialogtype = JOptionPane.WARNING_MESSAGE;
						JOptionPane.showMessageDialog((Component) null,
								dialogmessage, dialogs, dialogtype);

					}

				}

				else {
					dialogmessage = "NULL VALUES OCCURED IN TEXTFIELD!!!";
					dialogtype = JOptionPane.WARNING_MESSAGE;
					JOptionPane.showMessageDialog((Component) null,
							dialogmessage, dialogs, dialogtype);
					ResetRecord();

				}

				conn.close();

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "GENERAL EXCEPTION",
						"WARNING!!!", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Error in Edi Btn" + ex);
			}

		}

		else if (source == ExitBtn) {

			setVisible(false);
			dispose();
		}

	}

	private void enableMyTextBoxes(Boolean enabled) {

		txtAmbulanceId.setEditable(!enabled);
		txtAmbulanceNumber.setEditable(enabled);
		txtDriverName.setEditable(enabled);
		txtDriverPhone.setEditable(enabled);
		txtHospitalphone.setEditable(enabled);
		txtHospitalName.setEditable(enabled);
		txtAmbulanceAddress.setEditable(enabled);
		txtAmbulanceLat.setEditable(enabled);
		txtAmbulanceLong.setEditable(enabled);
		txtCity.setEditable(enabled);
		txtDescription.setEditable(enabled);
	}

	private void ResetRecord() {
		enableMyTextBoxes(false);
		txtAmbulanceId.setText("");
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
