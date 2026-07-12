// by Hyh -> 2025-12-15
package Main;
import Entity.Player;
import Tile.TileManager;
import Objects.SuperObject;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize= originalTileSize*scale;
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth=tileSize*maxScreenCol;
    public final int screenHeight=tileSize*maxScreenRow;

    // world setting
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int  worldHeight = tileSize*maxWorldRow;

    //FPS
    int FPS=60;

    KeyHandler KeyH = new KeyHandler();

    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this,KeyH);
    public SuperObject obj[] = new SuperObject[10];

    int PlayerX =100;
    int PlayerY =100;
    int PlayerSpeed =4;
    TileManager tileM =new  TileManager(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);

    }
    public void setupGame(){
        aSetter.setObject();
    }

    public void startGameThread(){
        gameThread =new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {


        double drawInterval= 1000000000/FPS;
        double nextDrawTime =System.nanoTime()+drawInterval;
        while (gameThread != null){
            update();



            repaint();

            try {
                double remainingTime = nextDrawTime-System.nanoTime();
                remainingTime= remainingTime/1000000;
                if (remainingTime<0){remainingTime=0;}
                Thread.sleep((long) remainingTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void update(){
        player.update();
        cChecker.checkTile(player);
        cChecker.checkObject(player, obj);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D)g;
        tileM.draw(g2);

        for(int i = 0;i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }
        player.draw(g2);
        g2.dispose();
    }
}