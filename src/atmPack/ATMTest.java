package atmPack;

import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;

import static org.junit.Assert.*;

public class ATMTest {

	/**
	 * Your assignment is to write many test cases  *****
	 * I have provided some examples to help you get started
	 * <p>
	 * All the lines are commented out until you get the basic
	 * ATM class working, then uncomment them
	 */


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

	@Test (expected = IllegalArgumentException.class)
	public void ATMother(){
		ATM s1 = new ATM(null);
	}

	@Test
	public void testTakeOut1() {
		ATM s1 = new ATM(3, 3, 2);
		s1.takeOut(1, 1, 1);
		assertEquals(2, s1.getHundreds());
		assertEquals(2, s1.getFifties());
		assertEquals(1, s1.getTwenties());
	}

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

	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut5ADD() {
		ATM s1 = new ATM(3, 0, 4);
		ATM s2 = s1.takeOut(370);
	}

	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOut6ADD() {
		ATM s1 = new ATM(1, 1, 4);
		ATM s2 = s1.takeOut(300);
	}

	@Test
	public void takeOutFromAnother(){
		ATM s1 = new ATM(4,7,3);
		ATM s2 = new ATM(3,2,0);
		s1.takeOut(s2);

		assertEquals(1, s1.getHundreds());
		assertEquals(5, s1.getFifties());
		assertEquals(3, s1.getTwenties());
	}

	//We need to edit takeOut(ATM other) to throw errors if any value of other is greater than this atm
//	@Test (expected = IllegalArgumentException.class)
//	public void takeOutFromAnotherNegative(){
//		ATM s1 = new ATM(4,7,3);
//		ATM s2 = new ATM(3,9,0);
//		s1.takeOut(s2);
//	}

	@Test
	public void testPutIn () {
		ATM s1 = new ATM();
		s1.putIn(2, 3, 4);

		assertEquals(2, s1.getHundreds());
		assertEquals(3, s1.getFifties());
		assertEquals(4, s1.getTwenties());
	}

	@Test
	public void testPutInAnother(){
		ATM s1 = new ATM(4,7,3);
		ATM s2 = new ATM(3,2,0);
		s1.putIn(s2);

		assertEquals(7,s1.getHundreds());
		assertEquals(9,s1.getFifties());
		assertEquals(3,s1.getTwenties());
	}

	@Test
	public void testPutInTakeOut () {
		ATM s1 = new ATM();
		s1.putIn(3, 3, 2);
		s1.takeOut(1, 1, 1);

		assertEquals(2, s1.getHundreds());
		assertEquals(2, s1.getFifties());
		assertEquals(1, s1.getTwenties());
	}

	@Test
	public void testEqual () {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 5, 4);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
	}

	@Test
	public void testEquals() {
		ATM s1 = new ATM(2, 3, 4);
		ATM s2 = new ATM(2, 3, 4);
		assertTrue(ATM.equals(s1, s2));
	}

	@Test
	public void testEqualsTwoObject(){
		ATM s1 = new ATM(2, 3, 4);
		ATM s2 = new ATM(6,7,5);
		assertFalse(ATM.equals(s1,s2));
	}

	@Test
	public void compareToGreater(){
		ATM s1 = new ATM(12, 0, 0);
		ATM s2 = new ATM(6,0,0);
		assertEquals(s1.compareTo(s2),1);
	}

	@Test
	public void compareToLess(){
		ATM s1 = new ATM(2, 0, 0);
		ATM s2 = new ATM(6,0,0);
		assertEquals(s1.compareTo(s2),-1);
	}


	@Test
	public void compareToEqual(){
		ATM s1 = new ATM(2, 0, 0);
		ATM s2 = new ATM(2,0,0);
		assertEquals(s1.compareTo(s2),0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testEqualsObject(){
		ATM s1 = new ATM(2, 3, 4);
		ATM s2 = new ATM(null);
	}

	@Test
	public void testCompareTo () {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 3, 4);
		ATM s4 = new ATM(2, 5, 4);

		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s3.compareTo(s1) < 0);
		assertTrue(s1.compareTo(s4) == 0);
	}

	@Test
	public void testLoadSave () {
		ATM s1 = new ATM(6, 5, 4);
		ATM s2 = new ATM(6, 5, 4);

		s1.save("file1");
		s1 = new ATM();  // resets to zero

		s1.load("file1");
		//System.out.println(s1);
		//System.out.println(s2);
		assertTrue(s1.equals(s2));

	}


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

	// IMPORTANT: only one test per exception!!!
	// testing negative number for nickels, takeOut
	@Test
			(expected = IllegalArgumentException.class)
	public void testTakeOutNegTwenties () {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(1, 1, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegHundreds1 () {
		new ATM(-300, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegHundreds2 () {
		new ATM(300, -10, 0);
	}

	// testing negative number for quarters, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegHundreds () {
		ATM s = new ATM(2, 3, 4);
		s.putIn(-30, 2, 30);
	}


	@Test
	public void setTwenties(){
		ATM s1 = new ATM(1,0,0);
		s1.setTwenties(4);
		assertEquals(4, s1.getTwenties());
	}

	@Test (expected = IllegalArgumentException.class)
	public void setNegativeTwenties(){
		ATM s1 = new ATM(1,0,0);
		s1.setTwenties(-6);
	}

	@Test
	public void setFifties(){
		ATM s1 = new ATM(1,3,0);
		s1.setFifties(0);
		assertEquals(0, s1.getFifties());
	}

	@Test (expected = IllegalArgumentException.class)
	public void setNegativeFifties(){
		ATM s1 = new ATM(1,0,0);
		s1.setFifties(-2);
	}

	@Test
	public void setHundreds(){
		ATM s1 = new ATM(1,0,0);
		s1.setHundreds(7);
		assertEquals(7, s1.getHundreds());
	}

	@Test (expected = IllegalArgumentException.class)
	public void setNegativeHundreds(){
		ATM s1 = new ATM(1,0,0);
		s1.setHundreds(-1);
	}
}
