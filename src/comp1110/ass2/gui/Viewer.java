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
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state a string representing the current game state
     */
    void displayState(String state) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.updateFromString(state);
        AllPlayers players = new AllPlayers();
        players.initialiseFromStringAll(gameInfo.playerStrings);
        Board board = new Board();
        board.initialiseFromString(gameInfo.abvRugStrings);
        Assam assam = new Assam();
        assam.initialiseFromString(gameInfo.assamString);

        //try to display all the players' state
        for(int i = 0; i < players.players.length; i++){
            Text text = new Text();
            text.setX(700);
            text.setY((i+1)*100);
            String statusText;
            if(players.players[i].status){
                statusText = "playing";
            }else {
                statusText = "eliminated";
            }
            text.setText("Player " + (i+1) + ":" + "\n" + "color: " + players.players[i].colour + "\n" + "dirhams: " + players.players[i].dirhams + "\n" + "rugs: " + players.players[i].rugs + "\n" + "status: " + statusText);
            root.getChildren().add(text);
        }

        //display the Assam's state, seems need to draw the Assam on the board?
        Text text = new Text();
        text.setX(900);
        text.setY(50);
        text.setText("Assam position: (" + assam.position.x + ", " + assam.position.y + ")\n" + "Assam direction: " + assam.direction);
        root.getChildren().add(text);

        //display the Board's state, but I am a little confused by the Tile, RugsOnBoard and Rug class.
        for (int i = 0; i < board.squares.length; i++) {
            for (int j = 0; j < board.squares[i].length; j++) {
                root.getChildren().add(board.squares[i][j]);
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

        root.getChildren().add(t);

        // FIXME Task 5: implement the simple state viewer
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Game State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(800);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Marrakech Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
//        Image image = new Image("file:assets/Board Image.png", 1100, 600, true, false);
//        ImageView imageView = new ImageView(image);
//        root.getChildren().add(imageView);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
