package ch.kananga.miniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Adler32;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner reservationArticleSpinner;
    private Button reservationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        reservationArticleSpinner = (Spinner) findViewById(R.id.reservation_article);

        reservationButton = (Button) findViewById(R.id.reservation_button);
        reservationButton.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.reservation_array, R.layout.support_simple_spinner_dropdown_item);

        reservationArticleSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == reservationButton) {
            onReservationButtonClick();
        }
    }

    private void onReservationButtonClick() {
        Toast.makeText(this, "hallo", Toast.LENGTH_LONG).show();
    }
}