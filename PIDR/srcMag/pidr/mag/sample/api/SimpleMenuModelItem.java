/**
 * 
 */
package pidr.mag.sample.api;

import java.awt.event.ActionListener;

/**
 * @author Arnault Le Prévost-Corvellec
 *
 */

public class SimpleMenuModelItem {

	private ActionListener action;

	private String name;

	/**
	 * Simply Comsctuct the object
	 * 
	 * @param name
	 *            the text to display
	 * @param todoOnClick
	 *            the action linked to the menu item
	 */
	public SimpleMenuModelItem(String name, ActionListener todoOnClick) {
		this.name = name;
		this.action = todoOnClick;
	}

	/**
	 * @return the action linked to the menu item
	 */
	public ActionListener getAction() {
		return action;
	}

	/**
	 * @return the text to display
	 */
	public String getName() {
		return name;
	}

}