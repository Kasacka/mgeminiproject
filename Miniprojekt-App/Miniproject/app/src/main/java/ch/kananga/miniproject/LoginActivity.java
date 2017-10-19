package ch.kananga.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;
import ch.kananga.miniproject.ui.RegisterActivity;
import ch.kananga.miniproject.ui.ReservationActivity;
import ch.kananga.miniproject.ui.ReservationListActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private Button loginRegistrationButton;
    private Button reservationButtonLink;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(this);

        reservationButtonLink = findViewById(R.id.reservation_button_link);
        reservationButtonLink.setOnClickListener(this);

        loginRegistrationButton = findViewById(R.id.loginRegistrationButton);
        loginRegistrationButton.setOnClickListener(this);

        LibraryService.setServerAddress(getString(R.string.serverAddress));
    }
    
    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            onLoginButtonClick();
        } else if (view == loginRegistrationButton) {
            onLoginRegistrationButtonClick();
        } else if (view == reservationButtonLink) {
            onReservationButtonLinkClick();
        }
        else {
            throw new AssertionError("event handler not implemented");
        }
    }

    private void onReservationButtonLinkClick() {
        startActivity(new Intent(this, ReservationListActivity.class));
    }

    private void onLoginRegistrationButtonClick() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void showToast() {
        Toast.makeText(this, R.string.loginAccessError, Toast.LENGTH_LONG)
                .show();
    }

    private void onLoginButtonClick() {
        String username   = ((EditText) findViewById(R.id.loginEmailText)).getText().toString();
        String password   = ((EditText) findViewById(R.id.loginPasswordText)).getText().toString();

        Callback<Boolean> result = new Callback<Boolean>()
        {
            @Override
            public void onCompletion(Boolean success)
            {
                System.out.println("in here with " + success.toString() + "...");

                if (success) {
                    startActivity(new Intent(LoginActivity.this, ReservationActivity.class));
                }
                else {
                    showToast();
                }
            }

            @Override
            public void onError(String message)
            {
                ImageView badimage = (ImageView) findViewById(R.id.loginAppImageView);
                badimage.setImageResource(R.drawable.gadget_app_bad);
            }
        };

        LibraryService.login(username, password, result);
    }
}
