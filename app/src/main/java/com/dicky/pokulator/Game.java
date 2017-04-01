package com.dicky.pokulator;

import java.util.ArrayList;

/**
 * Created by Dicky on 1/4/2017.
 */

public class Game {
    private ArrayList<User> users;
    private ArrayList<Round> rounds;

    private int bet;
    private boolean currentClockrise;
    private boolean iter;

    public Game (int bet, int direction) {
        users = new ArrayList<User> ();
        rounds = new ArrayList<Round> ();
        if (direction == 0) iter = true;
        else {
            iter = false;
            currentClockrise = (direction == 1) ? true : false;
        }
    }

    public void StartNewRound (ArrayList<User> users, int[] cardLefts) {
        rounds.add (new Round (currentClockrise, users, cardLefts));
        updateBalance();
        if (iter) currentClockrise = !currentClockrise;
    }

    private void updateBalance () {
        // TODO: balance calculation
    }

    public void GetAllBalance () {
        for (User user : users) {
            // TODO: print balance list
        }
    }

    public void GetRecordList () {
        for (Round round : rounds) {
            // TODO: print record list
        }
    }
}
