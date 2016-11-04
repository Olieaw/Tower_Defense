package ru.spbstu.icc.kspt.kuryakin;

import ru.spbstu.icc.kspt.kuryakin.GUI.Screen;

public class User {
    private Screen screen;

    public Player player;

    int startingMoney = 300;
    int startingHeals = 100;

    public User(Screen screen) {
        this.screen = screen;

        this.screen.scene = 0;
    }

    public void createPlayer(){
        this.player = new Player(this);
    }
}
