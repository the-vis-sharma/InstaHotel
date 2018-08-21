package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountManger extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountManger frame = new AccountManger();
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
	public AccountManger() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AccountManger.class.getResource("/com/img/logo.jpg")));
		setTitle("InstaHotel - User Accounts");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel AccountsPanel = new JPanel();
		AccountsPanel.setLayout(null);
		AccountsPanel.setBackground(Color.WHITE);
		AccountsPanel.setBounds(267, 165, 1085, 529);
		contentPane.add(AccountsPanel);
		
		JLabel lblAcWelcomeBack = new JLabel("Welcome Back <dynamic>!");
		lblAcWelcomeBack.setForeground(Color.WHITE);
		lblAcWelcomeBack.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAcWelcomeBack.setBounds(56, 22, 236, 22);
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
		
		
	}
}
