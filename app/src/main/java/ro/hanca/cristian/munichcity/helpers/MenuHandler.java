package ro.hanca.cristian.munichcity.helpers;

import android.view.Menu;
import android.view.MenuItem;

import ro.hanca.cristian.munichcity.R;

public class MenuHandler {

    public enum HandledMenuItem {
        NEAR, SEARCH, TRIP, WEATHER, INFO
    }

    private final Menu menu;
    private final Actions actions;

    private MenuItem near;
    private MenuItem search;
    private MenuItem trip;
    private MenuItem weather;
    private MenuItem info;

    private HandledMenuItem selected = HandledMenuItem.NEAR;

    private static final int nearId = R.id.menu_near;
    private static final int searchId = R.id.menu_search;
    private static final int tripId = R.id.menu_trip;
    private static final int weatherId = R.id.menu_weather;
    private static final int infoId = R.id.menu_info;

   public MenuHandler(Menu menu, Actions actions) {
        this.menu = menu;
        this.actions = actions;

        near = menu.findItem(nearId);
        search = menu.findItem(searchId);
        trip = menu.findItem(tripId);
        weather = menu.findItem(weatherId);
        info = menu.findItem(infoId);
    }

    public Menu getMenu() {
        return menu;
    }

    public HandledMenuItem getSelected() {
        return selected;
    }

    public void setSelected(HandledMenuItem selected) {
        this.selected = selected;
        updateSelected();
    }

    private void updateSelected() {
        near.setChecked(false);
        search.setChecked(false);
        trip.setChecked(false);
        weather.setChecked(false);
        info.setChecked(false);
        switch (selected) {
            case NEAR: near.setChecked(true); break;
            case SEARCH: search.setChecked(true); break;
            case TRIP: trip.setChecked(true); break;
            case WEATHER: weather.setChecked(true); break;
            case INFO: info.setChecked(true); break;
        }
    }

    public boolean handle(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case nearId :
                selected = HandledMenuItem.NEAR;
                actions.onNear();
                break;
            case searchId :
                selected = HandledMenuItem.SEARCH;
                actions.onSearch();
                break;
            case tripId :
                selected = HandledMenuItem.TRIP;
                actions.onTrip();
                break;
            case weatherId :
                selected = HandledMenuItem.WEATHER;
                actions.onWeather();
                break;
            case infoId :
                selected = HandledMenuItem.INFO;
                actions.onInfo();
                break;
            default:
                return false;
        }

        updateSelected();
        return true;
    }

    public interface Actions {
        void onNear();
        void onSearch();
        void onTrip();
        void onWeather();
        void onInfo();
    }

}
