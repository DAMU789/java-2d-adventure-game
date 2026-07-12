//Created by Hyh on 2025-12-23.
package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boot extends SuperObject{
    public Obj_Boot() {
        name = "Boot";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
