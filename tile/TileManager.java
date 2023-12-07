import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];


    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[20];
        mapTileNum = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("/worldMap/worldMap1.txt");
    }

    public void getTileImage(){

        try {

            for(int i = 0; i <= 17; i++){
                tile[i] = new Tile();
            }
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/void_tile.png"));
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor-1.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor-2.png"));
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_wall-1.png"));
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_wall-2.png"));
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_wall-1.png"));
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_wall-2.png"));
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_wall-1.png"));
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_wall-2.png"));
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottom_wall-1.png"));
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottom_wall-2.png"));
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_bottom_corner_wall-1.png"));
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_bottom_corner_wall-1.png"));
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_corner.png"));
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightt_corner.png"));
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/center_pillar.png"));
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_left_corner.png"));
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_right_corner.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String s){
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow){

                String line = br.readLine();

                while(col < gamePanel.maxWorldColumn){

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldColumn){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(IOException e){
                e.printStackTrace();
            }
    }
    public void draw(Graphics2D graphics){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            graphics.drawImage(tile[tileNum].image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize ,null);
            worldCol++;


            if (worldCol == gamePanel.maxScreenColumn) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
