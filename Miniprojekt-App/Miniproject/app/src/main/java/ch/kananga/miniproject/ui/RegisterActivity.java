package ch.kananga.miniproject.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.registrationRegisterButton);
        registerButton.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        LibraryService.setServerAddress(settings.getString(getString(R.string.serverAddress), ""));
    }

    @Override
    public void onClick(View view) {
        if (view == registerButton) {
            onRegisterButtonClick();
        } else {
            throw new AssertionError("view not found");
        }
    }

    private void onRegisterButtonClick() {
        final String email = ((TextView)findViewById(R.id.registrationEmailText)).getText().toString();
        final String password = ((TextView)findViewById(R.id.registrationPasswordText)).getText().toString();
        final String name = ((TextView)findViewById(R.id.registrationNameText)).getText().toString();
        final String matrikelNr = ((TextView)findViewById(R.id.registrationMatrikelText)).getText().toString();

        final Callback<Boolean> loginCallback = new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                Log.d("DEBUG", "Login Successful");
                startLoanActivity();
            }

            @Override
            public void onError(String message) {
                showToast(message);
            }
        };

        Callback<Boolean> registerCallback = new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                LibraryService.login(email, password, loginCallback);
            }

            @Override
            public void onError(String message) {
                showToast(message);
                Log.d("ERROR", "onError message=" + message);
            }
        };

        LibraryService.register(email, password, name, matrikelNr, registerCallback);
    }
}