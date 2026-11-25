package cs2103.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Board {
    private ArrayList<ArrayList<Tile>> board = new ArrayList<>();
    private ArrayList<Integer> mines = new ArrayList<>();
    private ArrayList<Integer> safes = new ArrayList<>();
    private ArrayList<Integer> tiles = new ArrayList<>();


    /** Declares a board with a set number of rows and columns, and fills it with approximately 1/6 of the board being mines.
     * 
     * @param rows - the number of rows in the board
     * @param columns - the number of columns in the board
     */
    public Board(int rows, int columns) {
        ArrayList<Integer> mineLocations = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i<(rows*columns); i++) {
            safes.add(i);
            tiles.add(i);
        }
        for(int i = 0; i < (rows*columns)/6; i++){
            int mineLoc = random.nextInt(rows * columns);
            if (!mines.contains(mineLoc)) {
                mineLocations.add(mineLoc);
                mines.add(mineLoc);
                safes.remove(Integer.valueOf(mineLoc));
            } else {
                i--;
            }
        }

        Collections.sort(mineLocations);

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
    /** Declares a board with a set number of rows and columns, and fills it with approximately 1/6 of the board being mines. This one takes a set seed, for reproducibility.
     * 
     * @param rows - the number of rows in the board
     * @param columns - the number of columns in the board
     * @param setSeed - the seed for the RNG
     */
    public Board(int rows, int columns, long setSeed){
        ArrayList<Integer> mineLocations = new ArrayList<>();
        Random random = new Random(setSeed);
        for(int i = 0; i<(rows*columns); i++) {
            safes.add(i);
            tiles.add(i);
        }
        for(int i = 0; i < (rows*columns)/6; i++){
            int mineLoc = random.nextInt(rows * columns);
            if(!mineLocations.contains(mineLoc)){
                mineLocations.add(mineLoc);
                mines.add(mineLoc);
                safes.remove(Integer.valueOf(mineLoc));
            }
            else{
                i--;
            }
        }

        Collections.sort(mineLocations);

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
    
    /** Returns the board for accessibility & testing
     * 
     * @return the board, represented as a 2D ArrayList of Tiles (either Mines or NumberTiles)
     */
    public ArrayList<ArrayList<Tile>> getBoard() {
        return this.board;
    }

    /** Reveals the tile at (row, col), and sets the number of mines at the location if the tile is a NumberTile
     * 
     * @param row - the row of the tile
     * @param col - the column of the tile
     * @throws NullPointerException - throws if the tile is null
     * @throws IndexOutOfBoundsException - throws if either the row or column doesn't exist
     */
    public void revealTile(int row, int col) throws NullPointerException, IndexOutOfBoundsException{
        var tile = board.get(row).get(col);
        
        if(row < 0 || col < 0 || row >= board.size() || col >= board.getFirst().size()){
            throw new IndexOutOfBoundsException("Index (" + row + "," + col + ") is not a valid tile");
        }
        if(tile == null){
            throw new NullPointerException("Tile is null at (" + row + "," + col + ")");
        }
        else if(tile instanceof NumberTile){
            tile.reveal();
            int mineCount = 0;
            for(int r = row - 1; r <= row + 1; r++){
                for(int c = col - 1; c <= col + 1; c++){
                    if((r >= 0 && c >= 0) && (r < board.size() && c < board.get(0).size()) && board.get(r).get(c) instanceof Mine){
                        mineCount++;
                    }
                }
            }
            ((NumberTile) tile).setMineCount(mineCount);
        }
        else if(tile instanceof Mine){
            tile.reveal();
        }
    }

    /** defines the board as a string, with N representing a number and M representing a mine
     * 
     * @return the board string
     */
    @Override
    public String toString(){
        String temp = "";
        for(ArrayList<Tile> row : board){
            for(Tile tile : row){
                if(tile instanceof Mine){
                    temp += "M";
                }
                else{
                    temp += "N";
                }
            }
        }
        return temp;
    }

    public enum GameStatus {WON, LOST, PLAYING}
    HashMap<Integer, Boolean> revealed = new HashMap<>();

    /**
     * Checks whether the game is still playing, won, or lost.
     *
     * @return LOST if any mine has been revealed, WON if all non-mine tiles are revealed,
     *         PLAYING otherwise
     */
    public GameStatus status() {
        int columns = board.getFirst().size();
        for (int loc : tiles) {
            Tile tile = board.get(loc / columns).get(loc % columns);
            revealed.put(loc, tile.isRevealed());
        }
        for(int loc: mines) {
            if(revealed.get(loc))
                return GameStatus.LOST;
        }
        for(int loc: safes) {
            if(!revealed.get(loc)) {
                return GameStatus.PLAYING;
            }
        }
        return GameStatus.WON;
    }
}
