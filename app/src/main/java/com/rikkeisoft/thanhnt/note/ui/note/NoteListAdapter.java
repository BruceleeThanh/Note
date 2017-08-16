package com.rikkeisoft.thanhnt.note.ui.note;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rikkeisoft.thanhnt.note.R;
import com.rikkeisoft.thanhnt.note.data.note.Note;
import com.rikkeisoft.thanhnt.note.data.note.NoteColor;
import com.rikkeisoft.thanhnt.note.ui.BaseViewHolder;
import com.rikkeisoft.thanhnt.note.utils.StringUtil;

import java.util.List;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public class NoteListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

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

    class ViewHolder extends BaseViewHolder {

        View itemView;
        CardView cvShortcutNote;
        TextView tvShortcutNoteTitle;
        ImageView ivIconAlarm;
        TextView tvShortcutNoteContent;
        TextView tvCreatedAt;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cvShortcutNote = (CardView) itemView.findViewById(R.id.cvShortcutNote);
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
            setShortcutNoteColor(note.getNodeColor());
            StringUtil.setText(tvShortcutNoteTitle, note.getTitle());
            if(note.getAlarm() == null){
                ivIconAlarm.setVisibility(View.GONE);
            }else{
                ivIconAlarm.setVisibility(View.VISIBLE);
            }
            StringUtil.setText(tvShortcutNoteContent, note.getContent());
            StringUtil.setText(tvCreatedAt, StringUtil.convertDateToString(note.getCreatedAt(), StringUtil.DATE_FORMAT_29));
        }

        private void setShortcutNoteColor(NoteColor noteColor) {
            if (noteColor == NoteColor.WHITE) {
                changeBackgroundColor(R.color.white);
            } else if (noteColor == NoteColor.ORANGE) {
                changeBackgroundColor(R.color.orange);
            } else if (noteColor == NoteColor.GREEN) {
                changeBackgroundColor(R.color.green);
            } else if (noteColor == NoteColor.BLUE) {
                changeBackgroundColor(R.color.blue);
            }
        }

        private void changeBackgroundColor(int colorId) {
            cvShortcutNote.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), colorId));
        }
    }
}
