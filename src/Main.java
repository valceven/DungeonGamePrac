import javax.swing.*;

public class Main{
    public static void main(String[] args){

        JFrame app = new JFrame();
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setTitle("DUNGEON GAME");
        GamePanel gamePanel = new GamePanel();
        app.add(gamePanel);
        app.pack();
        app.setLocationRelativeTo(null);
        app.setVisible(true);
        gamePanel.startGameThread();
    }
}