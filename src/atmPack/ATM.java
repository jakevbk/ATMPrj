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

    //do we need a get method
//    public static boolean isSuspend() {
//        return suspend;
//    }

    public static void suspend(Boolean on) {
        if(on == true)
            suspend = true;
        else
            suspend = false;
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

        if(other1.hundreds == other2.hundreds && other1.fifties == other2.fifties
                && other1.twenties == other2.twenties)
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
        if(convertToDollars(other1) > convertToDollars(other2))
            return 1;
        else if(convertToDollars(other1) < convertToDollars(other2))
            return -1;
        else
            return 0;
    }

    private static int convertToDollars(ATM temp) {
        return (temp.hundreds * 100) + (temp.fifties * 50) +
                (temp.twenties * 20);
    }

    public void takeOut(int hundred, int fifties, int twenties) {
        if (suspend)
            return;

        if (hundreds < 0 || fifties < 0 || twenties < 0)
            throw new IllegalArgumentException();

        this.hundreds -= hundred;
        this.fifties -= fifties;
        this.twenties -= twenties;
    }

    public void takeOut(ATM other) {
        if (suspend)
            return;

        this.hundreds -= other.hundreds;
        this.fifties -= other.fifties;
        this.twenties -= other.twenties;
    }

    //this code is only supposed to be 9 lines according to prof lol
    public ATM takeOut(int totalAmount) {
        if(totalAmount < 0)
            throw new IllegalArgumentException();
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
            leftOver1 = (totalAmount - hundred * 100);
            this.setHundreds(this.hundreds - hundreds);
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

    public void putIn(ATM other) {
        if (suspend)
            return;
        this.hundreds += other.hundreds;
        this.fifties += other.fifties;
        this.twenties += other.twenties;
    }

    public void putIn(int hundreds, int fifties, int twenties) {
        if (suspend)
            return;

        if (hundreds < 0 || fifties < 0 || twenties < 0)
            throw new IllegalArgumentException();

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

    public void save(String fileName) {
//        PrintWriter out = null;
//        try {
//            out = new PrintWriter(new BufferedWriter(new FileWriter(
//                    fileName)));
        try{
            FileWriter file = new FileWriter(fileName);

            BufferedWriter output = new BufferedWriter(file);

            output.write(this.hundreds + " " + this.fifties + " " + this.twenties);

            output.close();

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        //out.close();
    }

    public void load(String fileName) {
        hundreds = 0;
        fifties = 0;
        twenties = 0;

        //Scanner scanner = null;
        try {
            Scanner scanner = new Scanner(new File(fileName));
                hundreds = scanner.nextInt();
                fifties = scanner.nextInt();
                twenties = scanner.nextInt();
                ATM loaded = new ATM(hundreds,fifties,twenties);

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }
}