package model;

import exceptions.ExceptionNbHits;

import java.util.ArrayList;
import java.util.Random;

public class Island extends Observable {
    public Cell[][] board;
    public int width;
    public int height;
    public ArrayList<Player> players;
    public Player playerCourant;
    private final Cell heliport;
    private final static Cell.Element[] ELEMENTS = {Cell.Element.FIRE, Cell.Element.WATER, Cell.Element.EARTH, Cell.Element.AIR};
    Random random = new Random();

    public Island(int w, int h) {
        this.width = w;
        this.height = h;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();

        int heliportX = random.nextInt(this.width/2)+this.width/4;
        int heliportY = random.nextInt(this.height/2)+this.height/4;

        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                this.board[i][j] = new Cell(this, i, j, heliportX == i && heliportY == j);
            }
        }
        this.heliport = this.board[heliportX][heliportY];

        int nbKey = (int) (this.width*this.height*0.15);

        for(int i = 0; i < 4; i++) {
            int j = 0;
            while (j < nbKey/4){
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (!cell.hasKey() && !cell.isHeliport()) {
                    cell.setKey(ELEMENTS[i]);
                    j++;
                }
            }
            boolean artefact = false;
            while(!artefact) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if(cell.getAbs() < this.width/4 || cell.getAbs() > (this.width/4)*3 && cell.getOrd() < this.height/4 || cell.getOrd() > (this.height/4)*3 && !cell.hasKey() && !cell.isHeliport() && !cell.hasArtifact()) {
                    cell.setArtifact(ELEMENTS[i]);
                    artefact = true;
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    public void addPlayer(String name) {
        if(players.isEmpty()) {
            Player p = new Player(this, name, this.heliport.getAbs(), this.heliport.getOrd());
            this.players.add(p);
            this.playerCourant = p;
        }
        else {
            Player p = new Player(this, name, this.players.get(0), this.heliport.getAbs(), this.heliport.getOrd());
            this.players.get(this.players.size()-1).setNext(p);
            this.players.add(p);
        }
    }

    public void risingWater() {
        int nbcell = 0;
        // TODO REVOIR L'EXCEPTION
        try{
            while (nbcell < 3) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (!cell.isSubmerged()) {
                    cell.flood();
                    nbcell++;
                }
            }
        } catch (Exception e) {
            //If there's an error at runtime, flood everything
            for(int i = 0; i<this.width; i++) {
                for(int j = 0; j < this.height; j++) {
                    if (!this.board[i][j].isSubmerged()) {
                        this.board[i][j].flood();
                    }
                }
            }
        }
        double nb = Math.random();
        if(nb < 1) {
            int hint = random.nextInt(4);
            this.playerCourant.addKey(ELEMENTS[hint]);
        }

        this.playerCourant.restoreNbHits();
        this.playerCourant = this.playerCourant.getNext();
        System.out.println(this.players.size());
        notifyObservers();
    }

    public void dry(int x, int y) {
        try {
            this.playerCourant.addHits();
            Cell cell = this.board[x][y];
            if (cell.isFlooded()) {
                cell.dryCell();
            }
            else if (cell.isSubmerged()) {
                // TODO : exception pour l'impossibilité d'assécher
            }
            notifyObservers();
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas assécher");
            //exceptionNbHits.printStackTrace();
        }
    }

    public void movePlayer(Player.Direction key) {
        this.playerCourant.move(key);
        notifyObservers();
    }

    public void searchKey() {
        try {
            this.playerCourant.addHits();
            Cell cell = this.board[this.playerCourant.getAbs()][this.playerCourant.getOrd()];
            if (cell.hasKey()) {
                this.playerCourant.addKey(cell.getKey());
                cell.updateKey();
            } else {
                double nb = Math.random();
                if (nb < 0.65) {
                    cell.flood();
                }
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas fouiller la zone");
            //exceptionNbHits.printStackTrace();
        }
        notifyObservers();
    }

    public void recoverArtifact () {
        try {
            Cell cell = this.board[this.playerCourant.getAbs()][this.playerCourant.getOrd()];
            if(cell.hasArtifact() && this.playerCourant.hasKey(cell.getArtifact())) {
                this.playerCourant.addHits();
                this.playerCourant.addArtifact(cell.getArtifact());
                this.playerCourant.updateKey(cell.getArtifact());
                cell.updateArtifact();
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas ramasser d'artefact");
            //exceptionNbHits.printStackTrace();
        }
    }
}