package comp1110.ass2;

import javafx.scene.*;

public class Assam {
    public int direction;
    public IntPair position;
    /**
     *
     * Contains information about Assam (i.e. his position and facing direction).
     * By default, set to (0,0) and 1.
     * Direction ranges from 1-4:
     * 1 = Up; 2 = Right; 3 = Down; 4 = Left.
     *
     */
    public Assam() {
        this.position = new IntPair(0, 0);
        this.direction = 1;
    }

    public void initialiseFromString(String assamString) {
        char[] assamStringChars = assamString.toCharArray();
        position = new IntPair((Character.getNumericValue(assamStringChars[1])),
                (Character.getNumericValue(assamStringChars[2])));
        if (assamStringChars[3] == 'N') {
            direction = 1;
        } else if (assamStringChars[3] == 'E') {
            direction = 2;
        } else if (assamStringChars[3] == 'S') {
            direction = 3;
        } else {
            direction = 4;
        }
    }

    public void moveAssam(int amount) {

    }
}
