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
import com.example.notebook.domain.DeviceRepository;
import com.example.notebook.domain.FireStore;
import com.example.notebook.domain.Note;
import com.example.notebook.domain.NoteRepository;
import com.example.notebook.ui.Fragments.DetailFragment;
import com.example.notebook.ui.Fragments.FragmentAccount;
import com.example.notebook.ui.Fragments.FragmentEditor;
import com.example.notebook.ui.Fragments.NotesFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;

import java.util.Collections;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NotesFragment.NoteClick {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private final NoteRepository repository = new FireStore();
    private final FragmentEditor fragmentEditor = new FragmentEditor();
    private final NotesFragment notesFragment = NotesFragment.newInstance(repository);

    private DrawerLayout drawerLayout;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(GoogleSignIn.getLastSignedInAccount(this) == null){
            replaceFragment(R.id.container, new FragmentAccount());
        } else {
            replaceFragment(R.id.container, notesFragment);
        }


        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.deleteNotes) {
                    notesFragment.setNotes(Collections.emptyList());
                }
                if (item.getItemId() == R.id.addNotes) {
                    notesFragment.addNote(UUID.randomUUID().toString(), "https://look.com.ua/pic/201508/1280x1024/look.com.ua-127118.jpg");
                }
                return false;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView();
    }

    @Override
    public void noteClicked(Note note) {
        if (!flag) {
            replaceFragment(R.id.ContainerDetail, DetailFragment.newInstance(note));
        } else {
            fragmentEditor.setTextEdit(note);
        }
    }

    public void replaceFragment(int idContainer, Fragment fragment) {
        fragmentManager.beginTransaction().replace(idContainer, fragment, null).commit();
    }

    public void permutation2() {
        replaceFragment(R.id.container, fragmentEditor);
        replaceFragment(R.id.ContainerDetail, NotesFragment.newInstance(repository));
    }

    public void permutation1() {
        replaceFragment(R.id.container, notesFragment);
        replaceFragment(R.id.ContainerDetail, DetailFragment.newInstance(new Note("", "", null,"")));
    }

    public void navigationView() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.options1) {
                    flag = false;
                    permutation1();

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }

                if (item.getItemId() == R.id.options2) {
                    flag = true;
                    permutation2();

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
    }
}