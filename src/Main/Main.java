// by Hyh -> 2025-12-15
package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Boy");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.pack();

        gamePanel.setupGame();
        gamePanel.startGameThread();






    }
}