package ru.spbstu.icc.kspt.kuryakin.Core.EnemyAI;

import ru.spbstu.icc.kspt.kuryakin.Core.Map;

public class EnemyAI {

    public static EnemyRoute route;

    public static EnemyAIMove moveAI;

    public static int basePosX;
    public static int basePosY;

    public int id;

    public EnemyAI(Map map){
        route = new EnemyRoute(map);

        basePosX = route.base.xPos;
        basePosY = route.base.yPos;

        moveAI = new EnemyAIMove(0);
    }

    public EnemyAI(int id){

        this.id = id;
    }

    public EnemyRoute getRoute(){
        return route;
    }
}
