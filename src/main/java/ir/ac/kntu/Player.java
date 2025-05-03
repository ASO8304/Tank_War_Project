package ir.ac.kntu;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {

    private int highScore;

    private String username;

    public Player(String username) {
        this.username = username;
    }

    public Player(int highScore, String username) {
        this.highScore = highScore;
        this.username = username;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return highScore == player.highScore && Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(highScore, username);
    }
}
