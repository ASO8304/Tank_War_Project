🎮 TankWar: Battle Arena
A JavaFX-based tank combat game with dynamic enemies, destructible environments, and strategic gameplay.

Gameplay Demo Replace with a gameplay screenshot

🚀 Features
Player Mechanics

Control a tank with smooth movement (arrow keys) and shooting (spacebar).

Use nitro boosts (Shift key) and emergency brakes (Ctrl key).

Health system with heart-based UI.

Enemy AI

3 enemy types: Regular, Armored, and Random tanks with unique behaviors.

Spawn system with "bench tanks" that join the battle progressively.

Environment

Destructible walls (RegularWall) and indestructible barriers (IronWall).

Protect the Flag objective to survive.

Grid-based maps loaded from text files.

Progression

10 levels with increasing difficulty.

High-score system with player username registration.

📥 Installation
Requirements
Java 11+

JavaFX 11+ (Download SDK)

Resource Files: Ensure images/ and stages/ folders exist in src/main/resources/.

Steps
Clone the repository:

bash
git clone [your-repo-link]  
Compile and run (with JavaFX modules):

bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml ir/ac/kntu/*.java  
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml ir.ac.kntu.Main  
🕹️ How to Play
Key	Action
Arrow Keys	Move tank (Up/Down/Left/Right)
Space	Fire bullets
Shift	Activate nitro boost
Ctrl	Emergency stop
Enter	Restart after game over
Objective: Destroy all enemy tanks while protecting your flag (F on the map).

🏗️ Code Structure
Core Classes
Class	Purpose
Main.java	Launches the JavaFX application starting with the Menu scene.
Menu.java	Handles the main menu UI and navigation to registration/level selection.
Start.java	Manages the game loop, rendering, collisions, and enemy spawn logic.
MapReader.java	Reads level designs from .txt files (e.g., P = Player spawn).
Game Entities
Class	Description
PlayerTank.java	Player-controlled tank with health, score, and nitro mechanics.
RegularTank.java	Basic enemy with linear movement.
ArmoredTank.java	Heavy enemy with extra health and slower speed.
RandomTank.java	Unpredictable enemy that changes direction frequently.
Bullet.java	Handles projectile motion and collision detection.
Flag.java	Critical objective – losing it triggers game over.
Support Classes
Class	Role
Resources.java	Centralizes file paths for images (e.g., getBULLET_PATH()).
Player.java	Stores player data: username, high score, and progress.
DataBase.java	Saves/loads player profiles using serialization.
ObjectShape.java	Defines collision boundaries for tanks and walls.
🛠️ Customization
Create New Levels
Add a .txt file to stages/ with map codes:

P = Player spawn  
O = Enemy spawn (Regular)  
A = Enemy spawn (Armored)  
B = Breakable wall  
M = Metal wall  
F = Flag  
Example level design:

MMMMM  
B  PB  
F OOA  
Modify Difficulty
Adjust enemy spawn rates in Start.java > tankBuilder().

Tweak tank speeds or bullet damage in individual tank classes.
