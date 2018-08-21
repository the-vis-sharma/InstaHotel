package com.instahotel;

import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.JDateComponent;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class CreateJDatePicker {
	
	private static JDatePickerImpl datePicker;
	public static JDatePickerImpl JDatePicker(int dd, int mm, int yyyy)
	{
		UtilDateModel model = new UtilDateModel();
		model.setDate(yyyy, mm-1, dd);
		
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");		   
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		JFormattedTextField tf = datePicker.getJFormattedTextField();
		tf.setColumns(7);
		tf.setFont(new Font("Time New Roman", Font.PLAIN, 14));
		tf.setBorder(new EmptyBorder(0, 0, 0, 0));
		return datePicker;
	}
	
	public static String getSelectedDate(JDatePickerImpl dp)
	{
		Date selectedDate = (Date) dp.getModel().getValue();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(selectedDate);
	}
	
	public static boolean Before(JDatePickerImpl a, JDatePickerImpl d)
	{
		Date ad = (Date) a.getModel().getValue();
		Date dp = (Date) d.getModel().getValue();
		if(ad.before(dp))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "dd/MM/yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		
		return "";
	}

}