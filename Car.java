/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raceofrandom;

/*
 * COMP2112 Fall 2025 - Project 3: Race of Random
 * Group 2
 * Murat Karatas - 22COMP1015
 * Bugra Alp - 23SOFT1048
 */

public class Car {
    int number;
    int currentCheckpoint;
    int arrivalTime;
    LinkedList<Integer> path;
    boolean isFinished;
    boolean fellIntoPit;
    int finishPosition;
    
    public Car(int number) {
        this.number = number;
        this.currentCheckpoint = 0;
        this.arrivalTime = 0;
        this.path = new LinkedList<Integer>();
        this.path.insertLast(0);
        this.isFinished = false;
        this.fellIntoPit = false;
        this.finishPosition = -1;
    }
}