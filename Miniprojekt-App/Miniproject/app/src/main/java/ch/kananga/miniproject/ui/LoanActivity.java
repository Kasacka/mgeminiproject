package ch.kananga.miniproject.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Loan;
import ch.kananga.miniproject.service.Callback;
import ch.kananga.miniproject.service.LibraryService;
import ch.kananga.miniproject.ui.viewList.LoanAdapter;

public class LoanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = getSharedPreferences(getString(R.string.settings), MODE_PRIVATE);
        LibraryService.setServerAddress(settings.getString(getString(R.string.serverAddress), ""));

        final RecyclerView lentGadgets = (RecyclerView) findViewById(R.id.lendingList);
        lentGadgets.setHasFixedSize(true);

        final TextView emptyText = (TextView) findViewById(R.id.empty_loans);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lentGadgets.setLayoutManager(layoutManager);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefreshLoans);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLoans(lentGadgets, emptyText);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        loadLoans(lentGadgets, emptyText);
    }

    private void loadLoans(final RecyclerView list, final TextView emptyText) {
        Callback<List<Loan>> result = new Callback<List<Loan>>()
        {
            @Override
            public void onCompletion(List<Loan> loans)
            {
                LoanAdapter loanAdapter = new LoanAdapter(loans);
                list.setAdapter(loanAdapter);

                if (loans.isEmpty()) {
                    list.setVisibility(View.GONE);
                    emptyText.setVisibility(View.VISIBLE);
                }
                else {
                    list.setVisibility(View.VISIBLE);
                    emptyText.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String message)
            {
                Log.e("ERROR", "onError message=" + message);
                showToast("Die ausgeliehenen Gadgets konnten nicht geladen werden.");
                list.setVisibility(View.GONE);
                emptyText.setVisibility(View.VISIBLE);
            }
        };
        LibraryService.getLoansForCustomer(result);
    }

}
