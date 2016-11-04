package ru.spbstu.icc.kspt.kuryakin.GUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public static void main(String[] args) {
        new Frame();
    }

    public Frame(){

        this.setSize(800, 600);
        this.setTitle("Tower Defense");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setExtendedState(MAXIMIZED_BOTH);
//        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        Screen screen = new Screen(this);
        this.add(screen);
    }
}
