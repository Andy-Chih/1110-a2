package comp1110.ass2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;
public class Triangle extends Polygon {
    double x;
    double y;
    double side;
    final int WIDTH = 600;
    final int HEIGHT = 519;
    public Triangle(double x, double y, double side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }
    public Polygon makeTriangle() {
        double h = (Math.sqrt((Math.pow(side, 2)) - (Math.pow((side/2), 2))))/2;
        double point1X = -(side/2);
        double point1Y = h;
        double point2X = (side/2);
        double point2Y = h;
        double point3X = 0.0;
        double point3Y = -h;

        Polygon t = new Polygon();
        t.getPoints().addAll(point1X, point1Y, point2X, point2Y, point3X, point3Y);
        t.setFill(Color.BLACK);
        t.setLayoutX(60.0 * (this.x + 1) + 140.0);
        t.setLayoutY(60.0 * (this.y + 1) + (h/2) + 50.0);
        return t;
    }
}
