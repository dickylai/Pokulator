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
    private boolean undoAction;
    private Round lastRound;
    private ArrayList<String> lastRoundNames;
    private int[] lastRoundCards;

    private static final Game game = new Game ();
    public static Game getInstance() {return game;}

    public void StartGame (double bet, int direction) {
        users = new ArrayList<User> ();
        rounds = new ArrayList<Round> ();
        lastRoundNames = null;
        lastRoundCards = null;
        this.bet = bet;
        roundNo = 1;
        undoAction = false;
        if (direction == 0) {
            iter = true;
            currentClockwise = true;
        }
        else {
            iter = false;
            currentClockwise = (direction == 1) ? true : false;
        }
    }

    public void UndoRound () {
        roundNo--;
        lastRound = rounds.get(rounds.size() - 1);
        lastRoundNames = new ArrayList<String>();
        lastRoundCards = new int[4];
        for (int i = 0; i < 4; i++) {
            lastRoundNames.add(lastRound.GetUsers().get(i).GetName());
            lastRoundCards[i] = lastRound.GetRoundRecord().GetUserRoundRecord(i).GetCardLeft();
        }
        rounds.remove(rounds.size() - 1);
        if (iter) currentClockwise = !currentClockwise;
        updateBalance(lastRound.GetRoundRecord(), true);
        undoAction = true;
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
        for (int i = 0; i < 4; i++) {
            for (int j = i; j < 4; j++) {
                if (i == j) continue;
                if (roundUsers.get(j).IsIdSmaller(roundUsers.get(i))) {
                    User temp = roundUsers.get(j);
                    roundUsers.remove(j);
                    roundUsers.add(i,temp);
                }
            }
        }

        // store a new round
        Round newRound = new Round (roundNo, currentClockwise, roundUsers, cardLefts);
        rounds.add (newRound);

        // update balance
        updateBalance(newRound.GetRoundRecord(), false);

        // prepare for next round
        roundNo++;
        if (iter) currentClockwise = !currentClockwise;
        undoAction = false;
        lastRoundNames = names;
    }

    private void updateBalance (Record record, boolean undo) {
        int sign = 1;
        if (undo) sign = -1;
        for (int i = 0; i < 4; i++) {
            int temp = 0;
            for (int j = 0; j < 4; j++) {
                if (i == j) continue;
                temp += record.GetUserRoundRecord(j).GetCardLeft() - record.GetUserRoundRecord(i).GetCardLeft();
            }
            record.GetUserRoundRecord(i).GetUser().SetBalance(sign*temp*bet);
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

    public boolean IsUndoAction () { return undoAction; }

    public int[] GetLastRoundCards () { return lastRoundCards; }

    public ArrayList<String> GetLastRoundNames () {
        return lastRoundNames;
    }
}
