package com.rikkeisoft.thanhnt.note.ui.note;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rikkeisoft.thanhnt.note.R;
import com.rikkeisoft.thanhnt.note.data.Note;
import com.rikkeisoft.thanhnt.note.ui.BaseViewHolder;
import com.rikkeisoft.thanhnt.note.utils.StringUtil;

import java.util.List;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public class NoteListAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<Note> lstNotes = null;

    public NoteListAdapter(List<Note> lstNotes) {
        this.lstNotes = lstNotes;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shortcut_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return lstNotes == null ? 0 : lstNotes.size();
    }

    class ViewHolder extends BaseViewHolder{

        TextView tvShortcutNoteTitle;
        ImageView ivIconAlarm;
        TextView tvShortcutNoteContent;
        TextView tvCreatedAt;

        public ViewHolder(View itemView) {
            super(itemView);
            tvShortcutNoteTitle = (TextView) itemView.findViewById(R.id.tvShortcutNoteTitle);
            ivIconAlarm = (ImageView) itemView.findViewById(R.id.ivIconAlarm);
            tvShortcutNoteContent = (TextView) itemView.findViewById(R.id.tvShortcutNoteContent);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tvCreatedAt);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Note note = lstNotes.get(position);
            StringUtil.setText(tvShortcutNoteTitle, note.getTitle());
            StringUtil.setText(tvShortcutNoteContent, note.getContent());
        }
    }
}
