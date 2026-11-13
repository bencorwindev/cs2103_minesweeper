package cs2103.minesweeper;

public abstract class Tile {
    private boolean flagged; //if the tile is marked as a potential mine
    private boolean revealed; //if the tile has been revealed as a number or mine

    //TODO: Either use a getter to return if the tile is flagged or add gui code within the class itself

    /**
     * Changes the {@link #flagged} of a non-revealed tile
     */
    void flag(){
        if(!revealed)
            flagged = !flagged;
        //TODO: stub
    }

    /**
     * Reveals a hidden tile as either a mine or number,
     * if it is a {@see cs2103.minesweeper.Mine}, the game ends
     * if it is a number, its {@link #flagged} property will be set true
     */
    abstract void reveal();

}
