package com.dicky.pokulator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dicky on 1/4/2017.
 */

public class User {
    private static final AtomicInteger countUser = new AtomicInteger(0);
    private int id;
    private String name;
    private double balance;

    public User (String name) {
        id = countUser.incrementAndGet();
        this.name = name;
        balance = 0;
    }

    public int GetId () {
        return id;
    }

    public boolean IsEqual (User user2) {
        return (this.id == user2.GetId()) ? true : false;
    }

    public String GetName () {
        return name;
    }

    public double GetBalance () {
        return balance;
    }

    public void SetBalance (int change) {
        balance += change;
    }
}
