package com.example.notebook.domain;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.notebook.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeviceRepository implements Parcelable , NoteRepository {

    private ArrayList<Note> notesList = new ArrayList<>();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public DeviceRepository(){
        String text1 = "null";

        notesList.add(new Note(text1,text1,"https://look.com.ua/pic/201508/1280x1024/look.com.ua-127118.jpg",""));
        notesList.add(new Note(text1,text1,"https://lookw.ru/9/945/1566941301-oboi-008.jpg",""));
        notesList.add(new Note(text1,text1,"https://3mins.es/wp-content/uploads/2021/05/spacex-starship-scaled-1.jpg",""));
        notesList.add(new Note(text1,text1,"https://cdn.fishki.net/upload/post/2018/02/22/2519736/filin-sova-ptitsa-khishchnik-krylya-vzmakh-111969-1680x1050.jpg",""));
    }
    @Override
    public void getNote(Callback<List<Note>> callback)  {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notesList);
                    }
                });
            }
        }).start();
    }

    @Override
    public void addNote(String title, String image, Callback<Note> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                Note result = new Note(UUID.randomUUID().toString(),"null",image,"");
                notesList.add(result);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(result);
                    }
                });
            }
        }).start();
    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                notesList.remove(note);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });
            }
        }).start();
    }

    protected DeviceRepository(Parcel in) {
        notesList = in.createTypedArrayList(Note.CREATOR);
    }

    public static final Creator<DeviceRepository> CREATOR = new Creator<DeviceRepository>() {
        @Override
        public DeviceRepository createFromParcel(Parcel in) {
            return new DeviceRepository(in);
        }

        @Override
        public DeviceRepository[] newArray(int size) {
            return new DeviceRepository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(notesList);
    }
}
