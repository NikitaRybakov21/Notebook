package com.example.notebook.ui;

import com.example.notebook.domain.Note;
import com.example.notebook.domain.NoteRepository;

import java.util.List;

public class PresenterFragment {
    private NotesFragment notesFragment;
    private NoteRepository noteRepository;

    public PresenterFragment(NotesFragment notesFragment, NoteRepository noteRepository) {
        this.notesFragment = notesFragment;
        this.noteRepository = noteRepository;
    }

    public void setNotes(){
        List<Note> res = noteRepository.getNote();
        notesFragment.setNotes(res);
    }
}
