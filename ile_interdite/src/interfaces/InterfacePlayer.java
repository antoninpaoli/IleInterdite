package interfaces;

import exceptions.ExceptionNbEvents;
import model.Cell;
import model.Player;

import java.awt.*;
import java.util.ArrayList;

public interface InterfacePlayer {

    /**
     * Permet de passer au prochain joueur
     * @param p le prochain joueur
     */
    void setNext(Player p);

    /**
     * Enleve une cle de l'inventaire du joueur
     * @param key une cle de type Element
     */
    void updateKey(Cell.Element key);

    /**
     * Permet d'ajouter une cle a l'inventaire du joueur
     * @param e l'Element de la cle
     */
    void addKey(Cell.Element e);

    /**
     * Permet d'ajouter un artifact a l'inventaire du joueur
     * @param e l'Element de l'artifact
     */
    void addArtifact(Cell.Element e);

    /**
     * Permet d'ajouter une action speciale a l'inventaire du joueur
     * @param a le type d'action speciale
     */
    void addActions(Player.SpecialAction a);

    /**
     * Permet de verifier si un joueur est sur la meme case que le joueur courant
     * @param p le joueur a verifier
     * @return un boolean
     */
    boolean isOnSameCell(Player p);

    /**
     * Permet de bouger le joueur vers la direction designee
     * @param key la touche designant la direction
     */
    void move(Player.Direction key);

    /**
     * Permet de teleporter un joueur vers des coordonnees
     * @param x l'abcisse
     * @param y l'ordonnee
     */
    void teleportPlayer(int x, int y);

    /**
     * Permet de recuperer un artifact sur une case
     * @param cell la cellule sur laquelle l'artifact est
     * @return un boolean designant si l'operation fut effectuee
     * @throws ExceptionNbEvents si le joueur n'as plus assez de coups a jouer
     */
    boolean recoverArtifactPlayer(Cell cell) throws ExceptionNbEvents;

    /**
     * Permet d'enlever un coup au compteur de coups possibles du joueur
     * @throws ExceptionNbEvents si le joueur n'as plus assez de coups a jouer
     */
    void addEvents() throws ExceptionNbEvents;

    /**
     * Permet de restaurer a sa valeur defaut le nombre de coups possibles du joueur courant
     */
    void restoreNbEvents();

    /**
     * Renvoie le status de vie du joueur
     * @return un boolean
     */
    boolean isDead();

    /**
     * Retourne le nombre de cle d'un certain element disponibles dans l'inventaire du joueur
     * @param e un element
     * @return un entier
     */
    int nbKeyElement(Cell.Element e);

    /**
     * Retourne le nombre d'actions speciales disponibles dans l'inventaire du joueur
     * @param a un element
     * @return un entier
     */
    int nbSpecialAction(Player.SpecialAction a);

    /**
     * Permet de verifier quels joueurs sont sur la meme case
     * @return une liste de joueurs sur la meme case
     */
    ArrayList<Player> playersOnSameCell();
}