import javafx.scene.control.Cell;

import java.util.HashMap;

public class island {
    public HashMap<Location, Cell> board;
    public int width;
    public int height;

    public island(int w, int h){
        this.width = w;
        this.height = h;
        this.board = new HashMap<Location, Cell>();

        for(int i = 0; i < this.width * this.height; i++){
            //creer une cell pour chaque i
            //donner une Location qui augmente en x et y en fonction de width et height
            //random les keys et artifacts
            //Pas key et artifact du meme element sur la meme case
        }
    }

}

/**
board{
        JPanel board
        Hashmap<location, cellule> //permet de garder les cellules
    int width
            int height

            constructor board
            creer une cellule pour chaque location (en comptant width height)
            random des keys et artifacts en faisant attention a ce que
            les keys et les artefacts du meme type ne soient pas sur la meme cellule
            dimensions

            getCellule(Location) //return une cellule a partir d'une position
            void render() // appeller toutes les methodes render des cellules
            }
 **/