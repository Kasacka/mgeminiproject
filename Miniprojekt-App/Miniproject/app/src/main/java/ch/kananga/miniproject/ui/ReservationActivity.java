package ch.kananga.miniproject.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.List;
import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Gadget;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class ReservationActivity extends BaseActivity implements View.OnClickListener {
    private Spinner reservationArticleSpinner;
    private Button reservationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        LibraryService.setServerAddress(settings.getString(getString(R.string.serverAddress), ""));

        reservationArticleSpinner = (Spinner) findViewById(R.id.reservation_article);

        loadArticles();
        reservationButton = (Button) findViewById(R.id.reservation_button);
        reservationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == reservationButton) {
            onReservationButtonClick();
        } else {
            throw new AssertionError("Dieser Event-Handler ist nicht implementiert!");
        }
    }

    private void onReservationButtonClick() {
        Gadget gadget = (Gadget) reservationArticleSpinner.getSelectedItem();

        LibraryService.reserveGadget(gadget, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                showToast("Artikel wurde reserviert");
            }

            @Override
            public void onError(String message) {
                showToast(message);
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
                Log.e("ERROR", "onError message=" + message);
                showToast(message);
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
}