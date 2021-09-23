package com.example.notebook.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Note implements Parcelable {

    private String id;
    private String Note;
    private final String nameNote;
    private final String  dateNote;

    public Note(String note, String nameNote, String dateNote,String id) {
        this.Note = note;
        this.nameNote = nameNote;
        this.dateNote = dateNote;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNote(String note) {
        this.Note = note;
    }

    protected Note(Parcel in) {
        Note = in.readString();
        nameNote = in.readString();
        dateNote = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getNote() {
        return Note;
    }

    public String getNameNote() {
        return nameNote;
    }

    public String getDateNote() {
        return this.dateNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Note);
        parcel.writeString(nameNote);
        parcel.writeString(dateNote);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return nameNote == note.nameNote && Objects.equals(Note, note.Note) && Objects.equals(dateNote, note.dateNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Note, nameNote, dateNote);
    }
}
