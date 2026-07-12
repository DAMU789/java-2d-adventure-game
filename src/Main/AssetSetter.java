//Created by Hyh on 2025-12-23.
package Main;

import Objects.Obj_Boot;
import Objects.Obj_Chest;
import Objects.Obj_Door;
import Objects.Obj_Key;

//import Objects.ObjectKey;
public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject(){
        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 20 * gp.tileSize;

        gp.obj[1] = new Obj_Door();
        gp.obj[1].worldX = 32 * gp.tileSize;
        gp.obj[1].worldY = 28 * gp.tileSize;

        gp.obj[2] = new Obj_Chest();
        gp.obj[2].worldX = 32 * gp.tileSize;
        gp.obj[2].worldY = 30 * gp.tileSize;

        gp.obj[3] = new Obj_Boot();
        gp.obj[3].worldX = 56 * gp.tileSize;
        gp.obj[3].worldY = 28 * gp.tileSize;

        gp.obj[4] = new Obj_Door();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 12 * gp.tileSize;

        gp.obj[5] = new Obj_Chest();
        gp.obj[5].worldX = 24 * gp.tileSize;
        gp.obj[5].worldY = 39 * gp.tileSize;


    }
}
