package ro.hanca.cristian.munichcity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ro.hanca.cristian.munichcity.fragments.NearFragment;
import ro.hanca.cristian.munichcity.helpers.FragmentHelpers;
import ro.hanca.cristian.munichcity.helpers.MenuHandler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    MenuHandler menuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.activity = this;

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        menuHandler = new MenuHandler(navigationView.getMenu(), new MenuHandler.Actions() {
            @Override
            public void onNear() {

            }

            @Override
            public void onSearch() {

            }

            @Override
            public void onTrip() {

            }

            @Override
            public void onWeather() {

            }

            @Override
            public void onInfo() {

            }
        });

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment, new NearFragment(), Constants.gotoNear);
            ft.addToBackStack(Constants.gotoNear);
            ft.commit();

            menuHandler.setSelected(MenuHandler.HandledMenuItem.NEAR);
        }
    }

    public void lockMode(boolean active) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            if (active) {
                bar.hide();
            } else {
                bar.show();
            }
        }

        if (active) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    public void exit() {
        FragmentHelpers.popAll();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (FragmentHelpers.backCount() <= 1) {
            exit();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);

        return menuHandler.handle(item);
    }
}
