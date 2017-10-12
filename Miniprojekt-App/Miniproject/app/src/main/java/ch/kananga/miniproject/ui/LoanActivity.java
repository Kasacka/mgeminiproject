package ch.kananga.miniproject.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Loan;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;
import ch.kananga.miniproject.ui.viewList.LoanAdapter;

public class LoanActivity extends AppCompatActivity {

    private List<Loan> loans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        LibraryService.setServerAddress(getString(R.string.serverAddress));

        RecyclerView lentGadgets = (RecyclerView) findViewById(R.id.lendingList);
        lentGadgets.setHasFixedSize(true);

        TextView emptyText = (TextView) findViewById(R.id.empty_loans);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lentGadgets.setLayoutManager(layoutManager);

        Callback<List<Loan>> result = new Callback<List<Loan>>()
        {
            @Override
            public void onCompletion(List<Loan> customerLoans)
            {
                loans = customerLoans;
            }

            @Override
            public void onError(String message)
            {
                showError("Something went wrong while get all customer loans");
            }
        };
        LibraryService.getLoansForCustomer(result);

        LoanAdapter loanAdapter = new LoanAdapter(loans);
        lentGadgets.setAdapter(loanAdapter);
        
        if (loans.isEmpty()) {
            lentGadgets.setVisibility(View.GONE);
            emptyText.setVisibility(View.VISIBLE);
        }
        else {
            lentGadgets.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.GONE);
        }
    }

    public void showError(String errorText) {
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show();
    }
}
