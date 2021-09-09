package com.example.notebook.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.notebook.R;
import com.example.notebook.domain.Note;

public class NewMainActivity2 extends AppCompatActivity {

    public static String KEY2 = "key2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        Note note = getIntent().getParcelableExtra(KEY2);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container2, DetailFragment.newInstance(note),null).commit();
    }
}