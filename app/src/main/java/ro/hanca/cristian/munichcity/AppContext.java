package ro.hanca.cristian.munichcity;

import ro.hanca.cristian.munichcity.helpers.MenuHandler;
import ro.hanca.cristian.munichcity.models.DaoSession;

/**
 * Application Context Class.
 */
public class AppContext {

    /**
     * Reference to the single Activity of the app.
     */
    public static MainActivity activity = null;

    /**
     * Points of Interest Database.
     */
    public static DaoSession db = null;

    /**
     * Remember the last selection made by the user.
     */
    public static MenuHandler.HandledMenuItem selected_item =
            MenuHandler.HandledMenuItem.NEAR;

    /**
     * Static Class.
     */
    private AppContext() {}

}
