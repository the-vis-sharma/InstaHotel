package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ForPass extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserName;
	private JTextField tfAnswer1;
	private JTextField tfAnswer2;
	private JPasswordField pfNewPass;
	private JPasswordField pfConfPass;
	/**
	 * Create the frame.
	 */
	public ForPass(LoginManager lm) {
		setTitle("InstaHotel - Reset Login Password");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginManager.class.getResource("/com/img/logo.jpg")));
		lm.dispose();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		HeadSPanel.drawHeader(getContentPane());
		
		JPanel Container = new JPanel();
		Container.setBorder(new EmptyBorder(-10, 0, 0, 0));
		Container.setBounds(0, 133, 1362, 572);
		ImagePanel imgCont = new ImagePanel(new ImageIcon(LoginManager.class.getResource("/com/img/back.jpg")));
		Container.add(imgCont);
		imgCont.setLayout(null);
		
		JPanel fpPanel = new JPanel();
		fpPanel.setLayout(null);
		fpPanel.setBackground(Color.WHITE);
		fpPanel.setBounds(449, 100, 466, 228);
		imgCont.add(fpPanel);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.BLUE);
		lblUserName.setFont(new Font("Arial", Font.BOLD, 11));
		lblUserName.setBounds(33, 86, 66, 14);
		fpPanel.add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		tfUserName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 255)));
		tfUserName.setBounds(33, 111, 392, 42);
		fpPanel.add(tfUserName);
		
		JButton btnSearch = new JButton("Search");
		JButton ProgressBar = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = DBConnection.connection();
				Statement st=null,st2;
				String sql="select *from uldetail where UserName='"+tfUserName.getText()+"'";
				ResultSet rs;
				try {
					st = con.createStatement();
					rs = st.executeQuery(sql);
					if(rs.next())
					{
						int Q1 = rs.getInt("Question1");
						String ans1 = rs.getString("Answer1");
						int Q2 = rs.getInt("Question2");
						String ans2 = rs.getString("Answer2");
						
						ProgressBar.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/fpstep2.png")));
						ProgressBar.setDisabledIcon(new ImageIcon(LoginManager.class.getResource("/com/img/fpstep2.png")));
						fpPanel.remove(lblUserName);
						fpPanel.remove(tfUserName);
						fpPanel.remove(btnSearch);			
						fpPanel.repaint();
						fpPanel.setLayout(null);
						fpPanel.setBackground(Color.WHITE);
						fpPanel.setBounds(449, 100, 466, 361);
						imgCont.add(fpPanel);
						
						JLabel lblQuestion1 = new JLabel("Question 1");
						lblQuestion1.setFont(new Font("Arial", Font.BOLD, 11));
						lblQuestion1.setForeground(Color.BLUE);
						lblQuestion1.setBounds(33, 80, 68, 14);
						fpPanel.add(lblQuestion1);
						
						JComboBox cbQuestion1 = new JComboBox();
						cbQuestion1.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?\\", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
						cbQuestion1.setBounds(33, 105, 392, 31);
						fpPanel.add(cbQuestion1);
						
						tfAnswer1 = new JTextField();
						tfAnswer1.setBounds(33, 149, 392, 31);
						fpPanel.add(tfAnswer1);
						tfAnswer1.setColumns(10);
						
						JLabel lblQuestion2 = new JLabel("Question 2");
						lblQuestion2.setForeground(Color.BLUE);
						lblQuestion2.setFont(new Font("Arial", Font.BOLD, 11));
						lblQuestion2.setBounds(33, 191, 68, 14);
						fpPanel.add(lblQuestion2);
						
						JComboBox cbQuestion2 = new JComboBox();
						cbQuestion2.setModel(new DefaultComboBoxModel(new String[] {"Which phone number do you remember most from your childhood?", "What was your favorite place to visit as a child?", "Who is your favorite actor, musician, or artist?\\", "What is the name of your favorite pet?", "What is the first and last name of your first boyfriend or girlfriend?"}));
						cbQuestion2.setBounds(33, 216, 392, 31);
						fpPanel.add(cbQuestion2);
						
						tfAnswer2 = new JTextField();
						tfAnswer2.setColumns(10);
						tfAnswer2.setBounds(32, 261, 392, 31);
						fpPanel.add(tfAnswer2);
						
						JButton btnSubmit = new JButton("Submit");
						btnSubmit.setForeground(Color.WHITE);
						btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
						btnSubmit.setBackground(Color.BLUE);
						btnSubmit.setBounds(33, 306, 392, 42);
						fpPanel.add(btnSubmit);
						
						btnSubmit.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg1) {
								if(Q1==cbQuestion1.getSelectedIndex()&&ans1.equals(tfAnswer1.getText())&&Q2==cbQuestion2.getSelectedIndex()&&ans2.equals(tfAnswer2.getText()))
								{
									ProgressBar.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/fpstep3.png")));
									ProgressBar.setDisabledIcon(new ImageIcon(LoginManager.class.getResource("/com/img/fpstep3.png")));
									fpPanel.remove(lblQuestion1);
									fpPanel.remove(cbQuestion1);
									fpPanel.remove(tfAnswer1);
									fpPanel.remove(lblQuestion2);
									fpPanel.remove(cbQuestion2);
									fpPanel.remove(tfAnswer2);
									fpPanel.remove(btnSubmit);
									fpPanel.repaint();
									
									fpPanel.setLayout(null);
									fpPanel.setBackground(Color.WHITE);
									fpPanel.setBounds(449, 100, 466, 298);
									imgCont.add(fpPanel);
									
									JButton button_1 = new JButton("");
									button_1.setEnabled(false);
									button_1.setBorder(null);
									button_1.setBackground(Color.WHITE);
									button_1.setBounds(10, 11, 446, 61);
									fpPanel.add(button_1);
									
									JLabel lblNewPass = new JLabel("New Password");
									lblNewPass.setForeground(Color.BLUE);
									lblNewPass.setFont(new Font("Arial", Font.BOLD, 11));
									lblNewPass.setBounds(33, 83, 123, 14);
									fpPanel.add(lblNewPass);
									
									JLabel lblConfPass = new JLabel("Confrim Password");
									lblConfPass.setForeground(Color.BLUE);
									lblConfPass.setFont(new Font("Arial", Font.BOLD, 11));
									lblConfPass.setBounds(33, 161, 123, 14);
									fpPanel.add(lblConfPass);
									
									pfNewPass = new JPasswordField();
									pfNewPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
									pfNewPass.setBounds(33, 97, 392, 45);
									fpPanel.add(pfNewPass);
									
									pfConfPass = new JPasswordField();
									pfConfPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
									pfConfPass.setBounds(33, 176, 392, 45);
									fpPanel.add(pfConfPass);
									JButton btnDone = new JButton("Done");
									btnDone.addActionListener(new ActionListener() {
										
										public void actionPerformed(ActionEvent arg0) {
											
											if(pfNewPass.getText().equals(pfConfPass.getText()))
											{
												try {	
													String sql1 = "update uldetail set Pass='"+pfNewPass.getText()+"' where UserName='"+tfUserName.getText()+"'";
													Connection con = DBConnection.connection();
													Statement st = con.createStatement();
													st.executeUpdate(sql1);
													HeadSPanel.infoBox("Password Reset Successfully", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
												} catch (SQLException e) {
													e.printStackTrace();
												}
											}
											else
											{
												pfNewPass.setText("");
												pfConfPass.setText("");
												HeadSPanel.infoBox("These passwords don't match. Try again?", "Password Not Matched", JOptionPane.WARNING_MESSAGE);
											}
										}
									});
									btnDone.setForeground(Color.WHITE);
									btnDone.setFont(new Font("Tahoma", Font.PLAIN, 16));
									btnDone.setBackground(Color.BLUE);
									btnDone.setBounds(33, 241, 392, 42);
									fpPanel.add(btnDone);
								}
								else
								{
									HeadSPanel.infoBox("Wrong details entered", "Invalid Details", JOptionPane.ERROR_MESSAGE);
								}								
								
							}
						});
					}
					else
					{
						HeadSPanel.infoBox("You are not an registered user","Not an User", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					DBConnection.closeResource(con, st);
				}
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBackground(Color.BLUE);
		btnSearch.setBounds(33, 170, 392, 42);
		fpPanel.add(btnSearch);
		
		ProgressBar.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/fpstep1.png")));
		ProgressBar.setBorder(null);
		ProgressBar.setBackground(Color.WHITE);
		ProgressBar.setDisabledIcon(new ImageIcon(LoginManager.class.getResource("/com/img/fpstep1.png")));
		ProgressBar.setEnabled(false);
		ProgressBar.setBounds(10, 11, 446, 61);
		fpPanel.add(ProgressBar);
		
		
		getContentPane().add(Container);
	}
}
