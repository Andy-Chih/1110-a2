package comp1110.ass2;

public class GameInfo {
    public String[] playerStrings;
    public String assamString;
    public String[] abvRugStrings;

    /**
     *
     * Divides the game string into three distinct, more processable strings:
     * Player strings, the Assam string, and the rug strings.
     *
     */
    public GameInfo() {
        this.playerStrings = new String[4];
        this.assamString = new String();
        this.abvRugStrings = new String[49];
    }

    /**
     *
     * Divides the game string into smaller, processable strings as per the given game string from the Marrakech class.
     * @param gameString the game string, passed from the Marrakech class.
     */
    public void updateFromString(String gameString) {
        char[] gameStringChars = gameString.toCharArray();
        int playerCount = 0;
        int i = 0;
        while (i < gameStringChars.length) {
            if (gameStringChars[i] == 'P') {
                playerCount++;
                i = i + 8;
            }
            else if (gameStringChars[i] == 'A') {
                i = i + 4;
            }
            else if (gameStringChars[i] == 'B') {
                i = gameStringChars.length;
            }
            else {
                i++;
            }
        }

        char[][] playerStringsChars = new char[playerCount][8];
        char[] assamStringChars = new char[4];
        char[][] abvRugStringChars = new char[49][3];
        int playerNumTracker = 0;

        playerStrings = new String[playerCount];

        i = 0;

        while (i < gameStringChars.length) {
            if (gameStringChars[i] == 'P') {
                for (int j = 0; j < 8; j++) {
                    playerStringsChars[playerNumTracker][j] = gameStringChars[i];
                    i++;
                }
                playerStrings[playerNumTracker] = new String(playerStringsChars[playerNumTracker]);
                playerNumTracker++;
            }
            else if (gameStringChars[i] == 'A') {
                for (int j = 0; j < 4; j++) {
                    assamStringChars[j] = gameStringChars[i];
                    i++;
                }
                assamString = new String(assamStringChars);
            }
            else if (gameStringChars[i] == 'B') {
                i++;
                for (int j = 0; j < 49; j++) {
                    abvRugStringChars[j][0] = gameStringChars[i];
                    abvRugStringChars[j][1] = gameStringChars[i + 1];
                    abvRugStringChars[j][2] = gameStringChars[i + 2];
                    abvRugStrings[j] = new String(abvRugStringChars[j]);
                    i = i + 3;
                }
                i = gameStringChars.length;
            }
            else {
                i++;
            }
        }
    }
}
