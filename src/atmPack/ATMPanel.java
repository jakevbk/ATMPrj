package atmPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Write a description of class ATMPanel here.
 * 
 * @author Roger Ferguson
 * @version April 7, 2019
 */
public class ATMPanel extends JPanel {

	private ATM jar;

	NumberFormat fmt = NumberFormat.getCurrencyInstance();

	JButton withdrawBillsButton, withDrawTotalButton, depositBillsButton, saveButton, loadButton;
	JCheckBoxMenuItem suspend;

	JTextField twentsIN, fiftsIN, hundsIN, totalIN, hundredField, fiftyField, twentyField, totalField;


	public ATMPanel() {

		// create the game object as well as the ATMGUI Frame
		jar = new ATM();

		// set the layout to GridBag
		setLayout(new GridLayout(13, 2));
		GridBagConstraints loc = new GridBagConstraints();
		setBackground(Color.lightGray);

		// get Die #2 from game and place on ATMGUI
		loc = new GridBagConstraints();
//		loc.gridwidth = 2;
//		loc.gridx = 0;
//		loc.gridy = 3;
//		loc.insets.bottom = 0;
//		loc.insets.top = 0;
		add(new JLabel("Interact with ATM"));
		add(new JLabel(""));

		add(new JLabel("Hundreds:"), loc);
		hundredField = new JTextField("0", 3);
		add(hundredField);

		add(new JLabel("Fifties:"));
		fiftyField = new JTextField("0", 3);
		add(fiftyField);

		add(new JLabel("Twenties:"));
		twentyField = new JTextField("0", 3);
		add(twentyField);

		add(new JLabel("Total:"));
		totalField = new JTextField("0", 3);
		add(totalField);


		//I think this was a previous version so I created new JButtons
//		takeOutHFTButton = new JButton("Take Out with H,F,T");
//		add(takeOutHFTButton);

		withdrawBillsButton = new JButton("Withdraw Bills");
		add(withdrawBillsButton);

		withDrawTotalButton = new JButton("Withdraw Total");
		add(withDrawTotalButton);

		depositBillsButton = new JButton("Deposit Bills");
		loc = new GridBagConstraints();
		loc.anchor = GridBagConstraints.CENTER;
		loc.gridwidth = 2;
		loc.gridx = 0;
		loc.gridy = 3;
		loc.insets.bottom = 0;
		loc.insets.top = 0;
		add(depositBillsButton, loc);


		saveButton = new JButton("Save ATM");
		add(saveButton);

		loadButton = new JButton("Load ATM");
		add(loadButton);

		add(new JLabel(""));

		add(new JLabel("Current State ATM"));
		add(new JLabel(""));


		add(new JLabel("Total: "));
		totalIN = new JTextField("0", 3);
		totalIN.setEditable(false);
		add(totalIN);

		add(new JLabel("Hundreds: "));
		hundsIN = new JTextField("" + jar.getHundreds(), 3);
		hundsIN.setEditable(false);
		add(hundsIN);

		add(new JLabel("Fifties: "));
		fiftsIN = new JTextField("" + jar.getFifties(), 3);
		fiftsIN.setEditable(false);
		add(fiftsIN);

		add(new JLabel("Twenties: "));
		twentsIN = new JTextField("" + jar.getTwenties(), 3);
		twentsIN.setEditable(false);
		add(twentsIN);

		// register the listeners

		//takeOutHFTButton.addActionListener(new ButtonListener());
		withdrawBillsButton.addActionListener(new ButtonListener());
		withDrawTotalButton.addActionListener(new ButtonListener());
		depositBillsButton.addActionListener(new ButtonListener());
		saveButton.addActionListener(new ButtonListener());
		loadButton.addActionListener(new ButtonListener());
	}

	//helper method to determine total amount of atm
	private int convertToTotal(ATM jar){
		int total = 0;
		total = ((jar.getHundreds()*100) + (jar.getFifties()*50) + (jar.getTwenties()*20));
		return total;
	}



	/****************************************************************
	 Inner class to repond to the user action
	 ****************************************************************/
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			int hundreds, fifties, twenties;

			if (event.getSource() == withdrawBillsButton) {
				try {
					hundreds = Integer.parseInt(hundredField.getText());
					fifties = Integer.parseInt(fiftyField.getText());
					twenties = Integer.parseInt(twentyField.getText());
					System.out.println(hundreds);
					jar.takeOut(hundreds, fifties, twenties);
					hundsIN.setText("" + jar.getHundreds());
					fiftsIN.setText("" + jar.getFifties());
					twentsIN.setText("" + jar.getTwenties());
					totalIN.setText("" + fmt.format(convertToTotal(jar)));
				} catch (NumberFormatException io) {
					JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Not enough specified coins for this operation");
				}
			}

			if (event.getSource() == depositBillsButton) {
				try {
					hundreds = Integer.parseInt(hundredField.getText());
					fifties = Integer.parseInt(fiftyField.getText());
					twenties = Integer.parseInt(twentyField.getText());
					jar.putIn(hundreds, fifties, twenties);
					hundsIN.setText("" + jar.getHundreds());
					fiftsIN.setText("" + jar.getFifties());
					twentsIN.setText("" + jar.getTwenties());
					totalIN.setText("" + fmt.format(convertToTotal(jar)));
				} catch (NumberFormatException io) {
					JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
				}
			}
		}
	}
}

