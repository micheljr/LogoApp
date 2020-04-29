package ui.controller;

import ui.LOGOapp;

public class KeuzesController {

    private LOGOapp logoApp;
    private String login;

    public void setMainApp(LOGOapp logoApp){
        this.logoApp = logoApp;
    }

    public void setData(String login){
        this.login = login;
    }
}
