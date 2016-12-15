package ru.spbstu.icc.kspt.kuryakin.Core;

public class Map {

    public Level level;

    public static int map[][];

    public Map(String str){
        LevelFile levelFile = new LevelFile();
        this.level = levelFile.getLevel(str);
        this.level.findSpawnPoint();
        this.map = this.level.map;
    }
}
