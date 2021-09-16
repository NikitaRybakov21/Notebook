package com.example.notebook.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notebook.R;
import com.example.notebook.domain.Note;

public class DetailFragment extends Fragment {

    public static String KEY = "key";

    public static DetailFragment newInstance(Note note){
        DetailFragment detailFragment = new DetailFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(KEY,note);
        detailFragment.setArguments(arguments);

        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Note note = getArguments().getParcelable(KEY);

        TextView textView = view.findViewById(R.id.note);
        TextView textViewHead = view.findViewById(R.id.noteHead);
        textView.setText(note.getNote());
        textViewHead.setText((note.getNameNote()));
    }
}
