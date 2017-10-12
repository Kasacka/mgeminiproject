package ch.kananga.miniproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ch.kananga.miniproject.R;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button registerButton;
    private TextView emailInput;
    private TextView passwordInput;
    private TextView matrikelInput;
    private TextView nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.register_button);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        matrikelInput = findViewById(R.id.matrikel_input);
        nameInput = findViewById(R.id.name_input);

        registerButton.setOnClickListener(this);

        LibraryService.setServerAddress(getString(R.string.serverAddress));
    }

    @Override
    public void onClick(View view) {
        if (view == registerButton) {
            onRegisterButtonClick();
        } else {
            throw new AssertionError("view not found");
        }
    }

    private void startReservationActivity() {
        startActivity(new Intent(this, ReservationActivity.class));
    }

    private void onRegisterButtonClick() {
        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();
        final String name = nameInput.getText().toString();
        final String matrikelNr = matrikelInput.getText().toString();

        final Callback<Boolean> loginCallback = new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                Log.d("DEBUG", "Login Successful");
                startReservationActivity();
            }

            @Override
            public void onError(String message) {

            }
        };

        Callback<Boolean> registerCallback = new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {


                LibraryService.login(email, password, loginCallback);
            }

            @Override
            public void onError(String message) {
                Log.d("ERROR", "onError message=" + message);
            }
        };

        LibraryService.register(email, password, name, matrikelNr, registerCallback);
    }
}