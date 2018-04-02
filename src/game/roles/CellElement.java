package game.roles;

import game.map.Projectile;
import tool.LOGGER;

/**
 * Minden mező elemnek az ősosztálya.
 */
public abstract class CellElement {

    /**
     *
     * @param movable
     */
    public void acceptMovable(Movable movable) {
    }

    /**
     * @param projectile
     * @return
     */
    public boolean obstacleForProjectile(Projectile projectile) {//Lekérdezzük, hogy akadály-e a lövedéknek
        return false;
    }

    /**
     * @param movable the value of movable
     * @return the boolean
     */
    public boolean enterMovable(Movable movable) {//lekérdezzük, hogy jöhet-e Movable
        return true;
    }

    /**
     *
     * @param movable the value of par0
     */
    public void exitMovable(Movable movable) {//Jelezzük, hogy elment a Movable
        LOGGER.log(this);
    }

    /**
     *
     * @param movable
     * @return the boolean 
     */
    public boolean take(Movable movable) {
        return false;
    }//A doboz felvételéhez szükséges függvény
    
}
