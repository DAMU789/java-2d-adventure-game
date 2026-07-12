//Created by Hyh on 2025-12-23.
package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Obj_Door extends SuperObject{
    public Obj_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        solidArea = new Rectangle(0,0,48,48);
    }
}
