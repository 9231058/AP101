package home.Seyed.windowscalculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author SMA74
 */
public class Frame extends JFrame {

	private JButton[] buts;
	private JTextField textfield;
	private JButton AC;

	//Constructor
	public Frame(){
		super("Calculator");

		JPanel panel = new JPanel();

		buts = new JButton[16];
		AC = new JButton("AC");

		textfield = new JTextField(10);
//        textfield.setEditable(false);

		panel.setLayout(new GridLayout(4, 4, 2, 2));

		buts[0] = new JButton("7");
		buts[1] = new JButton("8");
		buts[2] = new JButton("9");
		buts[3] = new JButton("/");
		buts[4] = new JButton("4");
		buts[5] = new JButton("5");
		buts[6] = new JButton("6");
		buts[7] = new JButton("*");
		buts[8] = new JButton("1");
		buts[9] = new JButton("2");
		buts[10] = new JButton("3");
		buts[11] = new JButton("-");
		buts[12] = new JButton("0");
		buts[13] = new JButton(".");
		buts[14] = new JButton("=");
		buts[15] = new JButton("+");

		for (JButton b : buts) {
			panel.add(b);
		}

		//Layout Managing
		add(textfield);
		add(AC, BorderLayout.EAST);
		add(panel, BorderLayout.SOUTH);

		//Regirster Handeler
		ButtonHandler handler = new ButtonHandler();
		for (JButton b : buts) {
			b.addActionListener(handler);
		}
		AC.addActionListener(handler);
		// textfield.addActionListener(handler);
	}

	//Handler's code
	private class ButtonHandler implements ActionListener {

		private Float first;
		private Float second;
		private int operation;//+:0,-:1,*:2,/:3

		@Override
		public void actionPerformed(ActionEvent event){
			if (event.getSource() == AC) {
				textfield.setText(null);
			}

			if (textfield.getText().length() <= 18) {
				if (event.getSource() == buts[12]) {
					textfield.setText(textfield.getText() + "0");
				}

				if (event.getSource() == buts[8]) {
					textfield.setText(textfield.getText() + "1");
				}

				if (event.getSource() == buts[9]) {
					textfield.setText(textfield.getText() + "2");
				}

				if (event.getSource() == buts[10]) {
					textfield.setText(textfield.getText() + "3");
				}

				if (event.getSource() == buts[4]) {
					textfield.setText(textfield.getText() + "4");
				}

				if (event.getSource() == buts[5]) {
					textfield.setText(textfield.getText() + "5");
				}

				if (event.getSource() == buts[6]) {
					textfield.setText(textfield.getText() + "6");
				}

				if (event.getSource() == buts[0]) {
					textfield.setText(textfield.getText() + "7");
				}

				if (event.getSource() == buts[1]) {
					textfield.setText(textfield.getText() + "8");
				}

				if (event.getSource() == buts[2]) {
					textfield.setText(textfield.getText() + "9");
				}

				if (event.getSource() == buts[13]) {
					textfield.setText(textfield.getText() + ".");
				}

				if (event.getSource() == buts[15]) {
					first = Float.parseFloat(textfield.getText());
					textfield.setText(null);
					operation = 0;
				}
				if (event.getSource() == buts[11]) {
					first = Float.parseFloat(textfield.getText());
					textfield.setText(null);
					operation = 1;
				}

				if (event.getSource() == buts[7]) {
					first = Float.parseFloat(textfield.getText());
					textfield.setText(null);
					operation = 3;
				}

				if (event.getSource() == buts[3]) {
					first = Float.parseFloat(textfield.getText());
					textfield.setText(null);
					operation = 4;
				}

				if (event.getSource() == buts[14]) {
					if (operation == 0) {
						second = first + Float.parseFloat(textfield.getText());
						textfield.setText(second.toString());
					}
					if (operation == 1) {
						second = first - Float.parseFloat(textfield.getText());
						textfield.setText(second.toString());
					}
					if (operation == 3) {
						second = first * Float.parseFloat(textfield.getText());
						textfield.setText(second.toString());
					}
					if (operation == 4) {
						if (!"0".equals(textfield.getText())) {
							second = first / Float.parseFloat(textfield.getText());
							textfield.setText(second.toString());
						} else {
							textfield.setText("divide by ZERO!");
						}
					}

				}
			}
		}
	}
}
