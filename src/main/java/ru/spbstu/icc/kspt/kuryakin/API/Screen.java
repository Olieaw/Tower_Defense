package ru.spbstu.icc.kspt.kuryakin.API;

import ru.spbstu.icc.kspt.kuryakin.Core.Map;
import ru.spbstu.icc.kspt.kuryakin.Core.Tower.Tower;
import ru.spbstu.icc.kspt.kuryakin.Core.Tower.TowerMap;

import java.util.Scanner;

public class Screen {

    Map map;
    TowerMap towerMap;

    public Screen(){
        this.map = new Map("firstLevel");
        this.towerMap = new TowerMap();
    }

    public void Menu(){
        Scanner val = new Scanner(System.in);
        int id;
        int x;
        int y;



        while (true) {
            printMap();
            printTowerList();

            id = val.nextInt();
            x = val.nextInt();
            y = val.nextInt();
            if (towerMap.AddTower(x - 1, y - 1, id - 1) == true){
                System.out.println("Башня установленна");
            }else {
                System.out.println("Башняне неустановленна");
            }

        }



//        switch (val){
//            case 1:
//                towerMap.AddTower(2, 2, 0);
//                break;
//            case 2:
//                towerMap.AddTower(2, 2, 1);
//                break;
//            default:
//
//                break;
//        }
    }

    private void printMap(){

        System.out.println("    1  2  3  4  5  6  7  8  9 10 11 12 13 14");

        for (int x = 0; x < 14; x++) {
            if(x<9){
                System.out.print(" " + (x + 1) + "  ");
            }else {
                System.out.print((x + 1) + "  ");
            }
            for (int y = 0; y < 14; y++) {
                if (TowerMap.towerMap[x][y] != null) {
                    switch (TowerMap.towerMap[x][y].id){
                        case 0:
                            System.out.print("L  ");
                            break;
                        case 1:
                            System.out.print("F  ");
                            break;
                        default:

                            break;
                    }
                }
                else {
                    System.out.print(Map.map[y][x] + "  ");
                }
            }
            System.out.println();
        }
    }

    private void printTowerList(){
        if(TowerMap.towerMap != null){
            for (int i = 0; Tower.towerList[i] != null; i++) {
                System.out.println((i+1) + ". " + Tower.towerList[i].name);
            }
        }
    }
}
