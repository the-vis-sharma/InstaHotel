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
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ModifyUser extends JFrame {

	private JPanel contentPane;
	private JTextField tfSUsername;
	private JTextField textField_1;
	private JTextField txtNewUserDetails;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfAnswer2;
	private JTextField tfAnswer1;
	private JPasswordField pfNewPass;
	private JPasswordField pfConPass;

	/**
	 * Create the frame.
	 */
	public ModifyUser() {
		setTitle("InstaHotel - Modify User");
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(148, 0, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SearchPanel = new JPanel();
		SearchPanel.setLayout(null);
		SearchPanel.setBackground(new Color(233, 150, 122));
		SearchPanel.setBounds(10, 11, 664, 122);
		contentPane.add(SearchPanel);
		
		JLabel label = new JLabel("Username");
		label.setBounds(20, 58, 144, 14);
		SearchPanel.add(label);
		
		tfSUsername = new JTextField();
		tfSUsername.setColumns(10);
		tfSUsername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfSUsername.setBackground(new Color(233, 150, 122));
		tfSUsername.setBounds(20, 83, 200, 28);
		SearchPanel.add(tfSUsername);
		
		textField_1 = new JTextField();
		textField_1.setText("     Search User");
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_1.setBackground(new Color(128, 0, 128));
		textField_1.setBounds(10, 11, 644, 35);
		SearchPanel.add(textField_1);
		
		
		
		JPanel NewDetailPanel = new JPanel();
		NewDetailPanel.setBackground(new Color(233, 150, 122));
		NewDetailPanel.setBounds(10, 144, 664, 406);
		contentPane.add(NewDetailPanel);
		NewDetailPanel.setVisible(false);
		NewDetailPanel.setLayout(null);
		
		txtNewUserDetails = new JTextField();
		txtNewUserDetails.setText("     New User Details");
		txtNewUserDetails.setForeground(Color.WHITE);
		txtNewUserDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtNewUserDetails.setEditable(false);
		txtNewUserDetails.setColumns(10);
		txtNewUserDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtNewUserDetails.setBackground(new Color(128, 0, 128));
		txtNewUserDetails.setBounds(10, 11, 644, 35);
		NewDetailPanel.add(txtNewUserDetails);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblFirstName.setBounds(20, 69, 100, 21);
		NewDetailPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLastName.setBounds(333, 69, 100, 21);
		NewDetailPanel.add(lblLastName);
		
		tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Arial", Font.PLAIN, 14));
		tfFirstName.setColumns(10);
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBackground(new Color(233, 150, 122));
		tfFirstName.setBounds(20, 86, 200, 35);
		NewDetailPanel.add(tfFirstName);
		
		tfLastName = new JTextField();
		tfLastName.setFont(new Font("Arial", Font.PLAIN, 14));
		tfLastName.setColumns(10);
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setBackground(new Color(233, 150, 122));
		tfLastName.setBounds(333, 86, 200, 35);
		NewDetailPanel.add(tfLastName);
		
		JLabel label_3 = new JLabel("Question 1");
		label_3.setFont(new Font("Arial", Font.PLAIN, 11));
		label_3.setBounds(20, 198, 100, 21);
		NewDetailPanel.add(label_3);
		
		JLabel label_4 = new JLabel("Answer");
		label_4.setFont(new Font("Arial", Font.PLAIN, 11));
		label_4.setBounds(443, 198, 100, 21);
		NewDetailPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Question 2");
		label_5.setFont(new Font("Arial", Font.PLAIN, 11));
		label_5.setBounds(20, 266, 100, 21);
		NewDetailPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Answer");
		label_6.setFont(new Font("Arial", Font.PLAIN, 11));
		label_6.setBounds(443, 266, 100, 21);
		NewDetailPanel.add(label_6);
		
		tfAnswer2 = new JTextField();
		tfAnswer2.setFont(new Font("Arial", Font.PLAIN, 14));
		tfAnswer2.setColumns(10);
		tfAnswer2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfAnswer2.setBackground(new Color(233, 150, 122));
		tfAnswer2.setBounds(443, 287, 200, 35);
		NewDetailPanel.add(tfAnswer2);
		
		tfAnswer1 = new JTextField();
		tfAnswer1.setFont(new Font("Arial", Font.PLAIN, 14));
		tfAnswer1.setColumns(10);
		tfAnswer1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfAnswer1.setBackground(new Color(233, 150, 122));
		tfAnswer1.setBounds(443, 220, 200, 35);
		NewDetailPanel.add(tfAnswer1);
		
		JComboBox cbQuestion1 = new JComboBox();
		cbQuestion1.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
		cbQuestion1.setBounds(20, 220, 413, 35);
		NewDetailPanel.add(cbQuestion1);
		
		JComboBox cbQuestion2 = new JComboBox();
		cbQuestion2.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
		cbQuestion2.setBounds(20, 287, 413, 35);
		NewDetailPanel.add(cbQuestion2);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Arial", Font.PLAIN, 11));
		lblType.setBounds(20, 329, 100, 21);
		NewDetailPanel.add(lblType);
		
		JComboBox cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Other"}));
		cbType.setBounds(20, 361, 154, 35);
		NewDetailPanel.add(cbType);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewPassword.setBounds(20, 132, 147, 21);
		NewDetailPanel.add(lblNewPassword);
		
		JLabel lblConfrimPassword = new JLabel("Confrim Password");
		lblConfrimPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		lblConfrimPassword.setBounds(333, 132, 146, 21);
		NewDetailPanel.add(lblConfrimPassword);
		
		pfNewPass = new JPasswordField();
		pfNewPass.setFont(new Font("Arial", Font.PLAIN, 14));
		pfNewPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pfNewPass.setBackground(new Color(233, 150, 122));
		pfNewPass.setBounds(20, 152, 214, 35);
		NewDetailPanel.add(pfNewPass);
		
		pfConPass = new JPasswordField();
		pfConPass.setFont(new Font("Arial", Font.PLAIN, 14));
		pfConPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pfConPass.setBackground(new Color(233, 150, 122));
		pfConPass.setBounds(333, 152, 214, 35);
		NewDetailPanel.add(pfConPass);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean valid = true;
				if(tfFirstName.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("First Name can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				if(tfLastName.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("Last Name can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				if(pfNewPass.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("New Password can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				if(pfConPass.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("Confrim Password can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				if(tfAnswer1.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("You must answer First Question.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				if(tfAnswer2.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("You must answer First Question.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				if(!pfNewPass.getText().equals(pfConPass.getText()))
				{
					valid = false;
					HeadSPanel.infoBox("Password don't matched.", "Password Not Matched", JOptionPane.ERROR_MESSAGE);
				}
				if(!DBConnection.validatePass(pfNewPass.getText()))
				{
					valid = false;
					HeadSPanel.infoBox("Password must contains at least one digit, one upper case, one lower case and special symboll. Password must be between 6-20 characters.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
				}
				if(cbQuestion1.getSelectedIndex()==cbQuestion2.getSelectedIndex())
				{
					valid = false;
					HeadSPanel.infoBox("First Question and Second Question Can't be same.", "Questions Matched", JOptionPane.ERROR_MESSAGE);
				}
				if(valid)
				{
					Connection con = DBConnection.connection();
					Statement st = null;
					String sql = "UPDATE uldetail set Name='"+tfFirstName.getText()+" "+tfLastName.getText()+"', Pass='"+pfNewPass.getText()+"', Type='"+cbType.getSelectedIndex()+"', Question1='"+cbQuestion1.getSelectedIndex()+"', Answer1='"+tfAnswer1.getText()+"', Question2='"+cbQuestion2.getSelectedIndex()+"', Answer2='"+tfAnswer2.getText()+"' WHERE UserName='"+tfSUsername.getText()+"'";

					try {
						st = con.createStatement();
						st.executeUpdate(sql);
						HeadSPanel.infoBox("User Details Updated Successfully.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					finally {
						DBConnection.closeResource(con, st);
					}
				}
			}
		});
		btnSave.setIcon(new ImageIcon(ModifyUser.class.getResource("/com/img/arrow.png")));
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSave.setBackground(new Color(233, 150, 122));
		btnSave.setBounds(606, 351, 48, 44);
		NewDetailPanel.add(btnSave);
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = DBConnection.connection();
				Statement st = null;
				ResultSet rs = null;
				String sql = "SELECT *FROM uldetail WHERE UserName='"+tfSUsername.getText()+"'";
				try {
					st = con.createStatement();
					rs = st.executeQuery(sql);
					if(rs.next())
					{
						String name[] = rs.getString("Name").split(" ");
						tfFirstName.setText(name[0]);
						tfLastName.setText(name[1]);
						cbQuestion1.setSelectedItem(rs.getString("Question1"));
						tfAnswer1.setText(rs.getString("Answer1"));
						cbQuestion2.setSelectedItem(rs.getString("Question2"));
						tfAnswer2.setText(rs.getString("Answer2"));
						cbType.setSelectedItem(rs.getString("Type"));
						NewDetailPanel.setVisible(true);
					}
					else
					{
						HeadSPanel.infoBox("Sorry, No record found for this username. Please check the username and try again.", "No User Found", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnSearch.setIcon(new ImageIcon(ModifyUser.class.getResource("/com/img/arrow.png")));
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setBackground(new Color(233, 150, 122));
		btnSearch.setBounds(594, 67, 48, 44);
		SearchPanel.add(btnSearch);
	}
}
