package game.cellelements.doors;

import game.map.Projectile;
import game.roles.CellElement;
import game.roles.Movable;
import tool.LOGGER;

/**
 *
 */
public class Gate extends CellElement {

    protected boolean isClosed;//zárva vagy nyitva van

    public Gate() {
        isClosed = false;
    }// Default constructor

    public boolean isClosed() {//lekérdezzük, hogy zárva van-e
        return isClosed;
    }

    @Override
    public boolean obstacleForProjectile(Projectile projectile) {//Lekérdezzük, hogy akadály-e a lövedéknek
        return isClosed;
    }

    @Override
    public boolean enterMovable(Movable movable) {//lekérdezzük, hogy jöhet-e Movable
        return !isClosed;
    }
    
    public void open(){
        isClosed = false;
    }
    
    public void close(){
        isClosed = true;
    }

}
