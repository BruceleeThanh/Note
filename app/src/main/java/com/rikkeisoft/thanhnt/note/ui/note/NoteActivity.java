package com.rikkeisoft.thanhnt.note.ui.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rikkeisoft.thanhnt.note.R;
import com.rikkeisoft.thanhnt.note.data.note.Note;
import com.rikkeisoft.thanhnt.note.ui.createeditnote.CreateEditNoteActivity;

import java.util.List;

public class NoteActivity extends AppCompatActivity implements NoteContract.View {

    private NoteContract.Presenter presenter;
    private RecyclerView rvListNote;
    private TextView tvEmptyListNote;
    private NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        presenter = new NotePresenter(this);

        addControls();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void addControls() {
        rvListNote = (RecyclerView) findViewById(R.id.rvListNote);
        tvEmptyListNote = (TextView) findViewById(R.id.tvEmptyListNote);
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
        if (item.getItemId() == R.id.menuCreateNote) {
            presenter.createNote();
        }
        return true;
    }

    @Override
    public void showCreateNote() {
        Intent intent = new Intent(getBaseContext(), CreateEditNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNotes(List<Note> lstNotes) {
        if (lstNotes == null || lstNotes.size() == 0) {
            showEmptyListNoteError();
        } else {
            rvListNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            noteListAdapter = new NoteListAdapter(lstNotes);
            rvListNote.setAdapter(noteListAdapter);
        }
    }

    @Override
    public void showEmptyListNoteError() {
        rvListNote.setVisibility(View.GONE);
        tvEmptyListNote.setVisibility(View.VISIBLE);
    }

    @Override
    public void visibleListNote() {
        rvListNote.setVisibility(View.VISIBLE);
        tvEmptyListNote.setVisibility(View.GONE);
    }
}
