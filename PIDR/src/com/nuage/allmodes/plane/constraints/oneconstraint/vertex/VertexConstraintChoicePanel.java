package com.nuage.allmodes.plane.constraints.oneconstraint.vertex;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class VertexConstraintChoicePanel extends JPanel {

	private CardLayout cl;
	private JPanel vertexChoice;
	private JPanel vertexDatas;
	private ButtonGroup bG;
	private ButtonModel bm;

	public VertexConstraintChoicePanel(VertexConstraintChoice vertexConstraintChoice) {
		super(new GridLayout(1, 2));
		bG = new ButtonGroup();
		cl = new CardLayout();

		bG.add(vertexConstraintChoice.getVertexIJ());
		bG.add(vertexConstraintChoice.getVertexKI());
		bG.add(vertexConstraintChoice.getVertexJK());

		vertexConstraintChoice.getVertexIJ().setSelected(true);

		vertexChoice = new JPanel(new GridLayout(3, 1));
		vertexDatas = new JPanel(cl);

		bm = bG.getSelection();

		vertexChoice.add(vertexConstraintChoice.getVertexIJ(), "Vij");
		vertexChoice.add(vertexConstraintChoice.getVertexKI(), "Vki");
		vertexChoice.add(vertexConstraintChoice.getVertexJK(), "Vjk");

		addCustomListener(vertexConstraintChoice.getVertexIJ(), vertexConstraintChoice);
		addCustomListener(vertexConstraintChoice.getVertexKI(), vertexConstraintChoice);
		addCustomListener(vertexConstraintChoice.getVertexJK(), vertexConstraintChoice);

		vertexDatas.add(vertexConstraintChoice.getVertexVij(), "Vij");
		vertexDatas.add(vertexConstraintChoice.getVertexVki(), "Vki");
		vertexDatas.add(vertexConstraintChoice.getVertexVjk(), "Vjk");

		this.add(vertexChoice);
		this.add(vertexDatas);
	}

	public void addCustomListener(JRadioButton bouton, VertexConstraintChoice vertexConstraintChoice) {
		bouton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (!bm.equals(bG.getSelection())) {
					bm = bG.getSelection();
					if (bm.equals(vertexConstraintChoice.getVertexIJ().getModel())) {
						cl.show(vertexDatas, "Vij");
					}
					if (bm.equals(vertexConstraintChoice.getVertexKI().getModel())) {
						cl.show(vertexDatas, "Vki");
					}
					if (bm.equals(vertexConstraintChoice.getVertexJK().getModel())) {
						cl.show(vertexDatas, "Vjk");
					}
				}

			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("ConstraintsPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VertexConstraintChoice vertexConstraintChoice = new VertexConstraintChoice();
		VertexConstraintChoicePanel newContentPane = new VertexConstraintChoicePanel(vertexConstraintChoice);
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
}
