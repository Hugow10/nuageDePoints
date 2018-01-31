package pidr.mag.sample.api;

import java.awt.Dimension;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class DefaultPopupMenuOpener {

	protected MenuModelHandler model;

	/**
	 * Construct a {@link DefaultPopupMenuOpener} with a {@link MenuModelHandler}
	 * 
	 * @param model
	 *            the {@link MenuModelHandler} that will be set
	 */
	public DefaultPopupMenuOpener(MenuModelHandler model) {
		super();
		if (model == null)
			model = new DefaultMenuModelHandler();
		this.model = model;
	}

	/**
	 * @return the number of Elements that should be almost diplay
	 */
	public int getItemCount() {
		return Math.min(model.getItemCount(), model.getCommonConstraint().getMaxItemCount());
	}

	/**
	 * @return the item Margin most of the time 0
	 */
	private int getItemMargin() {
		return model.getItemMargin();
	}

	/**
	 * @param dimension
	 *            the {@link Dimension} used to update the
	 *            {@link RendererConstraints}
	 * @return the {@link JPopupMenu} to be display
	 */
	public JPopupMenu getPopupMenu(Dimension dimension) {
		model.refresh();
		model.getCommonConstraint().setPreferedWidth(dimension.width);
		JPopupMenu popup = new JPopupMenu();
		for (JMenuItem item : model) {
			popup.add(item);
		}
		return popup;
	}

	/**
	 * Compute the size of the popup menu using ItemCount, PreferedItemHeightand
	 * ItemMargin
	 * 
	 * @return the height of the popup menu
	 */
	public int getPreferedHeight() {
		return getItemCount() * (getPreferedItemHeight() + getItemMargin());
	}

	/**
	 * @return the prefered Height available in constraints in model.
	 */
	public int getPreferedItemHeight() {
		return model.getPreferedItemHeight();
	}
}