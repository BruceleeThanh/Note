package com.rikkeisoft.thanhnt.note.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Brucelee Thanh on 15-Aug-17.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int currentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        currentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
