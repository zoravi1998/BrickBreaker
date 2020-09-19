package brickbreaker2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class main extends JFrame implements Runnable {

    Graphics dbg;
    Image dbI;
    int gwidth = 400, gheight = 300, xdirection, x, y;
    Dimension screensize = new Dimension(gwidth, gheight);
    //Ball object
    static Ball b = new Ball(200, 150);

    Thread Ball, Paddle;

    Rectangle startButton = new Rectangle(150, 100, 100, 30);
    Rectangle quitButton = new Rectangle(150, 165, 100, 30);
    Rectangle NextLevel = new Rectangle(150, 165, 100, 30);
    Rectangle restart = new Rectangle(150, 165, 100, 30);

    boolean startedgame = false;
    boolean Level2 = false;
    boolean nexthover = false;
    boolean startHover = false;
    boolean quitHover = false;
    boolean reshover = false;

    public main() {
        this.setTitle("Brick Breaker 2");
        this.setSize(screensize);
        this.setVisible(true);
        this.setResizable(false);
        this.setBackground(Color.ORANGE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new AL());
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());
    }

    public void startgame() {
        b = new Ball(200,150);
        Ball = new Thread(b);
        Paddle = new Thread(b.p);
        startedgame = true;
        Ball.start();
        Paddle.start();
    }

    public void Level2() {
        
        b = new Ball(230, 260);
        Ball = new Thread(b);
        Paddle = new Thread(b.p);
        startedgame = true;
        Ball.start();
        Paddle.start();
    }

    @Override
    public void paint(Graphics Z) {
        dbI = createImage(getWidth(), getHeight());
        dbg = dbI.getGraphics();
        draw(dbg);
        Z.drawImage(dbI, 0, 0, this);
    }

    public void draw(Graphics Z) {
        if (startedgame != true) {
            Z.setFont(new Font("Ariel", Font.BOLD, 20));
            Z.setColor(Color.PINK);
            Z.drawString("Brick Breaker 2", 140, 90);

            if (startHover != true) {
                Z.setColor(Color.cyan);
            } else {
                Z.setColor(Color.red);
            }
            Z.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);

            if (quitHover != true) {
                Z.setColor(Color.cyan);
            } else {
                Z.setColor(Color.red);
            }
            Z.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
            Z.setFont(new Font("Ariel", Font.BOLD, 12));
            Z.setColor(Color.BLACK);
            Z.drawString("Start Game", startButton.x + 20, startButton.y + 17);
            Z.setColor(Color.black);
            Z.drawString("Quit Game", quitButton.x + 20, quitButton.y + 17);
        } else {
            //Game 

            b.draw(Z);
            b.p.draw(Z);
            b.b1.draw(Z);
            Z.drawString("" + b.b1.count, 30, 50);
            if (b.b1.ballFalldown == true) {
                Ball.stop();
                Paddle.stop();
                b.b1.count = 0;
                Z.setFont(new Font("Ariel", Font.BOLD, 20));
                Z.setColor(Color.red);
                Z.drawString("" + b.status, 110, 150);
                if (reshover != true) {
                    Z.setColor(Color.cyan);
                } else {
                    Z.setColor(Color.red);
                }
                Z.fillRect(restart.x, restart.y, restart.width, restart.height);
                Z.setColor(Color.BLACK);
                Z.drawString("Restart", NextLevel.x + 17, NextLevel.y + 20);

            }

            if (Level2 != true && startedgame == true && b.b1.brickOver == true) {

                Ball.stop();
                Paddle.stop();
                Z.fillRect(NextLevel.x, NextLevel.y, NextLevel.width, NextLevel.height);
                Z.setColor(Color.BLACK);
                Z.drawString("NEXT LEVEL", NextLevel.x + 20, NextLevel.y + 17);
                if (nexthover != true) {
                    Z.setColor(Color.cyan);
                } else {
                    Z.setColor(Color.red);
                }

            }
            if (Level2 == true && startedgame == true && b.b1.brickOver == true) {
                b.draw(Z);
                b.p.draw(Z);
                b.b2.draw(Z);
                Z.drawString("" + b.b1.count, 30, 50);
                if (b.b2.ballFalldown == true) {
                    Z.setFont(new Font("Ariel", Font.BOLD, 20));
                    Z.setColor(Color.red);
                    Z.drawString("" + b.status, 110, 150);
                    startedgame = false;
                    Ball.stop();
                    Paddle.stop();
                }
            }

        }
        repaint();
    }

    public class AL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            b.p.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            b.p.keyReleased(e);
        }
    }

    public class MouseHandler extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            int mx = e.getX();
            int my = e.getY();
            if (mx > startButton.x && mx < startButton.x + startButton.width
                    && my > startButton.y && my < startButton.y + startButton.height) {
                startHover = true;
            } else {
                startHover = false;
            }

            if (mx > quitButton.x && mx < quitButton.x + quitButton.width
                    && my > quitButton.y && my < quitButton.y + quitButton.height) {
                quitHover = true;
            } else {
                quitHover = false;
            }
            if (mx > NextLevel.x && mx < NextLevel.x + NextLevel.width
                    && my > NextLevel.y && my < NextLevel.y + NextLevel.height) {
                nexthover = true;
            } else {
                nexthover = false;
            }
            if (mx > restart.x && mx < restart.x + restart.width
                    && my > restart.y && my < restart.y + restart.height) {
                reshover = true;
            } else {
                reshover = false;
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int mx = e.getX();
            int my = e.getY();
            if (mx > startButton.x && mx < startButton.x + startButton.width
                    && my > startButton.y && my < startButton.y + startButton.height) {
                startgame();
            }
            if (mx > NextLevel.x && mx < NextLevel.x + NextLevel.width
                    && my > NextLevel.y && my < NextLevel.y + NextLevel.height) {

                Level2();
            }
            if (mx > restart.x && mx < restart.x + restart.width
                    && my > restart.y && my < restart.y + restart.height) {
                startgame();
            }

        }
    }

    @Override
    public void run() {
        try {

            while (true) {
                Thread.sleep(3);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        main obj = new main();

    }

}