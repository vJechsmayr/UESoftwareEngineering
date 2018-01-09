package tetris;
import java.util.List;

/**
 * Stellt einen Würfel dar
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
            super.setName("O/ Würfel");
        }

        @Override
        protected void UpdateCoordinates(RotateState state, List<Coordinate> coordinates)
        {
            // Beim Drehen des Würfels ändern sich die Koordinaten nicht
        }
    }