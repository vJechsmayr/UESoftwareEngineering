import java.util.List;

/**
 * Stellt ein J dar
 */
    public class JElement extends GameElement
    {
        // Zentrum, um das rotiert wird
        private Coordinate centerCoordinate;

        public JElement()
        {
            centerCoordinate = new Coordinate(4, 1);
            super.setName("J");
            UpdateCoordinates(getRotationState(), getCoordinates());
        }

        @Override
        protected void UpdateCoordinates(RotateState state, List<Coordinate> coordinates)
        {
            coordinates.clear();
            coordinates.add(centerCoordinate);
            switch (state)
            {
                case NORMAL:
                    coordinates.add(new Coordinate(centerCoordinate.getX() - 1, centerCoordinate.getY()));
                    coordinates.add(new Coordinate(centerCoordinate.getX() - 1, centerCoordinate.getY() - 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX() + 1, centerCoordinate.getY()));
                    break;
                case DEG90:
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() - 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX() + 1, centerCoordinate.getY() - 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() + 1));
                    break;
                case DEG180:
                    coordinates.add(new Coordinate(centerCoordinate.getX() - 1, centerCoordinate.getY()));
                    coordinates.add(new Coordinate(centerCoordinate.getX() + 1, centerCoordinate.getY()));
                    coordinates.add(new Coordinate(centerCoordinate.getX() + 1, centerCoordinate.getY() + 1));
                    break;
                case DEG270:
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() - 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX(), centerCoordinate.getY() + 1));
                    coordinates.add(new Coordinate(centerCoordinate.getX() - 1, centerCoordinate.getY() + 1));
                    break;
            }
        }
    }