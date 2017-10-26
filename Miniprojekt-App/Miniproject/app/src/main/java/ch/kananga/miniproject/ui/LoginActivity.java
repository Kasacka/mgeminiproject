package ch.kananga.miniproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button loginButton;
    private Switch keepLoggedIn;
    private EditText usernameText;
    private EditText passwordText;
    private Button loginRegistrationButton;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginLoginButton);
        loginButton.setOnClickListener(this);

        keepLoggedIn = (Switch) findViewById(R.id.loginKeepSwitch);
        keepLoggedIn.setOnClickListener(this);
        usernameText = (EditText) findViewById(R.id.loginEmailText);
        passwordText = (EditText) findViewById(R.id.loginPasswordText);

        loginRegistrationButton = (Button) findViewById(R.id.loginRegistrationButton);
        loginRegistrationButton.setOnClickListener(this);

        settings = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        if (settings.getString(getString(R.string.serverAddress), null) == null) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(getString(R.string.serverAddress), "https://warm-ocean-14675.herokuapp.com/public");
            editor.apply();
        }
        if (settings.getBoolean("keepLoggedIn", false)) {
            keepLoggedIn.setChecked(true);
            usernameText.setText(settings.getString("username", ""));
            passwordText.setText(settings.getString("password", ""));
        }

        LibraryService.setServerAddress(settings.getString(getString(R.string.serverAddress), ""));
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            onLoginButtonClick();
        } else if (view == loginRegistrationButton) {
            onLoginRegistrationButtonClick();
        }else if (view == keepLoggedIn) {
            onLoginKeepLoggedInButtonClick();
        } else {
            throw new AssertionError("event handler not implemented");
        }
    }

    private void onLoginRegistrationButtonClick() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void onLoginButtonClick() {
        final String username   = usernameText.getText().toString();
        final String password   = passwordText.getText().toString();

        Callback<Boolean> result = new Callback<Boolean>()
        {
            @Override
            public void onCompletion(Boolean success)
            {
                if (success) {
                    if (keepLoggedIn.isChecked()) {
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("keepLoggedIn", true);
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.apply();
                    }
                    startLoanActivity();
                }
                else {
                    showToast("Login failed");
                }
            }

            @Override
            public void onError(String message)
            {
                ImageView badimage = (ImageView) findViewById(R.id.loginAppImageView);
                badimage.setImageResource(R.drawable.gadget_app_bad);

                if (message.equals("user does not exist")) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.loginCoordinatorLayout), message, Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Go to register", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startRegisterActivity();
                        }
                    });
                    snackbar.show();
                } else {
                    showToast(message);
                }
            }
        };

        LibraryService.login(username, password, result);
    }

    private void onLoginKeepLoggedInButtonClick() {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("keepLoggedIn", keepLoggedIn.isChecked());
        if (!keepLoggedIn.isChecked()) {
            editor.remove("username");
            editor.remove("password");
        }
        editor.apply();
    }

}
