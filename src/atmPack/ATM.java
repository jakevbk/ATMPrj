package F20Project1GIVETOSTUDENTS;
/********************************************************
 *
 * Describe the class here
 *
 *   IMPORTANT:  This is skeleton code.... you will
 *   need to modify (or create) almost all methods
 *
 ********************************************************/

import java.io.*;
import java.util.Scanner;

public class ATM extends Object {

	/**
	 * the number of hundreds in the ATM
	 */
	private int hundreds;

	/**
	 * the number of fifties in the ATM
	 */
	private int fifties;

	/**
	 * the number of twenties in the ATM
	 */
	private int twenties;

	/**
	 * the number of left over amount after take out from this ATM
	 */
	// private int leftOver;

	private static boolean suspend = false;

	public ATM() {
	}


	public int getTwenties() {
		return twenties;
	}

	public void setTwenties(int twenties) {
		if(twenties < 0)
			throw new IllegalArgumentException();
		this.twenties = twenties;
	}

	public int getFifties() {
		return fifties;
	}

	public void setFifties(int fifties) {
		if(fifties < 0)
			throw new IllegalArgumentException();
		this.fifties = fifties;
	}

	public int getHundreds() {
		return hundreds;
	}

	public void setHundreds(int hundreds) {
		if(hundreds < 0)
			throw new IllegalArgumentException();
		this.hundreds = hundreds;
	}

	/********
	 *  A constructor that initializes the instance variables with
	 *  variables from other ATM.
	 *  Does other ATM have negative variable ?
	 * @param other
	 */
	public ATM(ATM other) {
		if(other == null)
			throw new IllegalArgumentException();
		this.hundreds = other.hundreds;
		fifties = other.fifties;
		twenties = other.twenties;
	}

	/******************************************************************
	 *  A constructor that initializes the instance variables with
	 *  the parameters.
	 *
	 *
	 * @param hundries the initial number of hundreds in the ATM
	 * @param fifties the initial number of fifties in the ATM
	 * @param twenties the initial number of twenties in the ATM
	 * @throws IllegalArgumentException with negative parameters
	 *
	 *****************************************************************/
	public ATM(int hundries, int fifties, int twenties) {

		if (hundries < 0 || fifties < 0 || twenties < 0)
			throw new IllegalArgumentException();

		this.hundreds = hundries;
		this.fifties = fifties;
		this.twenties = twenties;
	}

	/*********
	 * A method that return true if this ATM is the same as
	 * other object (ohter ATM)
	 * @param other
	 * @return
	 */
	public boolean equals(ATM other){ // change Object to ATM
		if(other == null)
			throw new IllegalArgumentException();
		if (this.twenties == other.twenties && this.fifties == other.fifties
		&& this.hundreds == other.hundreds){
			return true;
		}
		else{
			return false;
		}
	}

	/******
	 * A method that returns true if ATM other1 is the same as
	 * ATM other2.
	 * @param other1 an object of ATM
	 * @param other2 an object of ATM
	 * @return
	 */
	public static boolean equals(ATM other1, ATM other2){
		if (other1 == null || other2 == null)
			throw new IllegalArgumentException();
		if (other1.twenties == other2.twenties && other1.fifties == other2.fifties
				&& other1.hundreds == other2.hundreds){
			return true;
		}
		else{
			return false;
		}
	}

	/*********
	 * A method that compare an ATM object to other ATM object
	 * @param other
	 * @return
	 */
	public int compareTo(ATM other){
		if(other == null)
			throw new IllegalArgumentException();
		if(convertToDollars(this) > convertToDollars(other))
			return 1;
		else if(convertToDollars(this) < convertToDollars(other))
			return -1;
		else
			return 0;
	}

	/**********
	 * A method that compare ATM object other 1 to ATM object other 2
	 * @param other1 an ATM object
	 * @param other2 an ATM object
	 * @return
	 */
	public int compareTo(ATM other1, ATM other2){
		if (other1 == null || other2 == null)
			throw new IllegalArgumentException();
		if(convertToDollars(other1) > convertToDollars(other2))
			return 1;
		else if(convertToDollars(other1) < convertToDollars(other2))
			return -1;
		else
			return 0;
	}

	/*******
	 * A method that adds ATM other to the this ATM object
	 * @param other
	 */
	public void putIn(ATM other) {
		if(suspend)
			return;
		if(other == null)
			throw new IllegalArgumentException();
		this.hundreds += other.hundreds;
		this.fifties += other.fifties;
		this.twenties += other.twenties;
	}

	/*******
	 * A method that adds the parameters from the "this" ATM object
	 * @param hundreds the number of hundreds put in this ATM object
	 * @param fifties the number of fifties put in this ATM object
	 * @param twenties the number of twenties put in this ATM object
	 */
	public void putIn(int hundreds, int fifties, int twenties) {
		if (suspend)
			return;
		// what if the parameters are negative
		if (hundreds < 0 || fifties < 0 || twenties < 0)
			throw new IllegalArgumentException();

		this.hundreds += hundreds;
		this.fifties += fifties;
		this.twenties += twenties;
	}

	/*******
	 * A method that subtracts the parameters from the "this" ATM object
	 * @param hundreds the number of hundreds take out from this ATM object
	 * @param fifties the number of fifties take out from this ATM object
	 * @param twenties the number of twenties take out from this ATM object
	 */
	public void takeOut(int hundreds, int fifties, int twenties) {
		if (suspend)
			return;
		// what if the parameters are negative
		if (hundreds < 0 || fifties < 0 || twenties < 0)
			throw new IllegalArgumentException();

		this.hundreds -= hundreds;
		this.fifties -= fifties;
		this.twenties -= twenties;
	}

	/*****
	 * A method that subtracts ATM other to the “this” ATM object.
	 * @param other
	 */
	public void takeOut(ATM other) {
		if (suspend)
			return;
		if (other == null)
			throw new IllegalArgumentException();

		this.hundreds -= other.hundreds;
		this.fifties -= other.fifties;
		this.twenties -= other.twenties;
	}

	/**********
	 * This method returns a new ATM that has the number of
	 * hundreds, fifties, twenties withdrawn from “this” ATM.
	 * @param totalAmount
	 * @return temp
	 */
	public ATM takeOut(int totalAmount) {
		if(suspend)
			return null;

		int amountThisATM = (this.hundreds * 100) + (this.fifties * 50) + (this.twenties * 20);

		if (totalAmount < 0 || (totalAmount % 10) != 0 || amountThisATM < totalAmount)
			throw new IllegalArgumentException();

		ATM temp = new ATM();
		int hundred = 0, fifty = 0, twenty = 0, leftOver1 = 0, leftOver2 = 0, leftAmount = 0;
        // check for 100$ bill
		hundred = totalAmount / 100;
		if (this.hundreds < hundred) {   // if hundreds bill isn't enough
			//int diff100 = hundred - this.hundreds;
			temp.setHundreds(this.hundreds);
			leftOver1 = (totalAmount - this.hundreds * 100);
			this.setHundreds(0);
		} else { //this.hundreds >= hundred
			temp.setHundreds(hundred);
			leftOver1 = (totalAmount - hundred * 100);
			this.setHundreds(this.hundreds - hundred);
		}

		//
		if (leftOver1 / 100 >= 1){ // if there still hundred needed but no more 100$ bill (ex: 120)
			fifty = leftOver1 / 50;
			if (this.fifties < fifty){ // if fifties bill isn't enough
				//int diff50 = fifty - this.fifties;
				temp.setFifties(this.fifties);
				leftOver2 = (leftOver1 - fifty * 50);
				this.setFifties(0);
			}
			else { // if fifties bill is enough or more
				leftOver2 = (leftOver1 - fifty * 50);
				temp.setFifties(fifty);
				this.setFifties(this.fifties - fifty);
			}
			twenty = leftOver2 / 20;
			temp.setTwenties(twenty);
			this.setTwenties(this.twenties - twenty);

		}

		else{ //(leftOver1 / 100 < 1) // if there is no hundred left and no 100$ bill left (ex: 50)
			if (leftOver1 >= 50) {
				if ((leftOver1 / 10) % 2 == 0) {
					fifty = 0;
					twenty = leftOver1 / 20;
					temp.setFifties(0);
					temp.setTwenties(twenty);
					this.setTwenties(this.twenties - twenty);


				} else { //if (leftOver1 / 10)%2 != 0
					fifty = leftOver1 / 50;
					if (this.fifties < fifty) { // if fifties bill isn't enough
						throw new IllegalArgumentException();
					}
					else {
						temp.setFifties(fifty);
						this.setFifties(this.fifties - fifty);
						twenty = (leftOver1 - fifty * 50) / 20;
						if (this.twenties < twenty) { // if twenty bill isn't enough
							throw new IllegalArgumentException();
						}
						temp.setTwenties(twenty);
						this.setTwenties(this.twenties - twenty);
					}
				}
			}
			if (leftOver1 < 50) {
				fifty = 0;
				temp.setFifties(0);
				twenty = leftOver1 / 20;
				if (this.twenties < twenty) { // if fifties bill isn't enough
					throw new IllegalArgumentException();
				}
				temp.setTwenties(twenty);
				this.setTwenties(this.twenties - twenty);

			}
		}
		return temp;
	}

	/*****
	 * A method that returns a string that represents a ATM
	 * @return s
	 */
	public String toString() {
		String s = this.hundreds + " hundred dollar bill";
		if (hundreds != 1) {
			s += "s";
		}
		String d = this.fifties + " fifty dollar bill";
		if (fifties != 1) {
			d += "s";
		}
		String f = this.twenties + " twenty dollar bill";
		if (twenties != 1)
			f += "s";

		return s + ", " + d + ", " + f + ".";
	}

	/****
	 * A method that saves the “this” ATM to a file
	 * @param fileName
	 */
	public void save(String fileName) {

		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					fileName)));
			out.write(this.hundreds + " " + this.fifties + " " + this.twenties);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}

		out.close();
	}

	/******
	 * A  method that loads the “this” ATM object from a file
	 * @param fileName
	 */
	public void load(String fileName) {

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(fileName));
			//scanner.useDelimiter(",");
			hundreds = scanner.nextInt();
			fifties = scanner.nextInt();
			twenties = scanner.nextInt();


		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}

	}

	/****
	 * A method that turns ‘off’ or ‘on’ any takeOut/putIn methods in
	 * @param on
	 */
	public static void suspend (Boolean on){
     if(on == true){
     	suspend = true;
	 }
     else{
     	suspend = false;
	 }
	}



	private static int convertToDollars(ATM temp) {
		// check negative ?
		if (temp == null)
			throw new IllegalArgumentException();
		return (temp.hundreds * 100) + (temp.fifties * 50) +
				(temp.twenties * 20);
	}

	public void putIn(String amount) {
		if (amount.length() == 0 || !isNumeric(amount))
			throw new IllegalArgumentException();

		int dollars = Integer.parseInt(amount);
		if (dollars < 0)
			throw new IllegalArgumentException();

		int hundreds = dollars / 100;
		dollars = dollars % 100;
		int fifties = dollars / 50;
		dollars = dollars % 50;
		int twenties = dollars / 20;
		dollars = dollars % 20;
		if (dollars != 0)
			throw new IllegalArgumentException();
		this.hundreds += hundreds;
		this.fifties += fifties;
		this.twenties += twenties;
	}

	private boolean isNumeric(String str) {
		for (char c : str.toCharArray())
		{
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

}
