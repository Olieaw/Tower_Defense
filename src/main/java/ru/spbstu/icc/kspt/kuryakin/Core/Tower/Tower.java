package ru.spbstu.icc.kspt.kuryakin.Core.Tower;

public class Tower {

    public int id;
    public int cost;
    public int range;
    public int damage;
    public String name;

    public static final Tower[] towerList = new Tower[10];

    public static final Tower lightningTower = new TowerLightning(0, 100, 1, 10, "LightningTower");
    public static final Tower fireTower = new TowerFire(1, 90, 2, 8, "FireTower");

    public Tower(int id, int cost, int range, int damage, String name){
        if (towerList[id] == null) {

            towerList[id] = this;

            this.id = id;
            this.cost = cost;
            this.range = range;
            this.damage = damage;
            this.name = name;
        }
    }
}
