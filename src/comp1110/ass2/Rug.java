package comp1110.ass2;

public class Rug {
    public char colour;
    public int ID;
    public IntPair tile1Pos;
    public IntPair tile2Pos;

    public Rug() {
        this.colour = 'r';
        this.ID = 0;
        this.tile1Pos = new IntPair(0,0);
        this.tile2Pos = new IntPair(0,0);
    }

    /**
     *
     *
     * @param rugString the string representation of a rug, passed in by the RugsOnBoard class in the initialisation
     *                  of the rugs from the game string.
     */
    public void initialiseFromString(String rugString) {
        char[] rugStringChars = rugString.toCharArray();
        colour = rugStringChars[0];
        ID = (Character.getNumericValue(rugStringChars[1]) * 10) + Character.getNumericValue(rugStringChars[2]);
        tile1Pos = new IntPair(Character.getNumericValue(rugStringChars[3]), Character.getNumericValue(rugStringChars[4]));
        tile2Pos = new IntPair(Character.getNumericValue(rugStringChars[5]), Character.getNumericValue(rugStringChars[6]));
    }

    public void setRug(char colour, int id, IntPair tile1Pos, IntPair tile2Pos) {
        this.colour = colour;
        this.ID = id;
        this.tile1Pos = tile1Pos;
        this.tile2Pos = tile2Pos;
    }
}
