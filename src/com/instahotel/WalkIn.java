package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class WalkIn extends JFrame {

	private JPanel contentPane;
	private JTextField txtRoomDetails;
	private JTextField txtPersonalDetails;
	/**
	 * Create the frame.
	 */
	private JTable AvailRoomtable;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextField tfValue;
	private JTextField txtContactDetails;
	private JTextField tfCity;
	private JTextField tfPin;
	private JTextField tfMobile;
	private JTextField txtOtherInformations;
	private JTextField tfIDNumber;
	private JTextField tfVehicleNuber;
	public WalkIn() {
		setType(Type.UTILITY);
		setVisible(true);
		setTitle("InstaHotel - Walk In Reservations");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 0, 691, 721);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(147, 112, 219));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel RoomDetailsPanel = new JPanel();
		RoomDetailsPanel.setBackground(new Color(255, 182, 193));
		RoomDetailsPanel.setBounds(10, 11, 655, 113);
		contentPane.add(RoomDetailsPanel);
		RoomDetailsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 655, 104);
		contentPane.add(scrollPane);
		
		MyTableModel mtm = new MyTableModel(new String[]{"Select", "Room No", "Type", "Floor Location", "Rent"});
		AvailRoomtable = new JTable(mtm);
		AvailRoomtable.setSelectionBackground(Color.PINK);
		AvailRoomtable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(AvailRoomtable);
		
		txtRoomDetails = new JTextField();
		txtRoomDetails.setForeground(new Color(255, 255, 255));
		txtRoomDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtRoomDetails.setEditable(false);
		txtRoomDetails.setBackground(new Color(135, 206, 250));
		txtRoomDetails.setText("    Room Details");
		txtRoomDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtRoomDetails.setBounds(10, 11, 635, 30);
		RoomDetailsPanel.add(txtRoomDetails);
		txtRoomDetails.setColumns(10);
		
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
		ADPanel.setBackground(new Color(240, 240, 240));
		ADPanel.setBounds(20, 66, 120, 37);
		RoomDetailsPanel.add(ADPanel);
		ADPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		LocalDate now = LocalDate.now();
		JDatePickerImpl dpArrival = CreateJDatePicker.JDatePicker(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
		ADPanel.add(dpArrival);
		
		JPanel DDPanel = new JPanel();
		DDPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		DDPanel.setBackground(new Color(240, 240, 240));
		DDPanel.setBounds(237, 66, 120, 37);
		RoomDetailsPanel.add(DDPanel);
		JDatePickerImpl dpDeparture = CreateJDatePicker.JDatePicker(now.getDayOfMonth()+1, now.getMonthValue(), now.getYear());
		DDPanel.add(dpDeparture);
		
		DDPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		PersonalDetailsPanel.setBounds(10, 227, 655, 150);
		contentPane.add(PersonalDetailsPanel);
		
		
		
		txtPersonalDetails = new JTextField();
		txtPersonalDetails.setText("    Personal Details");
		txtPersonalDetails.setForeground(Color.WHITE);
		txtPersonalDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtPersonalDetails.setEditable(false);
		txtPersonalDetails.setColumns(10);
		txtPersonalDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtPersonalDetails.setBackground(new Color(135, 206, 250));
		txtPersonalDetails.setBounds(10, 11, 635, 30);
		PersonalDetailsPanel.add(txtPersonalDetails);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFirstName.setBounds(20, 52, 109, 14);
		PersonalDetailsPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name*");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLastName.setBounds(210, 52, 109, 14);
		PersonalDetailsPanel.add(lblLastName);
		
		tfFirstName = new JTextField();
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBackground(new Color(255, 182, 193));
		tfFirstName.setBounds(20, 70, 150, 20);
		PersonalDetailsPanel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setBackground(new Color(255, 182, 193));
		tfLastName.setBounds(210, 70, 150, 20);
		PersonalDetailsPanel.add(tfLastName);
		
		JLabel lblEmail = new JLabel("Email");
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
		lblGender.setBounds(405, 94, 109, 14);
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
		rbMale.setBounds(405, 116, 57, 23);
		rbMale.setActionCommand("Male");
		PersonalDetailsPanel.add(rbMale);
		rbMale.setBackground(new Color(255, 182, 193));
		rbMale.setSelected(true);
		bg.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setBounds(493, 116, 65, 23);
		rbFemale.setActionCommand("Female");
		PersonalDetailsPanel.add(rbFemale);
		rbFemale.setBackground(new Color(255, 182, 193));
		bg.add(rbFemale);
		
		JRadioButton rbOther = new JRadioButton("Other");
		rbOther.setBounds(588, 116, 57, 23);
		rbOther.setActionCommand("Other");
		PersonalDetailsPanel.add(rbOther);
		rbOther.setBackground(new Color(255, 182, 193));
		bg.add(rbOther);			
		
		JPanel ContactDetailsPanel = new JPanel();
		ContactDetailsPanel.setLayout(null);
		ContactDetailsPanel.setBackground(new Color(255, 182, 193));
		ContactDetailsPanel.setBounds(10, 377, 655, 150);
		contentPane.add(ContactDetailsPanel);
		
		txtContactDetails = new JTextField();
		txtContactDetails.setText("    Contact Details");
		txtContactDetails.setForeground(Color.WHITE);
		txtContactDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtContactDetails.setEditable(false);
		txtContactDetails.setColumns(10);
		txtContactDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtContactDetails.setBackground(new Color(135, 206, 250));
		txtContactDetails.setBounds(10, 11, 635, 30);
		ContactDetailsPanel.add(txtContactDetails);
		
		JLabel lblCity = new JLabel("City*");
		lblCity.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCity.setBounds(305, 52, 109, 14);
		ContactDetailsPanel.add(lblCity);
		
		JLabel lblState = new JLabel("State*");
		lblState.setFont(new Font("Arial", Font.PLAIN, 12));
		lblState.setBounds(495, 52, 109, 14);
		ContactDetailsPanel.add(lblState);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfCity.setBackground(new Color(255, 182, 193));
		tfCity.setBounds(305, 70, 150, 20);
		ContactDetailsPanel.add(tfCity);
		
		tfPin = new JTextField();
		tfPin.setColumns(10);
		tfPin.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfPin.setBackground(new Color(255, 182, 193));
		tfPin.setBounds(305, 119, 150, 20);
		ContactDetailsPanel.add(tfPin);
		
		JLabel lblAddress = new JLabel("Address*");
		lblAddress.setBounds(20, 52, 109, 14);
		ContactDetailsPanel.add(lblAddress);
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JTextArea taAddress = new JTextArea();
		taAddress.setBounds(20, 67, 240, 72);
		ContactDetailsPanel.add(taAddress);
		taAddress.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		taAddress.setBackground(new Color(255, 182, 193));
		
		JLabel lblPin = new JLabel("Pin");
		lblPin.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPin.setBounds(305, 101, 109, 14);
		ContactDetailsPanel.add(lblPin);
		
		JLabel lblMobile = new JLabel("Mobile*");
		lblMobile.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMobile.setBounds(495, 101, 109, 14);
		ContactDetailsPanel.add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setColumns(10);
		tfMobile.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfMobile.setBackground(new Color(255, 182, 193));
		tfMobile.setBounds(495, 119, 150, 20);
		ContactDetailsPanel.add(tfMobile);
		
		JComboBox cbState = new JComboBox();
		cbState.setModel(new DefaultComboBoxModel(new String[] {"Arunachal Pradesh ", "Andhra Pradesh ", "Assam ", "Bihar ", "Chhattisgarh ", "Goa ", "Gujarat ", "Haryana ", "Himachal Pradesh ", "Jammu and Kashmir ", "Jharkhand ", "Karnataka ", "Kerala ", "Madhya Pradesh ", "Maharashtra ", "Manipur ", "Meghalaya ", "Mizoram ", "Nagaland ", "Odisha ", "Punjab ", "Rajasthan ", "Sikkim ", "Tamil ", "Nadu ", "Telangana ", "Tripura ", "Uttar Pradesh ", "Uttarakhand ", "West Bengal"}));
		cbState.setBounds(495, 69, 150, 20);
		ContactDetailsPanel.add(cbState);
		
		JPanel OtherDetailsPanel = new JPanel();
		OtherDetailsPanel.setLayout(null);
		OtherDetailsPanel.setBackground(new Color(255, 182, 193));
		OtherDetailsPanel.setBounds(10, 527, 655, 148);
		contentPane.add(OtherDetailsPanel);
		
		txtOtherInformations = new JTextField();
		txtOtherInformations.setText("    Other Informations");
		txtOtherInformations.setForeground(Color.WHITE);
		txtOtherInformations.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtOtherInformations.setEditable(false);
		txtOtherInformations.setColumns(10);
		txtOtherInformations.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtOtherInformations.setBackground(new Color(135, 206, 250));
		txtOtherInformations.setBounds(10, 11, 635, 30);
		OtherDetailsPanel.add(txtOtherInformations);
		
		JLabel lblIdNumber = new JLabel("ID Number*");
		lblIdNumber.setFont(new Font("Arial", Font.PLAIN, 12));
		lblIdNumber.setBounds(495, 51, 109, 14);
		OtherDetailsPanel.add(lblIdNumber);
		
		JLabel lblVehicleType = new JLabel("Vehicle Type");
		lblVehicleType.setFont(new Font("Arial", Font.PLAIN, 12));
		lblVehicleType.setBounds(19, 100, 109, 14);
		OtherDetailsPanel.add(lblVehicleType);
		
		tfIDNumber = new JTextField();
		tfIDNumber.setColumns(10);
		tfIDNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfIDNumber.setBackground(new Color(255, 182, 193));
		tfIDNumber.setBounds(495, 69, 150, 20);
		OtherDetailsPanel.add(tfIDNumber);
		
		JLabel lblIdType = new JLabel("ID Type*");
		lblIdType.setFont(new Font("Arial", Font.PLAIN, 12));
		lblIdType.setBounds(20, 52, 109, 14);
		OtherDetailsPanel.add(lblIdType);
		
		JLabel lblVehicleNumber = new JLabel("Vehicle Number");
		lblVehicleNumber.setFont(new Font("Arial", Font.PLAIN, 12));
		lblVehicleNumber.setBounds(176, 100, 109, 14);
		OtherDetailsPanel.add(lblVehicleNumber);
		
		tfVehicleNuber = new JTextField();
		tfVehicleNuber.setColumns(10);
		tfVehicleNuber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfVehicleNuber.setBackground(new Color(255, 182, 193));
		tfVehicleNuber.setBounds(179, 118, 150, 20);
		OtherDetailsPanel.add(tfVehicleNuber);
		
		JComboBox cbIDType = new JComboBox();
		cbIDType.setModel(new DefaultComboBoxModel(new String[] {"Adhaar(UID)", "Passport", "Driving License ", "Election Commission ID Card ", "Ration Card with Photo, for the person whose photo is affixed ", "Income Tax PAN Card ", "Photo Credit Card ", "Smart card issued by CSD, Defence /Parliamentary", "Current  passbook of Post Office / any scheduled Bank, having Photo ", "Photo Identity Card (of Central Govt. /PSU or State Govt. /PSU only)", "Photo Identity Card issued by Govt. recognize Educational Institutions", "Cast and Domicile Certificate with photo  issued by State Govt.", "Pensioner Card having photo", "Freedom Fighter  Card having photo", "Kisan Passbook having photo", "Income Tax PAN Card "}));
		cbIDType.setBounds(20, 69, 438, 20);
		OtherDetailsPanel.add(cbIDType);
		
		JComboBox cbVehicleType = new JComboBox();
		cbVehicleType.setModel(new DefaultComboBoxModel(new String[] {"Two Wheeler", "Four Wheeler", "Other"}));
		cbVehicleType.setBounds(19, 117, 150, 20);
		OtherDetailsPanel.add(cbVehicleType);
		
		JButton btnSubmit = new JButton("");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = DBConnection.connection();
				Statement st = null;
				
				boolean valid=true;
				
				if(tfFirstName.getText().equals(""))
				{
					valid=false;
					HeadSPanel.infoBox("First Name can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfLastName.getText().equals(""))
				{
					valid=false;
					HeadSPanel.infoBox("Last Name can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(taAddress.getText().equals(""))
				{
					valid=false;
					HeadSPanel.infoBox("Address field can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfCity.getText().equals(""))
				{
					valid=false;
					HeadSPanel.infoBox("City can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfMobile.getText().equals(""))
				{
					valid=false;
					HeadSPanel.infoBox("Mobile No. can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfIDNumber.getText().equals(""))
				{
					valid=false;
					HeadSPanel.infoBox("ID Number can't be blank", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(!DBConnection.validateAlpha(tfCity.getText()))
				{
					valid=false;
					HeadSPanel.infoBox("Please Enter a valid City name", "Invalid City", JOptionPane.ERROR_MESSAGE);
				}
				else if(!DBConnection.validateDigit(tfPin.getText()))
				{
					valid=false;
					HeadSPanel.infoBox("Please Enter a valid Pin number", "Invalid Pin", JOptionPane.ERROR_MESSAGE);
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
				else if(valid)
				{
					boolean noRoomSelected = false;
					for (int i = 0; i < AvailRoomtable.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(AvailRoomtable.getValueAt(i, 0).toString());
				     
						if (isChecked) {
							String sql = "insert into walkindb(CustomerName,RoomNo, ArrivalDate, DepartureDate, NoOFDays, OtherCharges, Discount, VAT, AmtPaid, Total, SubTotal) value('"+tfFirstName.getText()+" "+tfLastName.getText()+"',"+AvailRoomtable.getValueAt(i, 1).toString()+",'"+CreateJDatePicker.getSelectedDate(dpArrival)+"','"+CreateJDatePicker.getSelectedDate(dpDeparture)+"',"+0+","+0.0+","+0+","+0+","+0.0+","+0.0+","+0.0+")";
							String sql1 = "replace into CustomerDB (CustomerName,Email,Gender,Address,City,State,Pin,Mobile,IDType,IDNumber,VehicleType,VehicleNumber) value('"+tfFirstName.getText()+" "+tfLastName.getText()+"','"+tfEmail.getText()+"','"+bg.getSelection().getActionCommand()+"','"+taAddress.getText()+"','"+tfCity.getText()+"','"+cbState.getSelectedItem()+"','"+tfPin.getText()+"','"+tfMobile.getText()+"','"+cbIDType.getSelectedItem()+"','"+tfIDNumber.getText()+"','"+cbVehicleType.getSelectedItem()+"','"+tfVehicleNuber.getText()+"')";
				    		try {
				    			st = con.createStatement();
				    			st.executeUpdate(sql);
				    			st.executeUpdate(sql1);
				    			HeadSPanel.infoBox("Room Booked Successfully", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
				    			dispose();
							} catch (SQLException e) {
								e.printStackTrace();
							}			    	
						}
						else {
			    			noRoomSelected = true;
			    		}
					}
					if(noRoomSelected) {
						HeadSPanel.infoBox("Please Choose at least one room.", "Choose Room", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSubmit.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSubmit.setBounds(599, 101, 46, 44);
		OtherDetailsPanel.add(btnSubmit);
		btnSubmit.setToolTipText("Submit");
		btnSubmit.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnSubmit.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JButton btnCancel = new JButton("");
		btnCancel.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(540, 101, 46, 44);
		OtherDetailsPanel.add(btnCancel);
		btnCancel.setToolTipText("Cancel");
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/cancel.png")));
		
	}
}
