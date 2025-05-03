package ir.ac.kntu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Setting extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 300, Color.LIGHTGREEN);

        Text difficultyText = new Text();
        difficultyText.setText("Difficulty");
        difficultyText.setFont(Font.font("Arial", 20));
        difficultyText.setX(10);
        difficultyText.setY(25);
        root.getChildren().add(difficultyText);

        Text reportDifficulty = new Text();
        reportDifficulty.setText("");
        reportDifficulty.setX(10);
        reportDifficulty.setY(100);
        reportDifficulty.setFont(Font.font("Arial", 14));

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton hardOption = new RadioButton();
        hardOption.setToggleGroup(toggleGroup);
        hardOption.setLayoutX(100);
        hardOption.setLayoutY(10);
        hardOption.setFont(Font.font("Arial", 14));
        hardOption.setText("Hard");

        RadioButton mediumOption = new RadioButton();
        mediumOption.setToggleGroup(toggleGroup);
        mediumOption.setLayoutX(100);
        mediumOption.setLayoutY(35);
        mediumOption.setFont(Font.font("Arial", 14));
        mediumOption.setText("Medium");

        RadioButton easyOption = new RadioButton();
        easyOption.setToggleGroup(toggleGroup);
        easyOption.setLayoutX(100);
        easyOption.setLayoutY(60);
        easyOption.setFont(Font.font("Arial", 14));
        easyOption.setText("Easy");

        Button submitDifficulty = new Button();
        submitDifficulty.setText("Save");
        submitDifficulty.setFont(Font.font("Arial", 16));
        submitDifficulty.setLayoutX(15);
        submitDifficulty.setLayoutY(45);
        submitDifficulty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hardOption.isSelected()) {
                    reportDifficulty.setText("Game difficulty changed to Hard");
                    //TODO

                }
                if (mediumOption.isSelected()) {
                    reportDifficulty.setText("Game difficulty changed to Medium");
                    //TODO
                }
                if (easyOption.isSelected()) {
                    reportDifficulty.setText("Game difficulty changed to Easy");
                    //TODO
                }
            }
        });

        root.getChildren().addAll(hardOption, mediumOption, easyOption, submitDifficulty, reportDifficulty);
        Image stageIcon = new Image("C:\\Users\\a\\Desktop\\Practices\\AP4012\\Project4\\src\\main\\resources\\images\\menuTank1.jpg");
        stage.setTitle("Setting");
        stage.getIcons().add(stageIcon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
