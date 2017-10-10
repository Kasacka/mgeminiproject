package ch.kananga.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.kananga.miniproject.ui.ReservationActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            onRegisterButtonClick();
        } else {
            throw new AssertionError("event handler not implemented");
        }
    }

    private void onRegisterButtonClick() {
        startActivity(new Intent(this, ReservationActivity.class));
    }
}
