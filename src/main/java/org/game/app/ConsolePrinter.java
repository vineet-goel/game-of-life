package org.game.app;

public class ConsolePrinter {

    public static final String DEAD_CELL_VIEW = "_";
    public static final String LIVE_CELL_VIEW = "X";

    public static void print(Board board) {

        for (int i = 0; i < board.getMaxRow(); i++) {
            for (int j = 0; j < board.getMaxCol(); j++) {
                if (board.getActiveCoordinateSet().contains(new Coordinate(i, j))) {
                    System.out.print(LIVE_CELL_VIEW);
                } else {
                    System.out.print(DEAD_CELL_VIEW);
                }
            }
            System.out.println();
        }
    }
}
