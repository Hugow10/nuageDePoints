package pidr.mag.sample.implementation;

import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import pidr.mag.sample.api.DefaultPopupMenuOpener;
import pidr.mag.sample.api.LayoutRendererConstraints;
import pidr.mag.sample.api.MenuModelHandler;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class FilePopupMenuOpener extends DefaultPopupMenuOpener {

	/**
	 * A Menu with item tha look like button
	 * 
	 * @param model
	 *            the model that will be set
	 */
	public FilePopupMenuOpener(MenuModelHandler model) {
		super(model);
		if (!(model.getCommonConstraint() instanceof LayoutRendererConstraints)) {
			throw new IllegalArgumentException(
					"Can't use a ButtonPopupMenuOpener without a LayoutRendererConstraints in the model");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.DefaultPopupMenuOpener#getItemCount()
	 */
	@Override
	public int getItemCount() {
		return super.getItemCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pidr.mag.sample.api.DefaultPopupMenuOpener#getPopupMenu(java.awt.Dimension)
	 */
	@Override
	public JPopupMenu getPopupMenu(Dimension dim) {
		model.refresh();
		model.getCommonConstraint().setPreferedWidth(dim.width);
		JPopupMenu popup = new JPopupMenu();
		popup.setLayout(((LayoutRendererConstraints) model.getCommonConstraint()).getLayoutManager());
		// On définit le max d'items dans la liste
		int max = ((LayoutRendererConstraints) model.getCommonConstraint()).getMaxItemCount();
		int i = 1;
		model.reinitCommonConstraints();
		for (JMenuItem item : model) {
			Object constraint = ((LayoutRendererConstraints) model.getCommonConstraint())
					.getInferedlayoutConstraintToNextElements();
			popup.add(item, constraint);
			if (++i > max)
				break;
		}
		return popup;
	}

}
