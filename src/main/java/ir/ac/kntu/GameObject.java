package ir.ac.kntu;

import javafx.scene.image.Image;

import java.awt.*;

public class GameObject {

    protected Image image;

    protected int x;

    protected int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addObject() {
        Start.objects.add(this);
    }

    public void terminateObject() {
        if (Start.objects.contains(this)) {
            Start.objects.remove(this);
        }
    }
}
