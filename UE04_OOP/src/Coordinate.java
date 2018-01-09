/**
 * 
 * Klasse für die Koordinaten eines Steins
 *
 */
    public class Coordinate
    {
        private int x;
        private int y;

        /**
         * Coordinate(int x, int y) Konstruktor
         * @param x
         * @param y
         */
        public Coordinate(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getX()
        {
            return x;
        }

        public void setX(int x)
        {
            this.x = x;
        }

        public int getY()
        {
            return y;
        }

        public void setY(int y)
        {
            this.y = y;
        }

        @Override
        public boolean equals(Object obj)
        {
            if(!(obj instanceof Coordinate))
            {
                return false;
            }

            Coordinate oC = (Coordinate)obj;
            if(oC.getX() == this.getX() && oC.getY() == this.getY())
            {
                return true;
            }

            return false;
        }
        
        @Override
        public String toString()
        {
            return "[" + getX() + ";" + getY() + "]";
        }
    }
