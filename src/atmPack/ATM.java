package atmPack;
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

    private static boolean suspend = false;

    public ATM() {
    }

    public ATM(ATM other) {
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

    private static int convertToDollars(ATM temp) {
        return (temp.hundreds * 100) + (temp.fifties * 50) +
                (temp.twenties * 20);
    }

    public void takeOut(int hundred, int fifties, int twenties) {
        this.hundreds -= hundred;
        this.fifties -= fifties;
        this.twenties -= twenties;
    }

    public void takeOut(ATM other) {
    }

    public ATM takeOut(int totalAmount) {
        throw new IllegalArgumentException();
    }

    public void putIn(ATM other) {
        this.hundreds += other.hundreds;
        this.fifties += other.fifties;
        this.twenties += other.twenties;
    }

    public void putIn(int hunderies, int fifties, int twenties) {
        if (suspend)
            return;

        this.hundreds += hunderies;
        this.fifties += fifties;
        this.twenties += twenties;
    }

    public void putIn(String amount) {
        int dollars = Integer.parseInt(amount);
        int hundreds = dollars / 100;
        dollars = dollars % 100;
    }

    public String toString() {
        String s = this.hundreds + " hundred dollar bill";
        if (hundreds != 1)
            s += "s";

        return s;
    }

    public void save(String fileName) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                    fileName)));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        out.close();
    }

    public void load(String fileName) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }
}