/**
 * 
 */
package pidr.mag.sample.api;

import java.util.LinkedList;

/**
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class RotativeMenuModel<T> extends LinkedList<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7093635342046457932L;
	private MenuModelHandler handler;

	/**
	 * 
	 * @param handler
	 *            the handler that contains
	 */
	public RotativeMenuModel(MenuModelHandler handler) {
		super();
		this.handler = handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.LinkedList#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		super.addFirst(e);
		if (size() > handler.getCommonConstraint().getMaxItemCount())
			super.removeLast();
		return true;
	}

	/**
	 * remove all superflu elements
	 */
	public void trim() {
		while (size() > handler.getCommonConstraint().getMaxItemCount()) {
			pollLast();
		}
	}
}
