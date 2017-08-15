package com.rikkeisoft.thanhnt.note.ui.createnotes;

import com.rikkeisoft.thanhnt.note.data.Note;
import com.rikkeisoft.thanhnt.note.ui.BasePresenter;
import com.rikkeisoft.thanhnt.note.ui.BaseView;
import com.rikkeisoft.thanhnt.note.data.NoteColor;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public interface CreateNoteContract {

    interface View extends BaseView<Presenter> {
        void showChooseColorMenu();
        void showInsertPictureMenu();
        void showEmptyNoteError();
        void showAlarm();
        void hideAlarm();
        void changeNoteColor(int colorId);
        void changeColorWhite();
        void changeColorOrange();
        void changeColorGreen();
        void changeColorBlue();
    }

    interface Presenter extends BasePresenter {
        void saveNote(String title, String content);
        void setNoteColor(NoteColor noteColor);
        void setColorWhite();
        void setColorOrange();
        void setColorGreen();
        void setColorBlue();
    }
}
