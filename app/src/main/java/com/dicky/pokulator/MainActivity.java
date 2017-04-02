package com.dicky.pokulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.arraySpinner = new String[] {
                "Alternate", "Clockwise", "Anti-clockwise"
        };
        Spinner s = (Spinner) findViewById(R.id.dirInput);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
    }

    public void StartNewGame (View view) {
        Intent intent = new Intent(this, SwipeActivity.class);
        EditText betInput = (EditText) findViewById(R.id.betInput);
        Spinner dirInput = (Spinner) findViewById(R.id.dirInput);

        TextView error = (TextView) findViewById(R.id.error);
        // validation
        double bet;
        try {
            bet = Double.parseDouble(betInput.getText().toString());
        } catch (Exception e) { // no input for the boxes
            error.setText("Bet value is required.");
            return;
        }
        int dir = dirInput.getSelectedItemPosition();

        Game.getInstance().StartGame(bet, dir);
        startActivity(intent);
    }
}
