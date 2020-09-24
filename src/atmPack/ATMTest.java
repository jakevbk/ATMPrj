package atmPack;

import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ATMTest {

	/**
	 * Your assignment is to write many test cases  *****
	 * I have provided some examples to help you get started
	 * <p>
	 * All the lines are commented out until you get the basic
	 * ATM class working, then uncomment them
	 */

	//Test to see if all three constructors set correct values
	@Test
	public void testConstructor() {
		ATM s1 = new ATM(6, 5, 4);

		assertEquals(6, s1.getHundreds());
		assertEquals(5, s1.getFifties());
		assertEquals(4, s1.getTwenties());

		ATM s2 = new ATM();
		assertEquals(0, s2.getHundreds());
		assertEquals(0, s2.getFifties());
		assertEquals(0, s2.getTwenties());

		ATM s3 = new ATM(s1);
		assertEquals(6, s3.getHundreds());
		assertEquals(5, s3.getFifties());
		assertEquals(4, s3.getTwenties());
	}

	//makes sure constructor doesn't allow negative hundreds
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegHundereds() {
		new ATM(-300, 0, 0);
	}

	//makes sure constructor doesn't allow negative fifties
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegFifty () {
		new ATM(300, -10, 0);
	}

	//makes sure constructor doesn't allow negative twenties
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegTwenties () {
		new ATM(300, 10, -30);
	}

	// checks to make  sure a null ATM throws an error
	@Test (expected = IllegalArgumentException.class)
	public void ATMother(){
		ATM s1 = new ATM(null);
	}

	//takeout and update "this" ATM
	@Test
	public void testTakeOut1() {
		ATM s1 = new ATM(3, 3, 2);
		s1.takeOut(1, 1, 1);
		assertEquals(2, s1.getHundreds());
		assertEquals(2, s1.getFifties());
		assertEquals(1, s1.getTwenties());
	}

	//takeout a total amount and update both this ATM and new ATM
	@Test
	public void testTakeOut2ADD() {
		ATM s1 = new ATM(5, 3, 3);
		ATM s2 = s1.takeOut(120);

		assertEquals(4, s1.getHundreds());
		assertEquals(3, s1.getFifties());
		assertEquals(2, s1.getTwenties());

		assertEquals(1, s2.getHundreds());
		assertEquals(0, s2.getFifties());
		assertEquals(1, s2.getTwenties());
	}

	//Takeout using at least one of each bill and update "this" ATM
	//and the new ATM
	@Test
	public void testTakeOut3ADD() {
		ATM s1 = new ATM(5, 3, 3);
		ATM s2 = s1.takeOut(270);

		assertEquals(3, s1.getHundreds());
		assertEquals(2, s1.getFifties());
		assertEquals(2, s1.getTwenties());

		assertEquals(2, s2.getHundreds());
		assertEquals(1, s2.getFifties());
		assertEquals(1, s2.getTwenties());
	}

	//takeout total amount using twenties and fifties update both ATMs
	@Test
	public void testTakeOut4ADD() {
		ATM s1 = new ATM(1, 1, 4);
		ATM s2 = s1.takeOut(130);

		assertEquals(s1.getHundreds(),1);
		assertEquals(s1.getFifties(),0);
		assertEquals(s1.getTwenties(),0);

		assertEquals(s2.getHundreds(),0);
		assertEquals(s2.getFifties(),1);
		assertEquals(s2.getTwenties(),4);
	}

	//testing to make sure can't takeout if not sufficient bills
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut5ADD() {
		ATM s1 = new ATM(3, 0, 4);
		ATM s2 = s1.takeOut(370);
	}

	//testing to make sure can't takeout if not sufficient funds
	@Test (expected = IllegalArgumentException.class)
	public void testTakeOut6ADD() {
		ATM s1 = new ATM(1, 1, 4);
		ATM s2 = s1.takeOut(300);
	}

	// testing not able to takeout by comparing two ATMs
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNull1() {
		ATM s1 = new ATM(3, 1, 2);
		ATM s2 = s1.takeOut(700);
		assertEquals(s2, null);
	}

	//Professor Ferguson's takeout method
	public ATM myTakeOut(int totalAmount, int hundreds, int fifties, int twenties) {

		if (totalAmount <= (hundreds * 100 + fifties * 50 + twenties * 20)) {
			for (int a = hundreds; a >= 0; a--) {
				for (int b = fifties; b >= 0; b--) {
					for (int c = twenties; c >= 0; c--) {
						if ((a * 100 + b * 50 + c * 20) == totalAmount) {
							hundreds -= a;
							fifties -= b;
							twenties -= c;
							return new ATM(a, b, c);

						}
					}
				}
			}
		}
		return null;
	}

	//test multiple ATMs with takeout total amount method and counts
	//each error caught. Check to see if our method matches Professor
	//Ferguson's in errors caught.
	@Test
	public void testTakeSuperTest() {
		int amount = 20;
		int numOfIll = 0;

		for (int h = 0; h < amount; h++) {
			System.out.println(h);
			for (int f = 0; f < amount; f++)
				for (int t = 0; t < amount; t++)
					for (int m = 10; m < ((amount *100) + (amount *50)
							+ (amount * 20) + 10); m += 10) {
						try {
							ATM a1 = new ATM(h, f, t);
							ATM b1 = a1.takeOut(m);

							ATM a2 = new ATM(h, f, t);
							ATM b2 = myTakeOut(m, h, f, t);

							assertEquals(b1,b2);
						}
						catch (IllegalArgumentException e) {
							numOfIll++;
						}
					}
		}
		System.out.println(numOfIll);
		assertEquals(1589500, numOfIll);
	}


	//testing to make sure fifties and twenties will be taken out
	//properly using takeout total method
	@Test
	public void testTakeOut3() {
		ATM s1 = new ATM(0, 50, 3);
		ATM s2 = s1.takeOut(160);
		assertEquals(s1.getHundreds(), 0);
		assertEquals(s1.getFifties(), 48);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 2);
		assertEquals(s2.getTwenties(), 3);
	}

	//testing to make sure hundreds and twenties will be taken out
	//properly using takeout total method
	@Test
	public void testTakeOut4() {
		ATM s1 = new ATM(5, 1, 4);
		ATM s2 = s1.takeOut(180);
		assertEquals(s1.getHundreds(), 4);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 1);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 4);
	}

	//testing to make sure a hundred will not be taken out
	//and that fifties and twenties will be properly taken out
	@Test
	public void testTakeOut4a() {
		ATM s1 = new ATM(1, 1, 4);
		ATM s2 = s1.takeOut(130);
		assertEquals(s1.getHundreds(), 1);
		assertEquals(s1.getFifties(), 0);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 1);
		assertEquals(s2.getTwenties(), 4);
	}

	//testing to takeout hundreds and twenties
	@Test
	public void testTakeOut4b() {
		ATM s1 = new ATM(5, 1, 4);
		ATM s2 = s1.takeOut(180);
		assertEquals(s1.getHundreds(), 4);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 1);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 4);
	}

	//takeout total amount with just twenties
	@Test
	public void testTakeOut5() {
		ATM s1 = new ATM(0, 1, 4);
		ATM s2 = s1.takeOut(80);
		assertEquals(s1.getHundreds(), 0);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 4);
	}

	//takeout total amount using fifties and twenties
	@Test
	public void testTakeOut6() {
		ATM s1 = new ATM(0, 3, 3);
		ATM s2 = s1.takeOut(160);
		assertEquals(s1.getHundreds(), 0);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 2);
		assertEquals(s2.getTwenties(), 3);
	}

	//making sure a null ATM can't be used
	@Test (expected = IllegalArgumentException.class)
	public void takeOutNullOther(){
		ATM s1 = new ATM(null);
		ATM s2 = new ATM(1,2,3);
		s1.takeOut(s2);
	}

	//taking out money from a different ATM
	@Test
	public void takeOutFromAnother(){
		ATM s1 = new ATM(4,7,3);
		ATM s2 = new ATM(3,2,0);
		s1.takeOut(s2);

		assertEquals(1, s1.getHundreds());
		assertEquals(5, s1.getFifties());
		assertEquals(3, s1.getTwenties());
	}

	//Can't takeout money if it results in an ATM having negative bills
	@Test (expected = IllegalArgumentException.class)
	public void takeOutFromAnotherNegative(){
		ATM s1 = new ATM(4,7,3);
		ATM s2 = new ATM(3,9,0);
		s1.takeOut(s2);
	}

	//putting money into an ATM and checking for proper bills
	@Test
	public void testPutIn () {
		ATM s1 = new ATM();
		s1.putIn(2, 3, 4);

		assertEquals(2, s1.getHundreds());
		assertEquals(3, s1.getFifties());
		assertEquals(4, s1.getTwenties());
	}

	//putting one ATM into a different ATM
	@Test
	public void testPutInAnother(){
		ATM s1 = new ATM(4,7,3);
		ATM s2 = new ATM(3,2,0);
		s1.putIn(s2);

		assertEquals(7,s1.getHundreds());
		assertEquals(9,s1.getFifties());
		assertEquals(3,s1.getTwenties());
	}

	//throwing an error if the method doesn't end with dollars being 0
	@Test (expected =IllegalArgumentException.class)
	public void testPutInString(){
		ATM s1 = new ATM(0,0,0);
		s1.putIn("42");
	}

	//putting in a string amount and updating ATM
	@Test
	public void testPutInString2(){
		ATM s1 = new ATM(0,0,0);
		s1.putIn("200");

		assertEquals(2,s1.getHundreds());
		assertEquals(0,s1.getFifties());
		assertEquals(0,s1.getTwenties());
	}

	//putting in a string amount and ensuring all bills are properly
	//determined by using the putIn(String str) method
	@Test
	public void testPutInString3(){
		ATM s1 = new ATM(0,0,0);
		s1.putIn("170");

		assertEquals(1,s1.getHundreds());
		assertEquals(1,s1.getFifties());
		assertEquals(1,s1.getTwenties());
	}

	//making sure negatives can't be put in. Doesn't make it that
	//far though because a "-" is not a numeric.
	@Test (expected = IllegalArgumentException.class)
	public void testPutInString4(){
		ATM s1 = new ATM(0,0,0);
		s1.putIn("-4");
	}

	//ensuring if suspend is true that the method returns
	@Test
	public void testPutInStringSuspend(){
		ATM s1 = new ATM(0,0,0);
		ATM.suspend(true);
		s1.putIn("40");
		ATM.suspend(false);
	}

	//putting in money and then taking out money. Check to make sure
	//the ATM has the correct bills
	@Test
	public void testPutInTakeOut () {
		ATM s1 = new ATM();
		s1.putIn(3, 3, 2);
		s1.takeOut(1, 1, 1);

		assertEquals(2, s1.getHundreds());
		assertEquals(2, s1.getFifties());
		assertEquals(1, s1.getTwenties());
	}

	//Can't put in negative hundreds
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegHundreds () {
		ATM s = new ATM(2, 3, 4);
		s.putIn(-30, 2, 30);
	}

	//Can't put in negative fifties
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegFifty () {
		ATM s = new ATM(2, 3, 4);
		s.putIn(0, -2, 30);
	}

	//Can't put in negative twenties
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegTwenties() {
		ATM s = new ATM(2, 3, 4);
		s.putIn(30, 2, -30);
	}

	//Check to see if ATMs have equal amount of bills/money
	@Test
	public void testEqual () {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 5, 4);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
	}

	//Making sure two ATMs are equal if they have same bills/money
	@Test
	public void testEquals() {
		ATM s1 = new ATM(2, 3, 4);
		ATM s2 = new ATM(2, 3, 4);
		assertTrue(ATM.equals(s1, s2));
	}

	//ATMs aren't equal if they don't have same amount of money/bills
	@Test
	public void testEqualsTwoObject(){
		ATM s1 = new ATM(2, 3, 4);
		ATM s2 = new ATM(6,7,5);
		assertFalse(ATM.equals(s1,s2));
	}

	//ATM can't equal a random string
	@Test (expected = IllegalArgumentException.class)
	public void testequals2() {
		ATM d1 = new ATM(2,21,2021);
		d1.equals("pizza");
	}

	//An ATM with money can't equal a null object
	@Test (expected = IllegalArgumentException.class)
	public void testequals3() {
		ATM d1 = new ATM(2,29,2021);
		d1.equals(null);
	}

	//compare ATMs returning an Int based on which ATM has more money
	@Test
	public void testCompareToOthers () {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 3, 4);
		ATM s4 = new ATM(2, 5, 4);

		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s3.compareTo(s1) < 0);
		assertTrue(s1.compareTo(s4) == 0);
	}

	//ATM s1 is greater than s2
	@Test
	public void compareToGreaterOther(){
		ATM s1 = new ATM(12, 0, 0);
		ATM s2 = new ATM(6,0,0);
		assertEquals(1, s1.compareTo(s2));
	}

	//ATM s1 is less than s2
	@Test
	public void compareToLessOther(){
		ATM s1 = new ATM(2, 0, 0);
		ATM s2 = new ATM(6,0,0);
		assertEquals(-1, s1.compareTo(s2));
	}

	//comparing ATM s1 to s2 and they are equal to each other
	@Test
	public void compareToEqualOther(){
		ATM s1 = new ATM(2, 0, 0);
		ATM s2 = new ATM(2,0,0);
		assertEquals(0, s1.compareTo(s2));
	}

	//comparing s1 and s2, they are equal
	@Test
	public void compareToEqualTwoATMs(){
		ATM s1 = new ATM(2, 0, 0);
		ATM s2 = new ATM(2,0,0);
		assertEquals(0, s1.compareTo(s1,s2));
	}

	//comparing s1 and s2, s1 is greater
	@Test
	public void compareToGreaterTwoATMs(){
		ATM s1 = new ATM(12, 0, 0);
		ATM s2 = new ATM(6,0,0);
		assertEquals(1, s1.compareTo(s1,s2));
	}

	//comparing s1 and s2, s1 is less
	@Test
	public void compareToLessTwoATMs(){
		ATM s1 = new ATM(2, 0, 0);
		ATM s2 = new ATM(6,0,0);
		assertEquals(-1, s1.compareTo(s1,s2));
	}

	//Can't compare a null ATM
	@Test (expected = IllegalArgumentException.class)
	public void compareToTwoNull() {
		ATM s1 = new ATM(null);
		ATM s2 = new ATM(6, 0, 0);
		assertEquals(-1, s1.compareTo(s1, s2));
	}

	//Save ATM s1. reset it. Then load it and check to see if the ATM
	// is properly reloaded
	@Test
	public void testLoadSave () {
		ATM s1 = new ATM(6, 5, 4);
		ATM s2 = new ATM(6, 5, 4);

		s1.save("file1");
		s1 = new ATM();  // resets to zero

		s1.load("file1");
		System.out.println(s1);
		System.out.println(s2);
		assertTrue(s1.equals(s2));
	}

	//Can't save the file so catch and throw error
	@Test (expected = IllegalArgumentException.class)
	public void testSaveLoadError () {
		ATM d = new ATM(1, 11, 2018);
		d.save("-!@#$%^&*()testit-.txt");
	}

	//Try to load a file that was never saved
	@Test (expected = IllegalArgumentException.class)
	public void loadNoFile(){
		ATM s1 = new ATM(1,1,1);
		s1.load("notSaved");
	}

	//Ensuring that can't make changes to ATM when suspend is true
	@Test
	public void testMutate () {
		ATM s1 = new ATM(6, 5, 4);
		ATM.suspend(true);
		s1.takeOut(120);
		assertEquals(6, s1.getHundreds());
		assertEquals(5, s1.getFifties());
		assertEquals(4, s1.getTwenties());
		ATM.suspend(false);
	}

	//The following suspend methods all check to see if suspend is true
	//that the method returns and does not continue on
	@Test
	public void getSuspend(){
		assertFalse(ATM.isSuspend());
	}

	@Test
	public void SuspendPutIn(){
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(2,5,4);
		ATM.suspend(true);
		s1.putIn(s2);
		ATM.suspend(false);
	}

	@Test
	public void SuspendPutIn2(){
		ATM s1 = new ATM(2, 5, 4);
		ATM.suspend(true);
		s1.putIn(100,100,100);
		ATM.suspend(false);
	}

	@Test
	public void SuspendTakeout(){
		ATM s1 = new ATM(1,2,3);
		ATM.suspend(true);
		s1.takeOut(1,1,1);
		ATM.suspend(false);
	}

	@Test
	public void SuspendTakeout2(){
		ATM s1 = new ATM(1,2,3);
		ATM.suspend(true);
		s1.takeOut(100);
		ATM.suspend(false);
	}

	@Test
	public void SuspendTakeout3(){
		ATM s1 = new ATM(1,2,3);
		ATM s2 = new ATM(1,2,3);
		ATM.suspend(true);
		s1.takeOut(s2);
		ATM.suspend(false);
	}

	@Test
	public void SuspendForSetters(){
		ATM s1 = new ATM(1,2,3);
		ATM.suspend(true);
		s1.setTwenties(2);
		s1.setFifties(2);
		s1.setHundreds(0);
		ATM.suspend(false);
	}

	//Can't cause negative hundreds in an ATM when taking out money
	@Test	(expected = IllegalArgumentException.class)
	public void testTakeOutCauseNegHund () {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(3, 1, 1);
	}

	//Can't cause negative fifties in an ATM when taking out money
	@Test	(expected = IllegalArgumentException.class)
	public void testTakeOutCauseNegFifty () {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(1, 41, 1);
	}

	//Can't cause negative twenties in an ATM when taking out money
	@Test	(expected = IllegalArgumentException.class)
	public void testTakeOutCauseNegTwenties () {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(1, 1, 6);
	}

	//Can't take out negative twenties
	@Test	(expected = IllegalArgumentException.class)
	public void testTakeOutNegTwenties () {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(1, 1, -1);
	}

	//Setting twenties and ensuring the ATM is updated
	@Test
	public void setTwenties(){
		ATM s1 = new ATM(1,0,0);
		s1.setTwenties(4);
		assertEquals(4, s1.getTwenties());
	}

	//Can't set negative twenties
	@Test (expected = IllegalArgumentException.class)
	public void setNegativeTwenties(){
		ATM s1 = new ATM(1,0,0);
		s1.setTwenties(-6);
	}

	//Can't take out negative fifties
	@Test	(expected = IllegalArgumentException.class)
	public void testTakeOutNegFifty () {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(1, -1, 1);
	}

	//Setting fifties and ensuring the ATM is updated
	@Test
	public void setFifties(){
		ATM s1 = new ATM(1,3,0);
		s1.setFifties(0);
		assertEquals(0, s1.getFifties());
	}

	//Can't set negative fifties
	@Test (expected = IllegalArgumentException.class)
	public void setNegativeFifties(){
		ATM s1 = new ATM(1,0,0);
		s1.setFifties(-2);
	}

	//Can't take out negative hundreds
	@Test	(expected = IllegalArgumentException.class)
	public void testTakeOutNegHundreds () {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(-1, 1, 1);
	}

	//Setting hundreds and ensuring the ATM is updated
	@Test
	public void setHundreds(){
		ATM s1 = new ATM(1,0,0);
		s1.setHundreds(7);
		assertEquals(7, s1.getHundreds());
	}

	////Can't set negative hundreds
	@Test (expected = IllegalArgumentException.class)
	public void setNegativeHundreds(){
		ATM s1 = new ATM(1,0,0);
		s1.setHundreds(-1);
	}

	//"forty" is not numeric so it throws an error
	@Test (expected = IllegalArgumentException.class)
	public void DigitString(){
		ATM s1 = new ATM(0,0,0);
		s1.putIn("forty");
	}

	//even though the string contains numbers, it is not numeric
	//because there is also a letter
	@Test (expected = IllegalArgumentException.class)
	public void DigitString1(){
		ATM s1 = new ATM(0,0,0);
		s1.putIn("42f");
	}

	//this method was given to us in ExtraTestCases but it did not
	//have any tests associated with it.
	public void TestItALL() {
		for (int hundreds = 0; hundreds < 10; hundreds++)
			for (int fifties = 0; fifties < 10; fifties++)
				for (int twenties = 0; twenties < 10; twenties++)
					for (int p = 0; p < 10; p++)
						for (int amt = 20; amt < 500; amt += 10) {
							ATM j1 = new ATM(hundreds, fifties, twenties);
							ATM j2 = new ATM(hundreds, fifties, twenties);
							ATM j3 = new ATM(hundreds, fifties, twenties);
							ATM j4 = new ATM(hundreds, fifties, twenties);

							try {
								ATM j11 = j1.takeOut(amt);
							} catch (Exception e) {
							}
						}
	}
}
