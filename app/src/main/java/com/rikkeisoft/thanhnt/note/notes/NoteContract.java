package com.rikkeisoft.thanhnt.note.notes;

import com.rikkeisoft.thanhnt.note.BasePresenter;
import com.rikkeisoft.thanhnt.note.BaseView;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public interface NoteContract  {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{
        void loadNotes();
    }
}
