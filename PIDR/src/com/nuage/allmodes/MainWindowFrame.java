package com.nuage.allmodes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nuage.Nuage;
import com.nuage.allmodes.d3.D3Panel;
import com.nuage.allmodes.line.LinePanel;
import com.nuage.allmodes.lineandd2.D2Panel;
import com.nuage.allmodes.plane.PlanePanel;
import com.nuage.allmodes.toolbar.ToolsPanel;

public class MainWindowFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 2587382723338443456L;
	private static JFrame current;
	private JPanel contentPanel;
	private LinePanel linePanel;
	private PlanePanel planePanel;
	private D2Panel d2Panel;
	private D3Panel d3Panel;
	private ToolsPanel toolsPanel;
	private MainWindow mainWindow;

	/**
	 * @param mainWindow
	 */
	public MainWindowFrame(MainWindow mainWindow) {
		Nuage.setFrame(this);
		this.setMainWindow(mainWindow);
		mainWindow.addObserver(this);

		linePanel = new LinePanel(mainWindow.getLine());
		planePanel = new PlanePanel(mainWindow.getPlane());
		d2Panel = new D2Panel(mainWindow.getD2());
		d3Panel = new D3Panel(mainWindow.getD3());
		toolsPanel = new ToolsPanel(mainWindow.getTools());

		setLayout(new BorderLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;

		JPanel contentPanel = new JPanel(new GridBagLayout());

		mainWindow.getOnglets().addTab("Droites", linePanel);
		mainWindow.getOnglets().addTab("2D", d2Panel);
		mainWindow.getOnglets().addTab("Plans", planePanel);
		mainWindow.getOnglets().addTab("3D", d3Panel);

		// Paramètres fenêtre
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new OnCloseWindowListener());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension((int) (screenSize.width * 0.9), (int) (screenSize.height * 0.9));

		mainWindow.getOnglets().setPreferredSize(frameSize);
		mainWindow.getOnglets().setOpaque(true);

		contentPanel.add(toolsPanel, gbc);

		gbc.gridy += gbc.gridheight;
		gbc.weighty = 1;
		gbc.weightx = 1;
		contentPanel.add(mainWindow.getOnglets(), gbc);

		add(contentPanel); // TODO add un autre gbc
		setPreferredSize(frameSize);
		setTitle("Nuage de points");
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		setVisible(true);
		pack();
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	public static void main(String[] args) {
		new MainWindowFrame(new MainWindow());
	}

	/**
	 * @return
	 */
	public static JFrame getCurrent() {
		return current;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

}
