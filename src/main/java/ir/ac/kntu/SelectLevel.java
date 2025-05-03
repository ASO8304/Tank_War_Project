package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectLevel extends Application {

    Button[] buttons = new Button[10];

     private Player player ;

    public SelectLevel(Player player) {
        this.player = player;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.DARKGREEN);

        Image menuTank = new Image("file:images\\selectLevel.jpg");
        ImageView imageView = new ImageView(menuTank);
        root.getChildren().add(imageView);
        int y = 200;
        for (int i = 0; i < 10; i++) {
            y += 50;
            buttons[i] = new Button("Level" + (i + 1));
            buttons[i].setFont(Font.font("Arial", 20));
            buttons[i].setLayoutX(700);
            buttons[i].setLayoutY(y);
            buttons[i].setBlendMode(BlendMode.LIGHTEN);
            buttons[i].setTextFill(Color.CHOCOLATE);
            int finalI = i + 1;
            buttons[i].setOnAction(event -> {
                try {
                    new Start(finalI, player).start(new Stage());
                    stage.close();
                    this.stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            root.getChildren().add(buttons[i]);
        }

        Text selectLevel = new Text("Select Level");
        selectLevel.setX(645);
        selectLevel.setY(100);
        selectLevel.setFont(Font.font("Arial", 40));
        selectLevel.setFill(Color.BLUE
        );

        root.getChildren().add(selectLevel);

        Image stageIcon = new Image("file:src\\main\\resources\\images\\menuTank1.jpg");
        stage.getIcons().add(stageIcon);

        stage.getIcons().add(new Image("file:images\\GameIcon.png"));
        stage.setTitle("Tank Game");
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }
}
