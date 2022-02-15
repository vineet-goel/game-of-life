package org.game.app;

import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {


    private static final Random randomGenerator = new Random();

    @Getter
    private final int maxRow;
    @Getter
    private final int maxCol;

    private Set<Coordinate> activeCoordinateSet;

    public Board(int maxRow, int maxCol) {
        validate(maxRow, maxCol);

        this.maxRow = maxRow;
        this.maxCol = maxCol;
        activeCoordinateSet = generateActiveCoordinates();
    }

    private void validate(int maxRow, int maxCol) {
        assert (maxRow > 0);
        assert (maxCol > 0);
    }

    public Set<Coordinate> getNeighbourCoordinates(Coordinate coordinate) {
        Set<Coordinate> neighborSet = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            int neighborRow = getNeighborIndex(coordinate.getRowNo(), maxRow, i);
            for (int j = -1; j <= 1; j++) {
                int neighborCol = getNeighborIndex(coordinate.getColNo(), maxCol, j);
                Coordinate neighborCoordinate = new Coordinate(neighborRow, neighborCol);
                neighborSet.add(neighborCoordinate);
            }
        }
        //Remove it's own coordinate
        neighborSet.remove(coordinate);
        return neighborSet;
    }

    private Set<Coordinate> generateActiveCoordinates() {

        int activeCoordinatesOutbound = randomGenerator.nextInt(maxRow * maxCol) + 1;
        return IntStream.range(0, activeCoordinatesOutbound)
                .mapToObj(index -> new Coordinate(randomGenerator.nextInt(maxRow), randomGenerator.nextInt(maxCol)))
                .collect(Collectors.toSet());
    }

    private int getNeighborIndex(int currentValue, int maxValue, int i) {
        int neighborIndex;
        if (currentValue == 0 && i == -1) {
            neighborIndex = maxValue - 1;
        } else if (currentValue + 1 == maxValue && i == 1) {
            neighborIndex = 0;
        } else {
            neighborIndex = currentValue + i;
        }
        return neighborIndex;
    }

    public Set<Coordinate> getActiveCoordinateSet() {
        return Collections.unmodifiableSet(activeCoordinateSet);
    }


    public void setActiveCoordinateSet(Set<Coordinate> activeCoordinateSet) {
        this.activeCoordinateSet = Collections.unmodifiableSet(activeCoordinateSet);
    }
}
