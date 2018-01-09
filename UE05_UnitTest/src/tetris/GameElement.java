package tetris;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * abstrakte Basis-Klasse für ein Spielelement
 *
 */

public abstract class GameElement {

    // Alle aktuellen Koordinaten des Elements im Spielfeld
    private List<Coordinate> coordinates;
    // Aktuelle Drehrichtung
    private RotateState rotationState;
    //Name des Elements
    private String name;
    
    /**
     * GameElement() Konstruktor
     */
    protected GameElement()
    {
        coordinates = new ArrayList<Coordinate>();
        rotationState = RotateState.NORMAL;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public void setName(String n)
    {
    	this.name = n;
    }

    /**
     * hasCollisions(List<Coordinate> elementCoordinates, List<Coordinate> usedCoordinates)
     * Überprüft, ob eine Koordinate des Elements in den belegten Koordinaten vorkommt
     * 
     * @param elementCoordinates Koordinaten des Elements
     * @param usedCoordinates Belegte Koordinaten
     * @return true, wenn eine Koordinate des Elements in den belegten Koordinaten vorkommt, sonst false
     */
    public static boolean hasCollisions(List<Coordinate> elementCoordinates, List<Coordinate> usedCoordinates)
    {
        for(Coordinate elementCoordinate : elementCoordinates)
        {
        	if(elementCoordinate.getX() < 0 || elementCoordinate.getY() < 0)
            {
               return true;
            }
        	
            for(Coordinate usedCoordinate : usedCoordinates)
            {
                if(usedCoordinate.equals(elementCoordinate))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * hasCollisions(List<Coordinate> usedCoordinates)
     * Überprüft, ob eine Koordinate des Elements in den belegten Koordinaten vorkommt
     * 
     * @param usedCoordinates Belegte Koordinaten
     * @return true, wenn eine Koordinate des Elements in den belegten Koordinaten vorkommt, sonst false
     */
    
    public boolean hasCollisions(List<Coordinate> usedCoordinates)
    {
        return GameElement.hasCollisions(getCoordinates(), usedCoordinates);
    }

    /**
     * FullRow(int rowIndex)
     * Entfernt alle Koordinaten des Elements mit der angegebenen Reihe und verschiebt alle Koordinaten, die darüber liegen, um eine Reihe nach unten
     * @param rowIndex Reihe, die entfernt werden soll
     */
    public void FullRow(int rowIndex)
    {
    	List<Coordinate> toRemove = new ArrayList<Coordinate>();
        for(Coordinate coordinate : coordinates)
        {
            if(coordinate.getY() == rowIndex)
            {
                // Koordinate an der angegebenen Reihe entfernen
                toRemove.add(coordinate);
            }
        }
        // Alle Koordinaten, die entfernt werden sollen, über eine eigene Liste abarbeiten, da während der foreach-Schleife die originale Liste nicht bearbeitet werden kann
        for(Coordinate coordinate : toRemove)
        {
            coordinates.remove(coordinate);
        }

        // Jetzt alle Koordinaten, die über der angegebenen Reihe liegen, um eine Reihe nach unten verschieben
        for(Coordinate coordinate : coordinates)
        {
            if(coordinate.getY() < rowIndex)
            {
                coordinate.setY(coordinate.getY() + 1);
            }
        }
    }

    /**
     * getCoordinates()
     * Gibt die aktuellen Koordinaten des Elements im Spielfeld zurück
     * @return Aktuelle Kooridnaten
     */
    public List<Coordinate> getCoordinates()
    {
        return coordinates;
    }

    /**
     * AddCoordinate(Coordinate coordinate)
     * Fügt die angegebene Koordinate hinzu
     * @param coordinate Neue Koordinate
     */
    protected void AddCoordinate(Coordinate coordinate)
    {
        coordinates.add(coordinate);
    }

    /**
     * getRotationState()
     * Gibt die aktuelle Drehrichtung des Elements zurück
     * @return Aktuelle Drehrichtung
     */
    public RotateState getRotationState()
    {
        return rotationState;
    }

    /**
     * setRotationState(RotateState rotationState)
     * Setzt die aktuelle Drehrichtung des Elements
     * @param rotationState Neue Drehrichtung
     */
    protected void setRotationState(RotateState rotationState)
    {
        this.rotationState = rotationState;
    }

    /**
     * canMove(DirectionMove move, List<Coordinate> usedCoordinates)
     * Überprüft, ob das Element in die angegebene Richtung bewegt werden kann
     * @param move Richtung
     * @param usedCoordinates Alle belegten Koordinaten im Spielfeld
     * @return true, wenn sich die neuen Koordinaten des Elements mit keiner belegten Koordinate überschneiden würden, sonst false
     */
    
    public boolean canMove(DirectionMove move, List<Coordinate> usedCoordinates)
    {
    	List<Coordinate> newCoordinates = new ArrayList<Coordinate>();

        if (move ==DirectionMove.LEFT)
        {
            for(Coordinate coordinate : getCoordinates())
            {
                newCoordinates.add(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
            }
        }else if (move ==DirectionMove.RIGHT)
		        {
		            for(Coordinate coordinate : getCoordinates())
		            {
		                newCoordinates.add(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
		            }
		        } else if (move ==DirectionMove.DOWN)
				        {
				            for (Coordinate coordinate : getCoordinates())
				            {
				                newCoordinates.add(new Coordinate(coordinate.getX(), coordinate.getY() + 1));
				            }
				        }

        // Überprüfen, ob die neuen Koordinaten eine Kollision erzeugen würden
        if (GameElement.hasCollisions(newCoordinates, usedCoordinates))
        {
        	//FÜR UNIT-TESTS AUSKOMMENTIERT!
        	//System.out.println("Element ist Kollidiert!");
            return false;
        }else{
        		return true;
             }
        
    }

    /**
     * move(DirectionMove move)
     * Bewegt das Element in die angegebene Richtung
     * @param move Richtung
     */
    public void move(DirectionMove move)
    {
        if (move.equals(DirectionMove.LEFT))
        {
            for(Coordinate coordinate : getCoordinates())
            {
                coordinate.setX(coordinate.getX() - 1);
            }
        }
        else if (move.equals(DirectionMove.RIGHT))
        {
            for(Coordinate coordinate : getCoordinates())
            {
                coordinate.setX(coordinate.getX() + 1);
            }
        }
        else if (move.equals(DirectionMove.DOWN))
        {
            for(Coordinate coordinate : getCoordinates())
            {
                coordinate.setY(coordinate.getY() + 1);
            }
        }
    }

    /**
     * canRotate(DirectionRotate rotate, List<Coordinate> usedCoordinates)
     * Überprüft, ob das Element in die angegebene Richtung rotiert werden kann
     * @param rotate Richtung
     * @param usedCoordinates Alle belegten Koordinaten im Spielfeld
     * @return true, wenn sich die neuen Koordinaten des Elements mit keiner belegten Koordinate überschneiden würden, sonst false
     */
    
    public boolean canRotate(DirectionRotate rotate, List<Coordinate> usedCoordinates)
    {
    	List<Coordinate> newCoordinates = new ArrayList<Coordinate>();
        RotateState newRotationState = getNewRotationState(rotate);

        UpdateCoordinates(newRotationState, newCoordinates);

        // Überprüfen, ob die neuen Koordinaten eine Kollision erzeugen würden
        if (GameElement.hasCollisions(newCoordinates, usedCoordinates))
        {
        	//FÜR UNIT-TESTS AUSKOMMENTIERT
        	//System.out.println("Element ist Kollidiert!");
            return false;
        }else
        {
        	return true;
        }
        
    }

    /**
     * rotate(DirectionRotate rotate)
     * Rotiert das Element in die angegebene Richtung
     * @param rotate Richtung
     */
    public void rotate(DirectionRotate rotate)
    {
        setRotationState(getNewRotationState(rotate));
        UpdateCoordinates(getRotationState(), getCoordinates());
    }

    /**
     * getNewRotationState(DirectionRotate rotate)
     * Liefert die neue Drehrichtung anhand der aktuellen Drehrichtung und der Richtung, in die rotiert werden soll
     * @param rotate Richtung, in die rotiert werden soll
     * @return Neue Drehrichtung
     */
    protected RotateState getNewRotationState(DirectionRotate rotate)
    {
        switch (rotate)
        {
            case LEFT:
                switch (rotationState)
                {
                    case NORMAL:
                        return RotateState.DEG90;
                    case DEG90:
                        return RotateState.DEG180;
                    case DEG180:
                        return RotateState.DEG270;
                    case DEG270:
                        return RotateState.NORMAL;
                }
                break;
            case RIGHT:
                switch (rotationState)
                {
                    case NORMAL:
                        return RotateState.DEG270;
                    case DEG90:
                        return RotateState.NORMAL;
                    case DEG180:
                        return RotateState.DEG90;
                    case DEG270:
                        return RotateState.DEG180;
                }
                break;
        }
        return RotateState.NORMAL;
    }

    /**
     * Aktualisiert die Koordinaten anhand des Mittelpunktes und der aktuellen Drehrichtung
     * @param state
     * @param coordinates Koordinaten, die aktualisiert werden sollen
     */
    protected abstract void UpdateCoordinates(RotateState state, List<Coordinate> coordinates);

	
}
