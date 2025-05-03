package ir.ac.kntu.Tank;

import ir.ac.kntu.GameObject;
import ir.ac.kntu.SpawnPond;
import ir.ac.kntu.Start;
import javafx.scene.image.Image;

public class ArmoredTank extends Tank {

    public ArmoredTank(int x, int y, boolean addObject) {
        super(x, y);
        setHealth(2);
        setPoint(200);
        setBulletPower(2);
        setDirection(8);
        setVelocity(2);
        if (addObject) {
            addObject();
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        setImage();
    }

    public void setImage() {
        if (this.direction == 8) {
            this.image = new Image("file:images\\ArmoredTank\\Up.png");
        } else if (this.direction == 2) {
            this.image = new Image("file:images\\ArmoredTank\\Down.png");
        } else if (this.direction == 6) {
            this.image = new Image("file:images\\ArmoredTank\\Right.png");
        } else if (this.direction == 4) {
            this.image = new Image("file:images\\ArmoredTank\\Left.png");
        }
    }

    public void move() {
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
            if (object instanceof Tank && !(object instanceof PlayerTank)) {
                continue;
            }
            if (object instanceof SpawnPond) {
                continue;
            }
            if (playerX > (object.getX() - 47) && playerX < (object.getX() + 47)
                    && (playerY > object.getY() - 47) && (playerY < object.getY() + 47)) {
                return false;
            }

        }
        return true;
    }

}
