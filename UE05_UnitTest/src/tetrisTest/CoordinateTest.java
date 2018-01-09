package tetrisTest;

import static org.junit.Assert.*;

import tetris.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {
	
	Coordinate co;
	Coordinate coe1;
	Coordinate coe2;
	
	@Before
	public void setUp() throws Exception {
		co = new Coordinate(9, 14);
		coe1 = new Coordinate(9, 14);
		coe2 = new Coordinate(9, 13);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testX() {
		assertEquals(9, co.getX());
	}
	@Test
	public void testY() {
		assertEquals(14, co.getY());
	}
	@Test
	public void testEquals() {
		assertEquals(true, co.equals(coe1));
		assertEquals(false, co.equals(coe2));
	}
	
	@Test 
	public void testToString(){
		assertEquals("[9;13]", coe2.toString());
	}

}
