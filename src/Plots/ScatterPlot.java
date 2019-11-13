package Plots;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import kMeans.Cluster;
import kMeans.Point;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author imssbora
 */
public class ScatterPlot extends JFrame {
  private static final long serialVersionUID = 6294689542092367723L;

  public ScatterPlot(String title, String xAxis, String yAxis, String description, List<Cluster> clusters) {
    super(title);

    // Create dataset
    XYDataset dataset = createDataset(clusters);

    // Create chart
    JFreeChart chart = ChartFactory.createScatterPlot(
        description, 
        xAxis, yAxis, dataset);

    
    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));
    
   
    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  private XYDataset createDataset(List<Cluster> clusters) {
    XYSeriesCollection dataset = new XYSeriesCollection();
    int i = 0;
    
    for (Cluster c : clusters) {
        XYSeries serie = new XYSeries("Cluster " + ++i);
        XYSeries centroid = new XYSeries("Centroid Cluster " + i);
        centroid.add(c.centroid.getY(), c.centroid.getZ());
        for (Point p : c.points) {
            serie.add(p.getY(), p.getZ());
        }
        dataset.addSeries(centroid);         
        dataset.addSeries(serie);
    }
    
    return dataset;
  }

}