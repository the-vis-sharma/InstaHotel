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

public class EditProfile extends JFrame {

	private JPanel contentPane;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextField tfMobile;
	private JTextField txtPersonal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile frame = new EditProfile();
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
	public EditProfile() {
		String curUser = "Visnu";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1124, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel EditProfilePanel = new JPanel();
		EditProfilePanel.setBounds(10, 11, 1085, 529);
		contentPane.add(EditProfilePanel);
		EditProfilePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(284, 80, 186, 31);
		EditProfilePanel.add(lblNewLabel);
		
		tfFirstName = new JTextField();
		tfFirstName.setBackground(UIManager.getColor("Button.background"));
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBounds(524, 80, 250, 31);
		EditProfilePanel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(284, 122, 186, 31);
		EditProfilePanel.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setBackground(UIManager.getColor("Button.background"));
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setColumns(10);
		tfLastName.setBounds(524, 122, 250, 31);
		EditProfilePanel.add(tfLastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(284, 164, 186, 31);
		EditProfilePanel.add(lblGender);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(284, 206, 186, 31);
		EditProfilePanel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBackground(UIManager.getColor("Button.background"));
		tfEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfEmail.setColumns(10);
		tfEmail.setBounds(524, 206, 250, 31);
		EditProfilePanel.add(tfEmail);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobile.setBounds(284, 248, 186, 31);
		EditProfilePanel.add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setBackground(UIManager.getColor("Button.background"));
		tfMobile.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfMobile.setColumns(10);
		tfMobile.setBounds(524, 248, 250, 31);
		EditProfilePanel.add(tfMobile);
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setBounds(524, 170, 70, 23);
		rbMale.setActionCommand("Male");
		bg.add(rbMale);
		EditProfilePanel.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setBounds(612, 170, 70, 23);
		rbFemale.setActionCommand("Female");
		bg.add(rbFemale);
		EditProfilePanel.add(rbFemale);
		
		JRadioButton rbOther = new JRadioButton("Other");
		rbOther.setBounds(704, 170, 70, 23);
		rbOther.setActionCommand("Other");
		bg.add(rbOther);
		EditProfilePanel.add(rbOther);
		
		Connection con = DBConnection.connection();
		Statement st=null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from uldetail where Name='"+curUser+"'");
			rs.next();
			//tfFirstName.setText(curUser.split(" ")[0]);
			//tfLastName.setText(curUser.split(" ")[1]);
			String email = rs.getString("Email");
			if(email.equals("0")) email = "";
			tfEmail.setText(email);
			String mob = rs.getString("Mobile");
			if(mob.equals("0")) mob = "";
			tfMobile.setText(mob);
			String gen = rs.getString("Gender");
			if(gen.equals("0")) gen = "";
			if(gen.equals("Male")) rbMale.setSelected(true);
			else if(gen.equals("Female")) rbFemale.setSelected(true);
			else if(gen.equals("Other")) rbOther.setSelected(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = DBConnection.connection();
				
				try {
					Statement st = con.createStatement();
					st.executeUpdate("UPDATE uldetail SET Name='"+tfFirstName.getText()+" "+tfLastName.getText()+"', Email='"+tfEmail.getText()+"', Mobile='"+tfMobile.getText()+"', Gender='"+bg.getSelection().getActionCommand()+"' where Name='"+curUser+"'");
					HeadSPanel.infoBox("Profile Detials Updated Successfully.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveChanges.setBounds(283, 314, 491, 31);
		EditProfilePanel.add(btnSaveChanges);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(205, 133, 63));
		panel.setBounds(35, 80, 174, 222);
		EditProfilePanel.add(panel);
		panel.setLayout(null);
		
		txtPersonal = new JTextField();
		txtPersonal.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		txtPersonal.setFocusable(false);
		txtPersonal.setEditable(false);
		txtPersonal.setForeground(new Color(255, 255, 255));
		txtPersonal.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.WHITE));
		txtPersonal.setBackground(Color.BLUE);
		txtPersonal.setText("    Personal");
		txtPersonal.setBounds(10, 11, 154, 30);
		panel.add(txtPersonal);
		txtPersonal.setColumns(10);
	}
}
