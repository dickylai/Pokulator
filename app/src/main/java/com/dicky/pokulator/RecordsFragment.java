package com.dicky.pokulator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment extends Fragment {
    private ArrayList<Round> rounds;
    private ArrayList<User> users;

    TableLayout tableLayout;
    TableRow tr;
    TextView p1Str, p2Str, p3Str, p4Str, roundStr;


    public RecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_records, container, false);
        users = null;
        rounds = Game.getInstance().GetRoundList();
        tableLayout = (TableLayout) v.findViewById(R.id.tableLayout);
        tableLayout.setColumnStretchable(0, true);
        tableLayout.setColumnStretchable(1, true);
        tableLayout.setColumnStretchable(2, true);
        tableLayout.setColumnStretchable(3, true);
        tableLayout.setColumnStretchable(4, true);

        for (Round round : rounds) {
            if (users == null || !users.equals(round.GetUsers())) {
                tr = new TableRow(this.getActivity());
                roundStr = new TextView(this.getActivity());
                p1Str = new TextView(this.getActivity());
                p2Str = new TextView(this.getActivity());
                p3Str = new TextView(this.getActivity());
                p4Str = new TextView(this.getActivity());

                roundStr.setText("");
                p1Str.setText(round.GetRoundRecord().GetUserRoundRecord(0).GetUser().GetName());
                p2Str.setText(round.GetRoundRecord().GetUserRoundRecord(1).GetUser().GetName());
                p3Str.setText(round.GetRoundRecord().GetUserRoundRecord(2).GetUser().GetName());
                p4Str.setText(round.GetRoundRecord().GetUserRoundRecord(3).GetUser().GetName());
                tr.addView(roundStr);
                tr.addView(p1Str);
                tr.addView(p2Str);
                tr.addView(p3Str);
                tr.addView(p4Str);
                tableLayout.addView(tr);
            }
            tr = new TableRow(this.getActivity());
            roundStr = new TextView(this.getActivity());
            p1Str = new TextView(this.getActivity());
            p2Str = new TextView(this.getActivity());
            p3Str = new TextView(this.getActivity());
            p4Str = new TextView(this.getActivity());

            roundStr.setText(String.valueOf(round.GetRoundNo()));
            p1Str.setText(String.valueOf(round.GetRoundRecord().GetUserRoundRecord(0).GetCardLeft()));
            p2Str.setText(String.valueOf(round.GetRoundRecord().GetUserRoundRecord(1).GetCardLeft()));
            p3Str.setText(String.valueOf(round.GetRoundRecord().GetUserRoundRecord(2).GetCardLeft()));
            p4Str.setText(String.valueOf(round.GetRoundRecord().GetUserRoundRecord(3).GetCardLeft()));
            tr.addView(roundStr);
            tr.addView(p1Str);
            tr.addView(p2Str);
            tr.addView(p3Str);
            tr.addView(p4Str);
            tableLayout.addView(tr);
            users = round.GetUsers();
        }

        return v;
    }

}
