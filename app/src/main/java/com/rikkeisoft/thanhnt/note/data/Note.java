package com.rikkeisoft.thanhnt.note.data;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public class Note extends RealmObject {
    @PrimaryKey
    private String id;
    @Required
    private String title;

    private String content;
    @Required
    private String nodeColor;

    private String urlImage;

    private Date alarm;

    private Date createdAt;

    public Note() {
    }

    public Note(String title, String content, NoteColor nodeColor) {
        this(title, content, nodeColor, null, null, new Date());
    }

    public Note(String title, String content, NoteColor nodeColor, String urlImage) {
        this(title, content, nodeColor, urlImage, null, new Date());
    }

    public Note(String title, String content, NoteColor nodeColor, String urlImage, Date alarm, Date createdAt) {
        this.title = title;
        this.content = content;
        setNodeColor(nodeColor);
        this.urlImage = urlImage;
        this.alarm = alarm;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoteColor getNodeColor() {
        return NoteColor.valueOf(this.nodeColor);
    }

    public void setNodeColor(NoteColor noteColor) {
        this.nodeColor = noteColor.toString();
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
