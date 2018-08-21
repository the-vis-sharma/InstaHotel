package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.SwingConstants;

public class Reports extends JFrame {

	private JPanel contentPane;
	int occupied,vacant,reserverd,CNA;
	int tTCIC, tCNC, tTCOC; float tTIn;
	int mTCIC, mCNC, mTCOC; float mTIn;
	int yTCIC, yCNC, yTCOC; float yTIn;
	Connection con;
	Statement st;
	ResultSet rs;

	/**
	 * Create the frame.
	 */
	public Reports() {
		setType(Type.UTILITY);
		setVisible(true);
		setTitle("InstaHotel - Reports");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(106, 90, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//--------------------Database-------------------------------//
		con = DBConnection.connection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from reservationdb");
			reserverd = Length(rs);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			rs = st.executeQuery("select * from walkindb where ArrivalDate <= '"+sdf.format(today)+"' AND DepartureDate > '"+sdf.format(today)+"'");
			occupied = Length(rs);
			rs = st.executeQuery("select * from reservationdb where ArrivalDate='"+sdf.format(today)+"'");
			CNA = Length(rs);
			rs = st.executeQuery("select * from roomdetail");
			vacant = Length(rs) - occupied;
			
			rs = st.executeQuery("select * from walkindb where ArrivalDate='"+sdf.format(today)+"'");
			tTCIC = Length(rs);
			tCNC = mCNC = yCNC = occupied;
			rs = st.executeQuery("select * from walkindb where DepartureDate='"+sdf.format(today)+"'");
			tTCOC = Length(rs);
			rs.beforeFirst();
			while(rs.next()) {
				tTIn += rs.getFloat("AmtPaid");
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
			rs = st.executeQuery("select * from walkindb where DATE_FORMAT(ArrivalDate, \"%Y-%m\")='"+sdf1.format(today)+"'");
			mTCIC = Length(rs);
			rs = st.executeQuery("select * from walkindb where DATE_FORMAT(DepartureDate, \"%Y-%m\")='"+sdf1.format(today)+"'");
			mTCOC = Length(rs);
			rs.beforeFirst();
			while(rs.next()) {
				mTIn += rs.getFloat("AmtPaid");
			}
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
			rs = st.executeQuery("select * from walkindb where DATE_FORMAT(ArrivalDate, \"%Y\")='"+sdf2.format(today)+"'");
			yTCIC = Length(rs);
			rs = st.executeQuery("select * from walkindb where DATE_FORMAT(DepartureDate, \"%Y\")='"+sdf2.format(today)+"'");
			yTCOC = Length(rs);
			rs.beforeFirst();
			while(rs.next()) {
				yTIn += rs.getFloat("AmtPaid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//-----------------------------------------------------------//
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 664, 439);
		contentPane.add(tabbedPane);
		
		//For PieChart
		DefaultPieDataset ds = new DefaultPieDataset();
		ds.setValue("Occupied", occupied);
		ds.setValue("Vacant", vacant);
		ds.setValue("Reserved", reserverd);
		ds.setValue("Customer Not Arrived", CNA);
		JFreeChart chart = ChartFactory.createPieChart3D("Room Status", ds, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setForegroundAlpha(0.60f);
		plot.setDepthFactor(0.3);
		JPanel panel = new ChartPanel(chart);
		tabbedPane.addTab("Rooms Status", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Today's Reports", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblTotalCheckedinCustomers = new JLabel("Total Checked-In Customers :");
		lblTotalCheckedinCustomers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCheckedinCustomers.setBounds(108, 150, 220, 28);
		panel_1.add(lblTotalCheckedinCustomers);
		
		JLabel lblTotalNoOf = new JLabel("Current No. of Customers :");
		lblTotalNoOf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalNoOf.setBounds(108, 189, 220, 28);
		panel_1.add(lblTotalNoOf);
		
		JLabel lblTotalCheckedoutCustomers = new JLabel("Total Checked-Out Customers :");
		lblTotalCheckedoutCustomers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCheckedoutCustomers.setBounds(108, 228, 220, 28);
		panel_1.add(lblTotalCheckedoutCustomers);
		
		JLabel lblTotalIncome = new JLabel("Total Income :");
		lblTotalIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalIncome.setBounds(108, 267, 220, 28);
		panel_1.add(lblTotalIncome);
		
		JLabel lbltTCIC = new JLabel(""+tTCIC);
		lbltTCIC.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltTCIC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbltTCIC.setBounds(433, 150, 98, 28);
		panel_1.add(lbltTCIC);
		
		JLabel lbltCNC = new JLabel(""+tCNC);
		lbltCNC.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltCNC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbltCNC.setBounds(433, 189, 98, 28);
		panel_1.add(lbltCNC);
		
		JLabel lbltTCOC = new JLabel(""+tTCOC);
		lbltTCOC.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltTCOC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbltTCOC.setBounds(433, 228, 98, 28);
		panel_1.add(lbltTCOC);
		
		JLabel lbltTIn = new JLabel(""+tTIn);
		lbltTIn.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltTIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lbltTIn.setBounds(433, 267, 98, 28);
		panel_1.add(lbltTIn);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(153, 50, 204));
		panel_4.setBounds(10, 11, 639, 100);
		panel_1.add(panel_4);
		
		JLabel label_3 = new JLabel("InstaHotel");
		label_3.setForeground(new Color(230, 230, 250));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label_3.setBounds(194, 19, 163, 47);
		panel_4.add(label_3);
		
		JLabel label_4 = new JLabel("Pleasing People The World Over !!!");
		label_4.setForeground(new Color(230, 230, 250));
		label_4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(194, 52, 207, 14);
		panel_4.add(label_4);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date tDate = new Date();
		JLabel lblNewLabel = new JLabel("Date: "+sdf.format(tDate));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(441, 11, 188, 28);
		panel_4.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblDailyReport = new JLabel("Daily Report");
		lblDailyReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblDailyReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDailyReport.setBounds(10, 122, 639, 28);
		panel_1.add(lblDailyReport);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Monthly Reports", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(153, 50, 204));
		panel_5.setBounds(10, 11, 639, 100);
		panel_2.add(panel_5);
		
		JLabel label_5 = new JLabel("InstaHotel");
		label_5.setForeground(new Color(230, 230, 250));
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label_5.setBounds(194, 19, 163, 47);
		panel_5.add(label_5);
		
		JLabel label_6 = new JLabel("Pleasing People The World Over !!!");
		label_6.setForeground(new Color(230, 230, 250));
		label_6.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		label_6.setBounds(194, 52, 207, 14);
		panel_5.add(label_6);
		
		JLabel label_7 = new JLabel("Date: "+sdf.format(tDate));
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_7.setBounds(441, 11, 188, 28);
		panel_5.add(label_7);
		
		JLabel lblMonthlyReport = new JLabel("Monthly Report");
		lblMonthlyReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonthlyReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMonthlyReport.setBounds(10, 122, 639, 28);
		panel_2.add(lblMonthlyReport);
		
		JLabel label_9 = new JLabel("Total Checked-In Customers :");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_9.setBounds(108, 150, 220, 28);
		panel_2.add(label_9);
		
		JLabel label_10 = new JLabel("Current No. of Customers :");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_10.setBounds(108, 189, 220, 28);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("Total Checked-Out Customers :");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_11.setBounds(108, 228, 220, 28);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("Total Income :");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_12.setBounds(108, 267, 220, 28);
		panel_2.add(label_12);
		
		JLabel lblmTCIC = new JLabel(""+mTCIC);
		lblmTCIC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmTCIC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblmTCIC.setBounds(433, 150, 98, 28);
		panel_2.add(lblmTCIC);
		
		JLabel lblmCNC = new JLabel(""+mCNC);
		lblmCNC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmCNC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblmCNC.setBounds(433, 189, 98, 28);
		panel_2.add(lblmCNC);
		
		JLabel lblmTCOC = new JLabel(""+mTCOC);
		lblmTCOC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmTCOC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblmTCOC.setBounds(433, 228, 98, 28);
		panel_2.add(lblmTCOC);
		
		JLabel lblmTIn = new JLabel(""+mTIn);
		lblmTIn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmTIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblmTIn.setBounds(433, 267, 98, 28);
		panel_2.add(lblmTIn);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Yearly Reports", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(153, 50, 204));
		panel_6.setBounds(10, 11, 639, 100);
		panel_3.add(panel_6);
		
		JLabel label_8 = new JLabel("InstaHotel");
		label_8.setForeground(new Color(230, 230, 250));
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label_8.setBounds(194, 19, 163, 47);
		panel_6.add(label_8);
		
		JLabel label_17 = new JLabel("Pleasing People The World Over !!!");
		label_17.setForeground(new Color(230, 230, 250));
		label_17.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		label_17.setBounds(194, 52, 207, 14);
		panel_6.add(label_17);
	
		JLabel label_18 = new JLabel("Date: "+sdf.format(tDate));
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setForeground(Color.WHITE);
		label_18.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_18.setBounds(441, 11, 188, 28);
		panel_6.add(label_18);
		
		JLabel lblYearlyReport = new JLabel("Yearly Report");
		lblYearlyReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblYearlyReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYearlyReport.setBounds(10, 122, 639, 28);
		panel_3.add(lblYearlyReport);
		
		JLabel label_20 = new JLabel("Total Checked-In Customers :");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_20.setBounds(108, 150, 220, 28);
		panel_3.add(label_20);
		
		JLabel label_21 = new JLabel("Current No. of Customers :");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_21.setBounds(108, 189, 220, 28);
		panel_3.add(label_21);
		
		JLabel label_22 = new JLabel("Total Checked-Out Customers :");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_22.setBounds(108, 228, 220, 28);
		panel_3.add(label_22);
		
		JLabel label_23 = new JLabel("Total Income :");
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_23.setBounds(108, 267, 220, 28);
		panel_3.add(label_23);
		
		JLabel lblyTCIC = new JLabel(""+yTCIC);
		lblyTCIC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyTCIC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblyTCIC.setBounds(433, 150, 98, 28);
		panel_3.add(lblyTCIC);
		
		JLabel lblyCNC = new JLabel(""+yCNC);
		lblyCNC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyCNC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblyCNC.setBounds(433, 189, 98, 28);
		panel_3.add(lblyCNC);
		
		JLabel lblyTCOC = new JLabel(""+yTCOC);
		lblyTCOC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyTCOC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblyTCOC.setBounds(433, 228, 98, 28);
		panel_3.add(lblyTCOC);
		
		JLabel lblyTIn = new JLabel(""+yTIn);
		lblyTIn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblyTIn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblyTIn.setBounds(433, 267, 98, 28);
		panel_3.add(lblyTIn);
	}
	public int Length(ResultSet rs) throws SQLException {
		int size=0;
		if(rs!=null) {
			rs.beforeFirst();
			rs.last();
			size=rs.getRow();
		}
		return size;
	}
}
