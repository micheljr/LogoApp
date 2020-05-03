package ui;

import exception.DBException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.controller.ErrorController;


public class ErrorTest extends Application {

    private Stage stage;
    private final DBException dbe = new DBException("try this!");

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        laadErrorScherm(dbe);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void laadErrorScherm(Exception dbe){
        try {
            String fxmlFile = "/fxml/Error.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

            ErrorController controller = new ErrorController(dbe);
            loader.setController(controller);
            Parent root = loader.load();

            controller.setdata();

            Scene scene = new Scene(root);
            stage.setTitle("Error");
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("!!! - " + e.getMessage());
        }
    }

}
