package ru.spbstu.icc.kspt.kuryakin.Core;

public class SpawnPoint {

    private static int x;
    private static int y;

    public SpawnPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static int getX(){
        return x;
    }

    public static int getY(){
        return y;
    }
}
