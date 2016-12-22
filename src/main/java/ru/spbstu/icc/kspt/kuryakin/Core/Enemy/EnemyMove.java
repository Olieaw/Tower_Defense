package ru.spbstu.icc.kspt.kuryakin.Core.Enemy;

import ru.spbstu.icc.kspt.kuryakin.Core.SpawnPoint;

public class EnemyMove {

    public Enemy enemy;

    public int xPos;
    public int yPos;

    public int routePosX;
    public int routePosY;

    public int health;

    public EnemyMove(Enemy enemy, SpawnPoint spawnPoint){

        this.enemy = enemy;

        this.routePosX = spawnPoint.getX();
        this.routePosY = spawnPoint.getY();

        this.xPos = spawnPoint.getX() * 40;
        this.yPos = spawnPoint.getY() * 40;

        this.health = enemy.health;
    }

    public EnemyMove update(){
        EnemyMove currentEnemy = this;

        if (currentEnemy.health <= 0){
            return null;
        }

        return currentEnemy;
    }
}
