package ru.spbstu.icc.kspt.kuryakin.Core;

public class Wave {

    int waveNumber = 0;
    int enemiesThisRound = 0;
    int enemiesPerRound = 10;

    public boolean  waveSpawning;

    public Wave(){

    }

    public void nextWave(){
        this.waveNumber++;
        this.enemiesThisRound = 0;
        this.waveSpawning = true;

        System.out.println("[Wave] Wave " + this.waveNumber + " incomming!");

//        for (int i = 0; i < this.screen.enemyMap.length; i++){
//            this.screen.enemyMap[i] = null;
//        }
    }

    private int currentDelay = 0;
    private int spawnRate = 1000;

    public void spawnEnemies(){
        if (this.enemiesThisRound < this.waveNumber * this.enemiesPerRound){
            if (currentDelay < spawnRate){
                currentDelay++;
            }else{
                currentDelay = 0;

                System.out.println("[Wave] Enemy Spawner");

                this.enemiesThisRound++;
//                this.screen.spawnEnemy();
            }
        }else {
            this.waveSpawning = false;
        }
    }
}
