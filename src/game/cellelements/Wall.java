package game.cellelements;

import game.map.Projectile;
import game.roles.CellElement;
import game.roles.Movable;

/**
 * Fal, minden más falnak az ősosztálya, ugyanakkor egy mező elem.
 */
public class Wall extends CellElement {
    /**
     * Egy mozgó objektum azonos cellára lépési szándékát jelzi.
     *
     * @param movable a mozgó objektum, ami be szándékozik lépni
     * @return false, egy mozgó objektum sosem lehet falban
     */
    @Override
    public boolean enterMovable(Movable movable) {
        return false;
    }

    /**
     * Akadályjelzés egy becsapódó lövedéknek.
     *
     * @param projectile a lövedék
     * @return true, a fal egy akadály
     */
    @Override
    public boolean obstacleForProjectile(Projectile projectile) {//Lekérdezzük, hogy akadály-e a lövedéknek
        return true;
    }

}
