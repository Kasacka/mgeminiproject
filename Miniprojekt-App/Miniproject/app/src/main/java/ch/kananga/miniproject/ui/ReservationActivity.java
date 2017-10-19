package ch.kananga.miniproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;
import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Gadget;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner reservationArticleSpinner;
    private Button reservationButton;
    private Button reservationListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        LibraryService.setServerAddress(getString(R.string.serverAddress));

        reservationArticleSpinner = findViewById(R.id.reservation_article);

        loadArticles();

        reservationButton = findViewById(R.id.reservation_button);
        reservationButton.setOnClickListener(this);

        reservationListButton = findViewById(R.id.reservation_list_button);
        reservationListButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == reservationButton) {
            onReservationButtonClick();
        } else if (view == reservationListButton) {
            onReservationListButtonClick();
        } else {
            throw new AssertionError("view not found");
        }
    }

    private void onReservationListButtonClick() {
        startActivity(new Intent(this, ReservationListActivity.class));
    }

    private void onReservationButtonClick() {
        Gadget gadget = (Gadget) reservationArticleSpinner.getSelectedItem();

        LibraryService.reserveGadget(gadget, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                showMessage("Article reserved");
            }

            @Override
            public void onError(String message) {
                showMessage(message);
            }
        });
    }

    private void loadArticles() {
        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                fillArticles(input);
            }

            @Override
            public void onError(String message) {
                showMessage(message);
            }
        });
    }

    private void fillArticles(List<Gadget> input) {
        Gadget[] gadgets = input.toArray(new Gadget[input.size()]);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                gadgets);
        reservationArticleSpinner.setAdapter(spinnerArrayAdapter);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}