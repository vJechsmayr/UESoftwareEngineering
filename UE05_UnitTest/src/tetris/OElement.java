package tetris;
import java.util.List;

/**
 * Stellt einen W�rfel dar
 *
 */
    public class OElement extends GameElement
    {
        public OElement()
        {
            AddCoordinate(new Coordinate(4, 0));
            AddCoordinate(new Coordinate(4, 1));
            AddCoordinate(new Coordinate(5, 0));
            AddCoordinate(new Coordinate(5, 1));
            super.setName("O/ W�rfel");
        }

        @Override
        protected void UpdateCoordinates(RotateState state, List<Coordinate> coordinates)
        {
            // Beim Drehen des W�rfels �ndern sich die Koordinaten nicht
        }
    }