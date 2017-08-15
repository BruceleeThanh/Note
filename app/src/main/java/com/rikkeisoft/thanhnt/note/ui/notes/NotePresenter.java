package com.rikkeisoft.thanhnt.note.ui.notes;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public class NotePresenter implements NoteContract.Presenter {

    private final NoteContract.View view;

    public NotePresenter(NoteContract.View view) {
        this.view = view;
    }

    public void start() {
        loadNotes();
    }

    @Override
    public void loadNotes() {

    }

    @Override
    public void createNote() {
        view.showCreateNote();
    }
}
