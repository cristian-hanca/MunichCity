package ro.hanca.cristian.munichcity.helpers;

import android.view.Menu;
import android.view.MenuItem;

import ro.hanca.cristian.munichcity.R;

public class MenuHandler {

    public enum HandledMenuItem {
        NEAR, SEARCH, TRIP, WEATHER, INFO, NONE
    }

    private final Menu menu;
    private final Actions actions;

    private MenuItem near;
    private MenuItem search;
    private MenuItem trip;
    private MenuItem weather;
    private MenuItem info;

    private final boolean activateSame;
    private HandledMenuItem selected = HandledMenuItem.NONE;

    private static final int nearId = R.id.menu_near;
    private static final int searchId = R.id.menu_search;
    private static final int tripId = R.id.menu_trip;
    private static final int weatherId = R.id.menu_weather;
    private static final int infoId = R.id.menu_info;

    public MenuHandler(Menu menu, boolean activateSame, Actions actions) {
        this.menu = menu;
        this.activateSame = activateSame;
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

    public void setSelected(HandledMenuItem selected, boolean callAction) {
        if (!activateSame && this.selected == selected) {
            return;
        }

        this.selected = selected;
        if (callAction) {
            switch (selected) {
                case NEAR: actions.onNear(); break;
                case SEARCH: actions.onSearch(); break;
                case TRIP: actions.onTrip(); break;
                case WEATHER: actions.onWeather(); break;
                case INFO: actions.onInfo(); break;
                case NONE: break;
            }
        }

        updateUi();
    }

    public void updateUi() {
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
            case NONE: break;
        }
    }

    public boolean handle(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case nearId : setSelected(HandledMenuItem.NEAR, true); break;
            case searchId : setSelected(HandledMenuItem.SEARCH, true); break;
            case tripId : setSelected(HandledMenuItem.TRIP, true); break;
            case weatherId : setSelected(HandledMenuItem.WEATHER, true); break;
            case infoId : setSelected(HandledMenuItem.INFO, true); break;
            default: return false;
        }

        updateUi();
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
