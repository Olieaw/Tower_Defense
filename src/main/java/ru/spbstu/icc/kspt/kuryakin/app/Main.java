package ru.spbstu.icc.kspt.kuryakin.app;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Main {
    private static String[][] m = new String[5][3];
    private static Scanner scn;

    public static void main(String[] args){
        openFile();
        readFile();
        out();
    }

    private static void openFile() {
            try {
            scn = new Scanner(new File("level\\firstLevel.txt"));
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "файл не найден");
        }
    }

    private static void readFile() {
        while(scn.hasNext()){
            for (int i = 0; i < m.length; i++){
                for (int j = 0; j < m[i].length; j++){
                    m[i][j] = scn.next();
                }
            }
        }
    }

    private static void out(){
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j]+"  ");
            }
            System.out.println();
        }
    }


}
