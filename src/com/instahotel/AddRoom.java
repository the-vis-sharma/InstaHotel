package com.instahotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Point;

public class AddRoom extends JFrame {

	private JPanel contentPane;
	private JTextField tfRoomNo;
	private JTextField tfFloorLoc;
	private JTextField tfRent;

	/**
	 * Create the frame.
	 */
	AddRoom ar=null;
	private JTextField tfSRoomNo;
	private JTextField tfNewRoomNo;
	private JTextField tfNewFloorLoc;
	private JTextField tfNewRent;
	private JTextField tfSDRoomNo;
	 public AddRoom() {
		setType(Type.UTILITY);
		ar = this;
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 450, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 430, 292);
		//tabbedPane.setLayout(null);
		contentPane.add(tabbedPane);
		
		JPanel AddRoomPanel = new JPanel();
		AddRoomPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Add Room", null, AddRoomPanel, null);
		AddRoomPanel.setLayout(null);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setBounds(32, 22, 85, 14);
		AddRoomPanel.add(lblRoomNo);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(243, 22, 46, 14);
		AddRoomPanel.add(lblType);
		
		JLabel lblFloorLocation = new JLabel("Floor Location");
		lblFloorLocation.setBounds(32, 75, 85, 14);
		AddRoomPanel.add(lblFloorLocation);
		
		JLabel lblRent = new JLabel("Rent");
		lblRent.setBounds(243, 75, 46, 14);
		AddRoomPanel.add(lblRent);
		
		tfRoomNo = new JTextField();
		tfRoomNo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.ORANGE));
		tfRoomNo.setBounds(32, 34, 172, 32);
		AddRoomPanel.add(tfRoomNo);
		tfRoomNo.setColumns(10);
		
		tfFloorLoc = new JTextField();
		tfFloorLoc.setColumns(10);
		tfFloorLoc.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.ORANGE));
		tfFloorLoc.setBounds(32, 87, 172, 32);
		AddRoomPanel.add(tfFloorLoc);
		
		tfRent = new JTextField();
		tfRent.setColumns(10);
		tfRent.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.ORANGE));
		tfRent.setBounds(243, 87, 172, 32);
		AddRoomPanel.add(tfRent);
		
		JComboBox cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(new String[] {"AC Single Room", "Non-AC Single Room  ", "AC Double Room", "Non-AC Double Room", "AC Family Room", "Non-AC Family Room"}));
		cbType.setBounds(243, 39, 125, 27);
		AddRoomPanel.add(cbType);
		
		JButton btnGo = new JButton("");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfRoomNo.getText().equals("")) {
					HeadSPanel.infoBox("Room No is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfFloorLoc.getText().equals("")) {
					HeadSPanel.infoBox("Floor Location is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfRent.getText().equals("")) {
					HeadSPanel.infoBox("Rent is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Connection con = DBConnection.connection();
					Statement st;
					String sql = "insert into roomdetail value("+tfRoomNo.getText()+",'"+cbType.getSelectedItem()+"','"+tfFloorLoc.getText()+"',"+tfRent.getText()+")";
					try {
						st = con.createStatement();
						st.executeUpdate(sql);
						tfRent.setText("");
						tfFloorLoc.setText("");
						tfRoomNo.setText("");
						cbType.setSelectedItem(0);
						HeadSPanel.infoBox("Room Added Successfully", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				}
			}
		});
		btnGo.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnGo.setBackground(Color.WHITE);
		btnGo.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnGo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGo.setBounds(343, 130, 46, 44);
		AddRoomPanel.add(btnGo);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Modify Room Details", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 425, 268);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSRoomNo = new JLabel("Enter Room No to Search");
		lblSRoomNo.setBounds(10, 11, 172, 14);
		panel_1.add(lblSRoomNo);
		
		tfSRoomNo = new JTextField();
		tfSRoomNo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.ORANGE));
		tfSRoomNo.setBounds(10, 24, 172, 32);
		panel_1.add(tfSRoomNo);
		tfSRoomNo.setColumns(10);
		
		JPanel ModifyRoomPanel = new JPanel();
		ModifyRoomPanel.setBackground(Color.PINK);
		ModifyRoomPanel.setBounds(10, 67, 405, 190);
		panel_1.add(ModifyRoomPanel);
		ModifyRoomPanel.setLayout(null);
		ModifyRoomPanel.setVisible(false);
		
		tfNewRoomNo = new JTextField();
		tfNewRoomNo.setBackground(Color.PINK);
		tfNewRoomNo.setColumns(10);
		tfNewRoomNo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		tfNewRoomNo.setBounds(22, 23, 172, 32);
		ModifyRoomPanel.add(tfNewRoomNo);
		
		JLabel lblNewRoomNo = new JLabel("Room No");
		lblNewRoomNo.setBounds(22, 11, 85, 14);
		ModifyRoomPanel.add(lblNewRoomNo);
		
		JLabel lblNewFloorLoc = new JLabel("Floor Location");
		lblNewFloorLoc.setBounds(22, 64, 85, 14);
		ModifyRoomPanel.add(lblNewFloorLoc);
		
		tfNewFloorLoc = new JTextField();
		tfNewFloorLoc.setBackground(Color.PINK);
		tfNewFloorLoc.setColumns(10);
		tfNewFloorLoc.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		tfNewFloorLoc.setBounds(22, 76, 172, 32);
		ModifyRoomPanel.add(tfNewFloorLoc);
		
		JLabel lblNewRent = new JLabel("Rent");
		lblNewRent.setBounds(223, 64, 46, 14);
		ModifyRoomPanel.add(lblNewRent);
		
		tfNewRent = new JTextField();
		tfNewRent.setBackground(Color.PINK);
		tfNewRent.setColumns(10);
		tfNewRent.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
		tfNewRent.setBounds(223, 76, 172, 32);
		ModifyRoomPanel.add(tfNewRent);
		
		JLabel lblNewType = new JLabel("Type");
		lblNewType.setBounds(223, 11, 46, 14);
		ModifyRoomPanel.add(lblNewType);
		
		JComboBox cbNewType = new JComboBox();
		cbNewType.setModel(new DefaultComboBoxModel(new String[] {"AC Single Room", "Non-AC Single Room  ", "AC Double Room", "Non-AC Double Room", "AC Family Room", "Non-AC Family Room"}));
		cbNewType.setBounds(223, 28, 125, 27);
		ModifyRoomPanel.add(cbNewType);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				if(tfNewRoomNo.getText().equals("")) {
					HeadSPanel.infoBox("Room No is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfNewFloorLoc.getText().equals("")) {
					HeadSPanel.infoBox("Floor Location is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else if(tfNewRent.getText().equals("")) {
					HeadSPanel.infoBox("Rent is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Connection con = DBConnection.connection();
					Statement st=null;
					String sql = "update roomdetail set RoomNo="+tfNewRoomNo.getText()+",Type='"+cbNewType.getSelectedItem()+"',FloorLoc='"+tfNewFloorLoc.getText()+"',Rent="+tfNewRent.getText()+" where RoomNo="+tfSRoomNo.getText(); 
					try {
						st = con.createStatement();
						st.executeUpdate(sql);
						tfSRoomNo.setText("");
						ModifyRoomPanel.setVisible(false);
						HeadSPanel.infoBox("Room details updated successfully", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					finally {
						DBConnection.closeResource(con, st);
					}
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUpdate.setBackground(Color.PINK);
		btnUpdate.setBounds(333, 119, 46, 44);
		ModifyRoomPanel.add(btnUpdate);
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfSRoomNo.getText().equals("")) {
					HeadSPanel.infoBox("Search Room No is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Connection con = DBConnection.connection();
					Statement st=null;
					ResultSet rs=null;
					String sql = "select *from roomdetail where RoomNo="+tfSRoomNo.getText();
					try {
						st = con.createStatement();
						rs = st.executeQuery(sql);
						if(rs.next())
						{
							ModifyRoomPanel.setVisible(true);
						}
						else
						{
							HeadSPanel.infoBox("No Room record found", "Room Not Found", JOptionPane.ERROR_MESSAGE);
						}
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					finally {
						DBConnection.closeResource(con, st);
					}
				}
			}
		});
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSearch.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnSearch.setBounds(199, 12, 46, 44);
		panel_1.add(btnSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Delete Room Details", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblSDRoomNo = new JLabel("Enter Room No to Search");
		lblSDRoomNo.setBounds(10, 11, 172, 14);
		panel_2.add(lblSDRoomNo);
		
		tfSDRoomNo = new JTextField();
		tfSDRoomNo.setColumns(10);
		tfSDRoomNo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.ORANGE));
		tfSDRoomNo.setBounds(10, 24, 172, 32);
		panel_2.add(tfSDRoomNo);
		
		JButton btnDSearch = new JButton("");
		btnDSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfSDRoomNo.getText().equals("")) {
					HeadSPanel.infoBox("Search Room No is required.", "Required Field", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Connection con = DBConnection.connection();
					Statement st=null;
					ResultSet rs=null;
					String sql = "select *from roomdetail where RoomNo="+tfSDRoomNo.getText();
					try {
						st = con.createStatement();
						rs = st.executeQuery(sql);
						if(rs.next())
						{
							JPanel DeleteRoomPanel = new JPanel();
							DeleteRoomPanel.setBounds(10, 67, 405, 192);
							DeleteRoomPanel.setLayout(null);
							
							JButton btnCancel = new JButton("");
							btnCancel.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									tfSDRoomNo.setText("");
									DeleteRoomPanel.setVisible(false);
								}
						});
						
						btnCancel.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/cancel.png")));
						btnCancel.setBounds(349, 137, 46, 44);
						DeleteRoomPanel.add(btnCancel);
						btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
						btnCancel.setBorder(new EmptyBorder(0, 0, 0, 0));
						btnCancel.setBackground(Color.WHITE);
						
						JTextPane txtpnRoomDetail = new JTextPane();
						txtpnRoomDetail.setText("Room No \t\t"+rs.getInt(1)+"\r\nType \t\t"+rs.getString(2)+"\r\nFloor Location \t "+rs.getString(3)+"\r\nPrice \t\t"+rs.getFloat(4)+"\r\n\nAre you Sure you wanna delete this room record?");
						txtpnRoomDetail.setEditable(false);
						txtpnRoomDetail.setBackground(Color.PINK);
						txtpnRoomDetail.setBounds(10, 10, 405, 120);
						DeleteRoomPanel.add(txtpnRoomDetail);
											
						JButton btnDelete = new JButton("");
						btnDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								Connection con = DBConnection.connection();
								Statement st=null;
								ResultSet rs=null;
								String sql = "delete from roomdetail where RoomNo="+tfSDRoomNo.getText();
								try {
									st = con.createStatement();
									st.executeUpdate(sql);
									tfSDRoomNo.setText("");
									DeleteRoomPanel.setVisible(false);
									HeadSPanel.infoBox("Room record has been deleted", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								finally {
									DBConnection.closeResource(con, st);
								}
							}
						});
						btnDelete.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/recycleBin.png")));
						btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
						btnDelete.setBorder(new EmptyBorder(0, 0, 0, 0));
						btnDelete.setBackground(Color.WHITE);
						btnDelete.setBounds(288, 137, 46, 44);
						DeleteRoomPanel.add(btnDelete);
						DeleteRoomPanel.setVisible(true);
						panel_2.add(DeleteRoomPanel);
						panel_2.repaint();
					}
					else
					{
						HeadSPanel.infoBox("No Room record found", "Room Not Found", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				finally {
					DBConnection.closeResource(con, st);
				}
			}
		}
		});
		btnDSearch.setIcon(new ImageIcon(LoginManager.class.getResource("/com/img/arrow.png")));
		btnDSearch.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnDSearch.setBackground(Color.WHITE);
		btnDSearch.setBounds(199, 12, 46, 44);
		panel_2.add(btnDSearch);
	}
}
