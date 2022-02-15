package org.game.app.gol;

import org.game.app.Coordinate;

import java.util.Set;
import java.util.function.Predicate;

public class RuleEngine {


    public static boolean isActive(Set<Coordinate> activeCoordinateSet, Set<Coordinate> neighborSet, Predicate<Integer> neighborSumPredicate) {
        return neighborSumPredicate.test(getNeighborSum(activeCoordinateSet, neighborSet));
    }

    private static int getNeighborSum(Set<Coordinate> activeCoordinateSet, Set<Coordinate> neighborSet) {

        return neighborSet.stream()
                .filter(neighborCell -> activeCoordinateSet.contains(new Coordinate(neighborCell.getRowNo(), neighborCell.getColNo())))
                .map(state -> 1)
                .reduce(0, Integer::sum);
    }


}
