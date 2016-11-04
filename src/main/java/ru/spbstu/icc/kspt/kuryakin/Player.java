package ru.spbstu.icc.kspt.kuryakin;

public class Player{

    public int health;
    public int money;

    public Player(User user) {
        this.money = user.startingMoney;
        this.health = user.startingHeals;
    }
}
