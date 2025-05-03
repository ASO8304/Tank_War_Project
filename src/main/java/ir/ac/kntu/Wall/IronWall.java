package ir.ac.kntu.Wall;

import javafx.scene.image.Image;

public class IronWall extends Wall {
    public IronWall(int x, int y) {
        super(x, y);
        setImage();
        addObject();
    }

    public void setImage() {
        this.image = new Image("file:images\\IronWall.png");
    }
}
