package ro.hanca.cristian.munichcity;

import android.*;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ro.hanca.cristian.munichcity.fragments.NearFragment;
import ro.hanca.cristian.munichcity.fragments.SearchFragment;
import ro.hanca.cristian.munichcity.helpers.DataBaseHelpers;
import ro.hanca.cristian.munichcity.helpers.FragmentHelpers;
import ro.hanca.cristian.munichcity.helpers.MenuHandler;
import ro.hanca.cristian.munichcity.models.DaoMaster;
import ro.hanca.cristian.munichcity.models.DaoSession;
import ro.hanca.cristian.munichcity.models.POI;
import ro.hanca.cristian.munichcity.models.SubType;
import ro.hanca.cristian.munichcity.models.Type;
import ro.hanca.cristian.munichcity.models.TypeDao;

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

        menuHandler = new MenuHandler(navigationView.getMenu(), false, new MenuHandler.Actions() {
            private void common() {
                AppContext.selected_item = menuHandler.getSelected();
                FragmentHelpers.popAll();
            }

            @Override
            public void onNear() {
                common();
                FragmentHelpers.goToSingleton(new NearFragment(), Constants.gotoNear);
            }

            @Override
            public void onSearch() {
                common();
                FragmentHelpers.goToSingleton(new SearchFragment(), Constants.gotoSearch);
            }

            @Override
            public void onTrip() {
                common();
            }

            @Override
            public void onWeather() {
                common();
            }

            @Override
            public void onInfo() {
                common();
            }
        });

        if (savedInstanceState == null) {
            if (ContextCompat.checkSelfPermission(AppContext.activity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        Constants.permission_fine_location);
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment, new NearFragment(), Constants.gotoNear);
            ft.addToBackStack(Constants.gotoNear);
            ft.commit();

            menuHandler.setSelected(MenuHandler.HandledMenuItem.NEAR, false);
        } else {
            menuHandler.setSelected(AppContext.selected_item, false);
        }

        if (AppContext.db == null) {
            AppContext.db = DataBaseHelpers.openDb();
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
