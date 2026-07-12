//Created by Hyh on 2025-12-23.
package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Obj_Chest extends SuperObject{
    public boolean isOpened = false;
    public BufferedImage openImage;
    public Obj_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));
            openImage = ImageIO.read(getClass().getResourceAsStream("/Objects/chest_opened.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        solidArea = new Rectangle(0,0,48,48);
    }
}
