package com.dicky.pokulator;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dicky on 1/4/2017.
 */

public class Round {
    private static final AtomicInteger countRound = new AtomicInteger(0);
    private int roundNo;
    private boolean clockDir;
    private Record record;

    public Round (boolean clockDir, ArrayList<User> users, int[] cardLefts) {
        roundNo = countRound.incrementAndGet();
        this.clockDir = clockDir;
        record = new Record (users, cardLefts);
    }

//    public string PrintRoundRecord () {
//        // TODO: print round numeber + direction + record
//    }
}