package ro.hanca.cristian.munichcity;

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
     * Static Class.
     */
    private AppContext() {}

}
