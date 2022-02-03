import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = (int) (FRAME_WIDTH * (0.5555));
    static final Dimension SCREEN_DIMENSION = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread Thread;
    Image icon;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball GameBall;
    Score score;

    GamePanel() {

        newPaddles();
        newBall();
        score = new Score(FRAME_WIDTH, FRAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_DIMENSION);

        Thread = new Thread(this);
        Thread.start();

    } 

    public void newBall() {

        random = new Random();
        GameBall = new Ball((FRAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(FRAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);

    }

    public void newPaddles() {

        paddle1 = new Paddle(0,(FRAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,1);
        paddle2 = new Paddle(FRAME_WIDTH - PADDLE_WIDTH,(FRAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,2);
    
    }

    public void paint(Graphics g) {

        icon = createImage(getWidth(), getHeight());
        graphics = icon.getGraphics();
        draw(graphics);
        g.drawImage(icon, 0, 0, this);

    }

    public void draw(Graphics gg) {

        paddle1.draw(gg);
        paddle2.draw(gg);
        GameBall.draw(gg);
        score.draw(gg);

    }

    public void move() {

        paddle1.move();
        paddle2.move();
        GameBall.move();

    }

    public void checkCollision() {

        // Bounces ball off the top & bottom window edges
        if(GameBall.y <=0) {
            GameBall.setYDirection(-GameBall.yVelocity);
        }
        if(GameBall.y >= FRAME_HEIGHT-BALL_DIAMETER) {
            GameBall.setYDirection(-GameBall.yVelocity);
        }

        if(GameBall.intersects(paddle2)) {
            GameBall.xVelocity = Math.abs(GameBall.xVelocity);
            GameBall.xVelocity++; // Optional for more difficulty
        if(GameBall.yVelocity>0)
            GameBall.yVelocity++; // Optional for more difficulty
        else
            GameBall.yVelocity--;
            GameBall.setXDirection(-GameBall.xVelocity);
            GameBall.setYDirection(GameBall.yVelocity);
        }

        if(GameBall.intersects(paddle1)) {
            GameBall.xVelocity = Math.abs(GameBall.xVelocity);
            GameBall.xVelocity++; // Optional for more difficulty
        if(GameBall.yVelocity>0)
            GameBall.yVelocity++; // Optional for more difficulty
        else
            GameBall.yVelocity--;
            GameBall.setXDirection(GameBall.xVelocity);
            GameBall.setYDirection(GameBall.yVelocity);
        }

        // Stops Paddle At Window Edges
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (FRAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = FRAME_HEIGHT - PADDLE_HEIGHT;

        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (FRAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = FRAME_HEIGHT - PADDLE_HEIGHT;

        // Give a player 1 point and creates new paddles & ball
        if(GameBall.x <=0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: "+score.player2);
        }

        if(GameBall.x >= FRAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: "+score.player1);
        }

    }

    public void run() {

        // Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {

            long now = System.nanoTime();
            delta +=(now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1) {

                move();
                checkCollision();
                repaint();
                delta--;

            }          
        }
    }

    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }

        public void keyRelease(KeyEvent e) {

            paddle1.keyRelased(e);
            paddle2.keyRelased(e);

        }

    }
}
