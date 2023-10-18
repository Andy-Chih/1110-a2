package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class RugFromStringTest {

    Rug rug;
    Rug testRug = new Rug();

    @BeforeEach
    void setUp() {
        rug.initialiseFromString("r131112");
        testRug.colour = 'r';
        testRug.ID = 13;
        testRug.tile1Pos = new IntPair(1, 1);
        testRug.tile2Pos = new IntPair(1, 2);
    }
    @Test
    @DisplayName("The rug's colour is correctly retrieved")
    void testRugColour() {
        assertEquals(testRug.colour, rug.colour, "Colour should be correctly retrieved");
    }
    @Test
    @DisplayName("The rug's ID is correctly retrieved")
    void testRugID() {
        assertEquals(testRug.ID, rug.ID, "ID should be correctly retrieved");
    }
    @Test
    @DisplayName("The rug's tile positions should be correctly retrieved")
    void testRugPos() {
        assertEquals(testRug.tile1Pos.x, rug.tile1Pos.x, "Tile 1 x position incorrectly retrieved");
        assertEquals(testRug.tile1Pos.y, rug.tile1Pos.y, "Tile 1 y position incorrectly retrieved");
        assertEquals(testRug.tile2Pos.x, rug.tile2Pos.x, "Tile 2 x position incorrectly retrieved");
        assertEquals(testRug.tile2Pos.y, rug.tile2Pos.y, "Tile 2 y position incorrectly retrieved");
    }
}
