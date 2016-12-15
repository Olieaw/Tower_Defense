package ru.spbstu.icc.kspt.kuryakin.Core.Enemy;

public class Enemy {

    public static final Enemy[] enemyList = new Enemy[100];

    public static final Enemy slime = new EnemySlime(0, 5, 2, 10, 3, 4);

    public int id;
    public int price;
    public double speed;
    public double attackSpeed;
    public int damage;
    public int health;

    public Enemy(int id, int price, int damage, int health, double speed, double attackSpeed){
        if(enemyList[id] != null){
            System.out.println("EnemyAI initialization");
        }else{
            enemyList[id] = this;

            this.id = id;
            this.price = price;
            this.damage = damage;
            this.health = health;
            this.speed = speed;
            this.attackSpeed = attackSpeed;
        }
    }
}