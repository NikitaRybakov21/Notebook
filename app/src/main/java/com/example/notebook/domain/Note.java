package com.example.notebook.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private final int Note;
    private final int nameNote;
    private final int dateNote;

    public Note(int note, int nameNote, int dateNote) {
        this.Note = note;
        this.nameNote = nameNote;
        this.dateNote = dateNote;
    }

    protected Note(Parcel in) {
        Note = in.readInt();
        nameNote = in.readInt();
        dateNote = in.readInt();
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

    public int getNote() {
        return Note;
    }

    public int getNameNote() {
        return nameNote;
    }

    public int getDateNote() {
        return dateNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Note);
        parcel.writeInt(nameNote);
        parcel.writeInt(dateNote);
    }
}
