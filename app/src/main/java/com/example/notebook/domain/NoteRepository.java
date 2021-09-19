package com.example.notebook.domain;
import java.util.List;

public interface NoteRepository {
    void getNote(Callback<List<Note>> callback);

    void addNote(String title,String image, Callback<Note> callback);

    void removeNote(Note note, Callback<Void> callback);
}
