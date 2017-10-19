package ch.kananga.miniproject.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Reservation;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class ReservationListActivity extends BaseActivity {
    private ListView reservationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LibraryService.setServerAddress(getString(R.string.serverAddress));

        reservationListView = (ListView) findViewById(R.id.reservation_list_view);
    }

    private void loadReservationList() {
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {

            }

            @Override
            public void onError(String message) {
                showToast(message);
            }
        });
    }
}
