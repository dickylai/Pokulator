package com.dicky.pokulator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoundInputFragment extends Fragment {
    private int roundNo;
    private boolean currentClockwise;
    private String name1, name2, name3, name4;

    public RoundInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_round_input, container, false);
        TextView roundValue = (TextView) v.findViewById(R.id.RoundValue);
        roundValue.setText(String.valueOf(Game.getInstance().GetRoundNo()));

        TextView dirValue = (TextView) v.findViewById(R.id.dirValue);
        String dirText = (Game.getInstance().GetCurrentClockwise()) ? "Clockwise" : "Anti-clockwise";
        dirValue.setText(dirText);

        if (Game.getInstance().GetRoundNo() > 1) {
            EditText nameInput1 = (EditText) v.findViewById(R.id.nameInput1);
            nameInput1.setText(Game.getInstance().GetLastRoundNames().get(0));
            EditText nameInput2 = (EditText) v.findViewById(R.id.nameInput2);
            nameInput2.setText(Game.getInstance().GetLastRoundNames().get(1));
            EditText nameInput3 = (EditText) v.findViewById(R.id.nameInput3);
            nameInput3.setText(Game.getInstance().GetLastRoundNames().get(2));
            EditText nameInput4 = (EditText) v.findViewById(R.id.nameInput4);
            nameInput4.setText(Game.getInstance().GetLastRoundNames().get(3));
        }

        return v;
    }

}
