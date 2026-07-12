# Java 2D 冒险游戏

这是上课时做的一个 Java 2D 小游戏练习，主要用来熟悉 Java 图形界面、键盘监听、图片加载、地图绘制和简单碰撞检测。

项目用 `JFrame` 和 `JPanel` 显示游戏窗口，角色可以通过 `W A S D` 移动。地图是用文本文件保存的瓦片编号，再根据编号加载草地、墙、水、树等图片。游戏里还放了钥匙、门、宝箱、靴子等物品，用来练习角色和物品之间的交互。

## 主要内容

- Java Swing/AWT 窗口和画布
- 游戏循环和画面刷新
- 键盘按键控制角色移动
- 角色行走图片切换
- 瓦片地图加载和绘制
- 地图障碍物碰撞检测
- 钥匙、门、宝箱、靴子等物品交互

## 目录

```text
src/
  Main/      程序入口、游戏面板、按键监听、碰撞检测
  Entity/    角色相关代码
  Tile/      地图瓦片和地图加载
  Objects/   游戏物品

Res/
  Maps/      地图文本
  Tiles/     地图图片
  player/    角色图片
  Objects/   物品图片
```

## 运行方式

推荐用 IntelliJ IDEA 打开项目：

1. 把 `src` 设置为 Sources Root。
2. 把 `Res` 设置为 Resources Root。
3. 运行 `src/Main/Main.java`。

也可以用命令行编译运行：

```bash
mkdir build
javac -encoding UTF-8 -d build $(find src -name "*.java")
cp -r Res/Maps Res/Tiles Res/player Res/Objects build/
java -cp build Main.Main
```

Windows PowerShell 可以参考：

```powershell
New-Item -ItemType Directory -Path build
$files = Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d build $files
Copy-Item Res\Maps build\Maps -Recurse
Copy-Item Res\Tiles build\Tiles -Recurse
Copy-Item Res\player build\player -Recurse
New-Item -ItemType Directory -Path build\Objects
Get-ChildItem Res\Objects | Copy-Item -Destination build\Objects -Recurse
java -cp build Main.Main
```

## 练习收获

这个项目主要是课程练习，不是完整商业游戏。通过它练习了 Java 面向对象、Swing 图形界面、键盘事件、资源加载、地图绘制和基础碰撞检测。
