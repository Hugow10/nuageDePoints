package pidr.mag.sample.implementation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import pidr.mag.sample.api.DefaultMenuModelHandler;
import pidr.mag.sample.api.MenuOpenerButton;
import pidr.mag.sample.api.RotativeMenuModel;
import pidr.mag.sample.api.SimpleMenuModelItem;

/**
 * 
 * @author Arnault Le Prévost-Corvellec
 *
 */
public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6847562764832334122L;

	public static void main(String[] args) {
		new Frame();
	}

	int itemnb = 5;

	private DefaultMenuModelHandler model;

	public Frame() {
		super("this s a simpe test");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JButton mainbutton = new JButton("ajouter un item");
		mainbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = "item " + itemnb++;
				model.add(msg, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(msg);
					}
				});
			}
		});
		add(mainbutton, BorderLayout.CENTER);

		// TODO normalement il n'y a que ces 6 lignes qui devrai vraiment t'interesser
		// et ce qui en decoule
		model = new DefaultMenuModelHandler();
		model.setModel(new RotativeMenuModel<SimpleMenuModelItem>(model));
		model.setCommonConstraint(new FileItemRendererConstraint(30, 7));
		model.setItemRenderer(new FileItemRenderer());
		generateInitialModel();
		MenuOpenerButton menuOpener = new MenuOpenerButton(new FilePopupMenuOpener(model), mainbutton);

		add(menuOpener, BorderLayout.EAST);
		this.setPreferredSize(new Dimension(500, 100));
		this.pack();
		this.setVisible(true);
	}

	private void generateInitialModel() {

		model.add("DefaultItemRenderer.java", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("item 1");
			}
		});
		model.add("C:\\Users\\arnau\\workspace\\MaG\\src\\pidr\\mag\\sample\\Frame.java", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("item 2");
			}
		});
		model.add(
				"C:\\Users\\arnau\\workspace\\MaG\\src\\pidr\\mag\\sample\\implementation\\FileItemRendererConstraint.java",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("item 3");
					}
				});
		model.add("C:\\Users\\arnau\\workspace\\MaG\\src\\pidr\\mag\\sample\\TestFile.java", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("item 4");
			}
		});

	}
}
