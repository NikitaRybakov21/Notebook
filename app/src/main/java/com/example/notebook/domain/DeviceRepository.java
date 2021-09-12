package com.example.notebook.domain;
import com.example.notebook.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepository implements NoteRepository{

    @Override
    public List<Note> getNote() {
        ArrayList<Note> notesList = new ArrayList<>();

        notesList.add(new Note(R.string.note1,R.string.noteName1,"https://look.com.ua/pic/201508/1280x1024/look.com.ua-127118.jpg"));
        notesList.add(new Note(R.string.note2,R.string.noteName2,"https://lookw.ru/9/945/1566941301-oboi-008.jpg"));
        notesList.add(new Note(R.string.note3,R.string.noteName3,"https://3mins.es/wp-content/uploads/2021/05/spacex-starship-scaled-1.jpg"));
        notesList.add(new Note(R.string.note4,R.string.noteName4,"https://cdn.fishki.net/upload/post/2018/02/22/2519736/filin-sova-ptitsa-khishchnik-krylya-vzmakh-111969-1680x1050.jpg"));

        return notesList;
    }
}
