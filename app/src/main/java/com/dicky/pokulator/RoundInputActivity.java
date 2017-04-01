package com.dicky.pokulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RoundInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_input);

        TextView roundValue = (TextView) findViewById(R.id.RoundValue);
        roundValue.setText(String.valueOf(Game.getInstance().GetRoundNo()));

        TextView dirValue = (TextView) findViewById(R.id.dirValue);
        String dirText = (Game.getInstance().GetCurrentClockwise()) ? "Clockwise" : "Anti-clockwise";
        dirValue.setText(dirText);

        if (Game.getInstance().GetRoundNo() > 1) {
            EditText nameInput1 = (EditText) findViewById(R.id.nameInput1);
            nameInput1.setText(Game.getInstance().GetLastRoundNames().get(0));
            EditText nameInput2 = (EditText) findViewById(R.id.nameInput2);
            nameInput2.setText(Game.getInstance().GetLastRoundNames().get(1));
            EditText nameInput3 = (EditText) findViewById(R.id.nameInput3);
            nameInput3.setText(Game.getInstance().GetLastRoundNames().get(2));
            EditText nameInput4 = (EditText) findViewById(R.id.nameInput4);
            nameInput4.setText(Game.getInstance().GetLastRoundNames().get(3));
        }
    }

    public void StartNewRound (View view) {
        ArrayList<String> names = new ArrayList<String>();
        int[] cardLefts = new int[4];

        TextView nameInput1 = (TextView) findViewById(R.id.nameInput1);
        TextView nameInput2 = (TextView) findViewById(R.id.nameInput2);
        TextView nameInput3 = (TextView) findViewById(R.id.nameInput3);
        TextView nameInput4 = (TextView) findViewById(R.id.nameInput4);

        TextView cardInput1 = (TextView) findViewById(R.id.cardInput1);
        TextView cardInput2 = (TextView) findViewById(R.id.cardInput2);
        TextView cardInput3 = (TextView) findViewById(R.id.cardInput3);
        TextView cardInput4 = (TextView) findViewById(R.id.cardInput4);

        names.add(nameInput1.getText().toString());
        names.add(nameInput2.getText().toString());
        names.add(nameInput3.getText().toString());
        names.add(nameInput4.getText().toString());

        try {
            cardLefts[0] = Integer.parseInt(cardInput1.getText().toString());
            cardLefts[1] = Integer.parseInt(cardInput2.getText().toString());
            cardLefts[2] = Integer.parseInt(cardInput3.getText().toString());
            cardLefts[3] = Integer.parseInt(cardInput4.getText().toString());
        } catch (Exception e) { // no input for the boxes
            return;
        }

        // validation
        for (int i = 0; i < 4; i++) {
            for (int j = i+1; j < 4; j++) {
                if (names.get(i).equals(names.get(j))) return;
            }
        }
        int countZero = 0;
        for (int i = 0; i < 4; i++) {
            if (cardLefts[i] == 0) countZero++;
        }
        if (countZero != 1) return;

        Game.getInstance().EndRound(names, cardLefts);

        Intent intent = new Intent(this, RoundInputActivity.class);
        startActivity(intent);
    }
}
