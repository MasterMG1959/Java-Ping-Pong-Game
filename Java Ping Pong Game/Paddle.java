import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

    int ib;
    int yVelocity;
    int SPEED = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int ib) {

        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.ib = ib;
 
    }

    public void  keyPressed(KeyEvent e) {

        switch(ib) {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-SPEED);
                    move();
                }   
            
                if(e.getKeyCode() == KeyEvent.VK_S) {
                setYDirection(SPEED);
                move();
                } 
                break; 
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                setYDirection(-SPEED);
                move();
                }   
        
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                setYDirection(SPEED);
                move();
                }
                break;
            }
        }

    public void keyRelased(KeyEvent e) {

        switch(ib) {
            case 1:
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }   
            
                if(e.getKeyCode() == KeyEvent.VK_S) {
                setYDirection(0);
                move();
                } 
                break; 
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                setYDirection(0);
                move();
                }   
        
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                setYDirection(0);
                move();
                }
                break;
            }
        }

    public void setYDirection(int yDirection) {

        yVelocity = yDirection;

    }

    public void move() {

        y = y + yVelocity;
    
    }

    public void draw(Graphics gg) {

        if(ib==1)
            gg.setColor(Color.BLUE);
        
        else
            gg.setColor(Color.YELLOW);

        gg.fillRect(x, y, width, height);

    }
}
