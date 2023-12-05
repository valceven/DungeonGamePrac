import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel,KeyHandler keyH){
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = 100;
        worldY = 100;
        speed = 2;
        direction = "right";
    }

    public void getPlayerImage(){

        try{
            knightLeft = ImageIO.read(getClass().getResourceAsStream("/player/knight-left.png"));
            knightRight = ImageIO.read(getClass().getResourceAsStream("/player/knight-right.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.up){
            worldY -= speed;
        }
        else if(keyH.down){
            worldY += speed;
        }
        else if(keyH.left){
            worldX -= speed;
            direction = "left";
        }
        else if(keyH.right){
            worldY += speed;
            direction = "right";
        }

    }
    public void draw(Graphics2D graphics){

        BufferedImage image = switch (direction) {
            case "left" -> knightLeft;
            case "right" -> knightRight;
            default -> null;
        };

        graphics.drawImage(image, screenX, screenY, gamePanel.tileSize,gamePanel.tileSize, null);
    }
}
