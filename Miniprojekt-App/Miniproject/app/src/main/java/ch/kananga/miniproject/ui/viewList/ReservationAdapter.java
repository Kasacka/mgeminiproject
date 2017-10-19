package ch.kananga.miniproject.ui.viewList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Reservation;
import ch.kananga.miniproject.service.LibraryService;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder>{

    private List<Reservation> reservations;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reservation_rowlayout, parent, false);
        TextView gadgetName = view.findViewById(R.id.reservation_gadget_name);
        TextView reservationDate = view.findViewById(R.id.reservation_date);
        Button deleteReservationButton = view.findViewById(R.id.delete_reservation);

        final int reservationId = viewType;

        deleteReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Löschen " + reservationId, Toast.LENGTH_LONG).show();
                //LibraryService.deleteReservation();
            }
        });

        return new ReservationViewHolder(view, gadgetName, reservationDate);
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