package org.game.app;

import org.game.app.gol.GameOfLife;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameOfLifeTest {

    @Test
    void getActiveCells_whenGridContainsNoActiveCell_shouldReturnNoActiveCell() {
        Set<Coordinate> activeCoordinateSet = Set.of();
        Board board = new Board(4, 4);
        board.setActiveCoordinateSet(activeCoordinateSet);

        GameOfLife.generateNextGeneration(board);

        assertEquals(0, board.getActiveCoordinateSet().size());
    }

    @Test
    void getActiveCells_whenGridContainsOneActiveCell_shouldReturnNoActiveCell() {
        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(2, 0));
        Board board = new Board(4, 4);
        board.setActiveCoordinateSet(activeCoordinateSet);

        GameOfLife.generateNextGeneration(board);

        assertEquals(0, board.getActiveCoordinateSet().size());
    }

    @Test
    void getActiveCells_whenGridContainsTwoActiveCell_shouldReturnNoActiveCell() {
        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(2, 0),
                new Coordinate(2, 1));
        Board board = new Board(4, 4);
        board.setActiveCoordinateSet(activeCoordinateSet);

        GameOfLife.generateNextGeneration(board);

        assertEquals(0, board.getActiveCoordinateSet().size());
    }

    @Test
    void getActiveCells_whenGridContainsThreeActiveCellFarApart_shouldReturnTwoActiveCells() {
        Set<Coordinate> expectedActiveCoordinates = Set.of(new Coordinate(0, 1),
                new Coordinate(0, 3));

        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(0, 0),
                new Coordinate(3, 0),
                new Coordinate(1, 2));
        Board board = new Board(4, 4);
        board.setActiveCoordinateSet(activeCoordinateSet);

        GameOfLife.generateNextGeneration(board);

        assertEquals(2, board.getActiveCoordinateSet().size());
        assertEquals(expectedActiveCoordinates, board.getActiveCoordinateSet());
    }

    @Test
    void getActiveCells_whenGridContainsThreeActiveCellInTriangleFormat_shouldReturnFourActiveCells() {
        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(2, 2));

        Set<Coordinate> expectedActiveCoordinates = Set.of(new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(2, 2),
                new Coordinate(1, 2));

        Board board = new Board(4, 4);
        board.setActiveCoordinateSet(activeCoordinateSet);

        GameOfLife.generateNextGeneration(board);

        assertEquals(4, board.getActiveCoordinateSet().size());
        assertEquals(expectedActiveCoordinates, board.getActiveCoordinateSet());
    }

    @Test
    void getActiveCells_whenGridContainsThreeActiveCellInOneLine_shouldReturnOneActiveCell() {
        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(1, 3));

        Set<Coordinate> expectedActiveCoordinates = Set.of(new Coordinate(2, 2),
                new Coordinate(1, 2),
                new Coordinate(0, 2));

        Board board = new Board(4, 4);
        board.setActiveCoordinateSet(activeCoordinateSet);

        GameOfLife.generateNextGeneration(board);

        assertEquals(3, board.getActiveCoordinateSet().size());
        assertEquals(expectedActiveCoordinates, board.getActiveCoordinateSet());
    }

    @Test
    void getActiveCells_whenGridContainsThreeActiveCell_shouldReturnFourActiveCells() {
        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(2, 0),
                new Coordinate(2, 3),
                new Coordinate(3, 3));
        Board board = new Board(4, 4);
        board.setActiveCoordinateSet(activeCoordinateSet);

        GameOfLife.generateNextGeneration(board);

        Set<Coordinate> expectedActiveCoordinates = Set.of(new Coordinate(2, 0),
                new Coordinate(2, 3),
                new Coordinate(3, 3),
                new Coordinate(3, 0));
        assertEquals(4, board.getActiveCoordinateSet().size());
        assertEquals(expectedActiveCoordinates, board.getActiveCoordinateSet());
    }


}