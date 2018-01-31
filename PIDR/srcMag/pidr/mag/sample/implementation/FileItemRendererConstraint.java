package pidr.mag.sample.implementation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import pidr.mag.sample.api.LayoutRendererConstraints;
import pidr.mag.sample.api.RendererConstraints;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class FileItemRendererConstraint extends LayoutRendererConstraints {

	private GridBagConstraints c;

	/**
	 * construct a {@link LayoutRendererConstraints} with a {@link GridBagLayout}
	 * and no limit size
	 * 
	 * @param preferedheight
	 *            the preferred height of element inherit
	 *            {@link RendererConstraints}
	 */
	public FileItemRendererConstraint(int preferedheight) {
		this(preferedheight, Integer.MAX_VALUE);
	}

	/**
	 * construct a {@link LayoutRendererConstraints} with a {@link GridBagLayout}
	 * 
	 * @param preferedheight
	 *            the preferred height of element inherit
	 *            {@link RendererConstraints}
	 * @param maxItemCount
	 *            the number of displayed Item
	 * 
	 */
	public FileItemRendererConstraint(int preferedheight, int maxItemCount) {
		super(preferedheight, GridBagLayout.class);
		setMaxItemCount(maxItemCount);
		this.setGridbagConstraints(new GridBagConstraints());
		reinit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.LayoutRendererConstraints#
	 * getInferedlayoutConstraintToNextElements()
	 */
	@Override
	public Object getInferedlayoutConstraintToNextElements() {
		this.c.gridy++;
		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.RendererConstraints#reinit()
	 */
	@Override
	public void reinit() {
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridheight = 1;
		c.gridwidth = 1;
	}

	/**
	 * Set the {@link GridBagConstraints}
	 * 
	 * @param c
	 *            the {@link GridBagConstraints} to set
	 */
	public void setGridbagConstraints(GridBagConstraints c) {
		this.c = c;
	}

}
