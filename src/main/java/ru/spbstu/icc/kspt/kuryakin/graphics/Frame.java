package ru.spbstu.icc.kspt.kuryakin.graphics;

import javax.swing.*;
import java.awt.*;

public class Frame {

    public static void main(String[] args) {
        new Frame();
    }

    public Frame(){

        JFrame frame = new JFrame("Tower Defense");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Menu menu = new Menu(frame);
        frame.add(menu);
    }
}
