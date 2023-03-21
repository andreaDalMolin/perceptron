import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class GraphDrawer {

    private final XYSeriesCollection dataset = new XYSeriesCollection();
    private final String label;

    public GraphDrawer(String label) {
        this.label = label;
    }

    public void addLine(double w0, double w1, double w2, String label) {
        XYSeries series = new XYSeries(label);
        double[][] coords = solveCoords(w0, w1, w2);
        series.add(coords[0][0], coords[0][1]);
        series.add(coords[1][0], coords[1][1]);
        dataset.addSeries(series);
    }

    public static double[][] solveCoords(double w0, double w1, double w2) {
        if (w1 == 0 && w2 == 0) {
            return new double[][]{{10,10},{10,10}};
        } else if (w1 == 0) {
            return new double[][]{{-10, -w0/w2},{10, -w0/w2}};
        } else if (w2 == 0) {
            return new double[][]{{-w0/w1,10},{-w0/w1,-10}};
        } else {
            return new double[][]{{-10, (-w0-w1*(-10))/w2},{10, (-w0-w1*10)/w2}};
        }
    }

    public void draw() {
        JFreeChart chart = ChartFactory.createXYLineChart(
                this.label, // Chart title
                "x", // X-axis label
                "y", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );

        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = chart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        renderer.setSeriesPaint( 2 , Color.YELLOW );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 2 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 3 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 4 , new BasicStroke( 4.0f ) );
        plot.setRenderer( renderer );

        // Display the chart
        ChartFrame frame = new ChartFrame("Line Plotter", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
