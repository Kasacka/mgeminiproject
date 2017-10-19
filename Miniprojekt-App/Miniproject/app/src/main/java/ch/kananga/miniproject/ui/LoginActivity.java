package ch.kananga.miniproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button loginButton;
    private Button loginRegistrationButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(this);

        loginRegistrationButton = (Button) findViewById(R.id.loginRegistrationButton);
        loginRegistrationButton.setOnClickListener(this);

        LibraryService.setServerAddress(getString(R.string.serverAddress));
    }
    
    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            onLoginButtonClick();
        } else if (view == loginRegistrationButton) {
            onLoginRegistrationButtonClick();
        } else {
            throw new AssertionError("event handler not implemented");
        }
    }

    private void onLoginRegistrationButtonClick() {
        startActivity(new Intent(this, RegisterActivity.class));
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
                    startLoanActivity();
                }
                else {
                    showToast("Something went totally wrong!");
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