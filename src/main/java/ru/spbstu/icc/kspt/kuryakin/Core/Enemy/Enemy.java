package ru.spbstu.icc.kspt.kuryakin.Core.Enemy;

public class Enemy {

    public static final Enemy[] enemyList = new Enemy[100];

    public static final Enemy slime = new EnemySlime(0, 10, 50, 3);
    public static final Enemy bigSlime = new EnemyBigSlime(1, 20, 100, 2);

    public int id;
    public double speed;
    public int damage;
    public int health;

    public Enemy(int id, int damage, int health, double speed){
        if(enemyList[id] != null){
        }else{
            enemyList[id] = this;

            this.id = id;
            this.damage = damage;
            this.health = health;
            this.speed = speed;
        }
    }
}
