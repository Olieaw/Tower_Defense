package ru.spbstu.icc.kspt.kuryakin.Core;

import ru.spbstu.icc.kspt.kuryakin.Core.Enemy.Enemy;
import ru.spbstu.icc.kspt.kuryakin.Core.Enemy.EnemyMove;

public class Wave {

    int waveNumber = 0;
    int enemiesThisRound = 0;
    int enemiesPerRound = 10;

    private int currentDelay = 10;
    private int spawnRate = 10;

    public void spawnEnemies(EnemyMove[] enemyMap, boolean spawn) {

        if (spawn == true) {
            if (currentDelay < spawnRate) {
                currentDelay++;
            } else {
                currentDelay = 0;
                if (enemiesPerRound > enemiesThisRound) {
                    if (enemiesThisRound > (enemiesPerRound / 2)) {
                        enemyMap[enemiesThisRound] = new EnemyMove(Enemy.enemyList[0], Level.spawnPoint);
                        enemiesThisRound++;
                    } else {
                        enemyMap[enemiesThisRound] = new EnemyMove(Enemy.enemyList[1], Level.spawnPoint);
                        enemiesThisRound++;
                    }
                }
            }
        }
    }
}
