package atmPack;
/********************************************************
 * Project 1
 * @author Thanh Tran, Jacob VanBronkhorst
 *
 * An project that programming for the ATM. The project needs to create
 * and implement methods and properties in ATM class.
 *
 ********************************************************/

import java.io.*;
// import java.nio.file.NoSuchFileException;
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
     * suspend the ATM if it is true
     */
    private static boolean suspend = false;

    /**
     * The constructor that sets the ATM to zero
     */
    public ATM() {
    }

    /**
     * The getter method to get the number of twenties in ATM
     *
     * @return twenties the number of twenties in ATM
     */
    public int getTwenties() {
        return twenties;
    }

    /**
     * The setter method to set the number of twenties to the parameter
     *
     * @param twenties the number of twenties will be set to the ATM
     * @throws IllegalArgumentException with negative parameter
     */
    public void setTwenties(int twenties) {
        if (twenties < 0)
            throw new IllegalArgumentException();
        this.twenties = twenties;
    }

    /**
     * The getter method to get the number of fifties in ATM
     *
     * @return fifties the number of fifties in ATM
     */
    public int getFifties() {
        return fifties;
    }

    /**
     * The setter method to set the number of fifties to the parameter
     *
     * @param fifties the number of fifties will be set to the ATM
     * @throws IllegalArgumentException with negative parameter
     */
    public void setFifties(int fifties) {
        if (fifties < 0)
            throw new IllegalArgumentException();
        this.fifties = fifties;
    }

    /**
     * The getter method to get the number of hundreds in ATM
     *
     * @return hundreds the number of hundreds in ATM
     */
    public int getHundreds() {
        return hundreds;
    }

    /**
     * The setter method to set the number of hundreds to the parameter
     *
     * @param hundreds the number of hundreds will be set to the ATM
     * @throws IllegalArgumentException with negative parameter
     */
    public void setHundreds(int hundreds) {
        if (hundreds < 0)
            throw new IllegalArgumentException();
        this.hundreds = hundreds;
    }

    /********
     *  A constructor that initializes the instance variables with
     *  variables from other ATM.
     *
     * @param other an ATM object
     * @throws IllegalArgumentException with null parameter
     */
    public ATM(ATM other) {

        if (other == null)
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
     * @param hundreds the initial number of hundreds in the ATM
     * @param fifties the initial number of fifties in the ATM
     * @param twenties the initial number of twenties in the ATM
     * @throws IllegalArgumentException with negative parameters
     *
     *****************************************************************/
    public ATM(int hundreds, int fifties, int twenties) {

        if (hundreds < 0 || fifties < 0 || twenties < 0)
            throw new IllegalArgumentException();

        this.hundreds = hundreds;
        this.fifties = fifties;
        this.twenties = twenties;
    }

    /*********
     * A method that return true if this ATM is the same as
     * other object.
     *
     * @param other an object type Object
     * @throws IllegalArgumentException if the parameter is null and
     * the parameter is different than ATM type
     * @return true if this ATM is the same as other ATM
     *         false otherwise
     */
    public boolean equals(Object other) {

        if (other != null) {
            if (other instanceof ATM) {
                ATM temp = (ATM) other;
                if (this.twenties == temp.twenties && this.fifties == temp.fifties
                        && this.hundreds == temp.hundreds)
                    return true;
                else
                    return false;

            } else
                throw new IllegalArgumentException();
        } else
            throw new IllegalArgumentException();

    }

    /******
     * A method that returns true if ATM other1 is the same as
     * ATM other2.
     *
     * @param other1 an object of ATM
     * @param other2 an object of ATM
     * @throws IllegalArgumentException if the parameters are null
     * @return true if both ATM are the same
     *         false otherwise
     */
    public static boolean equals(ATM other1, ATM other2) {

        if (other1 == null || other2 == null)
            throw new IllegalArgumentException();

        if (other1.twenties == other2.twenties && other1.fifties == other2.fifties
                && other1.hundreds == other2.hundreds) {
            return true;
        } else {
            return false;
        }
    }

    /*********
     * A method that compare an ATM object to other ATM object.
     *
     * @param other an object of ATM
     * @throws IllegalArgumentException if the parameter is null
     * @return 1 if total amount of this ATM is greater than other ATM
     *        -1 if total amount of this ATM is less than other ATM
     *         0 if total amount of both ATM are equal
     */
    public int compareTo(ATM other) {

        if (other == null)
            throw new IllegalArgumentException();

        if (convertToDollars(this) > convertToDollars(other))
            return 1;
        else if (convertToDollars(this) < convertToDollars(other))
            return -1;
        else
            return 0;
    }

    /**********
     * A method that compare ATM object other 1 to ATM object other 2.
     *
     * @param other1 an ATM object
     * @param other2 an ATM object
     * @throws IllegalArgumentException if parameters are null
     * @return 1 if total amount of ATM 1 is greater than ATM 2
     *        -1 if total amount of ATM 1 is less than ATM 2
     *         0 if total amount of both ATM are equal
     */
    public int compareTo(ATM other1, ATM other2) {

        if (other1 == null || other2 == null)
            throw new IllegalArgumentException();

        if (convertToDollars(other1) > convertToDollars(other2))
            return 1;
        else if (convertToDollars(other1) < convertToDollars(other2))
            return -1;
        else
            return 0;
    }

    /*******
     * A method that adds ATM other to the this ATM object.
     *
     * @param other an ATM object
     * @throws IllegalArgumentException if parameter is null
     * @return none if the ATM is suspended
     */
    public void putIn(ATM other) {

        if (suspend)
            return;
        if (other == null)
            throw new IllegalArgumentException();

        this.hundreds += other.hundreds;
        this.fifties += other.fifties;
        this.twenties += other.twenties;
    }

    /*******
     * A method that adds the parameters from the "this" ATM object.
     *
     * @param hundreds the number of hundreds put in this ATM object
     * @param fifties the number of fifties put in this ATM object
     * @param twenties the number of twenties put in this ATM object
     * @throws IllegalArgumentException if the parameters are negative
     * @return none if the ATM is suspended
     */
    public void putIn(int hundreds, int fifties, int twenties) {

        if (suspend)
            return;
        if (hundreds < 0 || fifties < 0 || twenties < 0)
            throw new IllegalArgumentException();

        this.hundreds += hundreds;
        this.fifties += fifties;
        this.twenties += twenties;
    }

    /*******
     * A method that subtracts the parameters from the "this" ATM object.
     *
     * @param hundreds the number of hundreds take out from this ATM object
     * @param fifties the number of fifties take out from this ATM object
     * @param twenties the number of twenties take out from this ATM object
     * @throws IllegalArgumentException if the parameters are negative and
     * the number of bills in this ATM is not enough
     * @return none if the ATM is suspended
     */
    public void takeOut(int hundreds, int fifties, int twenties) {

        if (suspend)
            return;
        if (hundreds < 0 || fifties < 0 || twenties < 0)
            throw new IllegalArgumentException();
        if (this.hundreds < hundreds || this.fifties < fifties || this.twenties < twenties)
            throw new IllegalArgumentException();

        this.hundreds -= hundreds;
        this.fifties -= fifties;
        this.twenties -= twenties;
    }

    /*****
     * A method that subtracts ATM other to the “this” ATM object.
     *
     * @param other an ATM object
     * @throws IllegalArgumentException if parameter is null and
     * number of bills in this ATM is not enough
     * @return none if this ATM is suspended
     */
    public void takeOut(ATM other) {

        if (suspend)
            return;
        if (other == null)
            throw new IllegalArgumentException();
        if (this.hundreds < other.hundreds || this.fifties < other.fifties
                || this.twenties < other.twenties)
            throw new IllegalArgumentException();

        this.hundreds -= other.hundreds;
        this.fifties -= other.fifties;
        this.twenties -= other.twenties;
    }

    /**********
     * This method returns a new ATM that has the number of
     * hundreds, fifties, twenties withdrawn from “this” ATM.
     *
     * @param totalAmount the amount of money needed to take
     * out from this ATM
     * @throws IllegalArgumentException if the parameter is negative,
     * not divisible for 10 or
     * there is insufficient amount in this ATM
     * @return temp a new ATM object
     */
    public ATM takeOut(int totalAmount) {

        if (suspend)
            return null;

        if (convertToDollars(this) < totalAmount || totalAmount < 0 ||
                (totalAmount == 10) || (totalAmount == 30) || (totalAmount % 10) != 0)
            throw new IllegalArgumentException();

        int hund = 0, fif = 0, twent = 0;

        while (hund < this.hundreds && (totalAmount == 100 || totalAmount >= 120)) {
            totalAmount = totalAmount - 100;
            hund++;
        }

        if (totalAmount == 30 || totalAmount == 10) {
            totalAmount = totalAmount + 100;
            hund--;
        }

        while (fif < this.fifties && (totalAmount == 50 || totalAmount >= 70)) {
            totalAmount = totalAmount - 50;
            fif++;
        }

        if (totalAmount == 30 || totalAmount == 10) {
            totalAmount = totalAmount + 50;
            fif--;
        }

        while (twent < this.twenties && totalAmount >= 20 && (totalAmount % 20 == 0)) {
            totalAmount = totalAmount - 20;
            twent++;
        }

        if (totalAmount == 0) {
            this.hundreds = this.hundreds - hund;
            this.fifties = this.fifties - fif;
            this.twenties = this.twenties - twent;

            ATM temp = new ATM(hund, fif, twent);
            return temp;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /*****
     * A method that returns a string that represents a ATM.
     *
     * @return s a new string
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
     * A method that saves the “this” ATM to a file.
     *
     * @param fileName an input file type string
     * @throws IllegalArgumentException if it can't be saved into the file
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
     * A  method that loads the “this” ATM object from a file.
     *
     * @param fileName an input file type string
     * @throws IllegalArgumentException if it can't find the file
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
     * A method that turns ‘off’ or ‘on’ any takeOut/putIn methods in.
     *
     * @param on an input condition (true or false) for suspend variable
     */
    public static void suspend(Boolean on) {

        if (on == true) {
            suspend = true;
        } else {
            suspend = false;
        }
    }
    
     /**
     * A getter method for suspend variable (true or false).
     *
     * @return suspend an instance variable that returns true or false
     */
    public static boolean isSuspend() {
        return suspend;
    }

    /**
     * the method that returns the total amount of money from the ATM.
     *
     * @param temp an ATM object
     * @return total amount of money in the ATM
     * @throws IllegalArgumentException if the parameter is null
     */
    private static int convertToDollars(ATM temp) {

        if (temp == null)
            throw new IllegalArgumentException();
        return (temp.hundreds * 100) + (temp.fifties * 50) +
                (temp.twenties * 20);
    }

    /**
     * A method that adds an amount of money to this ATM object.
     *
     * @param amount an input amount of money in type string
     * @throws IllegalArgumentException if the input string is missing or
     *                                  the string is not digit or
     *                                  the amount is negative or
     *                                  the leftover amount is different than zero
     */
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

    /**
     * A helper method that checks if the input string contains
     * only digit.
     *
     * @param str the input type string
     * @return true if the input string contains only digit
     * false otherwise
     */
    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

}
