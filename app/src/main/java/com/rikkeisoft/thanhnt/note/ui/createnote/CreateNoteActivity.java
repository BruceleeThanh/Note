package com.rikkeisoft.thanhnt.note.ui.createnote;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rikkeisoft.thanhnt.note.R;
import com.rikkeisoft.thanhnt.note.service.AlarmReceiver;
import com.rikkeisoft.thanhnt.note.utils.StringUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private ArrayAdapter<String> spiDateAlarmAdapter;
    private ArrayAdapter<String> spiTimeAlarmAdapter;

    private CreateNoteContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        presenter = new CreateNotePresenter(this);
        addControls();
        addEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
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

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
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
            presenter.saveNote(StringUtil.getText(etNoteTitle), StringUtil.getText(etNoteContent));
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
    public void showCreatedAt(String time) {
        StringUtil.setText(tvCreatedAt, time);
    }

    @Override
    public void showEmptyNoteError() {
        Toast.makeText(this, R.string.empty_note_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlarm() {
        tvAlarmAction.setVisibility(View.GONE);
        llAlarmAction.setVisibility(View.VISIBLE);
        presenter.setOnOffAlarm(true);
    }

    @Override
    public void hideAlarm() {
        tvAlarmAction.setVisibility(View.VISIBLE);
        llAlarmAction.setVisibility(View.GONE);
        presenter.setOnOffAlarm(false);
    }

    @Override
    public void setupDateAlarm(List<String> lstDateAlarm) {
        spiDateAlarmAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                lstDateAlarm
        );
        spiDateAlarmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiDateAlarm.setAdapter(spiDateAlarmAdapter);
        spiDateAlarm.setOnItemSelectedListener(setItemDateAlarmSelectedListener());
    }

    private AdapterView.OnItemSelectedListener setItemDateAlarmSelectedListener(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.setDateAlarm(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    @Override
    public void setupTimeAlarm(List<String> lstTimeAlarm) {
        spiTimeAlarmAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                lstTimeAlarm
        );
        spiTimeAlarmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiTimeAlarm.setAdapter(spiTimeAlarmAdapter);
        spiTimeAlarm.setOnItemSelectedListener(setItemTimeAlarmSelectedListener());
    }

    private AdapterView.OnItemSelectedListener setItemTimeAlarmSelectedListener(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.setTimeAlarm(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
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
    public void showCreateSuccess() {
        Toast.makeText(this, R.string.create_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoteList() {
        finish();
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

    @Override
    public void showDateAlarmPicker() {
        // Get Current Date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        presenter.setDateAlarmPicker(year, monthOfYear, dayOfMonth);
                    }
                }, year, month, day);
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getResources().getString(R.string.cancel_dialog_button),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.cancelDateAlarmPicker();
                    }
                });
        datePickerDialog.show();
    }

    @Override
    public void showTimeAlarmPicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        presenter.setTimeAlarmPicker(hourOfDay, minute);
                    }
                }, hour, minute, true);
        timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getResources().getString(R.string.cancel_dialog_button),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.cancelTimeAlarmPicker();
                    }
                });
        timePickerDialog.show();
    }

    @Override
    public void setItemSelectedDateAlarmPicker(int position) {
        spiDateAlarm.setSelection(position);
    }

    @Override
    public void setItemSelectedTimeAlarmPicker(int position) {
        spiTimeAlarm.setSelection(position);
    }

    @Override
    public void notifyDateAlarmAdapter() {
        spiDateAlarmAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyTimeAlarmAdapter() {
        spiTimeAlarmAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAlarmNote(int id, Date alarm, String message) {
        String action = getResources().getString(R.string.intent_action_broadcast_receiver);
        Intent intent = new Intent(CreateNoteActivity.this, AlarmReceiver.class);
        intent.setAction(action);
        intent.putExtra("id", id);
        intent.putExtra("message", message);
        pendingIntent = PendingIntent.getBroadcast(CreateNoteActivity.this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.getTime(), pendingIntent);
    }
}
