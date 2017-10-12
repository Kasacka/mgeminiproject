package ch.kananga.miniproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Reservation;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class ReservationListActivity extends AppCompatActivity {
    private ListView reservationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        LibraryService.setServerAddress(getString(R.string.serverAddress));

        reservationListView = findViewById(R.id.reservation_list_view);
    }

    private void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    private void loadReservationList() {
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {

            }

            @Override
            public void onError(String message) {
                showError(message);
            }
        });
    }
}
