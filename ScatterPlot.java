import java.awt.Color;  
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;  
import org.jfree.data.xy.XYDataset;  
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  
import java.util.*;
public class ScatterPlot extends JFrame {  

	public static String Finaldate="";
	public static ArrayList<String> finalPlotvalues;
	public static XYSeries series1;
	public static XYSeriesCollection plotdata = new XYSeriesCollection(); 
	
  public ScatterPlot(String title) {  
    super(title);  
  
    // Create dataset  
    XYDataset dataset = plotdata;  
  
    // Create chart  
    JFreeChart chart = ChartFactory.createScatterPlot(  
        "CSE 563 SRS Final Project",   
        "X-Axis", "Y-Axis", dataset, PlotOrientation.VERTICAL, true, true, true);  
  
      
    //Changes background color  
    XYPlot plot = (XYPlot)chart.getPlot();  
    plot.setBackgroundPaint(new Color(255,228,196));  
      
     
    // Create Panel  
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);  
  }  
  
  public static void createDataset() {  
	  int c10=0, c9=0, c8=0, c7=0, c6=0, c5=0, c4=0, c3=0, c2=0, c1=0;
	  
	  for(int i =0;i<finalPlotvalues.size();i++) {
		  if(Integer.parseInt(finalPlotvalues.get(i)) >= 75)
			  c10++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 67.5 && Integer.parseInt(finalPlotvalues.get(i)) < 75)
			  c9++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 60 && Integer.parseInt(finalPlotvalues.get(i)) < 67.5)
			  c8++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 52.5 && Integer.parseInt(finalPlotvalues.get(i)) < 60)
			  c7++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 45 && Integer.parseInt(finalPlotvalues.get(i)) < 52.5)
			  c6++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 37.5 && Integer.parseInt(finalPlotvalues.get(i)) < 45)
			  c5++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 30 && Integer.parseInt(finalPlotvalues.get(i)) < 37.5)
			  c4++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 22.5 && Integer.parseInt(finalPlotvalues.get(i)) < 30)
			  c3++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 15 && Integer.parseInt(finalPlotvalues.get(i)) < 22.5)
			  c2++;
		  else if(Integer.parseInt(finalPlotvalues.get(i)) >= 7.5 && Integer.parseInt(finalPlotvalues.get(i)) < 15)
			  c1++;
	  }
     
  
    //Boys (Age,weight) series  
    series1 = new XYSeries(Finaldate);  
    
    series1.add(1, c1);  
    series1.add(2, c2);  
    series1.add(3, c3);  
    series1.add(4, c4);  
    series1.add(5, c5);  
    series1.add(6, c6);  
    series1.add(7, c7);  
    series1.add(8, c8);  
    series1.add(9, c9);  
    series1.add(10, c10);  
  
    plotdata.addSeries(series1);  
      
   //Girls (Age,weight) series  
//     series1 = new XYSeries("Girls");  
//    series1.add(1, 72.5);  
//    series1.add(2, 80.1);  
//    series1.add(3, 87.2);  
//    series1.add(4, 94.5);  
//    series1.add(5, 101.4);  
//    series1.add(6, 107.4);           
//    series1.add(7, 112.8);  
//    series1.add(8, 118.2);  
//    series1.add(9, 122.9);  
//    series1.add(10, 123.4);  
//  
//    plotdata.addSeries(series1);      
//  
//    return plotdata;  
  }  
  
  public static void exec() {  
    SwingUtilities.invokeLater(() -> {  
      ScatterPlot example = new ScatterPlot("Scatter Chart Example");  
      example.setSize(800, 400);  
      example.setLocationRelativeTo(null);  
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });  
  }  
}  