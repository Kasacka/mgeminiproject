package ch.kananga.miniproject.ui.viewList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Loan;
import ch.kananga.miniproject.domain.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder>{

    private List<Reservation> dataset;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);

    public ReservationAdapter(List<Reservation> reservations) {
        dataset = reservations;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.loan_rowlayout, parent, false);
        TextView gadgetName = view.findViewById(R.id.gadgetName);
        TextView returnDate = view.findViewById(R.id.returnDate);
        return new ReservationViewHolder(view, gadgetName, returnDate);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        final Reservation reservation = dataset.get(position);
        holder.gadgetName.setText(reservation.getGadget().getName());
        holder.reservationDate.setText(formatter.format(reservation.getReservationDate()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
