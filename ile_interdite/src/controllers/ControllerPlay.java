package controllers;

import model.Island;
import views.ViewGame;
import views.ViewMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControllerPlay implements ActionListener {
    private final Island model;
    private final JFrame menu;

    public ControllerPlay(Island model, JFrame menu) {
        this.model = model;
        this.menu = menu;
    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.model.players.size() > 0) {
            this.menu.dispose();
            try {
                ViewGame view = new ViewGame(this.model);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (FontFormatException fontFormatException) {
                fontFormatException.printStackTrace();
            }
        }
        else {
            ViewMenu.updateLabel("Vous devez ajouter au moins 1 joueur");
        }
    }
}
