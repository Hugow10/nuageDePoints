package pidr.mag.sample.api;

import java.io.Serializable;
import java.util.Collection;

import javax.swing.JMenuItem;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public interface MenuModelHandler extends Iterable<JMenuItem>, Serializable {

	/**
	 * @return the {@link RendererConstraints} Object used as parameter for the
	 *         {@link ItemRenderer}
	 */
	public RendererConstraints getCommonConstraint();

	/**
	 * @return the number of Element available in the model
	 */
	public int getItemCount();

	/**
	 * @return the margin for Menu Item
	 */
	public int getItemMargin();

	/**
	 * @return the {@link ItemRenderer} that generate the graphical component in the
	 *         popup
	 */
	public ItemRenderer getItemRenderer();

	/**
	 * @return the preferred Height for Menu Item
	 */
	public int getPreferedItemHeight();

	/**
	 * Refresh graphical Model
	 */
	public void refresh();

	/**
	 * Reinitialize the {@link RendererConstraints} (used before diplay a new
	 * {@link JDynamicPopupMenu})
	 */
	public void reinitCommonConstraints();

	/**
	 * set the {@link RendererConstraints} Object used as parameter for the
	 * {@link ItemRenderer}
	 * 
	 * @param constraints
	 *            the constrains to set
	 */
	public void setCommonConstraint(RendererConstraints constraints);

	/**
	 * set the margin for Menu Item
	 * 
	 * @param margin
	 *            the margin to set
	 */
	public void setItemMargin(int margin);

	/**
	 * set the {@link ItemRenderer} that generate the graphical component in the
	 * popup
	 * 
	 * @param itemrenderer
	 *            the {@link ItemRenderer} to set
	 */
	public void setItemRenderer(ItemRenderer itemrenderer);

	public void setModel(Collection<SimpleMenuModelItem> c);

	/**
	 * set the preferred Height for Menu Item
	 * 
	 * @param preferedHeight
	 *            the heigth to set
	 */
	public void setPreferedItemHeight(int preferedHeight);

}