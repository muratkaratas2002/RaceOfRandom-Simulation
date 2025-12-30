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

public class Stack<T> {
    private LinkedList<T> list;
    
    public Stack() {
        list = new LinkedList<T>();
    }
    
    public void push(T item) {
        list.insertFirst(item);
    }
    
    public T pop() {
        return list.removeFirst();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int size() {
        return list.size();
    }
}
