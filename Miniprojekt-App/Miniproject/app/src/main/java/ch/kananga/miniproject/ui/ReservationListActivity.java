package ch.kananga.miniproject.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Reservation;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;
import ch.kananga.miniproject.ui.viewList.ReservationAdapter;

public class ReservationListActivity extends BaseActivity {
    private RecyclerView reservationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LibraryService.setServerAddress(getString(R.string.serverAddress));

        reservationListView = findViewById(R.id.reservation_list_view);
        reservationListView.setLayoutManager(new LinearLayoutManager(this));
        loadReservationList();
    }

    private void loadReservationList() {
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> reservations) {
                ReservationAdapter adapter = new ReservationAdapter(reservations);
                reservationListView.setAdapter(adapter);

                TextView emptyText = (TextView) findViewById(R.id.empty_reservations);
                if (reservations.isEmpty()) {
                    reservationListView.setVisibility(View.GONE);
                    emptyText.setVisibility(View.VISIBLE);
                }
                else {
                    reservationListView.setVisibility(View.VISIBLE);
                    emptyText.setVisibility(View.GONE);
                }
                showToast(reservationListView.getVisibility() + " = reservationList");
            }

            @Override
            public void onError(String message) {
                showToast(message);
            }
        });
    }
}
