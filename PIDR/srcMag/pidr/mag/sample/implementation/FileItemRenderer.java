package pidr.mag.sample.implementation;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;

import pidr.mag.sample.api.ItemRenderer;
import pidr.mag.sample.api.RendererConstraints;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class FileItemRenderer implements ItemRenderer {
	private static String concatSeparator;
	private static String splitSeparator;
	private static final String SUBSTITUTE = "...";
	private int margin = 0;
	private int preferedHeight = 40;
	private ButtonUI ui;

	/**
	 * Initialised the file renderer depending of OS and LookandFeel
	 * 
	 */
	public FileItemRenderer() {
		if (File.pathSeparatorChar == ';') {// on windows
			splitSeparator = "\\\\";
			concatSeparator = "\\";
		} else { // on unix
			splitSeparator = "/";
			concatSeparator = "/";
		}
		ui = ((ButtonUI) UIManager.getUI(new JButton()));
	}

	private void format(JMenuItem key, RendererConstraints constraint) {
		if (constraint != null) {
			try {
				String s = key.getText();
				// on suppose que les path sont correct pour les test il faut commenter les 2
				// ligne qui suivent
				File f = new File(s);
				s = f.getAbsolutePath();
				FontMetrics fm = key.getFontMetrics(key.getFont());
				int maxLineSize = constraint.getPreferedWidth() - 4; // on se laisse 5 pixel de marge
				String[] files = s.split(splitSeparator);
				while (fm.stringWidth(s) > maxLineSize && files.length > 5) { // a partir de la on va essayer de reduire
																				// la taille du path
					s = reduceOne(files);
					// System.out.println(s);
					files = s.split(splitSeparator);
				}
				key.setText(s);
			} catch (Exception e) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#getComponent(java.lang.String,
	 * pidr.mag.sample.api.RendererConstraints)
	 */
	@Override
	public JComponent getComponent(String key, RendererConstraints constraint) {
		JMenuItem item = new JMenuItem(key);
		item.setUI(this.ui);
		item.addPropertyChangeListener("preferredSize", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				format(item, constraint);
			}
		});
		int width = 100;
		item.setMargin(new Insets(2, 2, 2, 2));
		if (constraint != null) {
			width = constraint.getPreferedWidth();
		}
		item.setPreferredSize(new Dimension(width, getPreferedItemHeight()));
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#getItemMargin()
	 */
	@Override
	public int getItemMargin() {
		return margin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#getPreferedItemHeight()
	 */
	@Override
	public int getPreferedItemHeight() {
		return preferedHeight;
	}

	private String merge(String[] files) {
		StringBuilder sb = new StringBuilder();
		for (String s : files) {
			if (s != null)
				sb.append(concatSeparator + s);
		}
		if (File.pathSeparatorChar == ';') {// on windows
			return sb.substring(1);
		} else { // on unix
			return sb.toString();
		}

	}

	private String reduceOne(String[] files) {
		int index = (files.length - 1) / 2;
		if (files[index].equals(SUBSTITUTE)) {
			for (int i = index + 2; i < files.length; i++) {
				files[i - 1] = files[i];
			}
			files[files.length - 1] = null;
		} else {
			if (files[index + 1].equals(SUBSTITUTE)) {
				for (int i = index + 1; i < files.length; i++) {
					files[i - 1] = files[i];
				}
				files[files.length - 1] = null;
			} else
				files[index] = SUBSTITUTE;
		}
		return merge(files);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#setItemMargin(int)
	 */
	@Override
	public void setItemMargin(int margin) {
		this.margin = margin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#setPreferedItemHeight(int)
	 */
	@Override
	public void setPreferedItemHeight(int preferedHeight) {
		this.preferedHeight = preferedHeight;
	}

}
