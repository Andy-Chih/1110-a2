package comp1110.ass2;

public class AllPlayers {
    public Player[] players;
    public AllPlayers() {
        this.players = new Player[0];
    }
    public void initialiseFromStringAll(String[] playerStrings) {
        players = new Player[playerStrings.length];
        for (int i = 0; i < playerStrings.length; i++) {
            players[i] = new Player('r');
            players[i].initialiseFromString(playerStrings[i]);
        }
    }

    public boolean checkForWin() {
        return false;
    }
}
