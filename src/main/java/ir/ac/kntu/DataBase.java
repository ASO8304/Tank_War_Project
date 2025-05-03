package ir.ac.kntu;

import java.io.*;
import java.util.ArrayList;

public class DataBase {

    public ArrayList<Player> loadPlayerInfo() {
        ArrayList<Player> players = new ArrayList<Player>();
        File file = new File("file:PlayerInfo.info");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream input = new ObjectInputStream(fileInputStream)) {
            while (true) {
                try {
                    //Read info for each player
                    Player player = (Player) input.readObject();
                    players.add(player);
                } catch (EOFException e) {
                    //Reaching end of file
                    break;
                } catch (Exception e) {
                    System.out.println("Problem with some of the records in the player data file");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data for players has been saved.");
        }
        return players;
    }

    public void savePlayerInfos(ArrayList<Player> players) {
        File file = new File("file:PlayerInfo.info");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream output = new ObjectOutputStream(fileOutputStream)) {
            for (Player player : players) {
                try {
                    output.writeObject(player);
                } catch (IOException e) {
                    System.out.println("(Player::savePlayerInfos): " +
                            "An error occurred while trying to save info");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("(Player::savePlayerInfos): " +
                    "An error occurred while trying to save info");
            System.out.println(e.getMessage());
        }
    }

    public void addPlayerInfo(Player player) {
        ArrayList<Player> players = loadPlayerInfo();
        players.add(player);
        savePlayerInfos(players);
    }


}
