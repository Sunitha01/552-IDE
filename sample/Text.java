package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Text extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
        TextField tf1=new TextField();
        Button b = new Button("Run");
//        GridPane root = new GridPane();
//        root.addRow(0,tf1);
//        root.addRow(1,b);
        Scene scene=new Scene(root,800,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Text Field Example");
        primaryStage.show();




}
}
