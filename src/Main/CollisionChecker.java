//Created by Hyh on 2025/12/22.
package Main;

import Entity.Player;
import Entity.Entity;
import Objects.*;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        int tileNum1,tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                } //这一步是为了检测玩家是否在边缘
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize; // 注意这里应该是加号
                if (entityRightCol < gp.tileM.mapTileNum.length) { // 添加边界检查
                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                        entity.collisionOn = true;
                    }
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                if (entityLeftCol >= 0) { // 添加边界检查
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                        entity.collisionOn = true;
                    }
                }
                break;

        }
    }

    public void checkObject(Entity entity, SuperObject[] obj) {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null && obj[i].solidArea != null) { // 避免空指针
                // 1. 计算实体与物品的世界坐标碰撞区域
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                obj[i].solidArea.x = obj[i].worldX;
                obj[i].solidArea.y = obj[i].worldY;
                // 2. 预判移动后的碰撞位置
                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }
                // 3. 碰撞核心逻辑（按物品类型分支，修复顺序）
                if (entity.solidArea.intersects(obj[i].solidArea)) {
                    if (obj[i] instanceof Obj_Key) { // 钥匙：收集
                        entity.collisionOn = false;
                        ((Player)entity).collectItem("Key");
                        obj[i] = null;
                    } else if (obj[i] instanceof Obj_Boot) { // 靴子：收集
                        entity.collisionOn = false;
                        ((Player)entity).collectItem("Boot");
                        obj[i] = null;
                    } else if (obj[i] instanceof Obj_Door) { // 门：阻挡/消失
                        Player player = (Player) entity;
                        if (player.inventory.get("Key") > 0) { // 有钥匙：门消失
                            player.inventory.put("Key", player.inventory.get("Key") - 1);
                            obj[i] = null;
                            entity.collisionOn = false;
                            System.out.println("普通门消失！剩余钥匙：" + player.inventory.get("Key"));
                        } else { // 无钥匙：阻挡
                            entity.collisionOn = true;
                            System.out.println("需要普通钥匙才能开门！");
                        }
                    } else if (obj[i] instanceof Obj_Chest) { // 箱子：打开/奖励
                        Obj_Chest chest = (Obj_Chest) obj[i];
                        if (!chest.isOpened) { // 未打开：阻挡+打开动画
                            entity.collisionOn = true;
                            chest.isOpened = true;
                            chest.image = chest.openImage; // 切换为打开图片
                            chest.collision = false;
                            System.out.println("打开宝箱！获得奖励！");
                            // 生成普通钥匙奖励
                            for (int j = 0; j < obj.length; j++) {
                                if (obj[j] == null) {
                                    obj[j] = new Obj_Key();
                                    obj[j].worldX = obj[i].worldX;
                                    obj[j].worldY = obj[i].worldY - gp.tileSize; // 奖励在箱子上方
                                    break;
                                }
                            }
                        } else { // 已打开：不阻挡
                            entity.collisionOn = false;
                        }
                    }
                } else {
                    // 未碰撞到物品：不阻挡
                    entity.collisionOn = false;
                }
                // 4. 重置实体碰撞区域（恢复初始偏移）
                entity.solidArea.x = 8;
                entity.solidArea.y = 16;
            }
        }
    }
}
