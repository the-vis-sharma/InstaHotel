package com.instahotel;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
public class ChangeAvtar extends JFrame {
static Connection connection=DBConnection.connection();
static PreparedStatement ps=null;
static ResultSet rs=null;
static String filePath=null;

public static ImageIcon retriveImage(Home h, String UserName)
	{
		try {
			ps=connection.prepareStatement("select * from uldetail where Name=?");
			ps.setObject(1, UserName);
			rs=ps.executeQuery();
			byte b[] = null;
			while(rs.next())
			{
				b= rs.getBytes("dp");
			}
			ImageIcon ii = new ImageIcon (Toolkit.getDefaultToolkit().createImage(b));
			return ii;
		}
		catch(Exception e) {
		}
		return null;
	}

//1048576 Size limit allowed for Image storage in MySQL.
public static void storeImage(Home h, String UserName)
{
	try {
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("jpeg, gif and png files", "jpg", "gif", "png");
		chooser.addChoosableFileFilter(filter);
		chooser.setMultiSelectionEnabled(false);
		chooser.setVisible(true);
		chooser.showOpenDialog(h);
		File file=chooser.getSelectedFile();
		if(file!=null){
			filePath=file.getPath();
		}
		if(filePath!=null && check()){
			ps=connection.prepareStatement("update uldetail set dp=(?) where Name='"+UserName+"'");
			ImageIcon ii = new ImageIcon(Toolkit.getDefaultToolkit().getImage(file.getPath()));
			BufferedImage bi = new BufferedImage(220, 220, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bi.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(ii.getImage(), 0, 0, 220, 220, null);
			//ii.paintIcon(null, g, 0, 0);
			g.dispose();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			baos.flush();
			byte[] b = baos.toByteArray();
			baos.close();
			ps.setBytes(1, b);
			int val=ps.executeUpdate();
			if(val>=1)
				JOptionPane.showMessageDialog(h, "Bingo! Your Avtar changed Successfully.");
			else
				JOptionPane.showMessageDialog(h, "Error: Can't Upload Image.");
		}
		else {
			JOptionPane.showMessageDialog(h,"Please select an Image of type .jpeg/png/jpg.");
		}
	}
	catch(Exception e) {
		JOptionPane.showMessageDialog(h, e.getMessage());
		e.printStackTrace();
	}
}

private static boolean check() {
	if(filePath!=null) {
		if(filePath.endsWith(".jpeg")||filePath.endsWith(".png")||filePath.endsWith(".jpg")||filePath.endsWith(".JPEG")||filePath.endsWith(".PNG")||filePath.endsWith(".JPG")) {
			return true;
		}
		return false;
	}
	return false;
}
}