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

    public Round (int roundNo, boolean clockDir, ArrayList<User> users, int[] cardLefts) {
        this.roundNo = roundNo;
        this.clockDir = clockDir;
        record = new Record (users, cardLefts);
    }

    public Record GetRoundRecord () {
        return record;
    }
//    public string PrintRoundRecord () {
//        // TODO: print round numeber + direction + record
//    }
}