package ru.spbstu.icc.kspt.kuryakin.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JPanel {

    private JFrame frame;

    private JButton startButton;
    private JButton closeButton;

    public Menu(JFrame frame){
        this.frame = frame;

        startButton = new JButton("Старт");
        closeButton = new JButton("Выход");

        this.frame.add(startButton, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
                GridBagConstraints.CENTER, new Insets(2, 2, 2, 2), 40, 0));

        this.frame.add(closeButton, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.CENTER, new Insets(2, 2, 2, 2), 32, 0));

        startButton.addMouseListener(new ListenerStartGame());
        closeButton.addMouseListener(new ListenerCloseGame());
    }

    private class ListenerStartGame implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            startButton.setVisible(false);
            closeButton.setVisible(false);
            frame.remove(startButton);
            frame.remove(closeButton);

            Screen screen = new Screen(frame);
            frame.add(screen,new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }
    private class ListenerCloseGame implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }
}
