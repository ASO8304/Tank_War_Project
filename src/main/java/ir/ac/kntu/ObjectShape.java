package ir.ac.kntu;

public class ObjectShape {

    private Location vertex1;

    private Location vertex2;

    private Location vertex3;

    private Location vertex4;

    public ObjectShape(Location vertex1, Location vertex2, Location vertex3, Location vertex4) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        this.vertex4 = vertex4;
    }

    public ObjectShape(Location vertex1, int objectWidth, int objectHeight){
        this.vertex1 = vertex1;
        this.vertex2 = new Location(vertex1.getX() + objectWidth, vertex1.getY());
        this.vertex3 = new Location(vertex1.getX() + objectWidth, vertex1.getY() + objectHeight);
        this.vertex4 = new Location(vertex1.getX(), vertex1.getY() + objectHeight);
    }

    public Location[] getVertexes(){
        return new Location[]{vertex1, vertex2, vertex3 ,vertex4 };
    }
}
