package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import org.omg.PortableInterceptor.INACTIVE;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.Thread.State;

public class CheckOut extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearchCustomer;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField txtCustomerDetails;
	private JTextField txtPaymentDetails;
	private JTextField tfOtherCharges;
	private JTextField tfSubTotal;
	private JLabel lblDiscount;
	private JTextField tfDiscount;
	private JLabel lblTotalAmt;
	private JTextField tfTotalAmt;
	private JButton btnCheckOut;
	private JPanel panel_1;
	private JLabel label_2;
	private JTable table;
	private JButton button;

	/**
	 * Create the frame.
	 */
	long NoOfDays;
	private JLabel lblVAT;
	private JTextField tfVAT;
	private JLabel label_1;
	private JLabel lblAmtPayable;
	private JTextField tfAmtPayable;
	private JTextField tfDD;
	public CheckOut() {
		CheckOut co=this;
		setTitle("InstaHotel - Customer Check Out");
		setType(Type.UTILITY);
		setVisible(true);
		setBackground(new Color(138, 43, 226));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 602, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(138, 43, 226));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 182, 193));
		panel.setBounds(10, 11, 566, 539);
		contentPane.add(panel);
		panel.setLayout(null);
		
		MyTableModel mtm = new MyTableModel(new String[]{"Select","Room No","Rent/Day","Arrival Date","Departure Date","No Of Days"});
		
		tfSearchCustomer = new JTextField();
		tfSearchCustomer.setEditable(false);
		tfSearchCustomer.setBorder(new EmptyBorder(0, 0, 0, 0));
		tfSearchCustomer.setForeground(new Color(255, 255, 255));
		tfSearchCustomer.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		tfSearchCustomer.setBackground(new Color(0, 0, 255));
		tfSearchCustomer.setText("    Search Customer");
		tfSearchCustomer.setBounds(10, 11, 546, 29);
		panel.add(tfSearchCustomer);
		tfSearchCustomer.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFirstName.setBounds(20, 51, 109, 14);
		panel.add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfFirstName.setBackground(new Color(255, 182, 193));
		tfFirstName.setBounds(20, 69, 150, 20);
		panel.add(tfFirstName);
		
		JLabel lblLastName = new JLabel("Last Name*");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLastName.setBounds(210, 51, 109, 14);
		panel.add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		tfLastName.setBackground(new Color(255, 182, 193));
		tfLastName.setBounds(210, 69, 150, 20);
		panel.add(tfLastName);
		
		JButton btnSearch = new JButton("");
		int days;
		int rent[];
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = DBConnection.connection();
				Statement st = null;
				ResultSet rs = null;
				String sql = "SELECT * FROM reservationdb LEFT JOIN roomdetail ON reservationdb.RoomNo=roomdetail.RoomNo WHERE reservationdb.CustomerName='"+tfFirstName.getText()+" "+tfLastName.getText()+"'";
				try {
					st = con.createStatement();
					rs = st.executeQuery(sql);
					boolean NoRoom=true;
					int count=0;
					for (int i = mtm.getRowCount() - 1; i > -1; i--) 
					{
						mtm.removeRow(i);
					}
					
					while(rs.next())
					{
						NoOfDays = TimeUnit.DAYS.convert(rs.getDate("DepartureDate").getTime() - rs.getDate("ArrivalDate").getTime(), TimeUnit.MILLISECONDS)+1;
						NoRoom = false;
						mtm.insertRow(count, new Object[]{false,rs.getInt("RoomNo"),rs.getString("Rent"),rs.getString("ArrivalDate"),rs.getString("DepartureDate"),NoOfDays});
						count++;
					}
					if(NoRoom)
					{
						HeadSPanel.infoBox("No customer record found", "No Record Found", JOptionPane.WARNING_MESSAGE);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setBackground(new Color(255, 182, 193));
		btnSearch.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setBounds(510, 51, 46, 44);
		panel.add(btnSearch);
		
		txtCustomerDetails = new JTextField();
		txtCustomerDetails.setText("    Customer Details");
		txtCustomerDetails.setForeground(Color.WHITE);
		txtCustomerDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtCustomerDetails.setEditable(false);
		txtCustomerDetails.setColumns(10);
		txtCustomerDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtCustomerDetails.setBackground(Color.BLUE);
		txtCustomerDetails.setBounds(10, 100, 546, 29);
		panel.add(txtCustomerDetails);
		
		txtPaymentDetails = new JTextField();
		txtPaymentDetails.setText("    Payment Details");
		txtPaymentDetails.setForeground(Color.WHITE);
		txtPaymentDetails.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txtPaymentDetails.setEditable(false);
		txtPaymentDetails.setColumns(10);
		txtPaymentDetails.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtPaymentDetails.setBackground(Color.BLUE);
		txtPaymentDetails.setBounds(10, 296, 546, 29);
		panel.add(txtPaymentDetails);
		
		JLabel lblNoOfLate = new JLabel("No. of Late Days");
		
		lblNoOfLate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNoOfLate.setBounds(88, 336, 109, 14);
		panel.add(lblNoOfLate);
		
		JTextField tfNoOfLateDays = HeadSPanel.BtnCounter(panel, 330, 336);
			
		tfNoOfLateDays.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    
			  }
			  public void removeUpdate(DocumentEvent e) {
			    
			  }
			  public void insertUpdate(DocumentEvent e) {
				  int sum = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						
						if (isChecked) {
							tfSubTotal.setText(""+(Integer.parseInt(table.getValueAt(i, 2).toString()) * (Integer.parseInt(table.getValueAt(i, 5).toString())+Integer.parseInt(tfNoOfLateDays.getText()))-Integer.parseInt(tfOtherCharges.getText())));					
						}
					}
			  }

			});
		
		JLabel lblOtherCharges = new JLabel("Other Charges");
		lblOtherCharges.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOtherCharges.setBounds(88, 361, 109, 14);
		panel.add(lblOtherCharges);
	
		tfOtherCharges = new JTextField();
		tfOtherCharges.setText("0");
		tfOtherCharges.setColumns(10);
		tfOtherCharges.setBounds(330, 361, 130, 20);
		panel.add(tfOtherCharges);
		
		tfOtherCharges.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  
			  }
			  public void removeUpdate(DocumentEvent e) {
				  
			  }
			  public void insertUpdate(DocumentEvent e) {
				  int sum = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						
						if (isChecked) {
							tfSubTotal.setText(""+(Integer.parseInt(table.getValueAt(i, 2).toString()) * (Integer.parseInt(table.getValueAt(i, 5).toString())+Integer.parseInt(tfNoOfLateDays.getText()))+Integer.parseInt(tfOtherCharges.getText())));					
						}
					}
			  }
			  
			});
		
		JLabel lblSubTotal = new JLabel("Sub Total");
		lblSubTotal.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSubTotal.setBounds(88, 386, 109, 14);
		panel.add(lblSubTotal);
		
		tfSubTotal = new JTextField();
		tfSubTotal.setEditable(false);
		tfSubTotal.setColumns(10);
		tfSubTotal.setBounds(330, 386, 130, 20);
		panel.add(tfSubTotal);
		
		tfSubTotal.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    
			  }
			  public void removeUpdate(DocumentEvent e) {
			    
			  }
			  public void insertUpdate(DocumentEvent e) {
				  int sum = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						
						if (isChecked) {
							int dis = (Integer.parseInt(tfDiscount.getText())*Integer.parseInt(tfSubTotal.getText()))/100;
							
							tfTotalAmt.setText(""+(Integer.parseInt(tfSubTotal.getText())-dis));					
						}
					}
			  }

			});
		
		lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDiscount.setBounds(88, 411, 109, 14);
		panel.add(lblDiscount);
		
		tfDiscount = new JTextField();
		tfDiscount.setText("0");
		tfDiscount.setColumns(10);
		tfDiscount.setBounds(330, 411, 130, 20);
		panel.add(tfDiscount);
		
		tfDiscount.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    
			  }
			  public void removeUpdate(DocumentEvent e) {
			    
			  }
			  public void insertUpdate(DocumentEvent e) {
				  int sum = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						
						if (isChecked) {
							int dis = (Integer.parseInt(tfDiscount.getText())*Integer.parseInt(tfSubTotal.getText()))/100;
							
							tfTotalAmt.setText(""+(Integer.parseInt(tfSubTotal.getText())-dis));					
						}
					}
			  }

			});
		
		lblTotalAmt = new JLabel("Total Amount");
		lblTotalAmt.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTotalAmt.setBounds(88, 436, 109, 14);
		panel.add(lblTotalAmt);
		
		tfTotalAmt = new JTextField();
		tfTotalAmt.setEditable(false);
		tfTotalAmt.setColumns(10);
		tfTotalAmt.setBounds(330, 436, 130, 20);
		panel.add(tfTotalAmt);
		
		tfTotalAmt.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    
			  }
			  public void removeUpdate(DocumentEvent e) {
			    
			  }
			  public void insertUpdate(DocumentEvent e) {
				  int sum = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						
						if (isChecked) {
							int vat = (Integer.parseInt(tfVAT.getText())*Integer.parseInt(tfTotalAmt.getText()))/100;
							
							tfAmtPayable.setText(""+(Integer.parseInt(tfTotalAmt.getText())+vat));					
						}
					}
			  }

			});
		
		btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfFirstName.getText().equals("")) {
					HeadSPanel.infoBox("First Name can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfLastName.getText().equals("")) {
					HeadSPanel.infoBox("Last Name can't be blank.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfAmtPayable.getText().equals("")) {
					HeadSPanel.infoBox("Please choose at least one room.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else {
				LocalDate now = LocalDate.now();
				String today = ""+now.getYear()+"-"+now.getMonthValue()+"-"+now.getDayOfMonth();
				
				Connection con = DBConnection.connection();
				Statement st = null;
				
				long totalDays = NoOfDays+Long.parseLong(tfNoOfLateDays.getText());
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				String DD=null;
				try {
					Date NDD = f.parse(tfDD.getText());
					Calendar c = Calendar.getInstance(); 
					c.setTime(NDD); 
					c.add(Calendar.DATE, 1);
					DD = f.format(c.getTime());
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				String sql = "update walkindb set DepartureDate='"+DD+"', NoOfDays="+totalDays+", OtherCharges="+Integer.parseInt(tfOtherCharges.getText())+", Discount="+Integer.parseInt(tfDiscount.getText())+", VAT="+tfVAT.getText()+", SubTotal="+tfSubTotal.getText()+", Total="+tfTotalAmt.getText()+", AmtPaid="+tfAmtPayable.getText()+" where CustomerName='"+tfFirstName.getText()+" "+tfLastName.getText()+"'";
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					DBConnection.closeResource(con, st);
					CheckOutReciept cor = new CheckOutReciept(tfFirstName.getText()+" "+tfLastName.getText());
				}
			}
			}
		});
		btnCheckOut.setBounds(456, 505, 100, 23);
		panel.add(btnCheckOut);
		
		label_2 = new JLabel("%");
		label_2.setFont(new Font("Arial", Font.PLAIN, 12));
		label_2.setBounds(470, 412, 26, 14);
		panel.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 546, 120);
		panel.add(scrollPane);
		
		table = new JTable(mtm);
		scrollPane.setViewportView(table);
		
		tfDD = new JTextField();
		tfDD.setEditable(false);
		tfDD.setBounds(470, 271, 86, 20);
		tfDD.setColumns(10);
		
		button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					
					if (isChecked) {
						tfDD.setText(table.getValueAt(i, 4).toString());
						sum += Integer.parseInt(table.getValueAt(i, 2).toString())*(Integer.parseInt(table.getValueAt(i, 5).toString())+Integer.parseInt(tfNoOfLateDays.getText()));
					}
				}
				
				tfSubTotal.setText(sum+"");
			}
		});
		button.setBounds(10, 262, 89, 23);
		panel.add(button);
		
		lblVAT = new JLabel("V.A.T.");
		lblVAT.setFont(new Font("Arial", Font.PLAIN, 12));
		lblVAT.setBounds(88, 461, 109, 14);
		panel.add(lblVAT);
		
		tfVAT = new JTextField();
		tfVAT.setText("0");
		tfVAT.setColumns(10);
		tfVAT.setBounds(330, 461, 130, 20);
		panel.add(tfVAT);
		
		tfVAT.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    
			  }
			  public void removeUpdate(DocumentEvent e) {
			    
			  }
			  public void insertUpdate(DocumentEvent e) {
				  int sum = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						
						if (isChecked) {
							int vat = (Integer.parseInt(tfVAT.getText())*Integer.parseInt(tfTotalAmt.getText()))/100;
							
							tfAmtPayable.setText(""+(Integer.parseInt(tfTotalAmt.getText())+vat));					
						}
					}
			  }

			});

		
		label_1 = new JLabel("%");
		label_1.setFont(new Font("Arial", Font.PLAIN, 12));
		label_1.setBounds(470, 462, 26, 14);
		panel.add(label_1);
		
		lblAmtPayable = new JLabel("Payable Amount");
		lblAmtPayable.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAmtPayable.setBounds(88, 486, 109, 14);
		panel.add(lblAmtPayable);
		
		tfAmtPayable = new JTextField();
		tfAmtPayable.setEditable(false);
		tfAmtPayable.setColumns(10);
		tfAmtPayable.setBounds(330, 486, 130, 20);
		panel.add(tfAmtPayable);
		
	}
}
