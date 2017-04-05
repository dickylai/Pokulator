package com.dicky.pokulator;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SwipeActivity extends FragmentActivity {
    static final int NUM_OF_ITEMS = 3;
    ViewPager viewPager;
    SwipeAdaptor swipeAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        swipeAdaptor = new SwipeAdaptor(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(swipeAdaptor);
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {
    }

    public void EndThisGame (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void UndoRound (View view) {
        if (Game.getInstance().IsUndoAction()) return;
        if (Game.getInstance().GetRoundNo() > 1) {
            Game.getInstance().UndoRound();
            Intent intent = new Intent(this, SwipeActivity.class);
            startActivity(intent);
        }
    }

    public void EndThisRound (View view) {
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

        TextView error = (TextView) findViewById(R.id.error);

        try {
            cardLefts[0] = Integer.parseInt(cardInput1.getText().toString());
            cardLefts[1] = Integer.parseInt(cardInput2.getText().toString());
            cardLefts[2] = Integer.parseInt(cardInput3.getText().toString());
            cardLefts[3] = Integer.parseInt(cardInput4.getText().toString());
        } catch (Exception e) { // no input for the boxes
            error.setText("Card value(s) are required.");
            return;
        }

        // validation
        for (int i = 0; i < 4; i++) {
            for (int j = i+1; j < 4; j++) {
                if (names.get(i).equals(names.get(j))) {
                    error.setText("Duplicated names are detected.");
                    return;
                }
            }
        }
        int countZero = 0;
        for (int i = 0; i < 4; i++) {
            if (cardLefts[i] == 0) countZero++;
        }
        if (countZero != 1) {
            error.setText("One winner should be provided.");
            return;
        }

        Game.getInstance().EndRound(names, cardLefts);

        Intent intent = new Intent(this, SwipeActivity.class);
        startActivity(intent);
    }
}
