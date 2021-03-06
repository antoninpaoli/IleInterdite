package controllers;

import model.Island;
import model.Player;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControllerDelPlayer extends MouseAdapter {
    private final Island model;

    public ControllerDelPlayer(Island model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getComponent();
        for (Player p : this.model.getPlayers()) {
            if (p.getName().equals(label.getText())) {
                ViewMenu.delPlayer(this.model.getPlayers().indexOf(p));
                this.model.removePlayer(p);
                break;
            }
        }

        for (Player p : this.model.getPlayers()) {
            ViewMenu.addPlayer(p.getName(), this.model.getPlayers().indexOf(p));
        }
        ViewMenu.delPlayer(this.model.getPlayers().size());
    }
}
