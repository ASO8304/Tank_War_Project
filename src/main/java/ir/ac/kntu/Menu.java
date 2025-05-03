package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Menu extends Application {

    Button startButton = new Button();

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.DARKGREEN);

        ImageView menuTank = new ImageView(new Image("file:images\\menuTank.jpg"));
        menuTank.setFitWidth(1550);
        menuTank.setFitHeight(900);
        root.getChildren().add(menuTank);

        ImageView enter = new ImageView(new Image("file:images\\Enter.png"));
        enter.setX(1300);
        enter.setY(740);


        startButton.setLayoutX(1310);
        startButton.setLayoutY(750);
        startButton.setFont(Font.font("Arial", 30));
        startButton.setBackground(Background.EMPTY);
        startButton.setOnAction(event -> {
            try {
                new UserRegister().start(new Stage());
                //new SelectLevel().start(new Stage());
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
        root.getChildren().add(enter);
        root.getChildren().add(startButton);

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