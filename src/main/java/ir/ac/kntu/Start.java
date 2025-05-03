package ir.ac.kntu;


import ir.ac.kntu.Bullet.Bullet;
import ir.ac.kntu.Tank.*;
import ir.ac.kntu.Wall.IronWall;
import ir.ac.kntu.Wall.RegularWall;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


import static ir.ac.kntu.MapReader.loadMapInfo;
import static ir.ac.kntu.MapReader.loadScale;

public class Start extends Application {

    public static ArrayList<GameObject> objects = new ArrayList<>();

    public static ArrayList<Tank> benchTank = new ArrayList<>();

    public static PlayerTank playerTank;

    private int level;

    private Player player;

    public static int scaleWidth;

    public static int scaleHeight;

    public static ArrayList<Location> tankSpawnLocations = new ArrayList<>();

    public Image playerHeart = new Image("file:images\\Heart.png");

    public ImageView gameOverBackground = new ImageView(new Image("file:images\\TanksBench.jpg"));

    public Start(int level, Player player) {
        this.level = level;
        this.player = player;
    }
    
    public static void main(String[] args) {
        // Create a default player and start with level 1
        Player defaultPlayer = new Player("DefaultPlayer"); // You'll need to create this constructor
        launch(new Start(1, defaultPlayer)); // Start with level 1 and the default player
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        ArrayList<GameObject> objects = loadMapInfo(level);
        tankBuilder(level * 4);
        scaleWidth = loadScale(level)[0] * 50;
        scaleHeight = loadScale(level)[1] * 50;
        for (GameObject gameObject : objects) {
            if (gameObject instanceof PlayerTank) {
                playerTank = (PlayerTank) gameObject;
            }
        }

        Canvas canvas = new Canvas(scaleWidth, scaleHeight);
        canvas.setLayoutX(445);
        canvas.setLayoutY(10);

        Canvas tanksBench = new Canvas(100, (double) (45 * benchTank.size()) / 2);
        tanksBench.setLayoutX(300);
        tanksBench.setLayoutY(70);
        GraphicsContext tanksBenchGC = tanksBench.getGraphicsContext2D();

        Text remainingTanksText = new Text("RemainingTanks");
        remainingTanksText.setFont(Font.font("Arial", 18));
        remainingTanksText.setFill(Color.WHITE);
        remainingTanksText.setX(280);
        remainingTanksText.setY(50);

        Text healthText = new Text("Health");
        healthText.setFont(Font.font("Arial", 30));
        healthText.setFill(Color.WHITE);
        healthText.setX(130);
        healthText.setY(50);

        Text playerScoreText = new Text();
        playerScoreText.setText(String.valueOf(playerTank.getPlayerScore()));
        playerScoreText.setFont(Font.font("Arial", 30));
        playerScoreText.setFill(Color.WHITE);
        playerScoreText.setX(130);
        playerScoreText.setY(350);

        Text scoreText = new Text("Score");
        scoreText.setFont(Font.font("Arial", 30));
        scoreText.setFill(Color.WHITE);
        scoreText.setX(130);
        scoreText.setY(300);

        ImageView battleField = new ImageView(new Image("file:images\\BattleField.jpg"));
        battleField.setX(0);
        battleField.setY(0);
        battleField.setFitWidth(1550);
        battleField.setFitHeight(900);

        ImageView gridBorder = new ImageView(new Image("file:images\\GridBorder.jpg"));
        gridBorder.setX(440);
        gridBorder.setY(5);
        gridBorder.setFitHeight(scaleHeight + 10);
        gridBorder.setFitWidth(scaleWidth + 10);
        gridBorder.setBlendMode(BlendMode.DARKEN);

        ImageView canvasBackground = new ImageView(new Image("file:images\\GridBackground.jpg"));
        canvasBackground.setX(445);
        canvasBackground.setY(10);
        canvasBackground.setFitHeight(scaleHeight);
        canvasBackground.setFitWidth(scaleWidth);
        canvasBackground.setOpacity(0.6);

        ImageView benchBackground = new ImageView(new Image("file:images\\TanksBench.jpg"));
        benchBackground.setBlendMode(BlendMode.MULTIPLY);
        benchBackground.setX(300);
        benchBackground.setY(80);
        benchBackground.setFitHeight((double) (40 * benchTank.size()) / 2);
        benchBackground.setFitWidth(70);

        Canvas playerHealth = new Canvas(50, 600);
        playerHealth.setLayoutX(140);
        playerHealth.setLayoutY(40);
        GraphicsContext playerHealthGC = playerHealth.getGraphicsContext2D();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!checkGameOver() || checkGameWon()) {
                    if (playerTank.getPlayerScore() > player.getHighScore()) {
                        player.setHighScore(playerTank.getPlayerScore());
                        //new DataBase().addPlayerInfo(player);
                    }
                    showGameOver(root);
                    scene.setOnKeyPressed(keyEvent -> {
                        if (keyEvent.getCode() == KeyCode.ENTER) {
                            stage.close();
                            this.stop();
                        }
                    });
                }
                playerScoreText.setText(String.valueOf(playerTank.getPlayerScore()));
                drawRemainingTanks(tanksBenchGC);
                drawPlayerHealth(playerHealthGC);
                checkEntities();
                moveTanks();
                tanksFire();
                moveBullets();
                drawGrid(gc);
            }
        };
        animationTimer.start();

        root.getChildren().add(battleField);
        root.getChildren().add(gridBorder);
        root.getChildren().add(canvasBackground);
        root.getChildren().add(canvas);
        root.getChildren().add(benchBackground);
        root.getChildren().add(tanksBench);
        root.getChildren().add(playerHealth);
        root.getChildren().add(remainingTanksText);
        root.getChildren().add(healthText);
        root.getChildren().add(scoreText);
        root.getChildren().add(playerScoreText);

        scene.setOnKeyPressed(keyEvent -> {
            handleKeyPress(keyEvent.getCode());

        });

        stage.setOnCloseRequest(windowEvent -> Platform.exit());
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.getIcons().add(new Image("file:images\\GameIcon.png"));
        stage.setTitle("Tank Game");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void drawPlayerHealth(GraphicsContext playerHealthGC) {
        playerHealthGC.clearRect(0, 0, 50, 600);
        int y = 0;
        for (int i = 0; i < playerTank.getHealth(); i++) {
            y += 40;
            playerHealthGC.drawImage(playerHeart, 10, y, 40, 40);
        }
    }

    private void showGameOver(Group root) {
        gameOverBackground.setX(575);
        gameOverBackground.setY(120);
        gameOverBackground.setFitWidth(400);
        gameOverBackground.setFitHeight(100);
        gameOverBackground.setBlendMode(BlendMode.DIFFERENCE);
        root.getChildren().add(gameOverBackground);

        Text gameOver = new Text("Game Over");
        gameOver.setFill(Color.BLUE);
        gameOver.setFont(Font.font("Arial", 72));
        gameOver.setX(590);
        gameOver.setY(200);
        root.getChildren().add(gameOver);
    }

    public void handleKeyPress(KeyCode keycode) {

        if (keycode == KeyCode.RIGHT) {
            playerTank.setDirection(6);
            playerTank.setStop(false);
            System.out.println("Right");
        } else if (keycode == KeyCode.LEFT) {
            playerTank.setDirection(4);
            playerTank.setStop(false);
            System.out.println("LEFT");
        } else if (keycode == KeyCode.UP) {
            playerTank.setDirection(8);
            playerTank.setStop(false);
            System.out.println("UP");
        } else if (keycode == KeyCode.DOWN) {
            playerTank.setDirection(2);
            playerTank.setStop(false);
            System.out.println("DOWN");
        } else if (keycode == KeyCode.CONTROL) {
            if (playerTank.isStop()) {
                playerTank.setStop(false);
            } else {
                playerTank.setStop(true);
            }
            System.out.println("STOP");
        } else if (keycode == KeyCode.SPACE && objects.contains(playerTank)) {
            new Bullet(0, 0, playerTank);
            System.out.println("SHOOT");
        } else if (keycode == KeyCode.SHIFT) {
            if (playerTank.isNitro()) {
                playerTank.setNitro(false);
            } else {
                playerTank.setNitro(true);
            }
            System.out.println("NITRO");
        }
    }

    public void drawGrid(GraphicsContext gc) {
        gc.clearRect(0, 0, scaleWidth, scaleHeight);
        for (GameObject object : objects) {
            if (object instanceof SpawnPond) {
                gc.drawImage(object.getImage(), object.getX(), object.getY(), 50, 50);
            }
        }
        for (GameObject object : objects) {
            if (object instanceof SpawnPond || object instanceof Bullet) {
                continue;
            }
            gc.drawImage(object.getImage(), object.getX(), object.getY(), 50, 50);
        }
        for (GameObject object : objects) {
            if (object instanceof Bullet) {
                gc.drawImage(object.getImage(), object.getX(), object.getY(), 10, 10);
            }

        }

    }

    public void drawRemainingTanks(GraphicsContext benchGc) {
        benchGc.clearRect(0, 0, 100, 1000);
        int y = 0;
        for (int i = 0; i < benchTank.size(); i++) {
            if (i % 2 == 1) {
                Image tankImage = benchTank.get(i).getImage();
                benchGc.drawImage(tankImage, 0, y, 30, 30);
            } else {
                y += 30;
                Image tankImage = benchTank.get(i).getImage();
                benchGc.drawImage(tankImage, 40, y, 30, 30);
            }
        }
    }

    public void moveTanks() {
        for (GameObject tank : objects) {
            if (!(tank instanceof Tank)) {
                continue;
            }
            if ((tank instanceof RegularTank)) {
                ((RegularTank) tank).move();
            } else if ((tank instanceof ArmoredTank)) {
                ((ArmoredTank) tank).move();
            } else if ((tank instanceof RandomTank)) {
                ((RandomTank) tank).move();
            } else if ((tank instanceof PlayerTank)) {
                ((PlayerTank) tank).move();
            }
        }
    }

    public boolean checkGameOver() {
        for (GameObject object : Start.objects) {
            if (object instanceof PlayerTank) {
                if (((PlayerTank) object).getHealth() < 1) {
                    return false;
                }
            }
            if (object instanceof Flag) {
                if (((Flag) object).getHealth() < 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkGameWon() {
        for (GameObject object : Start.objects) {
            if (object instanceof PlayerTank) {
                continue;
            }
            if (object instanceof Tank) {
                return false;
            }
        }
        return true;
    }

    public void tanksFire() {
        try {
            for (GameObject object : Start.objects) {
                if (object instanceof PlayerTank) {
                    continue;
                }
                if (object instanceof Tank) {
                    ((Tank) object).fire();
                    ((Tank) object).updateDirection();
                }
            }
        } catch (Exception e) {

        }
    }

    public void moveBullets() {
        try {
            for (GameObject bullet : objects) {
                if (!(bullet instanceof Bullet)) {
                    continue;
                }
                ((Bullet) bullet).move();
            }
        } catch (RuntimeException re) {
            System.out.println("no Bullets");
        }
    }

    public void checkEntities() {
        try {
            int counterAliveTanks = 0;
            for (GameObject object : objects) {
                if (object instanceof PlayerTank) {
                    continue;
                }
                if (object instanceof Tank) {
                    counterAliveTanks++;
                }
            }
            if (counterAliveTanks < 4 && (benchTank.size() > 0)) {
                objects.add(benchTank.get(0));
                benchTank.remove(0);
            }
            for (GameObject object : objects) {
                if ((object instanceof IronWall)) {
                    continue;
                }
                if (object instanceof RegularWall) {
                    if (((RegularWall) object).getHealth() < 1) {
                        object.terminateObject();
                    }
                }
                if (object instanceof Tank) {
                    if (((Tank) object).getHealth() < 1) {
                        object.terminateObject();
                    }
                }
                if (object instanceof Flag) {
                    if (((Flag) object).getHealth() < 1) {
                        object.terminateObject();
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void tankBuilder(int tankNumbers) {
        for (int i = 0; i < tankNumbers; i++) {
            Random random = new Random();
            Location location = Start.tankSpawnLocations.get(random.nextInt(4));
            int posX = location.getX();
            int posY = location.getY();
            int tankType = random.nextInt(3);
            if (tankType == 0) {
                Start.benchTank.add(new RegularTank(posX, posY, false));
            } else if (tankType == 1) {
                Start.benchTank.add(new ArmoredTank(posX, posY, false));
            } else {
                Start.benchTank.add(new RandomTank(posX, posY, false));
            }
        }
    }
}
