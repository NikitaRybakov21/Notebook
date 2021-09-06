package com.example.notebook.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notebook.R;
import com.example.notebook.domain.DeviceRepository;
import com.example.notebook.domain.Note;

import java.util.List;

public class NotesFragment extends Fragment implements InterfaceFragment {

    private PresenterFragment presenterFragment;
    private LinearLayout container;
    private NoteClick noteClick;

    public interface NoteClick{
        void noteClicked(Note note);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof NoteClick){
            noteClick = (NoteClick) context;
        }
    }

    @Override
    public void onDetach() {
        noteClick = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterFragment = new PresenterFragment(this,new DeviceRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        container = view.findViewById(R.id.layout);
        presenterFragment.setNotes();
    }

    @Override
    public void setNotes(List<Note> notes) {

        for (Note note: notes){
            View viewNotes = LayoutInflater.from(requireContext()).inflate(R.layout.view_notes,container,false);

            viewNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noteClick != null) {
                        noteClick.noteClicked(note);
                    }
                }
            });

            TextView textView = viewNotes.findViewById(R.id.noteName);
            textView.setText(note.getNameNote());

            container.addView(viewNotes);
        }
    }
}
