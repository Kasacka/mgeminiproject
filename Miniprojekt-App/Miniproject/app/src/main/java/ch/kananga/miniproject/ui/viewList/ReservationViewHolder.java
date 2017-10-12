package ch.kananga.miniproject.ui.viewList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ReservationViewHolder extends RecyclerView.ViewHolder  {
    public View parent;
    public TextView gadgetName;
    public TextView reservationDate;

    public ReservationViewHolder(View parent, TextView gadgetName, TextView reservationDate) {
        super(parent);
        this.parent = parent;
        this.gadgetName = gadgetName;
        this.reservationDate = reservationDate;
    }
}