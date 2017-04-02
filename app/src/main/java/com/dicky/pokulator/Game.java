package com.dicky.pokulator;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Dicky on 1/4/2017.
 */

public class Game {
    private ArrayList<User> users;
    private ArrayList<Round> rounds;

    private double bet;
    private int roundNo;
    private boolean currentClockwise;
    private boolean iter;
    private ArrayList<String> lastRoundNames;

    private static final Game game = new Game ();
    public static Game getInstance() {return game;}

    public void StartGame (double bet, int direction) {
        users = new ArrayList<User> ();
        rounds = new ArrayList<Round> ();
        lastRoundNames = null;
        this.bet = bet;
        roundNo = 1;
        if (direction == 0) {
            iter = true;
            currentClockwise = true;
        }
        else {
            iter = false;
            currentClockwise = (direction == 1) ? true : false;
        }
    }

    public void EndRound (ArrayList<String> names, int[] cardLefts) {
        // check if user existed
        ArrayList<User> roundUsers = new ArrayList<User>();
        for (String name : names) {
            boolean match = false;
            for (User user : users) {
                if (user.GetName().equals(name)) {
                    roundUsers.add(user);
                    match = true;
                    break;
                }
            }
            if (!match) {
                User newUser = new User(name);
                users.add(newUser);
                roundUsers.add(newUser);
            }
        }

        // store a new round
        Round newRound = new Round (roundNo, currentClockwise, roundUsers, cardLefts);
        rounds.add (newRound);

        // update balance
        updateBalance(newRound.GetRoundRecord());

        // prepare for next round
        roundNo++;
        if (iter) currentClockwise = !currentClockwise;
        lastRoundNames = names;
    }

    private void updateBalance (Record record) {
        for (int i = 0; i < 4; i++) {
            int temp = 0;
            for (int j = 0; j < 4; j++) {
                if (i == j) continue;
                temp += record.GetUserRoundRecord(j).GetCardLeft() - record.GetUserRoundRecord(i).GetCardLeft();
            }
            record.GetUserRoundRecord(i).GetUser().SetBalance(temp*bet);
        }
    }

    public ArrayList<User> GetAllUsers () {
        return users;
    }

    public ArrayList<Round> GetRoundList () {
        return rounds;
    }

    public int GetRoundNo () {
        return roundNo;
    }

    public boolean GetCurrentClockwise () {
        return currentClockwise;
    }

    public ArrayList<String> GetLastRoundNames () {
        return lastRoundNames;
    }
}
