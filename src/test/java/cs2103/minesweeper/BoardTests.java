package cs2103.minesweeper;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BoardTests {
    
    //Tests if mine count is correct
    @Test
    public void boardMinesTest(){
        Board board = new Board(6, 6, 1);
        ArrayList<ArrayList<Tile>> boardArrayList = board.getBoard();

        int mineCount = 0;

        for(ArrayList<Tile> row : boardArrayList){
            for(Tile tile : row){
                if(tile instanceof Mine){
                    mineCount++;
                }
            }
        }

        assertEquals(6, mineCount);
    }

    //Tests if board is able to be reproduced
    @Test
    public void reproductionTest(){
        Board board1 = new Board(10, 10, 1);
        String board1String = board1.toString();
        System.out.println(board1String);

        Board board2 = new Board(10, 10, 1);
        String board2String = board2.toString();

        assertEquals(board1String, board2String);
    }

    @Test
    public void surroundingMinesTest(){
        Board board = new Board(6, 6, 1);

        board.revealTile(0, 5);
        NumberTile testTile = (NumberTile)board.getBoard().get(0).get(5);
        assertEquals(1, testTile.getMines());
    }
}

