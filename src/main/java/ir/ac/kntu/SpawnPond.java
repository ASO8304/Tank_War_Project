package ir.ac.kntu;

import javafx.scene.image.Image;

public class SpawnPond extends GameObject {

    public SpawnPond(int x, int y) {
        super(x, y);
        addObject();
        setImage(new Image("file:images\\SpawnPond.png"));
    }
    
}
