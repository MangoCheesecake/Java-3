package lab.pkg3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.*;

public class TicTacToeBoard extends JFrame
{
    private int playerTurn;
    private int turn;
    private int[][] gameState = new int [3][3];
    
    public TicTacToeBoard()
    {
        restartGame();
        
    }

    public void squarePressed(int row, int col, TicTacToeUI ui)
    {
        gameState[row][col] = playerTurn;
        int state = checkWin(row,col);
        if(state != 0)
        {
            ui.handleCheckWin(state);
        }
        else
        {
            advanceTurn();  
        }
        
    }
    
    public void advanceTurn()
    {
        if(playerTurn == 1)
        {
            playerTurn = 2;
        }
        else
        {
            playerTurn = 1;
        }
        turn++;
    }
    
    public void restartGame()
    {
        playerTurn = 1;
        turn = 1;
        for(int row = 0; row <3 ; row++)
        {
            for(int col = 0; col <3 ; col++)
            {
                gameState[row][col] = 0;
            }
        }
    }
    
    public int checkWin(int row, int col)
    {
        
        //check row
        for(int k = 0; k <3 ; k++)
        {
            if(gameState[row][k] != playerTurn)
            {
                break;
            }
            if(k == 2)
            {
                return playerTurn;
            }
        }

        //check col
        for(int k = 0; k <3 ; k++)
        {
            if(gameState[k][col] != playerTurn)
            {
                break;
            }
            if(k == 2)
            {
                return playerTurn;
            }
        }
        //check diagonal
        if(row == col){
            for(int i = 0; i < 3; i++){
                if(gameState[i][i] != playerTurn)
                {
                    break;
                }
                if(i == 2)
                {
                    return playerTurn;
                }
            }
        }
        
         if(row + col  == 2){
            for(int i = 0; i < 3; i++){
                if(gameState[i][2-i] != playerTurn)
                    break;
                if(i == 2)
                {
                    return playerTurn;
                }
            }
        }
         
         if(turn == 9)
         {
             return 3;
         }
         
         return 0;
        
    }
    
    public int getTurn()
    {
        return playerTurn;
    }

            
    public static void main(String[] args)
    {
        TicTacToeBoard board = new TicTacToeBoard();
        TicTacToeUI ui = new TicTacToeUI(board);
    }

}

