package com.nuage.allmodes.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nuage.allmodes.MainWindow;
import com.nuage.fileschoosers.excelfiles.ImportFileChooser;

import pidr.mag.sample.api.DefaultMenuModelHandler;
import pidr.mag.sample.api.MenuOpenerButton;
import pidr.mag.sample.api.RotativeMenuModel;
import pidr.mag.sample.api.SimpleMenuModelItem;
import pidr.mag.sample.implementation.FileItemRenderer;
import pidr.mag.sample.implementation.FileItemRendererConstraint;
import pidr.mag.sample.implementation.FilePopupMenuOpener;

public class Tools extends Observable {

	private JButton close;
	private JButton neww;
	private JButton openButton;
	private JButton register;
	private JButton registerAs;
	private JButton info;
	private JButton export;
	private JButton importt;
	private JButton print;
	private JButton preview;
	private JButton execute;
	private JButton open;
	private JPanel file;
	private JPanel infos;
	private JPanel datas;
	private JPanel printing;
	private JPanel executing;
	private JLabel nameFile;
	private JLabel nameInfos;
	private JLabel nameDatas;
	private JLabel namePrinting;
	private JLabel nameExecuting;
	private String lastOpened[];
	private DefaultMenuModelHandler openModel;
	private MenuOpenerButton menuOpener;
	private ImportFileChooser importFileChooser;
	private MainWindow mainWindow;

	public Tools(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

		close = new JButton("Fermer");
		close.addActionListener(new CloseButtonListener());

		neww = new JButton("Nouveau");
		neww.addActionListener(new NewButtonListener(this));

		lastOpened = new String[6];
		open = new JButton("Ouvrir");
		open.addActionListener(new OpenButtonListener(this));
		openModel = new DefaultMenuModelHandler();
		openModel.setModel(new RotativeMenuModel<SimpleMenuModelItem>(openModel));
		openModel.setCommonConstraint(new FileItemRendererConstraint(20, 7)); // TODO
																				// :
																				// Check
																				// size
		openModel.setItemRenderer(new FileItemRenderer());
		generateInitialModel();
		menuOpener = new MenuOpenerButton(new FilePopupMenuOpener(openModel), open);

		register = new JButton("Enregistrer");
		register.addActionListener(new SaveButtonListener(this));

		registerAs = new JButton("Enregistrer Sous...");
		registerAs.addActionListener(new SaveAsButtonListener(this));

		info = new JButton("Infos");
		info.addActionListener(new InfosButtonListener(this));

		export = new JButton("Exporter");
		export.addActionListener(new ExecuteButtonListener(this));

		importt = new JButton("Importer");
		importt.addActionListener(new ImportButtonListener(this));

		print = new JButton("Imprimer");
		print.addActionListener(new PrintButtonListener(this));

		preview = new JButton("Prévisualiser");
		preview.addActionListener(new PreviewButtonListener(this));

		execute = new JButton("Exécuter");
		execute.addActionListener(new ExecuteButtonListener(this));

		infos = new JPanel();

		file = new JPanel();

		datas = new JPanel();

		printing = new JPanel();

		executing = new JPanel();

		nameFile = new JLabel("Fichier");

		nameInfos = new JLabel("Info.");

		nameDatas = new JLabel("Données");

		namePrinting = new JLabel("Impression");

		nameExecuting = new JLabel("Exécution");

		importFileChooser = new ImportFileChooser();

	}

	private void generateInitialModel() {
		// TODO Récupérer l'historique des fichiers ouverts récemment
		openModel.add("DefaultItemRenderer.java", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("item 1");
			}
		});
		openModel.add("C:\\Users\\arnau\\workspace\\MaG\\src\\pidr\\mag\\sample\\Frame.java", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("item 2");
			}
		});
		openModel.add(
				"C:\\Users\\arnau\\workspace\\MaG\\src\\pidr\\mag\\sample\\implementation\\FileItemRendererConstraint.java",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("item 3");
					}
				});
		openModel.add("C:\\Users\\arnau\\workspace\\MaG\\src\\pidr\\mag\\sample\\TestFile.java", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("item 4");
			}
		});
	}

	public JButton getClose() {
		return close;
	}

	public JPanel getDatas() {
		return datas;
	}

	public JButton getExecute() {
		return execute;
	}

	public JPanel getExecuting() {
		return executing;
	}

	public JButton getExport() {
		return export;
	}

	public JPanel getFile() {
		return file;
	}

	public JButton getImportt() {
		return importt;
	}

	public JButton getInfo() {
		return info;
	}

	public JPanel getInfos() {
		return infos;
	}

	public String[] getLastOpened() {
		return lastOpened;
	}

	/**
	 * @return the menuOpener
	 */
	public MenuOpenerButton getMenuOpener() {
		return menuOpener;
	}

	public JLabel getNameDatas() {
		return nameDatas;
	}

	public JLabel getNameExecuting() {
		return nameExecuting;
	}

	public JLabel getNameFile() {
		return nameFile;
	}

	public JLabel getNameInfos() {
		return nameInfos;
	}

	public JLabel getNamePrinting() {
		return namePrinting;
	}

	public JButton getNeww() {
		return neww;
	}

	public JButton getOpen() {
		return open;
	}

	public JButton getOpenButton() {
		return openButton;
	}

	/**
	 * @return the openModel
	 */
	public DefaultMenuModelHandler getOpenModel() {
		return openModel;
	}

	public JButton getPreview() {
		return preview;
	}

	public JButton getPrint() {
		return print;
	}

	public JPanel getPrinting() {
		return printing;
	}

	public JButton getRegister() {
		return register;
	}

	public JButton getRegisterAs() {
		return registerAs;
	}

	public void setFile(JPanel file) {
		this.file = file;
	}

	/**
	 * @param menuOpener
	 *            the menuOpener to set
	 */
	public void setMenuOpener(MenuOpenerButton menuOpener) {
		this.menuOpener = menuOpener;
	}

	/**
	 * @param openModel
	 *            the openModel to set
	 */
	public void setOpenModel(DefaultMenuModelHandler openModel) {
		this.openModel = openModel;
	}

	/**
	 * @return the importFileChooser
	 */
	public ImportFileChooser getImportFileChooser() {
		return importFileChooser;
	}

	/**
	 * @return the mainWindow
	 */
	public MainWindow getMainWindow() {
		return mainWindow;
	}

}
