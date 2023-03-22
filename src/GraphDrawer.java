import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphDrawer {

    private final XYSeriesCollection dataset = new XYSeriesCollection();

    private final String label;

    public GraphDrawer(String label) {
        this.label = label;
    }

    public void addPoint(String label, double x, double y) {
        XYSeries series = new XYSeries(label);
        series.add(x,y);
        series.add(x,y);
        dataset.addSeries(series);
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
                this.label,
                "x",
                "y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel( chart );
        //chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );

        final XYPlot plot = chart.getXYPlot( );

        ValueAxis domainAxis = plot.getDomainAxis();
        ValueAxis rangeAxis = plot.getRangeAxis();

        domainAxis.setRange(-10.0, 10.0);
        rangeAxis.setRange(-10.0, 10.0);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.BLACK );
        renderer.setSeriesPaint( 1 , Color.BLACK );
        renderer.setSeriesPaint( 2 , Color.BLACK );
        renderer.setSeriesPaint( 3 , Color.BLACK );
        renderer.setSeriesStroke( 0 , new BasicStroke( 5.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 5.0f ) );
        renderer.setSeriesStroke( 2 , new BasicStroke( 5.0f ) );
        renderer.setSeriesStroke( 3 , new BasicStroke( 5.0f ) );
        renderer.setSeriesShape(0, new Rectangle(1,1,1,1));
        renderer.setSeriesShape(1, new Rectangle(1,1,1,1));
        renderer.setSeriesShape(2, new Rectangle(1,1,1,1));
        renderer.setSeriesShape(3, new Rectangle(1,1,1,1));

        renderPoints();

        renderer.setDefaultStroke(new BasicStroke(5.0f));
        renderer.setAutoPopulateSeriesStroke(false);

        plot.setRenderer( renderer );

        // Display the chart
        ChartFrame frame = new ChartFrame("Line Plotter", chart);
        //frame.pack();
        frame.setSize(700,800);
        frame.setVisible(true);
    }

    private void renderPoints() {

    }
}
