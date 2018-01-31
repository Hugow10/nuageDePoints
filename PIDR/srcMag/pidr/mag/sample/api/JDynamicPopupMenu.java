package pidr.mag.sample.api;

import javax.swing.JPopupMenu;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public abstract class JDynamicPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -3122166915862764737L;

	/**
	 * Refresh his model
	 */
	public abstract void refresh();

}
