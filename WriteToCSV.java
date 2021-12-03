import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableModel;


public class WriteToCSV{

	public static JFrame frame;

	public static void getPath()
	{
		frame = new JFrame();
		JFileChooser file_chooser = new JFileChooser();
		file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = file_chooser.showOpenDialog(file_chooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String path = file_chooser.getSelectedFile().getPath();
			exportToCSV(Main.table,path);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You have not selected a directory to save file");
		}
	}

	public static void exportToCSV(JTable tableToExport,String pathToExportTo) {

		try {
			TableModel model = tableToExport.getModel();
			FileWriter excel = new FileWriter(pathToExportTo);
			for(int i = 0; i < model.getColumnCount(); i++)
			{
				excel.write(model.getColumnName(i) + ",");
			}
			excel.write("\n");
			for(int i=0; i< model.getRowCount(); i++) 
            {
               for(int j=0; j < model.getColumnCount(); j++) 
               {
                   String data = (String)model.getValueAt(i, j);
                   if(data == "null")
                   {
                       data="";
                   }
                   excel.write(data+",");
               }
               excel.write("\n");
           }
			excel.close();
			JOptionPane.showMessageDialog(null, "File saved");

		} catch (IOException ex) 
		{
			JOptionPane.showMessageDialog(null, "File not saved");
		}
	}
}