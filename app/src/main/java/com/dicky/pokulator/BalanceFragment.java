package com.dicky.pokulator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceFragment extends Fragment {
    private ArrayList<User> users;
    TableLayout tableLayout;
    TableRow tr;
    TextView nameStr, balanceStr;

    public BalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_balance, container, false);
        users = Game.getInstance().GetAllUsers();
        tableLayout = (TableLayout)  v.findViewById(R.id.tableLayout);
        tableLayout.setColumnStretchable(0, true);
        tableLayout.setColumnStretchable(1, true);

        tr = new TableRow(this.getActivity());
        nameStr = new TextView(this.getActivity());
        balanceStr = new TextView(this.getActivity());
        nameStr.setText("Name");
        nameStr.setTextSize(20);
        nameStr.setGravity(Gravity.CENTER);
        balanceStr.setText("Balance ($)");
        balanceStr.setTextSize(20);
        balanceStr.setGravity(Gravity.CENTER);
        tr.addView(nameStr);
        tr.addView(balanceStr);
        tableLayout.addView(tr);

        for (User user : users) {
            tr = new TableRow(this.getActivity());
            nameStr = new TextView(this.getActivity());
            balanceStr = new TextView(this.getActivity());
            nameStr.setText(user.GetName());
            nameStr.setTextSize(20);
            nameStr.setGravity(Gravity.CENTER);
            balanceStr.setText(String.valueOf(user.GetBalance()));
            balanceStr.setTextSize(20);
            balanceStr.setGravity(Gravity.CENTER);
            tr.addView(nameStr);
            tr.addView(balanceStr);
            tableLayout.addView(tr);
        }

        return v;
    }

}
