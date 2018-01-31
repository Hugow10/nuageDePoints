package com.nuage.allmodes.calculator;

import java.util.Observable;

import javax.swing.JButton;

import com.nuage.allmodes.d2andd3.CalculatorListener;
import com.nuage.allmodes.d2andd3.TestFunction;

public class MathShortcuts extends Observable {

	private JButton sin;
	private JButton cos;
	private JButton tan;
	private JButton sinh;
	private JButton cosh;
	private JButton tanh;
	private JButton asin;
	private JButton acos;
	private JButton atan;
	private JButton asinh;
	private JButton acosh;
	private JButton atanh;
	private JButton abs;
	private JButton pi;
	private JButton pow;
	private JButton log10;
	private JButton ln;
	private JButton exp;
	private JButton fact;
	private JButton inv;
	private JButton sqrt;
	private JButton integ;
	private TestFunction function;

	public MathShortcuts(TestFunction function) {
		this.function = function;

		sin = new JButton("sin");
		sin.addActionListener(new CalculatorListener(this, ""));

		cos = new JButton("cos");
		cos.addActionListener(new CalculatorListener(this, ""));

		tan = new JButton("tan");
		tan.addActionListener(new CalculatorListener(this, ""));

		sinh = new JButton("sinh");
		sinh.addActionListener(new CalculatorListener(this, ""));

		cosh = new JButton("cosh");
		cosh.addActionListener(new CalculatorListener(this, ""));

		tanh = new JButton("tanh");
		tanh.addActionListener(new CalculatorListener(this, ""));

		asin = new JButton("asin");
		asin.addActionListener(new CalculatorListener(this, ""));

		acos = new JButton("acos");
		acos.addActionListener(new CalculatorListener(this, ""));

		atan = new JButton("atan");
		atan.addActionListener(new CalculatorListener(this, ""));

		asinh = new JButton("asinh");
		asinh.addActionListener(new CalculatorListener(this, ""));

		acosh = new JButton("acosh");
		acosh.addActionListener(new CalculatorListener(this, ""));

		atanh = new JButton("atanh");
		atanh.addActionListener(new CalculatorListener(this, ""));

		abs = new JButton("abs");
		abs.addActionListener(new CalculatorListener(this, ""));

		pi = new JButton("pi");
		pi.addActionListener(new CalculatorListener(this, ""));

		pow = new JButton("^");
		pow.addActionListener(new CalculatorListener(this, ""));

		log10 = new JButton("log10");
		log10.addActionListener(new CalculatorListener(this, ""));

		ln = new JButton("ln");
		ln.addActionListener(new CalculatorListener(this, ""));

		exp = new JButton("exp");
		exp.addActionListener(new CalculatorListener(this, ""));

		fact = new JButton("!");
		fact.addActionListener(new CalculatorListener(this, "factorial"));

		inv = new JButton("1/f");
		inv.addActionListener(new CalculatorListener(this, "1/"));

		sqrt = new JButton("sqrt");
		sqrt.addActionListener(new CalculatorListener(this, ""));

		integ = new JButton("Ent.");
		integ.addActionListener(new CalculatorListener(this, ""));
	}

	public void updatePhiJ(String content) {
		function.warnMainWindowFrame(content);
	}

	public JButton getSin() {
		return sin;
	}

	public JButton getCos() {
		return cos;
	}

	public JButton getTan() {
		return tan;
	}

	public JButton getSinh() {
		return sinh;
	}

	public JButton getCosh() {
		return cosh;
	}

	public JButton getTanh() {
		return tanh;
	}

	public JButton getAsin() {
		return asin;
	}

	public JButton getAcos() {
		return acos;
	}

	public JButton getAtan() {
		return atan;
	}

	public JButton getAsinh() {
		return asinh;
	}

	public JButton getAcosh() {
		return acosh;
	}

	public JButton getAtanh() {
		return atanh;
	}

	public JButton getAbs() {
		return abs;
	}

	public JButton getPi() {
		return pi;
	}

	public JButton getPow() {
		return pow;
	}

	public JButton getLog10() {
		return log10;
	}

	public JButton getLn() {
		return ln;
	}

	public JButton getExp() {
		return exp;
	}

	public JButton getFact() {
		return fact;
	}

	public JButton getInv() {
		return inv;
	}

	public JButton getSqrt() {
		return sqrt;
	}

	public JButton getInteg() {
		return integ;
	}

}
