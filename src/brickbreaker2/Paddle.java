package brickbreaker2;

import java.awt.*;
import java.awt.event.*;

public class Paddle implements Runnable {

    int x, y, Xdirection;
    Rectangle paddle;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
        paddle = new Rectangle(x, y, 100, 20);
    }

    public void setxdirection(int Xdir) {
        Xdirection = Xdir;
    }

    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();
        if (kc == KeyEvent.VK_LEFT) {
            setxdirection(-1);
        }
        if (kc == KeyEvent.VK_RIGHT) {
            setxdirection(+1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int kc = e.getKeyCode();
        if (kc == KeyEvent.VK_LEFT) {
            setxdirection(0);
        }
        if (kc == KeyEvent.VK_RIGHT) {
            setxdirection(0);
        }
    }

    public void draw(Graphics Z) {
        Z.setColor(Color.LIGHT_GRAY);
        Z.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }

    public void move() {
        paddle.x += Xdirection;
        if (paddle.x <= 0) {
            paddle.x = 0;
        }
        if (paddle.x >= 298) {
            paddle.x = 298;
        }
    }

    @Override
    public void run() {
        try {

            while (true) {
                move();
                Thread.sleep(2);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
