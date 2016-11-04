package ru.spbstu.icc.kspt.kuryakin.GUI;

import ru.spbstu.icc.kspt.kuryakin.Level;
import ru.spbstu.icc.kspt.kuryakin.LevelFile;
import ru.spbstu.icc.kspt.kuryakin.Tower;
import ru.spbstu.icc.kspt.kuryakin.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class Screen extends JPanel implements Runnable {

    Thread thread = new Thread(this);

    Frame frame;
    Level level;
    LevelFile levelFile;

    User user;

    private int fps = 0;

    public int scene;

    public int hand = 0;
    public int handXPos = 0;
    public int handYPos = 0;

    public boolean running = false;

    public int[][] map = new int[22][14];
    public Tower[][] towerMap = new Tower[22][14];
    public Image[] terrain = new Image[100];

    public  int towerWidth = 1;
    public  int towerHeight = 1;

    public Screen(Frame frame){
        this.frame = frame;

        this.frame.addKeyListener(new KeyHandler(this));
        this.frame.addMouseListener(new MouseHandler(this));
        this.frame.addMouseMotionListener(new MouseHandler(this));

        towerWidth = 35;
        towerHeight = 35;

        thread.start();
    }

    public void paintComponent(Graphics graphics) {
        graphics.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

        if(scene == 0){
            graphics.setColor(Color.BLUE);
            graphics.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
        } else if(scene ==1){
            //Background
            graphics.setColor(Color.GREEN);
            graphics.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

            //Grid
            graphics.setColor(Color.GRAY);
            for (int x = 0; x < 22; x++){
                for (int y = 0; y < 14; y++){
                    graphics.drawImage(terrain[map[x][y]], (x * towerWidth), (y * towerHeight), towerWidth, towerHeight, null);
                    graphics.drawRect((x*towerWidth), (y*towerHeight), towerWidth, towerHeight);
                }
            }

            //Headth + Money thingy
            graphics.drawRect(0, this.frame.getHeight() - 100, 105, towerHeight);
            graphics.drawString("Health: " + user.player.health, 0, this.frame.getHeight() -  100 + 15);

            graphics.drawRect(0, this.frame.getHeight() -  100 + towerHeight, 105, towerHeight);
            graphics.drawString("Money: " + user.player.money, 0, this.frame.getHeight() -  100 + 15 + towerHeight);

            //Tower List
            for (int x = 0; x < 20; x++) {
                for (int y = 0; y < 2; y++) {
                    if (Tower.towerList[x * 2 + y] != null){
                        graphics.drawImage(Tower.towerList[x * 2 + y].texture, 105 + (x * towerWidth), (this.frame.getHeight() - 100) + (y * towerHeight), towerWidth, towerHeight, null);

                        if (Tower.towerList[x * 2 + y].cost > this.user.player.money){
                            graphics.setColor(new Color(255, 0, 0, 100));
                            graphics.fillRect(105 + (x * towerWidth), (this.frame.getHeight() - 100) + (y * towerHeight), towerWidth, towerHeight);
                        }else {

                        }
                    }
                    graphics.setColor(Color.GRAY);
                    graphics.drawRect(105 + (x * towerWidth), (this.frame.getHeight() - 100) + (y * towerHeight), towerWidth, towerHeight);
                }
            }

            //Towers on Grind

            for (int x = 0; x < 22; x++) {
                for (int y = 0; y < 14; y++) {
                    if(towerMap[x][y] != null) {
                        graphics.setColor(Color.GRAY);
                        graphics.drawOval((x * towerWidth) - (towerMap[x][y].range * 2 * towerWidth + towerWidth) / 2 + towerWidth / 2,
                                (y * towerHeight)- (towerMap[x][y].range * 2 * towerHeight + towerHeight) / 2 + towerHeight / 2,towerMap[x][y].range * 2 * towerWidth + towerWidth,
                                towerMap[x][y].range * 2 * towerHeight + towerHeight);
                        graphics.setColor(new Color(64, 64, 64, 64));
                        graphics.fillOval((x * towerWidth) - (towerMap[x][y].range * 2 * towerWidth + towerWidth) / 2 + towerWidth / 2,
                                (y * towerHeight)- (towerMap[x][y].range * 2 * towerHeight + towerHeight) / 2 + towerHeight / 2,towerMap[x][y].range * 2 * towerWidth + towerWidth,
                                towerMap[x][y].range * 2 * towerHeight + towerHeight);
                        graphics.drawImage(Tower.towerList[towerMap[x][y].id].texture, (x * towerWidth), (y * towerHeight), towerWidth , towerHeight, null);
                    }
                }
            }

            // HAND
            if(hand != 0 && Tower.towerList[hand - 1] != null) {
                graphics.drawImage(Tower.towerList[hand - 1].texture, this.handXPos, this.handYPos, towerWidth, towerHeight, null);
            }

        } else {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
        }

        graphics.drawString(fps + "", 10, 10);
    }

    public void loadGame(){

        user = new User(this);
        levelFile = new LevelFile();

        for (int y = 0; y < 10; y++){
            for (int x = 0; x < 10; x++){
                terrain[x + (y * 10)] = new ImageIcon("res/terrain.png").getImage();
                terrain[x + (y * 10)] = createImage(new FilteredImageSource(terrain[x + (y * 10)].getSource(), new CropImageFilter(x * towerWidth, y * towerHeight, towerWidth, towerHeight)));
            }
        }

        running = true;
    }

    public void startGame(User user, String level){
        user.createPlayer();

        this.level = levelFile.getLevel(level);
        this.level.findSpawnPoint();
        this.map = this.level.map;

        this.scene = 1;
    }


    public void run() {
        System.out.println("Success");

        long lastFrame = System.currentTimeMillis();
        int frames = 0;

        loadGame();

        while (running){
            repaint();

            frames++;

            if(System.currentTimeMillis() - 1000 >= lastFrame){
                fps = frames;
                frames = 0;
                lastFrame = System.currentTimeMillis();
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public void placeTower(int x, int y){
        int xPos = (x - 2)/ towerWidth;
        int yPos = (y - 26)/ towerHeight;

        if (xPos >= 0 && xPos < 22 && yPos >= 0 && yPos < 14){

            if(towerMap[xPos][yPos] == null && map[xPos][yPos] == 0){
                user.player.money -= Tower.towerList[hand - 1].cost;

                towerMap[xPos][yPos] = Tower.towerList[hand - 1];
            }
        }
    }

    public class MouseHeld{

        boolean mouseDown = false;

        public void mouseMoved(MouseEvent e) {
            handXPos = e.getX() - (35 / 2);
            handYPos = e.getY() - (35 / 2) - 35;
        }

        public void updateMouse(MouseEvent e){
                if(scene == 1){

                    if(mouseDown && hand == 0){
                        if((e.getX() >= 109) && (e.getX() <= 142) &&
                                (e.getY() >= 527) && (e.getY() <= 560)){

                            if (user.player.money >= Tower.towerList[0].cost) {
                                System.out.println("[Shop] You bought a tower for " + Tower.towerList[0].cost + "!");
                                hand = 1;
                            }
                    }
                }
            }
        }

        public void mouseDown(MouseEvent e) {
            mouseDown = true;
            if (hand != 0){
                placeTower(e.getX(), e.getY());

                hand = 0;
            }
            updateMouse(e);
        }
    }

    public class KeyTyped{

        public void keyESC(){
            running = false;
        }


        public void keySPASE() {
            startGame(user, "firstLevel");
        }
    }
}
