/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seabattlegui;

/**
 * Interface provided by the graphical user interface of the sea battle game.
 * @author Nico Kuijpers
 */
public interface ISeaBattleGUI {
    
    /**
     * Set the name of the Player in the GUI.
     * @param playerNr identification of Player
     * @param name Player's name
     */
    public void setPlayerName(int playerNr, String name);
    
    /**
     * Set the name of the opponent in the GUI.
     * @param playerNr identification of Player
     * @param name opponent's name
     */
    public void setOpponentName(int playerNr, String name);
    
    /**
     * Communicate the result of a shot fired by the opponent.
     * The result of the shot will be one of the following:
     * MISSED  - No Ship was hit
     * HIT     - A Ship was hit
     * SUNK    - A Ship was sunk
     * ALLSUNK - All ships are sunk
     * @param playerNr identification of Player
     * @param shotType result of shot fired by opponent
     */
    public void opponentFiresShot(int playerNr, ShotType shotType);
    
    /**
     * Show state of a square in the ocean area.
     * The new state of the square will be shown in the area where
     * the ships of the Player are placed (ocean area).
     * @param playerNr identification of Player
     * @param posX        x-position of square
     * @param posY        y-position of square
     * @param squareState state of square
     */
    public void showSquarePlayer(int playerNr, int posX, int posY, SquareState squareState);
    
    /**
     * Show state of a square in the target area.
     * The new state of the square will be shown in the area where
     * the ships of the opponent are placed (target area)
     * @param playerNr identification of Player
     * @param posX        x-position of square
     * @param posY        y-position of square
     * @param squareState state of square
     */
    public void showSquareOpponent(int playerNr, int posX, int posY, SquareState squareState);
}
