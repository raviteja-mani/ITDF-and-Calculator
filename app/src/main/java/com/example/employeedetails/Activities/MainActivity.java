package com.example.employeedetails.Activities;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.employeedetails.Fragments.CalculatorFragment;
import com.example.employeedetails.Fragments.EmployeeDetails;
import com.example.employeedetails.Fragments.GuideLinesFragment;
import com.example.employeedetails.Fragments.HomeFragment;
import com.example.employeedetails.Fragments.NewTaxSlabsFragment;
import com.example.employeedetails.Fragments.SlabsFragment;
import com.example.employeedetails.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private ActionBarDrawerToggle t;
    private NavigationView navigationView;

    FragmentManager fragmentManager=getSupportFragmentManager();
    private GuideLinesFragment guideLinesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(TAG, String.valueOf(R.string.close));
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.stohrm_logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
fragmentManager.beginTransaction().addToBackStack("firstOne").replace(R.id.frameLayoutContainer,new EmployeeDetails()).commit();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        nextToGuideline=findViewById(R.id.nextToguidelines);
//        nextToGuideline.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                //fragmentManager.beginTransaction().add(R.layout.fragment_employee_details,guideLinesFragment).commit();
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slabs, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
       // as you specify a parent activity in AndroidManifest.xml.
   int id = item.getItemId();
       Fragment fragment=null;
       switch(id)
       {
           case R.id.taxSlabs:
               fragment=new SlabsFragment();
               break;
           case R.id.newtax:
               fragment =new NewTaxSlabsFragment();
               break;
       }
//       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

       fragmentManager.beginTransaction().addToBackStack("First").replace(R.id.frameLayoutContainer,fragment).commit();
       item.setChecked(true);
       drawer.closeDrawer(GravityCompat.START);

       return super.onOptionsItemSelected(item);
   }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        switch(id)
        {
            case R.id.details:
                fragment=new GuideLinesFragment();
                Toast.makeText(MainActivity.this, "In Employee details",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Home:
                fragment=new HomeFragment();
                break;
            case R.id.profile:
                fragment=new EmployeeDetails();
                break;
            case R.id.nav_calculator:
                fragment=new CalculatorFragment();
                break;
//            case R.id.guidelines:
//                    fragment=new GuideLinesFragment();
//                Toast.makeText(MainActivity.this, "In general guidelines",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_declaration:
//                fragment=new DeclarationFragment();
//                Toast.makeText(MainActivity.this,"In Declaration page",Toast.LENGTH_SHORT).show();
        }
//       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        fragmentManager.beginTransaction().addToBackStack("First").replace(R.id.frameLayoutContainer,fragment).commit();
        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Fragment> list= getSupportFragmentManager().getFragments();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutContainer,list.get(list.size()-1)).commit();
    }
}