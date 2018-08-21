package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

public class ContactUs extends JFrame {

	private JPanel contentPane;
	private JTextField txtContactDetails;
	private JTextField txtFeedback;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfMobile;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public ContactUs() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 742, 448);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 245, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel ContactDetailPanel = new JPanel();
		ContactDetailPanel.setBackground(new Color(138, 43, 226));
		ContactDetailPanel.setBounds(10, 11, 348, 387);
		contentPane.add(ContactDetailPanel);
		ContactDetailPanel.setLayout(null);
		
		txtContactDetails = new JTextField();
		txtContactDetails.setBackground(new Color(65, 105, 225));
		txtContactDetails.setForeground(new Color(255, 255, 255));
		txtContactDetails.setHorizontalAlignment(SwingConstants.CENTER);
		txtContactDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtContactDetails.setFocusTraversalKeysEnabled(false);
		txtContactDetails.setFocusable(false);
		txtContactDetails.setEditable(false);
		txtContactDetails.setBorder(null);
		txtContactDetails.setText("Contact Details");
		txtContactDetails.setBounds(10, 11, 328, 30);
		ContactDetailPanel.add(txtContactDetails);
		txtContactDetails.setColumns(10);
		
		JLabel lblVishnuBochiwal = new JLabel("VISHNU BOCHIWAL");
		lblVishnuBochiwal.setForeground(new Color(0, 0, 0));
		lblVishnuBochiwal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblVishnuBochiwal.setBounds(20, 52, 318, 30);
		ContactDetailPanel.add(lblVishnuBochiwal);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBackground(new Color(138, 43, 226));
		btnNewButton.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/phone-icon.png")));
		btnNewButton.setBounds(20, 80, 30, 30);
		ContactDetailPanel.add(btnNewButton);
		
		JLabel label = new JLabel("+91 76 11 845686");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(58, 80, 280, 30);
		ContactDetailPanel.add(label);
		
		JButton button = new JButton("");
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/whatsapp-logo-icone.png")));
		button.setBackground(new Color(138, 43, 226));
		button.setBounds(20, 115, 30, 30);
		ContactDetailPanel.add(button);
		
		JLabel label_1 = new JLabel("77 270 84 821");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_1.setBounds(58, 119, 280, 26);
		ContactDetailPanel.add(label_1);
		
		JButton button_1 = new JButton("");
		button_1.setHorizontalTextPosition(SwingConstants.CENTER);
		button_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		button_1.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/email-flat.png")));
		button_1.setBackground(new Color(138, 43, 226));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(20, 149, 30, 30);
		ContactDetailPanel.add(button_1);
		
		JLabel lblBochiwalvisnugmailcom = new JLabel("bochiwal.visnu@gmail.com");
		lblBochiwalvisnugmailcom.setForeground(new Color(0, 0, 0));
		lblBochiwalvisnugmailcom.setFont(new Font("Arial", Font.BOLD, 14));
		lblBochiwalvisnugmailcom.setBounds(58, 149, 280, 30);
		ContactDetailPanel.add(lblBochiwalvisnugmailcom);
		
		JLabel lblShubhamKabra = new JLabel("SHUBHAM KABRA");
		lblShubhamKabra.setForeground(Color.BLACK);
		lblShubhamKabra.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblShubhamKabra.setBounds(20, 229, 318, 30);
		ContactDetailPanel.add(lblShubhamKabra);
		
		JButton button_2 = new JButton("");
		button_2.setHorizontalTextPosition(SwingConstants.CENTER);
		button_2.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/phone-icon.png")));
		button_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		button_2.setBackground(new Color(138, 43, 226));
		button_2.setBounds(20, 257, 30, 30);
		ContactDetailPanel.add(button_2);
		
		JLabel label_3 = new JLabel("+91 7062 717 559");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_3.setBounds(58, 257, 280, 30);
		ContactDetailPanel.add(label_3);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/whatsapp-logo-icone.png")));
		button_3.setHorizontalTextPosition(SwingConstants.CENTER);
		button_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		button_3.setBackground(new Color(138, 43, 226));
		button_3.setBounds(20, 292, 30, 30);
		ContactDetailPanel.add(button_3);
		
		JLabel label_4 = new JLabel("99 29 604211");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_4.setBounds(58, 296, 280, 26);
		ContactDetailPanel.add(label_4);
		
		JButton button_4 = new JButton("");
		button_4.setHorizontalTextPosition(SwingConstants.CENTER);
		button_4.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/email-flat.png")));
		button_4.setBorder(new EmptyBorder(0, 0, 0, 0));
		button_4.setBackground(new Color(138, 43, 226));
		button_4.setBounds(20, 326, 30, 30);
		ContactDetailPanel.add(button_4);
		
		JLabel lblShubhamgmailcom = new JLabel("shubham.1997@gmail.com");
		lblShubhamgmailcom.setForeground(Color.BLACK);
		lblShubhamgmailcom.setFont(new Font("Arial", Font.BOLD, 14));
		lblShubhamgmailcom.setBounds(58, 326, 280, 30);
		ContactDetailPanel.add(lblShubhamgmailcom);
		
		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setBackground(new Color(255, 99, 71));
		textField.setBounds(10, 208, 328, 2);
		ContactDetailPanel.add(textField);
		textField.setColumns(10);
		
		JPanel FeedBackPanel = new JPanel();
		FeedBackPanel.setBackground(new Color(100, 149, 237));
		FeedBackPanel.setBounds(368, 11, 348, 387);
		contentPane.add(FeedBackPanel);
		FeedBackPanel.setLayout(null);
		
		txtFeedback = new JTextField();
		txtFeedback.setText("FeedBack ");
		txtFeedback.setHorizontalAlignment(SwingConstants.CENTER);
		txtFeedback.setForeground(Color.WHITE);
		txtFeedback.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtFeedback.setFocusable(false);
		txtFeedback.setFocusTraversalKeysEnabled(false);
		txtFeedback.setEditable(false);
		txtFeedback.setColumns(10);
		txtFeedback.setBorder(null);
		txtFeedback.setBackground(new Color(138, 43, 226));
		txtFeedback.setBounds(10, 11, 328, 30);
		FeedBackPanel.add(txtFeedback);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(20, 52, 46, 14);
		FeedBackPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 69, 0)));
		tfName.setBackground(new Color(100, 149, 237));
		tfName.setBounds(20, 67, 307, 30);
		FeedBackPanel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(20, 108, 46, 14);
		FeedBackPanel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 69, 0)));
		tfEmail.setBackground(new Color(100, 149, 237));
		tfEmail.setColumns(10);
		tfEmail.setBounds(20, 123, 307, 30);
		FeedBackPanel.add(tfEmail);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setForeground(new Color(255, 255, 255));
		lblMobile.setBounds(20, 164, 46, 14);
		FeedBackPanel.add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setBackground(new Color(100, 149, 237));
		tfMobile.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 69, 0)));
		tfMobile.setColumns(10);
		tfMobile.setBounds(20, 179, 307, 30);
		FeedBackPanel.add(tfMobile);
		
		JTextArea taMessage = new JTextArea();
		taMessage.setForeground(new Color(255, 255, 255));
		taMessage.setFont(new Font("Arial", Font.PLAIN, 13));
		taMessage.setBackground(new Color(100, 149, 237));
		taMessage.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 69, 0)));
		taMessage.setBounds(20, 234, 307, 89);
		FeedBackPanel.add(taMessage);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(new Color(255, 255, 255));
		lblMessage.setBounds(20, 220, 99, 14);
		FeedBackPanel.add(lblMessage);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setFocusTraversalPolicyProvider(true);
		btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton_1.setRequestFocusEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DBConnection.validateEmail(tfEmail.getText()))
				{
					if(DBConnection.validateMobile(tfMobile.getText()))
					{
						Connection con = DBConnection.connection();
						Statement st=null;
						String sql = "insert into FeedBackDB (Name,Email,Mobile,Message) value('"+tfName.getText()+"','"+tfEmail.getText()+"',"+tfMobile.getText()+",'"+taMessage.getText()+"')"; 
						try {
							st = con.createStatement();
							st.executeUpdate(sql);
							tfEmail.setText("");
							tfMobile.setText("");
							tfName.setText("");
							taMessage.setText("");
							HeadSPanel.infoBox("FeedBack Submitted Successfully", "Thank you!", JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else
					{
						HeadSPanel.infoBox("Please Enter a Valid Mobile No", "Invalid Mobile No", JOptionPane.ERROR_MESSAGE);
					}
				
				}
				else
				{
					HeadSPanel.infoBox("Please Enter a valid Email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		btnNewButton_1.setBackground(new Color(100, 149, 237));
		btnNewButton_1.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnNewButton_1.setBounds(281, 332, 46, 44);
		FeedBackPanel.add(btnNewButton_1);
	}
}
