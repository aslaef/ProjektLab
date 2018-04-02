package game.map;

/**
 * Az irányok felsorolása
 */
public enum Quarter {

    EAST,
    WEST,
    SOUTH,
    NORTH;

    /**
     * A függvény visszaadja az aktuális irány ellentetettjét
     * @return
     */
    public Quarter opposite(){
        switch (this) {
            case NORTH:
                return Quarter.SOUTH;
            case SOUTH:
                return Quarter.NORTH;
            case EAST:
                return Quarter.WEST;
            case WEST:
                return Quarter.EAST;
        }
        throw new IllegalStateException("Unknown quarter");
    }
    
}