package com.example.notebook.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.notebook.R;
import com.example.notebook.domain.Note;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NotesFragment.NoteClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,new NotesFragment(),null).commit();


        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.options1) {

                    Toast.makeText(MainActivity.this,R.string.options1,Toast.LENGTH_SHORT).show();

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void noteClicked(Note note) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerViewDetail, DetailFragment.newInstance(note),null).commit();
    }
}