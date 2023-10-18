package comp1110.ass2;

import gittest.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Marrakech {

    public Player[] players;
    public Board board;
    public RugsOnBoard rugsOnBoard;
    public Assam assam;


     /**
     * Determine whether a rug String is valid.
     * For this method, you need to determine whether the rug String is valid, but do not need to determine whether it
     * can be placed on the board (you will determine that in Task 10 ). A rug is valid if and only if all the following
     * conditions apply:
     *  - The String is 7 characters long
     *  - The first character in the String corresponds to the colour character of a player present in the game
     *  - The next two characters represent a 2-digit ID number
     *  - The next 4 characters represent coordinates that are on the board
     *  - The combination of that ID number and colour is unique
     * To clarify this last point, if a rug has the same ID as a rug on the board, but a different colour to that rug,
     * then it may still be valid. Obviously multiple rugs are allowed to have the same colour as well so long as they
     * do not share an ID. So, if we already have the rug c013343 on the board, then we can have the following rugs
     *  - c023343 (Shares the colour but not the ID)
     *  - y013343 (Shares the ID but not the colour)
     * But you cannot have c014445, because this has the same colour and ID as a rug on the board already.
     * @param gameString A String representing the current state of the game as per the README
     * @param rug A String representing the rug you are checking
     * @return true if the rug is valid, and false otherwise.
     */
    public static boolean isRugValid(String gameString, String rug) {

        //check if the length is 7
        if (rug.length() != 7) {
            return false;
        }

        //check if the first character is color existed (c,y,r,p)
        char color = rug.charAt(0);
        String c = Character.toString(color);
        if(!(c.equals("c") || c.equals("y") || c.equals("r") || c.equals("p"))){
            return false;
        }
        if(!gameString.contains(c)){
            return false;
        }

        //check if the ID is digital character
        char second = rug.charAt(1);
        char third = rug.charAt(2);
        if(!Character.isDigit(second) || !Character.isDigit(third)){
            return false;
        }

        //check if the last 4 characters represent coordinates that are on the board
        /*String pos1 = rug.substring(3,5);
        String pos2 = rug.substring(5);*/
        int pos1x = Character.getNumericValue(rug.charAt(3));
        int pos1y = Character.getNumericValue(rug.charAt(4));
        int pos2x = Character.getNumericValue(rug.charAt(5));
        int pos2y = Character.getNumericValue(rug.charAt(6));

        if(pos1x>6 || pos1y>6 || pos2x>6 || pos2y>6){
            return false;
        }

        //rug must be connected & 2*1
        if(pos1x != pos2x && pos1y != pos2y){
            return false;
        }
        if(pos1x == pos2x){
            if(Math.abs(pos1y-pos2y)!=1){
                return false;
            }
        }else if(pos1y == pos2y){
            if(Math.abs(pos1x-pos2x)!=1){
                return false;
            }
        }

        //check if the ID is unique
        String id = rug.substring(0,3);
        int index = 0;
        for(int i=0;i<gameString.length();i++){
            if(Character.toString(gameString.charAt(i)).equals("B")){
                index = i;
                break;
            }
        }
        String gameBoardString = gameString.substring(index);
        if(gameBoardString.contains(id)){
            return false;
        }

        // FIXME: Task 4 - completed by Andy
        return true;
    }

    /**
     * Roll the special Marrakech die and return the result.
     * Note that the die in Marrakech is not a regular 6-sided die, since there
     * are no faces that show 5 or 6, and instead 2 faces that show 2 and 3. That
     * is, of the 6 faces
     *  - One shows 1
     *  - Two show 2
     *  - Two show 3
     *  - One shows 4
     * As such, in order to get full marks for this task, you will need to implement
     * a die where the distribution of results from 1 to 4 is not even, with a 2 or 3
     * being twice as likely to be returned as a 1 or 4.
     * @return The result of the roll of the die meeting the criteria above
     */
    public static int rollDie() {
        Random rand = new Random();
        int int_random = rand.nextInt(6) + 1;
        int result = 0;
        if (int_random == 1) {
            result = 1;
        }
        else if (int_random == 2 || int_random == 3) {
            result = 2;
        }
        else if (int_random == 4 || int_random == 5) {
            result = 3;
        }
        else {
            result = 4;
        }
        return result;
        // FIXME: Task 6 - completed by Flynn
    }

    /**
     * Determine whether a game of Marrakech is over
     * Recall from the README that a game of Marrakech is over if a Player is about to enter the rotation phase of their
     * turn, but no longer has any rugs. Note that we do not encode in the game state String whose turn it is, so you
     * will have to think about how to use the information we do encode to determine whether a game is over or not.
     * @param currentGame A String representation of the current state of the game.
     * @return true if the game is over, or false otherwise.
     */
    public static boolean isGameOver(String currentGame) {
        // FIXME: Task 8
        char[] gameChar = currentGame.toCharArray();
        int playerNum = 0;
        ArrayList<Integer> inGamePlayerRugs = new ArrayList<Integer>();
        for (int i =0; i< gameChar.length; i++){
            if (gameChar[i] == 'P' && gameChar[i+7] == 'i'){
                playerNum++;
                String playerStatus = new String(gameChar, i, 8);
                int rugDigit1 = Character.getNumericValue(playerStatus.charAt(5));
                int rugDigit2 = Character.getNumericValue(playerStatus.charAt(6));
                int rug = rugDigit1*10 + rugDigit2;
                inGamePlayerRugs.add(rug);
            }
        }
        for (Integer num : inGamePlayerRugs) {
            if (num != 0) {
                return false;
            }
        }
        return true;


    }

    /**
     * Implement Assam's rotation.
     * Recall that Assam may only be rotated left or right, or left alone -- he cannot be rotated a full 180 degrees.
     * For example, if he is currently facing North (towards the top of the board), then he could be rotated to face
     * East or West, but not South. Assam can also only be rotated in 90 degree increments.
     * If the requested rotation is illegal, you should return Assam's current state unchanged.
     * @param currentAssam A String representing Assam's current state
     * @param rotation The requested rotation, in degrees. This degree reading is relative to the direction Assam
     *                 is currently facing, so a value of 0 for this argument will keep Assam facing in his
     *                 current orientation, 90 would be turning him to the right, etc.
     * @return A String representing Assam's state after the rotation, or the input currentAssam if the requested
     * rotation is illegal.
     */
    public static String rotateAssam(String currentAssam, int rotation) {
        // FIXME: Task 9
        char[] assamChar = currentAssam.toCharArray();
        char[] direction = {'N', 'E', 'S', 'W'};
        int directionIndex = 0;
        for(int i = 0; i < 4; i++){
            if (direction[i] == assamChar[3]){
                directionIndex = i;
            }
        }
        if (rotation%360 == 0){
            return currentAssam;
        }else if (rotation%360 == 90) {
            directionIndex = (directionIndex+1)%4;
            assamChar[3] = direction[directionIndex];
        }else if (rotation%360 == 270){
            directionIndex = (directionIndex+3)%4;
            assamChar[3] = direction[directionIndex];
        }else {
            return currentAssam;
        }
        String newAssam = new String(assamChar);

        return newAssam;
    }

    /**
     * Determine whether a potential new placement is valid (i.e that it describes a legal way to place a rug).
     * There are a number of rules which apply to potential new placements, which are detailed in the README but to
     * reiterate here:
     *   1. A new rug must have one edge adjacent to Assam (not counting diagonals)
     *   2. A new rug must not completely cover another rug. It is legal to partially cover an already placed rug, but
     *      the new rug must not cover the entirety of another rug that's already on the board.
     * @param gameState A game string representing the current state of the game
     * @param rug A rug string representing the candidate rug which you must check the validity of.
     * @return true if the placement is valid, and false otherwise.
     */
    public static boolean isPlacementValid(String gameState, String rug) {

        boolean validLocation = false;

        GameInfo game = new GameInfo();
        game.updateFromString(gameState);
        RugsOnBoard rugs = new RugsOnBoard();
        rugs.initialiseFromString(game.abvRugStrings);
        Rug rugToPlace = new Rug();
        rugToPlace.initialiseFromString(rug);
        Assam assam = new Assam();
        assam.initialiseFromString(game.assamString);

        if ((rugToPlace.tile1Pos.x < 0 ||
                rugToPlace.tile1Pos.y < 0 ||
                rugToPlace.tile2Pos.x < 0 ||
                rugToPlace.tile2Pos.y < 0 ||
                rugToPlace.tile1Pos.x > 6 ||
                rugToPlace.tile1Pos.y > 6 ||
                rugToPlace.tile2Pos.x > 6 ||
                rugToPlace.tile2Pos.y > 6) ||
                (((rugToPlace.tile1Pos.x == assam.position.x) &&
                        (rugToPlace.tile1Pos.y == assam.position.y)) ||
                        ((rugToPlace.tile2Pos.x == assam.position.x) &&
                                (rugToPlace.tile2Pos.y == assam.position.y)))) {
            return false;
        }

        IntPair[] validPlaces = {(new IntPair(assam.position.x, assam.position.y - 1)),
                (new IntPair(assam.position.x + 1, assam.position.y)),
                (new IntPair(assam.position.x, assam.position.y + 1)),
                (new IntPair(assam.position.x - 1, assam.position.y))};

        for (int i = 0; i < validPlaces.length; i++) {
            if ((validPlaces[i].x == rugToPlace.tile1Pos.x) && (validPlaces[i].y == rugToPlace.tile1Pos.y) ||
                    ((validPlaces[i].x == rugToPlace.tile2Pos.x) && (validPlaces[i].y == rugToPlace.tile2Pos.y))) {
                validLocation = true;
            }
        }

        RugAbreviated tile1Rug = new RugAbreviated();
        tile1Rug.initialiseFromString(game.abvRugStrings[(rugToPlace.tile1Pos.x * 7) + (rugToPlace.tile1Pos.y)]);
        RugAbreviated tile2Rug = new RugAbreviated();
        tile2Rug.initialiseFromString(game.abvRugStrings[(rugToPlace.tile2Pos.x * 7) + (rugToPlace.tile2Pos.y)]);

        if ((tile1Rug.ID == tile2Rug.ID) && (tile1Rug.colour == tile2Rug.colour) && (tile1Rug.colour != 'n')) {
            validLocation = false;
        }

        return validLocation;
    }

    /**
     * Determine the amount of payment required should another player land on a square.
     * For this method, you may assume that Assam has just landed on the square he is currently placed on, and that
     * the player who last moved Assam is not the player who owns the rug landed on (if there is a rug on his current
     * square). Recall that the payment owed to the owner of the rug is equal to the number of connected squares showing
     * on the board that are of that colour. Similarly to the placement rules, two squares are only connected if they
     * share an entire edge -- diagonals do not count.
     * @param gameString A String representation of the current state of the game.
     * @return The amount of payment due, as an integer.
     */
    public static int getPaymentAmount(String gameString) {
        // FIXME: Task 11

        //find the location of the player
        int startIdx1 = gameString.indexOf("A")+1;
        String location = gameString.substring(startIdx1,startIdx1+2);
        int x = location.charAt(0) - '0';
        int y = location.charAt(1) - '0';

        //turn gameString into array
        int startIdx2 = gameString.indexOf("B") + 1;
        String[][] board = new String[7][7];
        for(int i = 0 ; i < 7 ; i++) {
            for(int j = 0; j<7 ;j++){
                board[i][j] = gameString.substring(startIdx2,startIdx2+3);
                startIdx2 += 3;
            }
        }

        //find the current rug
        String current_rug = board[x][y];
        char currentColor = current_rug.charAt(0);

        boolean[][] visited = new boolean[7][7];
        int count = checkSurroundings(board, x, y, currentColor,visited);
        return count;
    }

    public static int checkSurroundings(String[][] board, int x, int y, char color, boolean[][] visited){
        if (x < 0 || x >= 7 || y < 0 || y >= 7 || visited[x][y] || board[x][y].charAt(0) != color) {
            return 0;
        }

        visited[x][y] = true;

        int count = 1;
        count += checkSurroundings(board, x-1, y, color, visited);
        count += checkSurroundings(board, x+1, y, color, visited);
        count += checkSurroundings(board, x, y-1, color, visited);
        count += checkSurroundings(board, x, y+1, color, visited);

        return count;
    }


    /**
     * Determine the winner of a game of Marrakech.
     * For this task, you will be provided with a game state string and have to return a char representing the colour
     * of the winner of the game. So for example if the cyan player is the winner, then you return 'c', if the red
     * player is the winner return 'r', etc...
     * If the game is not yet over, then you should return 'n'.
     * If the game is over, but is a tie, then you should return 't'.
     * Recall that a player's total score is the sum of their number of dirhams and the number of squares showing on the
     * board that are of their colour, and that a player who is out of the game cannot win. If multiple players have the
     * same total score, the player with the largest number of dirhams wins. If multiple players have the same total
     * score and number of dirhams, then the game is a tie.
     * @param gameState A String representation of the current state of the game
     * @return A char representing the winner of the game as described above.
     */
    public static char getWinner(String gameState) {
        // FIXME: Task 12
        //get players' Strings
        int endIdx = gameState.indexOf("A");
        int playerNum = endIdx/8;
        String[] player = new String[playerNum];
        HashMap<String, Integer> map= new HashMap<>();
        for(int i = 0; i < playerNum; i++){
            player[i] = gameState.substring(8*i , 8+8*i);
            if(player[i].charAt(7) == 'i'){
                map.put(player[i],0);
            }
        }

        //turn gameString into array
        int startIdx2 = gameState.indexOf("B") + 1;
        String[][] board = new String[7][7];
        for(int i = 0 ; i < 7 ; i++) {
            for(int j = 0; j<7 ;j++){
                board[i][j] = gameState.substring(startIdx2,startIdx2+3);
                startIdx2 += 3;
            }
        }
        for(String p : map.keySet()){
            int dirham = Integer.parseInt(p.substring(2,5));
            int rug = 0;
            for(int i = 0 ; i < 7 ; i++) {
                for(int j = 0; j<7 ;j++){
                    if(board[i][j].charAt(0) == (p.charAt(1))){
                        rug++;
                    }
                }
            }
            map.put(p,dirham+rug);
        }

        char winner = 'n';
        int highestScore = -1;
        int highestDirhams = -1;
        boolean isTie = false;

        for (String p : map.keySet()) {
            int currentScore = map.get(p);
            int currentDirhams = Integer.parseInt(p.substring(2, 5));

            if (currentScore > highestScore) {
                highestScore = currentScore;
                highestDirhams = currentDirhams;
                winner = p.charAt(1);
                isTie = false;
            } else if (currentScore == highestScore) {
                if (currentDirhams > highestDirhams) {
                    highestDirhams = currentDirhams;
                    winner = p.charAt(1);
                    isTie = false;
                } else if (currentDirhams == highestDirhams) {
                    isTie = true;
                }
            }
        }

        if (isTie) {
            return 't';
        }
        return winner;
    }

    /**
     * Implement Assam's movement.
     * Assam moves a number of squares equal to the die result, provided to you by the argument dieResult. Assam moves
     * in the direction he is currently facing. If part of Assam's movement results in him leaving the board, he moves
     * according to the tracks diagrammed in the assignment README, which should be studied carefully before attempting
     * this task. For this task, you are not required to do any checking that the die result is sensible, nor whether
     * the current Assam string is sensible either -- you may assume that both of these are valid.
     * @param currentAssam A string representation of Assam's current state.
     * @param dieResult The result of the die, which determines the number of squares Assam will move.
     * @return A String representing Assam's state after the movement.
     */
    public static String moveAssam(String currentAssam, int dieResult){
        // FIXME: Task 13
        Assam assam = new Assam();
        assam.initialiseFromString(currentAssam);
        int theoreticalNewPos = 0;
        if (assam.direction == 1) {
            theoreticalNewPos = assam.position.y - dieResult;
        }
        else if (assam.direction == 2) {
            theoreticalNewPos = assam.position.x + dieResult;
        }
        else if (assam.direction == 3) {
            theoreticalNewPos = assam.position.y + dieResult;
        }
        else {
            theoreticalNewPos = assam.position.x - dieResult;
        }
        if (theoreticalNewPos <= 6 && theoreticalNewPos >= 0) {
            if (assam.direction == 1) {
                return ("A" + assam.position.x + theoreticalNewPos + "N");
            }
            else if (assam.direction == 2) {
                return ("A" + theoreticalNewPos + assam.position.y + "E");
            }
            else if (assam.direction == 3) {
                return ("A" + assam.position.x + theoreticalNewPos + "S");
            }
            else {
                return ("A" + theoreticalNewPos + assam.position.y + "W");
            }
        }
        else {
            if (assam.direction == 1) {
                if (assam.position.x == 6) {
                    return ("A" + (7 + theoreticalNewPos) + "0W");
                }
                else if (assam.position.x % 2 == 0) {
                    return ("A" + (assam.position.x + 1) + ((-theoreticalNewPos) - 1) + "S");
                }
                else {
                    return ("A" + (assam.position.x - 1) + ((-theoreticalNewPos) - 1) + "S");
                }
            }
            else if (assam.direction == 2) {
                if (assam.position.y == 0) {
                    return ("A6" + (theoreticalNewPos - 7) + "S");
                }
                else if (assam.position.y % 2 == 0) {
                    return ("A" + (7 - (theoreticalNewPos - 6)) + (assam.position.y - 1) + "W");
                }
                else {
                    return ("A" + (7 - (theoreticalNewPos - 6)) + (assam.position.y + 1) + "W");
                }
            }
            else if (assam.direction == 3) {
                if (assam.position.x == 0) {
                    return ("A" + (theoreticalNewPos - 7) + "6E");
                }
                else if (assam.position.x % 2 == 0) {
                    return ("A" + (assam.position.x - 1) + (7 - (theoreticalNewPos - 6)) + "N");
                }
                else {
                    return ("A" + (assam.position.x + 1) + (7 - (theoreticalNewPos - 6)) + "N");
                }
            }
            else {
                if (assam.position.y == 6) {
                    return ("A0" + (7 + theoreticalNewPos) + "N");
                }
                else if (assam.position.y % 2 == 0) {
                    return ("A" + ((-theoreticalNewPos) - 1) + (assam.position.y + 1) + "E");
                }
                else {
                    return ("A" + ((-theoreticalNewPos) - 1) + (assam.position.y - 1) + "E");
                }
            }
        }
    }

    /**
     * Place a rug on the board
     * This method can be assumed to be called after Assam has been rotated and moved, i.e in the placement phase of
     * a turn. A rug may only be placed if it meets the conditions listed in the isPlacementValid task. If the rug
     * placement is valid, then you should return a new game string representing the board after the placement has
     * been completed. If the placement is invalid, then you should return the existing game unchanged.
     * @param currentGame A String representation of the current state of the game.
     * @param rug A String representation of the rug that is to be placed.
     * @return A new game string representing the game following the successful placement of this rug if it is valid,
     * or the input currentGame unchanged otherwise.
     */
    public static String makePlacement(String currentGame, String rug) {
        // FIXME: Task 14
        if (isPlacementValid(currentGame, rug) && isRugValid(currentGame, rug)) {

            Rug rugObj = new Rug();
            rugObj.initialiseFromString(rug);

            GameInfo gameInfo = new GameInfo();
            gameInfo.updateFromString(currentGame);
            AllPlayers players = new AllPlayers();
            players.initialiseFromStringAll(gameInfo.playerStrings);
            Board board = new Board();
            board.initialiseFromString(gameInfo.abvRugStrings);
            RugsOnBoard rugs = new RugsOnBoard();
            rugs.initialiseFromString(gameInfo.abvRugStrings);
            Assam assam = new Assam();
            assam.initialiseFromString(gameInfo.assamString);

            if (rugObj.colour == 'c') {
                players.players[0].rugs = players.players[0].rugs - 1;
            }
            else if (rugObj.colour == 'y') {
                players.players[1].rugs = players.players[1].rugs - 1;
            }
            else if (rugObj.colour == 'p') {
                players.players[2].rugs = players.players[2].rugs - 1;
            }
            else {
                players.players[3].rugs = players.players[3].rugs - 1;
            }

            rugs.rugs[rugObj.tile1Pos.y + rugObj.tile1Pos.x * 7].ID = rugObj.ID;
            rugs.rugs[rugObj.tile1Pos.y + rugObj.tile1Pos.x * 7].colour = rugObj.colour;
            rugs.rugs[rugObj.tile2Pos.y + rugObj.tile2Pos.x * 7].ID = rugObj.ID;
            rugs.rugs[rugObj.tile2Pos.y + rugObj.tile2Pos.x * 7].colour = rugObj.colour;

            char[] gameStateChars = new char[currentGame.length()];
            for (int i = 0; i < players.players.length; i ++) {
                gameStateChars[i * 8] = 'P';
                gameStateChars[i * 8 + 1] = players.players[i].colour;
                int tmp = players.players[i].dirhams/100;
                gameStateChars[i * 8 + 2] = String.valueOf(tmp).toCharArray()[0];
                tmp = (players.players[i].dirhams%100)/10;
                gameStateChars[i * 8 + 3] = String.valueOf(tmp).toCharArray()[0];
                tmp = (players.players[i].dirhams%10);
                gameStateChars[i * 8 + 4] = String.valueOf(tmp).toCharArray()[0];
                tmp = (players.players[i].rugs/10);
                gameStateChars[i * 8 + 5] = String.valueOf(tmp).toCharArray()[0];
                tmp = (players.players[i].rugs%10);
                gameStateChars[i * 8 + 6] = String.valueOf(tmp).toCharArray()[0];
                if (players.players[i].status) {
                    gameStateChars[i * 8 + 7] = 'i';
                }
                else {
                    gameStateChars[i * 8 + 7] = 'o';
                }
            }
            gameStateChars[(players.players.length) * 8] = 'A';
            gameStateChars[(players.players.length) * 8 + 1] = String.valueOf(assam.position.x).toCharArray()[0];
            gameStateChars[(players.players.length) * 8 + 2] = String.valueOf(assam.position.y).toCharArray()[0];
            if (assam.direction == 1) {
                gameStateChars[(players.players.length) * 8 + 3] = 'N';
            }
            else if (assam.direction == 2) {
                gameStateChars[(players.players.length) * 8 + 3] = 'E';
            }
            else if (assam.direction == 3) {
                gameStateChars[(players.players.length) * 8 + 3] = 'S';
            }
            else {
                gameStateChars[(players.players.length) * 8 + 3] = 'W';
            }
            gameStateChars[(players.players.length) * 8 + 4] = 'B';
            int current = (players.players.length) * 8 + 5;
            for (int i = 0; i < rugs.rugs.length; i++) {
                gameStateChars[current + i * 3] = rugs.rugs[i].colour;
                gameStateChars[current + i * 3 + 1] = (String.valueOf(rugs.rugs[i].ID/10)).toCharArray()[0];
                gameStateChars[current + i * 3 + 2] = (String.valueOf(rugs.rugs[i].ID%10)).toCharArray()[0];
            }
            return new String(gameStateChars);
        }
        else {
            return currentGame;
        }
    }

}
