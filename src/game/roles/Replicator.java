package game.roles;

import game.cellelements.Abyss;
import game.cellelements.Box;
import game.cellelements.ZPM;
import game.cellelements.doors.Exit;
import game.map.Cell;
import game.map.Projectile;
import game.map.Quarter;

/**
 * Replikátor osztálya
 * @author Rottenhoffer
 */
public class Replicator extends CellElement implements Runnable, Movable{
    /**
     * Aktuális cella, amin a replikátor van.
     */
    private Cell actualCell;

    public Replicator(Cell actualCell) {
        this.actualCell = actualCell;
    }

    /**
     * A random, időszerű mozgást megvalósító metódus.
     */
    @Override
    public void run() {
        //todo
    }

    /**
     * Paraméterül adott irányba lépés megvalósítása. Csak akkor lép, ha a az aktuális cellától lekért
     * szomszéd cella elfogadja a replikátor lépési szándékát a rajta lévő cella elemektől függően.
     *
     * Ha el tud lépni, meghívja az aktuális cella exitMovable metódusát és átadja magát a következő cella
     * acceptMovable metódusának. Kilépés után már az aktuális cella is megváltozik a szomszédcellára.
     *
     * @param quarter elmozdulási irány
     */
    public void moveTo(Quarter quarter){
        Cell neighbourCell = actualCell.getNeighbour(quarter);
        if(neighbourCell.enterMovable(this)){
            actualCell.exitMovable(this);
            actualCell = neighbourCell;
            actualCell.acceptMovable(this);
        }        
    }

    /**
     * Lövedékkel ütközés esetének kezelése. Ekkor a replikátor elpusztul, azaz meghívja az aktuális cella
     * removeElement metódusát önmagára.
     * @param projectile
     * @return
     */
    @Override
    public boolean obstacleForProjectile(Projectile projectile){
        actualCell.removeElement(this);
        return true;
    }

    /**
     * Szakadékba lépés esete. Ekkor elpusztul a replikátor, azaz meghívja az aktuális cella
     * removeElement metódusát önmagára, és elpusztítja a paraméterül kapott szakadékot is.
     *
     * @param abyss szakadék
     */
    @Override
    public void meetWith(Abyss abyss) {
        actualCell.removeElement(abyss);
        actualCell.removeElement(this);
    }

    /**
     * Kijáratra lépés esete. Ilyenkor nem történik semmi extra.
     * @param exit
     */
    @Override
    public void meetWith(Exit exit) {
        //don't care
    }

    /**
     * ZPM-el való találkozás esete. Ilyenkor nem történik semmi extra.
     * @param zpm
     */
    @Override
    public void meetWith(ZPM zpm) {
        //don't care
    }

    /**
     * Dobozra lépés esete. A doboz akadály a replikátor számára.
     * @param box
     * @return
     */
    @Override
    public boolean meetWith(Box box) {
        return false;
    }

    /**
     * Interfész miatt kötelező, egyébként helyes működés esetén egy sosem be nem álló esemény.
     * @param box adott doboz
     */
    @Override
    public void accept(Box box) {
        throw new UnsupportedOperationException("Replicator cannot take box");
    }

    @Override
    public int getWeight() {
        return 0;
    }

    
}
