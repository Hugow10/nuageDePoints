package pidr.mag.sample.api;

import javax.swing.JComponent;
import javax.swing.JMenuItem;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class DefaultItemRenderer implements ItemRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#getComponent(java.lang.String,
	 * pidr.mag.sample.api.RendererConstraints)
	 */
	@Override
	public JComponent getComponent(String key, RendererConstraints constrain) {
		JMenuItem item = new JMenuItem(key);
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#getItemMargin()
	 */
	@Override
	public int getItemMargin() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#getPreferedItemHeight()
	 */
	@Override
	public int getPreferedItemHeight() {
		return new JMenuItem("Non Empty Item").getPreferredSize().height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#setItemMargin(int)
	 */
	@Override
	public void setItemMargin(int preferedHeight) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.ItemRenderer#setPreferedItemHeight(int)
	 */
	@Override
	public void setPreferedItemHeight(int preferedHeight) {

	}
}
