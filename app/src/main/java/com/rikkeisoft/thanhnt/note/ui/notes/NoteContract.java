package com.rikkeisoft.thanhnt.note.ui.notes;

import com.rikkeisoft.thanhnt.note.ui.BasePresenter;
import com.rikkeisoft.thanhnt.note.ui.BaseView;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public interface NoteContract  {

    interface View extends BaseView<Presenter>{
        void showCreateNote();
    }

    interface Presenter extends BasePresenter{
        void loadNotes();
        void createNote();
    }
}
