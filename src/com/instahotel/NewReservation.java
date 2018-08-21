package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class NewReservation extends JFrame {

	private JPanel contentPane;
	private JTextField tfRoomDetails;
	private JTextField tfPersonalDetails;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	private JTable AvailRoomtable;
	private JTextField tfMobile;
	public NewReservation() {
		setTitle("InstaHotel - New Reservation");
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 692, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(147, 112, 219));
		panel.setBounds(0, 0, 675, 468);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 655, 104);
		panel.add(scrollPane);
		
		MyTableModel mtm = new MyTableModel(new String[]{"Select", "Room No", "Type", "Floor Location", "Rent"});
		AvailRoomtable = new JTable(mtm);
		AvailRoomtable.setSelectionBackground(Color.PINK);
		AvailRoomtable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(AvailRoomtable);
		
		JPanel RoomDetailsPanel = new JPanel();
		RoomDetailsPanel.setLayout(null);
		RoomDetailsPanel.setBackground(new Color(255, 182, 193));
		RoomDetailsPanel.setBounds(10, 11, 655, 113);
		panel.add(RoomDetailsPanel);
		
		tfRoomDetails = new JTextField();
		tfRoomDetails.setText("    Room Details");
		tfRoomDetails.setForeground(Color.WHITE);
		tfRoomDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		tfRoomDetails.setEditable(false);
		tfRoomDetails.setColumns(10);
		tfRoomDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfRoomDetails.setBackground(new Color(135, 206, 250));
		tfRoomDetails.setBounds(10, 11, 635, 30);
		RoomDetailsPanel.add(tfRoomDetails);
		
		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblArrivalDate.setBounds(20, 49, 109, 14);
		RoomDetailsPanel.add(lblArrivalDate);
		
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDepartureDate.setBounds(237, 49, 109, 14);
		RoomDetailsPanel.add(lblDepartureDate);
		
		JPanel ADPanel = new JPanel();
		ADPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		ADPanel.setBackground(SystemColor.menu);
		ADPanel.setBounds(20, 66, 120, 37);
		RoomDetailsPanel.add(ADPanel);
		ADPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		LocalDate now = LocalDate.now();
		JDatePickerImpl dpArrival = CreateJDatePicker.JDatePicker(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
		ADPanel.add(dpArrival);
		
		JPanel DDPanel = new JPanel();
		DDPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		DDPanel.setBackground(SystemColor.menu);
		DDPanel.setBounds(237, 66, 120, 37);
		RoomDetailsPanel.add(DDPanel);
		DDPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JDatePickerImpl dpDeparture = CreateJDatePicker.JDatePicker(now.getDayOfMonth()+1, now.getMonthValue(), now.getYear());
		DDPanel.add(dpDeparture);
		
		JButton btnCRA = new JButton("Check Room Availability");
		btnCRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CreateJDatePicker.Before(dpArrival, dpDeparture))
				{
					Connection con = DBConnection.connection();
					Statement st=null;
					boolean NoRoom=true;
					String sql = "SELECT *from roomdetail where RoomNo NOT IN "
							+ "(SELECT RoomNo from walkindb where ('"+CreateJDatePicker.getSelectedDate(dpArrival)+"' >= ArrivalDate "
							+ "AND '"+CreateJDatePicker.getSelectedDate(dpArrival)+"' <= DepartureDate) OR "
							+ "('"+CreateJDatePicker.getSelectedDate(dpDeparture)+"' >= ArrivalDate AND "
							+ "'"+CreateJDatePicker.getSelectedDate(dpDeparture)+"' <= DepartureDate) OR "
							+ "('"+CreateJDatePicker.getSelectedDate(dpArrival)+"' <= ArrivalDate AND "
							+ "'"+CreateJDatePicker.getSelectedDate(dpDeparture)+"' >= DepartureDate) UNION "
							+ "SELECT RoomNo from reservationdb where ('"+CreateJDatePicker.getSelectedDate(dpArrival)+"' >= ArrivalDate "
							+ "AND '"+CreateJDatePicker.getSelectedDate(dpArrival)+"' <= DepartureDate) OR "
							+ "('"+CreateJDatePicker.getSelectedDate(dpDeparture)+"' >= ArrivalDate AND "
							+ "'"+CreateJDatePicker.getSelectedDate(dpDeparture)+"' <= DepartureDate) OR "
							+ "('"+CreateJDatePicker.getSelectedDate(dpArrival)+"' <= ArrivalDate AND "
							+ "'"+CreateJDatePicker.getSelectedDate(dpDeparture)+"' >= DepartureDate))";
					ResultSet rs=null;
					int count=0;
					try {
						st = con.createStatement();
						rs = st.executeQuery(sql);
				
						for (int i = mtm.getRowCount() - 1; i > -1; i--) 
						{
							mtm.removeRow(i);
						}
						while(rs.next())
						{
							NoRoom = false;
							mtm.insertRow(count, new Object[]{false,rs.getInt("RoomNo"),rs.getString("Type"),rs.getString("FloorLoc"),rs.getFloat("Rent")});
							count++;
						}
						if(NoRoom)
						{
							HeadSPanel.infoBox("Sorry, Currently there is no room available", "Room Not Available", JOptionPane.ERROR_MESSAGE);
						}					
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
				{
					HeadSPanel.infoBox("The arrival date must comes before departure date", "Invalid Dates", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCRA.setBounds(444, 66, 186, 37);
		RoomDetailsPanel.add(btnCRA);
		
		JPanel PersonalDetailsPanel = new JPanel();
		PersonalDetailsPanel.setLayout(null);
		PersonalDetailsPanel.setBackground(new Color(255, 182, 193));
		PersonalDetailsPanel.setBounds(10, 227, 655, 230);
		panel.add(PersonalDetailsPanel);
		
		tfPersonalDetails = new JTextField();
		tfPersonalDetails.setText("    Personal Details");
		tfPersonalDetails.setForeground(Color.WHITE);
		tfPersonalDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		tfPersonalDetails.setEditable(false);
		tfPersonalDetails.setColumns(10);
		tfPersonalDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfPersonalDetails.setBackground(new Color(135, 206, 250));
		tfPersonalDetails.setBounds(10, 11, 635, 30);
		PersonalDetailsPanel.add(tfPersonalDetails);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFirstName.setBounds(20, 52, 109, 14);
		PersonalDetailsPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name*");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLastName.setBounds(210, 52, 109, 14);
		PersonalDetailsPanel.add(lblLastName);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBackground(new Color(255, 182, 193));
		tfFirstName.setBounds(20, 70, 150, 20);
		PersonalDetailsPanel.add(tfFirstName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setBackground(new Color(255, 182, 193));
		tfLastName.setBounds(210, 70, 150, 20);
		PersonalDetailsPanel.add(tfLastName);
		
		JLabel lblEmail = new JLabel("Email*");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmail.setBounds(405, 52, 109, 14);
		PersonalDetailsPanel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfEmail.setBackground(new Color(255, 182, 193));
		tfEmail.setBounds(405, 67, 240, 20);
		PersonalDetailsPanel.add(tfEmail);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Arial", Font.PLAIN, 12));
		lblGender.setBounds(405, 101, 109, 14);
		PersonalDetailsPanel.add(lblGender);
		
		JLabel lblAdult = new JLabel("Adult");
		lblAdult.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAdult.setBounds(20, 101, 109, 14);
		PersonalDetailsPanel.add(lblAdult);
		JTextField cfadult = HeadSPanel.BtnCounter(PersonalDetailsPanel, 20, 119);
		
		JLabel lblChild = new JLabel("Child");
		lblChild.setFont(new Font("Arial", Font.PLAIN, 12));
		lblChild.setBounds(210, 101, 109, 14);
		PersonalDetailsPanel.add(lblChild);
		JTextField cfchild = HeadSPanel.BtnCounter(PersonalDetailsPanel, 210, 119);
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setSelected(true);
		rbMale.setBackground(new Color(255, 182, 193));
		rbMale.setActionCommand("Male");
		rbMale.setBounds(405, 116, 57, 23);
		PersonalDetailsPanel.add(rbMale);
		bg.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setBackground(new Color(255, 182, 193));
		rbFemale.setActionCommand("Female");
		rbFemale.setBounds(493, 116, 78, 23);
		PersonalDetailsPanel.add(rbFemale);
		bg.add(rbFemale);
		
		JRadioButton rbOther = new JRadioButton("Other");
		rbOther.setBackground(new Color(255, 182, 193));
		rbOther.setActionCommand("Other");
		rbOther.setBounds(588, 116, 57, 23);
		PersonalDetailsPanel.add(rbOther);
		bg.add(rbOther);
		
		JButton btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/cancel.png")));
		btnCancel.setToolTipText("Cancel");
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCancel.setBounds(540, 175, 46, 44);
		PersonalDetailsPanel.add(btnCancel);
		
		JButton btnSubmit = new JButton("");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = DBConnection.connection();
				Statement st = null;
				
				boolean valid=true;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date tDay = new Date();
				try {
					System.out.println(CreateJDatePicker.getSelectedDate(dpArrival)+" "+sdf.format(tDay));
					if(getZeroTimeDate(sdf.parse(CreateJDatePicker.getSelectedDate(dpArrival))).compareTo(getZeroTimeDate(tDay))<0) {
						System.out.println( sdf.parse(CreateJDatePicker.getSelectedDate(dpArrival)).compareTo(tDay));
						valid=false;
						HeadSPanel.infoBox("Arrival Date can't be choosen before Today Date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
					}
					else if(tfFirstName.getText().equals(""))
					{
						valid=false;
						HeadSPanel.infoBox("First Name can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
					}
					else if(tfLastName.getText().equals(""))
					{
						valid=false;
						HeadSPanel.infoBox("Last Name can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
					}
					else if(!DBConnection.validateEmail(tfEmail.getText()))
					{
						valid=false;
						HeadSPanel.infoBox("Please Enter a valid Email", "Invalid Email", JOptionPane.ERROR_MESSAGE);
					}
					else if(!DBConnection.validateMobile(tfMobile.getText()))
					{
						valid=false;
						HeadSPanel.infoBox("Please Enter a valid Mobile Number", "Invalid Mobile Number", JOptionPane.ERROR_MESSAGE);
					}
					else if(tfEmail.getText().equals(""))
					{
						valid=false;
						HeadSPanel.infoBox("Email can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
					}
					else if(tfMobile.getText().equals(""))
					{
						valid=false;
						HeadSPanel.infoBox("Mobile Number can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
					}
					else if(valid)
					{
						for (int i = 0; i < AvailRoomtable.getRowCount(); i++) {
							Boolean isChecked = Boolean.valueOf(AvailRoomtable.getValueAt(i, 0).toString());
					     
							if (isChecked) {
								
								String sql = "insert into reservationdb(CustomerName,RoomNo, ArrivalDate, DepartureDate, Email, Gender, Mobile, Remark) value('"+tfFirstName.getText()+" "+tfLastName.getText()+"',"+AvailRoomtable.getValueAt(i, 1).toString()+",'"+CreateJDatePicker.getSelectedDate(dpArrival)+"','"+CreateJDatePicker.getSelectedDate(dpDeparture)+"','"+tfEmail.getText()+"','"+bg.getSelection().getActionCommand()+"','"+tfMobile.getText()+"','"+textArea.getText()+"')";
								System.out.println(sql);
								try {
					    			st = con.createStatement();
					    			st.executeUpdate(sql);
					    			HeadSPanel.infoBox("Room Booked Successfully", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
					    			dispose();
								} catch (SQLException a) {
									a.printStackTrace();
								} 				    	
							}
						}
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnSubmit.setToolTipText("Submit");
		btnSubmit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSubmit.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSubmit.setBounds(599, 175, 46, 44);
		PersonalDetailsPanel.add(btnSubmit);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRemark.setBounds(20, 146, 109, 14);
		PersonalDetailsPanel.add(lblRemark);
		
		textArea = new JTextArea();
		textArea.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textArea.setBackground(new Color(255, 182, 193));
		textArea.setBounds(20, 160, 299, 59);
		PersonalDetailsPanel.add(textArea);
		
		JLabel lblMobile = new JLabel("Mobile*");
		lblMobile.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMobile.setBounds(364, 160, 109, 14);
		PersonalDetailsPanel.add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setColumns(10);
		tfMobile.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfMobile.setBackground(new Color(255, 182, 193));
		tfMobile.setBounds(364, 178, 150, 20);
		PersonalDetailsPanel.add(tfMobile);
	}
	public static Date getZeroTimeDate(Date fecha) {
	    Date res = fecha;
	    Calendar calendar = Calendar.getInstance();

	    calendar.setTime( fecha );
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    res = calendar.getTime();

	    return res;
	}
}
