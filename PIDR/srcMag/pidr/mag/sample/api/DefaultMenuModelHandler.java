package pidr.mag.sample.api;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JMenuItem;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class DefaultMenuModelHandler implements MenuModelHandler {

	private static final long serialVersionUID = -4349123431481180287L;
	public final static int STATIC_FIELD_AFTER = 1;
	public final static int STATIC_FIELD_BEFORE = -1;

	private RendererConstraints contraints;
	private LinkedList<SimpleMenuModelItem> graphicamodel = new LinkedList<SimpleMenuModelItem>();
	private ItemRenderer keyrenderer = new DefaultItemRenderer();
	Collection<SimpleMenuModelItem> model = new DefaultMenuModel<SimpleMenuModelItem>();

	private int staticPosition = STATIC_FIELD_AFTER;

	public DefaultMenuModelHandler() {
		super();
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public DefaultMenuModelHandler(Collection<SimpleMenuModelItem> model) {
		this();
		this.model = model;
		refresh();
	}

	/**
	 * 
	 * @param name
	 *            text of the menu Item
	 * @param todoOnClick
	 *            action on Click
	 */
	public void add(String name, ActionListener todoOnClick) {
		model.add(new SimpleMenuModelItem(name, todoOnClick));
	}

	@Override
	public RendererConstraints getCommonConstraint() {
		return contraints;
	}

	/**
	 * Call the renderer to generate an Item , using the CommonConstraint
	 * 
	 * @param key
	 *            the text for the component
	 * @return a new Item for Menu
	 */
	private JMenuItem getItem(String key) {
		return (JMenuItem) keyrenderer.getComponent(key, getCommonConstraint());
	}

	@Override
	public int getItemCount() {
		return graphicamodel.size();
	}

	@Override
	public int getItemMargin() {
		return keyrenderer.getItemMargin();
	}

	/**
	 * @return the ItemRenderer
	 */
	@Override
	public ItemRenderer getItemRenderer() {
		return keyrenderer;
	}

	@Override
	public int getPreferedItemHeight() {
		return keyrenderer.getPreferedItemHeight();
	}

	/**
	 * @return the staticPosition
	 */
	public int getStaticPosition() {
		return staticPosition;
	}

	@Override
	public Iterator<JMenuItem> iterator() {
		refresh();
		return new Iterator<JMenuItem>() {
			Iterator<?> iterator = graphicamodel.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public JMenuItem next() {
				SimpleMenuModelItem key = (SimpleMenuModelItem) iterator.next();
				JMenuItem menu = getItem(key.getName());
				menu.addActionListener(key.getAction());
				return menu;
			}
		};
	}

	/**
	 * sychronized the accessible model with the graphical one (used by Menu)
	 */
	@Override
	public synchronized void refresh() {
		graphicamodel.clear();
		graphicamodel.addAll(model);
	}

	@Override
	public void reinitCommonConstraints() {
		contraints.reinit();
	}

	@Override
	public void setCommonConstraint(RendererConstraints constraints) {
		this.contraints = constraints;
	}

	@Override
	public void setItemMargin(int preferedHeight) {
		keyrenderer.setItemMargin(preferedHeight);
	}

	/**
	 * @param keyrenderer
	 *            the ItemRenderer to set
	 */
	@Override
	public void setItemRenderer(ItemRenderer keyrenderer) {
		this.keyrenderer = keyrenderer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pidr.mag.sample.api.MenuOpenerModelHandler#setModel(java.util.Collection)
	 */
	@Override
	public synchronized void setModel(Collection<SimpleMenuModelItem> c) {
		this.model = c;
	}

	@Override
	public void setPreferedItemHeight(int preferedHeight) {
		keyrenderer.setPreferedItemHeight(preferedHeight);
	}

	/**
	 * @param staticPosition
	 *            the staticPosition to set
	 */
	public void setStaticPosition(int staticPosition) {
		this.staticPosition = staticPosition;
	}

	public Object[] toArray() {
		return model.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return model.toArray(a);
	}

	@Override
	public String toString() {
		return model.toString();
	}

}
