package com.rikkeisoft.thanhnt.note.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rikkeisoft.thanhnt.note.R;

public class NoteActivity extends AppCompatActivity implements NoteContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    @Override
    public void setPresenter(NoteContract.Presenter presenter) {

    }
}
