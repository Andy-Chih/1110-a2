package comp1110.ass2;

public class Player {
    public int dirhams;
    public int rugs;
    public boolean status;

    public char colour;

    /**
     *
     * Stores a Player's information (i.e. dirhams, rugs, and status (in or out of the game)).
     *
     */
    public Player(char colour) {
        this.dirhams = 30;
        this.rugs = 15;
        this.status = true;
        this.colour = colour;
    }

    /**
     *
     * Stores information about the players as per the provided game string.
     * @param playerString the string representations of the players, passed in by the GameInfo class.
     */
    public void initialiseFromString(String playerString) {
        char[] playerStringChars = playerString.toCharArray();
        colour = playerStringChars[1];
        dirhams = ((Character.getNumericValue(playerStringChars[2])) * 100) +
                ((Character.getNumericValue(playerStringChars[3])) * 10) +
                (Character.getNumericValue(playerStringChars[4]));
        rugs = ((Character.getNumericValue(playerStringChars[5])) * 10) +
                (Character.getNumericValue(playerStringChars[6]));
        if (playerStringChars[7] == 'i') {
            status = true;
        }
        else {
            status = false;
        }
    }

    /**
     *
     * Removes one rug from a player's total rug count.
     *
     */
    public void placeRug() {

    }

    /**
     *
     * Deducts dirhams from a player's total when a payment must be made.
     * @param payAmount is the amount that the player must pay.
     *
     */
    public void makePayment(int payAmount) {

    }

    /**
     *
     * Eliminates a player by changing their status.
     *
     */
    public void eliminatePlayer() {

    }
}
