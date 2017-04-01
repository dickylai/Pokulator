package com.dicky.pokulator;

/**
 * Created by Dicky on 1/4/2017.
 */

public class IndividualRecord {
    private User user;
    private int cardLeft;

    public IndividualRecord (User user, int cardLeft) {
        this.user = user;
        this.cardLeft = cardLeft;
    }

    public User GetUser () {
        return user;
    }

    public int GetCardLeft () {
        return cardLeft;
    }
}