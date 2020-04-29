package ui;

import DAO.AccountDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.AccountService;
import ui.controller.KeuzesController;
import ui.controller.LoginController;
import ui.controller.RegistrerenController;

public class LOGOapp extends Application {

    private Stage stage;

    private AccountService accountService;
    private AccountDAO accountDAO;

    private AccountService createAccountService(){
        if (accountService == null){
            accountService = new AccountService(createAccountDao());
        }
        return accountService;
    }
    private AccountDAO createAccountDao(){
        if (accountDAO == null){
            accountDAO = new AccountDAO();
        }
        return accountDAO;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        laadLoginScherm();
        primaryStage.show();
    }

    public void laadLoginScherm(){
        try {
            String fxmlFile = "/fxml/Login.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

            LoginController controller = new LoginController(createAccountService());
            loader.setController(controller);
            Parent root = loader.load();

            controller.setMainApp(this);

            Scene scene = new Scene(root);
            stage.setTitle("LogoApp");
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("!!! - " + e.getMessage());
        }
    }

    public void laadKeuzeScherm(String login){
        try {
            String fxmlFile = "/fxml/Keuzes.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

            KeuzesController controller = new KeuzesController();
            controller.setData(login);

            loader.setController(controller);
            Parent root = loader.load();

            controller.setMainApp(this);

            Scene scene = new Scene(root);
            stage.setTitle("LogoApp");
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("!!! - " + e.getMessage());
        }
    }

    public void laadRegistrerenScherm(){
        try {
            String fxmlFile = "/fxml/Registreren.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

            RegistrerenController controller = new RegistrerenController(createAccountService());
            loader.setController(controller);
            Parent root = loader.load();

            controller.setMainApp(this);

            Scene scene = new Scene(root);
            stage.setTitle("LogoApp");
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("!!! - " + e.getMessage());
        }
    }

    /**
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
