package ch.kananga.miniproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;

import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;
import ch.kananga.miniproject.ui.ReservationActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(this);

        LibraryService.setServerAddress(getString(R.string.serverAddress));
    }
    
    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            onLoginButtonClick();
        } else {
            throw new AssertionError("event handler not implemented");
        }
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
                    Snackbar errorSnack = Snackbar.make(
                            findViewById(R.id.loginCoordinatorLayout),
                            R.string.loginAccessError,
                            Snackbar.LENGTH_LONG);
                    errorSnack.show();
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
