package ui.controller.DossierBeheer;

import ui.LOGOapp;

public class DossierBeheerController {

    private LOGOapp logoApp;
    private String login;

    public void setMainApp(LOGOapp logoApp) {
        this.logoApp = logoApp;
    }

    public void setData(String login) {
        this.login = login;
    }

    public void uitloggen(){
        logoApp.laadLoginScherm();
    }
}
