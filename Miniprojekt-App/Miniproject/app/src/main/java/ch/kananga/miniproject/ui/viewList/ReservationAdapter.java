package ch.kananga.miniproject.ui.viewList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Reservation;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder>{
    private List<Reservation> reservations;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, final int reservationIndex) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View rowView = layoutInflater.inflate(R.layout.reservation_rowlayout, parent, false);
        TextView gadgetName = rowView.findViewById(R.id.reservation_gadget_name);
        TextView reservationDate = rowView.findViewById(R.id.reservation_date);
        Button deleteReservationButton = rowView.findViewById(R.id.delete_reservation);

        final Reservation reservation = reservations.get(reservationIndex);

        deleteReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                LibraryService.deleteReservation(reservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        reservations.remove(reservationIndex);
                        notifyItemRemoved(reservationIndex);
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

        return new ReservationViewHolder(rowView, gadgetName, reservationDate);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        final Reservation reservation = reservations.get(position);
        holder.gadgetName.setText(reservation.getGadget().getName());
        holder.reservationDate.setText(formatter.format(reservation.getReservationDate()));
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }
}