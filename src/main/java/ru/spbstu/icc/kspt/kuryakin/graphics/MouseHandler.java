package ru.spbstu.icc.kspt.kuryakin.graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public Screen screen;
    public Screen.MouseHeld mouseHeld;

    public MouseHandler(Screen screen) {
        this.screen = screen;
        this.mouseHeld = this.screen.new MouseHeld();
    }

    public void mouseClicked(MouseEvent e) {
//        System.out.println(e.getX() + " " + e.getY());
    }

    public void mousePressed(MouseEvent e) {

        this.screen.spawnButton(e);
        this.screen.delTower(e);
        this.mouseHeld.mouseDown(e);
        this.screen.clicked(e);
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

        this.mouseHeld.mouseMoved(e);
    }
}
