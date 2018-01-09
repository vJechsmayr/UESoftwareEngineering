package tetris;
/**
 * 
 * Main-Klasse - Aufruf und Start des Spiels
 * Solange kein GameOver auftritt werden neue Spielzüge erzeugt
 *
 */
public class Tetris {

	public static void main(String[] args) {
		GameLogic game = new GameLogic(10,20);
		System.out.println("Tetris gestartet!");
		
		while(!game.isGameOver())
		{
			game.printStatus();
			game.doNextMove();
			//game.printGameField();
			
			System.out.println();
		}
		
		System.out.println("Tetris beendet!");

	}

}
