package game.cellelements;

import game.cellelements.doors.Exit;
import game.map.Cell;
import game.map.Projectile;
import game.roles.CellElement;
import game.roles.Movable;
/**
 * Doboz osztálya
 */
public class Box extends CellElement implements Movable {
    /**
     * Doboz súlya
     */
    private int weight;
    /**
     * Referencia a gazdacellára.
     */
    private Cell actualCell;

    public Box() {
    }

    public Box(int weight, Cell actualCell) {
        this.weight = weight;
        this.actualCell = actualCell;
    }

    public Cell getActualCell() {
        return actualCell;
    }

    public void setActualCell(Cell actualCell) {
        this.actualCell = actualCell;
    }    

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * A doboz felvételének megvalósítása.
     *
     * A paraméterül kapott mozgó objektum accept metódusának a doboz átad egy referenciát önmagáról.
     *
     * @param movable a mozgó objektum (ONeill vagy Jaffa)
     * @return önmaga
     */
    @Override
    public boolean take(Movable movable) {
        movable.accept(this);
        return true;
    }

    /**
     * Egy mozgó objektum a gazda cellára lépési szándékát jelzi.
     *
     * @param movable a mozgó objektum referenciája
     * @return a mozgó objektumtól függően vagy akadály, vagy nem akadályként funkcionál
     */
    @Override
    public boolean enterMovable(Movable movable) {     //lekérdezzük, hogy jöhet-e Movable
        return meetWith(this);
    }

    /**
     * Akadályjelzés egy becsapódó lövedéknek.
     * @param projectile a lövedék
     * @return true, hisz a doboz megállítja a lövedéket
     */
    @Override
    public boolean obstacleForProjectile(Projectile projectile) {
        return true;
    }

    /**
     * A szakadékba esést kezeli le.
     *
     * A gazdacella listájából kiszedeti önmagát és ezáltal megszűnik a játék szempontjából.
     * @param abyss
     */
    @Override
    public void meetWith(Abyss abyss) {
        actualCell.removeElement(this);
    }

    /**
     * #dontcare
     *
     * @param exit
     */
    @Override
    public void meetWith(Exit exit) {
    }

    /**
     * ZPM-el való találkozás esetén hívódik meg.
     * Ez esetben a doboz ráesett egy ZPM-re, így a ZPM eltört. Tehát a doboz kiszedeti a gazdacella listájából
     * a ZPM-et a removeElement metódus meghívásával.
     * @param zpm az eltörni készülő ZPM
     */
    @Override
    public void meetWith(ZPM zpm) {
        actualCell.removeElement(zpm);
        zpm.destroy();
    }

    /**
     * Ha egy dobozra hullik az adott doboz, akkor nincs gond, a dobozok stack-szerűen pakolhatok cellákra.
     *
     * @param box
     * @return true
     */
    @Override
    public boolean meetWith(Box box) {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * Interfész miatt kötelező, egyébként helyes működés esetén egy sosem be nem álló esemény.
     * @param box
     */
    @Override
    public void accept(Box box) {
        throw new UnsupportedOperationException("box shuldn't try to take another box");
    }

}
