package controllers;

import model.Island;
import views.ViewGame;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerReplay implements ActionListener {
    private JDialog game;

    public ControllerReplay(JFrame frame, JDialog dialog, Island model){
        this.game = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Rejouer") {
            Island model = new Island(12, 12);
            ViewGame.hidden();
            ViewMenu.visible(model);
            this.game.dispose();
        } else if (e.getActionCommand() == "Quitter"){
            System.exit(0);
        }
    }
}