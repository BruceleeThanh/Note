package com.rikkeisoft.thanhnt.note.ui.createnotes;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rikkeisoft.thanhnt.note.R;
import com.rikkeisoft.thanhnt.note.data.NoteColor;

public class CreateNoteActivity extends AppCompatActivity implements CreateNoteContract.View, View.OnClickListener {

    private RelativeLayout rlCreateNote;
    private ImageView ivNoteImage;
    private TextView tvCreatedAt;
    private EditText etNoteTitle;
    private EditText etNoteContent;
    private TextView tvAlarmAction;
    private LinearLayout llAlarmAction;
    private Spinner spiDateAlarm;
    private Spinner spiTimeAlarm;
    private ImageView ivCloseAlarm;
    private Dialog dialog;

    private CreateNoteContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        presenter = new CreateNotePresenter(this);
        addControls();
        addEvents();
    }

    private void addControls() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rlCreateNote = (RelativeLayout) findViewById(R.id.rlCreateNote);
        ivNoteImage = (ImageView) findViewById(R.id.ivNoteImage);
        tvCreatedAt = (TextView) findViewById(R.id.tvCreatedAt);
        etNoteTitle = (EditText) findViewById(R.id.etNoteTitle);
        etNoteContent = (EditText) findViewById(R.id.etNoteContent);
        tvAlarmAction = (TextView) findViewById(R.id.tvAlarmAction);
        llAlarmAction = (LinearLayout) findViewById(R.id.llAlarmAction);
        spiDateAlarm = (Spinner) findViewById(R.id.spiDateAlarm);
        spiTimeAlarm = (Spinner) findViewById(R.id.spiTimeAlarm);
        ivCloseAlarm = (ImageView) findViewById(R.id.ivCloseAlarm);
    }

    private void addEvents() {
        tvAlarmAction.setOnClickListener(this);
        ivCloseAlarm.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_create_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        } else if (itemId == R.id.menuInsertPicture) {
            showInsertPictureMenu();
        } else if (itemId == R.id.menuChooseColor) {
            showChooseColorMenu();
        } else if (itemId == R.id.menuDoneCreate) {
            presenter.saveNote(etNoteTitle.getText().toString(), etNoteContent.getText().toString());
        }
        return true;
    }

    @Override
    public void showChooseColorMenu() {
        dialog = new Dialog(this);
        dialog.setTitle(R.string.title_dialog_choose_color);
        dialog.setContentView(R.layout.dialog_choose_color);
        dialog.show();
        TextView tvColorWhite = (TextView) dialog.findViewById(R.id.tvColorWhite);
        tvColorWhite.setOnClickListener(this);
        TextView tvColorOrange = (TextView) dialog.findViewById(R.id.tvColorOrange);
        tvColorOrange.setOnClickListener(this);
        TextView tvColorGreen = (TextView) dialog.findViewById(R.id.tvColorGreen);
        tvColorGreen.setOnClickListener(this);
        TextView tvColorBlue = (TextView) dialog.findViewById(R.id.tvColorBlue);
        tvColorBlue.setOnClickListener(this);
    }

    @Override
    public void showInsertPictureMenu() {

    }

    @Override
    public void showEmptyNoteError() {
        Toast.makeText(this, R.string.empty_note_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlarm() {
        tvAlarmAction.setVisibility(View.GONE);
        llAlarmAction.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAlarm() {
        tvAlarmAction.setVisibility(View.VISIBLE);
        llAlarmAction.setVisibility(View.GONE);
    }

    @Override
    public void changeNoteColor(int colorId) {
        rlCreateNote.setBackgroundColor(ContextCompat.getColor(getBaseContext(), colorId));
    }

    @Override
    public void changeColorWhite() {
        changeNoteColor(R.color.white);
    }

    @Override
    public void changeColorOrange() {
        changeNoteColor(R.color.orange);
    }

    @Override
    public void changeColorGreen() {
        changeNoteColor(R.color.green);
    }

    @Override
    public void changeColorBlue() {
        changeNoteColor(R.color.blue);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tvAlarmAction) {
            showAlarm();
        } else if (id == R.id.ivCloseAlarm) {
            hideAlarm();
        } else if (id == R.id.tvColorWhite) {
            presenter.setColorWhite();
            dialog.cancel();
        } else if (id == R.id.tvColorOrange) {
            presenter.setColorOrange();
            dialog.cancel();
        } else if (id == R.id.tvColorGreen) {
            presenter.setColorGreen();
            dialog.cancel();
        } else if (id == R.id.tvColorBlue) {
            presenter.setColorBlue();
            dialog.cancel();
        }
    }
}
