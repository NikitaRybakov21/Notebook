package com.example.notebook.domain;
import android.os.Parcelable;

import java.util.List;

public interface NoteRepository extends Parcelable {

    void getNote(Callback<List<Note>> callback);

    void addNote(String title,String image,String notes, Callback<Note> callback);

    void removeNote(Note note, Callback<Void> callback);
}
