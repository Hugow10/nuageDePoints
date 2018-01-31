package com.nuage.allmodes.line;

import java.awt.Component;
import java.util.LinkedList;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.chart.factories.IChartComponentFactory;
import org.jzy3d.chart2d.Chart2d;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot2d.primitives.Serie2d;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.primitives.axes.layout.IAxeLayout;
import org.jzy3d.plot3d.rendering.canvas.Quality;

public class LineForReal {

	private Chart2d chart2D;
	private Serie2d serie2D;
	private Component canvas;

	/**
	 * a priori useless, à remplacer avec la nouvelle API
	 */

	public LineForReal(LinkedList<Double> xList, LinkedList<Double> yList, String xLabel, String yLabel) {
		chart2D = new Chart2d(IChartComponentFactory.Toolkit.swing);
		// chart2D.getSerie("x", Serie2d.Type.LINE);
		serie2D = chart2D.getSerie("y", Serie2d.Type.LINE);
		chart2D.view2d();
		IAxeLayout axe = chart2D.getAxeLayout();
		axe.setYAxeLabel(yLabel);
		axe.setXAxeLabel(xLabel);
		for (int i = 0; i < xList.size(); i++) {
			serie2D.add(xList.get(i), yList.get(i));
		}
		// Define a function to plot
		Mapper mapper = new Mapper() {
			@Override
			public double f(double x, double y) {
				return x * Math.sin(x * y);
			}
		};

		// Define range and precision for the function to plot
		Range range = new Range(1, 5);
		int steps = 80;

		// Create the object to represent the function over the given range.
		final Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
		surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(),
				surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
		surface.setFaceDisplayed(true);
		surface.setWireframeDisplayed(false);

		// Create a chart
		Chart chart = new Chart(Quality.Advanced);
		chart = AWTChartComponentFactory.chart(Quality.Advanced, IChartComponentFactory.Toolkit.swing);
		chart.getScene().getGraph().add(surface);

		chart.addMouseCameraController();
		chart.addMousePickingController(2);
		/*
		 * AWTCameraMouseController controller = new
		 * AWTCameraMouseController(chart);
		 * 
		 * addMouseListener(controller); addMouseMotionListener(controller);
		 * addMouseWheelListener(controller);
		 */
		canvas = (Component) chart.getCanvas();

		// JPanel panel = new JPanel();
		// panel.add(canvas, BorderLayout.CENTER);
		// return canvas;
		// return new JPanel();
	}

	public Component getCanvas() {
		return canvas;
	}

}
