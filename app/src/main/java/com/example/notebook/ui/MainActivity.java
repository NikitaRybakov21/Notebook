package com.example.notebook.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.notebook.R;
import com.example.notebook.domain.Note;

public class MainActivity extends AppCompatActivity implements NotesFragment.NoteClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void noteClicked(Note note) {
        if(getResources().getBoolean(R.bool.isLand)) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerViewDetail, DetailFragment.newInstance(note),null).commit();
        } else {
            Intent intent = new Intent(this, NewMainActivity2.class);
            intent.putExtra(NewMainActivity2.KEY2, note);
            startActivity(intent);
        }
    }
}