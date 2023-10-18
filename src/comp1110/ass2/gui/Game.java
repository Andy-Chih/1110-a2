package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class Game extends Application {

    private final Group root = new Group();
    private final Group boardGroup = new Group();
    private final Group controls = new Group();
    private final Group info = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;
    private String currentGame;
    private int playerAmount = 4;
    private Rug tempRug = new Rug();
    private Rectangle tempRugRect = new Rectangle();
    private GameInfo gameInfo = new GameInfo();
    private static AllPlayers players = new AllPlayers();
    private Board board = new Board();
    private Assam assam = new Assam();
    private RugsOnBoard rugs = new RugsOnBoard();
    private static int turn = 0;
    private boolean canUseAssamControls = true;
    private boolean canUseRugControls = false;
    private int assamDirectionHolder = 1;
    private int fullTurnTracker = 0;

    private void makeFirstScreen() {
        Label choiceLabel = new Label("How many players are playing?");
        choiceLabel.setLayoutX(WINDOW_WIDTH/2 - choiceLabel.getWidth());
        choiceLabel.setLayoutY(WINDOW_HEIGHT/2);
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        tempRug.tile1Pos.x = 0;
        tempRug.tile1Pos.y = 0;
        tempRug.tile2Pos.x = 1;
        tempRug.tile2Pos.y = 0;
        two.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentGame = "Pc03015iPy03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
                playerAmount = 2;
                root.getChildren().clear();
                makeControls();
            }
        });
        three.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentGame = "Pc03015iPy03015iPr03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
                playerAmount = 3;
                root.getChildren().clear();
                makeControls();
            }
        });
        four.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentGame = "Pc03015iPy03015iPr03015iPp03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
                playerAmount = 4;
                root.getChildren().clear();
                makeControls();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(two, three, four);
        hb.setSpacing(10);
        hb.setLayoutX(WINDOW_WIDTH/2 - hb.getWidth());
        hb.setLayoutY(WINDOW_HEIGHT/2 + 50);
        root.getChildren().addAll(choiceLabel, hb);
    }

    private void makeControls() {
        Button rotateAssamLeft = new Button("Rotate Left");
        Button rotateAssamRight = new Button("Rotate Right");
        Button rollDie = new Button("Roll Die");
        tempRugRect.setFill(Color.CYAN);
        updateRectangle();
        rotateAssamLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseAssamControls) {
                    if (assam.direction == 1 && assamDirectionHolder != 2) {
                        assam.direction = 4;
                    }
                    else if (assam.direction == 2 && assamDirectionHolder != 3) {
                        assam.direction = 1;
                    }
                    else if (assam.direction == 3 && assamDirectionHolder != 4) {
                        assam.direction = 2;
                    }
                    else if (assam.direction == 4 && assamDirectionHolder != 1) {
                        assam.direction = 3;
                    }
                    updateGameString(players, assam, rugs);
                    displayState(currentGame);
                }
            }
        });
        rotateAssamRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseAssamControls) {
                    if (assam.direction == 4 && assamDirectionHolder != 3) {
                        assam.direction = 1;
                    }
                    else if (assam.direction == 1 && assamDirectionHolder != 4) {
                        assam.direction = 2;
                    }
                    else if (assam.direction == 2 && assamDirectionHolder != 1) {
                        assam.direction = 3;
                    }
                    else if (assam.direction == 3 && assamDirectionHolder != 2) {
                        assam.direction = 4;
                    }
                    updateGameString(players, assam, rugs);
                    displayState(currentGame);
                }
            }
        });
        rollDie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseAssamControls) {
                    Random rand = new Random();
                    int int_random = rand.nextInt(6) + 1;
                    int dieResult = 0;
                    if (int_random == 1) {
                        dieResult = 1;
                    }
                    else if (int_random == 2 || int_random == 3) {
                        dieResult = 2;
                    }
                    else if (int_random == 4 || int_random == 5) {
                        dieResult = 3;
                    }
                    else {
                        dieResult = 4;
                    }

                    int theoreticalNewPos;
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
                            assam.position.y = theoreticalNewPos;
                        }
                        else if (assam.direction == 2) {
                            assam.position.x = theoreticalNewPos;
                        }
                        else if (assam.direction == 3) {
                            assam.position.y = theoreticalNewPos;
                        }
                        else {
                            assam.position.x = theoreticalNewPos;
                        }
                    }
                    else {
                        if (assam.direction == 1) {
                            if (assam.position.x == 6) {
                                assam.position.x = 7 + theoreticalNewPos;
                                assam.position.y = 0;
                                assam.direction = 4;
                            }
                            else if (assam.position.x % 2 == 0) {
                                assam.position.x = assam.position.x + 1;
                                assam.position.y = (-assam.position.y) - 1;
                                assam.direction = 3;
                            }
                            else {
                                assam.position.x = assam.position.x - 1;
                                assam.position.y = (-theoreticalNewPos) - 1;
                                assam.direction = 3;
                            }
                        }
                        else if (assam.direction == 2) {
                            if (assam.position.y == 0) {
                                assam.position.x = 6;
                                assam.position.y = theoreticalNewPos - 7;
                                assam.direction = 3;
                            }
                            else if (assam.position.y % 2 == 0) {
                                assam.position.x = (7 - (theoreticalNewPos - 6));
                                assam.position.y = assam.position.y - 1;
                                assam.direction = 4;
                            }
                            else {
                                assam.position.x = (7 - (theoreticalNewPos - 6));
                                assam.position.y = assam.position.y + 1;
                                assam.direction = 4;
                            }
                        }
                        else if (assam.direction == 3) {
                            if (assam.position.x == 0) {
                                assam.position.x = theoreticalNewPos - 7;
                                assam.position.y = 6;
                                assam.direction = 2;
                            }
                            else if (assam.position.x % 2 == 0) {
                                assam.position.x = assam.position.x - 1;
                                assam.position.y = 7 - (theoreticalNewPos - 6);
                                assam.direction = 1;
                            }
                            else {
                                assam.position.x = assam.position.x + 1;
                                assam.position.y = 7 - (theoreticalNewPos - 6);
                                assam.direction = 1;
                            }
                        }
                        else {
                            if (assam.position.y == 6) {
                                assam.position.x = 0;
                                assam.position.y = 7 + theoreticalNewPos;
                                assam.direction = 1;
                            }
                            else if (assam.position.y % 2 == 0) {
                                assam.position.x = (-theoreticalNewPos) - 1;
                                assam.position.y = assam.position.y + 1;
                                assam.direction = 2;
                            }
                            else {
                                assam.position.x = (-theoreticalNewPos - 1);
                                assam.position.y = assam.position.y - 1;
                                assam.direction = 2;
                            }
                        }
                    }
                    updateGameString(players, assam, rugs);
                    makePayment(currentGame);
                    updateGameString(players, assam, rugs);
                    displayState(currentGame);
                    updateRectangle();
                    canUseRugControls = true;
                    canUseAssamControls = false;
                    assamDirectionHolder = assam.direction;
                }
            }
        });
        HBox hbAssamControls = new HBox();
        hbAssamControls.getChildren().addAll(rotateAssamLeft, rotateAssamRight);
        VBox vbAssamControls = new VBox();
        vbAssamControls.getChildren().addAll(new Label("Assam Controls"), hbAssamControls, rollDie);
        vbAssamControls.setLayoutX(WINDOW_WIDTH - 500);
        vbAssamControls.setLayoutY(100);

        Button rugUp = new Button("^");
        Button rugLeft = new Button("<");
        Button rugDown = new Button("down");
        Button rugRight = new Button(">");
        Button rotateRug = new Button("Rotate");
        Button placeRug = new Button("Place");
        rugUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseRugControls) {
                    if (tempRug.tile1Pos.y > 0) {
                        tempRug.tile1Pos.y = tempRug.tile1Pos.y - 1;
                        tempRug.tile2Pos.y = tempRug.tile2Pos.y - 1;
                        updateRectangle();
                    }
                }
            }
        });
        rugLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseRugControls) {
                    if (tempRug.tile1Pos.x > 0) {
                        tempRug.tile1Pos.x = tempRug.tile1Pos.x - 1;
                        tempRug.tile2Pos.x = tempRug.tile2Pos.x - 1;
                        updateRectangle();
                    }
                }
            }
        });
        rugDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseRugControls) {
                    if (tempRug.tile2Pos.y < 6) {
                        tempRug.tile1Pos.y = tempRug.tile1Pos.y + 1;
                        tempRug.tile2Pos.y = tempRug.tile2Pos.y + 1;
                        updateRectangle();
                    }
                }
            }
        });
        rugRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseRugControls) {
                    if (tempRug.tile2Pos.x < 6) {
                        tempRug.tile1Pos.x = tempRug.tile1Pos.x + 1;
                        tempRug.tile2Pos.x = tempRug.tile2Pos.x + 1;
                        updateRectangle();
                    }
                }
            }
        });
        rotateRug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (canUseRugControls) {
                    if (tempRug.tile1Pos.y < tempRug.tile2Pos.y && tempRug.tile2Pos.x != 6) {
                        tempRug.tile2Pos.y = tempRug.tile1Pos.y;
                        tempRug.tile2Pos.x = tempRug.tile1Pos.x + 1;
                    }
                    else if (tempRug.tile1Pos.x < tempRug.tile2Pos.x && tempRug.tile2Pos.y != 6) {
                        tempRug.tile2Pos.y = tempRug.tile1Pos.y + 1;
                        tempRug.tile2Pos.x = tempRug.tile1Pos.x;
                    }
                    updateRectangle();
                }
            }
        });
        placeRug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                boolean validLocation = false;

                IntPair[] validPlaces = {(new IntPair(assam.position.x, assam.position.y - 1)),
                        (new IntPair(assam.position.x + 1, assam.position.y)),
                        (new IntPair(assam.position.x, assam.position.y + 1)),
                        (new IntPair(assam.position.x - 1, assam.position.y))};

                for (int i = 0; i < validPlaces.length; i++) {
                    if ((validPlaces[i].x == tempRug.tile1Pos.x) && (validPlaces[i].y == tempRug.tile1Pos.y) ||
                            ((validPlaces[i].x == tempRug.tile2Pos.x) && (validPlaces[i].y == tempRug.tile2Pos.y))) {
                        validLocation = true;
                    }
                }

                if ((tempRug.tile1Pos.x < 0 ||
                        tempRug.tile1Pos.y < 0 ||
                        tempRug.tile2Pos.x < 0 ||
                        tempRug.tile2Pos.y < 0 ||
                        tempRug.tile1Pos.x > 6 ||
                        tempRug.tile1Pos.y > 6 ||
                        tempRug.tile2Pos.x > 6 ||
                        tempRug.tile2Pos.y > 6) ||
                        (((tempRug.tile1Pos.x == assam.position.x) &&
                                (tempRug.tile1Pos.y == assam.position.y)) ||
                                ((tempRug.tile2Pos.x == assam.position.x) &&
                                        (tempRug.tile2Pos.y == assam.position.y)))) {
                    validLocation = false;
                }

                RugAbreviated tile1Rug = new RugAbreviated();
                tile1Rug.initialiseFromString(gameInfo.abvRugStrings[(tempRug.tile1Pos.x * 7) + (tempRug.tile1Pos.y)]);
                RugAbreviated tile2Rug = new RugAbreviated();
                tile2Rug.initialiseFromString(gameInfo.abvRugStrings[(tempRug.tile2Pos.x * 7) + (tempRug.tile2Pos.y)]);

                if ((tile1Rug.ID == tile2Rug.ID) && (tile1Rug.colour == tile2Rug.colour) && (tile1Rug.colour != 'n')) {
                    validLocation = false;
                }
                tempRug.ID = fullTurnTracker;
                if (validLocation) {
                    if (turn == 0) {
                        tempRug.colour = 'c';
                        tempRugRect.setFill(Color.YELLOW);
                    }
                    else if (turn == 1) {
                        tempRug.colour = 'y';
                        tempRugRect.setFill(Color.RED);
                    }
                    else if (turn == 2) {
                        tempRug.colour = 'r';
                        tempRugRect.setFill(Color.PURPLE);
                    }
                    else {
                        tempRug.colour = 'p';
                        tempRugRect.setFill(Color.CYAN);
                        fullTurnTracker++;
                    }
                    rugs.rugs[tempRug.tile1Pos.y + tempRug.tile1Pos.x * 7].ID = tempRug.ID;
                    rugs.rugs[tempRug.tile1Pos.y + tempRug.tile1Pos.x * 7].colour = tempRug.colour;
                    rugs.rugs[tempRug.tile2Pos.y + tempRug.tile2Pos.x * 7].ID = tempRug.ID;
                    rugs.rugs[tempRug.tile2Pos.y + tempRug.tile2Pos.x * 7].colour = tempRug.colour;
                    players.players[turn].rugs = players.players[turn].rugs - 1;
                    if (turn == playerAmount - 1) {
                        turn = 0;
                    }
                    else {
                        turn++;
                    }
                    updateGameString(players, assam, rugs);
                    displayState(currentGame);
                    canUseAssamControls = true;
                    canUseRugControls = false;
                }
            }
        });
        HBox hbRugControls = new HBox();
        hbRugControls.getChildren().addAll(rugLeft, rugDown, rugRight);
        VBox vbRugControls = new VBox();
        vbRugControls.getChildren().addAll(new Label("Rug Controls"), rugUp, hbRugControls, rotateRug, placeRug);
        vbRugControls.setLayoutX(WINDOW_WIDTH - 500);
        vbRugControls.setLayoutY(300);
        root.getChildren().addAll(vbAssamControls, vbRugControls);
        displayState(currentGame);
    }

    private void displayState(String state) {
        root.getChildren().remove(boardGroup);
        gameInfo.updateFromString(state);
        players.initialiseFromStringAll(gameInfo.playerStrings);
        board.initialiseFromString(gameInfo.abvRugStrings);
        assam.initialiseFromString(gameInfo.assamString);
        rugs.initialiseFromString(gameInfo.abvRugStrings);

        for (int i = 0; i < board.squares.length; i++) {
            for (int j = 0; j < board.squares[i].length; j++) {
                boardGroup.getChildren().add(board.squares[i][j]);
            }
        }
        Triangle assamPiece = new Triangle(assam.position.x, assam.position.y, 60.0);
        Polygon t = new Polygon();
        t = assamPiece.makeTriangle();
        if (assam.direction == 2) {
            t.setRotate(90);
        } else if (assam.direction == 3) {
            t.setRotate(180);
        } else if (assam.direction == 4) {
            t.setRotate(270);
        }

        boardGroup.getChildren().add(t);
        root.getChildren().add(boardGroup);
        root.getChildren().remove(info);
        info.getChildren().clear();

        Label[] lblPlayersInfo = new Label[playerAmount];
        VBox vbInfo = new VBox();
        for (int i = 0; i < playerAmount; i++) {
            if (i == 0) {
                lblPlayersInfo[i] = new Label("Cyan");
            }
            else if (i == 1) {
                lblPlayersInfo[i] = new Label("Yellow");
            }
            else if (i == 2) {
                lblPlayersInfo[i] = new Label("Red");
            }
            else {
                lblPlayersInfo[i] = new Label("Purple");
            }
            HBox hbDirhams = new HBox();
            hbDirhams.getChildren().addAll(new Label("Dirhams: "), new Label(String.valueOf(players.players[i].dirhams)));
            HBox hbRugs = new HBox();
            hbRugs.getChildren().addAll(new Label("Rugs: "), new Label(String.valueOf(players.players[i].rugs)));
            HBox hbStatus = new HBox();
            if (players.players[i].status == true) {
                hbStatus.getChildren().addAll(new Label("Status: "), new Label("playing"));
            }
            else {
                hbStatus.getChildren().addAll(new Label("Status: "), new Label("eliminated"));
            }
            vbInfo.getChildren().addAll(lblPlayersInfo[i], hbDirhams, hbRugs, hbStatus);
        }
        vbInfo.setSpacing(10);
        vbInfo.setLayoutX(50);
        vbInfo.setLayoutY(100);
        info.getChildren().add(vbInfo);
        root.getChildren().add(info);
    }

    private void updateRectangle() {
        root.getChildren().remove(tempRugRect);
        if (tempRug.tile1Pos.x < tempRug.tile2Pos.x) {
            tempRugRect.setWidth(120);
            tempRugRect.setHeight(60);
            tempRugRect.setLayoutX(tempRug.tile1Pos.x * 60.0 + 170.0);
            tempRugRect.setLayoutY(tempRug.tile1Pos.y * 60.0 + 92.0);
        }
        else {
            tempRugRect.setWidth(60);
            tempRugRect.setHeight(120);
            tempRugRect.setLayoutX(tempRug.tile1Pos.x * 60.0 + 170.0);
            tempRugRect.setLayoutY(tempRug.tile1Pos.y * 60.0 + 92.0);
        }
        root.getChildren().add(tempRugRect);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 7 and 15
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        makeFirstScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void updateGameString(AllPlayers players, Assam assam, RugsOnBoard rugs) {
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
        currentGame = new String(gameStateChars);
    }
    public static void makePayment(String gameString) {
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
        if (currentColor == 'c') {
            players.players[0].dirhams = players.players[0].dirhams + count;
            players.players[turn].dirhams = players.players[turn].dirhams - count;
        }
        else if (currentColor == 'y') {
            players.players[1].dirhams = players.players[1].dirhams + count;
            players.players[turn].dirhams = players.players[turn].dirhams - count;
        }
        else if (currentColor == 'r') {
            players.players[2].dirhams = players.players[2].dirhams + count;
            players.players[turn].dirhams = players.players[turn].dirhams - count;
        }
        else if (currentColor == 'p') {
            players.players[3].dirhams = players.players[3].dirhams + count;
            players.players[turn].dirhams = players.players[turn].dirhams - count;
        }
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
}
