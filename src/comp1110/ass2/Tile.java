package comp1110.ass2;

public class Tile {
    public int state;

    /**
     *
     * Contains a state as an integer value:
     * 0 = empty; 1 = occupied by player 1's rug; 2 = occupied by player 2's rug; etc.
     *
     */
    Tile() {
        this.state = 0;
    }
}
