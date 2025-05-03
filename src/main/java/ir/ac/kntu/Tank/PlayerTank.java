package ir.ac.kntu.Tank;

import ir.ac.kntu.Bullet.Bullet;
import ir.ac.kntu.GameObject;
import ir.ac.kntu.SpawnPond;
import ir.ac.kntu.Start;
import javafx.scene.image.Image;


public class PlayerTank extends Tank {

    private boolean nitro = false;

    private boolean stop = false;

    private int playerScore = 0 ;

    public PlayerTank(int x, int y) {
        super(x, y);
        this.setHealth(3);
        this.setPoint(0);
        this.setBulletPower(1);
        setDirection(6);
        addObject();
    }

    public boolean isNitro() {
        return nitro;
    }

    public void setNitro(boolean nitro) {
        this.nitro = nitro;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        setImage();
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setImage() {
        if (this.direction == 8) {
            this.image = new Image("file:images\\PlayerTank\\Up.png");
        } else if (this.direction == 2) {
            this.image = new Image("file:images\\PlayerTank\\Down.png");
        } else if (this.direction == 6) {
            this.image = new Image("file:images\\PlayerTank\\Right.png");
        } else if (this.direction == 4) {
            this.image = new Image("file:images\\PlayerTank\\Left.png");
        }
    }

    public void move() {
        if (stop) {
            setVelocity(0);
        } else {
            if (nitro) {
                setVelocity(5);
            } else {
                setVelocity(2);
            }
        }
        if (direction == 8 && collision(direction)) {
            int preY = this.getY();
            this.setY(preY - this.getVelocity());
        } else if (direction == 2 && collision(direction)) {
            int preY = this.getY();
            this.setY(preY + this.getVelocity());
        } else if (direction == 6 && collision(direction)) {
            int preX = this.getX();
            this.setX(preX + this.getVelocity());
        } else if (direction == 4 && collision(direction)) {
            int preX = this.getX();
            this.setX(preX - this.getVelocity());
        }
        handleOutOfGrid();
    }

    private void handleOutOfGrid() {
        if (this.getX() < 0) {
            this.setX(0);
        } else if (this.getX() + this.getTANK_WIDTH() > Start.scaleWidth) {
            this.setX(Start.scaleWidth - this.getTANK_WIDTH());
        }

        if (this.getY() < 0) {
            this.setY(0);
        } else if (this.getY() + this.getTANK_HEIGHT() > Start.scaleHeight) {
            this.setY(Start.scaleHeight - this.getTANK_HEIGHT());
        }
    }

    public boolean collision(int direction) {
        int playerX = this.getX();
        int playerY = this.getY();
        if (direction == 8) {
            playerY -= this.getVelocity();
        } else if (direction == 2) {
            playerY += this.getVelocity();
        } else if (direction == 6) {
            playerX += this.getVelocity();
        } else if (direction == 4) {
            playerX -= this.getVelocity();
        }
        for (GameObject object : Start.objects) {
            if (object instanceof PlayerTank || object instanceof SpawnPond) {
                continue;
            }
            if (object instanceof Bullet) {
                if (playerX > (object.getX() - 8) && playerX < (object.getX() + 8)
                        && (playerY > object.getY() - 8) && (playerY < object.getY() + 8)) {
                    return false;
                }
                continue;
            }
            if (playerX > (object.getX() - 47) && playerX < (object.getX() + 47)
                    && (playerY > object.getY() - 47) && (playerY < object.getY() + 47)) {
                return false;
            }

        }
        return true;
    }

   public void updatePlayerScore(int score){
        playerScore += score;
   }
}
