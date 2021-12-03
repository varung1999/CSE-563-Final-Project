import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.DateFormatter;
import javax.swing.text.TableView.TableRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class DatePickerSample extends JFrame implements ActionListener{

	public static JDatePickerImpl datePicker;
	public static String date="";
	public static JButton button;
	public static JFrame frame;
	public static SimpleDateFormat format;
	public static Calendar cal;
	public static Main m;
	public static String asu_id="";
	public static String name="";
	public static JDialog d;
	public static String time;
	public static HashMap<String,String> newMembers = new HashMap<>();
	public static int count = 6;

	DatePickerSample()
	{
		frame = new JFrame("Select Date");
		SqlDateModel model = new SqlDateModel();
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.day","Day");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		button = new JButton("Submit");
		button.setBounds(50,80,80,30);
		JDatePanelImpl panel = new JDatePanelImpl(model,p);

		datePicker = new JDatePickerImpl(panel,new AbstractFormatter() {

			@Override
			public Object stringToValue(String text) throws ParseException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				// TODO Auto-generated method stub
				if(value!=null)
				{
					cal = (Calendar) value;
					format = new SimpleDateFormat("dd-MMM-yyyy");
					String strDate =  format.format(cal.getTime());
					return strDate;

				}
				return "";
			}

		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				date = format.format(cal.getTime());
				ScatterPlot.Finaldate = date;
				frame.dispose();
				FillColumn();

			}
		});
		datePicker.setBounds(50, 30, 110, 30);
		frame.add(datePicker);
		frame.add(button);
		
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 300);
		frame.setVisible(true);

	}

	public static void FillColumn()
	{
		
		int count = Main.table.getRowCount();
		Object[] obj = new Object[count];
		for(int i = 0;i<count;i++)
		{
			obj[i]="0";
		}

		Main.tableModel.addColumn(date, obj);
		Logic();
	}


	public static void Logic()
	{	
		ScatterPlot.finalPlotvalues = new ArrayList<>();
		for(Map.Entry<String,String> it: m.map.entrySet())
		{
			boolean flag = false;
			for(int i =0;i<m.list.size();i++)
			{
				String[] current = m.list.get(i);

				asu_id = current[current.length-1];
				name = it.getKey();
				time = it.getValue();
				if(it.getKey().equals(asu_id))
				{
					m.tableModel.setValueAt(it.getValue(), i, count);
					ScatterPlot.finalPlotvalues.add(time);
					flag = true;
					break;
				}
			}
			if(flag == false)
			{
				newMembers.put(name,time);
			}
		}
		
		

		int hashMap_size = m.map.size();
		int newMembers_size = newMembers.size();
		
		int totalLoadedUsers = hashMap_size - newMembers_size;

		StringBuilder sb = new StringBuilder();
		int finalTime =0;
		if(newMembers_size!=0) {
			for(Map.Entry<String,String> iterator: newMembers.entrySet())
			{
				String name = iterator.getKey();
				int time = Integer.valueOf(iterator.getValue());
				
				name = name + ", connected for " + Integer.toString(time) +" minute ; "; 
				sb.append(name);
				
			}
		
			JOptionPane.showMessageDialog(frame,"Data Loaded for "+ totalLoadedUsers+" users in the roaster\n" + newMembers_size + " additional attendee(s) were found!\n" + sb.toString());
		}
		count++;
		newMembers = new HashMap<>();
		Main.map = new HashMap<>();
		ScatterPlot.createDataset();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


}
