import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    public static char[][] map;
    public static final int SIZE = 5;

    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '.';
    public static int DOTS_TO_WIN = 4;

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isWinner(DOT_X)) {
                System.out.println("Победил игрок!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printMap();
            if (isWinner(DOT_O)) {
                System.out.println("Победил компьютер!");
                break;
            }


            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Введите координаты в формате X и Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isСellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static void aiTurn() {
        int x;
        int y;
        Random rand = new Random();
        do {
            System.out.println("Введите координаты в формате X и Y");
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isСellValid(x, y));
        map[y][x] = DOT_O;
    }

    public static boolean isСellValid(int x, int y) {
        if (0 > x || 0 > y || x >= SIZE || y >= SIZE) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

        static boolean isWinner(char symb) {
            int fullSet = map.length - DOTS_TO_WIN;
            for (int rowStrike = 0; rowStrike <= fullSet; rowStrike++) {
                if (fullDiagonalStrike(symb, rowStrike)) {
                    return true;
                }
                for (int columnStrike = 0; columnStrike <= fullSet; columnStrike++) {
                    boolean checkWin =
                            fullStrike(symb, rowStrike, columnStrike);
                    if (checkWin) {
                        return true;
                    }
                }
            }
            return false;
        }
        // Вариант подсчета победы с урока
    //    {
//        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) {
//            return true;
//        }
//        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) {
//            return true;
//        }
//        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) {
//            return true;
//        }
//        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) {
//            return true;
//        }
//        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) {
//            return true;
//        }
//        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) {
//            return true;
//        }
//        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) {
//            return true;
//        }
//        if (map[0][2] == symb && map[1][1] == symb && map[2][0] == symb) {
//            return true;
//        }
//        return false;
//        }
    //вариант подсчета победы в дз
    static boolean fullStrike(char symb, int rowStrike, int columnStrike) {
        for (int row = rowStrike; row < (DOTS_TO_WIN + rowStrike); row++) {
            int horizontalLine = 0;
            int verticalLine = 0;
            for (int column = columnStrike; column < (DOTS_TO_WIN + columnStrike); column++) {
                if (map[row][column] == symb) {
                    horizontalLine++;
                } else {
                    horizontalLine = 0;
                }
                if (map[column][row] == symb) {
                    verticalLine++;
                } else {
                    verticalLine = 0;
                }
            }
            if ((horizontalLine == DOTS_TO_WIN) || (verticalLine == DOTS_TO_WIN)) {
                return true;
            }
        }
        return false;
    }

    static boolean fullDiagonalStrike(char symb, int rowStrike) {
        int firstDiagonalLine = 0;
        int secondDiagonalLine = 0;
        final int subSquareLength = (DOTS_TO_WIN + rowStrike);
        for (int row = rowStrike; row < subSquareLength; row++) {
            if (map[row][row] == symb) {
                firstDiagonalLine++;
            } else {
                firstDiagonalLine = 0;
            }
            if (map[row][map.length - 1 - row] == symb) {
                secondDiagonalLine++;
            } else {
                secondDiagonalLine = 0;
            }
        }
        return (firstDiagonalLine == DOTS_TO_WIN) || (secondDiagonalLine == DOTS_TO_WIN);
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}



