package ru.spbstu.icc.kspt.kuryakin.graphics;

import ru.spbstu.icc.kspt.kuryakin.Core.Map;
import ru.spbstu.icc.kspt.kuryakin.Core.Tower.TowerMap;
import ru.spbstu.icc.kspt.kuryakin.Core.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import static com.sun.deploy.uitoolkit.impl.awt.Applet2ImageFactory.createImage;

public class LoadGame {

    User user;
    Map map;
    TowerMap towerMap;
    public Image[] terrain;

    public LoadGame(){
        this.user = new User();
        this.map = new Map("firstLevel");
        this.towerMap = new TowerMap();
        this.terrain = new Image[10];

        for (int y = 0; y < 10; y++){
            for (int x = 0; x < 10; x++){
                terrain[x + (y * 10)] = new ImageIcon("res/terrain.png").getImage();
//                terrain[x + (y * 10)] = createImage(new FilteredImageSource(terrain[x + (y * 10)].getSource(), new CropImageFilter(x * towerSize, y * towerSize, towerSize, towerSize)));
            }
        }
    }
}
