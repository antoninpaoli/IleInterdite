package views;

import controllers.ControllerAction;
import model.Cell;
import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewIsland extends JPanel implements Observer {
    private final Island model;
    private final ViewPlayer player;
    public static final int SIZE = 35;
    public static ControllerAction ctrl;

    public ViewIsland(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.player = new ViewPlayer(this.model);
        this.setBounds(400,100,SIZE * this.model.getWidth(),SIZE * this.model.getHeight());
        this.setOpaque(false);
        ctrl = new ControllerAction(this.model);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.repaint();
        for (int i = 0; i < this.model.getWidth(); i++) {
            for (int j = 0; j < this.model.getHeight(); j++) {
                paint(g, model.getCell(i, j), i*SIZE, j*SIZE);
            }
        }
        this.player.paintComponent(g);
    }

    private void paint(Graphics g, Cell c, int x, int y) {
        switch (c.getState()) {
            case NORMAL:
                g.setColor(new Color(252, 210, 133, 192));
                break;
            case FLOODED:
                g.setColor(new Color(59, 141, 191, 192));
                break;
            case SUBMERGED:
                g.setColor(new Color(31, 61, 87, 192));
                break;
        }
        g.fillRect(x, y, SIZE -6, SIZE -6);


        if (c.hasArtifact()) {
            switch (c.getArtifact()) {
                case FIRE:
                    g.setColor(new Color(195, 42, 47));
                    break;
                case WATER:
                    g.setColor(new Color(1, 79, 140));
                    break;
                case EARTH:
                    g.setColor(new Color(91, 123, 93));
                    break;
                case AIR:
                    g.setColor(new Color(255, 255, 255));
                    break;
                case NONE:
                    break;
            }
            g.fillRoundRect(x+6, y+6, SIZE - 20, SIZE - 20, 6, 6);
        }
        if (c.isHeliport()) {
            g.setColor(Color.BLACK);
            g.fillOval(x+2, y+2, SIZE - 12, SIZE - 12);
            g.setColor(Color.WHITE);
            g.drawOval(x+2, y+2, SIZE - 12, SIZE - 12);
            g.drawString("H", x+9, y+17);
        }
        JLabel label = new JLabel();
        label.setBounds(x, y, SIZE-6, SIZE-6);
        label.addMouseListener(ctrl);
        this.add(label);
    }

    @Override
    public void update() {
        repaint();
    }
}
