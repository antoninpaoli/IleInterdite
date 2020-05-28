package views;

import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class ViewPlayer extends JPanel {
    private final Island model;

    public ViewPlayer(Island model) {
        this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        for (Player player : this.model.players) {
            paint(g, player, player.getAbs()*ViewIsland.SIZE, player.getOrd()*ViewIsland.SIZE);
        }
    }

    private void paint(Graphics g, Player p, int x, int y) {
        g.setColor(p.getColor());
        //TODO adapter en fonction du nombre de joueur sur la case
        g.fillOval(x+3, y+3, 10, 10);
    }
}
