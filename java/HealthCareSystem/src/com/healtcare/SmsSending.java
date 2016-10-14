package com.healtcare;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SmsSending extends JInternalFrame implements ActionListener {

	private JLabel lblSenerPhone;
	private JLabel lblMessage;

	private JTextArea txtPhonNo;
	private JTextArea txtMessage;

	JFrame JFParentFrame;

	private JPanel firstpanel;

	private JPanel panel2;

	private JButton sendBtn;
	private JButton ResetBtn;
	private JButton ExitBtn;

	public SmsSending(JFrame getParentFrame) {

		super("Send sms to user ", true, true, true, true);

		setSize(400, 800);
		JFParentFrame = getParentFrame;

		firstpanel = new JPanel();
		lblSenerPhone = new JLabel("Phone Number   ");
		lblMessage = new JLabel("Messsage       ");

		txtPhonNo = new JTextArea(1, 30);
		txtMessage = new JTextArea(3, 30);

		firstpanel.add(lblSenerPhone);
		firstpanel.add(txtPhonNo);

		firstpanel.add(lblMessage);
		firstpanel.add(txtMessage);

		firstpanel.setOpaque(true);
		firstpanel.setLayout(new GridLayout(10, 2));
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		sendBtn = new JButton("Send");// , new ImageIcon("src/images/add.gif")
		ResetBtn = new JButton("Reset");// reset, new
		ExitBtn = new JButton("Exit");// , new ImageIcon("src/images/exit.png")

		panel2.add(sendBtn);
		sendBtn.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();

		if (source.equals(sendBtn)) {

			try {
				if(SmsUtil.sendSms(txtPhonNo.getText().trim(), txtMessage.getText().trim()))
				{
					JOptionPane.showMessageDialog(null,
							"Sms Sent" , "Than You!!!",
							JOptionPane.INFORMATION_MESSAGE);
				}else
				{
					JOptionPane.showMessageDialog(null,
							"Error occured please try again" , "WARNING!!!",
							JOptionPane.INFORMATION_MESSAGE);	
				}
			} catch (Exception e) {

				e.printStackTrace();

				JOptionPane.showMessageDialog(null,
						"GENERAL EXCEPTION" + e.getMessage(), "WARNING!!!",
						JOptionPane.INFORMATION_MESSAGE);

			}

		} else if (source == ResetBtn) {
			ResetRecord();
		} else if (source == ExitBtn) {
			setVisible(false);
			dispose();
		}
	}

	private void ResetRecord() {
		txtMessage.setText("");
		txtPhonNo.setText("");
	}

}
