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

    /**
     * Suspending all ATMs if boolean suspend is true
     */
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

    /**
     * Setters and getter methods
     */

    public int getHundreds() {
        return hundreds;
    }

    public void setHundreds(int hundreds) {
        if(this.hundreds < 0)
            throw new IllegalArgumentException();
        this.hundreds = hundreds;
    }

    public int getFifties() {
        return fifties;
    }

    public void setFifties(int fifties) {
        if(this.fifties < 0)
            throw new IllegalArgumentException();
        this.fifties = fifties;
    }

    public int getTwenties() {
        return twenties;
    }

    public void setTwenties(int twenties) {
        if(this.twenties < 0)
            throw new IllegalArgumentException();
        this.twenties = twenties;
    }

    public static boolean isSuspend() {
        return suspend;
    }

    public static void setSuspend(boolean suspend) {
        ATM.suspend = suspend;
    }

    public boolean equals(Object other){
        //put a throw error statement first once you figure out this method

        ATM differentOne = new ATM((ATM) other);

        if(this.hundreds == differentOne.getHundreds() && this.fifties == differentOne.getFifties()
                && twenties == differentOne.getTwenties())
                    return true;
        else
            return false;
    }

    public static boolean equals(ATM other1, ATM other2){
        // throw error if either of the ATMs have negatives or are null
        ATM s1 = new ATM(other1);
        ATM s2 = new ATM(other2);

        if(s1.getHundreds() == s2.getHundreds() && s1.getFifties() == s2.getFifties()
                && s1.getTwenties() == s2.getTwenties())
                return true;
        else
            return false;
    }

    public int compareTo(ATM other){

        if(convertToDollars(this) > convertToDollars(other))
            return 1;
        else if(convertToDollars(this) < convertToDollars(other))
            return -1;
        else
            return 0;
    }

    public static int compareTo(ATM other1, ATM other2){
        ATM s1 = new ATM(other1);
        ATM s2 = new ATM(other2);

        if(convertToDollars(s1) > convertToDollars(s2))
            return 1;
        else if(convertToDollars(s1) < convertToDollars(s2))
            return -1;
        else
            return 0;
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

        this.hundreds -= other.hundreds;
        this.fifties -= other.fifties;
        this.twenties -= other.twenties;
    }

    public ATM takeOut(int totalAmount) {
        throw new IllegalArgumentException();
    }

    public void putIn(ATM other) {
        this.hundreds += other.hundreds;
        this.fifties += other.fifties;
        this.twenties += other.twenties;
    }

    public void putIn(int hundreds, int fifties, int twenties) {
        if (suspend)
            return;

        this.hundreds += hundreds;
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