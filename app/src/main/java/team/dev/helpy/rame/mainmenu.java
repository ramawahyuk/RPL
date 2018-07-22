package team.dev.helpy.rame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;


public class mainmenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.toolbar) Toolbar _toolbar;
    @BindView(R.id.draw_layout) DrawerLayout _drawer;
    @BindView(R.id.nav_view) NavigationView _navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        ButterKnife.bind(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, _drawer, _toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        _drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

            _navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                _navigationView.setCheckedItem(R.id.nav_home);
                break;
           /* case R.id.nav_lokasiuser:
                Intent  in =new Intent(MapsActivity.class);
                in.putExtra("some","somedata");
                startActivity(in);
                break;
            case R.id.nav_settings:
              //  startActivity(new Intent(this,Home.class));
                _navigationView.setCheckedItem(R.id.nav_settings);
                break;
            case R.id.nav_logout:
               break;*/
        }
        _drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
