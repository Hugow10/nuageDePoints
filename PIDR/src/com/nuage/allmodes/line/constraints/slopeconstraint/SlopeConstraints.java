package com.nuage.allmodes.line.constraints.slopeconstraint;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class SlopeConstraints {

	private JTextField slope;
	private JLabel slopeLabel;

	public SlopeConstraints() {
		slope = new JTextField(8);
		slopeLabel = new JLabel("Pente = ");
	}

	public JLabel getSlopeLabel() {
		return slopeLabel;
	}

	/**
	 * @return the slope
	 */
	public JTextField getSlope() {
		return slope;
	}

	public void setSlopeText(String slope) {
		this.slope.setText(slope);
	}
}
