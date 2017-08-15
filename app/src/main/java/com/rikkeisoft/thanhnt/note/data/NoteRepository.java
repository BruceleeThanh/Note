package com.rikkeisoft.thanhnt.note.data;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public class NoteRepository extends RealmHelper<Note> {

    public NoteRepository(Class<Note> type) {
        super(type);
    }
}
