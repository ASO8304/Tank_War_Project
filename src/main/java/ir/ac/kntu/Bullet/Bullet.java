package ir.ac.kntu.Bullet;

import ir.ac.kntu.*;
import ir.ac.kntu.Tank.PlayerTank;
import ir.ac.kntu.Tank.Tank;
import ir.ac.kntu.Wall.RegularWall;
import ir.ac.kntu.Wall.Wall;
import javafx.scene.image.Image;

public class Bullet extends GameObject {

    private final int BULLET_WIDTH = 10;

    private final int BULLET_HEIGHT = 10;

    private int direction;

    private Tank shooter;

    private int speed;

    public Bullet(int x, int y, Tank shooter) {
        super(x, y);
        if (!Start.objects.contains(shooter)) {
            terminateObject();
        }
        this.direction = shooter.getDirection();
        this.shooter = shooter;
        System.out.println("direction is: " + direction);
        setX(calculatePos(shooter)[0]);
        setY(calculatePos(shooter)[1]);
        setImage();
        setSpeed(4);
        addObject();
    }

    public int getBULLET_WIDTH() {
        return BULLET_WIDTH;
    }

    public int getBULLET_HEIGHT() {
        return BULLET_HEIGHT;
    }

    public Tank getShooter() {
        return shooter;
    }

    public void setShooter(Tank shooter) {
        this.shooter = shooter;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setImage() {
        if (direction == 8) {
            this.image = new Image("file:images\\Bullet\\Up.png");
        } else if (direction == 2) {
            this.image = new Image("file:images\\Bullet\\Down.png");
        } else if (direction == 4) {
            this.image = new Image("file:images\\Bullet\\Left.png");
        } else if (direction == 6) {
            this.image = new Image("file:images\\Bullet\\Right.png");
        }

    }

    public Image getImage() {
        return this.image;
    }

    public int[] calculatePos(Tank shooter) {
        int x = 0;
        int y = 0;
        if (shooter.getDirection() == 8) {
            x = shooter.getX() + 20;
            y = shooter.getY() - 10;
        } else if (shooter.getDirection() == 2) {
            x = shooter.getX() + 20;
            y = shooter.getY() + 50;
        } else if (shooter.getDirection() == 6) {
            x = shooter.getX() + 50;
            y = shooter.getY() + 19;
        } else if (shooter.getDirection() == 4) {
            x = shooter.getX() - 20;
            y = shooter.getY() + 20;
        }
        return new int[]{x, y};
    }

    public void move() {
        if (getShooter() instanceof PlayerTank) {
            playerBulletMove();
        } else {
            aiBulletMove();
        }
    }

    public void playerBulletMove() {
        if (direction == 8 && playerBulletCollision(direction)) {
            int preY = this.getY();
            this.setY(preY - this.getSpeed());
        } else if (direction == 2 && playerBulletCollision(direction)) {
            int preY = this.getY();
            this.setY(preY + this.getSpeed());
        } else if (direction == 6 && playerBulletCollision(direction)) {
            int preX = this.getX();
            this.setX(preX + this.getSpeed());
        } else if (direction == 4 && playerBulletCollision(direction)) {
            int preX = this.getX();
            this.setX(preX - this.getSpeed());
        }

        handleOutOfGrid();
    }

    private void handleOutOfGrid() {
        if (this.getX() < 0) {
            this.setX(0);
            terminateObject();
        } else if (this.getX() + this.getBULLET_WIDTH() > Start.scaleWidth) {
            this.setX(Start.scaleWidth - this.getBULLET_WIDTH());
            terminateObject();
        }

        if (this.getY() < 0) {
            this.setY(0);
            terminateObject();
        } else if (this.getY() + this.getBULLET_HEIGHT() > Start.scaleHeight) {
            this.setY(Start.scaleHeight - this.getBULLET_HEIGHT());
            terminateObject();
        }
    }

    public boolean playerBulletCollision(int direction) {
        int bulletX = this.getX();
        int bulletY = this.getY();
        if (direction == 8) {
            bulletY -= this.getSpeed();
        } else if (direction == 2) {
            bulletY += this.getSpeed();
        } else if (direction == 6) {
            bulletX += this.getSpeed();
        } else if (direction == 4) {
            bulletX -= this.getSpeed();
        }
        for (GameObject object : Start.objects) {
            if ((object instanceof Bullet) || (object instanceof PlayerTank) || (object instanceof SpawnPond)) {
                continue;
            }
            if (bulletX > (object.getX() - 8) && bulletX < (object.getX() + 48)
                    && (bulletY > object.getY() - 8) && (bulletY < object.getY() + 48)) {
                Start.objects.remove(this);
                playerShotCollision(object);
                return false;
            }
        }
        return true;
    }

    public void playerShotCollision(Object object) {
        if (object instanceof Tank) {
            int preHealth = ((Tank) object).getHealth();
            ((Tank) object).setHealth(preHealth - Start.playerTank.getBulletPower());
            if (((Tank) object).getHealth() < 1) {
                Start.playerTank.updatePlayerScore(((Tank) object).getPoint());
            }
        }
        if (object instanceof RegularWall) {
            int preHealth = ((Wall) object).getHealth();
            ((RegularWall) object).setHealth(preHealth - Start.playerTank.getBulletPower());
        }
    }

    public void aiBulletMove() {
        if (direction == 8 && aiBulletCollision(direction)) {
            int preY = this.getY();
            this.setY(preY - this.getSpeed());
        } else if (direction == 2 && aiBulletCollision(direction)) {
            int preY = this.getY();
            this.setY(preY + this.getSpeed());
        } else if (direction == 6 && aiBulletCollision(direction)) {
            int preX = this.getX();
            this.setX(preX + this.getSpeed());
        } else if (direction == 4 && aiBulletCollision(direction)) {
            int preX = this.getX();
            this.setX(preX - this.getSpeed());
        }
        handleOutOfGrid();
    }

    public boolean aiBulletCollision(int direction) {
        int bulletX = this.getX();
        int bulletY = this.getY();
        if (direction == 8) {
            bulletY -= this.getSpeed();
        } else if (direction == 2) {
            bulletY += this.getSpeed();
        } else if (direction == 6) {
            bulletX += this.getSpeed();
        } else if (direction == 4) {
            bulletX -= this.getSpeed();
        }
        for (GameObject object : Start.objects) {
            if (object instanceof PlayerTank || object instanceof Wall || object instanceof Flag) {
                if (bulletX > (object.getX() - 8) && bulletX < (object.getX() + 48)
                        && (bulletY > object.getY() - 8) && (bulletY < object.getY() + 48)) {
                    Start.objects.remove(this);
                    aiShotCollision(object);
                    return false;
                }
            }
        }
        return true;
    }

    public void aiShotCollision(Object object) {
        int shotPower = this.getShooter().getBulletPower();
        if (object instanceof PlayerTank) {
            int preHealth = ((PlayerTank) object).getHealth();
            ((PlayerTank) object).setHealth(preHealth - shotPower);
        }
        if (object instanceof RegularWall) {
            int preHealth = ((Wall) object).getHealth();
            ((RegularWall) object).setHealth(preHealth - shotPower);
        }
        if (object instanceof Flag) {
            int preHealth = ((Flag) object).getHealth();
            ((Flag) object).setHealth(preHealth - shotPower);
        }
    }
}
