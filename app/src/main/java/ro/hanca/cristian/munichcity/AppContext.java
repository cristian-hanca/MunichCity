package ro.hanca.cristian.munichcity;

import android.location.Location;

import java.util.Map;

import ro.hanca.cristian.munichcity.helpers.DataCache;
import ro.hanca.cristian.munichcity.helpers.LocationProvider;
import ro.hanca.cristian.munichcity.helpers.MenuHandler;
import ro.hanca.cristian.munichcity.models.DaoSession;
import ro.hanca.cristian.munichcity.models.POI;

/**
 * Application Context Class.
 */
public class AppContext {

    /**
     * Reference to the single Activity of the app.
     */
    public static MainActivity activity = null;

    /**
     * Location providing service.
     */
    public static LocationProvider location = null;

    /**
     * Points of Interest Database.
     */
    public static DaoSession db = null;

    /**
     * POI currently Viewed.
     */
    public static Map.Entry<Float, POI> selected_poi = null;

    /**
     * Remember the last selection made by the user.
     */
    public static MenuHandler.HandledMenuItem selected_item =
            MenuHandler.HandledMenuItem.NEAR;

    /**
     * Data Cache Singleton.
     */
    public static final DataCache cache = new DataCache();

    /**
     * Static Class.
     */
    private AppContext() {}

}
