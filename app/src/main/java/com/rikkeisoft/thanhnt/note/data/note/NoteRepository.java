package com.rikkeisoft.thanhnt.note.data.note;

import com.rikkeisoft.thanhnt.note.data.RealmHelper;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public class NoteRepository extends RealmHelper<Note> {

    public NoteRepository(Class<Note> type) {
        super(type);
    }

    @Override
    public Note createOfUpdate(Note note) {
        Number num = realm.where(type).max("id");
        if(num != null) {
            note.setId(num.intValue() + 1);
        }
        return super.createOfUpdate(note);
    }
}
