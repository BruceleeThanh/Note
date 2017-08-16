package com.rikkeisoft.thanhnt.note.ui.createnote;

import com.rikkeisoft.thanhnt.note.ui.BasePresenter;
import com.rikkeisoft.thanhnt.note.ui.BaseView;
import com.rikkeisoft.thanhnt.note.data.NoteColor;

import java.util.Date;
import java.util.List;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public interface CreateNoteContract {

    interface View extends BaseView<Presenter> {
        void showChooseColorMenu();

        void showInsertPictureMenu();

        void showCreatedAt(String time);

        void showEmptyNoteError();

        void showAlarm();

        void hideAlarm();

        void setupDateAlarm(List<String> lstDateAlarm);

        void setupTimeAlarm(List<String> lstTimeAlarm);

        void changeNoteColor(int colorId);

        void changeColorWhite();

        void changeColorOrange();

        void changeColorGreen();

        void changeColorBlue();

        void showCreateSuccess();

        void showNoteList();

        void showDateAlarmPicker();

        void showTimeAlarmPicker();

        void setItemSelectedDateAlarmPicker(int position);

        void setItemSelectedTimeAlarmPicker(int position);

        void notifyDateAlarmAdapter();

        void notifyTimeAlarmAdapter();

        void setAlarmNote(int id, Date alarm);
    }

    interface Presenter extends BasePresenter {
        void saveNote(String title, String content);

        void setNoteColor(NoteColor noteColor);

        void setColorWhite();

        void setColorOrange();

        void setColorGreen();

        void setColorBlue();

        void setDateAlarm(int index);

        void setTimeAlarm(int index);

        void setDateAlarmPicker(int year, int month, int day);

        void cancelDateAlarmPicker();

        void setTimeAlarmPicker(int hour, int minute);

        void cancelTimeAlarmPicker();

        void setOnOffAlarm(boolean isOnAlarm);


    }
}
