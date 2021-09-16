package com.example.notebook.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notebook.R;
import com.example.notebook.domain.DeviceRepository;
import com.example.notebook.domain.Note;

public class FragmentEditor extends Fragment {

    private EditText editText;
    private Button buttonApply;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editor_view,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonApply = view.findViewById(R.id.buttonApply);
        editText = view.findViewById(R.id.editText);
    }

    public void setTextEdit(Note note){
        editText.setText(note.getNote());

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                note.setNote(text);
            }
        });
    }
}
