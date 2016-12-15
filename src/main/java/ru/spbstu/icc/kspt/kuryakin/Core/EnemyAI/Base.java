package ru.spbstu.icc.kspt.kuryakin.Core.EnemyAI;

public class Base {

    public int yPos;
    public int xPos;

    int baseGround;

    private int RIGHT = 1;
    private int DOWN = 2;
    private int LEFT = 3;
    private int UP = 4;

    public Base(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
