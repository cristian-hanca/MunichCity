package ro.hanca.cristian.munichcity.helpers;

import android.view.Menu;
import android.view.MenuItem;

import ro.hanca.cristian.munichcity.R;

public class MenuHandler {

    private final Menu menu;
    private final Actions actions;

    private MenuItem near;
    private MenuItem search;
    private MenuItem trip;
    private MenuItem info;

    private static final int nearId = R.id.menu_near;
    private static final int searchId = R.id.menu_search;
    private static final int tripId = R.id.menu_trip;
    private static final int infoId = R.id.menu_info;

    public MenuHandler(Menu menu, Actions actions) {
        this.menu = menu;
        this.actions = actions;

        near = menu.findItem(nearId);
        search = menu.findItem(searchId);
        trip = menu.findItem(tripId);
        info = menu.findItem(infoId);
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean handle(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case nearId :
                actions.onNear();
                break;
            case searchId :
                actions.onSearch();
                break;
            case tripId :
                actions.onTrip();
                break;
            case infoId :
                actions.onInfo();
                break;
            default:
                return false;
        }

        return true;
    }

    public interface Actions {
        void onNear();
        void onSearch();
        void onTrip();
        void onInfo();
    }

}
