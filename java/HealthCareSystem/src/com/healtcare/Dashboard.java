package com.healtcare;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * 
 * @author saurabh pawar
 *
 */
public class Dashboard extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2073178829274522057L;
	JDesktopPane desktop = new JDesktopPane();
	String sMSGBOX_TITLE = "Health Care System V. 1.0";

	// Menu Bar Variables

	JMenuBar menubar = new JMenuBar();
	AddLoginUser addLoginUser;
	AddAmbulance addAmbulanceObj;
	JMenu menuFile = new JMenu("File");
	JMenu menuAmbulance = new JMenu("Ambuance");
	JMenu menuTools = new JMenu("Tools");
	JMenu menuReports = new JMenu("Reports");
	JMenu menuHelp = new JMenu("Help");

	// Menu Item

	JMenuItem itemAddLogin = new JMenuItem();
	JMenuItem itemExit = new JMenuItem();

	JMenuItem itemAdd = new JMenuItem();
	JMenuItem ambulanceEdit = new JMenuItem();
	JMenuItem itemDelete = new JMenuItem();
	JMenuItem searchAmbulance = new JMenuItem();

	JMenuItem sendSms = new JMenuItem();

	JMenuItem itemCalculator = new JMenuItem();
	JMenuItem itemNotePad = new JMenuItem();

	JMenuItem itemEmprpt = new JMenuItem();

	JMenuItem itemAuthor = new JMenuItem();
	JMenuItem itemHelp = new JMenuItem();

	// JPanel

	JPanel panel_Bottom = new JPanel();
	JPanel panel_Top = new JPanel();

	// Label

	JLabel lblUsername = new JLabel("User Name:");
	JLabel lblLogDetails = new JLabel("Time Login :");
	JLabel lblTimeNow = new JLabel();

	// TextField
	JTextField username = new JTextField();
	JTextField logtime = new JTextField();

	// JInternalFrame variables

	// Addwindow FormAddwindow;
	// Editwindow FormEditwindow;
	// Deletewindow FormDeletewindow;
	// Settingswindow FormSettingswindow;

	// /Emprptwindow FormEmprptwindow;

	// Settingswindow FormSettingswindow;

	// Authorwindow FormAuthorwindow;
	// Helpwindow FormHelpwindow;

	// Connection Variables

	Connection conn;

	// Date variables

	static Date td = new Date();

	// String Variables

	static Statement stmtLogin;

	// Class Variables
	clsSettings settings = new clsSettings();

	// // User Details
	static String sUser = "";
	static String sLogin = DateFormat.getDateTimeInstance().format(td);

	public Dashboard(String user, Date date) {
		super("Health Care System [Version 1.0]");
		sUser = user;
		td = date;

		JTextField username = new JTextField();
		username.setEditable(false);
		JTextField logtime = new JTextField();
		logtime.setEditable(false);
		username.setText(sUser);
		logtime.setText(sLogin);

		panel_Bottom.setLayout(new FlowLayout());
		panel_Bottom.setPreferredSize(new Dimension(10, 25));
		// panel_Bottom.add(lblUserIcon);
		panel_Bottom.add(lblUsername);
		panel_Bottom.add(username);
		panel_Bottom.add(lblLogDetails);
		panel_Bottom.add(logtime);

		panel_Top.setLayout(new BorderLayout());
		panel_Top.setPreferredSize(new Dimension(10, 65));
		panel_Top.add(createJToolBar(), BorderLayout.PAGE_START);

		desktop.setBackground(Color.WHITE);
		desktop.setAutoscrolls(true);
		desktop.setBorder(BorderFactory.createLoweredBevelBorder());
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		getContentPane().add(panel_Top, BorderLayout.PAGE_START);
		getContentPane().add(desktop, BorderLayout.CENTER);
		getContentPane().add(panel_Bottom, BorderLayout.PAGE_END);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				UnloadWindow();
			}
		});

		setJMenuBar(CreateJMenuBar());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon("src/images/Business.png").getImage());
		setSize(700, 700);
		setLocation(2, 2);
		show();

	}

	protected JMenuBar CreateJMenuBar() {

		// creating Submenu
		// Menu File
		menuFile.add(settings.setJMenuItem(itemAddLogin, "Add Login",
				"src/images/employee.png"));
		menuFile.add(settings.setJMenuItem(itemExit, "Quit",
				"src/images/exit.png"));

		itemExit.addActionListener(this);

		itemAddLogin.addActionListener(this);

		// MEnu Ambulance
		menuAmbulance.add(settings.setJMenuItem(itemAdd, "Add Ambulance",
				"src/images/employee.png"));
		menuAmbulance.add(settings.setJMenuItem(ambulanceEdit,
				"Edit Ambulance", "src/images/edit.png"));
		menuAmbulance.addSeparator();
		menuAmbulance.add(settings.setJMenuItem(itemDelete, "Delete Ambulance",
				"src/images/delete.png"));

		menuAmbulance.addSeparator();

		menuAmbulance.add(settings.setJMenuItem(searchAmbulance,
				"Search Ambulance", "src/images/search.png"));

		menuAmbulance.addSeparator();

		menuAmbulance.add(settings.setJMenuItem(sendSms, "Send Sms",
				"src/images/mspaint.png"));

		itemAdd.addActionListener(this);
		ambulanceEdit.addActionListener(this);
		itemDelete.addActionListener(this);
		searchAmbulance.addActionListener(this);
		sendSms.addActionListener(this);

		// setting tool bar

		menuTools.add(settings.setJMenuItem(itemCalculator, "Calculator",
				"src/images/calc.png"));
		menuTools.addSeparator();
		menuTools.add(settings.setJMenuItem(itemNotePad, "NotePad",
				"src/images/notepad.png"));

		itemCalculator.addActionListener(this);
		itemNotePad.addActionListener(this);

		// setting Reports bar

		menuReports.add(settings.setJMenuItem(itemEmprpt, "Ambulance Report",
				"src/images/emp_rpt.png"));
		menuTools.addSeparator();
		menuTools.addSeparator();
		itemEmprpt.addActionListener(this);

		// setting Help

		menuHelp.add(settings.setJMenuItem(itemAuthor, "About Author",
				"src/images/xp.png"));
		menuHelp.add(settings.setJMenuItem(itemHelp, "Help",
				"src/images/help.png"));

		itemAuthor.addActionListener(this);
		itemHelp.addActionListener(this);

		// adding menuitem to menubar

		menubar.add(settings.setJMenu(menuFile));
		menubar.add(settings.setJMenu(menuAmbulance));
		menubar.add(settings.setJMenu(menuTools));
		menubar.add(settings.setJMenu(menuReports));
		//menubar.add(settings.setJMenu(menuHelp));
		return menubar;

	}

	protected JToolBar createJToolBar() {
		JToolBar toolbar = new JToolBar("Toolbar");

		toolbar.add(settings.CreateJToolbarButton("Exit",
				"src/images/exit.png", "File_Exit", JToolBarActionListener));
		toolbar.addSeparator();
		toolbar.addSeparator();

		toolbar.add(settings.CreateJToolbarButton("Add - Ambulance",
				"src/images/employee.png", "Emp_Add", JToolBarActionListener));

		toolbar.add(settings.CreateJToolbarButton("Edit - Ambulance",
				"src/images/edit.png", "Emp_Edit", JToolBarActionListener));
		toolbar.addSeparator();

		toolbar.add(settings.CreateJToolbarButton("Delete - Ambulance",
				"src/images/delete.png", "Emp_Delete", JToolBarActionListener));
		toolbar.addSeparator();
		toolbar.addSeparator();

		toolbar.add(settings.CreateJToolbarButton("Find Abulance",
				"src/images/setting.png", "Settings", JToolBarActionListener));
		toolbar.add(settings.CreateJToolbarButton("Calculator",
				"src/images/calc.png", "Tools_Calculator",
				JToolBarActionListener));
		toolbar.add(settings.CreateJToolbarButton("NotePad",
				"src/images/notepad.png", "Tools_NotePad",
				JToolBarActionListener));
		toolbar.addSeparator();
		toolbar.addSeparator();

		toolbar.add(settings.CreateJToolbarButton("Ambulance - Report",
				"src/images/emp_rpt.png", "Reports_Ambulance",
				JToolBarActionListener));

		/*
		 * toolbar.add(settings.CreateJToolbarButton("Help - Author",
		 * "src/images/xp.png", "Help_Author", JToolBarActionListener));
		 * 
		 * toolbar.add(settings.CreateJToolbarButton("Help - Help",
		 * "src/images/help.png", "Help_Help", JToolBarActionListener));
		 */
		return toolbar;

	}

	ActionListener JToolBarActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String source = e.getActionCommand();

			if (source == "File_Exit") {
				loadJInternalFrame(2);
			} else if (source == "Emp_Add") {
				loadJInternalFrame(3);
			} else if (source == "Emp_Edit") {
				loadJInternalFrame(4);
			} else if (source == "Emp_Delete") {
				loadJInternalFrame(5);
			} else if (source == "Settings") {
				loadJInternalFrame(6);
			} else if (source == "Tools_Calculator") {
				loadJInternalFrame(7);
			} else if (source == "Tools_NotePad") {
				loadJInternalFrame(8);
			} else if (source == "Reports_Ambulance") {
				loadJInternalFrame(9);
			}

			else if (source == "Help_Author") {
				loadJInternalFrame(11);
			} else if (source == "Help_Help") {
				loadJInternalFrame(12);
			}
		}

	};

	public void actionPerformed(ActionEvent event) {
		Object object = event.getSource();

		if (object == itemAddLogin) {
			loadJInternalFrame(1);
		}

		if (object == itemExit) {
			loadJInternalFrame(2);
		} else if (object == itemAdd) {
			loadJInternalFrame(3);
		} else if (object == ambulanceEdit) {
			loadJInternalFrame(4);
		} else if (object == itemDelete) {
			loadJInternalFrame(5);
		} else if (object == searchAmbulance) {
			loadJInternalFrame(6);
		} else if (object == itemCalculator) {
			loadJInternalFrame(7);

		} else if (object == itemNotePad) {
			loadJInternalFrame(8);
		} else if (object == itemEmprpt) {
			loadJInternalFrame(9);
		}
		else if (object == sendSms) {
			loadJInternalFrame(12);
		} else if (object == itemHelp) {
			loadJInternalFrame(13);
		}
	}

	private void loadJInternalFrame(int intWhich) {
		switch (intWhich) {

		case 1:

			try {
				addLoginUser = new AddLoginUser(this);
				loadForm("Add Admin", addLoginUser);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nError");
			}
			break;
		case 2:
			System.exit(0);
			break;

		case 3:
			try {
				addAmbulanceObj = new AddAmbulance(this);
				loadForm("Add Ambulance", addAmbulanceObj);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nError");
			}
			break;

		case 4:
			try {

				// FormEditwindow = new Editwindow(this);
				loadForm("Edit Ambulance", new EditAmbulance(this));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nError");
			}
			break;

		case 5:
			try {
				// FormDeletewindow = new Deletewindow(this);
				loadForm("Delete Ambulance", new DeleteAmbulance(this));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nError");
			}
			break;

		case 6:
			try {
				loadForm("Search Ambulance", new SearchAmbulance(this));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nError");
			}
			break;

		case 7:
			runComponents("Calc.exe");
			break;

		case 8:
			runComponents("Notepad.exe");
			break;

		case 9:
			try {
				loadForm("Ambulance Report", new AmbulanceReport(this));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nError");
			}
			break;

		case 12:
			try {
				loadForm("Send Sms", new SmsSending(this));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nError");
			}

			break;

		case 13:
			// FormHelpwindow = new Helpwindow(this);
			break;

		}

	}

	protected void runComponents(String sComponents) {
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec(sComponents);
		} catch (IOException evt) {
			JOptionPane.showMessageDialog(null, evt.getMessage(),
					"Error Found", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void loadForm(String Title, JInternalFrame clsForm) {

		boolean xForm = isLoaded(Title);
		if (xForm == false) {
			desktop.add(clsForm);
			clsForm.setVisible(true);
			clsForm.show();
		} else {
			try {
				clsForm.setIcon(false);
				clsForm.setSelected(true);

			} catch (PropertyVetoException e) {
			}
		}
	} // Complete Load Form methode

	protected boolean isLoaded(String FormTitle) {
		JInternalFrame Form[] = desktop.getAllFrames();
		for (int i = 0; i < Form.length; i++) {
			if (Form[i].getTitle().equalsIgnoreCase(FormTitle)) {
				Form[i].show();
				try {
					Form[i].setIcon(false);
					Form[i].setSelected(true);

				} catch (PropertyVetoException e) {

				}
				return true;
			}
		}
		return false;
	} // Complete to Verify Form loaded or not

	protected void UnloadWindow() {
		try {
			int reply = JOptionPane.showConfirmDialog(this,
					"Are you sure to exit?", sMSGBOX_TITLE,
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (reply == JOptionPane.YES_OPTION) {

				setVisible(false);
				System.exit(0);
			}
		} catch (Exception e) {
		}

	}// Close the Windows

	public static void setlogin(String sUsername, Date sDate) {
		sUser = sUsername;
		td = sDate;

	}// Set Login

}
