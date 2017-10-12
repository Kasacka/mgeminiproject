package ch.kananga.miniproject.ui.viewList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ch.kananga.miniproject.R;
import ch.kananga.miniproject.domain.Loan;

public class LoanAdapter extends RecyclerView.Adapter<LoanViewHolder>{

    private List<Loan> dataset;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);

    public LoanAdapter(List<Loan> loans) {
        dataset = loans;
    }

    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.loan_rowlayout, parent, false);
        TextView gadgetName = (TextView) v.findViewById(R.id.gadgetName);
        TextView returnDate = (TextView) v.findViewById(R.id.returnDate);
        return new LoanViewHolder(v, gadgetName, returnDate);
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
        final Loan loan = dataset.get(position);
        holder.gadgetName.setText(loan.getGadget().getName());
        holder.returnDate.setText(formatter.format(loan.getReturnDate()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}