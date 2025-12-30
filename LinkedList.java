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

public class LinkedList<T> {
    Node<T> first;
    Node<T> last;
    int size;
    
    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }
    
    public void insertFirst(T data) {
        Node<T> newNode = new Node<T>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }
    
    public void insertLast(T data) {
        Node<T> newNode = new Node<T>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }
    
    public T removeFirst() {
        if (first == null) return null;
        
        T data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size--;
        return data;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }
}
