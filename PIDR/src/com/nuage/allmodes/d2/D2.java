package com.nuage.allmodes.d2;

import java.awt.Component;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.chart.factories.IChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import com.nuage.allmodes.DisplaySettings;
import com.nuage.allmodes.DisplaySettingsPanel;
import com.nuage.allmodes.TableCustomModel;
import com.nuage.allmodes.Updatable;
import com.nuage.allmodes.calculator.MathShortcuts;
import com.nuage.allmodes.calculator.MathShortcutsPanel;
import com.nuage.allmodes.d2andd3.CalculatedFunction;
import com.nuage.allmodes.d2andd3.CalculatedFunctionPanel;
import com.nuage.allmodes.d2andd3.TestFunction;
import com.nuage.allmodes.d2andd3.TestFunctionPanel;
import com.nuage.allmodes.lineandd2.Data2DPanel;
import com.nuage.allmodes.lineandd2.Table2DConstraintCustomModel;
import com.nuage.allmodes.lineandd2.TableCustom2DModel;
import com.nuage.allmodes.lineandplane.TransformX;
import com.nuage.allmodes.lineandplane.TransformY;

public class D2 implements Updatable {

	private TableCustom2DModel twoDDatasModel;
	private Data2DPanel twoDDatasPane;
	private Table2DConstraintCustomModel constraint2DModel;
	private Constraint2DPanel twoDConstraintsPane;
	private TestFunction twoDTestFunction;
	private TestFunctionPanel twoDTestFunctionPane;
	private MathShortcuts twoDMathShortcuts;
	private MathShortcutsPanel twoDMathShortcutsPane;
	private CalculatedFunction twoDCalculatedFunction;
	private CalculatedFunctionPanel twoDCalculatedFunctionPane;
	private DisplaySettings twoDDisplaySettings;
	private DisplaySettingsPanel twoDDisplaySettingsPane;
	private D2Graph d2Graph;
	private TableCustom2DModel twoDTransformedDatasModel;
	private Data2DPanel twoDTransformedDatasPane;

	public D2() {
		// Datas
		twoDDatasModel = new TableCustom2DModel(new String[] { "n°", "Xi", "Yi" });
		twoDDatasPane = new Data2DPanel(twoDDatasModel);
		// TransformedDatas
		twoDTransformedDatasModel = new TableCustom2DModel(new String[] { "n°", "Xi=tx(xi)", "Yi=ty(yi)" });
		twoDTransformedDatasPane = new Data2DPanel(twoDTransformedDatasModel);
		// Contraintes
		constraint2DModel = new Table2DConstraintCustomModel(
				new String[] { "N°", "Xw", "Valeur contrainte", "Ordre de dérivation" });
		// twoDConstraints = new Constraints(new String[]{"N°","Xw","Valeur
		// contrainte","Ordre de dérivation"});
		twoDConstraintsPane = new Constraint2DPanel(constraint2DModel);
		// Fonction de Test
		twoDTestFunction = new TestFunction("(x)");
		twoDTestFunctionPane = new TestFunctionPanel(twoDTestFunction);
		// Calculette
		twoDMathShortcuts = new MathShortcuts(twoDTestFunction);
		twoDMathShortcutsPane = new MathShortcutsPanel(twoDMathShortcuts);
		// Fonction calculée
		twoDCalculatedFunction = new CalculatedFunction(" f(x) =   ", "x0");
		twoDCalculatedFunctionPane = new CalculatedFunctionPanel(twoDCalculatedFunction);
		// Partie display
		twoDDisplaySettings = new DisplaySettings();
		twoDDisplaySettingsPane = new DisplaySettingsPanel(twoDDisplaySettings);
		d2Graph = new D2Graph();
	}

	public Component getVisualisationPanel() {
		// Define a function to plot
		Mapper mapper = new Mapper() {
			@Override
			public double f(double x, double y) {
				// return x * Math.sin(x * y);
				return 0;
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
		Chart chart = new Chart();
		chart = AWTChartComponentFactory.chart(Quality.Advanced, IChartComponentFactory.Toolkit.swing);
		chart.getScene().getGraph().add(surface);

		chart.addMouseCameraController();
		chart.addMousePickingController(2);
		/*
		 * AWTCameraMouseController controller = new AWTCameraMouseController(chart);
		 * 
		 * addMouseListener(controller); addMouseMotionListener(controller);
		 * addMouseWheelListener(controller);
		 */
		Component canvas = (Component) chart.getCanvas();

		// JPanel panel = new JPanel();
		// panel.add(canvas, BorderLayout.CENTER);
		return canvas;
		// return new JPanel();
	}

	/**
	 * @return the twoDConstraints
	 */
	public Table2DConstraintCustomModel getTwoDConstraints() {
		return constraint2DModel;
	}

	/**
	 * @return the twoDConstraintsPane
	 */
	public Constraint2DPanel getTwoDConstraintsPane() {
		return twoDConstraintsPane;
	}

	/**
	 * @return the twoDTestFunction
	 */
	private TestFunction getTwoDTestFunction() {
		return twoDTestFunction;
	}

	/**
	 * @return the twoDTestFunctionPane
	 */
	public TestFunctionPanel getTwoDTestFunctionPane() {
		return twoDTestFunctionPane;
	}

	/**
	 * @return the twoDMathShortcuts
	 */
	public MathShortcuts getTwoDMathShortcuts() {
		return twoDMathShortcuts;
	}

	/**
	 * @return the twoDMathShortcutsPane
	 */
	public MathShortcutsPanel getTwoDMathShortcutsPane() {
		return twoDMathShortcutsPane;
	}

	/**
	 * @return the twoDCalculatedFunction
	 */
	public CalculatedFunction getTwoDCalculatedFunction() {
		return twoDCalculatedFunction;
	}

	/**
	 * @return the twoDCalculatedFunctionPane
	 */
	public CalculatedFunctionPanel getTwoDCalculatedFunctionPane() {
		return twoDCalculatedFunctionPane;
	}

	/**
	 * @return the twoDDisplaySettings
	 */
	public DisplaySettings getTwoDDisplaySettings() {
		return twoDDisplaySettings;
	}

	/**
	 * @return the twoDDisplaySettingsPane
	 */
	public DisplaySettingsPanel getTwoDDisplaySettingsPane() {
		return twoDDisplaySettingsPane;
	}

	@Override
	public TransformX getTransformX() {
		return null;
	}

	@Override
	public TransformY getTransformY() {
		return null;
	}

	@Override
	public TestFunction getTestFunction() {
		return getTwoDTestFunction();
	}

	public D2Graph getD2Graph() {
		return d2Graph;
	}

	@Override
	public TableCustomModel getData() {
		return getDataModel();
	}

	/**
	 * @return the twoDDatasModel
	 */
	@Override
	public TableCustom2DModel getDataModel() {
		return twoDDatasModel;
	}

	/**
	 * @return the twoDDatasPane
	 */
	public Data2DPanel getTwoDDatasPane() {
		return twoDDatasPane;
	}

	/**
	 * @return the twoDTransformedDatasModel
	 */
	public TableCustom2DModel getTwoDTransformedDatasModel() {
		return twoDTransformedDatasModel;
	}

	/**
	 * @return the twoDTransformedDatasPane
	 */
	public Data2DPanel getTwoDTransformedDatasPane() {
		return twoDTransformedDatasPane;
	}

}
