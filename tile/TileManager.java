import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];


    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[20];
        mapTileNum = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try {

            for(int i = 0; i <= 18; i++){
                tile[i] = new Tile();
            }
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/void_tile.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor-1.png"));
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor-2.png"));
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_wall-1.png"));
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_wall-2.png"));
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_wall-1.png"));
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_wall-2.png"));
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_wall-1.png"));
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_wall-2.png"));
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottom_wall-1.png"));
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottom_wall-2.png"));
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_bottom_corner_wall-1.png"));
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/right_bottom_corner_wall-1.png"));
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/left_corner.png"));
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightt_corner.png"));
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/center_pillar.png"));
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_left_corner.png"));
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/top_right_corner.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/worldMap/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow){

                String line = br.readLine();

                while(col < gamePanel.maxScreenColumn){

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxScreenColumn){
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;


        while(col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            graphics.drawImage(tile[tileNum].image,x,y,gamePanel.tileSize, gamePanel.tileSize ,null);
            col++;
            x += gamePanel.tileSize;

            if (col == gamePanel.maxScreenColumn) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }

    }
}
