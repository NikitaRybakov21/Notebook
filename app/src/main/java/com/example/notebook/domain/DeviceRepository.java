package com.example.notebook.domain;
import com.example.notebook.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepository implements NoteRepository{

    @Override
    public List<Note> getNote() {
        ArrayList<Note> notesList = new ArrayList<>();

        notesList.add(new Note(R.string.note1,R.string.noteName1,R.string.noteDate1));
        notesList.add(new Note(R.string.note2,R.string.noteName2,R.string.noteData2));
        notesList.add(new Note(R.string.note3,R.string.noteName3,R.string.noteData3));

        return notesList;
    }
}
