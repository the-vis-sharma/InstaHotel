package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePickerImpl;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class SearchCancelReservation extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearchCancel;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTable ReservRoomtable;
	private ResultSet cDetails;

	/**
	 * Create the frame.
	 */
	public SearchCancelReservation() {
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 670, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Panel = new JPanel();
		Panel.setLayout(null);
		Panel.setBackground(new Color(255, 182, 193));
		Panel.setBounds(0, 0, 655, 329);
		contentPane.add(Panel);
		
		JPanel CancelRerservPanel = new JPanel();
		CancelRerservPanel.setBackground(new Color(255, 182, 193));
		CancelRerservPanel.setBounds(20, 168, 625, 161);
		Panel.add(CancelRerservPanel);
		CancelRerservPanel.setLayout(null);
		CancelRerservPanel.setVisible(false);
		DefaultTableModel dtm = new DefaultTableModel(new String[]{"Room No", "Customer Name", "Arrival Date", "Departure Date"},0);
		
		tfSearchCancel = new JTextField();
		tfSearchCancel.setText("    Enter Following Details to Search or Cancel Reservation");
		tfSearchCancel.setForeground(Color.WHITE);
		tfSearchCancel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		tfSearchCancel.setEditable(false);
		tfSearchCancel.setColumns(10);
		tfSearchCancel.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfSearchCancel.setBackground(new Color(135, 206, 250));
		tfSearchCancel.setBounds(10, 11, 635, 30);
		Panel.add(tfSearchCancel);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFrom.setBounds(20, 49, 109, 14);
		Panel.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTo.setBounds(166, 52, 109, 14);
		Panel.add(lblTo);
		
		JPanel FDPanel = new JPanel();
		FDPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FDPanel.setBackground(SystemColor.menu);
		FDPanel.setBounds(20, 66, 120, 37);
		Panel.add(FDPanel);
		FDPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		LocalDate now = LocalDate.now();
		JDatePickerImpl dpFrom = CreateJDatePicker.JDatePicker(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
		FDPanel.add(dpFrom);
		
		JPanel TDPanel = new JPanel();
		TDPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		TDPanel.setBackground(SystemColor.menu);
		TDPanel.setBounds(166, 69, 120, 37);
		Panel.add(TDPanel);
		TDPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JDatePickerImpl dpTo = CreateJDatePicker.JDatePicker(now.getDayOfMonth()+1, now.getMonthValue(), now.getYear());
		TDPanel.add(dpTo);
		
		JButton btnCRA = new JButton("Search Reservation");
		btnCRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CreateJDatePicker.Before(dpFrom, dpTo))
				{
					Connection con = DBConnection.connection();
					Statement st=null;
					String sql;
					boolean NoRoom=true;
					if(tfFirstName.getText().equals(""))
					{
						sql = "SELECT *from reservationdb where ArrivalDate >= '"+CreateJDatePicker.getSelectedDate(dpFrom)+"' AND ArrivalDate <= '"+CreateJDatePicker.getSelectedDate(dpTo)+"'";
					}
					else
					{
						sql = "SELECT *from reservationdb where ArrivalDate >= '"+CreateJDatePicker.getSelectedDate(dpFrom)+"' AND ArrivalDate <= '"+CreateJDatePicker.getSelectedDate(dpTo)+"' AND CustomerName='"+tfFirstName.getText()+" "+tfLastName.getText()+"'";
					}
					ResultSet rs=null;
					int count=0;
					try {
						st = con.createStatement();
						rs = st.executeQuery(sql);
						cDetails = rs;
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
							HeadSPanel.infoBox("No reservation record found", "No Reservation", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							CancelRerservPanel.setVisible(true);
						}
									
					} catch (SQLException b) {
						b.printStackTrace();
					}
				}
				else
				{
					HeadSPanel.infoBox("The From date must comes before To date", "Invalid Dates", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCRA.setBounds(495, 123, 150, 37);
		Panel.add(btnCRA);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFirstName.setBounds(311, 52, 109, 14);
		Panel.add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBackground(new Color(255, 182, 193));
		tfFirstName.setBounds(311, 70, 150, 33);
		Panel.add(tfFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLastName.setBounds(495, 52, 109, 14);
		Panel.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setBackground(new Color(255, 182, 193));
		tfLastName.setBounds(495, 70, 150, 33);
		Panel.add(tfLastName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 625, 97);
		CancelRerservPanel.add(scrollPane);
		
		ReservRoomtable = new JTable(dtm);
		ReservRoomtable.setSelectionBackground(Color.PINK);
		ReservRoomtable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(ReservRoomtable);
		
		JButton btnCancelReserv = new JButton("");
		btnCancelReserv.setBackground(new Color(255, 182, 193));
		btnCancelReserv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = DBConnection.connection();
				Statement st=null;
				String sql;
				
				if(tfFirstName.getText().equals(""))
				{
					sql = "delete from reservationdb where ArrivalDate >= '"+CreateJDatePicker.getSelectedDate(dpFrom)+"' AND ArrivalDate <= '"+CreateJDatePicker.getSelectedDate(dpTo)+"'";
				}
				else
				{
					sql = "delete from reservationdb where ArrivalDate >= '"+CreateJDatePicker.getSelectedDate(dpFrom)+"' AND ArrivalDate <= '"+CreateJDatePicker.getSelectedDate(dpTo)+"' AND CustomerName='"+tfFirstName.getText()+" "+tfLastName.getText()+"'";
				}
				ResultSet rs=null;
				int count=0;
				try {
					st = con.createStatement();
					st.executeUpdate(sql);	
					HeadSPanel.infoBox("Your reservation has been cancelled", "Cancelation Confrim", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (SQLException b) {
					b.printStackTrace();
				}
			}
		});
		btnCancelReserv.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCancelReserv.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/recycleBin.png")));
		btnCancelReserv.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelReserv.setBounds(508, 106, 46, 44);
		CancelRerservPanel.add(btnCancelReserv);
		
		JButton button = new JButton("");
		button.setToolTipText("Check-in");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CheckIn c = new CheckIn(cDetails);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(SearchCancelReservation.class.getResource("/com/img/walkin_Icon.png")));
		button.setBounds(569, 108, 46, 44);
		CancelRerservPanel.add(button);
		
	}
}
