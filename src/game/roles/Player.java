package game.roles;

import game.cellelements.Abyss;
import game.cellelements.Box;
import game.cellelements.ZPM;
import game.cellelements.doors.Exit;
import game.map.Cell;
import game.map.MapManager;
import game.map.Projectile;
import game.map.Quarter;

/**
 * Játékost megvalósító osztály.
 */
public class Player implements Movable {
    /**
     * Referencia a mapManagerre
     */
    private MapManager mapManager;
    /**
     * Súly
     */
    private int weight;
    /**
     * Felvett ZPM-ek száma.
     */
    private int zpmCount = 0;
    /**
     * Aktuális cella, amin a játékos van.
     */
    private Cell actualCell;
    /**
     * Irány, amerre a játékos néz.
     */
    private Quarter dir;
    /**
     * Felvett doboz referenciája.
     */
    private Box box;

    public Player() {
    }

    public Player(Cell cell, Quarter quarter, int weight, MapManager mapManager) {
        actualCell = cell;
        dir = quarter;
        this.weight = weight;
        this.mapManager = mapManager;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Paraméterül adott irányba lépés megvalósítása. Csak akkor lép, ha a az aktuális cellától lekért
     * szomszéd cella elfogadja a játékos lépési szándékát a rajta lévő cella elemektől függően.
     *
     * Ha el tud lépni, meghívja az aktuális cella exitMovable metódusát és átadja magát a következő cella
     * acceptMovable metódusának. Kilépés után már az aktuális cella is megváltozik a szomszédcellára.
     *
     * @param quarter elmozdulási irány
     */
    public void moveTo(Quarter quarter) {
        Cell nCell = actualCell.getNeighbour(quarter);
        this.dir = quarter;
        if (nCell.enterMovable(this)) {
            actualCell.exitMovable(this);
            actualCell = nCell;
            actualCell.acceptMovable(this);
        }
    }

    /**
     * Lövedék kilövése
     */
    public void shoot() {
        Projectile bullet = new Projectile(actualCell);
        bullet.launch(dir);
    }

    /**
     * Doboz felvételének első lépése. Ekkor a játékos orientációjában lévő szomszédos cellának jelzi a játékos a
     * doboz felvevési szándékát.
     */
    public void boxUp() {
        Cell nCell = actualCell.getNeighbour(dir);
        nCell.take(this);
    }

    /**
     * Paraméterül kapott doboz felvétele. Ilyenkor a játékos súlya is megnő a doboz súlyával.
     * @param box adott doboz
     */
    @Override
    public void accept(Box box) {
        box.getActualCell().removeElement(box);
        box.getActualCell().exitMovable(box);
        weight += box.getWeight();
        box.setActualCell(null);
        this.box = box;
    }

    /**
     * Doboz ledobása a játékossal szembeni cellára, ha van. Sikeres ledobás esetén játékos súlya lecsökken
     * a doboz súlyával.
     */
    public void boxDown() {
        Cell nCell = actualCell.getNeighbour(dir);
        if (box != null && nCell.enterMovable(box)) {
            nCell.addElement(box);
            box.setActualCell(nCell);
            nCell.acceptMovable(box);
            weight -= box.getWeight();
            box = null;
        }
    }

    /**
     * Szakadékkal való találkozás kezelése.
     * @param abyss szakadék
     */
    @Override
    public void meetWith(Abyss abyss) {
        System.out.println("Game Over " + this.toString() + " died");
    }

    /**
     * Kijáratba lépés kezelése.
     * @param exit
     */
    @Override
    public void meetWith(Exit exit) {
        System.out.println(this.toString() + " won the game");
    }

    /**
     * ZPM felvétele. Ha így páros számú ZPM-je lesz a játékosnak, meghívjuk a mapManager createZPM metódusát.
     * @param zpm
     */
    @Override
    public void meetWith(ZPM zpm) {
        actualCell.removeElement(zpm);
        zpm.destroy();
        if(++zpmCount % 2 == 0){
            mapManager.createZPM();
        }
    }

    /**
     * Dobozzal való játékos esetén megütközik a játékos, azaz nem tud tovább menni.
     *
     * @param box
     * @return false, a doboz játékosok számára akadály
     */
    @Override
    public boolean meetWith(Box box) {
        return false;
    }

    @Override
    public int getWeight() {
        return weight;
    }

}
