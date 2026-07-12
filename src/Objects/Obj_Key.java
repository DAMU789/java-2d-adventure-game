//Created by Hyh on 2025-12-23.
package Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Obj_Key extends SuperObject{
    public Obj_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
        solidArea = new Rectangle(0,0,48,48);
    }
}
