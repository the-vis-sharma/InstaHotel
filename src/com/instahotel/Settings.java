package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;

public class Settings extends JFrame {

	private JPanel contentPane;
	private JTextField txtPersonal;
	private JPasswordField pfOldPass;
	private JPasswordField pfNewPass;
	private JPasswordField pfConPass;
	private JTextField txtChangeSecurityQuestions;
	private JTextField tfAnswer1;
	private JTextField tfAnswer2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		String curUser = "Visnu Bochiwal";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1124, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SettingPanel = new JPanel();
		SettingPanel.setBounds(10, 11, 1085, 529);
		contentPane.add(SettingPanel);
		SettingPanel.setLayout(null);
		
		JPanel ChangePassPanel = new JPanel();
		ChangePassPanel.setBackground(new Color(205, 133, 63));
		ChangePassPanel.setBounds(595, 145, 450, 250);
		SettingPanel.add(ChangePassPanel);
		ChangePassPanel.setLayout(null);
		
		txtPersonal = new JTextField();
		txtPersonal.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		txtPersonal.setFocusable(false);
		txtPersonal.setEditable(false);
		txtPersonal.setForeground(new Color(255, 255, 255));
		txtPersonal.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.WHITE));
		txtPersonal.setBackground(Color.BLUE);
		txtPersonal.setText("    Change Password");
		txtPersonal.setBounds(10, 21, 430, 30);
		ChangePassPanel.add(txtPersonal);
		txtPersonal.setColumns(10);
		
		JLabel lblOldPass = new JLabel("Old Password");
		lblOldPass.setBounds(20, 62, 150, 31);
		ChangePassPanel.add(lblOldPass);
		lblOldPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewPass = new JLabel("New Password");
		lblNewPass.setBounds(20, 103, 150, 31);
		ChangePassPanel.add(lblNewPass);
		lblNewPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblConPass = new JLabel("Confrim Password");
		lblConPass.setBounds(20, 145, 150, 31);
		ChangePassPanel.add(lblConPass);
		lblConPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnChangePass = new JButton("Save Changes");
		btnChangePass.setFocusable(false);
		btnChangePass.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btnChangePass.setForeground(Color.WHITE);
		btnChangePass.setBackground(Color.BLUE);
		btnChangePass.setBounds(20, 194, 409, 31);
		ChangePassPanel.add(btnChangePass);
		
		pfOldPass = new JPasswordField();
		pfOldPass.setBackground(new Color(205, 133, 63));
		pfOldPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pfOldPass.setBounds(198, 62, 230, 30);
		ChangePassPanel.add(pfOldPass);
		
		pfNewPass = new JPasswordField();
		pfNewPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pfNewPass.setBackground(new Color(205, 133, 63));
		pfNewPass.setBounds(198, 104, 230, 30);
		ChangePassPanel.add(pfNewPass);
		
		pfConPass = new JPasswordField();
		pfConPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pfConPass.setBackground(new Color(205, 133, 63));
		pfConPass.setBounds(199, 145, 230, 30);
		ChangePassPanel.add(pfConPass);
		
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pfOldPass.getText().equals("")) {
					HeadSPanel.infoBox("Old Password can't be blank.", "Field Required", JOptionPane.ERROR_MESSAGE);
				}
				else if(pfNewPass.getText().equals(""))
				{
					HeadSPanel.infoBox("New Password can't be blank.", "Field Required", JOptionPane.ERROR_MESSAGE);
				}
				else if(pfNewPass.getText().equals(pfConPass.getText())) {
					Connection con = DBConnection.connection();
					ResultSet rs=null;
					try {
						Statement st = con.createStatement();
						rs = st.executeQuery("select Pass from uldetail where Name='"+curUser+"'");
						rs.next();
						if(rs.getString("Pass").equals(pfOldPass.getText())) {
							st.executeUpdate("UPDATE uldetail SET Pass='"+pfNewPass.getText()+"' where Name='"+curUser+"'");
							HeadSPanel.infoBox("Password Changed Successfully.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
							pfConPass.setText("");
							pfOldPass.setText("");
							pfNewPass.setText("");
						}
						else {
							HeadSPanel.infoBox("Your old password is incorrect.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
							pfConPass.setText("");
							pfOldPass.setText("");
							pfNewPass.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					HeadSPanel.infoBox("New Password and Confrim Password must be same.", "Password Not Matched", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JPanel ChangeSQuestionPanel = new JPanel();
		ChangeSQuestionPanel.setLayout(null);
		ChangeSQuestionPanel.setBackground(new Color(205, 133, 63));
		ChangeSQuestionPanel.setBounds(40, 80, 450, 380);
		SettingPanel.add(ChangeSQuestionPanel);
		
		txtChangeSecurityQuestions = new JTextField();
		txtChangeSecurityQuestions.setText("    Change Security Questions");
		txtChangeSecurityQuestions.setForeground(Color.WHITE);
		txtChangeSecurityQuestions.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		txtChangeSecurityQuestions.setFocusable(false);
		txtChangeSecurityQuestions.setEditable(false);
		txtChangeSecurityQuestions.setColumns(10);
		txtChangeSecurityQuestions.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.WHITE));
		txtChangeSecurityQuestions.setBackground(Color.BLUE);
		txtChangeSecurityQuestions.setBounds(10, 21, 430, 30);
		ChangeSQuestionPanel.add(txtChangeSecurityQuestions);
		
		JLabel lblQuestion1 = new JLabel("New Security Question 1");
		lblQuestion1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuestion1.setBounds(20, 62, 200, 31);
		ChangeSQuestionPanel.add(lblQuestion1);
		
		JComboBox cbSQuestion1 = new JComboBox();
		cbSQuestion1.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
		cbSQuestion1.setBounds(20, 97, 409, 30);
		ChangeSQuestionPanel.add(cbSQuestion1);
		
		tfAnswer1 = new JTextField();
		tfAnswer1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfAnswer1.setBackground(new Color(205, 133, 63));
		tfAnswer1.setBounds(20, 138, 409, 30);
		ChangeSQuestionPanel.add(tfAnswer1);
		tfAnswer1.setColumns(10);
		
		JLabel lblQuestion2 = new JLabel("New Security Question 2");
		lblQuestion2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuestion2.setBounds(20, 194, 200, 31);
		ChangeSQuestionPanel.add(lblQuestion2);
		
		JComboBox cbSQuestion2 = new JComboBox();
		cbSQuestion2.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
		cbSQuestion2.setBounds(20, 229, 409, 30);
		ChangeSQuestionPanel.add(cbSQuestion2);
		
		tfAnswer2 = new JTextField();
		tfAnswer2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfAnswer2.setBackground(new Color(205, 133, 63));
		tfAnswer2.setColumns(10);
		tfAnswer2.setBounds(20, 270, 409, 30);
		ChangeSQuestionPanel.add(tfAnswer2);
		
		JButton btnChangeQuestion = new JButton("Save Changes");
		btnChangeQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfAnswer1.getText().equals("")) {
					HeadSPanel.infoBox("Answer 1 can't be blank.", "Field Required", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfAnswer2.getText().equals(""))
				{
					HeadSPanel.infoBox("Answer 2 can't be blank.", "Field Required", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Connection con = DBConnection.connection();
					ResultSet rs=null;
					try {
						Statement st = con.createStatement();
						st.executeUpdate("UPDATE uldetail SET Question1="+cbSQuestion1.getSelectedIndex()+", Answer1='"+tfAnswer1.getText()+"', Question2="+cbSQuestion2.getSelectedIndex()+", Answer2='"+tfAnswer2.getText()+"' where Name='"+curUser+"'");
						HeadSPanel.infoBox("Security Questions Updated Successfully.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnChangeQuestion.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btnChangeQuestion.setForeground(Color.WHITE);
		btnChangeQuestion.setBackground(Color.BLUE);
		btnChangeQuestion.setBounds(20, 324, 409, 31);
		ChangeSQuestionPanel.add(btnChangeQuestion);
		
	}
}
