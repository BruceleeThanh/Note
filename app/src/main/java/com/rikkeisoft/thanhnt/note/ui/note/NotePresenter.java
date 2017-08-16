package com.rikkeisoft.thanhnt.note.ui.note;

import com.rikkeisoft.thanhnt.note.data.note.Note;
import com.rikkeisoft.thanhnt.note.data.note.NoteRepository;

import java.util.List;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public class NotePresenter implements NoteContract.Presenter {

    private final NoteContract.View view;
    private NoteRepository noteRepository;

    public NotePresenter(NoteContract.View view) {
        this.view = view;
        noteRepository = new NoteRepository(Note.class);
    }

    @Override
    public void start() {
        loadNotes();
    }

    @Override
    public void loadNotes() {
        List<Note> lstNotes = noteRepository.getAll();
        view.showNotes(lstNotes);
    }

    @Override
    public void createNote() {
        view.showCreateNote();
    }
}
