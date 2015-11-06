package com.company;


import java.util.Random;

public class World {
    //declare 2D arrays (grid and nextGrid)
    private boolean [][] grid;
    private boolean [][] nextGrid;
    private final int cols;
    private final int rows;

    public World(int rows, int cols) {
        grid = new boolean[rows][cols];
        nextGrid = new boolean[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public void seedFirstGeneration() {
        Random rand = new Random();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int i = rand.nextInt(10);
                if (i == 0) {
                    grid[row][col] = true;
                }
            }
        }
    }

    public void print() {
        for (boolean [] row:grid) {
            for (boolean cell: row) {
                if (cell) {
                    System.out.print("#");
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public boolean isAliveAt(int col, int row) {
        // Since the caller added a delta to row and col, they may
        // go out of bounds. This wraps to keep in the grid.
        if (row < 0) {
            row = rows-1;
        } else if (row == rows) {
            row = 0;
        }

        if (col < 0) {
            col = cols - 1;
        } else if (col == cols) {
            col = 0;
        }

        return grid[row][col];
    }

    public void computeNextGeneration() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean alive = grid[row][col];
                int liveNeighbors = getLiveNeighborCount(row, col);

                boolean nextState = false;
                if (alive) {
                    if (liveNeighbors < 2) {
                        nextState = false;
                    } else if (liveNeighbors < 4) {
                        // 2-3 neighbors
                        nextState = true;
                    } else {
                        // more than 3
                        nextState = false;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        // reproduction
                        nextState = true;
                    }
                }

                nextGrid[row][col] = nextState;
            }
        }
    }


    public int getLiveNeighborCount(int row, int col) {
        int liveNeighborCount = 0;

        for (int dx=-1; dx<=1; dx++) {
            for (int dy=-1; dy<=1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                if (isAliveAt(row + dy, col + dx)) {
                    liveNeighborCount++;
                }
            }
        }

        return liveNeighborCount;
    }
}
