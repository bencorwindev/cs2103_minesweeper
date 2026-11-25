package cs2103.minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardStatusTest {

    @Test
    void newBoardIsPlaying() {
        Board b = new Board(5, 5, 42L);
        assertEquals(Board.GameStatus.PLAYING, b.status());
    }

    @Test
    void revealingMineLoses() {
        Board b = new Board(5, 5, 42L);
        outer:
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (b.getBoard().get(r).get(c) instanceof Mine) {
                    b.revealTile(r, c);
                    break outer;
                }
            }
        }
        assertEquals(Board.GameStatus.LOST, b.status());
    }

    @Test
    void revealingAllSafeTilesWins() {
        Board b = new Board(5, 5, 42L);
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (b.getBoard().get(r).get(c) instanceof NumberTile) {
                    b.revealTile(r, c);
                }
            }
        }
        assertEquals(Board.GameStatus.WON, b.status());
    }
}