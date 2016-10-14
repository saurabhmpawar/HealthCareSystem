package com.healtcare;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class AmbulanceReport extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = -1481003103097094974L;

	private JLabel lblAmbulanceLat;

	private JLabel lblAmbulanceLong;

	private JTextField txtAmbulanceLat;
	private JTextField txtAmbulanceLong;

	JPanel panel3;

	JTable table;
	JFrame JFParentFrame;

	clsSettings settings = new clsSettings();
	clsConnection connect = new clsConnection();

	Connection conn;

	public AmbulanceReport(JFrame getParentFrame) {
		super("Ambulance  Repoert ", true, true, true, true);
		setSize(400, 800);
		JFParentFrame = getParentFrame;
		panel3 = new JPanel();

		table = new JTable();
		panel3.setLayout(new FlowLayout());
		loadTableData();

		JScrollPane scrollPane = new JScrollPane(table);
		
		panel3.add(scrollPane);
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(panel3, "CENTER");

		setFrameIcon(new ImageIcon("src/images/backup.gif"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

	@Override
	public void actionPerformed(ActionEvent element) {

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

}
