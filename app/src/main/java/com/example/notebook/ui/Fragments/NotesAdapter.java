package com.example.notebook.ui.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.notebook.R;
import com.example.notebook.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final ArrayList<Note> data = new ArrayList<>();
    private final NotesFragment notesFragment;

    public NotesAdapter(NotesFragment notesFragment) {
        this.notesFragment = notesFragment;
    }

    public void addNote(Note note) {
        data.add(note);
        notifyItemInserted(data.size() - 1);
    }

    public void setNotes(List<Note> toSet) {
        data.clear();
        data.addAll(toSet);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewNotes = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list, parent, false);

        return new NotesViewHolder(viewNotes);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        Note note = data.get(position);

        notesFragment.registerForContextMenu(holder.v);
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.v.showContextMenu();

                if (notesFragment.getLongNoteClick() != null) {
                    notesFragment.getLongNoteClick().longNoteClicked(note);
                }
                return true;
            }
        });


        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notesFragment.getNoteClick() != null) {
                    notesFragment.getNoteClick().noteClicked(note);
                }
            }
        });

        holder.getTextView().setText(note.getNameNote());
        Glide.with(holder.getImageView()).load(note.getDateNote()).into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public int removeNotes(Note selectedNote) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(selectedNote)) {
                data.remove(i);
                return i;
            }
        }
        return -1;
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final ImageView imageView;
        private final View v;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textViewCard);
            this.imageView = itemView.findViewById(R.id.imageViewCard);
            this.v = itemView;
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public View getV() {
            return v;
        }
    }
}
