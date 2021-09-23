package com.example.notebook.ui.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.notebook.R;
import com.example.notebook.domain.FireStore;
import com.example.notebook.domain.Note;
import com.example.notebook.domain.NoteRepository;
import com.example.notebook.ui.Fragments.DetailFragment;
import com.example.notebook.ui.Fragments.FragmentAccount;
import com.example.notebook.ui.Fragments.FragmentAdd;
import com.example.notebook.ui.Fragments.NotesFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NotesFragment.NoteClick {

    private final NoteRepository repository = new FireStore();
    private final NotesFragment notesFragment = NotesFragment.newInstance(repository);

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(R.id.container, notesFragment);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        toolbarMenu();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView();
    }

    @Override
    public void noteClicked(Note note) {
        replaceFragment(R.id.ContainerDetail, DetailFragment.newInstance(note));
    }

    private void replaceFragment(int idContainer, Fragment fragment) {
        fragmentManager.beginTransaction().replace(idContainer, fragment, null).commit();
    }

    private void setFragmentAdd(){
        fragmentManager.beginTransaction().add( R.id.container,new FragmentAdd(notesFragment,this), null).commit();
    }

    public void permutation1() {
        replaceFragment(R.id.container, notesFragment);
        replaceFragment(R.id.ContainerDetail, DetailFragment.newInstance(new Note("", "", null,"")));
    }

    private void toolbarMenu(){

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.addNotes) {
                    setFragmentAdd();
                }
                return false;
            }
        });
    }

    private void navigationView() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.options1) {
                    permutation1();

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                if (item.getItemId() == R.id.options3) {
                    replaceFragment(R.id.container, new FragmentAccount());

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
    }
}