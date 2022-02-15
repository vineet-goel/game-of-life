package org.game.app;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void board_whenMaxRowIsNegative_shouldThrowError() {
        assertThrows(AssertionError.class, () -> new Board(-2, 3));
    }

    @Test
    void board_whenMaxColIsNegative_shouldThrowError() {
        assertThrows(AssertionError.class, () -> new Board(2, -3));
    }

    @Test
    void board_whenBoardInitialized_shouldContainActiveCoordinatesSet() {
        Board board = new Board(5, 5);

        assertTrue(board.getActiveCoordinateSet().size() > 0);
    }

    @Test
    void getNeighbourCoordinates_whenCoordinateIsInMiddle_shouldReturnSurroundingCoordinates() {
        Set<Coordinate> expectedCoordinateSet = Set.of(new Coordinate(2, 2),
                new Coordinate(2, 3),
                new Coordinate(2, 4),
                new Coordinate(4, 2),
                new Coordinate(4, 3),
                new Coordinate(4, 4),
                new Coordinate(3, 2),
                new Coordinate(3, 4));

        Board board = new Board(5, 5);

        Set<Coordinate> neighbourCoordinateSet = board.getNeighbourCoordinates(new Coordinate(3, 3));

        assertEquals(expectedCoordinateSet, neighbourCoordinateSet);
    }

    @Test
    void getNeighbourCoordinates_whenCoordinateIs00_shouldReturnCorrespondingCoordinates() {
        Set<Coordinate> expectedCoordinateSet = Set.of(new Coordinate(1, 0),
                new Coordinate(4, 0),
                new Coordinate(1, 1),
                new Coordinate(4, 1),
                new Coordinate(1, 4),
                new Coordinate(4, 4),
                new Coordinate(0, 1),
                new Coordinate(0, 4));

        Board board = new Board(5, 5);

        Set<Coordinate> neighbourCoordinateSet = board.getNeighbourCoordinates(new Coordinate(0, 0));

        assertEquals(expectedCoordinateSet, neighbourCoordinateSet);
    }

    @Test
    void getNeighbourCoordinates_whenCoordinateIs00AndThereIsOnlyOneCell_shouldReturnEmptySet() {
        Board board = new Board(1, 1);

        Set<Coordinate> neighbourCoordinateSet = board.getNeighbourCoordinates(new Coordinate(0, 0));

        assertEquals(Collections.emptySet(), neighbourCoordinateSet);
    }

    @Test
    void getNeighbourCoordinates_whenCoordinateIs00AndThereAreTwoCells_shouldReturnOneItemInSet() {
        Set<Coordinate> expectedCoordinateSet = Set.of(new Coordinate(1, 0));

        Board board = new Board(2, 1);

        Set<Coordinate> neighbourCoordinateSet = board.getNeighbourCoordinates(new Coordinate(0, 0));

        assertEquals(expectedCoordinateSet, neighbourCoordinateSet);
    }


}