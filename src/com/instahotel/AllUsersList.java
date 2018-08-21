package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Window.Type;

public class AllUsersList extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AllUsersList() {
		setTitle("InstaHotel - All Users");
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(148, 0, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 664, 439);
		contentPane.add(scrollPane);
		DefaultTableModel dtm = new DefaultTableModel(new String[]{"Name","Username","Type"},0);
		table = new JTable(dtm);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		Connection con = DBConnection.connection();
		Statement st=null;
		String sql;
		sql = "SELECT *from uldetail";
		ResultSet rs=null;
		int count=0;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			for (int i = dtm.getRowCount() - 1; i > -1; i--) 
			{
				dtm.removeRow(i);
			}
			while(rs.next())
			{
				dtm.insertRow(count, new Object[]{rs.getString("Name"),rs.getString("UserName"),rs.getString("Type")});
				count++;
			}						
		} catch (SQLException b) {
			b.printStackTrace();
		}
	}
}
