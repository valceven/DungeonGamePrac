import java.awt.image.BufferedImage;

public class Entity {

    public int worldX,worldY;
    public int speed;
    public BufferedImage[] knightLeft = new BufferedImage[8];
    public BufferedImage[] knightRight = new BufferedImage[8];
    public BufferedImage[] knightUp = new BufferedImage[8];
    public BufferedImage[] knightDown = new BufferedImage[8];
    public String direction;
    public double spriteCounter = 0;
    public int spriteNum = 1;

}
