package com.example.notebook.ui.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.R;
import com.example.notebook.domain.Callback;
import com.example.notebook.domain.Note;
import com.example.notebook.domain.NoteRepository;

import java.util.List;

public class NotesFragment extends Fragment {

    private final NotesAdapter notesAdapter = new NotesAdapter(this);
    private NoteRepository repository;
    private NoteClick noteClick;

    private LongNoteClick longNoteClick;
    private final static String KEY = "KEY";
    private RecyclerView recyclerView;
    private Note selectedNote;

    public static NotesFragment newInstance(NoteRepository repository) {
        NotesFragment notesFragment = new NotesFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(KEY, repository);
        notesFragment.setArguments(arguments);

        return notesFragment;
    }

    public NoteClick getNoteClick() {
        return this.noteClick;
    }

    public LongNoteClick getLongNoteClick() {
        return this.longNoteClick;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof NoteClick) {
            noteClick = (NoteClick) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    public void setLongNoteClick(LongNoteClick longNoteClick) {
        this.longNoteClick = longNoteClick;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setLongNoteClick(new LongNoteClick() {
            @Override
            public void longNoteClicked(Note note) {
                selectedNote = note;
            }
        });

        recyclerView = view.findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(notesAdapter);

        repository.getNote(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {
                setNotes(data);
            }
        });
    }

    @Override
    public void onDetach() {
        noteClick = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = getArguments().getParcelable(KEY);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optionsContextDelete) {


            alertDialog();

            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void alertDialog() {


        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle(R.string.title)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        repository.removeNote(selectedNote, new Callback<Void>() {
                            @Override
                            public void onSuccess(Void data) {
                                onNoteRemoved(selectedNote);
                            }
                        });

                        Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        dialog.show();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_contex, menu);
    }

    public void addNote(String title, String notes, String imageUrl) {
        repository.addNote(title, imageUrl, notes, new Callback<Note>() {
            @Override
            public void onSuccess(Note data) {
                onNoteAdded(data);
            }
        });
    }

    public interface NoteClick {
        void noteClicked(Note note);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNotes(List<Note> notes) {
        notesAdapter.setNotes(notes);
        notesAdapter.notifyDataSetChanged();
    }

    public interface LongNoteClick {
        void longNoteClicked(Note note);
    }

    public void onNoteAdded(Note note){
        notesAdapter.addNote(note);
        recyclerView.smoothScrollToPosition(notesAdapter.getItemCount() - 1);
    }

    public void onNoteRemoved(Note selectedNote){
        int index = notesAdapter.removeNotes(selectedNote);
        notesAdapter.notifyItemRemoved(index);
    }
}
