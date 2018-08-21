
package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.jfree.chart.Effect3D;

import com.mysql.jdbc.PingTarget;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Home extends JFrame {
	
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	
	static ImagePanel imgAvtar;
	private JPanel DashBoardPanel, AccountsPanel, EditProfilePanel;
	private JPanel UserPanel;
	private JPanel AvtarPanel;
	private JButton btnLogout, btnDashBoard, btnEditProfile, btnSettings, btnAccounts, btnChangeAvatar;
	private JTextField txtChangePass;
	private JPasswordField pfOldPass;
	private JPasswordField pfNewPass;
	private JPasswordField pfConPass;
	private JTextField txtChangeSecurityQuestions;
	private JTextField tfAnswer1;
	private JTextField tfAnswer2;
	private JPanel SettingPanel;

	public Home(LoginManager lm,String curUser, ImageIcon dp, int utype) {
		
		lm.dispose();
		Home h = this;
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginManager.class.getResource("/com/img/logo.jpg")));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("InstaHotel - Home");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//-------------------------- Dashboard Panel -----------------------//
		DashBoardPanel = new JPanel();
		DashBoardPanel.setBackground(Color.WHITE);
		DashBoardPanel.setBounds(259, 151, 1085, 529);
		contentPane.add(DashBoardPanel);
		DashBoardPanel.setLayout(null);
		
		HeadSPanel.drawHeader(getContentPane());
		JLabel lblDBWelcomeBack = new JLabel("Welcome Back "+curUser+"!");
		lblDBWelcomeBack.setForeground(Color.WHITE);
		lblDBWelcomeBack.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDBWelcomeBack.setBounds(56, 22, 387, 22);
		DashBoardPanel.add(lblDBWelcomeBack);
		
		JButton DBTopBar = new JButton("");
		DBTopBar.setEnabled(false);
		DBTopBar.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		DBTopBar.setBackground(Color.BLACK);
		DBTopBar.setIcon(null);
		DBTopBar.setFont(new Font("Arial", Font.PLAIN, 18));
		DBTopBar.setHorizontalAlignment(SwingConstants.LEFT);
		DBTopBar.setBounds(39, 11, 1007, 45);
		DashBoardPanel.add(DBTopBar);
		
		JButton btnWalkIn = new JButton("");
		btnWalkIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WalkIn wi = new WalkIn();
			}
		});
		btnWalkIn.setHorizontalTextPosition(SwingConstants.CENTER);
		btnWalkIn.setIcon(new ImageIcon(Home.class.getResource("/com/img/walk-in.jpg")));
		btnWalkIn.setBounds(40, 62, 401, 150);
		DashBoardPanel.add(btnWalkIn);
		
		JButton btnRoomInfo = new JButton("");
		btnRoomInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRoom ad = new AddRoom();
			}
		});
		btnRoomInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRoomInfo.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/room-info.jpg")));
		btnRoomInfo.setBounds(40, 215, 200, 150);
		DashBoardPanel.add(btnRoomInfo);
		
		JButton btnNewReservation = new JButton("");
		btnNewReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewReservation nr = new NewReservation();
			}
		});
		btnNewReservation.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewReservation.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/new-reservation.jpg")));
		btnNewReservation.setBounds(444, 62, 200, 150);
		DashBoardPanel.add(btnNewReservation);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckOut co = new CheckOut();
			}
		});
		button_4.setIcon(new ImageIcon(getClass().getResource("/com/img/CheckOut.jpg")));
		button_4.setBounds(243, 214, 401, 150);
		DashBoardPanel.add(button_4);
		
		JButton btnSearchReservation = new JButton("");
		btnSearchReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchCancelReservation scr = new SearchCancelReservation();
			}
		});
		btnSearchReservation.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearchReservation.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/search-reservation.jpg")));
		btnSearchReservation.setBounds(646, 62, 200, 150);
		DashBoardPanel.add(btnSearchReservation);
		
		JButton btnAboutUs = new JButton("");
		btnAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUs au = new AboutUs();
			}
		});
		btnAboutUs.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/about-us.jpg")));
		btnAboutUs.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAboutUs.setBounds(241, 369, 200, 150);
		DashBoardPanel.add(btnAboutUs);
		
		JButton btnCurrentGuests = new JButton("");
		btnCurrentGuests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CurrentGuest cg = new CurrentGuest();
			}
		});
		btnCurrentGuests.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/current-guests.jpg")));
		btnCurrentGuests.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCurrentGuests.setBounds(645, 215, 200, 150);
		DashBoardPanel.add(btnCurrentGuests);
		
		JButton btnContactUs = new JButton("");
		btnContactUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContactUs cu = new ContactUs();
			}
		});
		
		btnContactUs.setIcon(new ImageIcon(Home.class.getResource("/com/img/contact-us.jpg")));
		btnContactUs.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContactUs.setBounds(445, 368, 401, 150);
		DashBoardPanel.add(btnContactUs);
		
		JButton btnInformation = new JButton("");
		btnInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Information i = new Information();
			}
		});
		btnInformation.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/information.jpg")));
		btnInformation.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInformation.setBounds(40, 369, 200, 150);
		DashBoardPanel.add(btnInformation);
		
		JButton btnReport = new JButton("");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reports r = new Reports();
			}
		});
		btnReport.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/reports.jpg")));
		btnReport.setHorizontalTextPosition(SwingConstants.CENTER);
		btnReport.setBounds(847, 61, 200, 304);
		DashBoardPanel.add(btnReport);
		
		JButton btnHelp = new JButton("");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help h = new Help();
			}
		});
		btnHelp.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/help.jpg")));
		btnHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHelp.setBounds(847, 368, 200, 150);
		DashBoardPanel.add(btnHelp);
		
		//--------------------- Accounts Panel ---------------------------//
		
		AccountsPanel = new JPanel();
		AccountsPanel.setLayout(null);
		AccountsPanel.setBackground(Color.WHITE);
		AccountsPanel.setBounds(259, 151, 1085, 529);
		AccountsPanel.setVisible(false);
		contentPane.add(AccountsPanel);
		
		JLabel lblAcWelcomeBack = new JLabel("Welcome Back "+curUser+"!");
		lblAcWelcomeBack.setForeground(Color.WHITE);
		lblAcWelcomeBack.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAcWelcomeBack.setBounds(56, 22, 387, 22);
		AccountsPanel.add(lblAcWelcomeBack);
		
		JButton AcTopBar = new JButton("");
		AcTopBar.setHorizontalAlignment(SwingConstants.LEFT);
		AcTopBar.setFont(new Font("Arial", Font.PLAIN, 18));
		AcTopBar.setEnabled(false);
		AcTopBar.setBackground(Color.BLACK);
		AcTopBar.setBounds(39, 11, 1007, 45);
		AccountsPanel.add(AcTopBar);
		
		JButton btnAddNewUser = new JButton("");
		btnAddNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUser au = new AddUser();
			}
		});
		btnAddNewUser.setIcon(new ImageIcon(AccountManger.class.getResource("/com/img/AddUser.jpg")));
		btnAddNewUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAddNewUser.setBounds(40, 62, 200, 150);
		AccountsPanel.add(btnAddNewUser);
		
		JButton btnSearchUser = new JButton("");
		btnSearchUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchUser su = new SearchUser();
			}
		});
		btnSearchUser.setIcon(new ImageIcon(AccountManger.class.getResource("/com/img/SearchUser.jpg")));
		btnSearchUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearchUser.setBounds(242, 62, 200, 150);
		AccountsPanel.add(btnSearchUser);
		
		JButton btnModifyUser = new JButton("");
		btnModifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifyUser mu = new ModifyUser();
			}
		});
		btnModifyUser.setIcon(new ImageIcon(AccountManger.class.getResource("/com/img/ModifyUser.jpg")));
		btnModifyUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModifyUser.setBounds(444, 62, 200, 150);
		AccountsPanel.add(btnModifyUser);
		
		JButton btnDeleteUser = new JButton("");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveUser ru = new RemoveUser();
			}
		});
		btnDeleteUser.setIcon(new ImageIcon(AccountManger.class.getResource("/com/img/DeleteUser.jpg")));
		btnDeleteUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDeleteUser.setBounds(646, 62, 200, 150);
		AccountsPanel.add(btnDeleteUser);
		
		JButton btnAllUser = new JButton("");
		btnAllUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AllUsersList aul = new AllUsersList();
			}
		});
		btnAllUser.setIcon(new ImageIcon(AccountManger.class.getResource("/com/img/AllUser.jpg")));
		btnAllUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAllUser.setBounds(847, 62, 200, 150);
		AccountsPanel.add(btnAllUser);
		
		JPanel UserPanel = new JPanel();
		UserPanel.setBackground(Color.BLACK);
		UserPanel.setBounds(0, 151, 240, 554);
		getContentPane().add(UserPanel);
		UserPanel.setLayout(null);
		
		JPanel AvtarPanel = new JPanel();
		AvtarPanel.setBounds(10, 11, 220, 220);
		UserPanel.add(AvtarPanel);
		AvtarPanel.setLayout(null);
		
		JLabel dpLbl = new JLabel("");
		dpLbl.setIcon(dp);
		dpLbl.setBounds(0, 0, 220, 220);
		AvtarPanel.add(dpLbl);
		
		btnDashBoard = new JButton("");
		btnChangeAvatar = new JButton("");
		btnChangeAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeAvtar.storeImage(h,curUser);
				ImageIcon newdp = ChangeAvtar.retriveImage(h, curUser);
				dpLbl.setIcon(newdp);
			}
		});
		btnEditProfile = new JButton("");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoardPanel.setVisible(false);
				btnDashBoard.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/dashboard.jpg")));
				AccountsPanel.setVisible(false);
				btnAccounts.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/accounts.jpg")));
				EditProfilePanel.setVisible(true);
				btnEditProfile.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/EditProfileSelected.jpg")));
			}
		});
		btnAccounts = new JButton("");
		btnSettings =  new JButton("");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoardPanel.setVisible(false);
				btnDashBoard.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/dashboard.jpg")));
				AccountsPanel.setVisible(false);
				btnAccounts.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/accounts.jpg")));
				EditProfilePanel.setVisible(false);
				btnEditProfile.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/EditProfile.jpg")));
				SettingPanel.setVisible(true);
				btnSettings.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/SettingsSelected.jpg")));
			}
		});
		btnDashBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DashBoardPanel.setVisible(true);
				btnDashBoard.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/dashboardSelected.jpg")));
				AccountsPanel.setVisible(false);
				btnAccounts.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/accounts.jpg")));
				EditProfilePanel.setVisible(false);
				btnEditProfile.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/EditProfile.jpg")));
				SettingPanel.setVisible(false);
				btnSettings.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/Settings.jpg")));
			}
		});
		btnDashBoard.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDashBoard.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/dashboardSelected.jpg")));
		btnDashBoard.setBounds(10, 242, 220, 50);
		UserPanel.add(btnDashBoard);
		
		btnChangeAvatar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChangeAvatar.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/changeAvatar.jpg")));
		btnChangeAvatar.setBounds(10, 291, 220, 50);
		UserPanel.add(btnChangeAvatar);
		
		btnEditProfile.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEditProfile.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/EditProfile.jpg")));
		btnEditProfile.setBounds(10, 340, 220, 50);
		UserPanel.add(btnEditProfile);
		
		btnAccounts.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAccounts.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/accounts.jpg")));
		btnAccounts.setBounds(10, 389, 220, 50);
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DashBoardPanel.setVisible(false);
				btnDashBoard.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/dashboard.jpg")));
				AccountsPanel.setVisible(true);
				btnAccounts.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/accountsSelected.jpg")));
				EditProfilePanel.setVisible(false);
				btnEditProfile.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/EditProfile.jpg")));
				SettingPanel.setVisible(false);
				btnSettings.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/Settings.jpg")));
			}
		});
		if(utype==1) btnAccounts.setVisible(false);
		UserPanel.add(btnAccounts);
				
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/settings.jpg")));
		if(utype==1) btnSettings.setBounds(10, 389, 220, 50);
		else btnSettings.setBounds(10, 438, 220, 50);
		UserPanel.add(btnSettings);
		
		JButton btnLogout = new JButton("");
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/logout.jpg")));
		if(utype==1) btnSettings.setBounds(10, 438, 220, 50);
		else btnLogout.setBounds(10, 487, 220, 50);
		UserPanel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginManager lm = new LoginManager(h);				
			}
		});
		
		//----------------------------Edit Profile----------------------//
		JTextField tfFirstName, tfLastName, tfEmail, tfMobile;
		EditProfilePanel = new JPanel();
		EditProfilePanel.setBackground(Color.WHITE);
		EditProfilePanel.setBounds(259, 151, 1085, 529);
		contentPane.add(EditProfilePanel);
		EditProfilePanel.setVisible(false);
		EditProfilePanel.setLayout(null);
		
		JLabel lblEPWelcomeBack = new JLabel("Welcome Back "+curUser+"!");
		lblEPWelcomeBack.setForeground(Color.WHITE);
		lblEPWelcomeBack.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEPWelcomeBack.setBounds(56, 22, 387, 22);
		EditProfilePanel.add(lblEPWelcomeBack);
		
		JButton EPTopBar = new JButton("");
		EPTopBar.setHorizontalAlignment(SwingConstants.LEFT);
		EPTopBar.setFont(new Font("Arial", Font.PLAIN, 18));
		EPTopBar.setEnabled(false);
		EPTopBar.setBackground(Color.BLACK);
		EPTopBar.setBounds(39, 11, 1007, 45);
		EditProfilePanel.add(EPTopBar);
		
		JPanel PInfoPanel = new JPanel();
		PInfoPanel.setLayout(null);
		PInfoPanel.setBackground(new Color(205, 133, 63));
		PInfoPanel.setBounds(280, 80, 540 , 315);
		EditProfilePanel.add(PInfoPanel);
		
		JTextField PInfoHeader = new JTextField();
		PInfoHeader.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		PInfoHeader.setFocusable(false);
		PInfoHeader.setEditable(false);
		PInfoHeader.setForeground(new Color(255, 255, 255));
		PInfoHeader.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.WHITE));
		PInfoHeader.setText("    Personal Information");
		PInfoHeader.setBackground(Color.BLUE);
		PInfoHeader.setBounds(10, 20, 510, 30);
		PInfoHeader.setColumns(10);
		PInfoPanel.add(PInfoHeader);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 60, 186, 31);
		PInfoPanel.add(lblNewLabel);
		
		tfFirstName = new JTextField();
		tfFirstName.setBackground(new Color(205, 133, 63));
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBounds(260, 60, 250, 31);
		PInfoPanel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(20, 102, 186, 31);
		PInfoPanel.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setBackground(new Color(205, 133, 63));
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setColumns(10);
		tfLastName.setBounds(260, 102, 250, 31);
		PInfoPanel.add(tfLastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(20, 144, 186, 31);
		PInfoPanel.add(lblGender);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(20, 186, 186, 31);
		PInfoPanel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBackground(new Color(205, 133, 63));
		tfEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfEmail.setColumns(10);
		tfEmail.setBounds(260, 186, 250, 31);
		PInfoPanel.add(tfEmail);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMobile.setBounds(20, 228, 186, 31);
		PInfoPanel.add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setBackground(new Color(205, 133, 63));
		tfMobile.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfMobile.setColumns(10);
		tfMobile.setBounds(260, 228, 250, 31);
		PInfoPanel.add(tfMobile);
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setBounds(260, 150, 70, 23);
		rbMale.setBackground(new Color(205, 133, 63));
		rbMale.setActionCommand("Male");
		bg.add(rbMale);
		PInfoPanel.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setBounds(348, 150, 70, 23);
		rbFemale.setActionCommand("Female");
		rbFemale.setBackground(new Color(205, 133, 63));
		bg.add(rbFemale);
		PInfoPanel.add(rbFemale);
		
		JRadioButton rbOther = new JRadioButton("Other");
		rbOther.setBounds(440, 150, 70, 23);
		rbOther.setActionCommand("Other");
		rbOther.setBackground(new Color(205, 133, 63));
		bg.add(rbOther);
		PInfoPanel.add(rbOther);
		
		Connection con = DBConnection.connection();
		Statement st=null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from uldetail where Name='"+curUser+"'");
			rs.next();
			String[] name = curUser.split(" ");
			tfFirstName.setText(name[0]);			
			if(name.length>1)
				tfLastName.setText(name[1]);
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
		btnSaveChanges.setBounds(20, 270, 491, 31);
		btnSaveChanges.setBackground(Color.BLUE);
		btnSaveChanges.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btnSaveChanges.setForeground(Color.WHITE);
		PInfoPanel.add(btnSaveChanges);
		
		//------------------------------------ Settings --------------------------------------//
		
		SettingPanel = new JPanel();
		SettingPanel.setBounds(259, 151, 1085, 529);
		contentPane.add(SettingPanel);
		SettingPanel.setLayout(null);
		SettingPanel.setVisible(false);
		SettingPanel.setBackground(Color.WHITE);
		
		JLabel lblSPWelcomeBack = new JLabel("Welcome Back "+curUser+"!");
		lblSPWelcomeBack.setForeground(Color.WHITE);
		lblSPWelcomeBack.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSPWelcomeBack.setBounds(56, 22, 387, 22);
		SettingPanel.add(lblSPWelcomeBack);
		
		JButton SPTopBar = new JButton("");
		SPTopBar.setHorizontalAlignment(SwingConstants.LEFT);
		SPTopBar.setFont(new Font("Arial", Font.PLAIN, 18));
		SPTopBar.setEnabled(false);
		SPTopBar.setBackground(Color.BLACK);
		SPTopBar.setBounds(39, 11, 1007, 45);
		SettingPanel.add(SPTopBar);
		
		JPanel ChangePassPanel = new JPanel();
		ChangePassPanel.setBackground(new Color(205, 133, 63));
		ChangePassPanel.setBounds(595, 145, 450, 250);
		SettingPanel.add(ChangePassPanel);
		ChangePassPanel.setLayout(null);
		
		txtChangePass = new JTextField();
		txtChangePass.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		txtChangePass.setFocusable(false);
		txtChangePass.setEditable(false);
		txtChangePass.setForeground(new Color(255, 255, 255));
		txtChangePass.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.WHITE));
		txtChangePass.setBackground(Color.BLUE);
		txtChangePass.setText("    Change Password");
		txtChangePass.setBounds(10, 21, 430, 30);
		ChangePassPanel.add(txtChangePass);
		txtChangePass.setColumns(10);
		
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
