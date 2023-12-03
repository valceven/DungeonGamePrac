import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyH;

    public Player(GamePanel gamePanel,KeyHandler keyH){
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "right";
    }

    public void getPlayerImage(){

        try{
            knightLeft = ImageIO.read(getClass().getResourceAsStream("/player/knight-left.jpg"));
            knightRight = ImageIO.read(getClass().getResourceAsStream("/player/knight-right.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.up){
            y -= speed;
        }
        else if(keyH.down){
            y += speed;
        }
        else if(keyH.left){
            x -= speed;
            direction = "left";
        }
        else if(keyH.right){
            x += speed;
            direction = "right";
        }

    }
    public void draw(Graphics2D graphics){

        BufferedImage image = switch (direction) {
            case "left" -> knightLeft;
            case "right" -> knightRight;
            default -> null;
        };

        graphics.drawImage(image, x, y, gamePanel.tileSize,gamePanel.tileSize, null);
    }
}
