package ir.ac.kntu.Tank;

import ir.ac.kntu.Bullet.Bullet;
import ir.ac.kntu.GameObject;
import ir.ac.kntu.Location;
import ir.ac.kntu.ObjectShape;
import ir.ac.kntu.Start;
import javafx.scene.image.Image;

import java.util.Objects;
import java.util.Random;

public class Tank extends GameObject {

    private final int TANK_WIDTH = 50;

    private final int TANK_HEIGHT = 50;

    protected int velocity;

    protected int direction;

    private int health;

    private int point;

    private int bulletPower;

    private long finishSleepFire = System.currentTimeMillis();

    private long finishSleepDirection = System.currentTimeMillis();

    public Tank(int x, int y) {
        super(x, y);
    }

    public int getTANK_WIDTH() {
        return TANK_WIDTH;
    }

    public int getTANK_HEIGHT() {
        return TANK_HEIGHT;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getBulletPower() {
        return bulletPower;
    }

    public void setBulletPower(int bulletPower) {
        this.bulletPower = bulletPower;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

//    public boolean checkCollided(int objectX, int objectY) {
//        return (objectX >= this.x) && (objectX <= (this.x + TANK_WIDTH)) &&
//                (objectY >= this.y) && (objectY <= (this.y + TANK_HEIGHT));
//    }
//
//    public boolean checkCollided(Location vertex) {
//        int objectX = vertex.getX();
//        int objectY = vertex.getY();
//        return (objectX >= this.x) && (objectX <= (this.x + TANK_WIDTH)) &&
//                (objectY >= this.y) && (objectY <= (this.y + TANK_HEIGHT));
//    }

    public void fire() {
        if (System.currentTimeMillis() > finishSleepFire) {
            new Bullet(0, 0, this);
            finishSleepFire = System.currentTimeMillis() + new Random().nextInt(1000) + 1000;
        }
    }

    public void updateDirection() {
        if (System.currentTimeMillis() > finishSleepDirection) {
            Random random = new Random();
            int direction = 2 * (random.nextInt(4) + 1);
            setDirection(direction);
            finishSleepDirection = System.currentTimeMillis() + random.nextInt(1000) + 1000;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tank tank = (Tank) o;
        return TANK_WIDTH == tank.TANK_WIDTH && TANK_HEIGHT == tank.TANK_HEIGHT && velocity == tank.velocity && direction == tank.direction && health == tank.health && point == tank.point && bulletPower == tank.bulletPower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TANK_WIDTH, TANK_HEIGHT, velocity, direction, health, point, bulletPower);
    }
}
