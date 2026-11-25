package cs2103.minesweeper;

public class Mine extends Tile{
    public void reveal() {
        /** reveals the tile if it isn't flagged and isn't already revealed.
         *
         */
        if(!this.isRevealed() && !this.isFlagged()){
            this.setRevealed(true);
        }
    }
}
