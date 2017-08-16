package com.rikkeisoft.thanhnt.note.ui.note;

import com.rikkeisoft.thanhnt.note.data.note.Note;
import com.rikkeisoft.thanhnt.note.ui.BasePresenter;
import com.rikkeisoft.thanhnt.note.ui.BaseView;

import java.util.List;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public interface NoteContract  {

    interface View extends BaseView<Presenter>{
        void showCreateNote();
        void showNotes(List<Note> lstNotes);
        void showEmptyListNoteError();
        void visibleListNote();
    }

    interface Presenter extends BasePresenter{
        void loadNotes();
        void createNote();
    }
}
