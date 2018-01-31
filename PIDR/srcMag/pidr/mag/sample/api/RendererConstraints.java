package pidr.mag.sample.api;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public abstract class RendererConstraints {
	private int maxItemCount = Integer.MAX_VALUE;
	private int preferedWidth;

	/**
	 * AbsrtactConstructor of the {@link RendererConstraints}
	 * 
	 * @param preferedWidth
	 *            the prefered size of the item(used by {@link ItemRenderer})
	 */
	public RendererConstraints(int preferedWidth) {
		this.preferedWidth = preferedWidth;
	}

	/**
	 * @return the number of Elements that should be almost diplay
	 */
	public int getMaxItemCount() {
		return maxItemCount;
	}

	/**
	 * @return the preferred width that will be set to MenuItem
	 */
	public int getPreferedWidth() {
		return preferedWidth;
	}

	/**
	 * Reinitialize if necessary the constraints present in this class
	 */
	public abstract void reinit();

	/**
	 * set the number of Elements that should be almost diplay
	 * 
	 * @param maxItemCount
	 *            number of Element
	 */
	public void setMaxItemCount(int maxItemCount) {
		this.maxItemCount = maxItemCount;
	}

	/**
	 * set the preferred width that will be set to MenuItem
	 * 
	 * @param preferedWidth
	 *            the width
	 */
	public void setPreferedWidth(int preferedWidth) {
		this.preferedWidth = preferedWidth;
	}

}
