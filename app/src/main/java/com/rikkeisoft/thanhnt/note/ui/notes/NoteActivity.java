package com.rikkeisoft.thanhnt.note.ui.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.rikkeisoft.thanhnt.note.R;
import com.rikkeisoft.thanhnt.note.ui.createnotes.CreateNoteActivity;

public class NoteActivity extends AppCompatActivity implements NoteContract.View{

    private NoteContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        presenter = new NotePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void addControls(){

    }

    @Override
    public void showCreateNote() {
        Intent intent = new Intent(getBaseContext(), CreateNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuCreateNote){
            presenter.createNote();
        }
        return true;
    }


}
