package comp1110.ass2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    public Tile[][] tiles;
    public Rectangle[][] squares;
    public Board() {
        this.tiles = new Tile[7][7];
        this.squares = new Rectangle[7][7];
    }

    public void initialiseFromString(String[] boardStrings) {

        RugAbreviated[][] tileInfos = new RugAbreviated[7][7];
        for (int i = 0; i < boardStrings.length; i++) {
            RugAbreviated tileInfo = new RugAbreviated();
            tileInfo.initialiseFromString(boardStrings[i]);
            tileInfos[(i/7)][i%7] = tileInfo;
        }

        for (int i = 0; i < tileInfos.length; i++) {
            for (int j = 0; j < tileInfos[i].length; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].state = 0;
                squares[i][j] = new Rectangle();
                squares[i][j].setStroke(Color.BLACK);
                squares[i][j].setWidth(60.0);
                squares[i][j].setHeight(60.0);
                squares[i][j].setX((60.0 * (i + 1)) + 110.0);
                squares[i][j].setY((60.0 * (j + 1)) + 32.0);
                if (tileInfos[i][j].colour == 'c') {
                    tiles[i][j].state = 1;
                    squares[i][j].setFill(Color.CYAN);
                } else if (tileInfos[i][j].colour == 'y') {
                    tiles[i][j].state = 2;
                    squares[i][j].setFill(Color.YELLOW);
                } else if (tileInfos[i][j].colour == 'r') {
                    tiles[i][j].state = 3;
                    squares[i][j].setFill(Color.RED);
                } else if (tileInfos[i][j].colour == 'p') {
                    tiles[i][j].state = 4;
                    squares[i][j].setFill(Color.PURPLE);
                } else {
                    tiles[i][j].state = 0;
                    squares[i][j].setFill(Color.LIGHTGREY);
                }
            }
        }
    }
}
