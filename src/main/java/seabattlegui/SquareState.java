/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seabattlegui;

/**
 * Indicate the state of a square.
 * @author Nico Kuijpers
 */
public enum SquareState {
    WATER,        // No Ship is positioned at this square
    SHIP,         // A Ship is positioned at this square
    SHOTMISSED,   // A shot was fired at this square, but no hit
    SHOTHIT,      // A shot was fired at this square and a Ship was hit
    SHIPSUNK;     // A shot was fired at this square and a Ship is sunk
}
