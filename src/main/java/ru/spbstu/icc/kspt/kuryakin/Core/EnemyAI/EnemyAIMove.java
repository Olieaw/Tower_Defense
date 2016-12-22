package ru.spbstu.icc.kspt.kuryakin.Core.EnemyAI;

import ru.spbstu.icc.kspt.kuryakin.Core.Enemy.EnemyMove;
import ru.spbstu.icc.kspt.kuryakin.Core.User;

public class EnemyAIMove extends EnemyAI {


    public EnemyAIMove(int id){

        super(id);
    }

    public void move (EnemyMove enemy) {
            if (enemy.routePosX == basePosX && enemy.routePosY == basePosY) {
                attackEnemy(enemy);
            } else {
                if (route.route[enemy.routePosX][enemy.routePosY] == route.UP) {
                    enemy.routePosY--;

                } else if (route.route[enemy.routePosX][enemy.routePosY] == route.DOWN) {
                    enemy.routePosY++;
                } else if (route.route[enemy.routePosX][enemy.routePosY] == route.RIGHT) {
                    enemy.routePosX++;
                } else if (route.route[enemy.routePosX][enemy.routePosY] == route.LEFT) {
                    enemy.routePosX--;
                }
            }
    }


    public void attackEnemy(EnemyMove enemy){
        User.health -= enemy.enemy.damage;
        enemy.health -= enemy.health;
    }
}
