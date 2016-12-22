package ru.spbstu.icc.kspt.kuryakin.Core.Tower;

import ru.spbstu.icc.kspt.kuryakin.Core.Map;
import ru.spbstu.icc.kspt.kuryakin.Core.User;
import ru.spbstu.icc.kspt.kuryakin.graphics.Screen;

public class TowerMap {

    public static Tower[][] towerMap;

    public TowerMap(){

        towerMap = new Tower[14][14];
    }

    public boolean AddTower(int x, int y, int id){
        if(TowerMap.towerMap[x][y] == null && Map.map[x][y] == 0){

            TowerMap.towerMap[x][y] = Tower.towerList[id];

            User.money -= TowerMap.towerMap[x][y].cost;
            return true;
        }
        return false;
    }

    public boolean DeleteTower(int x, int y){

        if(TowerMap.towerMap[x][y] != null){

            User.money += (int)(TowerMap.towerMap[x][y].cost * 0.9);
            TowerMap.towerMap[x][y] = null;
            return true;
        }
        return false;
    }
}
