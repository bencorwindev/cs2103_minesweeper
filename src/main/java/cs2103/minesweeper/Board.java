package cs2103.minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private ArrayList<ArrayList<Tile>> board = new ArrayList<>();

    public Board(int rows, int columns) {
        ArrayList<Integer> mineLocations = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i < (rows*columns)/6; i++){
            mineLocations.add(random.nextInt(rows * columns));
        }
        int tileCounter = 0;
        for(int i = 0; i < rows; i++){
            ArrayList<Tile> row = new ArrayList<>();
            for(int j = 0; j < columns; j++){
                if(!mineLocations.isEmpty() && tileCounter == mineLocations.get(0)){
                    mineLocations.removeFirst();
                    row.add(new Mine());
                }
                else{
                    row.add(new NumberTile());
                }
                tileCounter++;
            }
            board.add(row);
        }

    }
    public Board(int rows, int columns, long setSeed){
        ArrayList<Integer> mineLocations = new ArrayList<>();
        Random random = new Random(setSeed);

        for(int i = 0; i < (rows*columns)/6; i++){
            mineLocations.add(random.nextInt(rows * columns));
        }

        int tileCounter = 0;
        for(int i = 0; i < rows; i++){
            ArrayList<Tile> row = new ArrayList<>();
            for(int j = 0; j < columns; j++){
                if(!mineLocations.isEmpty() && tileCounter == mineLocations.get(0)){
                    mineLocations.removeFirst();
                    row.add(new Mine());
                }
                else{
                    row.add(new NumberTile());
                }
                tileCounter++;
            }
            board.add(row);
        }
    }

    public ArrayList<ArrayList<Tile>> getBoard() {
        return this.board;
    }
}
