package pidr.mag.sample.api;

import java.awt.LayoutManager;
import java.lang.reflect.Constructor;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public abstract class LayoutRendererConstraints extends RendererConstraints {

	private Constructor<?> layout;

	/**
	 * construct a {@link LayoutRendererConstraints}, that has the same feature than
	 * a {@link RendererConstraints} except that he can get a layout manager for the
	 * Popup Menu
	 * 
	 * @param preferedheight
	 *            the preferred height of element inherit
	 *            {@link RendererConstraints}
	 * @param layout
	 *            the class of the {@link LayoutManager} it will construct by his
	 *            default constructor any {@link LayoutManager} that didn't satisfy
	 *            this condition will throw an {@link IllegalArgumentException}
	 * 
	 */
	public LayoutRendererConstraints(int preferedheight, Class<?> layout) {
		super(preferedheight);
		setLayoutManager(layout);
	}

	/**
	 * Update the constraint used in the layout manager for the next element
	 * 
	 * @return the updated Constraints
	 */
	public abstract Object getInferedlayoutConstraintToNextElements();

	/**
	 * @return the layout Manager that will be used to display element in the Menu
	 */

	public LayoutManager getLayoutManager() {
		try {
			return (LayoutManager) layout.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("The select layout have no default constructor : " + layout);
		}
	}

	/**
	 * set the layout Manager that will be used to display element in the Menu
	 * 
	 * @param layout
	 *            the class of the {@link LayoutManager} it will construct by his
	 *            default constructor any {@link LayoutManager} that didn't satisfy
	 *            this condition will throw an {@link IllegalArgumentException}
	 */

	public void setLayoutManager(Class<?> layout) {
		try {
			layout.getConstructor().newInstance();
			this.layout = layout.getConstructor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
