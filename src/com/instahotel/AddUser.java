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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfUserName;
	private JTextField tfAnswer1;
	private JTextField tfAnswer2;
	private JPasswordField pfPass;
	private JPasswordField pfConPass;

	/**
	 * Create the frame.
	 */
	public AddUser() {
		setTitle("InstaHotel - Add New User");
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 660, 536);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(148, 0, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(73, 11, 95, 20);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(359, 11, 95, 20);
		contentPane.add(lblLastName);
		
		tfFirstName = new JTextField();
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBackground(new Color(233, 150, 122));
		tfFirstName.setBounds(73, 34, 200, 35);
		contentPane.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setBackground(new Color(233, 150, 122));
		tfLastName.setBounds(359, 34, 200, 35);
		contentPane.add(tfLastName);
		
		JLabel lblUsername = new JLabel("User Name");
		lblUsername.setBounds(73, 80, 95, 20);
		contentPane.add(lblUsername);
		
		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		tfUserName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfUserName.setBackground(new Color(233, 150, 122));
		tfUserName.setBounds(73, 103, 200, 35);
		contentPane.add(tfUserName);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(359, 80, 95, 20);
		contentPane.add(lblType);
		
		JComboBox cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Other"}));
		cbType.setBounds(359, 109, 200, 29);
		contentPane.add(cbType);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 149, 95, 20);
		contentPane.add(lblPassword);
		
		JLabel lblConfrimPassword = new JLabel("Confrim Password");
		lblConfrimPassword.setBounds(359, 149, 95, 20);
		contentPane.add(lblConfrimPassword);
		
		JLabel lblQuestion = new JLabel("Choose Frist Security Question");
		lblQuestion.setBounds(73, 218, 200, 20);
		contentPane.add(lblQuestion);
		
		JComboBox cbQuestion1 = new JComboBox();
		cbQuestion1.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
		cbQuestion1.setBounds(73, 247, 486, 29);
		contentPane.add(cbQuestion1);
		
		JLabel lblChooseSecondSecurity = new JLabel("Choose Second Security Question");
		lblChooseSecondSecurity.setBounds(73, 356, 200, 20);
		contentPane.add(lblChooseSecondSecurity);
		
		JComboBox cbQuestion2 = new JComboBox();
		cbQuestion2.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
		cbQuestion2.setBounds(73, 385, 486, 29);
		contentPane.add(cbQuestion2);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setBounds(73, 287, 95, 20);
		contentPane.add(lblAnswer);
		
		tfAnswer1 = new JTextField();
		tfAnswer1.setColumns(10);
		tfAnswer1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfAnswer1.setBackground(new Color(233, 150, 122));
		tfAnswer1.setBounds(73, 310, 200, 35);
		contentPane.add(tfAnswer1);
		
		JLabel label = new JLabel("Answer");
		label.setBounds(73, 425, 95, 20);
		contentPane.add(label);
		
		tfAnswer2 = new JTextField();
		tfAnswer2.setColumns(10);
		tfAnswer2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfAnswer2.setBackground(new Color(233, 150, 122));
		tfAnswer2.setBounds(73, 448, 200, 35);
		contentPane.add(tfAnswer2);
		
		JButton btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
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
				if(tfUserName.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("User Name can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				if(pfPass.getText().equals(""))
				{
					valid = false;
					HeadSPanel.infoBox("Password can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
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
				if(!pfPass.getText().equals(pfConPass.getText()))
				{
					valid = false;
					HeadSPanel.infoBox("Password don't matched.", "Password Not Matched", JOptionPane.ERROR_MESSAGE);
				}
				if(!DBConnection.validateUserName(tfUserName.getText()))
				{
					valid = false;
					HeadSPanel.infoBox("Username can only contains aphabates, digit and underscore. Username can't start with digits. Username must be between 8-30 characters.", "Invalid Username", JOptionPane.ERROR_MESSAGE);
				}
				if(!DBConnection.validatePass(pfPass.getText()))
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
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] dp=null;
					try {
						ObjectOutputStream o = new ObjectOutputStream(baos);
						o.writeObject(new ImageIcon(Home.class.getResource("/com/img/defaultAvtar.png")));
						dp = baos.toByteArray();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Connection con = DBConnection.connection();
					PreparedStatement ps = null;
					String sql = "INSERT INTO uldetail (NAME, UserName, Pass, Type, Question1, Answer1, Question2, Answer2, dp, Email, Mobile, Gender) VALUE('"+tfFirstName.getText()+" "+tfLastName.getText()+"','"+tfUserName.getText()+"','"+pfPass.getText()+"','"+cbType.getSelectedIndex()+"','"+cbQuestion1.getSelectedIndex()+"','"+tfAnswer1.getText()+"','"+cbQuestion2.getSelectedIndex()+"','"+tfAnswer2.getText()+"', (?), '0', '0', '0')";
					try {
						ps = con.prepareStatement(sql);
						ps.setBytes(1, dp);
						ps.executeUpdate();
						HeadSPanel.infoBox("User Created Successfully.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					finally {
						DBConnection.closeResource(con, null);
					}
				}
				
			}
		});
		btnCreate.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCreate.setBackground(new Color(233, 150, 122));
		btnCreate.setIcon(new ImageIcon(AddUser.class.getResource("/com/img/arrow.png")));
		btnCreate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCreate.setToolTipText("Create");
		btnCreate.setBounds(513, 439, 46, 44);
		contentPane.add(btnCreate);
		
		pfPass = new JPasswordField();
		pfPass.setBackground(new Color(233, 150, 122));
		pfPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pfPass.setBounds(73, 172, 200, 35);
		contentPane.add(pfPass);
		
		pfConPass = new JPasswordField();
		pfConPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pfConPass.setBackground(new Color(233, 150, 122));
		pfConPass.setBounds(359, 172, 200, 35);
		contentPane.add(pfConPass);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(233, 150, 122));
		panel.setBounds(10, 11, 624, 479);
		contentPane.add(panel);
		panel.setLayout(null);
	}
}
