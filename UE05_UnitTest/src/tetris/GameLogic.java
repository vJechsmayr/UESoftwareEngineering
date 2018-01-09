package tetris;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {

	private GameElement currentElement; //aktueller Stein
	private List<GameElement> gameElements;
	private int width;
	private int height;
	private boolean gameOver;
	
	/**
	 * GameLogic(int w, int h) Konstruktor
	 * Die Koordinate (0,0) bezeichnet die linke, obere Ecke des Spielfeldes,
	 * die Koordinate (width-1, height-1) die rechte, untere Ecke
	 * @param w Breite des Spielfeldes
	 * @param h H�he des Spielfeldes
	 */
	public GameLogic(int w, int h)
	{
		this.gameElements = new ArrayList<GameElement>();
		this.width = w;
		this.height = h;
		this.gameOver = false;
		
		//Rahmen erzeugen
		for(int i=0;i<h;i++)
		{
			gameElements.add(new BorderElement(0,i)); //Links
			gameElements.add(new BorderElement(w-1,i)); //Rechts	
		}
		
		for(int j=0;j<w;j++)
		{
			gameElements.add(new BorderElement(j, h-1)); //Unten
		}
	}

	/**
	 * isGameOver()
	 * Gibt an, ob das Spiel vorbei ist
	 * @return true, wenn das Spiel vorbei ist, sonst false
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * doNextMove()
	 * F�hrt den n�chsten Spielzug aus und �berpr�ft danach, ob das Spiel vorbei ist
	 */
	public void doNextMove()
    {
        if (currentElement == null)
        {
            // Ein neues Element erzeugen, wenn kein aktives Element vorhanden ist
            currentElement = createRandomElement();
            //F�R UNIT-TESTS AUSKOMMENTIERT
            //System.out.println(currentElement.getName() + " erzeugt");
            if (currentElement.hasCollisions(getUsedCoordinates()))
            {
                // Wenn das Element beim Erzeugen eine Kollision ausl�st ist das Spiel vorbei
                gameOver = true;
                //F�R UNIT-TESTS AUSKOMMENTIERT
                //System.out.println("Element ist Kollidiert!");
                return;
            }
        }
        
        // Das Element f�hrt, wenn m�glich, eine zuf�llige Bewegung aus
        // Zufallszahl von 0 - 3 erzeugen
        int random = (int)(Math.random() * 3);
        switch (random)
        {
            case 0:
                // Nach links bewegen
                if (currentElement.canMove(DirectionMove.LEFT, getUsedCoordinates()))
                {
                    currentElement.move(DirectionMove.LEFT);
                    //F�R UNIT-TESTS AUSKOMMENTIERT
                    //System.out.println("Element nach links bewegt");
                }else
	                {
                		//F�R UNIT-TESTS AUSKOMMENTIERT
                		//System.out.println("Element kann nicht nach links bewegt werden");
	                }
                break;
            case 1:
                // Nach rechts bewegen
                if (currentElement.canMove(DirectionMove.RIGHT, getUsedCoordinates()))
                {
                    currentElement.move(DirectionMove.RIGHT);
                    //F�R UNIT-TESTS AUSKOMMENTIERT
                    //System.out.println("Element nach rechts bewegt");
                } else
	                {
                		//F�R UNIT-TESTS AUSKOMMENTIERT
                		//System.out.println("Element kann nicht nach rechts bewegt werden");
	                }
                break;
            case 2:
                // Im Uhrzeigersinn rotieren
                if (currentElement.canRotate(DirectionRotate.LEFT, getUsedCoordinates()))
                {
                    currentElement.rotate(DirectionRotate.LEFT);
                    //F�R UNIT-TESTS AUSKOMMENTIERT
                    //System.out.println("Element im Uhrzeigersinn gedreht");
                }else
	                {
                		//F�R UNIT-TESTS AUSKOMMENTIERT
                		//System.out.println("Element kann nicht im Uhrzeigersinn gedreht werden");
	                }
                break;
            default:
                // Gegen den Uhrzeigersinn rotieren
                if (currentElement.canRotate(DirectionRotate.RIGHT, getUsedCoordinates()))
                {
                    currentElement.rotate(DirectionRotate.RIGHT);
                    //F�R UNIT-TESTS AUSKOMMENTIERT
                    //System.out.println("Element gegen den Uhrzeigersinn gedreht");
                }else
	                {
                		//F�R UNIT-TESTS AUSKOMMENTIERT
                		//System.out.println("Element kann nicht gegen den Uhrzeigersinn gedreht werden");
	                }
                break;
        }
        
        // Das Element wird in jedem Spielzug nach unten bewegt
        if (currentElement.canMove(DirectionMove.DOWN, getUsedCoordinates()))
        {
           currentElement.move(DirectionMove.DOWN);
        }else
	        {
	            // Element ist unten angelangt
        		//F�R UNIT-TESTS AUSKOMMENTIERT
        		//System.out.println("Element fixiert");
	            gameElements.add(currentElement);
	            currentElement = null;
	            
	            
	            // Auf volle Reihen �berpr�fen (von unten nach oben, dabei bei der vorletzten Reihe anfangen --> letzte Reihe ist der Rahmen)
	            int row = height - 2;
	            List<Coordinate> usedCoordinates = getUsedCoordinates();
	            while (row > 0)
	            {
	                // Pro Reihe die Koordinaten z�hlen, die auf der Reihe liegen
	                int count = 0;
	                for(Coordinate coordinate : usedCoordinates)
	                {
	                    if (coordinate.getY() == row)
	                    {
	                        count++;
	                    }
	                }
	                // Ist die Anzahl dieser Koordinaten gleich der Breite des Spielfeldes, ist die Reihe voll
	                if (count == width)
	                {
	                    for(GameElement element : gameElements)
	                    {
	                        // Jedes Element soll jetzt seine Koordinaten auf dieser Reihe entfernen und die dar�berliegenden nach unten verschieben
	                    	element.FullRow(row);
	                    }
	                    //F�R UNIT-TESTS AUSKOMMENTIERT
	                    //System.out.println("-- Reihe aufgel�st: " + row);
	                    // Die aktuelle Reihe beim n�chsten Durchlauf noch einmal �berpr�fen, da die dar�berliegenden Reihen einmal nach unten verschoben wurden
	                    usedCoordinates = getUsedCoordinates();
	                }
	                else
	                {
	                    // N�chste Zeile �berpr�fen
	                    row--;
	                }
	            }
	        }
    }

	/**
	 * printStatus()
	 * Gibt den aktuellen Spielzustand aus,
	 * auf welchen Koordinaten der aktuelle Stein sich befindet
	 */
	public void printStatus()
	{
		//System.out.println("Hallo Status!");
		List<Coordinate> coordinates = getUsedCoordinates();
		 
	        for (int y = 0; y < height; y++)
	        {
	            for (int x = 0; x < width; x++)
	            {
	                boolean found = false;
	                for(Coordinate coordinate : coordinates)
	                {
	                    if (coordinate.getX() == x &&
	                        coordinate.getY() == y)
	                    {
	                        found = true;
	                        break;
	                    }
	                }
	                if (found)
	                {
	                    // Nicht mehr bewegbare Elemente - Wand wird nicht ausgegeben
	                   // System.out.print("X");
	                }else if (currentElement != null)
			                {
			                    for(Coordinate coordinate : currentElement.getCoordinates())
			                    {
			                        if (coordinate.getX() == x && coordinate.getY() == y)
			                        {
			                            // Aktuelles Element
			                        	//System.out.print("O");
			                        	System.out.println("Element bei Position: " + x + "/" + y);
			                            found = true;
			                            break;
			                        }
			                    }
			                }
	            }
	        }
	}
	
	/**
	 * createRandomElement()
	 * Erzeugt ein neues, zuf�lliges Element
	 * @return Neues Element (IElement, JElement, LElement, OElement, SElement, TElement, ZElement)
	 */
    //F�r Unit-Tests von private auf public ge�ndert!
	public GameElement createRandomElement()
    {
        // Zufallszahl von 0 - 6 erzeugen
        int random = (int)(Math.random()*6);
        switch (random)
        {
            case 0:
                return new IElement();
            case 1:
                return new JElement();
            case 2:
                return new LElement();
            case 3:
                return new OElement();
            case 4:
                return new SElement();
            case 5:
                return new TElement();
            default:
                return new ZElement();
        }
    }
    
    /**
     * getUsedCoordinates()
     * Liefert die belegten Koordinaten im Spiel
     * @return belegte Koordinate
     */
    //Modifier von private auf public f�r Unit-Tests
    public List<Coordinate> getUsedCoordinates()
    {
    	List<Coordinate> usedCoordinates = new ArrayList<Coordinate>();
        for(GameElement element : gameElements)
        {
            // Alle Koordinaten eines jeden Elements in die Liste einf�gen
            for(Coordinate coordinate : element.getCoordinates())
            {
                usedCoordinates.add(coordinate);
            }
        }
        return usedCoordinates;
    }
	
    /**
     * Zeichnet das Spielfeld auf
     * im Entwicklungsprozess zur Kontrolle der Position ben�tigt
     */
	public void printGameField()
    {
        List<Coordinate> coordinates = getUsedCoordinates();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                boolean found = false;
                for(Coordinate coordinate : coordinates)
                {
                    if (coordinate.getX() == x &&
                        coordinate.getY() == y)
                    {
                        found = true;
                        break;
                    }
                }
                if (found)
                {
                    // Nicht mehr bewegbare Elemente - Wand wird nicht ausgegeben
                    System.out.print("X");
                }else if (currentElement != null)
		                {
		                    for(Coordinate coordinate : currentElement.getCoordinates())
		                    {
		                        if (coordinate.getX() == x &&
		                        coordinate.getY() == y)
		                        {
		                            // Aktuelles Element
		                        	System.out.print("O");
		                            found = true;
		                            break;
		                        }
		                    }
		                }
                
                if(!found)
                {
                	System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
