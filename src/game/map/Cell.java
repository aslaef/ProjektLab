package game.map;

import game.roles.CellElement;
import game.roles.Movable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tool.LOGGER;

/**
 * Mező vagy cella osztálya. Önmagában padlózatként funkcionál.
 */
public class Cell {
    /**
     * Szomszédos cellák tárolása iránnyal
     */
    private final Map<Quarter, Cell> neighbours;
    /**
     * A cellán lévő elemek listája.
     */
    private final List<CellElement> elementList;

    public Cell() {  //Default constructor
        elementList = new ArrayList<>();
        neighbours = new HashMap<>();
    }

    /**
     * Minden cella elemre meghívja a take metódust. Ez lényegében csak doboznak szól, hogyha van, hogy vetesse fel
     * magát a paraméterül kapott és adott játékossal.
     *
     * @param movable a játékos, amely a doboz felvevési szándékát jelezte a cellának
     */
    public void take(Movable movable) {
        for (int i = elementList.size() - 1; i >= 0; i--) {
            if (elementList.get(i).take(movable)) {
                return;
            }
        }
    }

    /**
     * Szomszédos cella lekérése paraméterül kapott irányra.
     *
     * @param quarter a szomszédos cella iránya
     * @return adott irányban lévő szomszédos cella
     */
    public Cell getNeighbour(Quarter quarter) {//Szomszéd irány szerinti lekérése
        return neighbours.get(quarter);
    }

    public boolean testProjectile(Projectile projectile) {//Lekérdezzük, hogy akadály-e a lövedéknek a rajta lévő Elementek
        boolean obstacle = false;
        for (int i = 0; i < elementList.size(); i++) {
            obstacle = obstacle || elementList.get(i).obstacleForProjectile(projectile);
        }

        return obstacle;
    }

    /**
     * Minden cella elemre meghívja az enterMovable metódust, amely által az elemek reagálhatnak, ha akadályként
     * működnek a paraméterül kapott, majd adott mozgó objektumra nézve.
     *
     * @param movable
     * @return a cella elemek válasza. csak akkor true, ha semelyik elem sem akadály
     */
    public boolean enterMovable(Movable movable) {
        boolean ret = true;
        for (int i = 0; i < elementList.size(); i++) {
            ret = ret && elementList.get(i).enterMovable(movable);
        }

        return ret;
    }

    /**
     * Minden cella elemre meghívja az exitMovable metódust, amely által az elemek reagálhatnak a paraméterül kapott,
     * majd adott mozgó objektum lelépésére.
     *
     * @param movable a celláról lelépő mozgó objektum
     */
    public void exitMovable(Movable movable) {
        for (int i = 0; i < elementList.size(); i++) {
            elementList.get(i).exitMovable(movable);
        }

    }

    /**
     * Minden cella elemre meghívja az acceptMovable metódust, amely által az elemek reagálhatnak a paraméterül kapott,
     * majd adott mozgó objektum cellára lépésére.
     *
     * @param movable a cellára lépő mozgó objektum
     */
    public void acceptMovable(Movable movable) {
        for (CellElement cellElement : elementList) {
            cellElement.acceptMovable(movable);
        }
    }

    /**
     * A paraméterül kapott irányban lévő szomszéd felülírása a paraméterül kapott cellára
     *
     * @param quarter az új szomszéd cella iránya
     * @param cell az új szomszéd cella
     */
    public void setNeighbour(Quarter quarter, Cell cell) {
        neighbours.put(quarter, cell);
    }

    /**
     * A paraméterül kapott elem felvétele az elemlistába.
     *
     * @param cellElement
     */
    public void addElement(CellElement cellElement) {
        elementList.add(cellElement);
    }//Új CellElement ráadása a cellára

    /**
     * A paraméterül kapott elem levétele az elemlistáról.
     *
     * @param cellElement
     */
    public void removeElement(CellElement cellElement) {
        elementList.remove(cellElement);
    }

}
