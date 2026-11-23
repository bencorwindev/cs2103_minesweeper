package cs2103.minesweeper;

public class NumberTile extends Tile{
    private int mines;

    /** reveals the tile if it isn't flagged and isn't already revealed.
     * 
     */
    public void reveal() {
        if(!this.isRevealed() && !this.isFlagged()){
            this.setRevealed(true);
        }
    }
    /** sets the number of mines bordering the tile to mines.
     * 
     * @param mines - the number of mines bordering the tile.
     */
    public void setMineCount(int mines){
        this.mines = mines;
    }
    /** gets mine count
     * 
     * @return number of mines bordering the tile
     */
    public int getMines(){
        return mines;
    }
}
