package tetrisTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tetris.*;

public class BorderElementTest {
	BorderElement bo;
	@Before
	public void setUp() throws Exception {
		bo = new BorderElement(10, 20);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanMove() {
		assertEquals(false, bo.canMove(DirectionMove.LEFT, bo.getCoordinates()));
	}
	@Test
	public void testCanRotate() {
		assertEquals(false, bo.canRotate(DirectionRotate.LEFT, bo.getCoordinates()));
	}
}
