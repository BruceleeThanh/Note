package com.rikkeisoft.thanhnt.note.ui.createeditnote;

import com.rikkeisoft.thanhnt.note.data.note.Note;
import com.rikkeisoft.thanhnt.note.data.note.NoteColor;
import com.rikkeisoft.thanhnt.note.data.note.NoteRepository;
import com.rikkeisoft.thanhnt.note.utils.Constant;
import com.rikkeisoft.thanhnt.note.utils.StringUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public class CreateEditNotePresenter implements CreateEditNoteContract.Presenter {

    private final String OTHER = "Other...";
    private final String TODAY = "Today";
    private final String TOMORROW = "Tomorrow";
    private final String NEXT = "Next";
    private final String NINE = "09:00";
    private final String THIRTEENTH = "13:00";
    private final String SEVENTEENTH = "17:00";
    private final String TWENTY = "20:00";


    private final CreateEditNoteContract.View view;
    private NoteRepository noteRepository;
    private NoteColor noteColor = NoteColor.WHITE;
    private Date currentDate;
    private Date alarm;
    private Calendar calendar = Calendar.getInstance();
    private List<String> lstDateAlarm;
    private List<String> lstTimeAlarm;

    private int previousDateAlarmSelected = 0;
    private int previousTimeAlarmSelected = 0;

    private int noteId = 0;

    public CreateEditNotePresenter(CreateEditNoteContract.View view) {
        this.view = view;
        noteRepository = new NoteRepository(Note.class);
    }

    public CreateEditNotePresenter(CreateEditNoteContract.View view, int noteId) {
        this.view = view;
        noteRepository = new NoteRepository(Note.class);
    }

    @Override
    public void start() {
        currentDate = StringUtil.getCurrentDate();
        view.showCreatedAt(StringUtil.convertDateToString(currentDate, StringUtil.DATE_FORMAT_28));

        setDateAlarm();
        setTimeAlarm();
    }

    private boolean isNewNote(){
        return noteId == 0;
    }

    private void setDateAlarm() {
        lstDateAlarm = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        String nextWeek = NEXT + " " + StringUtil.getDayOfWeek(calendar);
        lstDateAlarm.add(TODAY);
        lstDateAlarm.add(TOMORROW);
        lstDateAlarm.add(nextWeek);
        lstDateAlarm.add(OTHER);
        view.setupDateAlarm(lstDateAlarm);
    }

    private void setTimeAlarm() {
        lstTimeAlarm = new ArrayList<>();
        lstTimeAlarm.add(NINE);
        lstTimeAlarm.add(THIRTEENTH);
        lstTimeAlarm.add(SEVENTEENTH);
        lstTimeAlarm.add(TWENTY);
        lstTimeAlarm.add(OTHER);
        view.setupTimeAlarm(lstTimeAlarm);
    }

    @Override
    public void setNoteColor(NoteColor noteColor) {
        this.noteColor = noteColor;
        if (noteColor == NoteColor.WHITE) {
            view.changeColorWhite();
        } else if (noteColor == NoteColor.ORANGE) {
            view.changeColorOrange();
        } else if (noteColor == NoteColor.GREEN) {
            view.changeColorGreen();
        } else if (noteColor == NoteColor.BLUE) {
            view.changeColorBlue();
        }
    }

    @Override
    public void saveNote(String title, String content) {
        title = title.trim();
        if (title.isEmpty()) {
            view.showEmptyNoteError();
        } else {
            if(alarm != null){
                alarm = calendar.getTime();
            }
            Note note = new Note(title, content, noteColor, null, alarm, currentDate);
            Note newNote = noteRepository.createOfUpdate(note);
            view.showCreateSuccess();
            view.showNoteList();

            if (newNote.getAlarm() != null) {
                view.setAlarmNote(newNote.getId(), newNote.getAlarm(), newNote.getTitle());
            }
        }
    }

    @Override
    public void setColorWhite() {
        setNoteColor(NoteColor.WHITE);
    }

    @Override
    public void setColorOrange() {
        setNoteColor(NoteColor.ORANGE);
    }

    @Override
    public void setColorGreen() {
        setNoteColor(NoteColor.GREEN);
    }

    @Override
    public void setColorBlue() {
        setNoteColor(NoteColor.BLUE);
    }

    @Override
    public void setDateAlarm(int index) {
        if (index == 3) {
            // Other
            if (lstDateAlarm.get(index).equals(OTHER)) {
                view.showDateAlarmPicker();
            }
        } else {
            // reset ngày sau khi chuyển từ "Other..." (do sử dụng calendar.add();)
            Calendar current = Calendar.getInstance();
            calendar.set(Calendar.YEAR, current.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, current.get(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));

            if (index == 0) {
                // Today: current date is today
            } else if (index == 1) {
                // Tomorrow
                calendar.add(Calendar.DATE, 1);
            } else if (index == 2) {
                // Next week
                calendar.add(Calendar.DATE, 7);
            }

            // reset spinner spiDateAlarm sau khi chuyển từ "Other..."
            lstDateAlarm.set(3, OTHER);
            view.notifyDateAlarmAdapter();

            previousDateAlarmSelected = index;
        }

    }

    @Override
    public void setTimeAlarm(int index) {
        if (index == 4) {
            // Other
            if (lstTimeAlarm.get(index).equals(OTHER)) {
                view.showTimeAlarmPicker();
            }
        } else {
            if (index == 0) {
                // 09:00
                calendar.set(Calendar.HOUR_OF_DAY, 9);
                calendar.set(Calendar.MINUTE, 0);
            } else if (index == 1) {
                // 13:00
                calendar.set(Calendar.HOUR_OF_DAY, 13);
                calendar.set(Calendar.MINUTE, 0);
            } else if (index == 2) {
                // 17:00
                calendar.set(Calendar.HOUR_OF_DAY, 17);
                calendar.set(Calendar.MINUTE, 0);
            } else if (index == 3) {
                // 20:00
                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 0);
            }

            // reset spinner spiTimeAlarm sau khi chuyển từ "Other..."
            lstTimeAlarm.set(4, OTHER);
            view.notifyTimeAlarmAdapter();

            previousTimeAlarmSelected = index;
        }
    }

    @Override
    public void setDateAlarmPicker(int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        lstDateAlarm.set(3, StringUtil.convertCalendarToString(calendar, StringUtil.DATE_FORMAT_30));
        view.notifyDateAlarmAdapter();
    }

    @Override
    public void setTimeAlarmPicker(int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        lstTimeAlarm.set(4, StringUtil.convertCalendarToString(calendar, StringUtil.DATE_FORMAT_18));
        view.notifyTimeAlarmAdapter();
    }

    @Override
    public void cancelDateAlarmPicker() {
        view.setItemSelectedDateAlarmPicker(previousDateAlarmSelected);
    }

    @Override
    public void cancelTimeAlarmPicker() {
        view.setItemSelectedTimeAlarmPicker(previousTimeAlarmSelected);
    }

    @Override
    public void setOnOffAlarm(boolean isOnAlarm) {
        if (isOnAlarm) {
            alarm = calendar.getTime();
        } else {
            alarm = null;
        }
    }
}
