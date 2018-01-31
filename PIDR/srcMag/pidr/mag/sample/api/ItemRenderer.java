package pidr.mag.sample.api;

import javax.swing.JComponent;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public interface ItemRenderer {

	/**
	 * 
	 * @param text
	 *            the text to be display in the component
	 * @param constraint
	 *            the {@link RendererConstraints} that contain s the element for the
	 *            renderer can be extended if you need but check that your
	 *            {@link ItemRenderer} support the extension
	 * @return the component that should be display in the {@link JDynamicPopupMenu}
	 */
	public JComponent getComponent(String text, RendererConstraints constraint);

	/**
	 * @return the item Margin most of the time 0
	 */
	public int getItemMargin();

	/**
	 * @return the preferred height of the generated component most of the time
	 *         static for an {@link ItemRenderer}
	 */
	public int getPreferedItemHeight();

	/**
	 * @param margin
	 *            set the item margin used for Popup Menu size computation
	 */
	public void setItemMargin(int margin);

	/**
	 * set the preferred height of generated {@link JComponent}
	 * 
	 * @param preferedHeight
	 *            the height
	 */
	public void setPreferedItemHeight(int preferedHeight);

}
