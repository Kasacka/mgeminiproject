package ch.kananga.miniproject.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ch.kananga.miniproject.R;

public class ChangeLibraryActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_library);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button changeLibrary = findViewById(R.id.libraryChangeButton);
        changeLibrary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText newLibrary = (EditText) findViewById(R.id.libraryServerText);

        SharedPreferences settings = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(R.string.serverAddress), newLibrary.getText().toString());
        editor.apply();

        logout();
    }
}
