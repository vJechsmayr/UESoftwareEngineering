import java.util.List;

public class BorderElement extends GameElement{

	/**
	 * BorderElement(int x, int y) Kontruktor
	 * Wird als Rahmenelement verwendet
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 */
    public BorderElement(int x, int y)
    {
        AddCoordinate(new Coordinate(x, y));
    }

    @Override
    public void FullRow(int rowIndex)
    {
        // Ein Rahmen-Element wird bei einer vollen Reihe nicht verschoben
    }

    @Override
    public boolean canMove(DirectionMove move, List<Coordinate> usedCoordinates)
    {
        // Ein Rahmen-Element kann nicht verschoben werden
        return false;
    }

    @Override
    public void move(DirectionMove move)
    {
        // Ein Rahmen-Element kann nicht verschoben werden
    }

    @Override
    public boolean canRotate(DirectionRotate rotate, List<Coordinate> usedCoordinates)
    {
        // Ein Rahmen-Element kann nicht rotiert werden
        return false;
    }

    @Override
    public void rotate(DirectionRotate rotate)
    {
        // Ein Rahmen-Element kann nicht rotiert werden
    }

    @Override
    protected void UpdateCoordinates(RotateState state, List<Coordinate> listToUpdate)
    {
        // Ein Rahmen-Element kann nicht rotiert werden
    }

}
