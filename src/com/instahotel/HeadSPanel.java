package com.instahotel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HeadSPanel {
	
	static ImagePanel imgHead;
	public static void drawHeader(Container cont)
	{
		JPanel header = new JPanel();
		header.setBorder(new EmptyBorder(-10, 0, 0, 0));
		header.setBounds(0, 0, 1362, 133);
		imgHead = new ImagePanel(new ImageIcon(LoginManager.class.getResource("/com/img/header.jpg")));
		header.add(imgHead);
		cont.add(header);
	}
	
	public static void drawUPanel(Container cont,Home h)
	{
		
		JPanel UserPanel = new JPanel();
		UserPanel.setBackground(Color.BLACK);
		UserPanel.setBounds(0, 151, 240, 554);
		cont.add(UserPanel);
		UserPanel.setLayout(null);
		
		JPanel AvtarPanel = new JPanel();
		ImagePanel imgAvtar = new ImagePanel(new ImageIcon(LoginManager.class.getResource("/com/img/defaultAvtar.png")));
		AvtarPanel.add(imgAvtar);
		AvtarPanel.setBounds(10, 11, 220, 220);
		UserPanel.add(AvtarPanel);
		
		JButton btnDashBoard = new JButton("");
		btnDashBoard.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDashBoard.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/dashboard.jpg")));
		btnDashBoard.setBounds(10, 242, 220, 50);
		UserPanel.add(btnDashBoard);
		
		JButton btnChangeAvatar = new JButton("");
		btnChangeAvatar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChangeAvatar.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/changeAvatar.jpg")));
		btnChangeAvatar.setBounds(10, 291, 220, 50);
		UserPanel.add(btnChangeAvatar);
		
		JButton btnEditProfile = new JButton("");
		btnEditProfile.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEditProfile.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/EditProfile.jpg")));
		btnEditProfile.setBounds(10, 340, 220, 50);
		UserPanel.add(btnEditProfile);
		
		JButton btnAccounts = new JButton("");
		btnAccounts.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAccounts.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/accounts.jpg")));
		btnAccounts.setBounds(10, 389, 220, 50);
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		UserPanel.add(btnAccounts);
				
		JButton btnSettings = new JButton("");
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/settings.jpg")));
		btnSettings.setBounds(10, 438, 220, 50);
		UserPanel.add(btnSettings);
		
		JButton btnLogout = new JButton("");
		btnLogout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogout.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/logout.jpg")));
		btnLogout.setBounds(10, 487, 220, 50);
		UserPanel.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginManager lm = new LoginManager(h);				
			}
		});
	}
	
	public static void infoBox(String infoMessage, String titleBar, int type)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, type);
    }
	
	public static JTextField BtnCounter(JPanel jp, int x, int y)
	{
		JPanel CounterPanel = new JPanel();
		CounterPanel.setBounds(x, y, 150, 20);
		jp.add(CounterPanel);
		CounterPanel.setLayout(null);
		
		JTextField tfValue = new JTextField();
		tfValue.setHorizontalAlignment(SwingConstants.CENTER);
		tfValue.setEditable(false);
		tfValue.setText(0+"");
		tfValue.setBounds(41, 0, 68, 20);
		CounterPanel.add(tfValue);
		tfValue.setColumns(10);
		
		JButton btnInc = new JButton("+");
		btnInc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfValue.setText((Integer.parseInt(tfValue.getText())+1)+"");
			}
		});
		btnInc.setBounds(0, 0, 43, 20);
		CounterPanel.add(btnInc);
		
		JButton btnDec = new JButton("-");
		btnDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(tfValue.getText())-1;
				if(value>=0)
				{
				tfValue.setText(value+"");
				}
				else
				{
					tfValue.setText(0+"");
				}
			}
		});
		btnDec.setBounds(107, 0, 43, 20);
		CounterPanel.add(btnDec);
		return tfValue;
	}
}
