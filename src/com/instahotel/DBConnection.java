package com.instahotel;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DBConnection 
{
	public static Connection connection()
	{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/instahotel";
		String user = "root";
		String pass = "root";
		Connection con=null;
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		return con;
	}
	public static void closeResource(Connection con, Statement st)
	{
		try
		{
			if(st!=null)
			{
				st.close();
				st=null;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		try
		{
			if(con!=null)
			{
				con.close();
				con=null;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public static final Pattern VALID_USER_NAME_REGEX = 
		    Pattern.compile("^[A-Za-z_][A-Za-z0-9_]{7,29}$", Pattern.CASE_INSENSITIVE);

		public static boolean validateUserName(String UNStr) {
		        Matcher matcher = VALID_USER_NAME_REGEX .matcher(UNStr);
		        return matcher.find();
		}
		
		public static final Pattern VALID_PASSWORD_REGEX = 
			    Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", Pattern.CASE_INSENSITIVE);

			public static boolean validatePass(String PassStr) {
			        Matcher matcher = VALID_PASSWORD_REGEX .matcher(PassStr);
			        return matcher.find();
			}
		
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public static boolean validateEmail(String emailStr) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		        return matcher.find();
		}
	
		
		public static final Pattern VALID_MOBILE_NO_REGEX = 
			    Pattern.compile("^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{10})$", Pattern.CASE_INSENSITIVE);

			public static boolean validateMobile(String mobileStr) {
			        Matcher matcher = VALID_MOBILE_NO_REGEX .matcher(mobileStr);
			        return matcher.find();
			}
			
			public static final Pattern VALID_ALPHA_VALUE_REGEX = 
				    Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

				public static boolean validateAlpha(String str) {
				        Matcher matcher = VALID_ALPHA_VALUE_REGEX .matcher(str);
				        return matcher.find();
				}	
				
				public static final Pattern VALID_DIGIT_VALUE_REGEX = 
					    Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);

					public static boolean validateDigit(String str) {
					        Matcher matcher = VALID_DIGIT_VALUE_REGEX .matcher(str);
					        return matcher.find();
					}	
			
		public static void createDB()
		{
			byte[] dp=null;
			ImageIcon ii = new ImageIcon(Home.class.getResource("/com/img/defaultAvtar.png"));
			BufferedImage bi = new BufferedImage(220, 220, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bi.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(ii.getImage(), 0, 0, 220, 220, null);
			//ii.paintIcon(null, g, 0, 0);
			g.dispose();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ImageIO.write(bi, "jpg", baos);
				baos.flush();
				dp = baos.toByteArray();
				baos.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			String jdbcDriver = "com.mysql.jdbc.Driver";
			String CDB = "CREATE DATABASE IF NOT EXISTS instahotel";
			String SelectDB = "USE instahotel";
			String CTuldetail = "CREATE TABLE IF NOT EXISTS uldetail (UID INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY NOT NULL,Name VARCHAR(150) NOT NULL, UserName VARCHAR(30) NOT NULL, Pass VARCHAR(30) NOT NULL, Type INT NOT NULL, Question1 INT, Answer1 VARCHAR(50), Question2 INT, Answer2 VARCHAR(50), dp longblob, Email varchar(100), Mobile varchar(10), Gender varchar(20))";
			String adduser = "INSERT INTO uldetail (Name, UserName, Pass, Type, Question1, Answer1, Question2, Answer2, dp, Email, Mobile, Gender) SELECT 'Admin','admin','admin', 0, 1, 'Great', 5, 'Pta ni', (?), '0','0','0' FROM DUAL WHERE NOT EXISTS (SELECT * FROM uldetail);";
			String CTroomdetail = "CREATE TABLE IF NOT EXISTS roomdetail (RoomNo INT UNSIGNED PRIMARY KEY NOT NULL, Type VARCHAR(50) NOT NULL, FloorLoc VARCHAR(30) NOT NULL, Rent FLOAT UNSIGNED NOT NULL)";
			String CTwalkindb = "CREATE TABLE IF NOT EXISTS walkindb (ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL, CustomerName VARCHAR(50) NOT NULL, RoomNo INT UNSIGNED NOT NULL, ArrivalDate DATE NOT NULL, DepartureDate DATE NOT NULL, NoOfDays INT, OtherCharges FLOAT, Discount INT, VAT INT, AmtPaid FLOAT, Total FLOAT, SubTotal FLOAT)";
			String CTreservationdb = "CREATE TABLE IF NOT EXISTS reservationdb (ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL, CustomerName VARCHAR(50) NOT NULL, RoomNo INT UNSIGNED NOT NULL, ArrivalDate DATE NOT NULL, DepartureDate DATE NOT NULL, NoOfDays INT, OtherCharges FLOAT, Discount INT, VAT INT, AmtPaid FLOAT, Total FLOAT, SubTotal FLOAT)";
			String CTphonebookdb = "CREATE TABLE IF NOT EXISTS phonebookdb (Name VARCHAR(100) PRIMARY KEY NOT NULL, Number DECIMAL(15,0) NOT NULL, Position VARCHAR(50))";
			String CTfeedbackdb = "CREATE TABLE IF NOT EXISTS feedbackdb (ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL, Name VARCHAR(50) NOT NULL, Email VARCHAR(150) NOT NULL, Mobile DECIMAL(15,0) NOT NULL, Message VARCHAR(500) NOT NULL)";
			String CTcustomerdb = "CREATE TABLE IF NOT EXISTS customerdb (CustomerName VARCHAR(50) PRIMARY KEY NOT NULL, Email VARCHAR(150), Gender VARCHAR(10) NOT NULL, Address VARCHAR(200) NOT NULL, City VARCHAR(100) NOT NULL, State VARCHAR(150) NOT NULL, Pin DECIMAL, Mobile DECIMAL NOT NULL, IDType VARCHAR(200) NOT NULL, IDNumber VARCHAR(50) NOT NULL, VehicleType VARCHAR(50), VehicleNumber VARCHAR(50))";
			Connection con = null;
			Statement st = null;
			PreparedStatement ps = null;
			try {
				Class.forName(jdbcDriver);
				con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=root");
				st = con.createStatement();
				st.execute(CDB);
				st.close();
				con.close();
				con = connection();
				st =  con.createStatement();
				st.execute(SelectDB);
				st.execute(CTuldetail);
				st.execute(CTroomdetail);
				st.execute(CTwalkindb);
				st.execute(CTreservationdb);
				st.execute(CTphonebookdb);
				st.execute(CTfeedbackdb);
				st.execute(CTcustomerdb);
				ps=con.prepareStatement(adduser);
				ps.setBytes(1, dp);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			finally {
				closeResource(con, st);
			}
			
		
		}
		
}
