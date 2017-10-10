package ch.kananga.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.kananga.miniproject.ui.RegisterActivity;
import ch.kananga.miniproject.ui.ReservationActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button reservationViewButton;
    private Button registerButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        reservationViewButton = (Button) findViewById(R.id.reservation_view_button);
        registerButton = (Button) findViewById(R.id.register_button);

        reservationViewButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
        if (view == reservationViewButton) {
            onReservationViewButtonClick();
        } else if (view == registerButton) {
            onRegisterButtonClick();
        } else {
            throw new AssertionError("event handler not implemented");
        }
    }

    private void onReservationViewButtonClick() {
        startActivity(new Intent(this, ReservationActivity.class));
    }

    private void onRegisterButtonClick() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
