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
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage(){

        try{
            for(int i = 1; i < 8; i++){
                knightLeft[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_left" + i + ".png"));
                knightRight[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_right" + i + ".png"));
                knightUp[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_up" + i + ".png"));
                knightDown[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_down" + i + ".png"));
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.up){
            worldY -= speed;
            direction = "up";
        }
        else if(keyH.down){
            worldY += speed;
            direction = "down";
        }
        else if(keyH.left){
            worldX -= speed;
            direction = "left";
        }
        else if(keyH.right){
            worldX += speed;
            direction = "right";
        }else{

        }

        spriteCounter += 1;

        if(spriteCounter > 8){
            spriteNum++;
            if(spriteNum >= 7){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D graphics) {

        BufferedImage image = switch (direction) {
            case "left" -> knightLeft[spriteNum];
            case "right" -> knightRight[spriteNum];
            case "up" -> knightUp[spriteNum];
            case "down" -> knightDown[spriteNum];
                default -> null;
        };
        graphics.drawImage(image, screenX, screenY, gamePanel.tileSize,gamePanel.tileSize, null);
    }
}
