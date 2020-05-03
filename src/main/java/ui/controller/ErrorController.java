package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class ErrorController {

    @FXML
    private Label laErrorMessage;

    private final Exception e;

    public ErrorController(Exception e){
        this.e = e;
    }

    public void setdata() {
        laErrorMessage.setText(e.getMessage());
    }

    public void alert(){
        System.out.println("knop geklikt!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        StackTraceElement[] ste = e.getStackTrace();
        StringBuilder sb = new StringBuilder();

        for (StackTraceElement s : ste) {
            sb.append(s.toString()).append("\n");
        }
        alert.setContentText(sb.toString());
        alert.show();
    }
}
