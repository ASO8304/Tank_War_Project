package ir.ac.kntu;

import ir.ac.kntu.Tank.*;
import ir.ac.kntu.Wall.IronWall;
import ir.ac.kntu.Wall.RegularWall;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MapReader {

    public static ArrayList<GameObject> loadMapInfo(int stageNumber) {
        ArrayList<GameObject> map = new ArrayList<>();
        File folder = new File("stages");
        File[] files = folder.listFiles();
        Arrays.sort(files, Comparator.comparing(File::getName));
        File file = files[stageNumber - 1];
        try (Scanner scanner = new Scanner(file)) {
            int line = 0;
            int y = 0;
            while (scanner.hasNext()) {
                y = line * 50;
                line++;
                System.out.println(line);
                try {
                    //Read info for each line
                    ArrayList<GameObject> gameObjects = readMapInfo(scanner, y);
                    map.addAll(gameObjects);
                } catch (Exception e) {
                    System.out.println("Problem with some of the records in the student data file");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No previous data for maps has been saved.");
        }
        return map;
    }

    private static ArrayList<GameObject> readMapInfo(Scanner scanner, int y) {
        ArrayList<GameObject> lineObjects = new ArrayList<>();
        String[] characters = scanner.nextLine().split("");
        int x = 0;
        for (int i = 0; i < characters.length; i++) {
            x = (i * 50);
            lineObjects.add(convertCharacter(characters[i], x, y));
        }
        return lineObjects;
    }

    public static GameObject convertCharacter(String character, int x, int y) {
        switch (character) {
            case "P":
                return new PlayerTank(x, y);
            case "O":
                Start.tankSpawnLocations.add(new Location(x, y));
                new SpawnPond(x, y);
                return new RegularTank(x, y, true);
            case "A":
                Start.tankSpawnLocations.add(new Location(x, y));
                new SpawnPond(x, y);
                return new ArmoredTank(x, y, true);
            case "c":
                Start.tankSpawnLocations.add(new Location(x, y));
                new SpawnPond(x, y);
                return new RandomTank(x, y, 1, true);
            case "C":
                Start.tankSpawnLocations.add(new Location(x, y));
                new SpawnPond(x, y);
                return new RandomTank(x, y, 0, true);
            case "B":
                return new RegularWall(x, y);
            case "M":
                return new IronWall(x, y);
            case "F":
                return new Flag(x, y);
            default:
                return null;
        }
    }

    public static int[] loadScale(int stageNumber) {
        int[] scale = new int[2];
        File folder = new File("stages");
        File[] files = folder.listFiles();
        File file = files[stageNumber - 1];
        try (Scanner scanner = new Scanner(file)) {
            int counterScaleHeight = 0;
            int maxCharacters = 0;
            while (scanner.hasNext()) {
                counterScaleHeight++;
                try {
                    //Read info for each line
                    int lineLength = countCharacters(scanner);
                    if (lineLength > maxCharacters) {
                        maxCharacters = lineLength;
                    }
                } catch (Exception e) {
                    System.out.println("Problem with some of the records in the student data file");
                    System.out.println(e.getMessage());
                }
            }
            scale[0] = maxCharacters;
            scale[1] = counterScaleHeight;
        } catch (IOException e) {
            System.out.println("No previous data for maps has been saved.");
        }
        return scale;

    }

    public static int countCharacters(Scanner scanner) {
        String[] characters = scanner.nextLine().trim().split("");
        System.out.println(characters.length);
        return characters.length;
    }


}
