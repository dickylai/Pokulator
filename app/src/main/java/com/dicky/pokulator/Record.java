package com.dicky.pokulator;

import java.util.ArrayList;

/**
 * Created by Dicky on 1/4/2017.
 */

public class Record {
    private ArrayList<IndividualRecord> individualRecords;

    public Record (ArrayList<User> users, int[] cardLefts) {
        individualRecords = new ArrayList<IndividualRecord> ();

        for (int i = 0; i < 4; i++) {
            individualRecords.add(new IndividualRecord (users.get(i), cardLefts[i]));
        }
    }

    public IndividualRecord GetUserRoundRecord (int user) {
        return individualRecords.get(user);
    }
//    public String GetRecord () {
//        // TODO: print name + card lefts
//    }
}