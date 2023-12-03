import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; // 16x16 tile
    final int scaleValue = 3;
    final int tileSize = originalTileSize * scaleValue; // 48x48 tile
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; // 768 px
    final int screenHeight = tileSize * maxScreenRow; //576 px
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int defaultX = 100;
    int defaultY = 100;
    int  defaultSpeed = 4;
    int FPS = 60;

    Player player = new Player(this,keyH);

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){

        double drawInterval = 1000000000/FPS;
        double drawTimeInterval = System.nanoTime() + drawInterval;

        while(gameThread != null){

            update();
            repaint();

            try {
                double timeRem = drawTimeInterval - System.nanoTime();
                timeRem /= 1000000;

                if(timeRem < 0){
                    timeRem = 0;
                }

                Thread.sleep((long)timeRem);
                drawTimeInterval += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){

        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D)g;

        player.draw(graphics);

        graphics.dispose();
    }
}
