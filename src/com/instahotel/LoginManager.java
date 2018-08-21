package com.instahotel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Desktop.Action;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginManager extends JFrame {
	static LoginManager frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {	
				try {
					Home h=null;
					frame = new LoginManager(h);			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	static ImagePanel imgLBox, imgCont;
	private JPanel loginBox;
	private JTextField tfUName;
	private JPasswordField pfPass;
	public LoginManager(Home h) {
		DBConnection.createDB();
		if(h!=null)
		{
			h.dispose();
		}
		frame = this;
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginManager.class.getResource("/com/img/logo.jpg")));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("InstaHotel - Login");
		getContentPane().setLayout(null);
		
		HeadSPanel.drawHeader(getContentPane());
		
		JPanel Container = new JPanel();
		Container.setBorder(new EmptyBorder(-10, 0, 0, 0));
		Container.setBounds(0, 133, 1362, 572);
		imgCont = new ImagePanel(new ImageIcon(LoginManager.class.getResource("/com/img/back.jpg")));
		Container.add(imgCont);
		getContentPane().add(Container);
		
		loginBox = new JPanel();
		loginBox.setBackground(Color.WHITE);
		loginBox.setBounds(449, 100, 466, 300);
		imgCont.add(loginBox);
		loginBox.setLayout(null);
		
		JLabel lblWB = new JLabel("Welcome Back");
		lblWB.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/wbheader.jpg")));
		lblWB.setHorizontalAlignment(SwingConstants.CENTER);
		lblWB.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblWB.setForeground(Color.BLACK);
		lblWB.setBounds(10, 11, 446, 52);
		lblWB.setBackground(UIManager.getColor("Button.focus"));
		loginBox.add(lblWB);
		
		JLabel lblUName = new JLabel("User Name");
		lblUName.setFont(new Font("Arial", Font.BOLD, 11));
		lblUName.setForeground(Color.BLUE);
		lblUName.setBounds(33, 74, 66, 14);
		loginBox.add(lblUName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 11));
		lblPassword.setBounds(33, 152, 66, 14);
		loginBox.add(lblPassword);
		
		tfUName = new JTextField();
		tfUName.setBounds(33, 99, 392, 42);
		tfUName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 255)));
		loginBox.add(tfUName);
		tfUName.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		javax.swing.Action LoginAction = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Connection con = DBConnection.connection();
				Statement st=null;
				String sql="select *from uldetail where UserName='"+tfUName.getText()+"'";
				ResultSet rs=null;
				try {
					st = con.createStatement();
					rs = st.executeQuery(sql);
					if(rs.next())
					{
						if(rs.getString("Pass").equals(pfPass.getText()))
						{
							Home h = new Home(frame,rs.getString("Name"),new ImageIcon(rs.getBytes("dp")),rs.getInt("Type"));
						}
						else
						{
							HeadSPanel.infoBox("Invalid User Name or Password","Invalid Details", JOptionPane.ERROR_MESSAGE);
						}
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
		};
		
		btnNewButton.setAction(LoginAction);
		btnNewButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Login");
		btnNewButton.getActionMap().put("Login", LoginAction);
		btnNewButton.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/blogin.jpg")));
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(33, 230, 392, 42);
		loginBox.add(btnNewButton);
		
		pfPass = new JPasswordField();
		pfPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLUE));
		pfPass.setBounds(33, 177, 392, 42);
		loginBox.add(pfPass);
		
		JLabel lblNeedHelpForgot = new JLabel("Forgot Password?");
		lblNeedHelpForgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ForPass fp = new ForPass(frame);
			}
		});
		lblNeedHelpForgot.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNeedHelpForgot.setForeground(Color.BLUE);
		lblNeedHelpForgot.setBounds(334, 275, 93, 14);
		loginBox.add(lblNeedHelpForgot);
		getContentPane().add(Container);

	}
}
