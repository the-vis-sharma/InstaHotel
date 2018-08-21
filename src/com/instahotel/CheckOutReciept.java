package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class CheckOutReciept extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtPrice;
	private JTextField txtDescription;
	private JTextField txtNoOfDays;
	private JTextField txtRentday;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField tfName;
	private JTextField tfArrivalDate;
	private JTextField tfDepartureDate;
	private JTextField tfInovanceDate;
	private JTextField tfInovanceNo;
	private JTextField tfRoomNo;
	private JTextField tfDiscountPer;
	private JTextField tfVatPer;
	private JTextField tfTotalAmount;
	private JTextField tfOtherCharges;
	private JTextField tfDiscountValue;
	private JTextField tfSubTotal;
	private JTextField tfVatValue;
	private JTextField tfTotal;
	private JLabel lblRoomcharges;
	private JTextField tfDays;
	private JTextField tfRentDay;
	private JTextField tfPrice;

	/**
	 * Create the frame.
	 */
	public CheckOutReciept(String CustomerName) {
		CheckOutReciept cor = this;
		setType(Type.UTILITY);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 50, 650, 699);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 50, 204));
		panel.setBounds(10, 11, 614, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIntahotel = new JLabel("InstaHotel");
		lblIntahotel.setForeground(new Color(230, 230, 250));
		lblIntahotel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblIntahotel.setBounds(194, 19, 163, 47);
		panel.add(lblIntahotel);
		
		JLabel lblPleasingPeopleThe = new JLabel("Pleasing People The World Over !!!");
		lblPleasingPeopleThe.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblPleasingPeopleThe.setForeground(new Color(230, 230, 250));
		lblPleasingPeopleThe.setBounds(194, 52, 207, 14);
		panel.add(lblPleasingPeopleThe);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(20, 122, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblArrivalDate = new JLabel("ARRIVAL DATE");
		lblArrivalDate.setBounds(20, 147, 102, 14);
		contentPane.add(lblArrivalDate);
		
		JLabel lblDepartureDate = new JLabel("DEPARTURE DATE");
		lblDepartureDate.setBounds(20, 172, 102, 14);
		contentPane.add(lblDepartureDate);
		
		JLabel lblRoomNo = new JLabel("ROOM NO.");
		lblRoomNo.setBounds(353, 172, 102, 14);
		contentPane.add(lblRoomNo);
		
		JLabel lblInovanceDate = new JLabel("INOVANCE DATE");
		lblInovanceDate.setBounds(353, 122, 102, 14);
		contentPane.add(lblInovanceDate);
		
		JLabel lblInovanceNo = new JLabel("INOVANCE NO.");
		lblInovanceNo.setBounds(353, 147, 102, 14);
		contentPane.add(lblInovanceNo);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtTitle.setBackground(new Color(233, 150, 122));
		txtTitle.setFont(new Font("Arial", Font.BOLD, 12));
		txtTitle.setText("     TITLE");
		txtTitle.setBounds(10, 208, 143, 20);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setFont(new Font("Arial", Font.BOLD, 12));
		txtPrice.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtPrice.setBackground(new Color(233, 150, 122));
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrice.setText("PRICE     ");
		txtPrice.setColumns(10);
		txtPrice.setBounds(540, 208, 84, 20);
		contentPane.add(txtPrice);
		
		txtDescription = new JTextField();
		txtDescription.setEditable(false);
		txtDescription.setFont(new Font("Arial", Font.BOLD, 12));
		txtDescription.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtDescription.setBackground(new Color(233, 150, 122));
		txtDescription.setText("     DESCRIPTION");
		txtDescription.setColumns(10);
		txtDescription.setBounds(152, 208, 248, 20);
		contentPane.add(txtDescription);
		
		txtNoOfDays = new JTextField();
		txtNoOfDays.setEditable(false);
		txtNoOfDays.setHorizontalAlignment(SwingConstants.LEFT);
		txtNoOfDays.setText(" DAYS");
		txtNoOfDays.setFont(new Font("Arial", Font.BOLD, 12));
		txtNoOfDays.setColumns(10);
		txtNoOfDays.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtNoOfDays.setBackground(new Color(233, 150, 122));
		txtNoOfDays.setBounds(397, 208, 75, 20);
		contentPane.add(txtNoOfDays);
		
		txtRentday = new JTextField();
		txtRentday.setEditable(false);
		txtRentday.setText(" RENT/DAY");
		txtRentday.setHorizontalAlignment(SwingConstants.LEFT);
		txtRentday.setFont(new Font("Arial", Font.BOLD, 12));
		txtRentday.setColumns(10);
		txtRentday.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtRentday.setBackground(new Color(233, 150, 122));
		txtRentday.setBounds(469, 208, 75, 20);
		contentPane.add(txtRentday);
		
		JLabel lblOtherCharges = new JLabel("OTHER CHARGES");
		lblOtherCharges.setBounds(20, 290, 102, 14);
		contentPane.add(lblOtherCharges);
		
		JLabel lblDiscount = new JLabel("DISCOUNT %");
		lblDiscount.setBounds(20, 346, 102, 14);
		contentPane.add(lblDiscount);
		
		JLabel lblSubTotal = new JLabel("SUB TOTAL");
		lblSubTotal.setBounds(20, 318, 102, 14);
		contentPane.add(lblSubTotal);
		
		JLabel lblVat = new JLabel("V.A.T %");
		lblVat.setBounds(20, 402, 102, 14);
		contentPane.add(lblVat);
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT");
		lblTotalAmount.setBounds(20, 427, 102, 14);
		contentPane.add(lblTotalAmount);
		
		textField = new JTextField();
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setBackground(new Color(233, 150, 122));
		textField.setEditable(false);
		textField.setBounds(10, 447, 614, 3);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCasher = new JLabel("CASHIER");
		lblCasher.setBounds(550, 500, 59, 14);
		contentPane.add(lblCasher);
		
		JLabel lblNewLabel = new JLabel("ISI-6, RIICO Institutional Area, Sitapura, Jaipur, Rajasthan 302022");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 586, 614, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNotePlease = new JLabel("Note : Please deposite your room key card.");
		lblNotePlease.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotePlease.setBounds(10, 561, 614, 14);
		contentPane.add(lblNotePlease);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_1.setBackground(new Color(233, 150, 122));
		textField_1.setBounds(10, 582, 614, 3);
		contentPane.add(textField_1);
		
		JLabel lblWeHaveRecieved = new JLabel("We have recieved cash payment for the inovance amount.");
		lblWeHaveRecieved.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeHaveRecieved.setBounds(10, 463, 614, 14);
		contentPane.add(lblWeHaveRecieved);
		
		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfName.setBackground(Color.WHITE);
		tfName.setBounds(151, 119, 192, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfArrivalDate = new JTextField();
		tfArrivalDate.setBackground(Color.WHITE);
		tfArrivalDate.setEditable(false);
		tfArrivalDate.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfArrivalDate.setColumns(10);
		tfArrivalDate.setBounds(152, 144, 192, 20);
		contentPane.add(tfArrivalDate);
		
		tfDepartureDate = new JTextField();
		tfDepartureDate.setBackground(Color.WHITE);
		tfDepartureDate.setEditable(false);
		tfDepartureDate.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfDepartureDate.setColumns(10);
		tfDepartureDate.setBounds(152, 169, 192, 20);
		contentPane.add(tfDepartureDate);
		
		tfInovanceDate = new JTextField();
		tfInovanceDate.setBackground(Color.WHITE);
		tfInovanceDate.setEditable(false);
		tfInovanceDate.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfInovanceDate.setColumns(10);
		tfInovanceDate.setBounds(460, 119, 164, 20);
		contentPane.add(tfInovanceDate);
		
		tfInovanceNo = new JTextField();
		tfInovanceNo.setBackground(Color.WHITE);
		tfInovanceNo.setEditable(false);
		tfInovanceNo.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfInovanceNo.setColumns(10);
		tfInovanceNo.setBounds(460, 144, 164, 20);
		contentPane.add(tfInovanceNo);
		
		tfRoomNo = new JTextField();
		tfRoomNo.setBackground(Color.WHITE);
		tfRoomNo.setEditable(false);
		tfRoomNo.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfRoomNo.setColumns(10);
		tfRoomNo.setBounds(460, 169, 164, 20);
		contentPane.add(tfRoomNo);
		
		tfDiscountPer = new JTextField();
		tfDiscountPer.setBackground(Color.WHITE);
		tfDiscountPer.setEditable(false);
		tfDiscountPer.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfDiscountPer.setColumns(10);
		tfDiscountPer.setBounds(152, 343, 102, 20);
		contentPane.add(tfDiscountPer);
		
		tfVatPer = new JTextField();
		tfVatPer.setBackground(Color.WHITE);
		tfVatPer.setEditable(false);
		tfVatPer.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfVatPer.setColumns(10);
		tfVatPer.setBounds(152, 399, 102, 20);
		contentPane.add(tfVatPer);
		
		tfTotalAmount = new JTextField();
		tfTotalAmount.setBackground(Color.WHITE);
		tfTotalAmount.setEditable(false);
		tfTotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalAmount.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.PINK));
		tfTotalAmount.setColumns(10);
		tfTotalAmount.setBounds(550, 421, 59, 20);
		contentPane.add(tfTotalAmount);
		
		tfOtherCharges = new JTextField();
		tfOtherCharges.setBackground(Color.WHITE);
		tfOtherCharges.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfOtherCharges.setEditable(false);
		tfOtherCharges.setHorizontalAlignment(SwingConstants.RIGHT);
		tfOtherCharges.setColumns(10);
		tfOtherCharges.setBounds(550, 287, 59, 20);
		contentPane.add(tfOtherCharges);
		
		tfDiscountValue = new JTextField();
		tfDiscountValue.setBackground(Color.WHITE);
		tfDiscountValue.setEditable(false);
		tfDiscountValue.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfDiscountValue.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDiscountValue.setColumns(10);
		tfDiscountValue.setBounds(550, 343, 59, 20);
		contentPane.add(tfDiscountValue);
		
		tfSubTotal = new JTextField();
		tfSubTotal.setBackground(Color.WHITE);
		tfSubTotal.setEditable(false);
		tfSubTotal.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfSubTotal.setColumns(10);
		tfSubTotal.setBounds(550, 315, 59, 20);
		contentPane.add(tfSubTotal);
		
		tfVatValue = new JTextField();
		tfVatValue.setBackground(Color.WHITE);
		tfVatValue.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfVatValue.setEditable(false);
		tfVatValue.setHorizontalAlignment(SwingConstants.RIGHT);
		tfVatValue.setColumns(10);
		tfVatValue.setBounds(550, 399, 59, 20);
		contentPane.add(tfVatValue);
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(20, 371, 102, 14);
		contentPane.add(lblTotal);
		
		tfTotal = new JTextField();
		tfTotal.setBackground(Color.WHITE);
		tfTotal.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfTotal.setEditable(false);
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotal.setColumns(10);
		tfTotal.setBounds(550, 371, 59, 20);
		contentPane.add(tfTotal);
		
		lblRoomcharges = new JLabel("Room Charges");
		lblRoomcharges.setBounds(20, 234, 102, 14);
		contentPane.add(lblRoomcharges);
		
		tfDays = new JTextField();
		tfDays.setBackground(Color.WHITE);
		tfDays.setEditable(false);
		tfDays.setHorizontalAlignment(SwingConstants.CENTER);
		tfDays.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfDays.setColumns(10);
		tfDays.setBounds(396, 231, 59, 20);
		contentPane.add(tfDays);
		
		tfRentDay = new JTextField();
		tfRentDay.setBackground(Color.WHITE);
		tfRentDay.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfRentDay.setEditable(false);
		tfRentDay.setHorizontalAlignment(SwingConstants.CENTER);
		tfRentDay.setColumns(10);
		tfRentDay.setBounds(469, 231, 59, 20);
		contentPane.add(tfRentDay);
		
		tfPrice = new JTextField();
		tfPrice.setBackground(Color.WHITE);
		tfPrice.setEditable(false);
		tfPrice.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPrice.setColumns(10);
		tfPrice.setBounds(550, 231, 59, 20);
		contentPane.add(tfPrice);
		
		Connection con = DBConnection.connection();
		Statement st = null;
		String sql = "SELECT * FROM walkindb LEFT JOIN roomdetail ON walkindb.RoomNo=roomdetail.RoomNo WHERE walkindb.CustomerName='"+CustomerName+"'";
		ResultSet rs = null;
		
		LocalDate now = LocalDate.now();
		String today = ""+now.getDayOfMonth()+" "+now.getMonth()+", "+now.getYear();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			rs.next();
			tfName.setText(rs.getString("CustomerName"));
			tfArrivalDate.setText(rs.getString("ArrivalDate"));
			tfDepartureDate.setText(rs.getString("DepartureDate"));
			tfInovanceDate.setText(today);
			tfRoomNo.setText(""+rs.getInt("RoomNo"));
			tfDays.setText(""+rs.getInt("NoOfDays"));
			tfRentDay.setText(""+rs.getFloat("Rent"));
			float price = rs.getInt("NoOfDays")*rs.getFloat("Rent");
			tfPrice.setText(""+price);
			tfOtherCharges.setText(""+rs.getInt("OtherCharges"));
			tfSubTotal.setText(""+rs.getFloat("SubTotal"));
			tfDiscountPer.setText(""+rs.getInt("Discount"));
			float dis = rs.getInt("Discount")*rs.getFloat("SubTotal")/100;
			tfDiscountValue.setText("-"+dis);
			tfTotal.setText(""+rs.getFloat("Total"));
			tfVatPer.setText(""+rs.getInt("VAT"));
			float vat = rs.getInt("VAT")*rs.getFloat("Total")/100;
			tfVatValue.setText("+"+vat);
			tfTotalAmount.setText(""+rs.getFloat("AmtPaid"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closeResource(con, st);
		}
		
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PageFormat preformat = pjob.defaultPage();
		preformat.setOrientation(PageFormat.LANDSCAPE);
		PageFormat postformat = pjob.pageDialog(preformat);
		//If user does not hit cancel then print.
		if (preformat != postformat) {
		    //Set print component
		    pjob.setPrintable(new Printer(cor), postformat);
		    if (pjob.printDialog()) {
		        try {
					pjob.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
		    }
		}
	}
}
