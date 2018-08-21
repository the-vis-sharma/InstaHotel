package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Information extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfNumber;
	private JTextField tfPosition;
	private JTextField txtAddNewNumber;
	private JTextField txtHotelPhoneBook;
	private JTable PhoneBookTable;

	/**
	 * Create the frame.
	 */
	
	public Information() {
		setTitle("InstaHotel - Information");
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 670, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(138, 43, 226));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel HPBPanel = new JPanel();
		HPBPanel.setLayout(null);
		HPBPanel.setBackground(new Color(255, 182, 193));
		HPBPanel.setBounds(10, 154, 634, 270);
		contentPane.add(HPBPanel);
		
		txtHotelPhoneBook = new JTextField();
		txtHotelPhoneBook.setText("    Hotel Phone Book");
		txtHotelPhoneBook.setForeground(Color.WHITE);
		txtHotelPhoneBook.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtHotelPhoneBook.setEditable(false);
		txtHotelPhoneBook.setColumns(10);
		txtHotelPhoneBook.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtHotelPhoneBook.setBackground(Color.BLUE);
		txtHotelPhoneBook.setBounds(10, 11, 614, 34);
		HPBPanel.add(txtHotelPhoneBook);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 614, 203);
		HPBPanel.add(scrollPane);
		
		DefaultTableModel dtm = new DefaultTableModel(new String[]{"Name", "Number", "Position"}, 0);
		PhoneBookTable = new JTable(dtm);
		scrollPane.setViewportView(PhoneBookTable);
		
		Connection con = DBConnection.connection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select *from PhoneBookDB";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			int count=0;
			while(rs.next())
			{
				dtm.insertRow(count, new Object[]{rs.getString("Name"),rs.getString("Number"),rs.getString("Position")});
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JPanel AddNumberPanel = new JPanel();
		AddNumberPanel.setBackground(new Color(255, 182, 193));
		AddNumberPanel.setBounds(10, 11, 634, 132);
		contentPane.add(AddNumberPanel);
		AddNumberPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblName.setBounds(10, 60, 67, 17);
		AddNumberPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfName.setBackground(new Color(255, 182, 193));
		tfName.setBounds(10, 85, 171, 34);
		AddNumberPanel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNumber.setBounds(202, 60, 67, 17);
		AddNumberPanel.add(lblNumber);
		
		tfNumber = new JTextField();
		tfNumber.setColumns(10);
		tfNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfNumber.setBackground(new Color(255, 182, 193));
		tfNumber.setBounds(202, 85, 171, 34);
		AddNumberPanel.add(tfNumber);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPosition.setBounds(397, 60, 67, 17);
		AddNumberPanel.add(lblPosition);
		
		tfPosition = new JTextField();
		tfPosition.setColumns(10);
		tfPosition.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfPosition.setBackground(new Color(255, 182, 193));
		tfPosition.setBounds(397, 85, 171, 34);
		AddNumberPanel.add(tfPosition);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean Valid = true;
				if(tfName.getText().equals(""))
				{
					Valid = false;
					HeadSPanel.infoBox("Name can't be empty", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfNumber.getText().equals(""))
				{
					Valid = false;
					HeadSPanel.infoBox("Number can't be empty", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(!DBConnection.validateMobile(tfNumber.getText()))
				{
					Valid = false;
					HeadSPanel.infoBox("Enter a valid Number", "Invalid Number", JOptionPane.ERROR_MESSAGE);
				}
				else if(Valid)
				{
					Connection con = DBConnection.connection();
					Statement st = null;
					String sql = "replace into PhoneBookDB value ('"+tfName.getText()+"',"+tfNumber.getText()+",'"+tfPosition.getText()+"')";
					String sql1 = "select *from PhoneBookDB";
					ResultSet rs = null;
					try {
						st = con.createStatement();
						st.executeUpdate(sql);
						rs = st.executeQuery(sql1);
						tfName.setText("");
						tfNumber.setText("");
						tfPosition.setText("");
						HeadSPanel.infoBox("Number Added Successfully", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
						int count=0;
						for (int i = dtm.getRowCount() - 1; i > -1; i--) 
						{
							dtm.removeRow(i);
						}
						while(rs.next())
						{
							dtm.insertRow(count, new Object[]{rs.getString("Name"),rs.getString("Number"),rs.getString("Position")});
							count++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnSave.setToolTipText("Save");
		btnSave.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSave.setBackground(new Color(255, 182, 193));
		btnSave.setBounds(578, 79, 46, 44);
		AddNumberPanel.add(btnSave);
		
		txtAddNewNumber = new JTextField();
		txtAddNewNumber.setForeground(new Color(255, 255, 255));
		txtAddNewNumber.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtAddNewNumber.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtAddNewNumber.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtAddNewNumber.setText("    Add New Number");
		txtAddNewNumber.setBackground(new Color(0, 0, 255));
		txtAddNewNumber.setEditable(false);
		txtAddNewNumber.setBounds(10, 11, 614, 34);
		AddNumberPanel.add(txtAddNewNumber);
		txtAddNewNumber.setColumns(10);
	}
}
