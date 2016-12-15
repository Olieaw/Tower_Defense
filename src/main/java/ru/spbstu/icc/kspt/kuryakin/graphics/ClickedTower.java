package ru.spbstu.icc.kspt.kuryakin.graphics;

import ru.spbstu.icc.kspt.kuryakin.Core.Tower.TowerMap;

import java.awt.event.MouseEvent;

public class ClickedTower {

    boolean mouseClicked = false;
    public int click = 0;

//    public void towerClick(MouseEvent e){
//        int xPos = (e.getX() - 5)/ Screen.towerSize;
//        int yPos = (e.getY() - 28)/ Screen.towerSize;
//
//        if (mouseClicked && TowerMap.towerMap[xPos][yPos].id == 0) {
//            click = 1;
//        }
//
//        if (mouseClicked && TowerMap.towerMap[xPos][yPos].id == 1) {
//            click = 2;
//        }
//    }

    public void clicked(MouseEvent e) {
        int xPos = (e.getX() - 5)/ Screen.towerSize;
        int yPos = (e.getY() - 28)/ Screen.towerSize;

        mouseClicked = true;
        if (click != 0){
            click = 0;
        }else {
            if (mouseClicked && TowerMap.towerMap[xPos][yPos].id == 0 && TowerMap.towerMap[xPos][yPos] == null) {
                click = 1;
            }

            if (mouseClicked && TowerMap.towerMap[xPos][yPos].id == 1 && TowerMap.towerMap[xPos][yPos] == null) {
                click = 2;
            }
        }
    }
}

