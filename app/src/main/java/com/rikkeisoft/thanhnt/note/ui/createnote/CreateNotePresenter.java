package com.rikkeisoft.thanhnt.note.ui.createnote;

import com.rikkeisoft.thanhnt.note.data.Note;
import com.rikkeisoft.thanhnt.note.data.NoteColor;
import com.rikkeisoft.thanhnt.note.data.NoteRepository;


/**
 * Created by ThanhNT on 8/15/2017.
 */

public class CreateNotePresenter implements CreateNoteContract.Presenter {

    private final CreateNoteContract.View view;
    private NoteRepository noteRepository;
    private NoteColor noteColor = NoteColor.WHITE;

    public CreateNotePresenter(CreateNoteContract.View view) {
        this.view = view;
        noteRepository = new NoteRepository(Note.class);
    }

    @Override
    public void start() {

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
            Note note = new Note(title, content, noteColor);
            noteRepository.createOfUpdate(note);
            view.showCreateSuccess();
            view.showNoteList();
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
}
