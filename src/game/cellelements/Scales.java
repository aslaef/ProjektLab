package game.cellelements;

import game.cellelements.doors.Gate;
import game.roles.CellElement;
import game.roles.Movable;
import tool.LOGGER;

/**
 * A mérleg objektumok osztálya.
 */
public class Scales extends CellElement {
    /**
     * Mérleg súlyhatára
     */
    private int limit;
    /**
     * Mérlegre tett súly
     */
    private int weight = 0;
    /**
     * Mérleggel összekötött kapu referenciája
     */
    private Gate myGate;

    public Scales(int limit, Gate myGate) {
        this.limit = limit;
        this.myGate = myGate;
    }

    public Scales() {
    }

    /**
     * Súlyhatár settere
     * @param limit új súlyhatár értéke
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     *
     * @param gate
     */
    public void setGate(Gate gate){
        myGate = gate;
    }

    /**
     * Egy mozgó objektum mérlegre mozgásának érzékelése.
     * @param movable A belépő mozgó objektum referenciája
     */
    @Override
    public void acceptMovable(Movable movable) {
        weight += movable.getWeight();
        if(myGate.isClosed() && weight >= limit){
            myGate.open();
        }
    }

    /**
     * Egy mozgó objetum mérlegről elmozgásának érzékelése.
     * @param movable a mozgó objektum referenciája
     */
    @Override
    public void exitMovable(Movable movable) {
        weight -= movable.getWeight();
        if(!myGate.isClosed() && weight < limit){
            myGate.close();
        }
    }

}
