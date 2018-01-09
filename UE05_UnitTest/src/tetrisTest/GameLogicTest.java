package tetrisTest;

import static org.junit.Assert.*;

import tetris.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameLogicTest {
	
	GameLogic gl;
	@Before
	public void setUp() throws Exception {
		gl = new GameLogic(10, 20);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGameOverFalse() {
		assertFalse(gl.isGameOver());
	}
	
	@Test
	public void testGetUsedCoordinates(){
		assertNotNull(gl.getUsedCoordinates());
	}
	
	@Test
	public void testCreateRandomElement(){
		assertNotNull(gl.createRandomElement());
	}
	
	@Test
	public void testGameOverTrue(){
		while(!gl.isGameOver()){
			gl.doNextMove();
		}
		assertTrue(gl.isGameOver());
	}

}
