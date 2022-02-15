package org.game.app;

import lombok.SneakyThrows;
import org.game.app.gol.GameOfLife;

public class Application {

    private static final int MAX_ROW = 10;
    private static final int MAX_COL = 10;

    public static void main(String[] args) {
        playGOL();
    }

    @SneakyThrows
    private static void playGOL() {
        Board board = new Board(MAX_ROW, MAX_COL);

        // Terminate the game if the mother earth is hit by an asteroid and no human life is left
        while (board.getActiveCoordinateSet().size() > 0) {
            clearConsole();
            ConsolePrinter.print(board);
            GameOfLife.generateNextGeneration(board);
            Thread.sleep(500);
        }
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }







}
