package org.game.app;

import org.game.app.gol.RuleEngine;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class RuleEngineTest {

    @Test
    void isActive_whenConditionMatches_shouldReturnTrue() {
        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(1, 1));
        Set<Coordinate> neighborSet = Set.of(new Coordinate(1, 1));
        Predicate<Integer> predicate = sum -> sum == 1;

        boolean active = RuleEngine.isActive(activeCoordinateSet, neighborSet, predicate);

        assertTrue(active);
    }

    @Test
    void isActive_whenConditionMatches_shouldReturnFalse() {
        Set<Coordinate> activeCoordinateSet = Set.of(new Coordinate(1, 1));
        Set<Coordinate> neighborSet = Set.of(new Coordinate(1, 0));
        Predicate<Integer> predicate = sum -> sum == 1;

        boolean active = RuleEngine.isActive(activeCoordinateSet, neighborSet, predicate);

        assertFalse(active);
    }

}