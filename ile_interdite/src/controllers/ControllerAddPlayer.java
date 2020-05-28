package controllers;

import model.Island;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAddPlayer implements ActionListener {
    private final Island model;
    private final JTextField name;
    private final JFrame frame;

    public ControllerAddPlayer(Island model, JTextField name, JFrame frame) {
        this.model = model;
        this.name = name;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((this.model.players.size() < 8) && name.getText().length() <= 10 && name.getText().length() > 0) {
            this.model.addPlayer(name.getText());
            ViewMenu.addPlayer(name.getText(), this.model.players.size());
        }
        else {
            //TODO afficher lorsque le nb de caractère est trop petit ou trop grand
            System.out.println("Vous avez atteint le nombre de joueur max");
        }
        frame.dispose();
    }
}
