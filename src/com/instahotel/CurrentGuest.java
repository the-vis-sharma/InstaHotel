package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CurrentGuest extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	
	JTable ReservRoomtable;
	public CurrentGuest() {
		setType(Type.UTILITY);
		setVisible(true);
		setBackground(new Color(138, 43, 226));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 662, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(138, 43, 226));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 625, 439);
		contentPane.add(scrollPane);
		
		DefaultTableModel dtm = new DefaultTableModel(new String[]{"Room No", "Customer Name", "Arrival Date", "Departure Date"},0);
		ReservRoomtable = new JTable(dtm);
		ReservRoomtable.setSelectionBackground(Color.PINK);
		ReservRoomtable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(ReservRoomtable);
		
		Connection con = DBConnection.connection();
		Statement st=null;
		String sql;
		boolean NoRoom=true;
		LocalDate now = LocalDate.now();
		String today = ""+now.getYear()+"-"+now.getMonthValue()+"-"+now.getDayOfMonth();
		sql = "SELECT *from walkindb where ArrivalDate <= '"+today+"' AND DepartureDate >= '"+today+"'";
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
				NoRoom = false;
				dtm.insertRow(count, new Object[]{rs.getInt("RoomNo"),rs.getString("CustomerName"),rs.getString("ArrivalDate"),rs.getString("DepartureDate")});
				count++;
			}
			if(NoRoom)
			{
				HeadSPanel.infoBox("There are no customers currently", "No Customer", JOptionPane.WARNING_MESSAGE);
			}
						
		} catch (SQLException b) {
			b.printStackTrace();
		}
	}
}
