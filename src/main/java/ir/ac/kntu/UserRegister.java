package ir.ac.kntu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserRegister extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.DARKGREEN);

        ImageView registerMenu = new ImageView(new Image("file:images\\RegisterMenu.jpg"));
        registerMenu.setFitWidth(1550);
        registerMenu.setFitHeight(900);
        root.getChildren().add(registerMenu);

        ImageView usernameIcon = new ImageView(new Image("file:images\\Username.png"));
        usernameIcon.setX(165);
        usernameIcon.setY(95);
        usernameIcon.setFitHeight(40);
        usernameIcon.setFitWidth(40);

        TextField username = new TextField();
        username.setBackground(Background.EMPTY);
        username.setFont(Font.font("Arial", 40));
        username.setMaxWidth(300);
        username.setLayoutX(190);
        username.setLayoutY(80);
        username.setBorder(Border.EMPTY);

        String inputUsername = username.getText();
        System.out.println(inputUsername);

//        ArrayList<Player> players = new DataBase().loadPlayerInfo();
//        int y = 100;
//        for (Player player : players) {
//            y += 60;
//            Text usernamePl = new Text(player.getUsername());
//            Text highScorePl = new Text(String.valueOf(player.getHighScore()));
//            usernamePl.setX(1000);
//            usernamePl.setY(y);
//            highScorePl.setX(1300);
//            highScorePl.setY(y);
//            usernamePl.setFont(Font.font("Arial", 30));
//            highScorePl.setFont(Font.font("Arial", 30));
//            usernamePl.setFill(Color.WHITE);
//            highScorePl.setFill(Color.WHITE);
//            root.getChildren().addAll(usernamePl, highScorePl);
//        }

        Button register = new Button();
        register.setBackground(Background.EMPTY);
        register.setFont(Font.font("Arial", 60));
        register.setLayoutX(190);
        register.setLayoutY(250);
        register.setOnAction(event -> {
            try {
                new SelectLevel(new Player(inputUsername)).start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();
            try {
                this.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        scene.setOnKeyPressed(event -> {
            try {
                new SelectLevel(new Player(inputUsername)).start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();
            try {
                this.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        root.getChildren().add(username);
        root.getChildren().add(usernameIcon);
        root.getChildren().add(register);


        Image stageIcon = new Image("file:images\\GameIcon.png");
        stage.getIcons().add(stageIcon);
        stage.setTitle("Tank Game");
        stage.setScene(scene);
        stage.setX(70);
        stage.setY(4);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }
}
