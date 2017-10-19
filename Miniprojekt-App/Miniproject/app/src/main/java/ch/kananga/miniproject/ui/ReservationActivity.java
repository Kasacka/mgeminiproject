package ch.kananga.miniproject.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import ch.kananga.miniproject.R;
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

        LibraryService.setServerAddress(getString(R.string.serverAddress));

        reservationArticleSpinner = (Spinner) findViewById(R.id.reservation_article);

        reservationButton = (Button) findViewById(R.id.reservation_button);
        reservationButton.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.reservation_array, R.layout.support_simple_spinner_dropdown_item);

        reservationArticleSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == reservationButton) {

        }
    }
}