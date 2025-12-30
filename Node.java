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

public class Node<T> {
    T data;
    Node<T> next;
    
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}