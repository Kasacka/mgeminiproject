package ch.kananga.miniproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.List;
import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Reservation;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;
import ch.kananga.miniproject.ui.viewList.ReservationAdapter;

public class ReservationListActivity extends AppCompatActivity {
    private RecyclerView reservationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        LibraryService.setServerAddress(getString(R.string.serverAddress));

        reservationListView = findViewById(R.id.reservation_list_view);
        reservationListView.setLayoutManager(new LinearLayoutManager(this));
        loadReservationList();
    }

    private void showMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    private void loadReservationList() {
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> reservations) {
                ReservationAdapter adapter = new ReservationAdapter(reservations);
                reservationListView.setAdapter(adapter);
                reservationListView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(String message) {
                showMessage(message);
            }
        });
    }
}
