package game.map;

/**
 * Lövedék osztálya
 */
public class Projectile {
    /**
     * Aktuális cella, amin a lövedék található.
     */
    Cell aCell;
    /**
     * Lövedék színe
     */
    Color color;
    /**
     * Lövedék haladási iránya.
     */
    Quarter quarter;

    public Projectile(Cell cell) {//konsrtuktor
        this.aCell = cell;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Lövedék elindítása. Az adott irányba folyamatosan lekéri a következő cellára lépés lehetőségét a következő
     * cella testProjectile metódusával és pozitív visszajelzés esetén megváltoztatja az aktuális celláját a szomszéd
     * cellájéra.
     *
     * @param quarter a lövedék haladási iránya
     */
    public void launch(Quarter quarter) {
        this.quarter = quarter;
        Cell nCell = aCell.getNeighbour(quarter);
        while (!nCell.testProjectile(this)) {
            aCell = nCell;
            nCell = aCell.getNeighbour(quarter);
        }
    }

    /**
     * Lövedék színének fajtái
     */
    public static enum Color {
        BLUE,
        YELLOW,
        RED,
        GREEN;
        
        public Color getPair(){
            switch(this){
                case BLUE:
                    return YELLOW;
                case YELLOW:
                    return BLUE;
                case GREEN:
                    return RED;
                case RED:
                    return GREEN;
            }
            throw new IllegalStateException("Unknown color");
        }
    }

}
