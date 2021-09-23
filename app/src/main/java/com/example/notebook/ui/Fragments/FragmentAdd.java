package com.example.notebook.ui.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.notebook.R;
import com.example.notebook.domain.FireStore;
import com.example.notebook.domain.Note;
import com.example.notebook.ui.Activity.MainActivity;

import java.util.UUID;

public class FragmentAdd extends Fragment {
    private final NotesFragment notesFragment;
    private final MainActivity mainActivity;

    public FragmentAdd(NotesFragment notesFragment,MainActivity mainActivity){
        this.notesFragment = notesFragment;
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_notes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTextName = view.findViewById(R.id.notesNameEdit);
        EditText editTextNotes = view.findViewById(R.id.notesEdit);

        Button buttonApply = view.findViewById(R.id.notesApply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesFragment.addNote(editTextName.getText().toString(),editTextNotes.getText().toString(),
                        "https://penzavzglyad.ru/images/uploads/2021/05/12/5037026ada78848a6a62aa6012c20256.jpg");

                mainActivity.permutation1();
            }
        });
    }
}
