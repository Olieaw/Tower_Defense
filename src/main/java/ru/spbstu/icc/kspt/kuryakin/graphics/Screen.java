package ru.spbstu.icc.kspt.kuryakin.graphics;

import ru.spbstu.icc.kspt.kuryakin.Core.*;
import ru.spbstu.icc.kspt.kuryakin.Core.Enemy.Enemy;
import ru.spbstu.icc.kspt.kuryakin.Core.Enemy.EnemyMove;
import ru.spbstu.icc.kspt.kuryakin.Core.EnemyAI.EnemyAI;
import ru.spbstu.icc.kspt.kuryakin.Core.EnemyAI.EnemyRoute;
import ru.spbstu.icc.kspt.kuryakin.Core.Tower.Tower;
import ru.spbstu.icc.kspt.kuryakin.Core.Tower.TowerMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class Screen extends JPanel implements Runnable {

    private Thread thread = new Thread(this);
    private JFrame frame;

    public User user;
    public Map map;
    public TowerMap towerMap;
    public EnemyAI enemyAI;
    public EnemyMove[] enemyMap;

    public Wave wave;

    private Image[] terrain = new Image[25];
    private Image[] tower = new Image[10];
    private Image[] enemy = new Image[10];

    private int hand = 0;
    private int handXPos = 0;
    private int handYPos = 0;

    public static int towerSize = 1;

    private boolean mouseDown = false;
    private boolean spawn = false;
    private int xTower = 0;
    private int yTower = 0;

    public Screen(JFrame frame) {
        this.frame = frame;

        this.frame.addMouseListener(new MouseHandler(this));
        this.frame.addMouseMotionListener(new MouseHandler(this));

        towerSize = 40;

        thread.start();
    }

    public void paintComponent(Graphics graphics){
        graphics.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

        graphics.setColor(Color.GREEN);
        graphics.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

        paintTerrain(graphics);
        paintTower(graphics);
        paintTowerList(graphics);
        paintEnemy(graphics);

        if(hand != 0 && Tower.towerList[hand - 1] != null) {
            graphics.drawImage(tower[hand - 1], this.handXPos, this.handYPos, towerSize, towerSize, null);
        }

        paintTimeBar(graphics);
        paintHealthAndMoney(graphics);
        paintMenuTower(graphics);
        paintGameOver(graphics);
    }

    private void paintMenuTower(Graphics graphics){

        if(TowerMap.towerMap != null && TowerMap.towerMap[xTower][yTower] != null) {
            graphics.setColor(Color.GRAY);
            graphics.drawOval((xTower * towerSize) - (TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize) / 2 + towerSize / 2,
                    (yTower * towerSize) - (TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize) / 2 + towerSize / 2,
                    TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize,
                    TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize);

            graphics.setColor(new Color(64, 64, 64, 64));
            graphics.fillOval((xTower * towerSize) - (TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize) / 2 + towerSize / 2,
                    (yTower * towerSize) - (TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize) / 2 + towerSize / 2,
                    TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize,
                    TowerMap.towerMap[xTower][yTower].range * 2 * towerSize + towerSize);

            graphics.drawImage(tower[TowerMap.towerMap[xTower][yTower].id], 14*towerSize + 15, 3*towerSize, towerSize, towerSize, null);
            graphics.drawRect(14*towerSize + 15, 3*towerSize, towerSize, towerSize);

            graphics.setColor(Color.BLACK);
            if (TowerMap.towerMap[xTower][yTower].id == 0) {
                graphics.drawString("Электрическая башня", 14 * towerSize + 70, 3 * towerSize + 25);
            }
            if (TowerMap.towerMap[xTower][yTower].id == 1) {
                graphics.drawString("Огненная башня", 14 * towerSize + 70, 3 * towerSize + 25);
            }

            graphics.drawString("Радиус: " + TowerMap.towerMap[xTower][yTower].range * towerSize, 14 * towerSize + 15, 4 * towerSize + 25);
            graphics.drawString("Урон: " + TowerMap.towerMap[xTower][yTower].damage, 16 * towerSize + 15, 4 * towerSize + 25);

            graphics.drawRect(14 * towerSize + 20, 5 * towerSize + 10, 120, 20);
            graphics.drawString("Разграбить башню", 14 * towerSize + 25, 5 * towerSize + 25);
        }
    }

    private void paintTimeBar(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.drawRect(14*towerSize + 20, 2*towerSize + 10, 190, 20);
        if (spawn == false) {
            graphics.drawString("Старт", 14 * towerSize + 100, 2 * towerSize + 25);
        }else {
            graphics.drawString("Стоп", 14 * towerSize + 100, 2 * towerSize + 25);
        }
    }

    private void paintHealthAndMoney(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.drawRect(14*towerSize + 15, 12*towerSize, 200, 40);
        graphics.drawString("Health: " + user.health, 14*towerSize + 25, 12*towerSize + 15);
        graphics.drawRect(14*towerSize + 15, 13*towerSize, 200, 40);
        graphics.drawString("Money: " + user.money, 14*towerSize + 25, 13*towerSize + 15);
    }

    private void paintTerrain(Graphics graphics){

        for (int x = 0; x < 14; x++){
            for (int y = 0; y < 14; y++){
                if (map != null)
                    graphics.drawImage(terrain[map.map[x][y]], (x * towerSize), (y * towerSize), towerSize, towerSize, null);

                graphics.setColor(Color.GRAY);
                graphics.drawRect((x*towerSize), (y*towerSize), towerSize, towerSize);
            }
        }
    }

    private void paintTower(Graphics graphics){

        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (TowerMap.towerMap != null && TowerMap.towerMap[x][y] != null) {

                    graphics.setColor(Color.GRAY);
                    graphics.drawImage(tower[TowerMap.towerMap[x][y].id], (x * towerSize) + 3, (y * towerSize) + 3, towerSize - 6, towerSize - 6, null);
                }
            }
        }
    }

    private void paintTowerList(Graphics graphics){

        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 2; y++){
                if(Tower.towerList[x + (y * 2)] != null) {
                    graphics.drawImage(tower[Tower.towerList[x + (y * 2)].id], (14 * towerSize + 15) + (x * towerSize) + 3, (y * towerSize) + 3, towerSize - 6, towerSize - 6, null);

                    if (Tower.towerList[x + (y * 2)].cost > this.user.money){
                        graphics.setColor(new Color(255, 0, 0, 100));
                        graphics.fillRect((14*towerSize + 15) + (x * towerSize), (y * towerSize), towerSize, towerSize);
                    }
                }
                graphics.setColor(Color.BLACK);
                graphics.drawRect((14*towerSize + 15) + (x * towerSize), (y * towerSize), towerSize, towerSize);
            }
        }
    }

    private void paintEnemy(Graphics graphics){
        if (enemyMap != null) {
            for (int i = 0; i < enemyMap.length; i++) {
                if (enemyMap[i] != null) {
                    graphics.drawImage(enemy[enemyMap[i].enemy.id], enemyMap[i].routePosX * towerSize, enemyMap[i].routePosY * towerSize, towerSize, towerSize, null);
                    graphics.setColor(Color.RED);
                    graphics.drawString(enemyMap[i].health + "", enemyMap[i].routePosX * towerSize + 12, enemyMap[i].routePosY * towerSize);
                }
            }
        }
    }

    private void paintGameOver(Graphics graphics){
        if (user != null && user.gameOver() == true) {
            graphics.setColor(new Color(255, 0, 0, 100));
            graphics.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
        }
    }

    public void loadGame(){

        this.user = new User();
        this.map = new Map("firstLevel");
        this.towerMap = new TowerMap();
        this.enemyMap = new EnemyMove[200];
        this.wave = new Wave();

        this.enemyAI = new EnemyAI(this.map);

        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 5; x++){
                terrain[x + (y * 5)] = new ImageIcon("res/terrain.png").getImage();
                terrain[x + (y * 5)] = createImage(new FilteredImageSource(terrain[x + (y * 5)].getSource(), new CropImageFilter(x * 50, y * 50, 50, 50)));
            }
        }

        for (int y = 0; y < 2; y++){
            for (int x = 0; x < 5; x++){
                tower[x + (y * 2)] = new ImageIcon("res/tower.png").getImage();
                tower[x + (y * 2)] = createImage(new FilteredImageSource(tower[x + (y * 2)].getSource(), new CropImageFilter(x * 50, y * 50, 50, 50)));
            }
        }

        for (int y = 0; y < 2; y++){
            for (int x = 0; x < 5; x++){
                enemy[x + (y * 2)] = new ImageIcon("res/enemy.png").getImage();
                enemy[x + (y * 2)] = createImage(new FilteredImageSource(enemy[x + (y * 2)].getSource(), new CropImageFilter(x * 50, y * 50, 50, 50)));
            }
        }
    }

    public void run() {

        System.out.println("Screen");

        loadGame();

        while (true){
            repaint();

            if (user.gameOver() == false) {
                update();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enemyUpdate(){
        for (int i = 0; i < enemyMap.length; i++){
            if (enemyMap[i] != null){
                    EnemyAI.moveAI.move(enemyMap[i]);

                enemyMap[i].update();
            }
        }
    }

    public void update(){
        enemyUpdate();
        fire();
        diedEnemy();
        this.wave.spawnEnemies(this.enemyMap, spawn);
    }

    public class MouseHeld{

        boolean mouseDown = false;

        public void mouseMoved(MouseEvent e) {
            handXPos = e.getX() - (towerSize / 2);
            handYPos = e.getY() - (towerSize / 2) - 35;
        }

        public void updateMouse(MouseEvent e){

            if(mouseDown && hand == 0){
                if((e.getX() >= 581) && (e.getX() <= 619) &&
                        (e.getY() >= 29) && (e.getY() <= 67)){
                    if (user.money >= Tower.towerList[0].cost) {
                        hand = 1;
                    }
                }

                if((e.getX() >= 621) && (e.getX() <= 659) &&
                        (e.getY() >= 29) && (e.getY() <= 67)){
                    if (user.money >= Tower.towerList[1].cost) {
                        hand = 2;
                    }
                }

            }
        }

        public void mouseDown(MouseEvent e) {
            mouseDown = true;

            int xPos = (e.getX() - 5)/ towerSize;
            int yPos = (e.getY() - 28)/ towerSize;

            if (hand != 0){

                towerMap.AddTower(xPos, yPos, hand - 1);
                hand = 0;
            }
            updateMouse(e);
        }
    }

    public void clicked(MouseEvent e){
        int xPos = (e.getX() - 5)/ Screen.towerSize;
        int yPos = (e.getY() - 28)/ Screen.towerSize;

        mouseDown = true;

        if (xTower != 0 && yTower != 0) {
            xTower = 0;
            yTower = 0;
        }else {
            if ((e.getX() >= 5) && (e.getX() <= 565) && (e.getY() >= 28) && (e.getY() <= 588) && TowerMap.towerMap[xPos][yPos] != null) {
                xTower = xPos;
                yTower = yPos;
            }
        }
    }

    public void delTower(MouseEvent e){
        if ((e.getX() >= 585) && (e.getX() <= 705) && (e.getY() >= 238) && (e.getY() <= 258)) {
            towerMap.DeleteTower(xTower, yTower);
        }
    }

    public void spawnButton(MouseEvent e){
        if((e.getX() >= 585) && (e.getX() <= 775) && (e.getY() >= 118) && (e.getY() <= 138)){
            spawn = !spawn;
        }
    }

    public void fire(){
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (towerMap.towerMap != null && towerMap.towerMap[y][x] != null &&
                        enemyMap != null){
                    for (int i = 0; i < enemyMap.length; i++){
                        if(enemyMap[i] != null &&
                                enemyMap[i].routePosX >= (y - towerMap.towerMap[y][x].range) &&
                                enemyMap[i].routePosX <= (y + towerMap.towerMap[y][x].range) &&
                                enemyMap[i].routePosY >= (x - towerMap.towerMap[y][x].range) &&
                                enemyMap[i].routePosY <= (x + towerMap.towerMap[y][x].range)){
                            enemyMap[i].health -= towerMap.towerMap[y][x].damage;

                        }
                    }
                }
            }
        }
    }

    public void diedEnemy(){
        for (int i = 0; i < enemyMap.length; i++) {
            if (enemyMap[i] != null && enemyMap[i].health <= 0) {
                User.money += 10;
                enemyMap[i] = null;
            }
        }
    }
}
