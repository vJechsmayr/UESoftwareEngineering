package tetris;
import java.util.List;


/**
 * 
 * Stellt ein T, ein Dreieck bzw. eine Pyramide dar
 *
 */
    public class TElement extends GameElement
    {
        // Zentrum, um das rotiert wird
        private Coordinate centerCoordinate;

        public TElement()
        {
            centerCoordinate = new Coordinate(5, 1);
            super.setName("T");
            UpdateCoordinates(getRotationState(), getCoordinates());
        }

        @Override
        protected void UpdateCoordinates(RotateState state, List<Coordinate> coordinates)
        {
            coordinates.clear();
            coordinates.add(centerCoordinate);
            switch(state)
            {
                case NORMAL:
                    coordinates.add(new Coordinate(centerCoordinate.getX() - 1, centerCoordinate.getY()));
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() - 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX() + 1, centerCoordinate.getY()));
                    break;
                case DEG90:
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() - 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX() + 1, centerCoordinate.getY()));
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() + 1));
                    break;
                case DEG180:
                    coordinates.add(new Coordinate(centerCoordinate.getX() - 1, centerCoordinate.getY()));
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() + 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX() + 1, centerCoordinate.getY()));
                    break;
                case DEG270:
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() - 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX() - 1, centerCoordinate.getY()));
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() + 1));
                    break;
            }
        }
    }