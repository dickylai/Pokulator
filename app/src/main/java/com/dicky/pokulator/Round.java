package com.dicky.pokulator;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dicky on 1/4/2017.
 */

public class Round {
    private int roundNo;
    private boolean clockDir;
    private Record record;
    private ArrayList<User> users;

    public Round (int roundNo, boolean clockDir, ArrayList<User> users, int[] cardLefts) {
        this.roundNo = roundNo;
        this.clockDir = clockDir;
        this.users = users;
        record = new Record (users, cardLefts);
    }

    public ArrayList<User> GetUsers () {
        return users;
    }

    public int GetRoundNo () {
        return roundNo;
    }

    public Record GetRoundRecord () {
        return record;
    }
}