package ch.kananga.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button reservationViewButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        reservationViewButton = (Button) findViewById(R.id.reservation_view_button);
        reservationViewButton.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
        if (view == reservationViewButton) {
            onReservationViewButtonClick();
        }
    }

    private void onReservationViewButtonClick() {
        startActivity(new Intent(this, ReservationActivity.class));
    }
}
