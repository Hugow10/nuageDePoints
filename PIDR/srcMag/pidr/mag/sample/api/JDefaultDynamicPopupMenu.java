package pidr.mag.sample.api;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class JDefaultDynamicPopupMenu extends JDynamicPopupMenu {

	private static final long serialVersionUID = -3450684182985245483L;
	private MenuModelHandler model;

	/**
	 * Create a {@link JDefaultDynamicPopupMenu}
	 * 
	 * @param model
	 *            the used model
	 */
	public JDefaultDynamicPopupMenu(MenuModelHandler model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pidr.mag.sample.api.JDynamicPopupMenu#refresh()
	 */
	@Override
	public void refresh() {
		model.refresh();
	}

}
