package com.example.notebook.domain;

import android.annotation.SuppressLint;
import android.os.Parcel;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("ParcelCreator")
public class FireStore implements NoteRepository{
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void getNote(Callback<List<Note>> callback) {

        db.collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Note> result = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String notes = document.get("Notes",String.class);
                                String notesIm = document.get("ImNotes",String.class);
                                String notesName = document.get("Name",String.class);

                                result.add(new Note(notes,notesName,notesIm,document.getId()));
                            }
                            callback.onSuccess(result);
                        }
                    }
                });
    }

    @Override
    public void addNote(String title, String image, String notes, Callback<Note> callback) {
        Map<String, Object> data = new HashMap<>();
        data.put("Name", title);
        data.put("ImNotes", image);
        data.put("Notes", notes);


        db.collection("notes")
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            String noteId = task.getResult().getId();

                            callback.onSuccess(new Note(notes, title, image, noteId));
                        }
                    }
                });
    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {
        db.collection("notes").document(note.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess(aVoid);
                    }
                });
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
