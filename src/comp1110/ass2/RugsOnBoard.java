package comp1110.ass2;

public class RugsOnBoard {
    public RugAbreviated[] rugs;
    public RugsOnBoard() {
        this.rugs = new RugAbreviated[0];
    }

    /**
     *
     * Retrieves information from the game string and initialises the rugs on the board.
     * @param rugStrings the string representation of the rugs currently on the board, stored in an array.
     *
     */
    public void initialiseFromString(String[] rugStrings) {
        rugs = new RugAbreviated[rugStrings.length];
        for (int i = 0; i < rugStrings.length; i++) {
            char[] rugChars = rugStrings[i].toCharArray();
            rugs[i] = new RugAbreviated();
            rugs[i].colour = rugChars[0];
            rugs[i].ID = (Character.getNumericValue(rugChars[1]) * 10) + (Character.getNumericValue(rugChars[2]));
        }
    }
}
