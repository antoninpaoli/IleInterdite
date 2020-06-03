package views;

import controllers.ControllerDelPlayer;
import controllers.ControllerFonctionnement;
import controllers.ControllerPlay;
import controllers.ControllerPlayer;
import fonts.PantonFont;
import model.Island;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewMenu extends JPanel{
    private final Island model;
    private static JFrame menu = new JFrame();
    private static ArrayList<JLabel> players = new ArrayList<>();
    private static JLabel info = new JLabel();

    public ViewMenu(Island model) {
        this.model = model;

        menu.setTitle("Menu");
        menu.setSize(875, 686);
        menu.setLayout(null);
        menu.setResizable(false);

        JLabel title = new JLabel("L'île interdite");
        title.setFont(PantonFont.getPantonBold().deriveFont(Font.PLAIN, 32));
        title.setBounds(20,28,200,80);
        title.setForeground(Color.WHITE);
        menu.add(title);

        JLabel operation = new JLabel("Fonctionnement");
        operation.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 23));
        operation.setBounds(25,108,200,80);
        operation.setForeground(Color.WHITE);
        menu.add(operation);

        JButton instructions = new JButton("Instructions");
        instructions.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 20));
        instructions.setBounds(55,170,160,35);
        instructions.setForeground(new Color(188, 199, 236));
        instructions.setContentAreaFilled(false);
        instructions.setBorderPainted(false);
        instructions.setFocusPainted(false);
        instructions.addActionListener(new ControllerFonctionnement());
        menu.add(instructions);

        JButton controles = new JButton("Contrôles");
        controles.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 20));
        controles.setBounds(62,205,160,35);
        controles.setForeground(new Color(188, 199, 236));
        controles.setContentAreaFilled(false);
        controles.setBorderPainted(false);
        controles.setFocusPainted(false);
        controles.addActionListener(new ControllerFonctionnement());
        menu.add(controles);

        JButton buttonPlay = new JButton("Jouer");
        buttonPlay.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 25));
        buttonPlay.setBounds(48,354,130,45);
        buttonPlay.setForeground(new Color(188, 199, 236));
        buttonPlay.setContentAreaFilled(false);
        buttonPlay.setBorderPainted(false);
        buttonPlay.setFocusPainted(false);
        buttonPlay.addActionListener(new ControllerPlay(this.model));
        menu.add(buttonPlay);

        info.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 10));
        info.setBounds(35,400,180,35);
        info.setForeground(Color.WHITE);
        menu.add(info);

        JButton buttonAddPlayer = new JButton("Ajouter joueur");
        buttonAddPlayer.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 12));
        buttonAddPlayer.setBounds(719,380,120,40);
        buttonAddPlayer.setForeground(Color.WHITE);
        buttonAddPlayer.setContentAreaFilled(false);
        buttonAddPlayer.setBorderPainted(false);
        buttonAddPlayer.setFocusPainted(false);
        buttonAddPlayer.addActionListener(new ControllerPlayer(this.model));
        menu.add(buttonAddPlayer);

        int coordy = 110;
        for(int i = 0; i < 8; i++) {
            JLabel player = new JLabel();
            player.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 14));
            player.setBounds(740,coordy,100,35);
            player.setForeground(Color.WHITE);
            player.addMouseListener(new ControllerDelPlayer(this.model));
            players.add(player);
            coordy += 30;
            menu.add(player);
        }

        JLabel footer = new JLabel("Projet POGL - Antoine BARBANNAUD - Nelly LAM - Antonin PAOLI");
        footer.setFont(PantonFont.getPantonLight().deriveFont(Font.PLAIN, 10));
        footer.setBounds(550,588,300,80);
        footer.setForeground(new Color(89, 97, 125));
        menu.add(footer);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_menu.jpg").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, CENTER);
        background.setBounds(0,0,864,648);
        menu.add(background);

        menu.setVisible(true);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void addPlayer(String name, int joueur) {
        players.get(joueur).setText(name);
    }

    public static void delPlayer(int joueur) {
        players.get(joueur).setText("");
    }

    public static void updateLabel(String message) {
        info.setText(message);
    }

    public static void hidden() {
        menu.dispose();
    }
}