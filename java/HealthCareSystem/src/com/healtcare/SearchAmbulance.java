package com.healtcare;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class SearchAmbulance extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 6990054067536502728L;
	Connection conn;

	JFrame JFParentFrame;

	JLabel lblSearchCaption;
	JTextField txtSearch;
	JTable table;
	JButton btnFind;

	JComboBox<String> searchoption;

	String strinputText;
	String strSelectedoption;
	private JPanel firstpanel;

	private JPanel panel2;

	clsSettings settings = new clsSettings();
	clsConnection connect = new clsConnection();

	public SearchAmbulance(JFrame getParentFrame) {

		super("Add - Ambulance details ", true, true, true, true);

		table = new JTable();

		setSize(400, 800);
		JFParentFrame = getParentFrame;
		searchoption = new JComboBox<String>();

		btnFind = new JButton("Find");
		firstpanel = new JPanel();
		panel2 = new JPanel();
		lblSearchCaption = new JLabel("Searcg by:");
		txtSearch = new JTextField(50);
		searchoption.addItem("ambulance_id");
		searchoption.addItem("driver_name");
		searchoption.addItem("driver_ph");
		searchoption.addItem("hospital_ph");
		searchoption.addItem("hospital_nm");
		searchoption.addItem("ambulance_adr");
		searchoption.addItem("ambulance_lat");
		searchoption.addItem("ambulance_long");
		searchoption.addItem("ambulane_status");
		searchoption.addItem("city");
		searchoption.addItem("description");

		firstpanel.add(lblSearchCaption);
		firstpanel.add(searchoption);
		firstpanel.add(txtSearch);
		firstpanel.add(btnFind);
		btnFind.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane(table);
		panel2.add(scrollPane);

		getContentPane().setLayout(new GridLayout(2, 1));
		getContentPane().add(firstpanel, "CENTER");
		getContentPane().add(panel2, "CENTER");

		setFrameIcon(new ImageIcon("src/images/search.png"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if (source.equals(btnFind)) {

			strinputText = txtSearch.getText().trim();
			strSelectedoption = searchoption.getSelectedItem().toString();

			loadTableData();

		}
	}

	private void loadTableData() {
		try {
			conn = connect.setConnection(conn);
			String query = "select * from ambulance where " + strSelectedoption
					+ " like'%" + strinputText + "%'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
