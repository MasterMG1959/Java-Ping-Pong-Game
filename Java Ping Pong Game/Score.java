import java.awt.*;

public class Score extends Rectangle {

    static int FRAME_WIDTH;
    static int FRAME_HEIGHT;
    int player1;
    int player2;

    Score(int FRAME_WIDTH, int FRAME_HEIGHT) {

        Score.FRAME_WIDTH = FRAME_WIDTH;
        Score.FRAME_HEIGHT = FRAME_HEIGHT;

    }

    public void draw(Graphics gg) {

        gg.setColor(Color.WHITE);
        gg.setFont(new Font("Consolas", Font.PLAIN, 60));

        // Line going down the middle of the game
        gg.drawLine(FRAME_WIDTH/2, 0, FRAME_WIDTH/2, FRAME_HEIGHT);

        // Score header
        gg.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (FRAME_WIDTH/2)-85, 50);
        gg.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (FRAME_WIDTH/2)+20, 50);

    }
    
}
