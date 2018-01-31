package pidr.mag.sample.api;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class MenuOpenerButton extends JButton {

	private static final long serialVersionUID = 4209400827947770591L;
	private JComponent container;
	private DefaultPopupMenuOpener customPopupMenu;
	private JPopupMenu lastpopup;
	private ActionListener openAction;

	/**
	 * Construct a {@link MenuOpenerButton} using a {@link DefaultPopupMenuOpener}
	 * or any clas that extends
	 * 
	 * @param popupMenuOpener
	 *            the PopupMenuOpener (containing the model)
	 * @param parent
	 *            the parent behind the popup will be display
	 */
	public MenuOpenerButton(DefaultPopupMenuOpener popupMenuOpener, JComponent parent) {
		super();
		setIcon(new ImageIcon(this.getClass().getResource("icon.png")));
		if (parent == null)
			container = this;
		this.container = parent;
		setPopupMenuOpener(popupMenuOpener);
		openAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPopupMenuVisible(true);
			}
		};
		super.addActionListener(openAction);
	}

	/**
	 * Construct a {@link MenuOpenerButton} using the {@link DefaultPopupMenuOpener}
	 * 
	 * @param model
	 *            the model used in the {@link DefaultPopupMenuOpener}
	 * @param parent
	 *            the parent behind the popup will be display
	 */
	public MenuOpenerButton(MenuModelHandler model, JComponent parent) {
		this(new DefaultPopupMenuOpener(model), parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.AbstractButton#addActionListener(java.awt.event.ActionListener)
	 */
	@Override
	public void addActionListener(ActionListener l) {
		super.removeActionListener(openAction);
		super.addActionListener(l);
		super.addActionListener(openAction);
	}

	/**
	 * @param dimension
	 *            the {@link Dimension} used to update the
	 *            {@link RendererConstraints}
	 * @return the {@link JPopupMenu} to be display
	 */
	public JPopupMenu getPopupMenu(Dimension dimension) {
		return customPopupMenu.getPopupMenu(dimension);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractButton#removeActionListener(java.awt.event.
	 * ActionListener)
	 */
	@Override
	public void removeActionListener(ActionListener l) {
		if (!l.equals(openAction))
			super.removeActionListener(l);
	}

	/**
	 * set the PopupMenuOpener
	 * 
	 * @param popupMenuOpener
	 *            the PopupMenuOpener (containing the model)
	 */
	public void setPopupMenuOpener(DefaultPopupMenuOpener popupMenuOpener) {

		this.customPopupMenu = popupMenuOpener;

	}

	/**
	 * Sets the visibility of the popup menu.
	 * 
	 * @param b
	 *            true to make the popup visible, or false to hide it
	 */
	public void setPopupMenuVisible(boolean b) {
		if (b) {
			Rectangle pos = container.getBounds();
			lastpopup = getPopupMenu(new Dimension(getBounds().width + pos.width, customPopupMenu.getPreferedHeight()));
			lastpopup.show(container, pos.x, pos.y + pos.height);
		} else
			lastpopup.setVisible(false);
	}
}
