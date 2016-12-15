package ru.spbstu.icc.kspt.kuryakin.Core.EnemyAI;

import ru.spbstu.icc.kspt.kuryakin.Core.Level;
import ru.spbstu.icc.kspt.kuryakin.Core.Map;

public class EnemyRoute {

    Map map;

    public int[][] route = new int[14][14];

    public int RIGHT = 1;
    public int DOWN = 2;
    public int LEFT = 3;
    public int UP = 4;

    int lastPos = -1;

    public int xPos;
    public int yPos;

    int baseBlock = 3;
    Base base;

    public EnemyRoute(Map map){
        this.map = map;

        this.xPos = this.map.level.spawnPoint.getX();
        this.yPos = this.map.level.spawnPoint.getY();

        calculateRoute();
    }

    private void calculateRoute() {

        while (base == null){
            calculateNextPos();
        }
    }

    private void calculateNextPos() {
        for (int i = 0; i < 5; i++){
            if (i != lastPos){
                if (yPos > 0 && i == UP){
                    if (map.map[xPos][yPos - 1] == 1){
                        this.lastPos = DOWN;
                        route[xPos][yPos] = UP;

                        yPos--;

                        break;
                    }else if (map.map[xPos][yPos - 1] == baseBlock){
                        base = new Base(xPos, yPos);

                        break;
                    }
                }

                if (xPos < 14 && i == RIGHT){
                    if (map.map[xPos + 1][yPos] == 1){
                        this.lastPos = LEFT;
                        route[xPos][yPos] = RIGHT;

                        xPos++;

                        break;
                    }else if (map.map[xPos + 1][yPos] == baseBlock){
                        base = new Base(xPos, yPos);

                        break;
                    }
                }

                if (xPos > 0 && i == LEFT){
                    if (map.map[xPos - 1][yPos] == 1){
                        this.lastPos = RIGHT;
                        route[xPos][yPos] = LEFT;

                        xPos--;

                        break;
                    }else if (map.map[xPos - 1][yPos] == baseBlock){
                        base = new Base(xPos, yPos);

                        break;
                    }
                }

                if (yPos < 14 && i == DOWN){
                    if (map.map[xPos][yPos + 1] == 1){
                        this.lastPos = UP;
                        route[xPos][yPos] = DOWN;

                        yPos++;

                        break;
                    }else if (map.map[xPos][yPos + 1] == baseBlock){
                        base = new Base(xPos, yPos);

                        break;
                    }
                }
            }
        }
    }
}
