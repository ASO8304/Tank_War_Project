package ir.ac.kntu.Wall;

import ir.ac.kntu.GameObject;

public class Wall extends GameObject {

    private final int WALL_WIDTH = 50;

    private final int WALL_HEIGHT = 50;

    protected int health;

    public Wall(int x, int y) {
        super(x, y);
    }

    public int getHealth() {
        return health;
    }

    public boolean checkCollided(int objectX, int objectY){
        return (objectX >= this.x) && (objectX <= (this.x + WALL_WIDTH)) &&
                (objectY >= this.y) && (objectY <= (this.y + WALL_HEIGHT));
    }
}
