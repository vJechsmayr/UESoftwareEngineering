package tetrisTest;

import static org.junit.Assert.*;

import tetris.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameElementTest {
	GameLogic gl;
	GameElement ge1;
	GameElement ge2;
	GameElement ge3;
	
	@Before
	public void setUp() throws Exception {
		gl = new GameLogic(10, 20);
		ge1 = new LElement();
		ge2 = new TElement();
		ge3 = new ZElement();
		ge1.rotate(DirectionRotate.RIGHT);
		ge2.rotate(DirectionRotate.LEFT);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGameElement() {
		assertTrue(ge1 instanceof LElement);
		assertTrue(ge2 instanceof TElement);
		assertTrue(ge3 instanceof ZElement);
	}
	
	@Test
	public void testRotate(){
		assertEquals(RotateState.DEG90, ge1.getRotationState());
		assertEquals(RotateState.DEG270, ge2.getRotationState());
		assertEquals(RotateState.NORMAL, ge3.getRotationState());
		//Fehler? ge1 RotateState ist 270 und ge2 90...
	}
	
	@Test
		//False erwartet, da Elemente nicht in die CoordinateList eingetragen wurden
		//daher nur die BorderElements enthalten!
	public void testHasCollisions(){
		assertFalse(ge2.hasCollisions(gl.getUsedCoordinates()));
	}
	
	@Test
	public void testCanMove(){
		//True erwartet, da Elemente nicht in die CoordinateList eingetragen wurden
		//daher nur die BorderElements enthalten!
		assertTrue(ge2.canMove(DirectionMove.DOWN, gl.getUsedCoordinates()));
	}
	
	@Test
	public void testCanRotate(){
		//True erwartet, da Elemente nicht in die CoordinateList eingetragen wurden
		//daher nur die BorderElements enthalten!
		assertTrue(ge3.canRotate(DirectionRotate.LEFT, gl.getUsedCoordinates()));
	}

}
