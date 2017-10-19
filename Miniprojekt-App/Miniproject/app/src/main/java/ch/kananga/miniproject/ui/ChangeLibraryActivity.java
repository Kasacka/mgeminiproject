package ch.kananga.miniproject.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ch.kananga.miniproject.R;

public class ChangeLibraryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_library);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
