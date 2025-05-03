package ir.ac.kntu.Wall;

import javafx.scene.image.Image;

public class RegularWall extends Wall {

    public RegularWall(int x, int y) {
        super(x, y);
        setHealth(4);
        setImage();
        addObject();
    }

    public void setImage() {
        if (getHealth() == 4) {
            this.image = new Image("file:images\\RegularWall\\Hp4.png");
        } else if (getHealth() == 3) {
            this.image = new Image("file:images\\RegularWall\\Hp3.png");
        } else if (getHealth() == 2) {
            this.image = new Image("file:images\\RegularWall\\Hp2.png");
        } else if (getHealth() == 1) {
            this.image = new Image("file:images\\RegularWall\\Hp1.png");
        }
    }

    public void setHealth(int health) {
        this.health = health;
        setImage();
    }
}