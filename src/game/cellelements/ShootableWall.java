package game.cellelements;

import game.map.Cell;
import game.map.Projectile;
import game.map.Quarter;
import java.util.HashMap;
import java.util.Map;

/**
 * Lőhető fal osztálya. A Fal osztály leszármazottja.
 */
public class ShootableWall extends Wall {
    /**
     * A portálokat tároló statikus változó. Tárolja a portálobjektumokat és azok színét.
     */
    private static final Map<Projectile.Color, Portal> PORTALS = new HashMap<>();
    /**
     * Az adott fal cellája.
     */
    private final Cell myCell;

    /**
     * A paraméterül kapott cellát elmenti, mint saját cellát.
     *
     * @param cell a cella, amin a fal található
     */
    public ShootableWall(Cell cell) {
        myCell = cell;
    }
    

    /**
     * Létrehoz egy portált, szükség esetén át- illetve visszaálltja a megfelelő celláknak a szomszédait.
     *
     * @param projectile az adott lövedék, amely a falba csapódik be
     * @return true, a fal egy akadály a lövedék számára
     */
    @Override
    public boolean obstacleForProjectile(Projectile projectile) {
        
        Portal pairPortal = PORTALS.get(projectile.getColor().getPair());
        pairPortal.resetNeighbours();
        Portal createdPortal = new Portal(myCell, projectile.getQuarter());
        PORTALS.put(projectile.getColor(), createdPortal);
        createdPortal.createWormhole(PORTALS.get(projectile.getColor().getPair()));       
        
        return true;
    }

    /**
     * A portál osztálya.
     */
    private final class Portal {
        /**
         * Melyik mezőn van a portál
         */
        private Cell cell;
        /**
         * A falnak melyik oldalán van a portál.
         */
        private Quarter quarter;
        /**
         * Az a szomszédos mező, amelyre nyílik a portál.
         */
        private Cell neighbourCell;

        public Portal(Cell cell, Quarter quarter) {
            this.cell = cell;
            this.quarter = quarter;
            neighbourCell = cell.getNeighbour(quarter);
        }

        /**
         * Féregjárat létrehozása. A paraméterül kapott portállal létrehoz egy féregjáratot, azaz átállítja
         * a celláknak a portál felőli szomszédságát egymásra.
         *
         * @param otherPortal a féregjárat másik vége
         */
        public void createWormhole(Portal otherPortal){
            if(otherPortal == null) return;
            neighbourCell.setNeighbour(quarter.opposite(), otherPortal.getNeighbourCell());
            otherPortal.getNeighbourCell().setNeighbour(otherPortal.getQuarter().opposite(), neighbourCell);
        }

        /**
         * Visszaállítja a szomszéd (portálra nyíló) cella szomszédságát a portál cellájára.
         */
        public void resetNeighbours(){
            neighbourCell.setNeighbour(quarter.opposite(), cell);
        }

        /**
         *
         * @return
         */
        public Cell getNeighbourCell() {
            return neighbourCell;
        }

        /**
         * @param neighbourCell
         */
        public void setNeighbourCell(Cell neighbourCell) {
            this.neighbourCell = neighbourCell;
        }

        public Cell getCell() {
            return cell;
        }

        public void setCell(Cell cell) {
            this.cell = cell;
        }

        public Quarter getQuarter() {
            return quarter;
        }

        public void setQuarter(Quarter quarter) {
            this.quarter = quarter;
        }

    }
}
