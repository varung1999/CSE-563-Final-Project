import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView.TableRow;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class Main implements ActionListener{

	public static JFrame frame;
	public static JMenuBar menuBar;
	public static JMenu FileMenu;
	public static JMenu AboutMenu;
	public static JMenuItem LoadRoaster;
	public static JMenuItem AddAttendance;
	public static JMenuItem Save;
	public static JMenuItem PlotData;
	public static JTable table;
	public static DefaultTableModel tableModel;
	public static JMenuItem TeamMates;
	public static JScrollPane sp;
	public static DatePickerSample dps;
	public static String newDate="";
	
	public static Vector<String[]> list = new Vector<>();
	public static HashMap<String,String> map = new HashMap<>();
	
	public static WriteToCSV write;
	
	//jdatepicker 
	JDatePickerImpl datePicker;
	SqlDateModel model;
	Properties p;
	JDatePanelImpl panel;
	
	public Main()
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				JFrame frame = new JFrame();
				frame.setTitle("CSE 563 SRS");

				menuBar = new JMenuBar();
				FileMenu = new JMenu("File");
				AboutMenu = new JMenu("About");
				TeamMates = new JMenuItem("Team Members");
				
				TeamMates.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						printTeamNames();
					}

				});
				AboutMenu.add(TeamMates);
				

				LoadRoaster = new JMenuItem("Load A Roaster");
				LoadRoaster.addActionListener(new ActionListener() {
					
					
					public void actionPerformed(ActionEvent e)
					{
						frame.add(sp);
						LoadRoaster();
						frame.pack();
						
					}
				});
				AddAttendance = new JMenuItem("Add Attendance");
				AddAttendance.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						Attendance();	

					}
				});
				Save = new JMenuItem("Save");
				Save.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						write = new WriteToCSV();
						write.getPath();
					}
				});
				PlotData = new JMenuItem("Plot");
				PlotData.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e)
					{
						ScatterPlot.exec();
					}
				});

				FileMenu.add(LoadRoaster);
				FileMenu.add(AddAttendance);
				FileMenu.add(Save);
				FileMenu.add(PlotData);

				menuBar.add(FileMenu);
				menuBar.add(AboutMenu);
				
				tableModel = new DefaultTableModel();
				tableModel.addColumn("ID");
				tableModel.addColumn("First Name");
				tableModel.addColumn("Last Name");
				tableModel.addColumn("Program");
				tableModel.addColumn("Level");
				tableModel.addColumn("ASURITE ID");

				table = new JTable(tableModel);
				table.setBounds(30,40,200,300);          
				sp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				frame.add(menuBar,BorderLayout.NORTH);
				frame.setSize(400, 400); 
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	public void LoadRoaster()
	{
		
		JFileChooser file_chooser = new JFileChooser();
		if(file_chooser.showOpenDialog(file_chooser)== file_chooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(frame, "File has been selected by the user");
			File file = file_chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			FileReader file_reader;		
			
			try {
				file_reader = new FileReader(file);
				BufferedReader br = new BufferedReader(file_reader);
				String line="";
				String[] temp = new String[6];
				
				
				while((line=br.readLine())!=null)
				{
					temp = line.split(",");
					list.add(temp);
					tableModel.addRow(temp);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "File has not been selected by user");
		}
		
	}
	

	private void printTeamNames()
	{
		JOptionPane.showMessageDialog(frame, "Praneeth Kaliki- 1222739856\n Raviram Mamidi - 1222307268\n Vaibhav Mani - 1220342773\n Avinash Bawane - 1220264344 ");
	}
	
	private void Attendance()
	{
		
		JFileChooser achooser = new JFileChooser();
		if(achooser.showOpenDialog(achooser)== achooser.APPROVE_OPTION)
		{
			JOptionPane.showMessageDialog(frame, "File has been selected by the user");
			File file = achooser.getSelectedFile();
			String path = file.getAbsolutePath();
			FileReader file_reader;		
			
			try {
				file_reader = new FileReader(file);
				BufferedReader br = new BufferedReader(file_reader);
				String line="";
				String[] temp = new String[2];
				
				
				while((line=br.readLine())!=null)
				{
					temp = line.split(",");
					String name = temp[0];
					int time = Integer.valueOf(temp[1]);
					
					if(map.containsKey(name))
					{
						int value = Integer.valueOf(map.get(name));
						value = value + time;
						
						map.put(name, Integer.toString(value));
					}
					else
					{
						map.put(name, Integer.toString(time));
					}
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "File has not been selected by user");
		}
		dps = new DatePickerSample();
	}
	

	public static void main(String[] args) {
		
		Main m = new Main();
	}

}