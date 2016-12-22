package ru.spbstu.icc.kspt.kuryakin.Core;

public class User {

    public static int health;
    public static int money;
    public User(){
        health = 100;
        money = 300;
    }

    public boolean gameOver(){
        if (health < 1){
            return true;
        }
        return false;
    }
}
