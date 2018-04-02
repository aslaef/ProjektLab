package game.roles;

import game.cellelements.Abyss;
import game.cellelements.Box;
import game.cellelements.ZPM;
import game.cellelements.doors.Exit;

/**
 * A mozgó objektumok osztályainak interfésze
 */
public interface Movable {
    /**
     * A mozgó objektum szakadékba esését kezeli.
     * @param abyss
     */
    public void meetWith(Abyss abyss);

    /**
     * A kijáratba lépés esetét kezeli.
     *
     * @param exit
     */
    public void meetWith(Exit exit);

    /**
     * ZPM-el való találkozás esetét kezeli.
     *
     * @param zpm
     */
    public void meetWith(ZPM zpm);

    /**
     * Dobozzal való találkozás esetét kezeli.
     * @param box
     * @return
     */
    public boolean meetWith(Box box);

    /**
     * Doboz felvétele.
     * @param box adott doboz
     */
    public void accept(Box box);

    /**
     * Súly gettere
     * @return a movable súlya
     */
    public int getWeight();
    
}
