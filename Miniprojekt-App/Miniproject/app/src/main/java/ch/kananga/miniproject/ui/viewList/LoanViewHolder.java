package ch.kananga.miniproject.ui.viewList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LoanViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public TextView gadgetName;
    public TextView returnDate;

    public LoanViewHolder(View parent, TextView gadgetName, TextView returnDate) {
        super(parent);
        this.parent = parent;
        this.gadgetName = gadgetName;
        this.returnDate = returnDate;
    }

}
