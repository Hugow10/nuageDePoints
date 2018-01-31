package com.nuage.allmodes.line;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from an {@link XYDataset}.
 *
 */
@SuppressWarnings("serial")
public class LineGraph extends ApplicationFrame {

	private JFreeChart freeChart;
	private ChartPanel chartPanel;
	private XYSeriesCollection dataset;

	/**
	 * a priori useless même si opérationnel ici, à remplacer avec la nouvelle
	 * API
	 */
	public LineGraph(String title) {
		super(title);
		dataset = new XYSeriesCollection();
		freeChart = createChart(dataset);
		chartPanel = new ChartPanel(freeChart);
		chartPanel.setPreferredSize(new Dimension(500, 270));
	}

	public void fill(LinkedList<Double> xList, LinkedList<Double> yList) {
		createDataset(xList, yList);
		freeChart.getXYPlot().setDataset(dataset);
		chartPanel.setChart(freeChart);
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return a sample dataset.
	 */
	private void createDataset(LinkedList<Double> xList, LinkedList<Double> yList) {
		dataset.removeAllSeries();
		final XYSeries series1 = new XYSeries("First");
		for (int i = 0; i < xList.size(); i++) {
			series1.add(xList.get(i), yList.get(i));
		}

		dataset.addSeries(series1);

	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the data for the chart.
	 * 
	 * @return a chart.
	 */
	private JFreeChart createChart(final XYDataset dataset) {

		// create the chart...
		freeChart = ChartFactory.createXYLineChart(getTitle(), // chart title
				"X", // x axis label
				"Y", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
		);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		freeChart.setBackgroundPaint(Color.white);

		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = freeChart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.
		return freeChart;

	}

	/**
	 * @return the chartPanel
	 */
	public ChartPanel getCanvas() {
		return chartPanel;
	}

}