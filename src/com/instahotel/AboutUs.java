package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Cursor;
import java.awt.Font;

public class AboutUs extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public AboutUs() {
		setTitle("InstaHotel - About Us");
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 50, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnTheMotiveOf = new JTextPane();
		txtpnTheMotiveOf.setText("\tThe motive of our project is to control various activities performed. In the Hotel using the computer with the work of InstaHotel. From the beginning, the important thing in our mind is that we should concentrate our project work on a subject that is easy to understand and is according to our daily requirement. By keeping this in mind, the subject chosen by us is hotel management. \r\n\tIn the present time there is a great rush in hotels, as these have become necessity for middle and upper class of the society. People travel a lot, stay in hotels, goes to the hotels for functions, meeting and refreshment. Our project is developed keeping in mind the general needs of the customers when he goes to the hospital. An important uniqueness about the described Hotel\u2019s restaurants and bar are available to only those customers who have already booked room or hall in hospital.");
		txtpnTheMotiveOf.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpnTheMotiveOf.setEditable(false);
		txtpnTheMotiveOf.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnTheMotiveOf.setBounds(10, 11, 664, 229);
		contentPane.add(txtpnTheMotiveOf);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 238, 664, 212);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JTextPane txtpnSubmittedBy = new JTextPane();
		txtpnSubmittedBy.setEditable(false);
		txtpnSubmittedBy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnSubmittedBy.setVerifyInputWhenFocusTarget(false);
		txtpnSubmittedBy.setText("Submitted By :\r\nVishnu Bochiwal\r\nShubham Kabra\r\nBatch - C3, Year - III\r\nBranch - Computer Engineering\r\nPoornima College of Engineering, Jaipur");
		txtpnSubmittedBy.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		txtpnSubmittedBy.setBounds(389, 11, 265, 139);
		panel.add(txtpnSubmittedBy);
	}
}
