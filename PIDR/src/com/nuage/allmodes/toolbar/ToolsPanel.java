package com.nuage.allmodes.toolbar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ToolsPanel extends JPanel {

	public ToolsPanel(Tools tools) {
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = createGBC();

		createFilePanel(tools);
		add(tools.getFile(), gbc);

		createInfoPanel(tools);

		gbc.gridx++;
		add(tools.getInfos(), gbc);

		createDataPanel(tools);

		gbc.gridx++;
		add(tools.getDatas(), gbc);

		createPrintingPanel(tools);

		gbc.gridx++;
		add(tools.getPrinting(), gbc);

		createExecutingPanel(tools);

		gbc.gridx++;
		add(tools.getExecuting(), gbc);
	}

	private void createExecutingPanel(Tools tools) {
		GridBagConstraints gbc = createGBC();
		tools.getExecuting().add(tools.getExecute(), gbc);
	}

	private void createPrintingPanel(Tools tools) {
		GridBagConstraints gbc = createGBC();
		tools.getPrinting().add(tools.getPrint(), gbc);

		gbc.gridx = 1;
		tools.getPrinting().add(tools.getPreview(), gbc);
	}

	private void createDataPanel(Tools tools) {
		GridBagConstraints gbc = createGBC();
		tools.getDatas().add(tools.getExport(), gbc);

		gbc.gridx = 1;
		tools.getDatas().add(tools.getImportt(), gbc);
	}

	private void createInfoPanel(Tools tools) {
		GridBagConstraints gbc = createGBC();
		tools.getInfos().add(tools.getInfo(), gbc);
	}

	private void createFilePanel(Tools tools) {
		GridBagConstraints gbc = createGBC();

		tools.getFile().add(tools.getClose(), gbc);

		gbc.gridx++;
		tools.getFile().add(tools.getNeww(), gbc);

		gbc.gridx++;
		gbc.ipadx = 120;
		tools.getFile().add(tools.getOpen(), gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		gbc.ipadx = 0;
		tools.getFile().add(tools.getMenuOpener(), gbc);

		gbc.gridx++;
		gbc.weightx = 1;
		tools.getFile().add(tools.getRegister(), gbc);

		gbc.gridx++;
		tools.getFile().add(tools.getRegisterAs(), gbc);
	}

	/**
	 * @return
	 */
	private GridBagConstraints createGBC() {
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		return gbc;
	}

	// main pour test
	public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setSize(1300, 1300);
		Tools tools = new Tools(null);
		ToolsPanel paneltest = new ToolsPanel(tools);
		test.add(paneltest, null);
		test.setVisible(true);
	}

}
