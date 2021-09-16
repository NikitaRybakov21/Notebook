package com.example.notebook.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.notebook.R;
import com.example.notebook.domain.DeviceRepository;
import com.example.notebook.domain.Note;

import java.util.List;

public class NotesFragment extends Fragment implements InterfaceFragment {

    private PresenterFragment presenterFragment;
    private final NotesAdapter notesAdapter = new NotesAdapter(this);
    public NoteClick noteClick;

    public NoteClick getNoteClick(){
        return this.noteClick;
    }

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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(notesAdapter);

        presenterFragment.setNotes();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void setNotes(List<Note> notes) {
        notesAdapter.setNotes(notes);
        notesAdapter.notifyDataSetChanged();

       /*for (Note note: notes){
            View viewNotes = LayoutInflater.from(requireContext()).inflate(R.layout.notes_list,conteiner,false);

            viewNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noteClick != null) {
                        noteClick.noteClicked(note);
                    }
                }
            });

            TextView textView = viewNotes.findViewById(R.id.textViewCard);
            textView.setText(note.getNameNote());

            ImageView imageView = viewNotes.findViewById(R.id.imageViewCard);
            Glide.with(this).load(note.getDateNote()).into(imageView);

            container.addView(viewNotes);
        }*/
    }
}
