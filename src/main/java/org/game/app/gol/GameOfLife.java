package org.game.app.gol;

import lombok.Value;
import org.game.app.Board;
import org.game.app.Coordinate;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Value
public class GameOfLife {

    private static final Predicate<Integer> ALIVE_NEIGHBOR_PREDICATE = sum -> sum == 3;
    private static final Predicate<Integer> SURVIVE_NEIGHBOR_PREDICATE = sum -> sum == 2 || sum == 3;


    public static void generateNextGeneration(Board board) {
        Set<Coordinate> nextGenActiveCoordinateSet = new HashSet<>();

        //Only two types of cells can alive/sustain active state. Rest of the cells in space can be ignored
        // 1. Cells which that are already active
        // 2. Dead cells that are neighbor of active cells

        for (Coordinate activeCoordinate : board.getActiveCoordinateSet()) {
            Set<Coordinate> activeCoordinateNeighbours = board.getNeighbourCoordinates(activeCoordinate);
            if (RuleEngine.isActive(board.getActiveCoordinateSet(), activeCoordinateNeighbours, SURVIVE_NEIGHBOR_PREDICATE)) {
                nextGenActiveCoordinateSet.add(new Coordinate(activeCoordinate.getRowNo(), activeCoordinate.getColNo()));
            }
        }

        for (Coordinate deadCoordinate : getDeadNeighbourCoordinates(board)) {
            Set<Coordinate> deadNeighbours = board.getNeighbourCoordinates(deadCoordinate);
            if (RuleEngine.isActive(board.getActiveCoordinateSet(), deadNeighbours, ALIVE_NEIGHBOR_PREDICATE)) {
                nextGenActiveCoordinateSet.add(new Coordinate(deadCoordinate.getRowNo(), deadCoordinate.getColNo()));
            }
        }

        board.setActiveCoordinateSet(nextGenActiveCoordinateSet);
    }


    private static Set<Coordinate> getDeadNeighbourCoordinates(Board board) {
        Set<Coordinate> deadNeighbourCoordinateSet = new HashSet<>();
        for (Coordinate coordinate : board.getActiveCoordinateSet()) {
            Set<Coordinate> neighbourSet = board.getNeighbourCoordinates(coordinate);
            deadNeighbourCoordinateSet.addAll(neighbourSet);
        }
        deadNeighbourCoordinateSet.removeAll(board.getActiveCoordinateSet());
        return deadNeighbourCoordinateSet;
    }
}
