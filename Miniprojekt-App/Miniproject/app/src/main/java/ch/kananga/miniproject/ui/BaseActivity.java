package ch.kananga.miniproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;

import static ch.kananga.miniproject.R.id.loans;

public class BaseActivity extends AppCompatActivity {

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void startLoanActivity() {
        startActivity(new Intent(this, LoanActivity.class));
    }
    public void startRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
    private void startReservationListActivity() {
        startActivity(new Intent(this, ReservationListActivity.class));
    }
    public void startReservationActivity() {
        startActivity(new Intent(this, ReservationActivity.class));
    }
    private void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }
    private void startChangeLibraryActivity() {
        startActivity(new Intent(this, ChangeLibraryActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == loans) {
            startLoanActivity();
        } else if (item.getItemId() == R.id.reservations){
            startReservationListActivity();
        } else if (item.getItemId() == R.id.libraries) {
            startChangeLibraryActivity();
        } else if (item.getItemId() == R.id.logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Callback<Boolean> result = new Callback<Boolean>()
        {
            @Override
            public void onCompletion(Boolean success)
            {
                if (success) {
                    startLoginActivity();
                }
                else {
                    showToast("Something went totally wrong!");
                }
            }

            @Override
            public void onError(String message)
            {
                showToast("You could not logout from app. Please try again");
            }
        };
        LibraryService.logout(result);
    }
}
