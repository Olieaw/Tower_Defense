package ru.spbstu.icc.kspt.kuryakin.Core.EnemyAI;

public class EnemyAIMove extends EnemyAI {


    public EnemyAIMove(int id){

        super(id);
    }

//    public void move (EnemyMove enemy){
//        if (enemy.xPos % Screen.towerSize == 0 && enemy.yPos % Screen.towerSize == 0 && enemy.routePosX == enemy.xPos / Screen.towerSize && enemy.routePosY == enemy.yPos / Screen.towerSize){
//            if (enemy.routePosX == basePosX && enemy.routePosY == basePosY){
//                enemy.attack = true;
//            }else {
//                if (route.route[enemy.routePosX][enemy.routePosY] == route.UP){
//                    enemy.routePosY--;
//                }else if (route.route[enemy.routePosX][enemy.routePosY] == route.DOWN){
//                    enemy.routePosY++;
//                }else if (route.route[enemy.routePosX][enemy.routePosY] == route.RIGHT){
//                    enemy.routePosX++;
//                }else if (route.route[enemy.routePosX][enemy.routePosY] == route.LEFT){
//                    enemy.routePosX--;
//                }else {
//                    cantFindRoute();
//                }
//            }
//        }else {
//            double xPos = enemy.xPos / Screen.towerSize;
//            double yPos = enemy.yPos / Screen.towerSize;
//
//            if (xPos > enemy.routePosX){enemy.xPos -= enemy.enemy.speed/24;}
//
//            if (xPos < enemy.routePosX){enemy.xPos += enemy.enemy.speed/24;}
//
//            if (yPos > enemy.routePosY){enemy.yPos -= enemy.enemy.speed/24;}
//
//            if (yPos < enemy.routePosY){enemy.yPos += enemy.enemy.speed/24;}
//
//        }
//    }

    public void cantFindRoute(){
        System.out.println("[EnemyIAMove] Can't find next move!");
    }
}
