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
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SearchUser extends JFrame {

	private JPanel contentPane;
	private JTextField tfSUserName;
	private JTextField txtSearchUser;
	private JTextField txtDeafultUser;
	private JTextField tfName;
	private JTextField tfUserName;
	private JTextField tfQuestion1;
	private JTextField tfAnswer1;
	private JTextField tfQuestion2;
	private JTextField tfAnswer2;
	private JLabel lblQuestion;
	private JLabel lblAnswer;
	private JLabel lblQuestion_1;
	private JLabel lblAnswer_1;

	/**
	 * Create the frame.
	 */
	public SearchUser() {
		setTitle("InstaHotel - Search User");
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(148, 0, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SearchPanel = new JPanel();
		SearchPanel.setBackground(new Color(233, 150, 122));
		SearchPanel.setBounds(10, 11, 664, 122);
		contentPane.add(SearchPanel);
		SearchPanel.setLayout(null);
		
		JPanel UserDetailPanel = new JPanel();
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(20, 58, 144, 14);
		SearchPanel.add(lblUsername);
		
		tfSUserName = new JTextField();
		tfSUserName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfSUserName.setBackground(new Color(233, 150, 122));
		tfSUserName.setBounds(20, 83, 200, 28);
		SearchPanel.add(tfSUserName);
		tfSUserName.setColumns(10);
		
		
		
		txtSearchUser = new JTextField();
		txtSearchUser.setEditable(false);
		txtSearchUser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtSearchUser.setText("     Search User");
		txtSearchUser.setForeground(new Color(255, 255, 255));
		txtSearchUser.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtSearchUser.setBackground(new Color(128, 0, 128));
		txtSearchUser.setBounds(10, 11, 644, 35);
		SearchPanel.add(txtSearchUser);
		txtSearchUser.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = DBConnection.connection();
				Statement st = null;
				ResultSet rs = null;
				String sql = "SELECT *FROM uldetail WHERE UserName='"+tfSUserName.getText()+"'";
				try {
					st = con.createStatement();
					rs = st.executeQuery(sql);
					if(rs.next())
					{
						tfName.setText(rs.getString("Name"));
						tfUserName.setText(rs.getString("UserName"));
						tfQuestion1.setText(rs.getString("Question1"));
						tfAnswer1.setText(rs.getString("Answer1"));
						tfQuestion2.setText(rs.getString("Question2"));
						tfAnswer2.setText(rs.getString("Answer2"));
						UserDetailPanel.setVisible(true);
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
		btnNewButton.setIcon(new ImageIcon(SearchUser.class.getResource("/com/img/arrow.png")));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setBackground(new Color(233, 150, 122));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBounds(594, 67, 48, 44);
		SearchPanel.add(btnNewButton);
		
		
		UserDetailPanel.setBackground(new Color(233, 150, 122));
		UserDetailPanel.setBounds(10, 144, 664, 306);
		contentPane.add(UserDetailPanel);
		UserDetailPanel.setLayout(null);
		UserDetailPanel.setVisible(false);
		
		txtDeafultUser = new JTextField();
		txtDeafultUser.setText("     User Details");
		txtDeafultUser.setForeground(Color.WHITE);
		txtDeafultUser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtDeafultUser.setEditable(false);
		txtDeafultUser.setColumns(10);
		txtDeafultUser.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtDeafultUser.setBackground(new Color(128, 0, 128));
		txtDeafultUser.setBounds(10, 11, 644, 35);
		UserDetailPanel.add(txtDeafultUser);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblName.setBounds(20, 64, 57, 21);
		UserDetailPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfName.setBackground(new Color(233, 150, 122));
		tfName.setEditable(false);
		tfName.setFont(new Font("Arial", Font.BOLD, 14));
		tfName.setBounds(98, 57, 200, 35);
		UserDetailPanel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsername_1.setBounds(333, 64, 100, 21);
		UserDetailPanel.add(lblUsername_1);
		
		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Arial", Font.BOLD, 14));
		tfUserName.setEditable(false);
		tfUserName.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfUserName.setBackground(new Color(233, 150, 122));
		tfUserName.setColumns(10);
		tfUserName.setBounds(443, 57, 200, 35);
		UserDetailPanel.add(tfUserName);
		
		tfQuestion1 = new JTextField();
		tfQuestion1.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfQuestion1.setBackground(new Color(233, 150, 122));
		tfQuestion1.setEditable(false);
		tfQuestion1.setFont(new Font("Arial", Font.BOLD, 14));
		tfQuestion1.setColumns(10);
		tfQuestion1.setBounds(169, 101, 468, 35);
		UserDetailPanel.add(tfQuestion1);
		
		tfAnswer1 = new JTextField();
		tfAnswer1.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfAnswer1.setBackground(new Color(233, 150, 122));
		tfAnswer1.setEditable(false);
		tfAnswer1.setFont(new Font("Arial", Font.BOLD, 14));
		tfAnswer1.setColumns(10);
		tfAnswer1.setBounds(169, 147, 200, 35);
		UserDetailPanel.add(tfAnswer1);
		
		tfQuestion2 = new JTextField();
		tfQuestion2.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfQuestion2.setBackground(new Color(233, 150, 122));
		tfQuestion2.setEditable(false);
		tfQuestion2.setFont(new Font("Arial", Font.BOLD, 14));
		tfQuestion2.setColumns(10);
		tfQuestion2.setBounds(169, 193, 474, 35);
		UserDetailPanel.add(tfQuestion2);
		
		tfAnswer2 = new JTextField();
		tfAnswer2.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfAnswer2.setBackground(new Color(233, 150, 122));
		tfAnswer2.setEditable(false);
		tfAnswer2.setFont(new Font("Arial", Font.BOLD, 14));
		tfAnswer2.setColumns(10);
		tfAnswer2.setBounds(169, 240, 200, 35);
		UserDetailPanel.add(tfAnswer2);
		
		lblQuestion = new JLabel("Question 1");
		lblQuestion.setFont(new Font("Arial", Font.PLAIN, 14));
		lblQuestion.setBounds(20, 114, 100, 21);
		UserDetailPanel.add(lblQuestion);
		
		lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAnswer.setBounds(20, 160, 100, 21);
		UserDetailPanel.add(lblAnswer);
		
		lblQuestion_1 = new JLabel("Question 2");
		lblQuestion_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblQuestion_1.setBounds(20, 204, 100, 21);
		UserDetailPanel.add(lblQuestion_1);
		
		lblAnswer_1 = new JLabel("Answer");
		lblAnswer_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAnswer_1.setBounds(20, 247, 100, 21);
		UserDetailPanel.add(lblAnswer_1);
	}
}
