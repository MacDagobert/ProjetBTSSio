/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tetriste2;

/**
 *
 * @author Victor
 */
public class Grille {
    public final int GRID_WIDTH = 10;
    public final int GRID_HEIGHT = 20;
    public final int BLOCK_SIZE = 30;

    public boolean[][] grid = new boolean[GRID_HEIGHT][GRID_WIDTH]; // Grille de jeu
    private Tetromino currentTetromino; // Tetromino actuel
    private int tetrominoX = 4, tetrominoY = 0; // Position du tetromino

    public int getGRID_WIDTH() {
        return GRID_WIDTH;
    }

    public int getGRID_HEIGHT() {
        return GRID_HEIGHT;
    }

    public int getBLOCK_SIZE() {
        return BLOCK_SIZE;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    public int getTetrominoX() {
        return tetrominoX;
    }

    public int getTetrominoY() {
        return tetrominoY;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }

    public void setCurrentTetromino(Tetromino currentTetromino) {
        this.currentTetromino = currentTetromino;
    }

    public void setTetrominoX(int tetrominoX) {
        this.tetrominoX = tetrominoX;
    }

    public void setTetrominoY(int tetrominoY) {
        this.tetrominoY = tetrominoY;
    }
    
    
    
    
    
}
