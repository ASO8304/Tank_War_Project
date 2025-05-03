package ir.ac.kntu;

import javafx.scene.image.Image;

public class Flag extends GameObject{

    private int health;

    public Flag(int x, int y) {
        super(x, y);
        health = 1;
        setImage(new Image("file:images\\Flag\\Flag.png"));
        addObject();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
