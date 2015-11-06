package com.company;

/*
Any live cell with fewer than two live neighbours dies, as if caused by under-population.
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies, as if by over-population.
Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
 */

public class Main {

    public static void main(String[] args) {
        World w = new World(5, 7);
        w.seedFirstGeneration();
        w.print();

//        for (int i = 0; i < 4; i++) {
//            w.print();
//            w.computeNextGeneration();
//        }


    }
}

