import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIDomino extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Mexican Domino");
        primaryStage.setScene(new Scene(root, 960  , 600));
        primaryStage.show();
    }
    public static void run(String[] args) {
        launch(args);
    }

}
